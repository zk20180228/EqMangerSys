package com.zkhv.tongbu.oracle.dao;

import java.util.List;

import com.zkhv.mkjview.entity.T_OPRoom;
import com.zkhv.utils.PageUtil;


public interface OR_OPRoomMapper /*extends BaseMapper<T_OPRoom>*/ {
   
	//多条件查询，返回列表集合[动态sql]
	public List selectPageListUseDyc(PageUtil<T_OPRoom> page);
	
	//多条件查询，返回总记录数[动态sql]
	public Integer selectPageCountUseDyc(PageUtil<T_OPRoom> page);
	
	
}