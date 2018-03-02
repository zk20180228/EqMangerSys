package com.zkhv.tongbu.oracle.service;


import com.zkhv.mkjview.entity.T_OPPatient;
import com.zkhv.utils.PageUtil;

public interface OR_PatientService /*extends BaseService<T_OPPatient>*/{
	
	//多条件查询，返回列表集合[动态sql]
	public PageUtil selectPageListUseDyc(PageUtil<T_OPPatient> page);

	public T_OPPatient select(T_OPPatient t_OPPatient);
	
	
}
