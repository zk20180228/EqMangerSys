package com.zkhv.mkjview.dao;

import java.util.List;

import com.zkhv.common.BaseMapper;
import com.zkhv.mkjview.entity.T_OPRoom;


public interface T_OPRoomMapper extends BaseMapper<T_OPRoom> {

	List<T_OPRoom> findAll();
   
	
}