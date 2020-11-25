package com.yxj.jiaxiaohulian.entity;

import java.util.Date;

public class Admin {

    private String aName;
    private String aPwd;
    private String aSalt; //随机盐
    private String aSex;
    private Date aAddTime;
    private String aTouXiang;
    private String aPhone;
    private Integer aId;
    private String aRole;

    public String getaSalt() {
        return aSalt;
    }

    public void setaSalt(String aSalt) {
        this.aSalt = aSalt;
    }

    public String getaRole() {
        return aRole;
    }

    public void setaRole(String aRole) {
        this.aRole = aRole;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getaPhone() {
        return aPhone;
    }

    public void setaPhone(String aPhone) {
        this.aPhone = aPhone;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaPwd() {
        return aPwd;
    }

    public void setaPwd(String aPwd) {
        this.aPwd = aPwd;
    }

    public String getaSex() {
        return aSex;
    }

    public void setaSex(String aSex) {
        this.aSex = aSex;
    }

    public Date getaAddTime() {
        return aAddTime;
    }

    public void setaAddTime(Date aAddTime) {
        this.aAddTime = aAddTime;
    }

    public String getaTouXiang() {
        return aTouXiang;
    }

    public void setaTouXiang(String aTouXiang) {
        this.aTouXiang = aTouXiang;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aName='" + aName + '\'' +
                ", aPwd='" + aPwd + '\'' +
                ", aSalt='" + aSalt + '\'' +
                ", aSex='" + aSex + '\'' +
                ", aAddTime=" + aAddTime +
                ", aTouXiang='" + aTouXiang + '\'' +
                ", aPhone='" + aPhone + '\'' +
                ", aId=" + aId +
                ", aRole='" + aRole + '\'' +
                '}';
    }
}
