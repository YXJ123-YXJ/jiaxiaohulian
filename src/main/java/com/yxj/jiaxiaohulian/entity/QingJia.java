package com.yxj.jiaxiaohulian.entity;

import java.util.Date;

public class QingJia {

    private Integer qId;
    private String qText;
    private String usName;
    private int qUjFlag;
    private int qUtFlag;
    private int qUaFlag;
    private Integer qDays;
    private Date qTimeFrom;
    private Date qTimeTo;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getqUjFlag() {
        return qUjFlag;
    }

    public void setqUjFlag(int qUjFlag) {
        this.qUjFlag = qUjFlag;
    }

    public int getqUtFlag() {
        return qUtFlag;
    }

    public void setqUtFlag(int qUtFlag) {
        this.qUtFlag = qUtFlag;
    }

    public int getqUaFlag() {
        return qUaFlag;
    }

    public void setqUaFlag(int qUaFlag) {
        this.qUaFlag = qUaFlag;
    }

    public Date getqTimeFrom() {
        return qTimeFrom;
    }

    public void setqTimeFrom(Date qTimeFrom) {
        this.qTimeFrom = qTimeFrom;
    }

    public Date getqTimeTo() {
        return qTimeTo;
    }

    public void setqTimeTo(Date qTimeTo) {
        this.qTimeTo = qTimeTo;
    }

    public Integer getqDays() {
        return qDays;
    }

    public void setqDays(Integer qDays) {
        this.qDays = qDays;
    }

    public Integer getqId() {
        return qId;
    }

    public void setqId(Integer qId) {
        this.qId = qId;
    }

    public String getqText() {
        return qText;
    }

    public void setqText(String qText) {
        this.qText = qText;
    }

    public String getUsName() {
        return usName;
    }

    public void setUsName(String usName) {
        this.usName = usName;
    }

    @Override
    public String toString() {
        return "QingJia{" +
                "qId=" + qId +
                ", qText='" + qText + '\'' +
                ", usName='" + usName + '\'' +
                ", qUjFlag=" + qUjFlag +
                ", qUtFlag=" + qUtFlag +
                ", qUaFlag=" + qUaFlag +
                ", qDays=" + qDays +
                ", qTimeFrom=" + qTimeFrom +
                ", qTimeTo=" + qTimeTo +
                ", user=" + user +
                '}';
    }
}
