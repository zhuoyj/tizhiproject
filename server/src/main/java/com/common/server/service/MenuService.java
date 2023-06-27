package com.common.server.service;

import com.common.server.domain.Rolemenu;

import com.common.server.domain.SysMenu;
import com.common.server.domain.bo.MenuEx;
import com.common.server.mapper.MenuMapper;
import com.common.server.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RolemenuService rolemenuService;

    public List<SysMenu> list() {
        return menuMapper.selectAll();
    }

    public List<SysMenu> queryByRid(Long rid){
        return menuMapper.queryByRid(rid);
    }


    public void insert(SysMenu menu) {

        menuMapper.insert(menu);
    }


    public void update(SysMenu menu) {
        menuMapper.updateByPrimaryKey(menu);

    }

    //获取单个对象
    public SysMenu findOne(Long id) {
        SysMenu menu = new SysMenu();
        menu.setId(id);


        return this.menuMapper.selectOne(menu);
    }


    @Transactional
    public void del(Long id) {
        SysMenu menu = this.findOne(id);
        if (menu != null) {
         List<Rolemenu> rolemenus=   rolemenuService.queryBymid(id);
            rolemenus.forEach(rolemenu -> {
                rolemenuService.del(rolemenu.getId());
            });

            if (menu.getPid()>0){
                menuMapper.delete(menu);

            }else {
                List<SysMenu> menus = this.queryBypid(id);
                menus.forEach(m->{
                    menuMapper.delete(m);
                });
            }

        }
    }


    public PageResult<SysMenu> findAllByPage(Integer page, Integer rows, String key, String sortBy, Boolean desc) {
        Example example = new Example(SysMenu.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        PageHelper.startPage(page, rows);
        List<SysMenu> menus = this.menuMapper.selectByExample(example);
        PageInfo<SysMenu> pageInfo = new PageInfo<>(menus);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }


    public List<SysMenu> queryBypid(Long id) {
        SysMenu model=new SysMenu();
        model.setPid(id);
        return menuMapper.select(model);
    }
    public List<SysMenu> getMenus(Long rid) {
        List<SysMenu> sysMenus = menuMapper.selectByRid(rid);
        return sysMenus;
    }
//    public List<MenuEx> getMenus(Long rid) {
//        List<MenuEx> result=new ArrayList<>();
//        List<SysMenu> sysMenus = this.menuMapper.querys(rid, Long.valueOf(0));
//        for (int i = 0; i < sysMenus.size(); i++) {
//            MenuEx menuEx=new MenuEx();
//            SysMenu sysMenu = sysMenus.get(i);
//            BeanUtils.copyProperties(sysMenu,menuEx);
//            SysMenu childMs=new SysMenu();
//            childMs.setPid(menuEx.getId());
//            List<SysMenu> sysMenuList = this.menuMapper.querys(rid, sysMenu.getId());;
//            menuEx.setChilds(sysMenuList);
//            result.add(menuEx);
//        }
//        return result;
//    }
}
