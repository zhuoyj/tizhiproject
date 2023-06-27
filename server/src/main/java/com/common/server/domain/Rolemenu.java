package com.common.server.domain;


import javax.persistence.*;


@Table(name = "tb_rolemenu")
public class Rolemenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Long id;
    private Long menuid;
    private Long roleid;
    @Transient
    private Integer isck;

    public Integer getIsck() {
        return isck;
    }

    public void setIsck(Integer isck) {
        this.isck = isck;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Rolemenu() {
    }

    public Rolemenu(Long menuid, Long roleid) {
        this.menuid = menuid;
        this.roleid = roleid;
    }
}
