package com.zkhv.tongbu.oracle.service;

import com.zkhv.mkjview.entity.T_OPRoom;
import com.zkhv.utils.PageUtil;


public interface OR_OPRoomService/* extends BaseService<T_OPRoom>*/{
	
	//多条件查询，返回列表集合[动态sql]
	public PageUtil selectPageListUseDyc(PageUtil<T_OPRoom> page);

}
