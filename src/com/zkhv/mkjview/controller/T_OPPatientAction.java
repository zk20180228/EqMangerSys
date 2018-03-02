package com.zkhv.mkjview.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkhv.mkjview.entity.T_OPPatient;
import com.zkhv.mkjview.service.T_OPPatientService;
import com.zkhv.utils.PageUtil;


@Controller
@RequestMapping("/t_Patient")
public class T_OPPatientAction {

	@Resource
	private T_OPPatientService t_OPPatientService;
	
	
	//跳转到患者
	@RequestMapping("/selectPageUseDyc")
	@ResponseBody
	public Object selectPageUseDyc(PageUtil<T_OPPatient> page,T_OPPatient patient){
		Date strDate = patient.getOps_time();
		if(strDate!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");
			Long time = strDate.getTime()+23*60*60*1000+59*60*1000+59*1000+999;//23小时59分59秒999毫秒
			Date selectDate =new Date(time);
			patient.setSelectEndTime(selectDate);
		}
		page.setEntity(patient);
		PageUtil<T_OPPatient> p = t_OPPatientService.selectPageByDyc(page);
		return p.getMap();
	}
	
	
	@RequestMapping("/select")
	public String  select(T_OPPatient t_OPPatient,HttpServletRequest request){
		
		request.setAttribute("p", t_OPPatientService.select(t_OPPatient));
		 return "forward:/WEB-INF/detailInfo_patient.jsp";
	}
	
	
}
