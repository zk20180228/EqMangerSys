package com.zkhv.test.ping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PingTest01 {

	public void TestPingCmd() {
		// 获取对当前程序的运行时对象
		Runtime runtime = Runtime.getRuntime();
		// 创建一个本机进程
		Process process = null;
		// 返回行信息
		String line = null;
		// 输入流
		InputStream is = null;
		// 字节流
		InputStreamReader isr = null;

		BufferedReader br = null;
		String ip = "192.168.0.30";
		boolean res = false;// 结果
		try {
			// 执行ping命令
			process = runtime.exec("ping " + ip);
			// 获取进程的输入流对象
			is = process.getInputStream();
			// 把输入流转换成字符流
			isr = new InputStreamReader(is);
			// 包装
			br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				if (line.contains("TTL")) {
					res = true;
					break;
				}
			}
			is.close();
			isr.close();
			br.close();
			if (res) {
				System.out.println("ping 通  ...");

			} else {
				System.out.println("ping 不通...");
			}

		} catch (IOException e) {
			System.out.println(e);
			runtime.exit(1);
		}

	}

}
