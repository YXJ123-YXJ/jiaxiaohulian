package com.yxj.jiaxiaohulian.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxj.jiaxiaohulian.dao.NewsDao;
import com.yxj.jiaxiaohulian.entity.XueXiaoXinWen;
import com.yxj.jiaxiaohulian.service.NewsService;
import com.yxj.jiaxiaohulian.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private RedisUtil redisUtil;

    /***
    *@Description 查询所有新闻
    *@Param [pageNum]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:08
    */
    @Override
    public Object findAllNews(int pageNum) {
        Object obj = redisUtil.get("jiaxiaohulian:xuexiaoxinwen:qianAllNews"+pageNum);
        if (obj==null) {
            PageHelper.startPage(pageNum, 3);
            List<XueXiaoXinWen> list = newsDao.findAllNews();
            PageInfo<XueXiaoXinWen> pageInfo = new PageInfo<XueXiaoXinWen>(list);
            List<XueXiaoXinWen> list1 = pageInfo.getList();
            Map<Object, Object> map = new HashMap<>();
            map.put("list1", list1);
            map.put("maxPage", pageInfo.getPages());

            redisUtil.set("jiaxiaohulian:xuexiaoxinwen:qianAllNews"+pageNum,map,1800L);
            obj = redisUtil.get("jiaxiaohulian:xuexiaoxinwen:qianAllNews"+pageNum);
        }
        /*System.out.println("页数："+pageInfo.getPages());
        System.out.println("当前页面数据量："+pageInfo.getSize());
        System.out.println("总数："+pageInfo.getTotal());*/
        return obj;
    }

    /***
    *@Description 添加点击量，并返回更新后的点击量，每个用户只能贡献一个点击量
    *@Param [uName, xId]
    *@Return java.lang.Integer
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:08
    */
    @Override
    public Integer addDianJiLiangIfNotDianJi(String uName, Integer xId) {
        Map<String,Object> map = new HashMap<>();
        map.put("uName",uName);
        map.put("xId",xId);
        if (newsDao.findDianJiByUNameAndXId(map)==null){
            newsDao.addDianJiLiang(map);

            List<XueXiaoXinWen> list = newsDao.findAllNews();
            int count = list.size()%3==0?list.size():(list.size()+1);
            for (int i = 1; i <= count; i++) {
                redisUtil.del("jiaxiaohulian:xuexiaoxinwen:qianAllNews"+i);
            }
            redisUtil.del("jiaxiaohulian:xuexiaoxinwen:newsList");//删除后台新闻的Redis数据
        }
        return newsDao.findDianJiLiangByXId(xId);
    }


}
