package com.yxj.jiaxiaohulian.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable{

    private String uName;
    private String uPwd;
    private String uSalt; //随机盐（用于MD5加密后添加的随机盐）
    private Integer uQuanXian;
    private String uSex;
    private Integer uAge;
    private String uDiZhi;
    private String uTouXiang;
    private String uPhone;
    private String uEmail;
    private String uIdCard;
    private Date uAddTime;
    private String usName;
    private String utName;
    private String ujName;
    private Integer uStuNo;
    private String uYuanXi;
    private String uClazz;
    private Integer uShenHe;
    private String uRole; //用户角色（加shiro之后）

    public String getuSalt() {
        return uSalt;
    }

    public void setuSalt(String uSalt) {
        this.uSalt = uSalt;
    }

    public String getuRole() {
        return uRole;
    }

    public void setuRole(String uRole) {
        this.uRole = uRole;
    }

    public Integer getuShenHe() {
        return uShenHe;
    }

    public void setuShenHe(Integer uShenHe) {
        this.uShenHe = uShenHe;
    }

    public String getuClazz() {
        return uClazz;
    }

    public void setuClazz(String uClazz) {
        this.uClazz = uClazz;
    }

    public Integer getuStuNo() {
        return uStuNo;
    }

    public void setuStuNo(Integer uStuNo) {
        this.uStuNo = uStuNo;
    }

    public String getuYuanXi() {
        return uYuanXi;
    }

    public void setuYuanXi(String uYuanXi) {
        this.uYuanXi = uYuanXi;
    }

    private List<LiuYan> liuYans;

    public List<LiuYan> getLiuYans() {
        return liuYans;
    }

    public void setLiuYans(List<LiuYan> liuYans) {
        this.liuYans = liuYans;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPwd() {
        return uPwd;
    }

    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public Integer getuQuanXian() {
        return uQuanXian;
    }

    public void setuQuanXian(Integer uQuanXian) {
        this.uQuanXian = uQuanXian;
    }

    public String getuSex() {
        return uSex;
    }

    public void setuSex(String uSex) {
        this.uSex = uSex;
    }

    public Integer getuAge() {
        return uAge;
    }

    public void setuAge(Integer uAge) {
        this.uAge = uAge;
    }

    public String getuDiZhi() {
        return uDiZhi;
    }

    public void setuDiZhi(String uDiZhi) {
        this.uDiZhi = uDiZhi;
    }

    public String getuTouXiang() {
        return uTouXiang;
    }

    public void setuTouXiang(String uTouXiang) {
        this.uTouXiang = uTouXiang;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuIdCard() {
        return uIdCard;
    }

    public void setuIdCard(String uIdCard) {
        this.uIdCard = uIdCard;
    }

    public Date getuAddTime() {
        return uAddTime;
    }

    public void setuAddTime(Date uAddTime) {
        this.uAddTime = uAddTime;
    }

    public String getUsName() {
        return usName;
    }

    public void setUsName(String usName) {
        this.usName = usName;
    }

    public String getUtName() {
        return utName;
    }

    public void setUtName(String utName) {
        this.utName = utName;
    }

    public String getUjName() {
        return ujName;
    }

    public void setUjName(String ujName) {
        this.ujName = ujName;
    }

    @Override
    public String toString() {
        return "User{" +
                "uName='" + uName + '\'' +
                ", uPwd='" + uPwd + '\'' +
                ", uSalt='" + uSalt + '\'' +
                ", uQuanXian=" + uQuanXian +
                ", uSex='" + uSex + '\'' +
                ", uAge=" + uAge +
                ", uDiZhi='" + uDiZhi + '\'' +
                ", uTouXiang='" + uTouXiang + '\'' +
                ", uPhone='" + uPhone + '\'' +
                ", uEmail='" + uEmail + '\'' +
                ", uIdCard='" + uIdCard + '\'' +
                ", uAddTime=" + uAddTime +
                ", usName='" + usName + '\'' +
                ", utName='" + utName + '\'' +
                ", ujName='" + ujName + '\'' +
                ", uStuNo=" + uStuNo +
                ", uYuanXi='" + uYuanXi + '\'' +
                ", uClazz='" + uClazz + '\'' +
                ", uShenHe=" + uShenHe +
                ", uRole='" + uRole + '\'' +
                ", liuYans=" + liuYans +
                '}';
    }
}
