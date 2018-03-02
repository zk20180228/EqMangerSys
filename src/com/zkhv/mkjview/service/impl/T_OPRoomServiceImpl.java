package com.zkhv.mkjview.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zkhv.common.BaseServiceImpl;
import com.zkhv.mkjview.entity.T_OPRoom;
import com.zkhv.mkjview.service.T_OPRoomService;

@Service("t_OPRoomService")
public class T_OPRoomServiceImpl extends BaseServiceImpl<T_OPRoom> implements T_OPRoomService{

	
	public List<T_OPRoom> findAll() {
		
		return t_OPRoomMapper.findAll();
	}
	

}
