package com.common.server.service;

import com.common.server.domain.Rolemenu;
import com.common.server.domain.SysMenu;
import com.common.server.mapper.RolemenuMapper;
import com.common.server.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class RolemenuService {
    @Autowired
    private RolemenuMapper rolemenuMapper;
    @Autowired
    private MenuService menuService;

    public List<Rolemenu> list() {
        return rolemenuMapper.selectAll();
    }

    @Transactional
    public void insert(Rolemenu rolemenu) {
        Rolemenu select = this.rolemenuMapper.selectOne(rolemenu);
        if (select == null) {
            if (rolemenu.getIsck() == 1) {
                this.rolemenuMapper.insert(rolemenu);
            }

        } else {
            if (rolemenu.getIsck() == 0) {
                this.rolemenuMapper.delete(rolemenu);
            }
        }
    }

    public void update(Rolemenu rolemenu) {
        rolemenuMapper.updateByPrimaryKey(rolemenu);
    }

    public Rolemenu findOne(Rolemenu rolemenu) {
        return this.rolemenuMapper.selectOne(rolemenu);
    }

    //获取单个对象
    public Rolemenu findOne(Long id) {
        Rolemenu rolemenu = new Rolemenu();
        rolemenu.setId(id);
        return this.rolemenuMapper.selectOne(rolemenu);
    }


    public void del(Long id) {
        Rolemenu rolemenu = this.findOne(id);


        if (rolemenu != null) {
            rolemenuMapper.delete(rolemenu);
        }
    }

    public PageResult<Rolemenu> findAllByPage(Integer page, Integer rows, String key, String sortBy, Boolean desc) {
        Example example = new Example(Rolemenu.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        PageHelper.startPage(page, rows);
        List<Rolemenu> rolemenus = this.rolemenuMapper.selectByExample(example);
        PageInfo<Rolemenu> pageInfo = new PageInfo<>(rolemenus);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Transactional
    public void delByrm(Long rid, Long mid) {

        Rolemenu one = this.findOne(new Rolemenu(mid, rid));
        if (one != null) {
            SysMenu menu = menuService.findOne(mid);
            if (menu.getPid() > 0) {
                this.del(one.getId());
            } else {
                List<SysMenu> menus = this.menuService.queryBypid(menu.getId());
                menus.forEach(sysMenu -> {
                    Rolemenu rolemenu = this.findOne(new Rolemenu(sysMenu.getId(), rid));
                    if (rolemenu != null) {
                        this.del(rolemenu.getId());
                    }
                });
                this.del(one.getId());
            }

        }
    }

    public List<Rolemenu> queryBymid(Long id) {
        Rolemenu rolemenu = new Rolemenu();
        rolemenu.setMenuid(id);
        return this.rolemenuMapper.select(rolemenu);
    }

    public List<Rolemenu> queryByrid(Long id) {
        Rolemenu rolemenu = new Rolemenu();
        rolemenu.setRoleid(id);
        return this.rolemenuMapper.select(rolemenu);
    }
}
