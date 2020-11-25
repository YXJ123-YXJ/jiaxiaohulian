package com.yxj.jiaxiaohulian.service.serviceImpl;

import com.yxj.jiaxiaohulian.dao.QingJiaDao;
import com.yxj.jiaxiaohulian.entity.QingJia;
import com.yxj.jiaxiaohulian.service.QingJiaService;
import com.yxj.jiaxiaohulian.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class QingJiaServiceImpl implements QingJiaService {

    @Autowired
    private QingJiaDao qingJiaDao;

    @Autowired
    private RedisUtil redisUtil;
    
    /***
    *@Description 学生请假
    *@Param [qingJia]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:09
    */
    @Override
    public void addQingJia(QingJia qingJia) {
        qingJiaDao.addQingJia(qingJia);

        redisUtil.del("jiaxiaohulian:qingjia:qianFindQingJiaByUsName");
        redisUtil.del("jiaxiaohulian:qingjia:qianFindQingJiaByUtName");
        redisUtil.del("jiaxiaohulian:qingjia:qianFindQingJiaByUjName");
        redisUtil.del("jiaxiaohulian:qingjia:findQingJiaOverSevenDays");
    }
    
    /***
    *@Description 学生查询自己的请假记录
    *@Param [usName]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:09
    */
    @Override
    public Object findQingJiaByUsName(String usName) {
        Object obj = redisUtil.get("jiaxiaohulian:qingjia:qianFindQingJiaByUsName");
        if (obj==null){
            redisUtil.set("jiaxiaohulian:qingjia:qianFindQingJiaByUsName",qingJiaDao.findQingJiaByUsName(usName),1800L);
            obj = redisUtil.get("jiaxiaohulian:qingjia:qianFindQingJiaByUsName");
        }
        return obj;
    }

    /***
    *@Description 辅导员或家长查询自己的学生的请假列表
    *@Param [utName, ujName]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:10
    */
    @Override
    public Object findQingJiaByUtjName(String utName,String ujName) {
        Map<String,String> map = new HashMap<>();
        map.put("utName",utName);
        map.put("ujName",ujName);
        if (utName!=null&&utName!=""){
            Object obj = redisUtil.get("jiaxiaohulian:qingjia:qianFindQingJiaByUtName");
            if (obj==null){
                redisUtil.set("jiaxiaohulian:qingjia:qianFindQingJiaByUtName",qingJiaDao.findQingJiaByUtName(map),1800L);
                obj = redisUtil.get("jiaxiaohulian:qingjia:qianFindQingJiaByUtName");
            }
            return obj;
        }else {
            Object obj = redisUtil.get("jiaxiaohulian:qingjia:qianFindQingJiaByUjName");
            if (obj==null){
                redisUtil.set("jiaxiaohulian:qingjia:qianFindQingJiaByUjName",qingJiaDao.findQingJiaByUtName(map),1800L);
                obj = redisUtil.get("jiaxiaohulian:qingjia:qianFindQingJiaByUjName");
            }
            return obj;
        }
    }
    
    /***
    *@Description 辅导员或家长 同意或不同意学生的请假申请
    *@Param [qId, qUtFlag, qUjFlag]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:11
    */
    @Override
    public void qUtjFlagChange(String qId,String qUtFlag,String qUjFlag) {
        Map<String,String> map = new HashMap<>();
        map.put("qId",qId);
        map.put("qUtFlag",qUtFlag);
        map.put("qUjFlag",qUjFlag);
        qingJiaDao.qUtFlagChange(map);

        redisUtil.del("jiaxiaohulian:qingjia:qianFindQingJiaByUsName");
        redisUtil.del("jiaxiaohulian:qingjia:qianFindQingJiaByUtName");
        redisUtil.del("jiaxiaohulian:qingjia:qianFindQingJiaByUjName");
        redisUtil.del("jiaxiaohulian:qingjia:findQingJiaOverSevenDays");
    }
}
