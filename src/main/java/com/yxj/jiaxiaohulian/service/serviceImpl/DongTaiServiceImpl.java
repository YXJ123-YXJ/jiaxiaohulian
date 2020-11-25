package com.yxj.jiaxiaohulian.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxj.jiaxiaohulian.dao.DongTaiDao;
import com.yxj.jiaxiaohulian.entity.PingLun;
import com.yxj.jiaxiaohulian.entity.ShiShengDongTai;
import com.yxj.jiaxiaohulian.service.DongTaiService;
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
public class DongTaiServiceImpl implements DongTaiService { //注：带有qian的表示前台页面的Redis数据

    @Autowired
    private DongTaiDao dongTaiDao;

    @Autowired
    private RedisUtil redisUtil;

    /***
    *@Description 查询全部动态
    *@Param [pageNum, uName] 此处uName没有实际作用
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 11:17
    */
    @Override
    public Object findAllDongDai(int pageNum,String uName) {
        Object obj = redisUtil.get("jiaxiaohulian:shishengdongtai:qianAllDongTai"+pageNum);
        if (obj==null){
            PageHelper.startPage(pageNum,3);
            List<ShiShengDongTai> list = dongTaiDao.findAllDongDai(null);
            PageInfo<ShiShengDongTai> pageInfo = new PageInfo<ShiShengDongTai>(list);
            List<ShiShengDongTai> list1 = pageInfo.getList();
            Map<Object,Object> map = new HashMap<>();
            map.put("list1",list1);
            map.put("maxPage",pageInfo.getPages());

            redisUtil.set("jiaxiaohulian:shishengdongtai:qianAllDongTai"+pageNum,map,1800L);//设置对应key的缓存过期时间（秒）
            obj = redisUtil.get("jiaxiaohulian:shishengdongtai:qianAllDongTai"+pageNum);
        }
        return obj;

//        System.out.println(dongTaiDao.findAllDongDai(uName));

        /*List<Integer> bdtIds = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            bdtIds.add(list.get(i).getDtId());
        }*/

        /*System.out.println("页数："+pageInfo.getPages());
        System.out.println("当前页面数据量："+pageInfo.getSize());
        System.out.println("总数："+pageInfo.getTotal());*/
//        return map;
    }
    
    /***
    *@Description 根据用户名查询我的动态
    *@Param [pageNum, uName]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 11:19
    */
    @Override
    public Object findMyDongTai(int pageNum,String uName) {
        Object obj = redisUtil.get("jiaxiaohulian:shishengdongtai:qianMyDongTai"+pageNum);
        if (obj==null){
            PageHelper.startPage(pageNum,3);
            List<ShiShengDongTai> list = dongTaiDao.findAllDongDai(uName);
            PageInfo<ShiShengDongTai> pageInfo = new PageInfo<ShiShengDongTai>(list);
            List<ShiShengDongTai> list1 = pageInfo.getList();
            Map<Object,Object> map = new HashMap<>();
            map.put("list1",list1);
            map.put("maxPage",pageInfo.getPages());

            redisUtil.set("jiaxiaohulian:shishengdongtai:qianMyDongTai"+pageNum,map,1800L);//设置对应key的缓存过期时间（秒）
            obj = redisUtil.get("jiaxiaohulian:shishengdongtai:qianMyDongTai"+pageNum);
        }
        return obj;

//        System.out.println(dongTaiDao.findAllDongDai(uName));

        /*List<Integer> bdtIds = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            bdtIds.add(list.get(i).getDtId());
        }*/

        /*System.out.println("页数："+pageInfo.getPages());
        System.out.println("当前页面数据量："+pageInfo.getSize());
        System.out.println("总数："+pageInfo.getTotal());*/
//        return map;
    }
    
    /***
    *@Description 用户发表动态
    *@Param [dongTai, file]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 11:21
    */
    @Override
    public void addDongTai(ShiShengDongTai dongTai, MultipartFile file) {
        dongTai.setDtCreateTime(new Date());
//        dongTai.setDtDianJiLiang(0);
        dongTai.setDtTuPian(FileUpload.upload(file));
        dongTaiDao.addDongTai(dongTai);

        List<ShiShengDongTai> list = dongTaiDao.findAllDongDai(null);
        int pageNum = list.size()%3==0?(list.size()/3):(list.size()/3+1);  //后台更新后，用户页面的Redis数据删除
        for (int i=1;i<=pageNum;i++){
            redisUtil.del("jiaxiaohulian:shishengdongtai:qianAllDongTai"+i);
            redisUtil.del("jiaxiaohulian:shishengdongtai:qianMyDongTai"+i);
        }
        redisUtil.del("jiaxiaohulian:shishengdongtai:dongTaiList");
    }
    
    /***
    *@Description 根据动态id查询对应动态的评论
    *@Param [bdtId]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 11:22
    */
    @Override
    public Object findPingLunByBdtId(Integer bdtId) {
        Object obj = redisUtil.get("jiaxiaohulian:pinglun:qianPinglun"+bdtId);
        if (obj==null){
            redisUtil.set("jiaxiaohulian:pinglun:qianPinglun"+bdtId,dongTaiDao.findPingLunByBdtId(bdtId),1800L);//设置对应key的缓存过期时间（秒）
            obj = redisUtil.get("jiaxiaohulian:pinglun:qianPinglun"+bdtId);
        }
        return obj;
    }
    
    /***
    *@Description 用户对动态发表评论，其中PingLun对象里包含了用户名和被评论的动态id
    *@Param [pingLun]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 11:24
    */
    @Override
    public void addPingLun(PingLun pingLun) {
        pingLun.setpCreateTime(new Date());
        dongTaiDao.addPingLun(pingLun);
        redisUtil.del("jiaxiaohulian:pinglun:qianPinglun"+pingLun.getBdtId());
    }
    
    /***
    *@Description 通过动态id删除自己发表的动态，同时删除对应动态的评论和浏览量
    *@Param [dtId]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 11:25
    */
    @Override
    public void deleteDongTaiById(Integer dtId) {
        dongTaiDao.deleteDongTaiById(dtId);
        dongTaiDao.deletePingLunByBDtId(dtId);
        dongTaiDao.deleteLiuLanByDtId(dtId);

        List<ShiShengDongTai> list = dongTaiDao.findAllDongDai(null);
        int pageNum = list.size()%3==0?(list.size()/3):(list.size()/3+1);  //后台更新后，用户页面的Redis数据删除
        for (int i=1;i<=pageNum;i++){
            redisUtil.del("jiaxiaohulian:shishengdongtai:qianAllDongTai"+i);
            redisUtil.del("jiaxiaohulian:shishengdongtai:qianMyDongTai"+i);
        }
        redisUtil.del("jiaxiaohulian:shishengdongtai:dongTaiList"); //删除后台Redis中的动态数据
        redisUtil.del("jiaxiaohulian:pinglun:qianPinglun"+dtId);
    }
    
    /***
    *@Description 增加浏览量（给动态浏览表添加一条记录，用count统计浏览量）
    *@Param [uName, dtId]
    *@Return java.lang.Integer
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 11:51
    */
    @Override
    public Integer addDianJiLiang(String uName, Integer dtId) { //返回更新后的动态浏览量
        Map<String,Object> map = new HashMap<>();
        map.put("uName",uName);
        map.put("dtId",dtId);
        if (dongTaiDao.findDianJiLiangByDtIdAndUName(map)==null){ //用户没有浏览过该条动态才执行添加浏览量操作
            dongTaiDao.addDianJiLiang(map);
            List<ShiShengDongTai> list = dongTaiDao.findAllDongDai(null);
            int pageNum = list.size()%3==0?(list.size()/3):(list.size()/3+1);  //后台更新后，用户页面的Redis数据删除
            for (int i=1;i<=pageNum;i++){
                redisUtil.del("jiaxiaohulian:shishengdongtai:qianAllDongTai"+i);
                redisUtil.del("jiaxiaohulian:shishengdongtai:qianMyDongTai"+i);
            }
            redisUtil.del("jiaxiaohulian:shishengdongtai:dongTaiList"); //删除后台Redis中的动态数据
        }
        return dongTaiDao.findDianJiLiangByDtId(dtId);
    }
}
