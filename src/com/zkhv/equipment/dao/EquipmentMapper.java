package com.zkhv.equipment.dao;

import java.util.List;

import com.zkhv.common.BaseMapper;
import com.zkhv.equipment.entity.Equipment;

public interface EquipmentMapper extends BaseMapper<Equipment> {


    int deleteByPrimaryKey(String eid);


    int insertSelective(Equipment record);

    Equipment selectByPrimaryKey(String eid);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);
    
    List<Equipment> findAll();

	
	int checkIsUnique(Equipment equipment);
	
	//修改设备的url
	void updatePageUrl(Equipment equipment);

	void updateEstate(Equipment equipment);


	void editCutScreen(Equipment equipment);

	//修改提示信息
	void editPrompt(Equipment equipment);
    
    
}