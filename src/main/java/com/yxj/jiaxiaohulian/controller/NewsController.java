package com.yxj.jiaxiaohulian.controller;

import com.github.pagehelper.PageInfo;
import com.yxj.jiaxiaohulian.entity.XueXiaoXinWen;
import com.yxj.jiaxiaohulian.service.NewsService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("findAllNews.do")
    @ResponseBody
    @RequiresAuthentication
    public Object findAllNews(@RequestParam("pageNum") String pageNum){
        return newsService.findAllNews(Integer.valueOf(pageNum));
    }

    @RequestMapping("addDianJiLiang.do")
    @ResponseBody
    @RequiresAuthentication
    public Integer addDianJiLiang(String uName,String xId){
        return newsService.addDianJiLiangIfNotDianJi(uName,Integer.valueOf(xId));
    }
}
