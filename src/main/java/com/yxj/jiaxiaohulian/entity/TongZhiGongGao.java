package com.yxj.jiaxiaohulian.entity;

import java.util.Date;

public class TongZhiGongGao {

    private Integer tzId;
    private String tzText;
    private String tzName;
    private Date tzCreateTime;
    private Integer tzLevel;
    private String tzTextFor;
    private String tzTextFrom;

    public String getTzTextFor() {
        return tzTextFor;
    }

    public void setTzTextFor(String tzTextFor) {
        this.tzTextFor = tzTextFor;
    }

    public String getTzTextFrom() {
        return tzTextFrom;
    }

    public void setTzTextFrom(String tzTextFrom) {
        this.tzTextFrom = tzTextFrom;
    }

    public String getTzName() {
        return tzName;
    }

    public void setTzName(String tzName) {
        this.tzName = tzName;
    }

    public Integer getTzId() {
        return tzId;
    }

    public void setTzId(Integer tzId) {
        this.tzId = tzId;
    }

    public String getTzText() {
        return tzText;
    }

    public void setTzText(String tzText) {
        this.tzText = tzText;
    }

    public Date getTzCreateTime() {
        return tzCreateTime;
    }

    public void setTzCreateTime(Date tzCreateTime) {
        this.tzCreateTime = tzCreateTime;
    }

    public Integer getTzLevel() {
        return tzLevel;
    }

    public void setTzLevel(Integer tzLevel) {
        this.tzLevel = tzLevel;
    }

    @Override
    public String toString() {
        return "TongZhiGongGao{" +
                "tzId=" + tzId +
                ", tzText='" + tzText + '\'' +
                ", tzName='" + tzName + '\'' +
                ", tzCreateTime='" + tzCreateTime + '\'' +
                ", tzLevel='" + tzLevel + '\'' +
                ", tzTextFor='" + tzTextFor + '\'' +
                ", tzTextFrom='" + tzTextFrom + '\'' +
                '}';
    }
}
