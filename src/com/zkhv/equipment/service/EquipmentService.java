package com.zkhv.equipment.service;

import java.util.List;

import com.zkhv.common.BaseService;
import com.zkhv.equipment.entity.Equipment;

public interface EquipmentService extends BaseService<Equipment> {
	
	//根据机器的编号查询机器	
	 Equipment selectByPrimaryKey(String eid);
	
	 List<Equipment> findAll();
	    
	 void deleteByPrimaryKey(String eid);
	
	 //校验门口机ip和编号唯一 
	 int checkIsUnique(Equipment equipment);

	 void updatePageUrl(String id, String sendData);
	
	 //根据id修改门口机的状态
	 void updateEstate(Equipment equipment);

	void editCutScreen(String id, int sendData);
	//修改门口机提示信息
	void editPrompt(String id, String sendData);
	
	
	 
}
