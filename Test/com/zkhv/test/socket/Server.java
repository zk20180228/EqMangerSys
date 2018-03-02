package com.zkhv.test.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import com.zkhv.log.entity.LogInfo;
import com.zkhv.log.service.LogInfoService;

//服务端
public class Server {

	public static void main(String[] args) throws Exception {

		// 建立服务端
		ServerSocket serverSocket = new ServerSocket(12022);

		
		while (true) {
			Socket socket = serverSocket.accept();
			// 处理接受的数据
			InputStream inputStream = socket.getInputStream();
			// 转换为字符流
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String acceptMsg = reader.readLine();
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write(acceptMsg.getBytes("utf-8"));
			outputStream.flush();
		}
		
	}

}
