package com.zkhv.tongbu.oracle.dao;

import java.util.List;

import com.zkhv.mkjview.entity.T_OPPatient;
import com.zkhv.utils.PageUtil;


public interface OR_PatientMapper /*extends BaseMapper<T_OPPatient>*/ {
   
	
			//多条件查询，返回列表集合[动态sql]
			public List selectPageListUseDyc(PageUtil<T_OPPatient> page);
			
			//多条件查询，返回总记录数[动态sql]
			public Integer selectPageCountUseDyc(PageUtil<T_OPPatient> page);

			public T_OPPatient select(T_OPPatient t_OPPatient);
			
	
	
}