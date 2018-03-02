package com.zkhv.tongbu.sqlServer.dao;

import java.util.List;

import com.zkhv.mkjview.entity.T_OPRoom;
import com.zkhv.mkjview.entity.T_OPWork;
import com.zkhv.mkjview.entity.T_OPPatient;
import com.zkhv.tongbu.sqlServer.entity.T_OPImportSet;

public interface SqlServerPatientDao {
	//根据患者id查询患者是否存在
	public T_OPPatient findById(String id);
	//根据排班id查询排班信息是否存在
	public T_OPWork findWorkById(String id);
	//根据手术室id查询手术室是否存在
	public T_OPRoom findRoomById(String id);
	//查询sql条件表的患者信息表条件
	public T_OPImportSet findImportSetByName(String name);
	//更新患者信息
	public void updatePatient(T_OPPatient upList);
	//删除患者信息
	public void deletePatient(List<T_OPPatient> delList);
	//添加患者信息
	public void insertPatient(T_OPPatient delList);
	//更新手术排班信息
	public void updateOPWork(T_OPWork upList);
	//删除手术排班信息
	public void deleteOPWork(List<T_OPWork> delList);
	//添加手术排班信息
	public void insertOPWork(T_OPWork delList);
	//直接删除手术室信息
	public void deleteOPRoom();
	//批量增加手术室信息
	public void insertOprooms(T_OPRoom opRooms);
	//更新sql条件表
	public void updateImportSet(T_OPImportSet t);
}
