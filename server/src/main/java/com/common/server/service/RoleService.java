package com.common.server.service;

import com.common.server.mapper.RoleMapper;
import com.common.server.domain.Role;
import com.common.server.domain.Rolemenu;
import com.common.server.domain.User;
import com.common.server.mapper.UserMapper;
import com.common.server.utils.TabulatorRes;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.common.server.utils.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RolemenuService rolemenuService;

    //获取列表
    public List<Role> list() {
        return roleMapper.selectAll();
    }

    //增加
    public void insert(Role role) {
        roleMapper.insert(role);
    }

    //修改
    public void update(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    //获取单个对象
    public Role findOne(Long id) {
        Role role = new Role();
        role.setId(id);
        role = this.roleMapper.selectOne(role);
        return role;
    }

    //删除
    @Transactional
    public void del(Long id) {
        Role role = this.findOne(id);
        if (role != null) {
            User user = new User();
            user.setRid(role.getId());
            List<User> users = this.userMapper.select(user);
            users.forEach(c -> {
                userService.del(c.getId());
            });
            List<Rolemenu> rolemenus=rolemenuService.queryByrid(id);
            rolemenus.forEach(rolemenu -> {
                this.rolemenuService.del(rolemenu.getId());
            });

            roleMapper.delete(role);
        }
    }

    //分页
    public PageResult<Role> findAllByPage(Integer page, Integer rows, String key, String sortBy, Boolean desc) {
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        PageHelper.startPage(page, rows);
        List<Role> roles = this.roleMapper.selectByExample(example);
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    public TabulatorRes<Role> findTabulatorRes(Integer page, Integer size, String key, List<String> filter, List<String> sorters) {
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        PageHelper.startPage(page, size);
        List<Role> roles  = this.roleMapper.selectByExample(example);
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        Integer totalpage=this.list().size()%size>0?this.list().size()/size+1:this.list().size()/size;
        return new TabulatorRes<>(totalpage, pageInfo.getList());
    }
}
