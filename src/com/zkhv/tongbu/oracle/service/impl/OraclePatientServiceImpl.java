package com.zkhv.tongbu.oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zkhv.tongbu.oracle.dao.OraclePatientDao;
import com.zkhv.tongbu.oracle.entity.V_OPERATING_INFO;
import com.zkhv.tongbu.oracle.entity.V_OPER_SCHEDULED_INFO;
import com.zkhv.tongbu.oracle.entity.V_PAT_OPERATING;
import com.zkhv.tongbu.oracle.service.OraclePatientService;
import com.zkhv.tongbu.sqlServer.entity.T_OPImportSet;
@Service
@Transactional
public class OraclePatientServiceImpl implements OraclePatientService {

	
	@Resource
	private OraclePatientDao oraclePatientDao;
	
	@Override
	public V_PAT_OPERATING findById(Integer id) {
		return oraclePatientDao.findById(id);
	}

	@Override
	//根据查询条件对象查询oracle(患者信息)
	public List<V_PAT_OPERATING> findAllPatientByQuery(T_OPImportSet t_ImportSet) {
		return oraclePatientDao.findAllPatientByQuery(t_ImportSet);
	}

	@Override
	public List<V_OPER_SCHEDULED_INFO> findAllWorkByQuery(T_OPImportSet importSet) {
		return oraclePatientDao.findAllWorkByQuery(importSet);
	}

	@Override
	public List<V_OPERATING_INFO> findAllOperatingByQuery(T_OPImportSet importSet) {
		return oraclePatientDao.findAllOperatingByQuery(importSet);
	}


	
	
	
	
}
