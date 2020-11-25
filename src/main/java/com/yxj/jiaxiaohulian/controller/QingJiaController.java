package com.yxj.jiaxiaohulian.controller;

import com.yxj.jiaxiaohulian.entity.QingJia;
import com.yxj.jiaxiaohulian.service.QingJiaService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/qingJia")
public class QingJiaController {

    @Autowired
    private QingJiaService qingJiaService;

    @RequestMapping("addQingJia.do")
    @ResponseBody
    @RequiresRoles(value={"student"})
    public void addQingJia(QingJia qingJia){
        qingJiaService.addQingJia(qingJia);
    }

    @RequestMapping("findQingJiaByUsName.do")
    @ResponseBody
    @RequiresRoles(value={"student"})
    public Object findQingJiaByUsName(String usName){
        return qingJiaService.findQingJiaByUsName(usName);
    }

    @RequestMapping("findQingJiaByUtjName.do")
    @ResponseBody
    @RequiresRoles(value={"teacher","jiazhang"},logical= Logical.OR)
    public Object findQingJiaByUtName(String utName,String ujName){
        return qingJiaService.findQingJiaByUtjName(utName,ujName);
    }

    @RequestMapping("qUtjFlagChange.do")
    @ResponseBody
    @RequiresRoles(value={"teacher","jiazhang"},logical= Logical.OR)
    public void qUtjFlagChange(String qId,String qUtFlag,String qUjFlag){
        qingJiaService.qUtjFlagChange(qId,qUtFlag,qUjFlag);
    }

    /*@RequestMapping("findQingJiaByUjName.do")
    @ResponseBody
    public List<QingJia> findQingJiaByUjName(String ujName){
        return qingJiaService.findQingJiaByUtjName(null,ujName);
    }*/
}
