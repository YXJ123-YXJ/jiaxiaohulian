package com.yxj.jiaxiaohulian.service.serviceImpl;

import com.yxj.jiaxiaohulian.dao.AdminDao;
import com.yxj.jiaxiaohulian.entity.*;
import com.yxj.jiaxiaohulian.service.AdminService;
import com.yxj.jiaxiaohulian.service.LoginService;
import com.yxj.jiaxiaohulian.util.FileUpload;
import com.yxj.jiaxiaohulian.util.RedisUtil;
import com.yxj.jiaxiaohulian.util.SaltUtils;
import com.yxj.jiaxiaohulian.util.shiro.CustomLoginToken;
import com.yxj.jiaxiaohulian.util.shiro.UserType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisUtil redisUtil;

//    @Override
//    public boolean adminLogin(User user, HttpServletRequest request) {
        /*Admin admin1 = adminDao.adminLogin(admin);
        if (admin1!=null){
            request.getSession().setAttribute("LOGIN_ADMIN",admin1);
            return true;
        }
        return false;*/

        //获取当前用户
        /*Subject subject = SecurityUtils.getSubject();
        //封装用户登录数据
        UsernamePasswordToken token = new CustomLoginToken(user.getuName(),user.getuPwd(), UserType.ADMIN);
        System.out.println("Admin..........................................");
        try {
            subject.login(token);
            User user1 = adminDao.findRolesByAName(user.getuName()).get(0);
            request.getSession().setAttribute("LOGIN_ADMIN", user1);
            return true;
        } catch (UnknownAccountException e){ //用户名不存在
            return false;
        } catch (IncorrectCredentialsException e){ //密码错误
            return false;
        } catch (AuthenticationException e){ //shiro的大异常
            return false;
        }

    }*/
    
    /***
    *@Description 管理员添加新闻
    *@Param [xueXiaoXinWen, file]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:18
    */
    @Override
    public void addNews(XueXiaoXinWen xueXiaoXinWen, MultipartFile file) {
        String xTuPian = FileUpload.upload(file);
        xueXiaoXinWen.setxCreateTime(new Date());
        xueXiaoXinWen.setxTuPian(xTuPian);
        adminDao.addNews(xueXiaoXinWen);
        redisUtil.del("jiaxiaohulian:xuexiaoxinwen:newsList");
    }
    
    /***
    *@Description 管理员查询新闻（包括查询全部和按条件查询）
    *@Param [xueXiaoXinWen]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:18
    */
    @Override
    public Object findNews(XueXiaoXinWen xueXiaoXinWen) {
        if (xueXiaoXinWen==null||(xueXiaoXinWen.getxType()==""&&xueXiaoXinWen.getxName()=="")) {
            Object obj = redisUtil.get("jiaxiaohulian:xuexiaoxinwen:newsList");
            if (obj == null) {
                List<XueXiaoXinWen> news1 = adminDao.findNews(xueXiaoXinWen);
                redisUtil.set("jiaxiaohulian:xuexiaoxinwen:newsList", news1,1800L);//设置对应key的缓存过期时间（秒）
                obj = redisUtil.get("jiaxiaohulian:xuexiaoxinwen:newsList");
            }
            return obj;
        }
        return adminDao.findNews(xueXiaoXinWen);
    }
    
    /***
    *@Description 管理员通过新闻id删除对应的新闻
    *@Param [xId]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:19
    */
    @Override
    public void deleteNewsByXId(Integer xId) {
        adminDao.deleteNewsByXId(xId);
        adminDao.deleteDianJiLiangByXId(xId);
        redisUtil.del("jiaxiaohulian:xuexiaoxinwen:newsList");

        //同时删除前台新闻Redis的数据
        List<XueXiaoXinWen> list = adminDao.findNews(null);
        int count = list.size()%3==0?list.size():(list.size()+1);
        for (int i = 1; i <= count; i++) {
            redisUtil.del("jiaxiaohulian:xuexiaoxinwen:qianAllNews"+i);
        }
    }
    
    /***
    *@Description 管理员查询动态（包括查询全部和按条件查询）
    *@Param [shiShengDongTai]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:19
    */
    @Override
    public Object findDongTai(ShiShengDongTai shiShengDongTai) {
        if (shiShengDongTai==null||(shiShengDongTai.getDtName()==""&&shiShengDongTai.getuName()=="")){
            Object obj = redisUtil.get("jiaxiaohulian:shishengdongtai:dongTaiList");
            if (obj==null){
                redisUtil.set("jiaxiaohulian:shishengdongtai:dongTaiList",adminDao.findDongTai(shiShengDongTai),1800L);//设置对应key的缓存过期时间（秒）
                obj = redisUtil.get("jiaxiaohulian:shishengdongtai:dongTaiList");
            }
            return obj;
        }
        return adminDao.findDongTai(shiShengDongTai);
    }
    
    /***
    *@Description 管理员按dtId删除动态，同时删除对应动态的评论 和 浏览量
    *@Param [dtId]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:20
    */
    @Override
    public void deleteDongTaiByDtId(Integer dtId) { 
        adminDao.deleteDongTaiByDtId(dtId);
        adminDao.deletePingLunByDtId(dtId);
        adminDao.deleteDianJiLiangByDtId(dtId);

        List<ShiShengDongTai> list = adminDao.findDongTai(null);
        int pageNum = list.size()%3==0?(list.size()/3):(list.size()/3+1);  //后台更新后，用户页面的Redis数据删除
        for (int i=1;i<=pageNum;i++){
            redisUtil.del("jiaxiaohulian:shishengdongtai:userAllDongTai"+i);
            redisUtil.del("jiaxiaohulian:shishengdongtai:userMyDongTai"+i);
        }

        redisUtil.del("jiaxiaohulian:shishengdongtai:dongTaiList");
    }
    
    /***
    *@Description 管理员发表通知公告
    *@Param [tongZhiGongGao]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:21
    */
    @Override
    public void addTongZhi(TongZhiGongGao tongZhiGongGao) {
        tongZhiGongGao.setTzCreateTime(new Date());
        adminDao.addTongZhi(tongZhiGongGao);
        redisUtil.del("jiaxiaohulian:tongzhigonggao:tongZhiList");
        redisUtil.del("jiaxiaohulian:tongzhigonggao:qianFindAllTongZhi");
    }
    
    /***
    *@Description 管理员查询通知公告（包括查询全部和按条件查询）
    *@Param [tongZhiGongGao]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:21
    */
    @Override
    public Object findTongZhi(TongZhiGongGao tongZhiGongGao) {
        if (tongZhiGongGao==null||(tongZhiGongGao.getTzName()==""&&tongZhiGongGao.getTzTextFrom()==""&&tongZhiGongGao.getTzTextFor()==""&&tongZhiGongGao.getTzLevel()==null)){
            Object obj = redisUtil.get("jiaxiaohulian:tongzhigonggao:tongZhiList");
            if (obj==null){
                redisUtil.set("jiaxiaohulian:tongzhigonggao:tongZhiList",adminDao.findTongZhi(tongZhiGongGao),1800L);//设置对应key的缓存过期时间（秒）
                obj = redisUtil.get("jiaxiaohulian:tongzhigonggao:tongZhiList");
            }
            return obj;
        }
        return adminDao.findTongZhi(tongZhiGongGao);
    }
    
    /***
    *@Description 通过tzId删除通知，通知删除对应的公告查看记录
    *@Param [tzId]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:21
    */
    @Override
    public void deleteTongZhiByTzId(Integer tzId) { 
        adminDao.deleteTongZhiByTzId(tzId);
        adminDao.deleteGongGaoChaKanByTzId(tzId);
        redisUtil.del("jiaxiaohulian:tongzhigonggao:tongZhiList");
        redisUtil.del("jiaxiaohulian:tongzhigonggao:qianFindAllTongZhi");
    }
    
    /***
    *@Description 通过通知id修改对应通知的内容
    *@Param [tongZhiGongGao]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:22
    */
    @Override
    public void updateTongZhiByTzId(TongZhiGongGao tongZhiGongGao) {
        adminDao.updateTongZhiByTzId(tongZhiGongGao);
        redisUtil.del("jiaxiaohulian:tongzhigonggao:tongZhiList");
        redisUtil.del("jiaxiaohulian:tongzhigonggao:qianFindAllTongZhi");
    }
    
    /***
    *@Description 管理员查询留言（包括全部查询和按条件查询）
    *@Param [liuYan]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:23
    */
    @Override
    public Object findLiuYan(LiuYan liuYan) {
        if (liuYan==null||(liuYan.getuNameFrom()==""&&liuYan.getuNameTo()=="")){
            Object obj = redisUtil.get("jiaxiaohulian:liuyan:liuYanList");
            if (obj==null){
                redisUtil.set("jiaxiaohulian:liuyan:liuYanList",adminDao.findLiuYan(liuYan),1800L);//设置对应key的缓存过期时间（秒）
                obj = redisUtil.get("jiaxiaohulian:liuyan:liuYanList");
            }
            return obj;
        }
        return adminDao.findLiuYan(liuYan);
    }
    
    /***
    *@Description 管理员通过留言id删除对应的留言
    *@Param [lId]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:23
    */
    @Override
    public void deleteLiuYanByLId(Integer lId) {
        adminDao.deleteLiuYanByLId(lId);
        redisUtil.del("jiaxiaohulian:liuyan:liuYanList");

        List<LiuYan> list = adminDao.findLiuYan(null);
        int count = list.size()%3==0?list.size():(list.size()+1);
        for (int i = 1; i <= count; i++) {
            redisUtil.del("jiaxiaohulian:liuyan:qianLiuYan"+i);
            redisUtil.del("jiaxiaohulian:liuyan:qianLiuYanByUNameTo"+i);
        }
    }
    
    /***
    *@Description 查询系统信息
    *@Param []
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:24
    */
    @Override
    public Object findSystem() {
        Object obj = redisUtil.get("jiaxiaohulian:system:systems");
        if (obj==null){
            redisUtil.set("jiaxiaohulian:system:systems",adminDao.findSystem(),1800L);//设置对应key的缓存过期时间（秒）
            obj = redisUtil.get("jiaxiaohulian:system:systems");
        }
        return obj;
    }
    
    /***
    *@Description 修改系统信息
    *@Param [system]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:24
    */
    @Override
    public void updateSystem(Systems system) {
        adminDao.updateSystem(system);
        redisUtil.del("jiaxiaohulian:system:systems");
    }
    
    /***
    *@Description 添加友情链接
    *@Param [youQingLianJie]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:24
    */
    @Override
    public void addWangZhan(YouQingLianJie youQingLianJie) {
        youQingLianJie.setLjCreateTime(new Date());
        adminDao.addWangZhan(youQingLianJie);
        redisUtil.del("jiaxiaohulian:youqinglianjie:youQingLianJieList");
        redisUtil.del("jiaxiaohulian:youqinglianjie:qianWangZhan");
    }
    
    /***
    *@Description 查询友情链接
    *@Param [youQingLianJie]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:24
    */
    @Override
    public Object findWangZhan(YouQingLianJie youQingLianJie) {
        if (youQingLianJie==null||(youQingLianJie.getLjName()==""&&youQingLianJie.getLjSrc()=="")){
            Object obj = redisUtil.get("jiaxiaohulian:youqinglianjie:youQingLianJieList");
            if (obj==null){
                redisUtil.set("jiaxiaohulian:youqinglianjie:youQingLianJieList",adminDao.findWangZhan(youQingLianJie),1800L);//设置对应key的缓存过期时间（秒）
                obj = redisUtil.get("jiaxiaohulian:youqinglianjie:youQingLianJieList");
            }
            return obj;
        }
        return adminDao.findWangZhan(youQingLianJie);
    }
    
    /***
    *@Description 通过友情链接id修改友情链接
    *@Param [youQingLianJie]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:25
    */
    @Override
    public void updateWangZhanByLjId(YouQingLianJie youQingLianJie) {
        youQingLianJie.setLjCreateTime(new Date());
        adminDao.updateWangZhanByLjId(youQingLianJie);
        redisUtil.del("jiaxiaohulian:youqinglianjie:youQingLianJieList");
        redisUtil.del("jiaxiaohulian:youqinglianjie:qianWangZhan");
    }

    /***
    *@Description 通过友情链接id删除友情链接
    *@Param [ljId]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:25
    */
    @Override
    public void deleteWangZhanByLjId(Integer ljId) {
        adminDao.deleteWangZhanByLjId(ljId);
        redisUtil.del("jiaxiaohulian:youqinglianjie:youQingLianJieList");
        redisUtil.del("jiaxiaohulian:youqinglianjie:qianWangZhan");
    }

    /***
    *@Description 根距用户权限查询对应的用户
    *@Param [user]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:26
    */
    @Override
    public Object findUser(User user) {
        if (user.getuQuanXian()==1){
            if (user.getuName()==""&&user.getuSex()==""&&user.getuYuanXi()==""){
                Object obj = redisUtil.get("jiaxiaohulian:user:userList");
                if (obj==null){
                    redisUtil.set("jiaxiaohulian:user:userList",adminDao.findUser(null),1800L);//设置对应key的缓存过期时间（秒）
                    obj = redisUtil.get("jiaxiaohulian:user:userList");
                }
                return obj;
            }
            return adminDao.findUser(user);
        }
        if (user.getuQuanXian()==2){
            if (user.getuName()==""&&user.getuDiZhi()==""&&user.getuPhone()==""&&user.getUsName()==""){
                Object obj = redisUtil.get("jiaxiaohulian:user:userList");
                if (obj==null){
                    redisUtil.set("jiaxiaohulian:user:userList",adminDao.findUser(null),1800L);//设置对应key的缓存过期时间（秒）
                    obj = redisUtil.get("jiaxiaohulian:user:userList");
                }
                return obj;
            }
            return adminDao.findUser(user);
        }
        if (user.getuQuanXian()==3){
            if (user.getuName()==""&&user.getuYuanXi()==""&&user.getuClazz()==""&&user.getuSex()==""){
                Object obj = redisUtil.get("jiaxiaohulian:user:userList");
                if (obj==null){
                    redisUtil.set("jiaxiaohulian:user:userList",adminDao.findUser(null),1800L);//设置对应key的缓存过期时间（秒）
                    obj = redisUtil.get("jiaxiaohulian:user:userList");
                }
                return obj;
            }
            return adminDao.findUser(user);
        }
        return null;
    }

    /***
    *@Description 根距用户名修改用户的状态（0:审核中   1:正常   2:审核不通过   3:账号被冻结）
    *@Param [user]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:28
    */
    @Override
    public void updateUserStateByUName(User user) {
        adminDao.updateUserStateByUName(user);
        if (user.getuShenHe()==1||user.getuShenHe()==3){
            redisUtil.del("jiaxiaohulian:user:userToShenHeList");
            redisUtil.del("jiaxiaohulian:user:userList");
            //添加留言时的下拉列表的数据变化 其中的i代表不同的权限，根据不同的权限显示不同的人
            for (int i = 1; i <= 3; i++) {
                redisUtil.del("jiaxiaohulian:userSelect:qianUserSelect"+i);
            }
        }
        if (user.getuShenHe()==2){
            redisUtil.del("jiaxiaohulian:user:userToShenHeList");
            //添加留言时的下拉列表的数据变化 其中的i代表不同的权限，根据不同的权限显示不同的人
            /*for (int i = 1; i <= 3; i++) {
                redisUtil.del("jiaxiaohulian:userSelect:qianUserSelect"+i);
            }*/
            redisUtil.del("jiaxiaohulian:user:userList");
        }
    }

    /***
    *@Description 通过用户名删除用户同时删除与该用户有关的信息
     *              注意：要先删除评论再删除动态
    *@Param [uName]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:28
    */
    @Override
    public void deleteUserByUName(String uName) {
        adminDao.deleteUserByUName(uName);
        adminDao.deleteGongGaoChaKanByUName(uName);
        adminDao.deleteLiuYanByUNameFrom(uName);
        adminDao.deletePingLunByUName(uName);
        Integer[] bdtIds = adminDao.findDtIdsByUName(uName);
        if (bdtIds.length>0){ //判断从findDtIdsByUName查出的数组是否为空，不为空才执行此方法
            Integer[] pIds = adminDao.findPIdsByBdtIds(bdtIds);
            if (pIds.length>0) { //判断从findPIdsByBdtIds查出的数组是否为空，不为空才执行此方法
                adminDao.deletePingLunByPIds(pIds);
            }
        }
        adminDao.deleteDongTaiByUName(uName);
        adminDao.deleteQingJiaByUsName(uName);

        redisUtil.del("jiaxiaohulian:user:userList"); //删除用户列表的Redis数据
        for (int i = 1; i <= 3; i++) { //删除留言那里的下拉框的Redis数据
            redisUtil.del("jiaxiaohulian:userSelect:qianUserSelect"+i);
        }
        //删除留言有关的Redis
        redisUtil.del("jiaxiaohulian:liuyan:liuYanList");
        List<LiuYan> list = adminDao.findLiuYan(null);
        int count = list.size()%3==0?list.size():(list.size()+1);
        for (int i = 1; i <= count; i++) {
            redisUtil.del("jiaxiaohulian:liuyan:qianLiuYan"+i);
            redisUtil.del("jiaxiaohulian:liuyan:qianLiuYanByUNameTo"+i);
        }
        //删除与被删除用户有关的评论信息
        if (bdtIds.length>0){
            for (int i=0;i<bdtIds.length;i++){
                redisUtil.del("jiaxiaohulian:pinglun:qianPinglun"+bdtIds[i]);
            }
        }
        //删除动态的Redis
        List<ShiShengDongTai> list1 = adminDao.findDongTai(null);
        int pageNum = list1.size()%3==0?(list1.size()/3):(list1.size()/3+1);  //后台更新后，用户页面的Redis数据删除
        for (int i=1;i<=pageNum;i++){
            redisUtil.del("jiaxiaohulian:shishengdongtai:userAllDongTai"+i);
            redisUtil.del("jiaxiaohulian:shishengdongtai:userMyDongTai"+i);
        }
        redisUtil.del("jiaxiaohulian:shishengdongtai:dongTaiList");
        //删除与请假有关的Redis
        redisUtil.del("jiaxiaohulian:qingjia:qianFindQingJiaByUsName");
        redisUtil.del("jiaxiaohulian:qingjia:qianFindQingJiaByUtName");
        redisUtil.del("jiaxiaohulian:qingjia:qianFindQingJiaByUjName");
        redisUtil.del("jiaxiaohulian:qingjia:findQingJiaOverSevenDays");
    }

    /***
    *@Description 查询状态审核中的用户（即状态为0） 包含全部查询和按条件查询
    *@Param [user]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:30
    */
    @Override
    public Object findToShenHe(User user) {
        if (user==null||(user.getuName()==""&&user.getuYuanXi()==""&&user.getuQuanXian()==null)){
            Object obj = redisUtil.get("jiaxiaohulian:user:userToShenHeList");
            if (obj==null){
                redisUtil.set("jiaxiaohulian:user:userToShenHeList",adminDao.findToShenHe(null),1800L);
                obj = redisUtil.get("jiaxiaohulian:user:userToShenHeList");
            }
            return obj;
        }
        return adminDao.findToShenHe(user);
    }

    /***
    *@Description 根距用户名删除对应的待审核（状态为0）或审核不通过（状态为2）的用户
    *@Param [uNames]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:30
    */
    @Override
    public void deleteToShenHeUserByUName(String[] uNames) {
        if (uNames.length>0){
            adminDao.deleteToShenHeUserByUName(uNames);
            redisUtil.del("jiaxiaohulian:user:userToShenHeList");
        }
    }

    /***
    *@Description 查询请假时间大于或等于七天的请假申请列表
    *@Param []
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:32
    */
    @Override
    public Object findQingJiaOverSevenDays() {
        Object obj = redisUtil.get("jiaxiaohulian:qingjia:findQingJiaOverSevenDays");
        if (obj==null){
            redisUtil.set("jiaxiaohulian:qingjia:findQingJiaOverSevenDays",adminDao.findQingJiaOverSevenDays(),1800L);
            obj = redisUtil.get("jiaxiaohulian:qingjia:findQingJiaOverSevenDays");
        }
        return obj;
    }

    /***
    *@Description 修改管理员是否同意请假的状态
    *@Param [qId, qUaFlag]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:33
    */
    @Override
    public void qUaFlagChange(String qId,String qUaFlag) {
        Map<String,String> map = new HashMap<>();
        map.put("qId",qId);
        map.put("qUaFlag",qUaFlag);
        adminDao.qUaFlagChange(map);

        redisUtil.del("jiaxiaohulian:qingjia:qianFindQingJiaByUsName");
        redisUtil.del("jiaxiaohulian:qingjia:qianFindQingJiaByUtName");
        redisUtil.del("jiaxiaohulian:qingjia:qianFindQingJiaByUjName");
        redisUtil.del("jiaxiaohulian:qingjia:findQingJiaOverSevenDays");
    }

    /***
    *@Description 超级管理员添加管理员
    *@Param [user]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:33
    */
    @Override
    public void addAdmin(User user) {
        user.setuAddTime(new Date());
        user.setuShenHe(111);

        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将随机盐保存到数据库
        user.setuSalt(salt);
        //3.明文的密码进行MD5+salt+hash散列次数    1024次散列  登录时也要用1024次散列才能匹配上
        Md5Hash md5Hash = new Md5Hash(user.getuPwd(),salt,1024);
        user.setuPwd(md5Hash.toHex());
        adminDao.addAdmin(user);
        redisUtil.del("jiaxiaohulian:admin:adminList");
    }

    /***
    *@Description 通过管理员账号查询对应的管理员
    *@Param [uName]
    *@Return com.yxj.jiaxiaohulian.entity.User
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:34
    */
    @Override
    public User findAdminByName(String uName) {
        return adminDao.findAdminByName(uName);
    }

    /***
    *@Description 超级管理员查询管理员用户列表（包括全部查询和按条件查询）
    *@Param [user]
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:34
    */
    @Override
    public Object findAdmin(User user) {
        if (user==null||(user.getuName()==""&&user.getuSex()==""&&user.getuRole()=="")){
            Object obj = redisUtil.get("jiaxiaohulian:admin:adminList");
            if (obj==null){
                redisUtil.set("jiaxiaohulian:admin:adminList",adminDao.findAdmin(user),1800L);
                obj = redisUtil.get("jiaxiaohulian:admin:adminList");
            }
            return obj;
        }
        return adminDao.findAdmin(user);
    }

    /***
    *@Description 管理员自己修改自己的密码（修改密码暂时没用此方法，用的是下边的updateAdminPwd(User user)方法）
    *@Param [user]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:35
    */
    @Override
    public void updateAdmin(User user) {
        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将随机盐保存到数据库
        user.setuSalt(salt);
        //3.明文的密码进行MD5+salt+hash散列次数    1024次散列  登录时也要用1024次散列才能匹配上
        Md5Hash md5Hash = new Md5Hash(user.getuPwd(),salt,1024);
        user.setuPwd(md5Hash.toHex());
        adminDao.updateAdmin(user);
        redisUtil.del("jiaxiaohulian:admin:adminList");
    }

    /***
    *@Description 超级管理员修改其他管理员的信息
    *@Param [user]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:36
    */
    @Override
    public void updateBySuperAdmin(User user) {
        adminDao.updateBySuperAdmin(user);
        redisUtil.del("jiaxiaohulian:admin:adminList");
    }

    /***
    *@Description 超级管理员通过管理员用户id删除管理员
    *@Param [uStuNo]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:36
    */
    @Override
    public void deleteAdminByAId(Integer uStuNo) {
        adminDao.deleteAdminByAId(uStuNo);
        redisUtil.del("jiaxiaohulian:admin:adminList");
    }

    /***
    *@Description 查询不包含自己的其他管理员
    *@Param [user]
    *@Return com.yxj.jiaxiaohulian.entity.User
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:37
    */
    @Override
    public User findAdminNotIncludeMe(User user) {
        return adminDao.findAdminNotIncludeMe(user);
    }

    /***
    *@Description 清除所有Redis数据
    *@Param []
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:37
    */
    @Override
    public void cleanAllRedis() { //清除Redis中的所有数据
        Map<String,Object> map= redisUtil.cleanRedis();
        System.out.println(map.get("code")+" "+map.get("msg"));
    }

    /***
    *@Description 修改用户之前验证原密码
    *@Param [user]
    *@Return com.yxj.jiaxiaohulian.entity.User
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:38
    */
    @Override
    public User checkPwd(User user) {
        //2.将随机盐保存到数据库
        String salt = findAdminByName(user.getuName()).getuSalt();
        //3.明文的密码进行MD5+salt+hash散列次数    1024次散列  登录时也要用1024次散列才能匹配上
        Md5Hash md5Hash = new Md5Hash(user.getuPwd(),salt,1024);
        user.setuPwd(md5Hash.toHex());
        User user1 = adminDao.adminLogin(user);
        if (user1!=null&&md5Hash.toHex().equals(user1.getuPwd())){
            return user1;
        }
        return null;
    }

    /***
    *@Description 修改管理员用户自己修改自己的密码
    *@Param [user]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:38
    */
    @Override
    public void updateAdminPwd(User user) {
        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将随机盐保存到数据库
        user.setuSalt(salt);
        //3.明文的密码进行MD5+salt+hash散列次数    1024次散列  登录时也要用1024次散列才能匹配上
        Md5Hash md5Hash = new Md5Hash(user.getuPwd(),salt,1024);
        user.setuPwd(md5Hash.toHex());
        adminDao.updateAdminPwd(user);

    }


}
