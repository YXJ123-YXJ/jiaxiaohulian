package com.yxj.jiaxiaohulian.dao;

import com.yxj.jiaxiaohulian.entity.PingLun;
import com.yxj.jiaxiaohulian.entity.ShiShengDongTai;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface DongTaiDao {

    public List<ShiShengDongTai> findAllDongDai(String uName);  //查询所有动态
    public void addDongTai(ShiShengDongTai dongTai); //发布动态
    public void deleteDongTaiById(Integer dtId);   //删除动态
    public void deletePingLunByBDtId(Integer bdtId); //删除动态的同事删除对应动态的评论
    public void deleteLiuLanByDtId(Integer dtId); //删除动态的同事删除对应动态的点击量

    public Map<String,Object> findDianJiLiangByDtIdAndUName(Map<String,Object> map); //查询用户是否已浏览了这条动态
    public void addDianJiLiang(Map<String,Object> map); //添加浏览量，每个用户只能贡献一个浏览量
    public Integer findDianJiLiangByDtId(Integer dtId); //查询更新后的浏览量（点击量）

    public List<PingLun> findPingLunByBdtId(Integer bdtId); //通过动态id查询评论
    public void addPingLun(PingLun pingLun);   //添加评论

}
