package com.yxj.jiaxiaohulian.service;

import com.yxj.jiaxiaohulian.entity.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {

//    public boolean adminLogin(User user, HttpServletRequest request);

    public void addNews(XueXiaoXinWen xueXiaoXinWen, MultipartFile file);
    public Object findNews(XueXiaoXinWen xueXiaoXinWen);
    public void deleteNewsByXId(Integer xId);

    public Object findDongTai(ShiShengDongTai shiShengDongTai);
    public void deleteDongTaiByDtId(Integer dtId); //按dtId删除动态，同时删除对应动态的评论

    public void addTongZhi(TongZhiGongGao tongZhiGongGao);
    public Object findTongZhi(TongZhiGongGao tongZhiGongGao);
    public void deleteTongZhiByTzId(Integer tzId); //通过tzId删除通知
    public void updateTongZhiByTzId(TongZhiGongGao tongZhiGongGao);

    public Object findLiuYan(LiuYan liuYan);
    public void deleteLiuYanByLId(Integer lId);

    public Object findSystem();  //查询系统相关信息
    public void updateSystem(Systems system);
    public void addWangZhan(YouQingLianJie youQingLianJie); //添加友情链接
    public Object findWangZhan(YouQingLianJie youQingLianJie);
    public void updateWangZhanByLjId(YouQingLianJie youQingLianJie); //修改友情链接
    public void deleteWangZhanByLjId(Integer ljId);

    public Object findUser(User user);  //查询用户
    public void updateUserStateByUName(User user);
    public void deleteUserByUName(String uName);
    public Object findToShenHe(User user);
    public void deleteToShenHeUserByUName(String[] uNames);
    public Object findQingJiaOverSevenDays(); //查询请假天数大于等于七天的学生
    public void qUaFlagChange(String qId,String qUaFlag);

    public void addAdmin(User user); //添加管理员
    public User findAdminByName(String uName); //添加管理员时，检查账号是否存在
    public Object findAdmin(User user);
    public void updateAdmin(User user);
    public void updateBySuperAdmin(User user);
    public void deleteAdminByAId(Integer uStuNo); //删除管理员
    public User findAdminNotIncludeMe(User user);

    public void cleanAllRedis();//清除Redis中的所有数据

    public User checkPwd(User user);  //修改管理员密码之前验证原密码
    public void updateAdminPwd(User user);

}
