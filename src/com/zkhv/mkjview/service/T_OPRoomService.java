package com.zkhv.mkjview.service;

import java.util.List;

import com.zkhv.common.BaseService;
import com.zkhv.mkjview.entity.T_OPRoom;

public interface T_OPRoomService extends BaseService<T_OPRoom>{
	
	public List<T_OPRoom> findAll();
	

}
