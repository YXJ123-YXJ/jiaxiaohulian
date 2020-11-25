package com.yxj.jiaxiaohulian.service;

import com.yxj.jiaxiaohulian.entity.Systems;
import com.yxj.jiaxiaohulian.entity.User;
import com.yxj.jiaxiaohulian.entity.YouQingLianJie;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LoginService {

    public Integer userLogin(User user, HttpServletRequest request);

    public Object findWangZhan();

    public User checkUNameExsit(String uName);

    public Object findSystems();

    public List<String> findYuanXi(String yXueYuan,String yXi);

    public List<String> findAllUtName();

    public void userRegister(User user, MultipartFile file);

    public User checkOldPwd(User user);

    public void updateUPwd(User user);

    public void cleanAllRedis();

    public void logout();

}
