package com.zkhv.tongbu.oracle.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zkhv.mkjview.entity.T_OPWork;
import com.zkhv.tongbu.oracle.dao.OR_OPWorkMapper;
import com.zkhv.tongbu.oracle.service.OR_OPWorkService;
import com.zkhv.utils.PageUtil;

@Service("or_OPWorkService")
public class OR_OPWorkServiceImpl/* extends BaseServiceImpl<T_OPWork>*/ implements OR_OPWorkService{

	@Resource
	private OR_OPWorkMapper opWorkMapper;
	
	

	public PageUtil<T_OPWork> selectPageListUseDyc(PageUtil<T_OPWork> page) {
		
		//设置列表集合
		page.setList(opWorkMapper.selectPageListUseDyc(page));
		//设置中记录数
		page.setTatalRecord(opWorkMapper.selectPageCountUseDyc(page));
		return page;
	}
	
	public T_OPWork select(T_OPWork t_OPWork) {
		
		return opWorkMapper.select(t_OPWork);
	}
	

}
