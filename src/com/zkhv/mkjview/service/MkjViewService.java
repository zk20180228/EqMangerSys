package com.zkhv.mkjview.service;

import java.util.List;
import java.util.Map;

import com.zkhv.mkjview.entity.PatientVo;
import com.zkhv.mkjview.entity.T_config_doctor;
import com.zkhv.mkjview.entity.T_OPMKJ;
import com.zkhv.mkjview.entity.T_OPWork;
import com.zkhv.mkjview.entity.T_OPPatient;
import com.zkhv.tongbu.sqlServer.entity.T_OPImportSet;

public interface MkjViewService {
	//根据门口机ID查询手术信息
	public List<T_OPWork> findopWorkByMkjid(Integer id);
	//根据门口机ID查询患者信息
	public List<T_OPPatient> findPatientByMkjid(Integer id);
	//查询根据医生编号查询医生
	public T_config_doctor findDoctorById(String id);
	//根据门口机编号查询门口机
	public T_OPMKJ findMkjByCode(String mkj_code);
	//根据手术室id查询本手术室患者数量
	public Integer findPatientNum(String room_id);
	
	public List<PatientVo> findPatientsByRoomId(String roomId);
	
	public List<Map<String,String>> findDocTitleMap();
	
	public List<T_OPPatient> findPatientByRoomId1(String id);
	
	public List<T_OPImportSet> selectAllSql();
	//根据id查询sql信息
	public T_OPImportSet findSqlById(String imp_id);
	//更新sql信息
	public void updateSql(T_OPImportSet importSet);
	//根据手术室ID查询最后一次更新的数据
	public PatientVo findLastRecord(String id);
}
