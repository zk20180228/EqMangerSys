package com.zkhv.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.zkhv.constant.Constant;
import com.zkhv.log.entity.LogInfo;
import com.zkhv.log.service.LogInfoService;

//serverSocket服务端[TOMCAT启动后一直开启]
@Component
public class ServerSocketThread extends Thread implements ApplicationContextAware{
	
	private static ApplicationContext appCtx;
	private ServerSocket serverSocket = null;  
	
	 public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	        appCtx = applicationContext;
	    }
	
	 public  ApplicationContext getApplicationContext() {
	        return appCtx;
	    }
	
	 public  Object getBean(String beanName) {
	        return appCtx.getBean(beanName);
	    }
	 
	
   
	public ServerSocketThread() {
    	
	}
	
    public ServerSocketThread(ServerSocket serverScoket){  
        try {  
            if(null == serverSocket){  
                this.serverSocket = new ServerSocket(Constant.SERVER_PORT);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }  
      
    public void run(){  
        while(!this.isInterrupted()){  
            try {  
                Socket socket = serverSocket.accept();  
                if(null != socket && !socket.isClosed()){     
                 //处理接受的数据  
                InputStream inputStream = socket.getInputStream();
            	//转换为字符流
            	BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            	String acceptMsg =null;
            	while((acceptMsg=reader.readLine())!=null){
            		if(acceptMsg.contains("Exception:")){
            			LogInfo logInfo = new LogInfo();
            			logInfo.setLogContent(acceptMsg);
            			logInfo.setLogTime(new Timestamp(new Date().getTime()));
            			String uuidStr = UUID.randomUUID().toString().replace("-", "");
            			logInfo.setLogId(uuidStr);
            			//得到Exception:得到指定子字符串第一次出现的位置
            			int index = acceptMsg.indexOf("Exception:");
            			logInfo.setRemark(acceptMsg.substring(0, index));//用于记录异常机器的编号
            			LogInfoService logInfoService = (LogInfoService) this.getBean("logInfoService");
            			logInfoService.insert(logInfo);
            			}
            		}
                }  
                  
            }catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
      
      
    public void closeSocketServer(){  
       try {  
            if(null!=serverSocket && !serverSocket.isClosed())  
            {  
             serverSocket.close();  
            }  
       } catch (IOException e) {  
        e.printStackTrace();  
       }  
     }
    
    
    


	
	
	
	
}
