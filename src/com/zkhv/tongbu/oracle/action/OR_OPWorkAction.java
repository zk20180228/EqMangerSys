package com.zkhv.tongbu.oracle.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkhv.mkjview.entity.T_OPWork;
import com.zkhv.tongbu.oracle.service.OR_OPWorkService;
import com.zkhv.utils.PageUtil;


@Controller
@RequestMapping("/or_OPWork")
public class OR_OPWorkAction {

	@Resource
	private OR_OPWorkService or_OPWorkService;
	
	
	//跳转到患者
	@RequestMapping("/selectPageUseDyc")
	@ResponseBody
	public Object selectPageUseDyc(PageUtil<T_OPWork> page,T_OPWork t_OPWork){
		Date strDate = t_OPWork.getOps_time();
		if(strDate!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");
			Long time = strDate.getTime()+23*60*60*1000+59*60*1000+59*1000+999;//23小时59分59秒999毫秒
			Date selectDate =new Date(time);
			t_OPWork.setSelectEndTime(selectDate);
		}
		page.setEntity(t_OPWork);
		PageUtil<T_OPWork> p = or_OPWorkService.selectPageListUseDyc(page);
		return p.getMap();
	}
	
	
	
	@RequestMapping("/select")
	public String  select( T_OPWork  t_OPWork,HttpServletRequest request){
		request.setAttribute("w", or_OPWorkService.select(t_OPWork));
		 return "forward:/WEB-INF/detailInfo_work.jsp";
	}
	
	
}
