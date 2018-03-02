package com.zkhv.log.service;

import java.util.List;

import com.zkhv.common.BaseService;
import com.zkhv.log.entity.LogInfo;

public interface LogInfoService extends BaseService<LogInfo> {
	
		
		List<LogInfo> findAll();

}
