package com.zkhv.common;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/base")
public class BaseAction {
	
		//容器会自动创建并注入
		@Resource
		protected ServletContext application;	
		
		
		

}
