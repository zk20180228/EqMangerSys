package com.zkhv.tongbu.oracle.entity;

import java.util.Date;

public class V_OPER_SCHEDULED_INFO {
	private String id;		//编号
	private String area_name;//手术区域名称
	private String room_id;//手术室编号
	private String room_name;//手术室名称
	private String p_name;//患者姓名
	private String p_num;//患者住院号
	private String p_dept;//患者所在科室
	private String p_sex;//患者性别
	private String p_old;//患者年龄
	private String p_diagnose;//患者诊断
	private String ops_anme;//手术名称
	private String ops_doctor;//主刀医生
	private String ops_doctor_id;//主刀医生编号
	private String ops_mzdoctor;//麻醉医生
	private String ops_mzdoctor_id;//麻醉医生编号
	private Date ops_time;//手术时间
	private Integer ops_state;//手术状态
	private Date update_time;//信息更新时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_num() {
		return p_num;
	}
	public void setP_num(String p_num) {
		this.p_num = p_num;
	}
	public String getP_dept() {
		return p_dept;
	}
	public void setP_dept(String p_dept) {
		this.p_dept = p_dept;
	}
	public String getP_sex() {
		return p_sex;
	}
	public void setP_sex(String p_sex) {
		this.p_sex = p_sex;
	}
	public String getP_old() {
		return p_old;
	}
	public void setP_old(String p_old) {
		this.p_old = p_old;
	}
	public String getP_diagnose() {
		return p_diagnose;
	}
	public void setP_diagnose(String p_diagnose) {
		this.p_diagnose = p_diagnose;
	}
	public String getOps_anme() {
		return ops_anme;
	}
	public void setOps_anme(String ops_anme) {
		this.ops_anme = ops_anme;
	}
	public String getOps_doctor() {
		return ops_doctor;
	}
	public void setOps_doctor(String ops_doctor) {
		this.ops_doctor = ops_doctor;
	}
	public String getOps_doctor_id() {
		return ops_doctor_id;
	}
	public void setOps_doctor_id(String ops_doctor_id) {
		this.ops_doctor_id = ops_doctor_id;
	}
	public String getOps_mzdoctor() {
		return ops_mzdoctor;
	}
	public void setOps_mzdoctor(String ops_mzdoctor) {
		this.ops_mzdoctor = ops_mzdoctor;
	}
	public String getOps_mzdoctor_id() {
		return ops_mzdoctor_id;
	}
	public void setOps_mzdoctor_id(String ops_mzdoctor_id) {
		this.ops_mzdoctor_id = ops_mzdoctor_id;
	}
	public Date getOps_time() {
		return ops_time;
	}
	public void setOps_time(Date ops_time) {
		this.ops_time = ops_time;
	}
	public Integer getOps_state() {
		return ops_state;
	}
	public void setOps_state(Integer ops_state) {
		this.ops_state = ops_state;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	@Override
	public String toString() {
		return "V_OPER_SCHEDULED_INFO [id=" + id + ", area_name=" + area_name
				+ ", room_id=" + room_id + ", room_name=" + room_name
				+ ", p_name=" + p_name + ", p_num=" + p_num + ", p_dept="
				+ p_dept + ", p_sex=" + p_sex + ", p_old=" + p_old
				+ ", p_diagnose=" + p_diagnose + ", ops_anme=" + ops_anme
				+ ", ops_doctor=" + ops_doctor + ", ops_doctor_id="
				+ ops_doctor_id + ", ops_mzdoctor=" + ops_mzdoctor
				+ ", ops_mzdoctor_id=" + ops_mzdoctor_id + ", ops_time="
				+ ops_time + ", ops_state=" + ops_state + ", update_time="
				+ update_time + "]";
	}
	
	
	
	

}
