package com.yxj.jiaxiaohulian.dao;

import com.yxj.jiaxiaohulian.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface AdminDao {

    //管理员登录
    public User adminLogin(User user); //管理员登录
    public List<User> findRolesByAName(String uName); //根据管理员账号查询管理员

    // 新闻
    public void addNews(XueXiaoXinWen xueXiaoXinWen); //添加新闻
    public List<XueXiaoXinWen> findNews(XueXiaoXinWen xueXiaoXinWen); //查询新闻
    public void deleteNewsByXId(Integer xId);  //通过xId删除新闻
    public void deleteDianJiLiangByXId(Integer xId); //通过xId删除新闻点击量

    // 动态
    public List<ShiShengDongTai> findDongTai(ShiShengDongTai shiShengDongTai); //查询动态
    public void deleteDongTaiByDtId(Integer dtId); //按dtId删除动态
    public void deletePingLunByDtId(Integer bdtId); //按bdtId删除评论
    public void deleteDianJiLiangByDtId(Integer dtId); //通过dtId删除动态浏览量

    //通知
    public void addTongZhi(TongZhiGongGao tongZhiGongGao); //添加通知公告
    public List<TongZhiGongGao> findTongZhi(TongZhiGongGao tongZhiGongGao); //查询通知公告
    public void deleteTongZhiByTzId(Integer tzId); //通过tzId删除通知
    public void deleteGongGaoChaKanByTzId(Integer tzId); // 通过tzId删除公告查看记录
    public void updateTongZhiByTzId(TongZhiGongGao tongZhiGongGao); //修改通知

    //留言
    public List<LiuYan> findLiuYan(LiuYan liuYan); //查询留言
    public void deleteLiuYanByLId(Integer lId); //通过lId删除留言

    //系统
    public Systems findSystem();  //查询系统相关信息
    public void updateSystem(Systems system); //修改系统相关信息
    public void addWangZhan(YouQingLianJie youQingLianJie); //添加友情链接
    public List<YouQingLianJie> findWangZhan(YouQingLianJie youQingLianJie); //查询友情链接
    public void updateWangZhanByLjId(YouQingLianJie youQingLianJie); //修改友情链接
    public void deleteWangZhanByLjId(Integer ljId); //按ljId删除友情链接

    //用户
    public List<User> findUser(User user);  //查询用户
    public void updateUserStateByUName(User user); //修改User的状态（1、正常 2、已冻结）有了用户状态就不写删除用户了
    //删除用户开始
    public void deleteUserByUName(String uName); //通过用户名删除用户
    public void deleteGongGaoChaKanByUName(String uName); //删除用户的同时删除公告查看
    public void deleteLiuYanByUNameFrom(String uNameFrom); //删除用户的同时删除该用户给别人留的言
    public void deletePingLunByUName(String uName); //删除用户的同时删除他对所有动态的评论信息
    //此处注意：要先删除评论再删除动态
    public void deleteDongTaiByUName(String uName); //删除用户的同时删除该用户发表的动态
    public Integer[] findDtIdsByUName(String uName); //通过用户名查询出此用户发布的所有动态的dtId,用于后边查询此用户发表的所有动态对应的所有评论
    public Integer[] findPIdsByBdtIds(Integer[] bdtIds); //(注：入参数组不能为空，需要在service层判断从findDtIdsByUName查出的数组是否为空，不为空才执行此方法)查询出该用户所有动态的评论用于后边的删除评论
    public void deletePingLunByPIds(Integer[] pIds); //(注：入参数组不能为空，需要在service层判断从findPIdsByBdtIds查出的数组是否为空，不为空才执行此方法)删除用户的同时删除该用户发表的动态对应的所有评论
    public void deleteQingJiaByUsName(String usName); //删除用户的同时删除该用户所有请假记录
    //删除用户结束
    public List<User> findToShenHe(User user); //查询已注册，但未审核或审核不通过的用户
    public void deleteToShenHeUserByUName(String[] uNames); //批量删除已注册但在审核中的用户
    public List<QingJia> findQingJiaOverSevenDays(); //查询请假天数大于等于七天的学生
    public void qUaFlagChange(Map<String,String> map);  //当请假天数大于等于7天时，管理员是否同意请假（改变请假表的q_ua_flag的值）

    //系统管理
    public void addAdmin(User user); //添加管理员
    public User findAdminByName(String uName); //添加管理员时，检查账号是否存在
    public List<User> findAdmin(User user); //查询管理员
    public void updateAdmin(User user); //修改管理员自己修改自己的信息
    public void updateBySuperAdmin(User user); //超级管理员修改其他管理员的信息
    public void deleteAdminByAId(Integer uStuNo); //删除管理员
    public User findAdminNotIncludeMe(User user); //用于修改管理员信息时判断修改后的名字是否已存在

    public void updateAdminPwd(User user);  //管理员修改自己的密码

}
