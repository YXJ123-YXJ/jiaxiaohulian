package com.yxj.jiaxiaohulian.dao;

import com.yxj.jiaxiaohulian.entity.LiuYan;
import com.yxj.jiaxiaohulian.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface LiuYanDao {

    public List<String> findUserByUQuanXian(Map<String,Object> map); //添加留言时下拉列表根据选择的权限动态变化对应的姓名，只查询状态为1(正常)的用户
    public void addLiuYan(LiuYan liuYan); //添加留言
    public List<LiuYan> findLiuYanByUNameFrom(String uNameFrom); //查询我的留言
    public List<LiuYan> findLiuYanByUNameTo(String uNameTo); //查询别人给我留的言
    public User findUNameFromMsg(String uNameFrom);  //查询给我留言的人的信息
    public void huiFuLiuYan(Map<String,Object> map); //回复留言

}
