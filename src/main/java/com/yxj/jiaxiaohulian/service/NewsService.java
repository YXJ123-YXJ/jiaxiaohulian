package com.yxj.jiaxiaohulian.service;

import com.github.pagehelper.PageInfo;
import com.yxj.jiaxiaohulian.entity.XueXiaoXinWen;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface NewsService {

    public Object findAllNews(int pageNum);
    public Integer addDianJiLiangIfNotDianJi(String uName,Integer xId);
}
