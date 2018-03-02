package com.zkhv.tongbu.sqlServer.entity;

import java.util.Date;

public class T_OPImportSet {
	private String imp_id;
	private String imp_name;//对接操作表中文名称
	private String imp_code;//对接操作表名称
	private Integer imp_autoexe;//是否自动更新
	private String imp_starttime;//自动更新开始时间
	private String imp_endtime;//自动更新结束时间
	private String imp_field;//查询数据库的最终字段
	private String imp_tempfield;//oracle查询字段 (别名)
	private String imp_table;//oracle表名称
	private String imp_where;//查询条件
	private Integer imp_count;//每次对接执行条数
	private String imp_orderby;//排序依据
	private Integer imp_frequency;
	private String imp_time;
	private Integer imp_order;//排序号
	private Integer createusr;
	private Date createtime;
	private Integer updateusr;
	private Date updatetime;
	private String imp_optimename;//操作时间
	private Date imp_autooptime;//自动更新时间
	private String imp_isprocedure;//是否可用
	private String imp_wholesql;//完整语句
	public String getImp_id() {
		return imp_id;
	}
	public void setImp_id(String imp_id) {
		this.imp_id = imp_id;
	}
	public String getImp_name() {
		return imp_name;
	}
	public void setImp_name(String imp_name) {
		this.imp_name = imp_name;
	}
	public String getImp_code() {
		return imp_code;
	}
	public void setImp_code(String imp_code) {
		this.imp_code = imp_code;
	}
	public Integer getImp_autoexe() {
		return imp_autoexe;
	}
	public void setImp_autoexe(Integer imp_autoexe) {
		this.imp_autoexe = imp_autoexe;
	}
	public String getImp_starttime() {
		return imp_starttime;
	}
	public void setImp_starttime(String imp_starttime) {
		this.imp_starttime = imp_starttime;
	}
	public String getImp_endtime() {
		return imp_endtime;
	}
	public void setImp_endtime(String imp_endtime) {
		this.imp_endtime = imp_endtime;
	}
	public String getImp_field() {
		return imp_field;
	}
	public void setImp_field(String imp_field) {
		this.imp_field = imp_field;
	}
	public String getImp_tempfield() {
		return imp_tempfield;
	}
	public void setImp_tempfield(String imp_tempfield) {
		this.imp_tempfield = imp_tempfield;
	}
	public String getImp_table() {
		return imp_table;
	}
	public void setImp_table(String imp_table) {
		this.imp_table = imp_table;
	}
	public String getImp_where() {
		return imp_where;
	}
	public void setImp_where(String imp_where) {
		this.imp_where = imp_where;
	}
	public Integer getImp_count() {
		return imp_count;
	}
	public void setImp_count(Integer imp_count) {
		this.imp_count = imp_count;
	}
	public String getImp_orderby() {
		return imp_orderby;
	}
	public void setImp_orderby(String imp_orderby) {
		this.imp_orderby = imp_orderby;
	}
	public Integer getImp_frequency() {
		return imp_frequency;
	}
	public void setImp_frequency(Integer imp_frequency) {
		this.imp_frequency = imp_frequency;
	}
	public String getImp_time() {
		return imp_time;
	}
	public void setImp_time(String imp_time) {
		this.imp_time = imp_time;
	}
	public Integer getImp_order() {
		return imp_order;
	}
	public void setImp_order(Integer imp_order) {
		this.imp_order = imp_order;
	}
	public Integer getCreateusr() {
		return createusr;
	}
	public void setCreateusr(Integer createusr) {
		this.createusr = createusr;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Integer getUpdateusr() {
		return updateusr;
	}
	public void setUpdateusr(Integer updateusr) {
		this.updateusr = updateusr;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getImp_optimename() {
		return imp_optimename;
	}
	public void setImp_optimename(String imp_optimename) {
		this.imp_optimename = imp_optimename;
	}
	public Date getImp_autooptime() {
		return imp_autooptime;
	}
	public void setImp_autooptime(Date imp_autooptime) {
		this.imp_autooptime = imp_autooptime;
	}
	public String getImp_isprocedure() {
		return imp_isprocedure;
	}
	public void setImp_isprocedure(String imp_isprocedure) {
		this.imp_isprocedure = imp_isprocedure;
	}
	
	public String getImp_wholesql() {
		return imp_wholesql;
	}
	public void setImp_wholesql(String imp_wholesql) {
		this.imp_wholesql = imp_wholesql;
	}
	@Override
	public String toString() {
		return "T_ImportSet [imp_id=" + imp_id + ", imp_name=" + imp_name
				+ ", imp_code=" + imp_code + ", imp_autoexe=" + imp_autoexe
				+ ", imp_starttime=" + imp_starttime + ", imp_endtime="
				+ imp_endtime + ", imp_field=" + imp_field + ", imp_tempfield="
				+ imp_tempfield + ", imp_table=" + imp_table + ", imp_where="
				+ imp_where + ", imp_count=" + imp_count + ", imp_orderby="
				+ imp_orderby + ", imp_frequency=" + imp_frequency
				+ ", imp_time=" + imp_time + ", imp_order=" + imp_order
				+ ", createusr=" + createusr + ", createtime=" + createtime
				+ ", updateusr=" + updateusr + ", updatetime=" + updatetime
				+ ", imp_optimename=" + imp_optimename + ", imp_autooptime="
				+ imp_autooptime + ", imp_isprocedure=" + imp_isprocedure
				+ ", imp_wholesql=" + imp_wholesql + "]";
	}
	
	
	
}
