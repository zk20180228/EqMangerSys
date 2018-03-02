package com.zkhv.equipment.service.impl;

import java.util.List;




import org.springframework.stereotype.Service;

import com.zkhv.common.BaseServiceImpl;
import com.zkhv.constant.Constant;
import com.zkhv.equipment.entity.Equipment;
import com.zkhv.equipment.service.EquipmentService;

@Service("equipmentService")
public class EquipmentServiceImpl extends BaseServiceImpl<Equipment> implements EquipmentService{
	
	
	
	public Equipment selectByPrimaryKey(String eid) {
		
		return equipmentMapper.selectByPrimaryKey(eid);
	}


	public List<Equipment> findAll() {
		return equipmentMapper.findAll();
	}


	public void deleteByPrimaryKey(String eid) {
		equipmentMapper.deleteByPrimaryKey(eid);
	}

	
	public void insert(Equipment equipment) {
		if(equipment.getEstate()==null){
			equipment.setEstate(Constant.ALREADY_CONNECT);//设置为默认的连接状态
		}	
		equipmentMapper.insert(equipment);
	}



	public void update(Equipment equipment) {
		if(equipment.getEstate()==null){
			equipment.setEstate(Constant.ALREADY_CONNECT);//设置为默认的连接状态
		}	
		equipmentMapper.update(equipment);
	}


	
	public int checkIsUnique(Equipment equipment) {
		
		return equipmentMapper.checkIsUnique(equipment);
	}


	
	public void updatePageUrl(String id, String sendData) {
		
		Equipment equipment = new Equipment();
		equipment.setEid(id);
		equipment.setEurl(sendData);
		equipmentMapper.updatePageUrl(equipment);
	}


	
	public void updateEstate(Equipment equipment) {
		
		
		equipmentMapper.updateEstate(equipment);
	}


	
	public void editCutScreen(String id, int sendData) {
		
		Equipment equipment = new Equipment();
		equipment.setEid(id);
		equipment.setCutScreen(sendData);
		equipmentMapper.editCutScreen(equipment);
	}


	
	public void editPrompt(String id, String sendData) {
		Equipment equipment = new Equipment();
		equipment.setEid(id);
		equipment.setPrompt(sendData);
		equipmentMapper.editPrompt(equipment);
	}

}
