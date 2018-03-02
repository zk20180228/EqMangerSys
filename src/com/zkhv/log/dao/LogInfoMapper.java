package com.zkhv.log.dao;

import java.util.List;

import com.zkhv.common.BaseMapper;
import com.zkhv.log.entity.LogInfo;


public interface LogInfoMapper extends BaseMapper<LogInfo>{
		
	
	
	List<LogInfo> findAll();
	
	
    
}