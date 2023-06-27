package com.common.server.domain;

import javax.persistence.*;


@Table(name = "tb_msg")
public class Msg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")


    private Long id;

    @Transient
    private String contents;

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    private Long aid;
    @Transient
    private Long senduid;

    public Long getSenduid() {
        return senduid;
    }

    public void setSenduid(Long senduid) {
        this.senduid = senduid;
    }

    @Transient
    private String cids;
    @Transient
    private Long cid;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    @Transient
    private Long rid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getCids() {
        return cids;
    }

    public void setCids(String cids) {
        this.cids = cids;
    }

    @Transient
    private String aname;


    private Long uid;
    @Transient
    private String uname;

    private Integer isread;
    private String times;

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
