package com.zkhv.tongbu.sqlServer.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zkhv.mkjview.entity.T_OPRoom;
import com.zkhv.mkjview.entity.T_OPWork;
import com.zkhv.mkjview.entity.T_OPPatient;
import com.zkhv.tongbu.sqlServer.dao.SqlServerPatientDao;
import com.zkhv.tongbu.sqlServer.entity.T_OPImportSet;
import com.zkhv.tongbu.sqlServer.service.SqlServerPatientService;
@Service
@Transactional
public class SqlServerPatientServiceImpl implements SqlServerPatientService{

	@Resource
	private SqlServerPatientDao sqlServerPatientDao;

	SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
//	@Override
//	public T_OPPatient findById(String id) {
//		return sqlServerPatientDao.findById(id);
//	}
	//查询sql条件表的患者信息表条件
	public T_OPImportSet findImportSetByName(String name){
		return sqlServerPatientDao.findImportSetByName(name);
	}
	//添加或更新数据(患者信息)
	@Override
	public void addOrUpdatePatient(List<T_OPPatient> patients){
		//需要更新的患者集合
		List<T_OPPatient> upList = new ArrayList<T_OPPatient>();
		//需要删除并添加的患者集合
		List<T_OPPatient> delList = new ArrayList<T_OPPatient>();
		//不存在的患者集合
		List<T_OPPatient> insList = new ArrayList<T_OPPatient>();
		//已经存在的患者集合
		List<T_OPPatient> havList = new ArrayList<T_OPPatient>();
		
		for (T_OPPatient t_Patient : patients){
			t_Patient.setUpdate_time(new Date());
			//根据患者id查询患者信息为空的话是新数据
			T_OPPatient t = sqlServerPatientDao.findById(t_Patient.getId());
			if(t == null){
				insList.add(t_Patient);
			}else{
				havList.add(t_Patient);
			}
		}
		//不存在的直接插入
		if(insList != null && insList.size()>0){
			for (T_OPPatient t_Patient : insList) {
				sqlServerPatientDao.insertPatient(t_Patient);
			}
		}
		
		for (T_OPPatient t_Patient : havList) {
			if(SDF.format(t_Patient.getOps_time()).equals(SDF.format(new Date()))){
				//更新
				upList.add(t_Patient);
			}else{
				//删除后插入
				delList.add(t_Patient);
			}
		}
		//已经存在且是当天的数据 更新
		if(upList != null && upList.size()>0){
			for (T_OPPatient t_Patient : upList) {
				sqlServerPatientDao.updatePatient(t_Patient);
			}
		}
		//已经存在且不是当天的数据 删除后插入
		if(delList!=null&&delList.size()>0){
			sqlServerPatientDao.deletePatient(delList);
			for (T_OPPatient t_Patient : delList) {
				sqlServerPatientDao.insertPatient(t_Patient);
			}
		}
	}

	//添加或更新数据(排班信息)
	@Override
	public void addOrUpdateWork(List<T_OPWork> opWorks){
		//需要更新的排班集合
		List<T_OPWork> upList = new ArrayList<T_OPWork>();
		//需要删除并添加的排班集合
		List<T_OPWork> delList = new ArrayList<T_OPWork>();
		//不存在的排班集合
		List<T_OPWork> insList = new ArrayList<T_OPWork>();
		//已经存在的排班集合
		List<T_OPWork> havList = new ArrayList<T_OPWork>();
		
		for (T_OPWork t_opwork : opWorks){
			t_opwork.setUpdate_time(new Date());
			//根据患者id查询患者信息为空的话是新数据
			T_OPWork opWork = sqlServerPatientDao.findWorkById(t_opwork.getId());
			if(opWork == null){
				insList.add(t_opwork);
			}else{
				havList.add(t_opwork);
			}
		}
		//不存在的直接插入
		if(insList != null && insList.size()>0){
			for (T_OPWork t_opwork : insList) {
				sqlServerPatientDao.insertOPWork(t_opwork);
			}
		}
		
		for (T_OPWork t_opwork : havList) {
			if(SDF.format(t_opwork.getOps_time()).equals(SDF.format(new Date()))){
				//更新
				upList.add(t_opwork);
			}else{
				//删除后插入
				delList.add(t_opwork);
			}
		}
		//已经存在且是当天的数据 更新
		if(upList != null && upList.size()>0){
			for (T_OPWork t_opwork : upList) {
				sqlServerPatientDao.updateOPWork(t_opwork);
			}
		}
		//已经存在且不是当天的数据 删除后插入
		if(delList!=null&&delList.size()>0){
			sqlServerPatientDao.deleteOPWork(delList);
			for (T_OPWork t_opwork : delList) {
				sqlServerPatientDao.insertOPWork(t_opwork);
			}
			
		}
	}

	//添加或更新数据(手术室信息)
	@Override
	public void addOrUpdateOperating(List<T_OPRoom> opRooms) {
		//先清空在插入
		if(opRooms!=null&&opRooms.size()>0){
			sqlServerPatientDao.deleteOPRoom();
			for (T_OPRoom t_OPRoom : opRooms){
				t_OPRoom.setUpdate_time(new Date());
				sqlServerPatientDao.insertOprooms(t_OPRoom);
			}
		}
		
	}
	@Override
	public void updateImportSet(T_OPImportSet t) {
		sqlServerPatientDao.updateImportSet(t);
	}
	
}
