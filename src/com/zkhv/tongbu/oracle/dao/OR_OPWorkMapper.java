package com.zkhv.tongbu.oracle.dao;

import java.util.List;

import com.zkhv.mkjview.entity.T_OPWork;
import com.zkhv.utils.PageUtil;


public interface OR_OPWorkMapper  {
   
	//多条件查询，返回列表集合[动态sql]
	public List selectPageListUseDyc(PageUtil<T_OPWork> page);
	
	//多条件查询，返回总记录数[动态sql]
	public Integer selectPageCountUseDyc(PageUtil<T_OPWork> page);

	public T_OPWork select(T_OPWork t_OPWork);
	
	
	
}