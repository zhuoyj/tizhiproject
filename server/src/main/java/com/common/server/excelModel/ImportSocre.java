package com.common.server.excelModel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

@ExcelTarget("ImportSocre")
public class ImportSocre {
    private static final long serialVersionUID = 1L;
    @Excel(name = "学生编号", orderNum = "0")
    private String userid;
    @Excel(name = "学生姓名", orderNum = "1")
    private String uname;
    @Excel(name = "项目名称", orderNum = "2")
    private String aname;
    @Excel(name = "分数", orderNum = "3")
    private String score;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
