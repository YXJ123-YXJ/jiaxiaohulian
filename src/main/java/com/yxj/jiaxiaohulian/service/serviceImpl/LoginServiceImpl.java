package com.yxj.jiaxiaohulian.service.serviceImpl;

import com.yxj.jiaxiaohulian.dao.LoginDao;
import com.yxj.jiaxiaohulian.entity.Systems;
import com.yxj.jiaxiaohulian.entity.User;
import com.yxj.jiaxiaohulian.entity.YouQingLianJie;
import com.yxj.jiaxiaohulian.realms.UserRealm;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserRealm userRealm;
    
    /***
    *@Description 用户或管理员登录
    *@Param [user, request]
    *@Return java.lang.Integer
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:00
    */
    @Override
    public Integer userLogin(User user, HttpServletRequest request) {

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户登录数据
        UsernamePasswordToken token = new CustomLoginToken(user.getuName(),user.getuPwd(), UserType.USER);
        try {
            subject.login(token);
//            subject.hasRole("登录");
            User user1 = loginDao.findRolesByUserName(user.getuName());
            Integer uShenHe = user1.getuShenHe();
            if (uShenHe == 1) {
                request.getSession().setAttribute("LOGIN_USER", user1);
            }
            if (uShenHe==111){ //111表明是管理员
                request.getSession().setAttribute("LOGIN_ADMIN", user1);
            }
            return uShenHe;
        } catch (UnknownAccountException e){ //用户名不存在
            return 5;
        } catch (IncorrectCredentialsException e){ //密码错误
            return 5;
        } catch (AuthenticationException e){ //shiro的大异常
            return 5;
        }
    }
    
    /***
    *@Description 查询友情链接
    *@Param []
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:01
    */
    @Override
    public Object findWangZhan() {
        Object obj = redisUtil.get("jiaxiaohulian:youqinglianjie:qianWangZhan");
        if (obj==null){
            redisUtil.set("jiaxiaohulian:youqinglianjie:qianWangZhan",loginDao.findWangZhan(),1800L);
            obj = redisUtil.get("jiaxiaohulian:youqinglianjie:qianWangZhan");
        }
        return obj;
    }
    
    /***
    *@Description 用户注册时查询账号是否已存在
     *          注意：在用户注册时记得要删除后台有关用户审核的Redis数据，
     *         前台的添加留言的下拉列表的Redis数据不用删，
     *         因为下拉列表只查询了状态为1(正常)的用户
    *@Param [uName]
    *@Return com.yxj.jiaxiaohulian.entity.User
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:01
    */
    @Override
    public User checkUNameExsit(String uName) {
        User user = loginDao.checkUNameExsit(uName);
        return user;
    }
    
    /***
    *@Description 查询系统信息
    *@Param []
    *@Return java.lang.Object
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:03
    */
    @Override
    public Object findSystems() {
        Object obj = redisUtil.get("jiaxiaohulian:system:systems"); //这里就没有封装前台Redis数据了，直接和后台共用一个Redis数据
        if (obj==null){
            redisUtil.set("jiaxiaohulian:system:systems",loginDao.findSystems(),1800);
            obj = redisUtil.get("jiaxiaohulian:system:systems");
        }
        return obj;
    }
    
    /***
    *@Description 用户注册时的院系下拉列表的数据查询
    *@Param [yXueYuan, yXi]
    *@Return java.util.List<java.lang.String>
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:04
    */
    @Override
    public List<String> findYuanXi(String yXueYuan, String yXi) {
        if (yXueYuan!=null&&yXueYuan!=""&&(yXi==null||yXi=="")){
            return loginDao.findYXi(yXueYuan);
        }
        if (yXueYuan!=null&&yXueYuan!=""&&yXi!=null&&yXi!=""){
            Map<String,Object> map = new HashMap<>();
            map.put("yXueYuan",yXueYuan);
            map.put("yXi",yXi);
            return loginDao.findYClazz(map);
        }
        return loginDao.findYXueYuan();
    }
    
    /***
    *@Description 当用户为学生时，辅导员下拉列表查询全部状态为1的辅导员
    *@Param []
    *@Return java.util.List<java.lang.String>
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:05
    */
    @Override
    public List<String> findAllUtName() {
        return loginDao.findAllUtName();
    }
    
    /***
    *@Description 用户注册
    *@Param [user, file]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:06
    */
    @Override
    public void userRegister(User user, MultipartFile file) {
        user.setuTouXiang(FileUpload.upload(file));
        if (user.getuQuanXian()==1){
            user.setuRole("teacher");
        }
        if (user.getuQuanXian()==2){
            user.setuRole("jiazhang");
        }
        if (user.getuQuanXian()==3){
            user.setuRole("student");
        }
        user.setuAddTime(new Date());
        user.setuShenHe(0);

        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将随机盐保存到数据库
        user.setuSalt(salt);
        //3.明文的密码进行MD5+salt+hash散列次数    1024次散列  登录时也要用1024次散列才能匹配上
        Md5Hash md5Hash = new Md5Hash(user.getuPwd(),salt,1024);
        user.setuPwd(md5Hash.toHex());

        loginDao.userRegister(user);
        redisUtil.del("jiaxiaohulian:user:userToShenHeList");
    }
    
    /***
    *@Description 用户修改密码时校验原密码
    *@Param [user]
    *@Return com.yxj.jiaxiaohulian.entity.User
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:07
    */
    @Override
    public User checkOldPwd(User user) {

        //2.将随机盐保存到数据库
        String salt = loginDao.findRolesByUserName(user.getuName()).getuSalt();
        //3.明文的密码进行MD5+salt+hash散列次数    1024次散列  登录时也要用1024次散列才能匹配上
        Md5Hash md5Hash = new Md5Hash(user.getuPwd(),salt,1024);
        user.setuPwd(md5Hash.toHex());
        List<User> list = loginDao.userLogin(user);
        if (list==null||list.size()==0){
            return null;
        }
        User user1 = list.get(0);
        if (user1!=null&&md5Hash.toHex().equals(user1.getuPwd())){
            return user1;
        }
        return null;
    }
    
    /***
    *@Description 用户修改密码
    *@Param [user]
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:07
    */
    @Override
    public void updateUPwd(User user) {
        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将随机盐保存到数据库
        user.setuSalt(salt);
        //3.明文的密码进行MD5+salt+hash散列次数    1024次散列  登录时也要用1024次散列才能匹配上
        Md5Hash md5Hash = new Md5Hash(user.getuPwd(),salt,1024);
        user.setuPwd(md5Hash.toHex());
        loginDao.updateUPwd(user);
    }

    /***
    *@Description 清除所有Redis数据
    *@Param []
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:07
    */
    @Override
    public void cleanAllRedis() { //清除Redis中的所有数据
        Map<String,Object> map= redisUtil.cleanRedis();
        System.out.println(map.get("code")+" "+map.get("msg"));
    }
    
    /***
    *@Description 退出登录同时清除所有缓存数据
    *@Param []
    *@Return void
    *@Author Mr.Yang
    *@Date 2020/11/17
    *@Time 12:08
    */
    @Override
    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        cleanAllRedis();
        subject.logout(); //退出用户
    }

}
