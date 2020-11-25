package com.yxj.jiaxiaohulian.dao;

import com.yxj.jiaxiaohulian.entity.TongZhiGongGao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TongZhiDao {

    public List<TongZhiGongGao> findAllTongZhi(Integer tzId); //查询所有通知
    public void addChaKan(Map<String,Object> map); //当用户点击通知后，向查看表中添加一条记录
    public Map<String,Object> findChaKanByUNameAndTzId(Map<String,Object> map); //查询用户是否查看了此条公告
//    public List<Map<String,Object>> findChaKanByUNameAndTzIdList(Map<String,Object> map); //查询用户是否查看了此条公告

}
