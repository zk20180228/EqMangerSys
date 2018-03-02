package com.zkhv.user.entity;

import java.io.Serializable;

public class User implements Serializable{
   
	
	private static final long serialVersionUID = 1550194540533450985L;

	private String uid;//用户的id

    private String uname;//用户的名字

    private String uaccount;//账号

    private String upwd;//密码

    private String ustate;//状态
    
    private String user_sex;//性别
    
    private int  del_flg;//标记

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUaccount() {
        return uaccount;
    }

    public void setUaccount(String uaccount) {
        this.uaccount = uaccount;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUstate() {
        return ustate;
    }

    public void setUstate(String ustate) {
        this.ustate = ustate;
    }

	public String getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}

	public int getDel_flg() {
		return del_flg;
	}

	public void setDel_flg(int del_flg) {
		this.del_flg = del_flg;
	}



    
    
    
    
    
    
    
}