package com.zkhv.command.entity;

import java.io.Serializable;

//系统参数指令的对象
public class SysCommand implements Serializable{
	
	
	private static final long serialVersionUID = -4011905255446400242L;

	private String commandId;//命令的id
	
	private String commandKey;//命令key
	
	private String commanValue;//命令值
	
	private String commandDesc;//命令描述
	
	private String commandType;//命令的类型

	public String getCommandId() {
		return commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}

	public String getCommandKey() {
		return commandKey;
	}

	public void setCommandKey(String commandKey) {
		this.commandKey = commandKey;
	}

	public String getCommanValue() {
		return commanValue;
	}

	public void setCommanValue(String commanValue) {
		this.commanValue = commanValue;
	}

	public String getCommandDesc() {
		return commandDesc;
	}

	public void setCommandDesc(String commandDesc) {
		this.commandDesc = commandDesc;
	}

	public String getCommandType() {
		return commandType;
	}

	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}

	
	
	public String toString() {
		return "SysCommand [commandId=" + commandId + ", commandKey="
				+ commandKey + ", commanValue=" + commanValue
				+ ", commandDesc=" + commandDesc + ", commandType="
				+ commandType + "]";
	}
	

	
	
	
	

}
