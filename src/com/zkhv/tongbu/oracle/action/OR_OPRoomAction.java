package com.zkhv.tongbu.oracle.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkhv.mkjview.entity.T_OPRoom;
import com.zkhv.tongbu.oracle.service.OR_OPRoomService;
import com.zkhv.utils.PageUtil;


@Controller
@RequestMapping("/or_OPRoom")
public class OR_OPRoomAction {

	@Resource
	private OR_OPRoomService or_OPRoomService;
	
	
	//跳转到患者
	@RequestMapping("/selectPageUseDyc")
	@ResponseBody
	public Object selectPageUseDyc(PageUtil<T_OPRoom> page,T_OPRoom t_OPRoom){
		page.setEntity(t_OPRoom);
		PageUtil<T_OPRoom> p = or_OPRoomService.selectPageListUseDyc(page);
		return p.getMap();
	}
	
	
	
	
}
