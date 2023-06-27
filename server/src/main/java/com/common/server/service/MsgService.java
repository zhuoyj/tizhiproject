package com.common.server.service;

import com.common.server.domain.Actions;
import com.common.server.domain.Msg;
import com.common.server.domain.User;
import com.common.server.mapper.MsgMapper;
import com.common.server.utils.PageResult;
import com.common.server.utils.TabulatorRes;
import com.common.server.utils.WebSocket;
import com.common.server.utils.WebsocketClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MsgService {
    @Autowired
    private final static ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private MsgMapper msgMapper;
    @Autowired
    private UserClassService userClassService;
    @Autowired
    private UserService userService;
    @Autowired
    private WebSocket webSocket;
    @Autowired
    private ActionsService actionsService;



    public List<Msg> list(Long aid, Long uid) {
        Msg msg = new Msg();
        if (aid != null) {
            msg.setAid(aid);
        }
        if (uid != null) {
            msg.setUid(uid);
        }
        msg.setIsread(0);
        List<Msg> select = msgMapper.select(msg);
        List<Msg> collect = select.stream().map(c -> {
            Msg one = this.findOne(c.getId());
            return one;
        }).collect(Collectors.toList());
        return collect;
    }

    @Transactional
    public void insert(Msg msg) throws IOException {
        Actions actions = actionsService.findOne(msg.getAid());
        User sendUser = userService.findOne(msg.getSenduid());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(new Date());
        msg.setTimes(format);
        if (msg.getRid() == 2||msg.getRid() == 1) {
            if (actions!=null){
                List<Actions> aclist = actionsService.findOneByExId(actions.getExid());
                if (CollectionUtils.isNotEmpty(aclist)){
                    for (Actions actions1 : aclist) {
                        List<User> list = userService.list(Long.valueOf(3),actions1.getCid());
                        if (CollectionUtils.isNotEmpty(list)){
                            for (User user : list) {
                                Msg model = new Msg();
                                model.setAid(msg.getAid());
                                model.setUid(user.getId());
                                model.setTimes(format);
                                model.setIsread(0);
                                msgMapper.insert(model);
                                String pringtStr = sendUser.getName() + "给您推送了" + actions.getName() + "的详细信息";
                                if (msg.getContents()!=null){
                                    pringtStr=msg.getContents();
                                }
                                WebsocketClass websocketClass = new WebsocketClass();
                                websocketClass.setPrints(pringtStr);
                                SimpleDateFormat msgsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                websocketClass.setTimes(msgsdf.format(new Date()));
                                websocketClass.setUname(msg.getAid().toString());
                                webSocket.sendMessageByUserId(String.valueOf(user.getId()), MAPPER.writeValueAsString(websocketClass));
                            }
                        }
                    }
                }
            }
//            if (StringUtils.isNotBlank(msg.getCids())) {
//                String[] strings = StringUtils.split(msg.getCids(), ",");
//                for (String s : strings) {
//                    List<User> list = userService.list(Long.valueOf(3), Long.valueOf(s));
//                    if (CollectionUtils.isNotEmpty(list)){
//                        for (User user : list) {
//                            Msg model = new Msg();
//                            model.setAid(msg.getAid());
//                            model.setUid(user.getId());
//                            model.setTimes(format);
//                            model.setIsread(0);
//                            msgMapper.insert(model);
//                            String pringtStr = sendUser.getName() + "给您推送了" + actions.getName() + "的详细信息";
//                            WebsocketClass websocketClass = new WebsocketClass();
//                            websocketClass.setPrints(pringtStr);
//                            SimpleDateFormat msgsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                            websocketClass.setTimes(msgsdf.format(new Date()));
//                            websocketClass.setUname(msg.getAid().toString());
//                            webSocket.sendMessageByUserId(String.valueOf(user.getId()), MAPPER.writeValueAsString(websocketClass));
//                        }
//                    }
//
//                }
//            }
            List<Actions> oneByExId = actionsService.findOneByExId(actions.getExid());
            for (Actions actions1 : oneByExId) {
                actions1.setStatus(Long.valueOf(1));
                actionsService.update(actions1);
            }
        }
        if (msg.getRid() == 3) {

            List<User> list = userService.list(Long.valueOf(4), msg.getCid());
            for (User user : list) {
                Msg model = new Msg();
                model.setAid(msg.getAid());
                model.setUid(user.getId());
                model.setTimes(format);
                model.setIsread(0);
                msgMapper.insert(model);
                String pringtStr = sendUser.getName() + "给您推送了" + actions.getName() + "的详细信息";
                if (msg.getContents()!=null){
                    pringtStr=msg.getContents();
                }
                WebsocketClass websocketClass = new WebsocketClass();
                websocketClass.setPrints(pringtStr);
                SimpleDateFormat msgsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                websocketClass.setTimes(msgsdf.format(new Date()));
                websocketClass.setUname(msg.getAid().toString());
                webSocket.sendMessageByUserId(String.valueOf(user.getId()), MAPPER.writeValueAsString(websocketClass));
            }
            List<Actions> oneByExId = actionsService.findOneByExId(actions.getExid());
            for (Actions actions1 : oneByExId) {
                actions1.setStatus(Long.valueOf(2));
                actionsService.update(actions1);
            }
        }
    }

    public void update(Msg msg) {
        msgMapper.updateByPrimaryKey(msg);

    }

    //获取单个对象
    public Msg findOne(Long id) {
        Msg msg = new Msg();
        msg.setId(id);
        Msg selectOne = this.msgMapper.selectOne(msg);
        Actions one = actionsService.findOne(selectOne.getAid());
        if (one != null) {
            selectOne.setAname(one.getName());
        }
        User user = userService.findOne(selectOne.getUid());
        if (user != null) {
            selectOne.setUname(user.getName());
        }
        return selectOne;
    }


    public void del(Long id) {
        Msg msg = this.findOne(id);
        if (msg != null) {
            msgMapper.delete(msg);
        }
    }


    public PageResult<Msg> findAllByPage(Integer page, Integer rows, String key, String sortBy, Boolean desc) {
        Example example = new Example(Msg.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        PageHelper.startPage(page, rows);
        List<Msg> msgs = this.msgMapper.selectByExample(example);
        PageInfo<Msg> pageInfo = new PageInfo<>(msgs);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    public TabulatorRes<Msg> findTabulatorRes(Integer page, Integer size, String key, List<String> filter, List<String> sorters) {
        Example example = new Example(Msg.class);
        Example.Criteria criteria = example.createCriteria();
        PageHelper.startPage(page, size);
        List<Msg> msgs = this.msgMapper.selectByExample(example);
        PageInfo<Msg> pageInfo = new PageInfo<>(msgs);
        Integer totalpage = msgs.size() % size > 0 ? msgs.size() / size + 1 : msgs.size() / size;
        return new TabulatorRes<>(totalpage, pageInfo.getList());
    }

    public void changeMsgStatus(Msg msg) {
        Msg model = new Msg();
        model.setAid(msg.getAid());
        model.setUid(msg.getUid());
        List<Msg> selects = this.msgMapper.select(model);
        for (Msg select : selects) {
            select.setIsread(1);
            this.update(select);
        }

    }
}
