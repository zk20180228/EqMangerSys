package com.zkhv.equipment.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkhv.common.BaseAction;
import com.zkhv.constant.Constant;
import com.zkhv.equipment.entity.Equipment;
import com.zkhv.equipment.service.EquipmentService;
import com.zkhv.mkjview.service.T_OPRoomService;
import com.zkhv.utils.PageUtil;
import com.zkhv.utils.SocketClientUtil;

//门口机控制器
@Controller
@RequestMapping("/equipment")
public class EquipmentAction  extends BaseAction{
	
	@Resource
	private EquipmentService equipmentService;
	
	@Resource
	private T_OPRoomService t_OPRoomService;
	
	//刷新  produces = "text/html;charset=UTF-8"设置响应的json编码
	@ResponseBody
	@RequestMapping(value = "/refresh",produces = "text/html;charset=UTF-8")
	public String refresh(String[] eids,String sendData) {
		
		return this.PBMethod(eids, Constant.REFRESH,sendData);
	}
	
	//切换节目
	@ResponseBody
	@RequestMapping(value="/editUrl" ,produces = "text/html;charset=UTF-8")
	public String editUrl(String[] eids, HttpServletRequest request) throws Exception {
		/*sendData="https://www.baidu.com/";*/
		//先更新page_url
		//如果输入的是中文，乱码解决
		String sendData = new String(request.getParameter("sendData").getBytes("iso-8859-1"),"utf-8").trim();
		if(StringUtils.isNotBlank(sendData)){
			for(String id :eids){
				equipmentService.updatePageUrl(id,sendData);
			}
		}
		
		return this.PBMethod(eids, Constant.EDIT_URL,sendData);
	}
	
	//检测网络
	@ResponseBody
	@RequestMapping(value="/checkConnect",produces = "text/html;charset=UTF-8")
	public String checkConnect(String[] eids, String sendData) {
		return this.PBMethod(eids, Constant.CHECK_CONNECT,sendData);
	}
	
	//矫正时间
	@ResponseBody
	@RequestMapping(value="/correctTime",produces = "text/html;charset=UTF-8")
	public String correctTime(String[] eids, String sendData) {
		sendData=new SimpleDateFormat("yy/MM/dd HH:mm:ss").format(new Date());
		//固定格式+yy/MM/dd HH:mm:ss
		return this.PBMethod(eids, Constant.CORRECT_TIME,sendData);
	}
	
	//恢复默认(初始化)
	@ResponseBody
	@RequestMapping(value="/initLoad" ,produces = "text/html;charset=UTF-8")
	public String initLoad(String[] eids, String sendData,HttpServletRequest request) throws UnknownHostException {
		Map<String,String> map = (Map<String, String>) application.getAttribute(Constant.SYSCMDMAP);
		
		sendData=request.getScheme()+ "://"
				+map.get(Constant.SERVER_IP)+ ":" 
				+ request.getServerPort()+request.getContextPath()
				+ "/"+map.get(Constant.DEFAULT_URL); 
				//先更新page_url
				if(StringUtils.isNotBlank(sendData)){
					for(String id :eids){
						equipmentService.updatePageUrl(id,sendData);
					}
				}
		return this.PBMethod(eids, Constant.INIT_LOAD,sendData);
	}
	
	
	//版本更新
	@ResponseBody
	@RequestMapping(value="/updateVersion",produces = "text/html;charset=UTF-8")
	public String updateVersion(String[] eids, String sendData,HttpServletRequest request) throws UnknownHostException  {
		Map<String,String> map = (Map<String, String>) application.getAttribute(Constant.SYSCMDMAP);
		sendData=request.getScheme() + "://"
				+map.get(Constant.SERVER_IP)+ ":" 
				+ request.getServerPort()
				+ "/"+map.get(Constant.APP_SUFFIX);       //"http://192.168.0.30:8080/APK/ORIS.apk";
		return this.PBMethod(eids, Constant.UPDATE_VERSION,sendData);
	}
	
	//应用重启
	@ResponseBody
	@RequestMapping(value="/applyReboot",produces = "text/html;charset=UTF-8")
	public String applyReboot(String[] eids, String sendData) {
		return this.PBMethod(eids, Constant.APPLY_REBOOT,sendData);
	}
	
	
	//重启
	@ResponseBody
	@RequestMapping(value="/reboot",produces = "text/html;charset=UTF-8")
	public String reboot(String[] eids, String sendData) {
		return this.PBMethod(eids, Constant.REBOOT,sendData);
	}
	
	//服务启动的时候刷新一次
	@PostConstruct
	public void startUprRefresh() {
			
		List<Equipment> list = equipmentService.findAll();
			int length= list.size();
			String[] eids= new String[length];
			for(int i=0;i<length;i++){
				eids[i]=list.get(i).getEid();
			}
			
			 this.PBMethod(eids, Constant.REFRESH,"");
		}
	

	//关机
	
	//开机
	
	
	//定时开关机
	
	
	
	//删除
	@ResponseBody
	@RequestMapping(value="/deleteEquipment",produces ="text/html;charset=UTF-8")
	public String deleteEquipment(String[] eids){
		
		String msg="true";
		if(eids!=null&&eids.length>0){
			for(String id :eids){
				equipmentService.deleteByPrimaryKey(id);
			}
		}
		return msg;
	}
	
	//修改切屏频次
	@ResponseBody
	@RequestMapping(value="/editCutScreen",produces ="text/html;charset=UTF-8")
	public  String editCutScreen(String[] eids,String sendData) {
			
		int Data=Integer.valueOf(sendData);
		String flag="false";
		if(Data>0){
			if(eids!=null&&eids.length>0){
				for(String id :eids){
					equipmentService.editCutScreen(id,Data);
				}
				this.PBMethod(eids, Constant.REFRESH,"");//切换频次后刷新
				flag="true";
			}
		}

		return flag;
	}
	
	//修改提示信息
	@ResponseBody
	@RequestMapping(value="/editPrompt",produces ="text/html;charset=UTF-8")
	public  String 	editPrompt(String[] eids,String sendData) throws Exception {
		String data= URLDecoder.decode(sendData,"utf-8");//url解码
		sendData=new String(data.getBytes("iso-8859-1"),"utf-8");//编码
		//解决url后边跟中文参数的乱码问题
		String flag="false";
			if(eids!=null&&eids.length>0&&StringUtils.isNotBlank(sendData)){
				for(String id :eids){
					equipmentService.editPrompt(id,sendData);
				}
				flag="true";
			}

		return flag;
	}
	
	
	
	//编辑(页面)
	@RequestMapping("/equipmentEditUI")
	public String equipmentEditUI(String eid,HttpServletRequest request){
		
		if(eid!=null&&eid.length()>0){
			request.setAttribute("equipment", equipmentService.selectByPrimaryKey(eid));
			request.setAttribute("op_room", t_OPRoomService.findAll());
		}
		return "forward:/WEB-INF/equipmentEditUI.jsp";
	}
	
	//保存编辑----------》ip地址不能重,门口机编号不能重(保存编辑时的校验)
	@ResponseBody
	@RequestMapping(value="/equipmentEdit",produces ="text/html;charset=UTF-8")
	public String equipmentEdit(Equipment equipment){
		
		String flag="false";
		if(equipment!=null&&StringUtils.isNotBlank(equipment.getEid())){
			equipmentService.update(equipment);
			flag="true";
		}
		
		return flag;
	}
		
	
	
	//添加(页面)
	@RequestMapping("/equipmentAddUI")
	public String equipmentAddUI(HttpServletRequest request){
		
		request.setAttribute("op_room", t_OPRoomService.findAll());
		
		return "forward:/WEB-INF/equipmentAddUI.jsp";
	}
	
	//保存添加----------》ip地址不能重,门口机编号不能重(保存编辑时的校验)
	@ResponseBody
	@RequestMapping(value="/equipmentAdd",produces = "text/html;charset=UTF-8")
	public String equipmentAdd(Equipment equipment){
		String flag="false";
			if(equipment!=null){
				//生成32位的UUID
				String uuidStr = UUID.randomUUID().toString().replace("-", "");
				equipment.setEid(uuidStr);
				equipmentService.insert(equipment);
				flag="true";
			}
		return flag;	
	}
	
	
	//异步校验门口机(门口机)唯一性
	//校验思路:当前存在id,说明是修改，当前不存在是添加
	@ResponseBody
	@RequestMapping(value="/checkEquipment",produces = "text/html;charset=UTF-8")
	public String checkEquipment(Equipment equipment){

		String msg="false";
		//添加时校验
		int i=equipmentService.checkIsUnique(equipment);
		if(i!=0){
			msg="true";
		 }
		
		return msg;
	}
	

	
	//加载门口机列表
	@ResponseBody
	@RequestMapping("/equipmentList")
	public Object equipmentList(PageUtil<Equipment> page,Equipment equipment){
		page.setEntity(equipment);
		PageUtil<Equipment> p = equipmentService.selectPageByDyc(page);
		return p.getMap();
	}
	
	
	
	
	//公共的方法
	public String PBMethod(String[] eids,String command, String sendData){
		
		Map<String,String> map = (Map<String, String>) application.getAttribute(Constant.SYSCMDMAP);
		command=sendData==null||sendData.trim().equals("")?map.get(command):map.get(command)+sendData;
		Equipment equipment=null;
		if (eids!=null&&eids.length>0) {
			String ip = "";
			for (String eid : eids) {
				//1.查得该机ip
				equipment= equipmentService.selectByPrimaryKey(eid);
				//2.发送命令
				if(equipment!=null&&StringUtils.isNotBlank(equipment.getEip())){
				try {
					//把当前门口机的编号发送过去
					SocketClientUtil.sendMessage(equipment.getEip(), command+","+equipment.getEnumber());
					//判断当前设备的连接状态，如果是未连接，改变为链接
					String state = equipment.getEstate();
					if(Constant.UN_CONNECT.equals(state)){
						equipment.setEstate(Constant.ALREADY_CONNECT);
						//未连接---》已连接
						equipmentService.updateEstate(equipment);
					}
				} catch (IOException e) {//超时，未连接
					equipment.setEstate(Constant.UN_CONNECT);
					equipmentService.updateEstate(equipment);
				} catch (Exception e) {
					e.printStackTrace();
					}
				}
			}
		}
		return "true";
	}
	

}
