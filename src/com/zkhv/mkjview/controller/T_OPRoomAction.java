package com.zkhv.mkjview.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkhv.mkjview.entity.T_OPRoom;
import com.zkhv.mkjview.service.T_OPRoomService;
import com.zkhv.utils.PageUtil;


@Controller
@RequestMapping("/t_OPRoom")
public class T_OPRoomAction {

	@Resource
	private T_OPRoomService t_OPRoomService;
	
	
	//跳转到患者
	@RequestMapping("/selectPageUseDyc")
	@ResponseBody
	public Object selectPageUseDyc(PageUtil<T_OPRoom> page,T_OPRoom t_OPRoom){
		page.setEntity(t_OPRoom);
		PageUtil<T_OPRoom> p = t_OPRoomService.selectPageByDyc(page);
		return p.getMap();
	}
	
	
	
	
}
