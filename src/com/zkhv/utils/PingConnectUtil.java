package com.zkhv.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Deprecated//ping速度太慢不适合
public class PingConnectUtil {
	
	 public static String ping(String ipAddress, int timeOut) {  
	        BufferedReader in = null;  
	        Runtime r = Runtime.getRuntime();  // 将要执行的ping命令,此命令是windows格式的命令  
	        String pingCommand = "ping " + ipAddress + " -w " + timeOut;  //w 超时时间
	        try {   
	        	// 执行命令并获取输出  
	            Process p = r.exec(pingCommand);   
	            if (p == null) {    
	                return "false";   
	            }
	            in = new BufferedReader(new InputStreamReader(p.getInputStream()));   // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数  
	            String line = null;  
	            Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)",    Pattern.CASE_INSENSITIVE);  
		        Matcher matcher = null;  
	            while((line = in.readLine()) != null) {
	            	matcher = pattern.matcher(line);
	                if(matcher.find()){
	                	return "true";
	                }
	            } 
	            //不匹配
	            return "false";
	        } catch (Exception ex) {   
	            ex.printStackTrace();   // 出现异常则返回假  
	            return "flase";  
	        } finally {   
	            try {    
	                in.close();   
	            } catch (IOException e) {    
	                e.printStackTrace();   
	            }  
	        }
	    }
	 
	 
	 
	 public static void main(String[] args) {
		System.out.println(ping("192.168.0,113", 200)); 
	}
	 

}
