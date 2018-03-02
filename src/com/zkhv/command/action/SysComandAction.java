package com.zkhv.command.action;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkhv.command.service.SysCommandService;
import com.zkhv.common.BaseAction;
import com.zkhv.constant.Constant;

@Controller
@RequestMapping("/sysComand")
public class SysComandAction extends BaseAction{
	
	@Resource
	private SysCommandService sysCommandService;
	
	
	
	//重新加载系统命令
	@ResponseBody
	@RequestMapping("/reLoadCommand")
	public String reLoadCommand(){
		loadCmd();
		return "true";
	}
	
	//装载系统命令
	public void loadCmd(){
		
		application.setAttribute(Constant.SYSCMDMAP, sysCommandService.reLoadCommand());//加载系统命令
	}
	
	//系统启动的时候加载
	@PostConstruct
	public void initLoadCmd(){
		
		loadCmd();
	}
	
	

}
