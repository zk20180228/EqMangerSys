package com.zkhv.mkjview.entity;


public class MkjView {
	private String id;//编号
	private String area_name;//手术区域名称
	private String room_id;//手术室编号
	private String room_name;//手术室名称
	private String p_name;//患者姓名
	private String p_num;//患者住院号
	private String p_dept;//患者所在科室
	private String p_sex;//患者性别
	private String p_old;//患者年龄
	private String p_diagnose;//患者诊断
	private String ops_name;//手术名称
	private String ops_doctor;//主刀医生
	private String ops_mzdoctor;//麻醉医生
	private String ops_time;//手术时间
	private String ops_starttime;//手术开始时间
	private String ops_stilltime;//手术持续时间
	private String ops_endtime;//手术结束时间
	private String update_time;//信息更新时间
	private Integer ops_status;//手术状态 (患者表)
	private String ops_doctor_pic;//主刀医生头像
	private String ops_mzdoctor_pic;//麻醉医生头像
	private String ops_doctitle;//主刀医生职称
	private String ops_mzdoctitle;//麻醉医生职称
	private Integer ops_state;//排班表状态
	
	public Integer getOps_state() {
		return ops_state;
	}
	public void setOps_state(Integer ops_state) {
		this.ops_state = ops_state;
	}
	public String getOps_doctitle() {
		return ops_doctitle;
	}
	public void setOps_doctitle(String ops_doctitle) {
		this.ops_doctitle = ops_doctitle;
	}
	public String getOps_mzdoctitle() {
		return ops_mzdoctitle;
	}
	public void setOps_mzdoctitle(String ops_mzdoctitle) {
		this.ops_mzdoctitle = ops_mzdoctitle;
	}
	public String getOps_doctor_pic() {
		return ops_doctor_pic;
	}
	public void setOps_doctor_pic(String ops_doctor_pic) {
		this.ops_doctor_pic = ops_doctor_pic;
	}
	public String getOps_mzdoctor_pic() {
		return ops_mzdoctor_pic;
	}
	public void setOps_mzdoctor_pic(String ops_mzdoctor_pic) {
		this.ops_mzdoctor_pic = ops_mzdoctor_pic;
	}
	public Integer getOps_status() {
		return ops_status;
	}
	public void setOps_status(Integer ops_status) {
		this.ops_status = ops_status;
	}
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
	public String getOps_name() {
		return ops_name;
	}
	public void setOps_name(String ops_name) {
		this.ops_name = ops_name;
	}
	public String getOps_doctor() {
		return ops_doctor;
	}
	public void setOps_doctor(String ops_doctor) {
		this.ops_doctor = ops_doctor;
	}
	public String getOps_mzdoctor() {
		return ops_mzdoctor;
	}
	public void setOps_mzdoctor(String ops_mzdoctor) {
		this.ops_mzdoctor = ops_mzdoctor;
	}
	public String getOps_time() {
		return ops_time;
	}
	public void setOps_time(String ops_time) {
		this.ops_time = ops_time;
	}
	public String getOps_starttime() {
		return ops_starttime;
	}
	public void setOps_starttime(String ops_starttime) {
		this.ops_starttime = ops_starttime;
	}
	public String getOps_stilltime() {
		return ops_stilltime;
	}
	public void setOps_stilltime(String ops_stilltime) {
		this.ops_stilltime = ops_stilltime;
	}
	public String getOps_endtime() {
		return ops_endtime;
	}
	public void setOps_endtime(String ops_endtime) {
		this.ops_endtime = ops_endtime;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	@Override
	public String toString() {
		return "MkjView [id=" + id + ", area_name=" + area_name + ", room_id="
				+ room_id + ", room_name=" + room_name + ", p_name=" + p_name
				+ ", p_num=" + p_num + ", p_dept=" + p_dept + ", p_sex="
				+ p_sex + ", p_old=" + p_old + ", p_diagnose=" + p_diagnose
				+ ", ops_name=" + ops_name + ", ops_doctor=" + ops_doctor
				+ ", ops_mzdoctor=" + ops_mzdoctor + ", ops_time=" + ops_time
				+ ", ops_starttime=" + ops_starttime + ", ops_stilltime="
				+ ops_stilltime + ", ops_endtime=" + ops_endtime
				+ ", update_time=" + update_time + ", ops_status=" + ops_status
				+ ", ops_doctor_pic=" + ops_doctor_pic + ", ops_mzdoctor_pic="
				+ ops_mzdoctor_pic + ", ops_doctitle=" + ops_doctitle
				+ ", ops_mzdoctitle=" + ops_mzdoctitle + ", ops_state="
				+ ops_state + "]";
	}
	
	
}
