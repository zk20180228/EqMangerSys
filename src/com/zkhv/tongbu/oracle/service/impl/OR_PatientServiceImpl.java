package com.zkhv.tongbu.oracle.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zkhv.mkjview.entity.T_OPPatient;
import com.zkhv.tongbu.oracle.dao.OR_PatientMapper;
import com.zkhv.tongbu.oracle.service.OR_PatientService;
import com.zkhv.utils.PageUtil;

@Service("or_PatientService")
public class OR_PatientServiceImpl /*extends BaseServiceImpl<T_OPPatient> */implements OR_PatientService{
	
	@Resource
	private OR_PatientMapper oR_PatientMapper; 
	
	
	@SuppressWarnings("unchecked")
	public PageUtil<T_OPPatient> selectPageListUseDyc(PageUtil<T_OPPatient> page) {

		//设置列表集合
		page.setList(oR_PatientMapper.selectPageListUseDyc(page));
		//设置中记录数
		page.setTatalRecord(oR_PatientMapper.selectPageCountUseDyc(page));
		return page;
	}


	
	public T_OPPatient select(T_OPPatient t_OPPatient) {
		
		return oR_PatientMapper.select(t_OPPatient);
	}


	


	



	
	
	

}
