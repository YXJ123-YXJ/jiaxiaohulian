package com.yxj.jiaxiaohulian.service;

import com.yxj.jiaxiaohulian.entity.LiuYan;
import com.yxj.jiaxiaohulian.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface LiuYanService {

    public Object findUserByUQuanXian(Integer uQuanXian,String uName);
    public void addLiuYan(LiuYan liuYan, MultipartFile file);
    public Object findLiuYanByUNameFrom(Integer pageNum,String uNameFrom); //查询我的留言
    public Object findLiuYanByUNameTo(Integer pageNum,String uNameTo); //查询别人给我留的言
    public void huiFuLiuYan(Integer lId,String lHuiFu);
}
