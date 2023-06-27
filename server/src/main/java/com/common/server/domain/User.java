package com.common.server.domain;

import javax.persistence.*;

//
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Long id;
    //
    private String account;
    //
    private String password;
    //
    private String headpic;
    //
    private String name;
    //
    private Long rid;
    //
    private Boolean sex;
    //
    private Long age;
    private String phone;
    //
    private String address;

    private String idcardno;

    private Long userid;

    private Long userclassid;

    private Integer status;

    @Transient
    private String actionName;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdcardno() {
        return idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getUserclassid() {
        return userclassid;
    }

    public void setUserclassid(Long userclassid) {
        this.userclassid = userclassid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User() {
    }

    public User(String headpic, String name, Long rid, Boolean sex, Long age, String phone, String address, String idcardno, Long userid, Long userclassid, Integer status) {
        this.headpic = headpic;
        this.name = name;
        this.rid = rid;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.idcardno = idcardno;
        this.userid = userid;
        this.userclassid = userclassid;
        this.status = status;
    }
}
