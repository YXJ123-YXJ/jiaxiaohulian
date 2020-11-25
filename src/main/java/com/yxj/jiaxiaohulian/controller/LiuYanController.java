package com.yxj.jiaxiaohulian.controller;

import com.yxj.jiaxiaohulian.entity.LiuYan;
import com.yxj.jiaxiaohulian.entity.User;
import com.yxj.jiaxiaohulian.service.LiuYanService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/liuYan")
public class LiuYanController {

    @Autowired
    private LiuYanService liuYanService;

    @RequestMapping("findUserByUQuanXian.do")
    @ResponseBody
    @RequiresAuthentication
    public Object findUserByUQuanXian(String uQuanXian,String uName){
        return liuYanService.findUserByUQuanXian(Integer.valueOf(uQuanXian),uName);
    }

    @RequestMapping("addLiuYan.do")
    @RequiresAuthentication
    public String addLiuYan(LiuYan liuYan, MultipartFile file){
        liuYanService.addLiuYan(liuYan,file);
        return "redirect:/user/liuYan/liuYan.html";
    }

    @RequestMapping("findLiuYanByUNameFrom.do")
    @ResponseBody
    @RequiresAuthentication
    public Object findLiuYanByUNameFrom(String pageNum,String uNameFrom){
        return liuYanService.findLiuYanByUNameFrom(Integer.valueOf(pageNum),uNameFrom);
    }

    @RequestMapping("findLiuYanByUNameTo.do")
    @ResponseBody
    @RequiresAuthentication
    public Object findLiuYanByUNameTo(String pageNum,String uNameTo){
        return liuYanService.findLiuYanByUNameTo(Integer.valueOf(pageNum),uNameTo);
    }

    @RequestMapping("huiFuLiuYan.do")
    @ResponseBody
    @RequiresAuthentication
    public void huiFuLiuYan(String lId,String lHuiFu){
        liuYanService.huiFuLiuYan(Integer.valueOf(lId),lHuiFu);
    }

}
