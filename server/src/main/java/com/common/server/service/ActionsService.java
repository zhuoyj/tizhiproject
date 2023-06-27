package com.common.server.service;

import com.common.server.domain.Actions;
import com.common.server.domain.Msg;
import com.common.server.domain.User;
import com.common.server.domain.UserClass;
import com.common.server.mapper.ActionsMapper;
import com.common.server.utils.IdWorker;
import com.common.server.utils.PageResult;
import com.common.server.utils.TabulatorRes;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActionsService {
    @Autowired
    private ActionsMapper actionsMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserClassService userClassService;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MsgService msgService;


    public List<Actions> list() {
        return actionsMapper.selectAll();
    }

    @Transactional
    public void insert(Actions actions) {
        List<UserClass> list = userClassService.list(null);
        Long exid=idWorker.nextId();
        for (UserClass userClass : list) {
            Actions ac=new Actions();
            BeanUtils.copyProperties(actions,ac);
            ac.setExid(exid);
            ac.setCid(userClass.getId());
            actionsMapper.insert(ac);
        }

    }

    @Transactional
    public void update(Actions actions) throws IOException {
        List<Actions> list = findOneByExId(actions.getExid());

        for (Actions ac : list) {
            Long id=ac.getId();
            Long cid=ac.getCid();
            BeanUtils.copyProperties(actions,ac);
            ac.setId(id);
            ac.setCid(cid);
            actionsMapper.updateByPrimaryKey(ac);
        }


    }
    public List<Actions> findOneByExId(Long id) {
        Actions actions = new Actions();
        actions.setExid(id);
        return this.actionsMapper.select(actions);
    }
    //获取单个对象
    public Actions findOne(Long id) {
        Actions actions = new Actions();
        actions.setId(id);


        return this.actionsMapper.selectOne(actions);
    }

    public Actions findOne(String name) {
        Actions actions = new Actions();
        actions.setName(name);


        return this.actionsMapper.selectOne(actions);
    }

    public void del(Long id) {
        Actions one = this.findOne(id);
        if (one!=null){
            List<Actions> list = findOneByExId(one.getExid());
            for (Actions ac : list) {
                actionsMapper.delete(ac);
            }
        }

    }

    public PageResult<Actions> findAllByPage(Integer page, Integer rows, String key, String sortBy, Boolean desc) {
        Example example = new Example(Actions.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        PageHelper.startPage(page, rows);
        List<Actions> actionss = this.actionsMapper.selectByExample(example);
        PageInfo<Actions> pageInfo = new PageInfo<>(actionss);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    public TabulatorRes<Actions> findTabulatorRes(Integer page, Integer size, String key, List<String> filter, List<String> sorters, Integer tid, Long uid, Integer status) {
        Example example = new Example(Actions.class);
        Example.Criteria criteria = example.createCriteria();
        if (tid != null) {
            criteria.andEqualTo("typeid", tid);
        }
        if (uid != null) {
            criteria.andEqualTo("uid", uid);
        }
        if (status!=null){
            criteria.andGreaterThanOrEqualTo("status",status);
        }
        PageHelper.startPage(page, size);
        Page<Actions> actionss = (Page<Actions>) this.actionsMapper.queryByExample(tid,uid,status);
        List<Actions> collect = actionss.stream().map(c -> {
            if (c.getUid() != null) {
                User one = this.userService.findOne(c.getUid());
                if (one != null) {
                    c.setUname(one.getName());
                }

            }
            if (c.getCid() != null) {
                UserClass one = this.userClassService.findOne(c.getCid());
                if (one != null) {
                    c.setCname(one.getName());
                }

            }
            return c;
        }).collect(Collectors.toList());
        Integer totalpage = actionss.size() % size > 0 ? actionss.size() / size + 1 : actionss.size() / size;
        return new TabulatorRes<>(totalpage, actionss);
    }
}
