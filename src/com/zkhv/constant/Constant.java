package com.zkhv.constant;


public class Constant {
	
	//登录用户的标记
	public static final String LOGIN_USER="SYS_USER";

	//连接状态
	public static final String ALREADY_CONNECT= "<font style=color:#00CC00;>已连接</font>";//绿色
	public static final String UN_CONNECT="<font style =color:#FF0000;>未连接</font>";//红色
	
	//命令的【key】并不是命令文本身，命令是根据key在数据库查的
	public static final String SHUTDOWN ="SHUTDOWN";//关机
	public static final String REBOOT="REBOOT";//重启
	public static final String TIMING_SHUTDOWN="TIMING_SHUTDOWN";//定时关机
	public static final String REFRESH="REFRESH";//刷新  ok
	public static final String EDIT_URL="EDIT_URL";//变更url(切换节目)
	public static final String CHECK_CONNECT="CHECK_CONNECT";//测试里链接 (检查网络)
	public static final String CORRECT_TIME="CORRECT_TIME";//校正时间
	public static final String INIT_LOAD="INIT_LOAD";//初始化
	public static final String UPDATE_VERSION="UPDATE_VERSION";//更新版本
	public static final String APP_SUFFIX="APP_SUFFIX";//更新版本后缀(app的位置)
	public static final String APPLY_REBOOT="APPLY_REBOOT";//应用重启
	public static final String DEFAULT_URL="DEFAULT_URL";//应用重启
	public static final int TIME_OUT=200;//超时时间
	public static String  SERVER_IP="SERVER_IP";//服务器的ip
	
	
	public static final String SYSCMDMAP="SysCmdMap";//application中存放的命令的map的key,如application.setAttribute("SysCmdMap", sysCommandService.reLoadCommand());
	
	public static final String SYSCMDLIST="SysCmdList";//application中存放的url的list的key,如application.setAttribute(Constant.SYSCMDLIST, sysCommandService.reloadUrl());
	
	
	
	//客户端的端口号
	public static final int CLIENT_PORT=12005;
	
	//服务端的端口号
	public static final int SERVER_PORT=12006;
	
	
	
	
	
	
	
	
	//常量map 
/*	public static Map<String,String> CONSTANT_MAP;*/
	
	//此代码块只执行一次
	/*static {
		CONSTANT_MAP= new HashMap<String,String>();
		CONSTANT_MAP.put("1", VALID_STATE);
		CONSTANT_MAP.put("0", UNVALID_STATE);
		CONSTANT_MAP.put("sd", SHUTDOWN);
		CONSTANT_MAP.put("rb", REBOOT);
		CONSTANT_MAP.put("tsd", TIMING_SHUTDOWN);
	}*/
	
}
