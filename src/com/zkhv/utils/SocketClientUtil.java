package com.zkhv.utils;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.zkhv.constant.Constant;
import com.zkhv.equipment.entity.Equipment;
import com.zkhv.equipment.service.EquipmentService;

//socket客户端工具类
//给我Ip,端口号(固定),数据，我可以写信息给对应ip的移动端
public class SocketClientUtil {
	
	public static Socket socket;
	public static ServerSocket serverSocket;
	//向客户端发送信息
	public static void  sendMessage(String ip,/*int port,*/ String command) throws Exception{
			
			//建立客户端
			socket= new Socket();
			//设置超时时间
			socket.connect(new InetSocketAddress(InetAddress.getByName(ip), Constant.CLIENT_PORT), Constant.TIME_OUT);
			//得到客户端输出流对象
			OutputStream outputStream = socket.getOutputStream();
			//写给服务端
			outputStream.write(command.getBytes("utf-8"));
		
			//关闭资源
			outputStream.close();
			socket.close();
			
	}
	
	
	//公共的方法=====这个方法是封装前后台的方法，暂时没时间做了看方法内容就能知道干嘛的
	@Deprecated
	public String PBMethod(EquipmentService equipmentService,String ip,String[] eids,String command){
			
		/*	//建立客户端
			socket= new Socket(InetAddress.getByName(ip), Constant.port);
			//得到客户端输出流对象
			OutputStream outputStream = socket.getOutputStream();
			//写给服务端
			outputStream.write(command.getBytes("utf-8"));
			//关闭资源
			outputStream.close();
			socket.close();*/
		
		
			String flag = "全部成功！";
			StringBuffer sb = new StringBuffer();
			Equipment equipment=null;
			if (eids!=null&&eids.length>0) {
				ip = "";
				//遍历ip地址
				for (String eid : eids) {
					//1.查得该机ip
					equipment= equipmentService.selectByPrimaryKey(eid);
					//2.发送命令
					try {
						SocketClientUtil.sendMessage(equipment.getEip(), command);
					}catch (ConnectException e1) {
						//将不成功的ip加入到sb中
						sb.append(equipment.getEid()+",");
						e1.printStackTrace();
					} catch (Exception e) {
						//将不成功的ip加入到sb中
						sb.append(equipment.getEid()+",");
						e.printStackTrace();
					}
				}
			}
			return sb.length()>0?sb.toString()+"号机未成功!":flag;
		}
		

}
