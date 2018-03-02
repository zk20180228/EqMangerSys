package com.zkhv.command.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zkhv.command.dao.SysCommandMapper;
import com.zkhv.command.entity.SysCommand;
import com.zkhv.command.service.SysCommandService;

@Service("sysCommandService")
public class SysCommandServiceImpl implements SysCommandService{
	
	@Resource
	private SysCommandMapper sysCommandMapper;

	
	//返回命令key,和value的map
	public Map<String,String> reLoadCommand(){
		//用于存放系统命令的map集合
		Map<String,String> hashMap = new HashMap<String,String>();
		List<SysCommand> sysList= sysCommandMapper.findAllSysCommmand();
		if(sysList!=null&&sysList.size()>0){
			for(SysCommand cmd:sysList){
				if(cmd.getCommandKey()!=null){
					hashMap.put(cmd.getCommandKey(), cmd.getCommanValue());
				}
			}
		}
		return hashMap;
	}
	
	@Deprecated
	public List<String> reloadUrl(){
		List<String> list = new ArrayList<String>();
		List<SysCommand> sysList= sysCommandMapper.findAllSysCommmand();
		if(sysList!=null&&sysList.size()>0){
			for(SysCommand cmd:sysList){
				if(cmd.getCommandKey()==null&&"url".equals(cmd.getCommandType())){
					list.add(cmd.getCommanValue());
				}
			}
		}
		return list;
		
		
		
	}
	
	
	
	

}
