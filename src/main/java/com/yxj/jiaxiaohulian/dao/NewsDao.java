package com.yxj.jiaxiaohulian.dao;

import com.yxj.jiaxiaohulian.entity.XueXiaoXinWen;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface NewsDao {

    public List<XueXiaoXinWen> findAllNews(); //查询所有校园新闻
    public Map<String,Object> findDianJiByUNameAndXId(Map<String,Object> map); //查询该用户是否点击过该条新闻
    public void addDianJiLiang(Map<String,Object> map);  //点击量表增加一条记录，一个用户只能贡献一个点击量
//    public void updateDianJiLiangNumber(Integer xId); //新闻表的点击量的值加一
    public Integer findDianJiLiangByXId(Integer xId);
}
