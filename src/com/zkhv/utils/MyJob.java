package com.zkhv.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zkhv.mkjview.entity.T_OPPatient;
import com.zkhv.mkjview.entity.T_OPRoom;
import com.zkhv.mkjview.entity.T_OPWork;
import com.zkhv.tongbu.oracle.entity.V_OPERATING_INFO;
import com.zkhv.tongbu.oracle.entity.V_OPER_SCHEDULED_INFO;
import com.zkhv.tongbu.oracle.entity.V_PAT_OPERATING;
import com.zkhv.tongbu.oracle.service.OraclePatientService;
import com.zkhv.tongbu.sqlServer.entity.T_OPImportSet;
import com.zkhv.tongbu.sqlServer.service.SqlServerPatientService;

/**
 * 定时同步数据
 * @author houzq
 *
 * 2017年3月22日
 */
@Controller
public class MyJob  {
	
	
	@Resource
	private OraclePatientService oraclePatientService;
	@Resource
	private SqlServerPatientService sqlServerPatientService;
	@RequestMapping("/utils/run.action")//手动同步数据(包括同步所有和分别同步)
	public void run( String[] nameList,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, IOException {
		if(nameList==null || (nameList!=null&&nameList.length==0)) {
			nameList = new String[3];
			nameList[0]="患者信息表";
			nameList[1]="手术排班表";
			nameList[2]="手术室表";
		}
		String msg = "";
		try {
			List<String> names = Arrays.asList(nameList);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (String name : names) {
				T_OPImportSet importSet = findImportSetByName(name);
				if("患者信息表".equals(name)){
					//符合条件的患者信息集合
					List<V_PAT_OPERATING> allPatientByQuery = findAllPatientByQuery(importSet);
					List<T_OPPatient> patients = new ArrayList<T_OPPatient>();
					for (V_PAT_OPERATING t_PAT_OPERATING : allPatientByQuery){
						T_OPPatient patient = new T_OPPatient();
						BeanUtils.copyProperties(patient, t_PAT_OPERATING);
						patient.setOps_status(t_PAT_OPERATING.getOps_status());
						patients.add(patient);
					}
					importSet.setImp_starttime(sdf.format(new Date()));
					addPatients(patients);
					importSet.setImp_endtime(sdf.format(new Date()));
					importSet.setImp_count(allPatientByQuery.size());
					sqlServerPatientService.updateImportSet(importSet);
					LogUtil.getLog().info("本次手动同步数据信息:数据库名称为:"+importSet.getImp_name()+"同步数量为:"+importSet.getImp_count()+"条,"+"同步时间:"+importSet.getImp_endtime()+"<><><><><><><><><><>");
				}
				if("手术排班表".equals(name)){
					//符合条件的手术排班集合
					List<V_OPER_SCHEDULED_INFO> allWorkByQuery = findAllWorkByQuery( importSet);
					List<T_OPWork> opWorks = new ArrayList<T_OPWork>();
					for (V_OPER_SCHEDULED_INFO t_OPER_SCHEDULED_INFO : allWorkByQuery){
						T_OPWork opWork = new T_OPWork();
						BeanUtils.copyProperties(opWork, t_OPER_SCHEDULED_INFO);
						opWork.setOps_state(t_OPER_SCHEDULED_INFO.getOps_state());
						opWorks.add(opWork);
					}
					importSet.setImp_starttime(sdf.format(new Date()));
					addOpWorks(opWorks);
					importSet.setImp_endtime(sdf.format(new Date()));
					importSet.setImp_count(allWorkByQuery.size());
					sqlServerPatientService.updateImportSet(importSet);
					LogUtil.getLog().info("本次手动同步数据信息:数据库名称为:"+importSet.getImp_name()+"同步数量为:"+importSet.getImp_count()+"条,"+"同步时间:"+importSet.getImp_endtime()+"<><><><><><><><><><>");
				}
				if("手术室表".equals(name)){
					//符合条件的手术室集合
					List<V_OPERATING_INFO> allOperatingByQuery = findAllOperatingByQuery(importSet);
					List<T_OPRoom>opRooms = new ArrayList<T_OPRoom>();
					for (V_OPERATING_INFO t_OPERATING_INFO : allOperatingByQuery){
						T_OPRoom opRoom = new T_OPRoom();
						opRoom.setId(String.valueOf(t_OPERATING_INFO.getId()));
						opRoom.setArea_name(t_OPERATING_INFO.getArea_name());
						opRoom.setRoom_id(t_OPERATING_INFO.getRoom_id());
						opRoom.setRoom_name(t_OPERATING_INFO.getRoom_name());
						opRooms.add(opRoom);
					}
					importSet.setImp_starttime(sdf.format(new Date()));
					addopRooms(opRooms);
					importSet.setImp_endtime(sdf.format(new Date()));
					importSet.setImp_count(allOperatingByQuery.size());
					sqlServerPatientService.updateImportSet(importSet);
					LogUtil.getLog().info("本次手动同步数据信息:数据库名称为:"+importSet.getImp_name()+"同步数量为:"+importSet.getImp_count()+"条,"+"同步时间:"+importSet.getImp_endtime()+"<><><><><><><><><><>");
				}
			}
			msg = "同步成功";
		} catch (Exception e) {
			msg = "同步失败";
			e.printStackTrace();
		}finally{
			response.getWriter().print(msg);
		}
	}
	//定时同步排班表(每四小时同步一次)
	public void run1() throws IllegalAccessException, InvocationTargetException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		T_OPImportSet t_ImportSet1 = findImportSetByName("手术排班表");
		//符合条件的手术排班集合
		List<V_OPER_SCHEDULED_INFO> allWorkByQuery = findAllWorkByQuery( t_ImportSet1);
		//创建一个新集合保存数据
		List<T_OPWork> opWorks = new ArrayList<T_OPWork>();
		for (V_OPER_SCHEDULED_INFO t_OPER_SCHEDULED_INFO : allWorkByQuery){
			T_OPWork opWork = new T_OPWork();
			BeanUtils.copyProperties(opWork, t_OPER_SCHEDULED_INFO);
			opWork.setOps_state(t_OPER_SCHEDULED_INFO.getOps_state());//设置排班状态
			opWorks.add(opWork);
		}
		//开始同步数据
		t_ImportSet1.setImp_starttime(sdf.format(new Date()));
		addOpWorks(opWorks);
		t_ImportSet1.setImp_endtime(sdf.format(new Date()));
		t_ImportSet1.setImp_count(allWorkByQuery.size());
		sqlServerPatientService.updateImportSet(t_ImportSet1);
		LogUtil.getLog().info("本次自动同步数据信息:数据库名称为:"+t_ImportSet1.getImp_name()+"同步数量为:"+t_ImportSet1.getImp_count()+"条,"+"同步时间:"+t_ImportSet1.getImp_endtime()+"~~~~~~~~~~~~~~~~~~~~~");
	}

	//定时同步患者表(一分钟同步一次)
	public void run2() throws IllegalAccessException, InvocationTargetException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		T_OPImportSet t_ImportSet = findImportSetByName("患者信息表");
		//符合条件的患者信息集合
		List<V_PAT_OPERATING> allPatientByQuery = findAllPatientByQuery(t_ImportSet);
		//创建一个新集合保存数据
		List<T_OPPatient> patients = new ArrayList<T_OPPatient>();
		for (V_PAT_OPERATING t_PAT_OPERATING : allPatientByQuery){
			T_OPPatient patient = new T_OPPatient();
			BeanUtils.copyProperties(patient, t_PAT_OPERATING);
			patient.setOps_status(t_PAT_OPERATING.getOps_status());
			patients.add(patient);
		}
		//开始同步数据
		t_ImportSet.setImp_starttime(sdf.format(new Date()));
		addPatients(patients);
		t_ImportSet.setImp_endtime(sdf.format(new Date()));
		t_ImportSet.setImp_count(allPatientByQuery.size());
		sqlServerPatientService.updateImportSet(t_ImportSet);
		LogUtil.getLog().info("本次自动同步数据信息:数据库名称为:"+t_ImportSet.getImp_name()+"同步数量为:"+t_ImportSet.getImp_count()+"条,"+"同步时间:"+t_ImportSet.getImp_endtime()+"~~~~~~~~~~~~~~~~~~~~~");
	}
	//定时同步手术表(每天晚上八点同步一次)
	public void run3() throws IllegalAccessException, InvocationTargetException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		T_OPImportSet t_ImportSet2 = findImportSetByName("手术室表");
		//符合条件的手术室集合
		List<V_OPERATING_INFO> allOperatingByQuery = findAllOperatingByQuery(t_ImportSet2);
		//创建一个新集合保存数据
		List<T_OPRoom>opRooms = new ArrayList<T_OPRoom>();
		for (V_OPERATING_INFO t_OPERATING_INFO : allOperatingByQuery){
			T_OPRoom opRoom = new T_OPRoom();
			opRoom.setId(String.valueOf(t_OPERATING_INFO.getId()));
			opRoom.setArea_name(t_OPERATING_INFO.getArea_name());
			opRoom.setRoom_id(t_OPERATING_INFO.getRoom_id());
			opRoom.setRoom_name(t_OPERATING_INFO.getRoom_name());
			opRooms.add(opRoom);
		}
		//开始同步数据
		t_ImportSet2.setImp_starttime(sdf.format(new Date()));
		addopRooms(opRooms);
		t_ImportSet2.setImp_endtime(sdf.format(new Date()));
		t_ImportSet2.setImp_count(allOperatingByQuery.size());
		sqlServerPatientService.updateImportSet(t_ImportSet2);
		LogUtil.getLog().info("本次自动同步数据信息:数据库名称为:"+t_ImportSet2.getImp_name()+"同步数量为:"+t_ImportSet2.getImp_count()+"条,"+"同步时间:"+t_ImportSet2.getImp_endtime()+"~~~~~~~~~~~~~~~~~~~~~");
	}
	
	
	//查询条件表
	private T_OPImportSet findImportSetByName(String name){
		return sqlServerPatientService.findImportSetByName(name);
	}
	//根据查询条件对象查询oracle(患者信息)
	private List<V_PAT_OPERATING> findAllPatientByQuery(T_OPImportSet t_ImportSet){
		return oraclePatientService.findAllPatientByQuery(t_ImportSet);
	}
	//根据查询条件对象查询oracle(排班信息)
	public List<V_OPER_SCHEDULED_INFO> findAllWorkByQuery(T_OPImportSet importSet){
		return oraclePatientService.findAllWorkByQuery(importSet);
	}
	//根据查询条件对象查询oracle(手术室)
	public List<V_OPERATING_INFO> findAllOperatingByQuery(T_OPImportSet importSet){
		return oraclePatientService.findAllOperatingByQuery(importSet);
	}
	//将数据插入到sqlserver(患者信息)
	private void addPatients(List<T_OPPatient> patients){
		sqlServerPatientService.addOrUpdatePatient(patients);
		
	}
	//将数据插入到sqlserver(手术排班信息)
	private void addOpWorks(List<T_OPWork> opWorks) {
		sqlServerPatientService.addOrUpdateWork(opWorks);
	}
	//将数据插入到sqlServer(手术室ID)
	private void addopRooms(List<T_OPRoom> opRooms) {
		sqlServerPatientService.addOrUpdateOperating(opRooms);
	}
	
}