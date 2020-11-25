package com.yxj.jiaxiaohulian.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxj.jiaxiaohulian.dao.LiuYanDao;
import com.yxj.jiaxiaohulian.entity.LiuYan;
import com.yxj.jiaxiaohulian.entity.User;
import com.yxj.jiaxiaohulian.service.LiuYanService;
import com.yxj.jiaxiaohulian.util.FileUpload;
import com.yxj.jiaxiaohulian.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class LiuYanServiceImpl implements LiuYanService { //注：带有qian的表示前台页面的Redis数据

    @Autowired
    private LiuYanDao liuYanDao;

    @Autowired
    private RedisUtil redisUtil;
    
    /***
    *@Description 添加留言时下拉列表根据选择的权限动态变化对应的姓名(不包含自己)
    *@Param [uQuanXian, uName]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 11:52
    */
    @Override
    public Object findUserByUQuanXian(Integer uQuanXian,String uName) { 
        Object obj = redisUtil.get("jiaxiaohulian:userSelect:qianUserSelect"+uQuanXian);
        if (obj==null) {
            Map<String, Object> map = new HashMap<>();
            map.put("uQuanXian", uQuanXian);
            map.put("uName", uName);

            redisUtil.set("jiaxiaohulian:userSelect:qianUserSelect"+uQuanXian,liuYanDao.findUserByUQuanXian(map),1800L);
            obj = redisUtil.get("jiaxiaohulian:userSelect:qianUserSelect"+uQuanXian);
        }
        return obj;
    }

    /***
    *@Description 添加留言（file暂时没用上）
    *@Param [liuYan, file]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 11:53
    */
    @Override
    public void addLiuYan(LiuYan liuYan, MultipartFile file) {

//        String lFuJian = FileUpload.upload(file);
//        liuYan.setlFuJian(lFuJian);
        liuYan.setlCreateTime(new Date());
        liuYanDao.addLiuYan(liuYan);
        
        //执行完添加留言后删除留言Redis数据
        List<LiuYan> list = liuYanDao.findLiuYanByUNameFrom(null);
        int count = list.size()%3==0?list.size():(list.size()+1);
        for (int i = 1; i <= count; i++) {
            redisUtil.del("jiaxiaohulian:liuyan:qianLiuYan"+i);
            redisUtil.del("jiaxiaohulian:liuyan:qianLiuYanByUNameTo"+i);
        }
        redisUtil.del("jiaxiaohulian:user:liuYanList");

    }

    /***
    *@Description 查询我发的留言
    *@Param [pageNum, uNameFrom]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 11:54
    */
    @Override
    public Object findLiuYanByUNameFrom(Integer pageNum,String uNameFrom) {
        Object obj = redisUtil.get("jiaxiaohulian:liuyan:qianLiuYan"+pageNum);
        if (obj==null) {
            PageHelper.startPage(pageNum, 3);
            List<LiuYan> list = liuYanDao.findLiuYanByUNameFrom(uNameFrom);
            PageInfo<LiuYan> pageInfo = new PageInfo<LiuYan>(list);
            List<LiuYan> list1 = pageInfo.getList();
            Map<String, Object> map = new HashMap<>();
            map.put("list1", list1);
            map.put("maxPage", pageInfo.getPages());

            redisUtil.set("jiaxiaohulian:liuyan:qianLiuYan"+pageNum,map,1800L);
            obj = redisUtil.get("jiaxiaohulian:liuyan:qianLiuYan"+pageNum);
        }
        return obj;
    }
    
    /***
    *@Description 查询别人给我的留言时，先查留言表获得留言信息，再查用户表获得给我留言的人的信息，
     *            将这两个对象放入map中，再将map放入list中返回
    *@Param [pageNum, uNameTo]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 11:56
    */
    //
    @Override
    public Object findLiuYanByUNameTo(Integer pageNum,String uNameTo) {
        Object obj = redisUtil.get("jiaxiaohulian:liuyan:qianLiuYanByUNameTo"+pageNum);
        if (obj==null) {
            PageHelper.startPage(pageNum, 3);
            List<LiuYan> liuYanByUNameTo = liuYanDao.findLiuYanByUNameTo(uNameTo);
            List<Map<String, Object>> list = new ArrayList<>();
            PageInfo<LiuYan> pageInfo = new PageInfo<LiuYan>(liuYanByUNameTo);
            List<LiuYan> list1 = pageInfo.getList();
            for (int i = 0; i < list1.size(); i++) {
                LiuYan liuYan = list1.get(i);
                Map<String, Object> map = new HashMap<>();
                User uNameFromMsg = liuYanDao.findUNameFromMsg(liuYan.getuNameFrom());
                map.put("liuYan", liuYan);
                map.put("uNameFromMsg", uNameFromMsg);
                map.put("maxPage", pageInfo.getPages());
                list.add(map);
            }

            redisUtil.set("jiaxiaohulian:liuyan:qianLiuYanByUNameTo"+pageNum,list,1800L);
            obj = redisUtil.get("jiaxiaohulian:liuyan:qianLiuYanByUNameTo"+pageNum);
        }
        return obj;
    }
    
    /***
    *@Description 根据留言id回复留言
    *@Param [lId, lHuiFu]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 11:59
    */
    @Override
    public void huiFuLiuYan(Integer lId, String lHuiFu) {
        Map<String,Object> map = new HashMap<>();
        map.put("lId",lId);
        map.put("lHuiFu",lHuiFu);
        liuYanDao.huiFuLiuYan(map);

        List<LiuYan> list = liuYanDao.findLiuYanByUNameFrom(null);
        int count = list.size()%3==0?list.size():(list.size()+1);
        for (int i = 1; i <= count; i++) {
            redisUtil.del("jiaxiaohulian:liuyan:qianLiuYan"+i);
            redisUtil.del("jiaxiaohulian:liuyan:qianLiuYanByUNameTo"+i);
        }
        redisUtil.del("jiaxiaohulian:user:userToShenHeList");
    }
}
