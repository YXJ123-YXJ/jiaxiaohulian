package com.yxj.jiaxiaohulian.controller;

import com.yxj.jiaxiaohulian.entity.*;
import com.yxj.jiaxiaohulian.service.AdminService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("{page}.html")
    public String toAdmin(@PathVariable("page")String page){
        return "/admin/"+page;
    }

    @RequestMapping("system/{page}.html")
    public String toAdminSystem(@PathVariable("page")String page){
        return "/admin/system/"+page;
    }


    /*@RequestMapping("adminLogin.do")
    @ResponseBody
    public String adminLogin(User user, HttpServletRequest request){
        if (adminService.adminLogin(user,request)){
            return "success";
        }
        return "fail";
    }*/


    @RequestMapping("addNews.do")
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public String addNews(XueXiaoXinWen xueXiaoXinWen, MultipartFile file){
        adminService.addNews(xueXiaoXinWen,file);
        return "/admin/findNews";
    }

    @RequestMapping("findNews.do")
    @ResponseBody
    @RequiresAuthentication
    public Object findNews(XueXiaoXinWen xueXiaoXinWen){
        return adminService.findNews(xueXiaoXinWen);
    }

    @RequestMapping("deleteNewsByXId.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public void deleteNewsByXId(String xId){
        adminService.deleteNewsByXId(Integer.valueOf(xId));
    }



    @RequestMapping("findDongTai.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public Object findDongTai(ShiShengDongTai shiShengDongTai){
        return adminService.findDongTai(shiShengDongTai);
    }

    @RequestMapping("deleteDongTaiByDtId.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public void deleteDongTaiByDtId(String dtId){
        adminService.deleteDongTaiByDtId(Integer.valueOf(dtId));
    }



    @RequestMapping("addTongZhi.do")
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public String addTongZhi(TongZhiGongGao tongZhiGongGao){
        adminService.addTongZhi(tongZhiGongGao);
        return "/admin/findTongZhi";
    }

    @RequestMapping("findTongZhi.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public Object findTongZhi(TongZhiGongGao tongZhiGongGao){
        return adminService.findTongZhi(tongZhiGongGao);
    }

    @RequestMapping("deleteTongZhiByTzId.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public void deleteTongZhiByTzId(String tzId){
        adminService.deleteTongZhiByTzId(Integer.valueOf(tzId));
    }

    @RequestMapping("updateTongZhiByTzId.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public void updateTongZhiByTzId(TongZhiGongGao tongZhiGongGao){
        adminService.updateTongZhiByTzId(tongZhiGongGao);
    }



    @RequestMapping("findLiuYan.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public Object findLiuYan(LiuYan liuYan){
        return adminService.findLiuYan(liuYan);
    }

    @RequestMapping("deleteLiuYanByLId.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public void deleteLiuYanByLId(String lId){
        adminService.deleteLiuYanByLId(Integer.valueOf(lId));
    }



    @RequestMapping("findSystem.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public Object findSystem(){
        return adminService.findSystem();
    }

    @RequestMapping("updateSystem.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public void updateSystem(Systems system){
        adminService.updateSystem(system);
    }



    @RequestMapping("addWangZhan.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public void addWangZhan(YouQingLianJie youQingLianJie){
        adminService.addWangZhan(youQingLianJie);
    }

    @RequestMapping("findWangZhan.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public Object findWangZhan(YouQingLianJie youQingLianJie){
        return adminService.findWangZhan(youQingLianJie);
    }

    @RequestMapping("updateWangZhanByLjId.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public void updateWangZhanByLjId(YouQingLianJie youQingLianJie){
        adminService.updateWangZhanByLjId(youQingLianJie);
    }

    @RequestMapping("deleteWangZhanByLjId.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public void deleteWangZhanByLjId(String ljId){
        adminService.deleteWangZhanByLjId(Integer.valueOf(ljId));
    }



    @RequestMapping("findUser.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public Object findUser(User user){
        return adminService.findUser(user);
    }

    @RequestMapping("updateUserStateByUName.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public void updateUserStateByUName(User user){
        adminService.updateUserStateByUName(user);
    }

    @RequestMapping("deleteUserByUName.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public void deleteUserByUName(String uName){
        adminService.deleteUserByUName(uName);
    }

    @RequestMapping("findToShenHe.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public Object findToShenHe(User user){
        return adminService.findToShenHe(user);
    }

    @RequestMapping("deleteToShenHeUserByUName.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public void deleteToShenHeUserByUName(@RequestParam("arr[]") String[] arr){
        adminService.deleteToShenHeUserByUName(arr);
    }

    @RequestMapping("findQingJiaOverSevenDays.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public Object findQingJiaOverSevenDays(){
        return adminService.findQingJiaOverSevenDays();
    }

    @RequestMapping("qUaFlagChange.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public void qUaFlagChange(String qId,String qUaFlag){
        adminService.qUaFlagChange(qId,qUaFlag);
    }



    @RequestMapping("addAdmin.do")
    @ResponseBody
    @RequiresRoles(value={"超级管理员"})
    public void addAdmin(User user){
        adminService.addAdmin(user);
    }

    @RequestMapping("findAdmin.do")
    @ResponseBody
    @RequiresRoles(value={"超级管理员"})
    public Object findAdmin(User user){
        return adminService.findAdmin(user);
    }

    @RequestMapping("findAdminByName.do")
    @ResponseBody
    @RequiresRoles(value={"超级管理员"})
    public User findAdminByName(String uName){ //添加管理员时，检查账号是否存在
        return adminService.findAdminByName(uName);
    }

    @RequestMapping("updateAdmin.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public void updateAdmin(User user){
        adminService.updateAdmin(user);
    }

    @RequestMapping("updateBySuperAdmin.do")
    @ResponseBody
    @RequiresRoles(value={"超级管理员"})
    public void updateBySuperAdmin(User user){
        adminService.updateBySuperAdmin(user);
    }

    @RequestMapping("deleteAdminByAId.do")
    @ResponseBody
    @RequiresRoles(value={"超级管理员"})
    public void deleteAdminByAId(String uStuNo){
        adminService.deleteAdminByAId(Integer.valueOf(uStuNo));
    }

    @RequestMapping("findAdminNotIncludeMe.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public User findAdminNotIncludeMe(User user){
        return adminService.findAdminNotIncludeMe(user);
    }

    @RequestMapping("checkPwd.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public String checkPwd(User user){  //修改用户之前验证原密码
        if (adminService.checkPwd(user)!=null){
            return "success";
        }
        return "fail";
    }

    @RequestMapping("updateAdminPwd.do")
    @ResponseBody
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public void updateAdminPwd(User user){ //管理员修改自己的密码
        adminService.updateAdminPwd(user);
    }

    @RequestMapping("toUpdatePage.do") //用于管理员修改密码后调到登录页重新登录
    @RequiresRoles(value={"普通管理员","超级管理员"},logical= Logical.OR)
    public String toUpdatePage(HttpServletRequest request){
//        request.getSession().invalidate(); //销毁session
//        adminService.cleanAllRedis(); //清除Redis中的所有数据
        return "/admin/updateAdmin";
    }

}
