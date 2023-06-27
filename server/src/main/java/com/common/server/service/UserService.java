package com.common.server.service;

import com.common.server.domain.Actions;
import com.common.server.domain.Msg;
import com.common.server.domain.User;
import com.common.server.domain.Voice;
import com.common.server.mapper.RoleMapper;
import com.common.server.mapper.SysMapper;
import com.common.server.mapper.UserMapper;
import com.common.server.mapper.VoiceMapper;
import com.common.server.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {


    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private SysMapper sysMapper;
    @Autowired
    private SysService sysService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private VoiceMapper voiceMapper;
    @Autowired
    private VoiceService voiceService;
    @Autowired
    private MsgService msgService;
    @Autowired
    private ActionsService actionsService;

    //获取列表
    public List<User> list(Long rid,Long cid) {
        User user = new User();
        if (rid!=null){
            user.setRid(rid);
        }
       if (cid!=null){
           user.setUserclassid(cid);
       }
        return userMapper.select(user);
    }

    //增加
    public void insert(User user) {
        if (user.getRid()==3){
            user.setStatus(0);
        }else {
            user.setStatus(1);
        }
        userMapper.insert(user);
    }

    //修改
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    //获取单个对象
    public User findOne(Long id) {
        User user = new User();
        user.setId(id);



        return this.userMapper.selectOne(user);
    }
    public User findOneUserId(Long id) {
        User user = new User();
        user.setUserid(id);
        user = this.userMapper.selectOne(user);


        return user;
    }
    //删除
    public void del(Long id) {
        User user = this.findOne(id);
        if (user != null) {

            Voice voice = new Voice();
            voice.setUser_Id(user.getId());
            List<Voice> voices = this.voiceMapper.select(voice);
            voices.forEach(c -> {
                voiceService.del(c.getId());
            });
            userMapper.delete(user);
        }
    }

    //分页
    public PageResult<User> findAllByPage(Integer page, Integer rows, String key, String sortBy, Boolean desc, Long rid) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%");
        }
        if (rid != null) {
            criteria.andEqualTo("rid", rid);
        }
        PageHelper.startPage(page, rows);
        List<User> users = this.userMapper.selectByExample(example);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        List<User> collect = pageInfo.getList().stream().map(item -> {
            return this.findOne(item.getId());
        }).collect(Collectors.toList());
        Integer size = Math.toIntExact(pageInfo.getTotal() % rows > 0 ? pageInfo.getTotal() / rows + 1 : pageInfo.getTotal() / rows);
        return new PageResult<>(pageInfo.getTotal(), size, collect);
    }

    public User login(String username, String password) {
        Long uid = Long.valueOf(0);
        User users = new User();
//        users.setAccount(username);
//        users.setPassword(password);
        users = this.userMapper.login(username,password);
//        if (users!=null){
//
//        }
//        if (users.getRid()==2){
//            Animalhelp oneByUid = animalhelpService.findOneByUid(users.getId());
//            if (oneByUid!=null){
//                users.setAnimalhelp(oneByUid);
//            }
//        }
        return users;
    }

    public void changwPwd(Long uid, String pwd) {
        User one = this.findOne(uid);
        one.setPassword(pwd);
        this.update(one);
    }

    public User getAccount(String account) {
        User user = new User();
        user.setAccount(account);
        return userMapper.selectOne(user);
    }

    public String getQues(Long uid) {
        String cons = "";
        User user = this.findOne(uid);

        return cons;
    }
    public List<User> queryBycid(Long cid) {
        User user = new User();
        user.setUserclassid(cid);
        return userMapper.select(user);
    }
    public List<User> queryBycid(Long cid, Long rid) {
        User user = new User();
        user.setUserclassid(cid);
        user.setRid(rid);
        return userMapper.select(user);
    }

    public List<User> queryClassUserByUid(Long uid, Long rid) {
        User one = this.findOne(uid);
        return queryBycid(one.getUserclassid(), rid);
    }

    @Transactional
    public List<User> sendMsg(String userId, Long aid, Long status, Long rid) throws IOException {
        Actions one = actionsService.findOne(aid);
        one.setStatus(status);
        actionsService.update(one);
        if (rid.intValue()==1||rid.intValue()==2){
            rid= Long.valueOf(3);
        }else {
            rid= Long.valueOf(4);
        }

        List<User> users = queryBycid(one.getCid(),rid);

        for (User user : users) {
            Msg msg = new Msg();
            msg.setAid(aid);
            msg.setUid(user.getId());
            msg.setIsread(0);
            user.setActionName(one.getName());
            msgService.insert(msg);
        }
        return users;

    }

    public User QueryOne(User user){
        return userMapper.selectOne(user);
    }
}
