package com.common.server.excelModel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;

/**
 * excel导入user参数
 *
 * @author luoYong
 * @version 1.0
 * @date 2022/2/15 14:34
 */
@ExcelTarget("ImportUser")
public class ImportUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "学号", orderNum = "0")
    private String userid;
    /**
     * @Excel 作用在一个filed上面，对列的描述
     * @param name 列名
     * @param orderNum 下标，从0开始。
     */
    @Excel(name = "姓名", orderNum = "1")
    private String name;

    @Excel(name = "年龄", orderNum = "2")
    private Integer age;

    @Excel(name = "性别", orderNum = "3")
    private String sex;

    @Excel(name = "地址", orderNum = "4")
    private String address;
    @Excel(name = "联系电话", orderNum = "5")
    private String phone;

    @Excel(name = "身份证", orderNum = "6")
    private String idcardno;
    @Excel(name = "班级", orderNum = "7")
    private String userclassid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcardno() {
        return idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }

    public String getUserclassid() {
        return userclassid;
    }

    public void setUserclassid(String userclassid) {
        this.userclassid = userclassid;
    }
}
