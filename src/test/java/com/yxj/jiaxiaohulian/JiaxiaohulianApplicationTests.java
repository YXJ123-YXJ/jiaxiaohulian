package com.yxj.jiaxiaohulian;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxj.jiaxiaohulian.dao.AdminDao;
import com.yxj.jiaxiaohulian.dao.LoginDao;
import com.yxj.jiaxiaohulian.dao.NewsDao;
import com.yxj.jiaxiaohulian.entity.User;
import com.yxj.jiaxiaohulian.entity.XueXiaoXinWen;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class JiaxiaohulianApplicationTests {

    @Autowired
    private LoginDao logindao;
    @Autowired
    private NewsDao newsDao;
    @Autowired
    private AdminDao adminDao;

    @Test
    void contextLoads() {
        User user = new User();
        user.setuName("aaa");
        user.setuPwd("aaa");
        user.setuQuanXian(1);
        System.out.println(logindao.userLogin(user));
    }

    @Test
    public void testPage(){
        PageHelper.startPage(1,3);
        List<XueXiaoXinWen> list = newsDao.findAllNews();
        for (XueXiaoXinWen x:list){
            System.out.println(x);
        }
        System.out.println("--------------------------------");
        PageInfo<XueXiaoXinWen> pageInfo = new PageInfo<XueXiaoXinWen>(list);
        for (XueXiaoXinWen x1:pageInfo.getList()){
            System.out.println(x1);
        }
        System.out.println("页数："+pageInfo.getPages());
        System.out.println("当前页面数据量："+pageInfo.getSize());
        System.out.println("总数："+pageInfo.getTotal());
    }

    @Test
    public void testArray(){
        Integer[] a = {1,2,3};
//        System.out.println(Arrays.toString(adminDao.findDtIdByUName("王老师")));
//        System.out.println(Arrays.toString(adminDao.findPIdsByBdtIds(a)));
//        adminDao.deletePingLunByPIds(a);
    }

    @Test
    public void TestException(){

    }


}
