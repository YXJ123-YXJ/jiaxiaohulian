package com.yxj.jiaxiaohulian.entity;

import java.util.Date;

public class LiuYan {

    private Integer lId;
    private String lText;
    private String uNameFrom;
    private String uNameTo;
    private Date lCreateTime;
    private String lFuJian;
    private String lHuiFu;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getlHuiFu() {
        return lHuiFu;
    }

    public void setlHuiFu(String lHuiFu) {
        this.lHuiFu = lHuiFu;
    }

    public Integer getlId() {
        return lId;
    }

    public void setlId(Integer lId) {
        this.lId = lId;
    }

    public String getlText() {
        return lText;
    }

    public void setlText(String lText) {
        this.lText = lText;
    }

    public String getuNameFrom() {
        return uNameFrom;
    }

    public void setuNameFrom(String uNameFrom) {
        this.uNameFrom = uNameFrom;
    }

    public String getuNameTo() {
        return uNameTo;
    }

    public void setuNameTo(String uNameTo) {
        this.uNameTo = uNameTo;
    }

    public Date getlCreateTime() {
        return lCreateTime;
    }

    public void setlCreateTime(Date lCreateTime) {
        this.lCreateTime = lCreateTime;
    }

    public String getlFuJian() {
        return lFuJian;
    }

    public void setlFuJian(String lFuJian) {
        this.lFuJian = lFuJian;
    }

    @Override
    public String toString() {
        return "LiuYan{" +
                "lId=" + lId +
                ", lText='" + lText + '\'' +
                ", uNameFrom='" + uNameFrom + '\'' +
                ", uNameTo='" + uNameTo + '\'' +
                ", lCreateTime=" + lCreateTime +
                ", lFuJian='" + lFuJian + '\'' +
                ", lHuiFu='" + lHuiFu + '\'' +
                ", user=" + user +
                '}';
    }
}
