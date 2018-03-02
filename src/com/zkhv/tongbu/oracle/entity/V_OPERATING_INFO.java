package com.zkhv.tongbu.oracle.entity;

public class V_OPERATING_INFO {
	private Integer id;		//编号
	private String area_name;//手术区域名称
	private String room_id;//手术室编号
	private String room_name;//手术室名称
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public String getRoom_id() {
		return room_id;
	}
	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	@Override
	public String toString() {
		return "T_OPRoom [id=" + id + ", area_name=" + area_name + ", room_id="
				+ room_id + ", room_name=" + room_name + "]";
	}
	
	
}
