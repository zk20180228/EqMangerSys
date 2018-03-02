package com.zkhv.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class LogUtil {
	
	
	private static Log log;
	
	
	public static Log getLog(){
			return LogFactory.getLog(LogUtil.class);
	}
	


}
