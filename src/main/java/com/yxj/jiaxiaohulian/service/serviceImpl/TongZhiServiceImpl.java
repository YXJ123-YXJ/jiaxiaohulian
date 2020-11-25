package com.yxj.jiaxiaohulian.service.serviceImpl;

import com.yxj.jiaxiaohulian.dao.TongZhiDao;
import com.yxj.jiaxiaohulian.entity.TongZhiGongGao;
import com.yxj.jiaxiaohulian.service.TongZhiService;
import com.yxj.jiaxiaohulian.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TongZhiServiceImpl implements TongZhiService {

    @Autowired
    private TongZhiDao tongZhiDao;

    @Autowired
    private RedisUtil redisUtil;

    /***
    *@Description 查询所有通知 同时查询该用户对这些通知的查看情况（是否查看）
    *@Param [uName]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:13
    */
    @Override
    public Object findAllTongZhi(String uName) {
        Object obj = redisUtil.get("jiaxiaohulian:tongzhigonggao:qianFindAllTongZhi");
        if (obj==null) {
            List<TongZhiGongGao> list = tongZhiDao.findAllTongZhi(null);
            List<Map<String, Object>> list1 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("tongZhi", list.get(i)); //将通知信息封装到map中
                map.put("chaKan", shiFouChaKan(uName, list.get(i).getTzId())); //将是否查看封装到map中
                list1.add(map);
            }
            redisUtil.set("jiaxiaohulian:tongzhigonggao:qianFindAllTongZhi",list1,1800L);
            obj = redisUtil.get("jiaxiaohulian:tongzhigonggao:qianFindAllTongZhi");
        }
        return obj;
    }

    /***
    *@Description 如果用户没有查看该tzId对应的通知，则在用户查看后将是否查看的状态改为 已查看
    *@Param [uName, tzId]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:13
    */
    @Override
    public void addChaKan(String uName, Integer tzId) {
        Map<String,Object> map = new HashMap<>();
        map.put("uName",uName);
        map.put("tzId",tzId);
        if (tongZhiDao.findChaKanByUNameAndTzId(map)==null){
            tongZhiDao.addChaKan(map);
            redisUtil.del("jiaxiaohulian:tongzhigonggao:qianFindAllTongZhi");
        }
    }

    /***
    *@Description 查询用户对对应通知的查看情况，此方法供上边的findAllTongZhi()调用
    *@Param [uName, tzId]
    *@Return java.lang.String
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:16
    */
    @Override
    public String shiFouChaKan(String uName, Integer tzId) {
        Map<String,Object> map = new HashMap<>();
        map.put("uName",uName);
        map.put("tzId",tzId);
        if (tongZhiDao.findChaKanByUNameAndTzId(map)==null){
            return "未查看";
        }
        return "已查看";
    }

    /***
    *@Description 根据tzId查询单条公告的信息，用于显示通知内容的详细信息
    *@Param [tzId]
    *@Return com.yxj.jiaxiaohulian.entity.TongZhiGongGao
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:16
    */
    @Override
    public TongZhiGongGao findTongZhiByTzId(Integer tzId) {
        return tongZhiDao.findAllTongZhi(tzId).get(0);
    }
}
