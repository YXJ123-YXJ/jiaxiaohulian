package com.yxj.jiaxiaohulian.controller;

import com.yxj.jiaxiaohulian.entity.TongZhiGongGao;
import com.yxj.jiaxiaohulian.service.TongZhiService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tongZhi")
public class TongZhiController {

    @Autowired
    private TongZhiService tongZhiService;

    @RequestMapping("findAllTongZhi.do")
    @ResponseBody
    @RequiresAuthentication
    public Object findAllTongZhi(String uName){
        return tongZhiService.findAllTongZhi(uName);
    }

    @RequestMapping("addChaKan.do")
    @ResponseBody
    @RequiresAuthentication
    public void addChaKan(String uName,String tzId){
        tongZhiService.addChaKan(uName,Integer.valueOf(tzId));
    }

    @RequestMapping("shiFouChaKan.do")
    @ResponseBody
    @RequiresAuthentication
    public String shiFouChaKan(String uName,String tzId){
        return tongZhiService.shiFouChaKan(uName,Integer.valueOf(tzId));
    }

    @RequestMapping("findTongZhiByTzId.do")
    @ResponseBody
    @RequiresAuthentication
    public TongZhiGongGao findTongZhiByTzId(String tzId){
        return tongZhiService.findTongZhiByTzId(Integer.valueOf(tzId));
    }
}
