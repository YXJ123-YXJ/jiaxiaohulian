package com.yxj.jiaxiaohulian.dao;

import com.yxj.jiaxiaohulian.entity.Systems;
import com.yxj.jiaxiaohulian.entity.User;
import com.yxj.jiaxiaohulian.entity.YouQingLianJie;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface LoginDao {

    public List<User> userLogin(User user); //用户登录

    public List<YouQingLianJie> findWangZhan(); //查询友情链接

    public User checkUNameExsit(String uName); //注册时判断用户是否存在

    public Systems findSystems(); //查询与系统有关的介绍信息

    public List<String> findYXueYuan(); //查询学院   用于用户注册时的显示下拉列表
    public List<String> findYXi(String yXueYuan);      //查询系     用于用户注册时的显示下拉列表
    public List<String> findYClazz(Map<String,Object> map);   //查询班级   用于用户注册时的显示下拉列表

    public List<String> findAllUtName(); //查询全部状态为1(正常)的辅导员，用于注册时下拉列表的显示

    public void userRegister(User user); //用户注册

//    public User findUserByUName(String uName); //根据用户名查询用户，用于用户修改密码时验证原密码

    public void updateUPwd(User user); //修改密码

    public User findRolesByUserName(String uName);

}
