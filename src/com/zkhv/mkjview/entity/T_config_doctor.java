package com.zkhv.mkjview.entity;

import java.sql.Blob;
import java.sql.Timestamp;

public class T_config_doctor {
	private String id;//编号
	private String dr_num;//员工号
	private String dr_name;//姓名
	private String dr_pass;//密码
	private String dr_title;//职称(编码)
	private Blob dr_pic;//照片
	private Integer dr_amlimit;//上午限额
	private Integer dr_pmlimit;//下午限额
	private Integer dr_nightlimit;//晚上限额
	private Integer dr_order;//排序号
	private String dr_desc;//描述
	private String dr_remark;//备注
	private String dr_state;//状态
	private String createusr;//创建人
	private Timestamp createtime;//创建时间
	private String updateusr;//更信任
	private Timestamp updatetime;//更新时间
	private String dt_name;//职称(名称)
	
	public String getDt_name() {
		return dt_name;
	}
	public void setDt_name(String dt_name) {
		this.dt_name = dt_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDr_num() {
		return dr_num;
	}
	public void setDr_num(String dr_num) {
		this.dr_num = dr_num;
	}
	public String getDr_name() {
		return dr_name;
	}
	public void setDr_name(String dr_name) {
		this.dr_name = dr_name;
	}
	public String getDr_pass() {
		return dr_pass;
	}
	public void setDr_pass(String dr_pass) {
		this.dr_pass = dr_pass;
	}
	public String getDr_title() {
		return dr_title;
	}
	public void setDr_title(String dr_title) {
		this.dr_title = dr_title;
	}
	public Blob getDr_pic() {
		return dr_pic;
	}
	public void setDr_pic(Blob dr_pic) {
		this.dr_pic = dr_pic;
	}
	public Integer getDr_amlimit() {
		return dr_amlimit;
	}
	public void setDr_amlimit(Integer dr_amlimit) {
		this.dr_amlimit = dr_amlimit;
	}
	public Integer getDr_pmlimit() {
		return dr_pmlimit;
	}
	public void setDr_pmlimit(Integer dr_pmlimit) {
		this.dr_pmlimit = dr_pmlimit;
	}
	public Integer getDr_nightlimit() {
		return dr_nightlimit;
	}
	public void setDr_nightlimit(Integer dr_nightlimit) {
		this.dr_nightlimit = dr_nightlimit;
	}
	public Integer getDr_order() {
		return dr_order;
	}
	public void setDr_order(Integer dr_order) {
		this.dr_order = dr_order;
	}
	public String getDr_desc() {
		return dr_desc;
	}
	public void setDr_desc(String dr_desc) {
		this.dr_desc = dr_desc;
	}
	public String getDr_remark() {
		return dr_remark;
	}
	public void setDr_remark(String dr_remark) {
		this.dr_remark = dr_remark;
	}
	public String getDr_state() {
		return dr_state;
	}
	public void setDr_state(String dr_state) {
		this.dr_state = dr_state;
	}
	public String getCreateusr() {
		return createusr;
	}
	public void setCreateusr(String createusr) {
		this.createusr = createusr;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public String getUpdateusr() {
		return updateusr;
	}
	public void setUpdateusr(String updateusr) {
		this.updateusr = updateusr;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	@Override
	public String toString() {
		return "T_Doctor [id=" + id + ", dr_num=" + dr_num + ", dr_name="
				+ dr_name + ", dr_pass=" + dr_pass + ", dr_title=" + dr_title
				+ ", dr_pic=" + dr_pic + ", dr_amlimit=" + dr_amlimit
				+ ", dr_pmlimit=" + dr_pmlimit + ", dr_nightlimit="
				+ dr_nightlimit + ", dr_order=" + dr_order + ", dr_desc="
				+ dr_desc + ", dr_remark=" + dr_remark + ", dr_state="
				+ dr_state + ", createusr=" + createusr + ", createtime="
				+ createtime + ", updateusr=" + updateusr + ", updatetime="
				+ updatetime + "]";
	}
	
	
	
}
