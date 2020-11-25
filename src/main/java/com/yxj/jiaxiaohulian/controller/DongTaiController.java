package com.yxj.jiaxiaohulian.controller;

import com.yxj.jiaxiaohulian.entity.PingLun;
import com.yxj.jiaxiaohulian.entity.ShiShengDongTai;
import com.yxj.jiaxiaohulian.service.DongTaiService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/dongTai")
public class DongTaiController {

    @Autowired
    public DongTaiService dongTaiService;

    @RequestMapping("findAllDongTai.do")
    @ResponseBody
    @RequiresAuthentication
    public Object findAllDongTai(@RequestParam("pageNum") String pageNum,String uName){
        return dongTaiService.findAllDongDai(Integer.valueOf(pageNum),uName);
    }

    @RequestMapping("findMyDongTai.do")
    @ResponseBody
    //属于teacher或者student之一;修改logical为OR 即可
    @RequiresRoles(value={"student","teacher"},logical= Logical.OR)
    public Object findMyDongTai(@RequestParam("pageNum") String pageNum,String uName){
        return dongTaiService.findMyDongTai(Integer.valueOf(pageNum),uName);
    }

    @RequestMapping("addDongTai.do")
    @RequiresRoles(value={"teacher","student"},logical= Logical.OR)
    public String addDongTai(ShiShengDongTai dongTai, MultipartFile file){
        System.out.println(dongTai);
        dongTaiService.addDongTai(dongTai,file);
        return "redirect:/user/dongTai/dongTai.html";
    }

    @RequestMapping("findPingLunByBdtId.do")
    @ResponseBody
    @RequiresAuthentication
    public Object findPingLunByBdtId(Integer bdtId){
        return dongTaiService.findPingLunByBdtId(bdtId);
    }

    @RequestMapping("addPingLun.do")
    @ResponseBody
    @RequiresAuthentication
    public void addPingLun(PingLun pingLun){
        dongTaiService.addPingLun(pingLun);
    }

    @RequestMapping("addPingLunIndex.do")
    @RequiresAuthentication
    public String addPingLunIndex(PingLun pingLun){
        dongTaiService.addPingLun(pingLun);
        return "redirect:/index.html";
    }

    @RequestMapping("deleteDongTaiById.do")
    @ResponseBody
    @RequiresRoles(value={"student","teacher"},logical= Logical.OR)
    public void deleteDongTaiById(String dtId){
        dongTaiService.deleteDongTaiById(Integer.valueOf(dtId));
    }

    @RequestMapping("addDianJiLiang.do")
    @ResponseBody
    @RequiresAuthentication
    public Integer addDianJiLiang(String uName,String dtId){
        return dongTaiService.addDianJiLiang(uName,Integer.valueOf(dtId));
    }

}
