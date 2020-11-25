package com.yxj.jiaxiaohulian.service;

import com.yxj.jiaxiaohulian.entity.TongZhiGongGao;

import java.util.List;
import java.util.Map;

public interface TongZhiService {

    public Object findAllTongZhi(String uName);
    public void addChaKan(String uName,Integer tzId);
    public String shiFouChaKan(String uName,Integer tzId);
    public TongZhiGongGao findTongZhiByTzId(Integer tzId);
}
