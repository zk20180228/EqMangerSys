package com.zkhv.mkjview.entity;

public class T_OPMKJ {
	private String id;//门口机id
	private String mkj_code;//门口机编号
	private String mkj_ip;//门口机ip
	private String mkj_status;//门口机状态
	private Integer mkj_onoff_flag;//门口机定时开关机开关标记
	private String mkj_onoff_time;//门口机开关机时间
	private String mkj_page_url;//门口机访问地址
	private Integer mkj_pagereload_fre;//门口机页面整体刷新频次
	private Integer mkj_cutscreen_fre;//门口机切屏次数
	private String mkj_description;//门口机描述
	private String mkj_prompt;//门口机提示信息
	private String oproom_id;//手术室id
	private Integer patientNum;//门口机对应手术室患者数量
	private String room_name;//手术室
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMkj_code() {
		return mkj_code;
	}
	public void setMkj_code(String mkj_code) {
		this.mkj_code = mkj_code;
	}
	public String getMkj_ip() {
		return mkj_ip;
	}
	public void setMkj_ip(String mkj_ip) {
		this.mkj_ip = mkj_ip;
	}
	public String getMkj_status() {
		return mkj_status;
	}
	public void setMkj_status(String mkj_status) {
		this.mkj_status = mkj_status;
	}
	public Integer getMkj_onoff_flag() {
		return mkj_onoff_flag;
	}
	public void setMkj_onoff_flag(Integer mkj_onoff_flag) {
		this.mkj_onoff_flag = mkj_onoff_flag;
	}
	public String getMkj_onoff_time() {
		return mkj_onoff_time;
	}
	public void setMkj_onoff_time(String mkj_onoff_time) {
		this.mkj_onoff_time = mkj_onoff_time;
	}
	public String getMkj_page_url() {
		return mkj_page_url;
	}
	public void setMkj_page_url(String mkj_page_url) {
		this.mkj_page_url = mkj_page_url;
	}
	public Integer getMkj_pagereload_fre() {
		return mkj_pagereload_fre;
	}
	public void setMkj_pagereload_fre(Integer mkj_pagereload_fre) {
		this.mkj_pagereload_fre = mkj_pagereload_fre;
	}
	public Integer getMkj_cutscreen_fre() {
		return mkj_cutscreen_fre;
	}
	public void setMkj_cutscreen_fre(Integer mkj_cutscreen_fre) {
		this.mkj_cutscreen_fre = mkj_cutscreen_fre;
	}
	public String getMkj_description() {
		return mkj_description;
	}
	public void setMkj_description(String mkj_description) {
		this.mkj_description = mkj_description;
	}
	public String getMkj_prompt() {
		return mkj_prompt;
	}
	public void setMkj_prompt(String mkj_prompt) {
		this.mkj_prompt = mkj_prompt;
	}
	public String getOproom_id() {
		return oproom_id;
	}
	public void setOproom_id(String oproom_id) {
		this.oproom_id = oproom_id;
	}
	public Integer getPatientNum() {
		return patientNum;
	}
	public void setPatientNum(Integer patientNum) {
		this.patientNum = patientNum;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	@Override
	public String toString() {
		return "T_MKJ [id=" + id + ", mkj_code=" + mkj_code + ", mkj_ip="
				+ mkj_ip + ", mkj_status=" + mkj_status + ", mkj_onoff_flag="
				+ mkj_onoff_flag + ", mkj_onoff_time=" + mkj_onoff_time
				+ ", mkj_page_url=" + mkj_page_url + ", mkj_pagereload_fre="
				+ mkj_pagereload_fre + ", mkj_cutscreen_fre="
				+ mkj_cutscreen_fre + ", mkj_description=" + mkj_description
				+ ", mkj_prompt=" + mkj_prompt + ", oproom_id=" + oproom_id
				+ ", patientNum=" + patientNum + "]";
	}
	
	
	
}
