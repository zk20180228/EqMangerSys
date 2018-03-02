package com.zkhv.tongbu.sqlServer.service;

import java.util.List;

import com.zkhv.mkjview.entity.T_OPRoom;
import com.zkhv.mkjview.entity.T_OPWork;
import com.zkhv.mkjview.entity.T_OPPatient;
import com.zkhv.tongbu.sqlServer.entity.T_OPImportSet;

public interface SqlServerPatientService {

//	T_OPPatient findById(String id);
	//查询sql条件表的患者信息表条件
	public T_OPImportSet findImportSetByName(String name);
	//添加或更新数据(患者信息)
	public void addOrUpdatePatient(List<T_OPPatient> patients);
	//添加或更新数据(排班信息)
	public void addOrUpdateWork(List<T_OPWork> opWorks );
	//添加或更新数据(手术室信息)
	public void addOrUpdateOperating(List<T_OPRoom> opRooms);
	//执行完成更新语句表
	public void updateImportSet(T_OPImportSet t);
}
