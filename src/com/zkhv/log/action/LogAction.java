package com.zkhv.log.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkhv.log.entity.LogInfo;
import com.zkhv.log.service.LogInfoService;
import com.zkhv.utils.PageUtil;

@Controller
@RequestMapping("/logAction")
public class LogAction {
	
	@Resource
	private LogInfoService logInfoService;
	
	@ResponseBody
	@RequestMapping("/findAll")
	public Object findAll(LogInfo logInfo){
		
		HashedMap map = new HashedMap();
		List<LogInfo> list= logInfoService.findAll();
		map.put("total", list.size());
		map.put("rows", list);
		
		return map;
	}
	
	
	//分页查日志信息
	@RequestMapping("/selectPageUseDyc")
	@ResponseBody
	public Object selectPageUseDyc(PageUtil<LogInfo> page,LogInfo logInfo) throws Exception{
		page.setEntity(logInfo);
		PageUtil<LogInfo> p = logInfoService.selectPageByDyc(page);
		return p.getMap();
	}
	
	
	
	
}
