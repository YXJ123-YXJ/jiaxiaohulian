package com.yxj.jiaxiaohulian.entity;

public class YuanXi {

    private Integer y_id;
    private String yXueYuan;
    private String yXi;
    private String yClazz;

    public Integer getY_id() {
        return y_id;
    }

    public void setY_id(Integer y_id) {
        this.y_id = y_id;
    }

    public String getyXueYuan() {
        return yXueYuan;
    }

    public void setyXueYuan(String yXueYuan) {
        this.yXueYuan = yXueYuan;
    }

    public String getyXi() {
        return yXi;
    }

    public void setyXi(String yXi) {
        this.yXi = yXi;
    }

    public String getyClazz() {
        return yClazz;
    }

    public void setyClazz(String yClazz) {
        this.yClazz = yClazz;
    }

    @Override
    public String toString() {
        return "YuanXi{" +
                "y_id=" + y_id +
                ", yXueYuan='" + yXueYuan + '\'' +
                ", yXi='" + yXi + '\'' +
                ", yClazz='" + yClazz + '\'' +
                '}';
    }
}
