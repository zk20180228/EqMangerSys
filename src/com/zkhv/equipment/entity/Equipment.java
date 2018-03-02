package com.zkhv.equipment.entity;

import java.io.Serializable;

public class Equipment implements Serializable{
    
	private static final long serialVersionUID = 5723725983668356397L;

	private String eid;//门口机id
	
	private String enumber;//门口机编号，唯一
	
	private String eip;//门口机ip

	private String ename;//门口机名

    private String estate;//门口机状态
    
    private String edesc;//门口机描述
    
    private String trimingON_OFF;//门口机定时开关机标记

    private String offTime;//门口机关机时间
    
    private String eon;//门口机关机时间
    
    private String eurl;//门口机访问的url
    
    private int rfCount=10;//门口机刷新频次，默认
    
    private int cutScreen=10;//门口机切屏频次，默认
    
    private String prompt;//门口机提示信息
    
    private String opRoomId;//手术室的ID
    

    public String getEid() {
        return eid;
    }

    public void equipment(String eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEip() {
        return eip;
    }

    public void setEip(String eip) {
        this.eip = eip;
    }


	public String getEstate() {
		return estate;
	}

	public void setEstate(String estate) {
		this.estate = estate;
	}

	public String getEdesc() {
        return edesc;
    }

    public void setEdesc(String edesc) {
        this.edesc = edesc;
    }

	public String getEnumber() {
		return enumber;
	}

	public void setEnumber(String enumber) {
		this.enumber = enumber;
	}

	public String getTrimingON_OFF() {
		return trimingON_OFF;
	}

	public void setTrimingON_OFF(String trimingON_OFF) {
		this.trimingON_OFF = trimingON_OFF;
	}

	public String getEurl() {
		return eurl;
	}

	public void setEurl(String eurl) {
		this.eurl = eurl;
	}

	public int getRfCount() {
		return rfCount;
	}

	public void setRfCount(int rfCount) {
		this.rfCount = rfCount;
	}

	public int getCutScreen() {
		return cutScreen;
	}

	public void setCutScreen(int cutScreen) {
		this.cutScreen = cutScreen;
	}

	public String getOffTime() {
		return offTime;
	}

	public void setOffTime(String offTime) {
		this.offTime = offTime;
	}

	public String getEon() {
		return eon;
	}

	public void setEon(String eon) {
		this.eon = eon;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getOpRoomId() {
		return opRoomId;
	}

	public void setOpRoomId(String opRoomId) {
		this.opRoomId = opRoomId;
	}

	
	
	public void setEid(String eid) {
		this.eid = eid;
	}

	@Override
	public String toString() {
		return "Equipment [eid=" + eid + ", enumber=" + enumber + ", eip="
				+ eip + ", ename=" + ename + ", estate=" + estate + ", edesc="
				+ edesc + ", trimingON_OFF=" + trimingON_OFF + ", offTime="
				+ offTime + ", eon=" + eon + ", eurl=" + eurl + ", rfCount="
				+ rfCount + ", cutScreen=" + cutScreen + ", prompt=" + prompt
				+ ", opRoomId=" + opRoomId + "]";
	}

	
	
	


	

    
	
    
    
    
}