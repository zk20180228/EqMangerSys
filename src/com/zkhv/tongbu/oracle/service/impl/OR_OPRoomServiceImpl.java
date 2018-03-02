package com.zkhv.tongbu.oracle.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zkhv.mkjview.entity.T_OPRoom;
import com.zkhv.tongbu.oracle.dao.OR_OPRoomMapper;
import com.zkhv.tongbu.oracle.service.OR_OPRoomService;
import com.zkhv.utils.PageUtil;


@Service("or_OPRoomService")
public class OR_OPRoomServiceImpl /*extends BaseServiceImpl<T_OPRoom>*/ implements OR_OPRoomService{

	@Resource	
	private OR_OPRoomMapper opRoomMapper;
	
	
	public PageUtil selectPageListUseDyc(PageUtil<T_OPRoom> page) {
		
		//设置列表集合
		page.setList(opRoomMapper.selectPageListUseDyc(page));
		//设置中记录数
		page.setTatalRecord(opRoomMapper.selectPageCountUseDyc(page));
		return page;
	}
	

}
