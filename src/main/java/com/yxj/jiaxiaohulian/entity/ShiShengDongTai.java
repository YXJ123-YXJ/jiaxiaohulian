package com.yxj.jiaxiaohulian.entity;

import java.util.Date;

public class ShiShengDongTai {

    private Integer dtId;
    private String dtName;
    private String dtText;
    private String uName;
    private Date dtCreateTime;
    private Integer dtDianJiLiang;
    private String dtTuPian;

    public Integer getDtId() {
        return dtId;
    }

    public void setDtId(Integer dtId) {
        this.dtId = dtId;
    }

    public String getDtText() {
        return dtText;
    }

    public void setDtText(String dtText) {
        this.dtText = dtText;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Date getDtCreateTime() {
        return dtCreateTime;
    }

    public void setDtCreateTime(Date dtCreateTime) {
        this.dtCreateTime = dtCreateTime;
    }

    public Integer getDtDianJiLiang() {
        return dtDianJiLiang;
    }

    public void setDtDianJiLiang(Integer dtDianJiLiang) {
        this.dtDianJiLiang = dtDianJiLiang;
    }

    public String getDtTuPian() {
        return dtTuPian;
    }

    public void setDtTuPian(String dtTuPian) {
        this.dtTuPian = dtTuPian;
    }

    @Override
    public String toString() {
        return "ShiShengDongTai{" +
                "dtId=" + dtId +
                ", dtName='" + dtName + '\'' +
                ", dtText='" + dtText + '\'' +
                ", uName='" + uName + '\'' +
                ", dtCreateTime='" + dtCreateTime + '\'' +
                ", dtDianJiLiang='" + dtDianJiLiang + '\'' +
                ", dtTuPian='" + dtTuPian + '\'' +
                '}';
    }

    public String getDtName() {
        return dtName;
    }

    public void setDtName(String dtName) {
        this.dtName = dtName;
    }
}
