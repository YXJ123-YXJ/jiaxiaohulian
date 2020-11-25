package com.yxj.jiaxiaohulian.controller;

import com.yxj.jiaxiaohulian.entity.User;
import com.yxj.jiaxiaohulian.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/{first}.html")
    public String toPage(@PathVariable("first") String first){
        return "/"+first;
    }

    @RequestMapping("/user/{first}/{second}.html")
    public String toPagePage(@PathVariable("first") String first,@PathVariable("second") String second){
        return "/user/"+first+"/"+second;
    }

    @RequestMapping("checkUNameExsit.do")
    @ResponseBody
    @RequiresAuthentication
    public String checkUNameExsit(String uName){
        if (loginService.checkUNameExsit(uName)!=null){
            return "success";
        }
        return "fail";
    }

    @RequestMapping("userRegister.do")
    public String userRregister(User user, MultipartFile file){
        loginService.userRegister(user,file);
        return "redirect:/userLogin.html";
    }

    @RequestMapping("/login.do")
    @ResponseBody
    public Integer userLogin(User user, HttpServletRequest request){
        return loginService.userLogin(user,request);
    }

    @RequestMapping("findWangZhan.do")
    @ResponseBody
    @RequiresAuthentication
    public Object findWangZhan(){
        return loginService.findWangZhan();
    }

    @RequestMapping("findSystems.do")
    @ResponseBody
    @RequiresAuthentication
    public Object findSystems(){
        return loginService.findSystems();
    }

    @RequestMapping("findYuanXi.do")
    @ResponseBody
    public List<String> findYuanXi(String yXueYuan, String yXi){
        return loginService.findYuanXi(yXueYuan,yXi);
    }

    //查询全部状态为1(正常)的辅导员，用于注册时下拉列表的显示
    @RequestMapping("findAllUtName.do")
    @ResponseBody
    public List<String> findAllUtName(){
        return loginService.findAllUtName();
    }




//用于测试异常500
    @RequestMapping("/info1")
    public String test(){
        log.info("/user/info1");

        throw new NullPointerException("TestController have exception");
    }


    @RequestMapping("/")
    public String view(HttpServletRequest request){
        return "index";
    }

    @RequestMapping("findUserByUName.do")
    @ResponseBody
    @RequiresAuthentication
    public String findUserByUName(User user){ //用于密码修改时比对原密码
        if (loginService.checkOldPwd(user)!=null){
            return "success";
        }
        return "fail";
    }

    @RequestMapping("updateUPwd.do")
    @ResponseBody
    @RequiresAuthentication
    public void updateUPwd(User user){
        loginService.updateUPwd(user);
    }

    @RequestMapping("toUserLogin.do") //用于用户修改密码后调到index页
    @RequiresAuthentication
    public String toUserLogin(HttpServletRequest request){
//        request.getSession().invalidate(); //销毁session
        loginService.cleanAllRedis(); //清除Redis中的所有数据
        return "redirect:/index.html";
    }

    @RequestMapping("killSession.do")
    @ResponseBody
    @RequiresAuthentication
    public void killSession(HttpServletRequest request){

        // 清除session   不删除session，只删除session里的值
        Enumeration<String> enumeration = request.getSession().getAttributeNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toString();
            System.out.println("session的key"+key);
            request.getSession().removeAttribute(key);
        }

//        request.getSession().invalidate();
//        loginService.cleanAllRedis(); //清除Redis中的所有数据
    }

    @RequestMapping("logout.do")
//    @ResponseBody
    @RequiresAuthentication
    public String logout(){
        loginService.logout();
        return "redirect:/userLogin.html";
    }

}
