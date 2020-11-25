package com.yxj.jiaxiaohulian.entity;

import java.io.Serializable;
import java.util.Date;

public class XueXiaoXinWen {

    private Integer xId;
    private String xName;
    private String xText;
    private String xType;
    private Date xCreateTime;
    private Integer xDianJiLiang;
    private String xTuPian;

    public String getxTuPian() {
        return xTuPian;
    }

    public void setxTuPian(String xTuPian) {
        this.xTuPian = xTuPian;
    }

    public Integer getxId() {
        return xId;
    }

    public void setxId(Integer xId) {
        this.xId = xId;
    }

    public String getxText() {
        return xText;
    }

    public void setxText(String xText) {
        this.xText = xText;
    }

    public String getxType() {
        return xType;
    }

    public void setxType(String xType) {
        this.xType = xType;
    }

    public Date getxCreateTime() {
        return xCreateTime;
    }

    public void setxCreateTime(Date xCreateTime) {
        this.xCreateTime = xCreateTime;
    }

    public Integer getxDianJiLiang() {
        return xDianJiLiang;
    }

    public void setxDianJiLiang(Integer xDianJiLiang) {
        this.xDianJiLiang = xDianJiLiang;
    }

    @Override
    public String toString() {
        return "XueXiaoXinWen{" +
                "xId=" + xId +
                ", xName='" + xName + '\'' +
                ", xText='" + xText + '\'' +
                ", xType='" + xType + '\'' +
                ", xCreateTime=" + xCreateTime +
                ", xDianJiLiang=" + xDianJiLiang +
                ", xTuPian='" + xTuPian + '\'' +
                '}';
    }

    public String getxName() {
        return xName;
    }

    public void setxName(String xName) {
        this.xName = xName;
    }
}
