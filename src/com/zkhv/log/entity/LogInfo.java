package com.zkhv.log.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class LogInfo implements Serializable {

	private static final long serialVersionUID = 2313810260660083867L;
	
	private String logId;//日志id
	private Timestamp logTime;//日志时间
	private String logContent;//内容
	private String remark;//备注,在这里为门口机编号
	private String log_time;//这个字段是来处理时间字符串，没意义
	
	
	
	
	
	public String getLog_time() {
		return log_time;
	}
	public void setLog_time(String log_time) {
		this.log_time = log_time;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	
	public Timestamp getLogTime() {
		return logTime;
	}
	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	public String toString() {
		return "LogInfo [logId=" + logId + ", logTime=" + logTime
				+ ", logContent=" + logContent + ", remark=" + remark
				+ ", log_time=" + log_time + "]";
	}
	
	
	
	
	
	
	
	

}
