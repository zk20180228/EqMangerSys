package com.zkhv.test.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

//客户端
public class Client {
	
	public static void main(String[] args) throws Exception {
		//得到标准的输入流
		//InputStream in=new InputStream(); 
		 	
		//建立socket的客户端服务
		Socket socket = new Socket(InetAddress.getByName("192.168.0.30"), 12021);
		
		String str = "ORIS_Load_Url,http://news.163.com/";//reader.readLine()
		
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(str.getBytes("utf-8"));
		outputStream.flush();
		
		while(true){//获取服务端的信息
			InputStream in  = socket.getInputStream();
			//包装一下字节流
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			System.out.println(reader.readLine()+"==================================================================");
		}
		
	} 
	
	
	
	@Test
	public void getLocalIP() throws UnknownHostException{
	
		InetAddress localHost = InetAddress.getLocalHost();
		System.out.println(localHost.getHostAddress());
		
		
		
	}
		
		
		
}
