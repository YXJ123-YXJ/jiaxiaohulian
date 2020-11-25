package com.yxj.jiaxiaohulian.dao;

import com.yxj.jiaxiaohulian.entity.QingJia;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface QingJiaDao {

    public void addQingJia(QingJia qingJia); //学生请假申请
    public List<QingJia> findQingJiaByUsName(String usName); //根据学生姓名查询请假记录
    public List<QingJia> findQingJiaByUtName(Map<String,String> map); //根据辅导员或家长查询学生请假情况
    public void qUtFlagChange(Map<String,String> map);  //辅导员或家长是否同意请假（改变请假表的q_ut_flag或q_uj_flag的值）,天数大于等于七天需家长同意

}
