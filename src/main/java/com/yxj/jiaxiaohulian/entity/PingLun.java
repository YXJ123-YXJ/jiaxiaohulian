package com.yxj.jiaxiaohulian.entity;

import java.util.Date;

public class PingLun {

    private Integer pId;
    private String pText;
    private Date pCreateTime;
    private String uName;
    private Integer bdtId;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpText() {
        return pText;
    }

    public void setpText(String pText) {
        this.pText = pText;
    }

    public Date getpCreateTime() {
        return pCreateTime;
    }

    public void setpCreateTime(Date pCreateTime) {
        this.pCreateTime = pCreateTime;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Integer getBdtId() {
        return bdtId;
    }

    public void setBdtId(Integer bdtId) {
        this.bdtId = bdtId;
    }

    @Override
    public String toString() {
        return "PingLun{" +
                "pId=" + pId +
                ", pText='" + pText + '\'' +
                ", pCreateTime=" + pCreateTime +
                ", uName='" + uName + '\'' +
                ", bdtId=" + bdtId +
                '}';
    }
}
