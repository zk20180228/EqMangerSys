package com.zkhv.tongbu.oracle.dao;

import java.util.List;

import com.zkhv.tongbu.oracle.entity.V_OPERATING_INFO;
import com.zkhv.tongbu.oracle.entity.V_OPER_SCHEDULED_INFO;
import com.zkhv.tongbu.oracle.entity.V_PAT_OPERATING;
import com.zkhv.tongbu.sqlServer.entity.T_OPImportSet;


public interface OraclePatientDao {
	
//	测试查询oracle表
	public V_PAT_OPERATING findById(Integer id);

	//根据查询条件对象查询oracle(患者信息)
	public List<V_PAT_OPERATING> findAllPatientByQuery(T_OPImportSet importSet);
	//根据查询条件对象查询oracle(排班信息)
	public List<V_OPER_SCHEDULED_INFO> findAllWorkByQuery(T_OPImportSet importSet);
	//根据查询条件对象查询oracle(手术室)
	public List<V_OPERATING_INFO> findAllOperatingByQuery(T_OPImportSet importSet);
}
