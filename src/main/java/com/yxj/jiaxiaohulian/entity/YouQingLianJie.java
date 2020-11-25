package com.yxj.jiaxiaohulian.entity;

import java.util.Date;

public class YouQingLianJie {

    private Integer ljId;
    private String ljName;
    private String ljSrc;
    private Date ljCreateTime;

    public Date getLjCreateTime() {
        return ljCreateTime;
    }

    public void setLjCreateTime(Date ljCreateTime) {
        this.ljCreateTime = ljCreateTime;
    }

    public Integer getLjId() {
        return ljId;
    }

    public void setLjId(Integer ljId) {
        this.ljId = ljId;
    }

    public String getLjName() {
        return ljName;
    }

    public void setLjName(String ljName) {
        this.ljName = ljName;
    }

    public String getLjSrc() {
        return ljSrc;
    }

    public void setLjSrc(String ljSrc) {
        this.ljSrc = ljSrc;
    }

    @Override
    public String toString() {
        return "YouQingLianJie{" +
                "ljId=" + ljId +
                ", ljName='" + ljName + '\'' +
                ", ljSrc='" + ljSrc + '\'' +
                ", ljCreateTime=" + ljCreateTime +
                '}';
    }
}
