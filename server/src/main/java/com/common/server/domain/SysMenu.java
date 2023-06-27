package com.common.server.domain;

import javax.persistence.*;


@Table(name = "tb_menu")
public class SysMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Long id;


    private String name;
    @Transient
    private String title;

    private String url;

    @Transient
    private String pageName;
    private String icon;

    private Long pid;

    private Integer sort;

    private Boolean isend;

    @Transient
    private Boolean isck;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getIsend() {
        return isend;
    }

    public void setIsend(Boolean isend) {
        this.isend = isend;
    }

    public Boolean getIsck() {
        return isck;
    }

    public void setIsck(Boolean isck) {
        this.isck = isck;
    }
}
