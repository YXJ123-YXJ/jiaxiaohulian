package com.yxj.jiaxiaohulian.service;

import com.yxj.jiaxiaohulian.entity.PingLun;
import com.yxj.jiaxiaohulian.entity.ShiShengDongTai;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DongTaiService {

    public Object findAllDongDai(int pageNum,String uName);
    public Object findMyDongTai(int pageNum,String uName);
    public void addDongTai(ShiShengDongTai dongTai, MultipartFile file);
    public Object findPingLunByBdtId(Integer bdtId);
    public void addPingLun(PingLun pingLun);
    public void deleteDongTaiById(Integer dtId);
    public Integer addDianJiLiang(String uName, Integer dtId);
}
