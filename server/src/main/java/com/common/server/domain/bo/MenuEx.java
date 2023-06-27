package com.common.server.domain.bo;

import com.common.server.domain.SysMenu;

import java.util.List;

public class MenuEx extends SysMenu {
    private List<SysMenu> childs;

    public List<SysMenu> getChilds() {
        return childs;
    }

    public void setChilds(List<SysMenu> childs) {
        this.childs = childs;
    }
}
