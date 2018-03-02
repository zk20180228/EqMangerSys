package com.zkhv.log.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zkhv.common.BaseServiceImpl;
import com.zkhv.log.entity.LogInfo;
import com.zkhv.log.service.LogInfoService;

@Service("logInfoService")
public class LogInfoServiceImpl extends BaseServiceImpl<LogInfo> implements LogInfoService {
	
	
	
	public List<LogInfo> findAll() {
		
		return logInfoMapper.findAll();
	}

	
}
