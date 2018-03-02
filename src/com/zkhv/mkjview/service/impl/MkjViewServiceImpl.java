package com.zkhv.mkjview.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkhv.mkjview.dao.MkjViewMapper;
import com.zkhv.mkjview.entity.PatientVo;
import com.zkhv.mkjview.entity.T_config_doctor;
import com.zkhv.mkjview.entity.T_OPMKJ;
import com.zkhv.mkjview.entity.T_OPWork;
import com.zkhv.mkjview.entity.T_OPPatient;
import com.zkhv.mkjview.service.MkjViewService;
import com.zkhv.tongbu.sqlServer.entity.T_OPImportSet;
@Service("mkjViewService")
public class MkjViewServiceImpl implements MkjViewService {

	@Autowired
	private MkjViewMapper mkjViewMapper;
	

	//根据门口机ID查询手术信息
	public List<T_OPWork> findopWorkByMkjid(Integer id){
		return mkjViewMapper.findopWorkByMkjid(id);
	}

	//根据门口机ID查询患者信息
	public List<T_OPPatient> findPatientByMkjid(Integer id) {
		return mkjViewMapper.findPatientByMkjid(id);
	}
	//查询根据医生编号查询医生
	public T_config_doctor findDoctorById(String id){
		return mkjViewMapper.findDoctorById(id);
	}
	//根据门口机编号查询门口机
	public T_OPMKJ findMkjByCode(String mkj_code){
		return mkjViewMapper.findMkjByCode(mkj_code);
	}
	//根据手术室id查询本手术室患者数量
	public Integer findPatientNum(String room_id){
		return mkjViewMapper.findPatientNum(room_id);
	}
	
	public List<PatientVo> findPatientsByRoomId(String roomId){
		return mkjViewMapper.findPatientsByRoomId(roomId);
	}
	
	public List<Map<String,String>> findDocTitleMap(){
		return mkjViewMapper.findDocTitleMap();
	}

	@Override
	public List<T_OPPatient> findPatientByRoomId1(String id) {
		
		return mkjViewMapper.findPatientByRoomId1(id);
	}

	@Override
	public List<T_OPImportSet> selectAllSql() {
		return mkjViewMapper.selectAllSql();
	}

	@Override
	//根据id查询sql信息
	public T_OPImportSet findSqlById(String imp_id) {
		
		return mkjViewMapper.findSqlById(imp_id);
	}

	@Override
	//修改sql信息
	public void updateSql(T_OPImportSet importSet) {
		 mkjViewMapper.updateSql(importSet);
	}

	@Override
	//根据手术室ID查询最后一次更新的数据
	public PatientVo findLastRecord(String id) {
		
		return mkjViewMapper.findLastRecord(id);
	}
}
