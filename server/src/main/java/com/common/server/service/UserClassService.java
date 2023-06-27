package com.common.server.service;

import com.common.server.domain.User;
import com.common.server.domain.UserClass;
import com.common.server.mapper.UserClassMapper;
import com.common.server.utils.PageResult;
import com.common.server.utils.TabulatorRes;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserClassService {
    @Autowired
    private UserClassMapper userClassMapper;


    public List<UserClass> list(Long cid) {
        UserClass userClass=new UserClass();
        if (cid!=null){
            userClass.setId(cid);
        }
        return userClassMapper.select(userClass);
    }


    public void insert(UserClass userClass) {

        userClassMapper.insert(userClass);
    }


    public void update(UserClass userClass) {
        userClassMapper.updateByPrimaryKey(userClass);

    }

    //获取单个对象
    public UserClass findOne(Long id) {
        UserClass userClass = new UserClass();
        userClass.setId(id);


        return this.userClassMapper.selectOne(userClass);
    }


    public void del(Long id) {
        UserClass userClass = this.findOne(id);
        if (userClass != null) {
            userClassMapper.delete(userClass);
        }
    }


    public PageResult<UserClass> findAllByPage(Integer page, Integer rows, String key, String sortBy, Boolean desc) {
        Example example = new Example(UserClass.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        PageHelper.startPage(page, rows);
        List<UserClass> userClasss = this.userClassMapper.selectByExample(example);
        PageInfo<UserClass> pageInfo = new PageInfo<>(userClasss);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    public TabulatorRes<UserClass> findTabulatorRes(Integer page, Integer size, String key, List<String> filter, List<String> sorters) {
        Example example = new Example(UserClass.class);
        Example.Criteria criteria = example.createCriteria();
        PageHelper.startPage(page, size);
        List<UserClass> userClasss = this.userClassMapper.selectByExample(example);
        PageInfo<UserClass> pageInfo = new PageInfo<>(userClasss);

        Integer totalpage = userClasss.size() % size > 0 ? userClasss.size() / size + 1 : userClasss.size() / size;
        return new TabulatorRes<>(totalpage, pageInfo.getList());
    }
    public UserClass QueryOne(UserClass userClass){
        return userClassMapper.selectOne(userClass);
    }
}
