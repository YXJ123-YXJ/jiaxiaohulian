package com.yxj.jiaxiaohulian.service;

import com.yxj.jiaxiaohulian.entity.QingJia;

import java.util.List;

public interface QingJiaService {

    public void addQingJia(QingJia qingJia);
    public Object findQingJiaByUsName(String usName);
    public Object findQingJiaByUtjName(String utName,String ujName);
    public void qUtjFlagChange(String qId,String qUtFlag,String qUjFlag);
}
