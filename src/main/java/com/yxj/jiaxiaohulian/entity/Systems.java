package com.yxj.jiaxiaohulian.entity;

public class Systems {

    private Integer systemId;
    private String xiTongJianJie;
    private String xiTongXieYi;
    private String lianXiWoMen;
    private String guanYuWoMen;

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public String getXiTongJianJie() {
        return xiTongJianJie;
    }

    public void setXiTongJianJie(String xiTongJianJie) {
        this.xiTongJianJie = xiTongJianJie;
    }

    public String getXiTongXieYi() {
        return xiTongXieYi;
    }

    public void setXiTongXieYi(String xiTongXieYi) {
        this.xiTongXieYi = xiTongXieYi;
    }

    public String getLianXiWoMen() {
        return lianXiWoMen;
    }

    public void setLianXiWoMen(String lianXiWoMen) {
        this.lianXiWoMen = lianXiWoMen;
    }

    public String getGuanYuWoMen() {
        return guanYuWoMen;
    }

    public void setGuanYuWoMen(String guanYuWoMen) {
        this.guanYuWoMen = guanYuWoMen;
    }

    @Override
    public String toString() {
        return "Systems{" +
                "systemId=" + systemId +
                ", xiTongJianJie='" + xiTongJianJie + '\'' +
                ", xiTongXieYi='" + xiTongXieYi + '\'' +
                ", lianXiWoMen='" + lianXiWoMen + '\'' +
                ", guanYuWoMen='" + guanYuWoMen + '\'' +
                '}';
    }
}
