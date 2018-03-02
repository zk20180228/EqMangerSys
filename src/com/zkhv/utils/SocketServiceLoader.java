package com.zkhv.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SocketServiceLoader implements ServletContextListener{
	
		private ServerSocketThread serverSocketThread;  
	      
	    public void contextDestroyed(ServletContextEvent arg0) {  
	        if(null!=serverSocketThread && !serverSocketThread.isInterrupted())  
	           {  
	        	serverSocketThread.closeSocketServer();  
	            serverSocketThread.interrupt();  
	           }  
	    }  
	  
	    
	    public void contextInitialized(ServletContextEvent arg0) {  
	    	
	    	
	    	
	    	
	        if(null==serverSocketThread)  
	        {  
	         //新建线程类  
	        serverSocketThread=new ServerSocketThread(null);  
	        //启动线程  
	        serverSocketThread.start();  
	        }  
	    }  


}
