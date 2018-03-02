package com.zkhv.mkjview.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkhv.mkjview.entity.MkjView;
import com.zkhv.mkjview.entity.PatientVo;
import com.zkhv.mkjview.entity.T_config_doctor;
import com.zkhv.mkjview.entity.T_OPMKJ;
import com.zkhv.mkjview.service.MkjViewService;
import com.zkhv.tongbu.sqlServer.entity.T_OPImportSet;


/**
 * 门口页面展示
 * @author houzq
 *
 * com.zkhv.mkjview.controller
 */

@Controller
@RequestMapping("/mkjview")
public class MkjViewController {

	private Logger logger=Logger.getLogger(MkjViewController.class);
	
	@Autowired
	private MkjViewService mkjViewService;

	/**
	 * 将医生图片信息转换为.jpg格式,保存到本地
	 * @param path 保存路径
	 * @param T_config_doctor 医生信息
	 */
	private String setDorPic(String path,T_config_doctor doctor){
		String id = doctor.getId();
		Blob dr_pic = doctor.getDr_pic();
		InputStream in = null;
		FileOutputStream newFile = null;
		if(StringUtils.isNotBlank(id)&&dr_pic!=null){
			File file = new File(path, id+".jpg");
			try {
				int num = (int) dr_pic.length();
				byte buf[] = new byte[num];
				in = dr_pic.getBinaryStream();
				if(!file.exists()){
					file.createNewFile();
				}
				 newFile = new FileOutputStream(file);
				 while((num = in.read(buf)) != -1){
					 newFile.write(buf);
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}finally{//关流
				if(newFile!=null){
					try {
						newFile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(in != null){
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return "images/doctorheadimg/"+id+".jpg";
	}
	/**
	 * 
	 * @param mkj_code 门口机编号
	 * @return
	 */
	@RequestMapping("findMkjByCode.action")
	public  @ResponseBody String findMkjByCode(String mkj_code){
		logger.info("根据门口机code查询对应的配置信息,接收参数:mkj_code="+mkj_code);
		List<T_OPMKJ> mkjs = new ArrayList<T_OPMKJ>();
		try {
			if(StringUtils.isBlank(mkj_code)){
				String jsonStr = JSONArray.fromObject(mkjs).toString();
				return jsonStr;
			}
			T_OPMKJ t_opmkj = mkjViewService.findMkjByCode(mkj_code);
			Integer patientNum = mkjViewService.findPatientNum(t_opmkj.getOproom_id());
			t_opmkj.setPatientNum(patientNum);
			mkjs.add(t_opmkj);
			String jsonStr = JSONArray.fromObject(mkjs).toString();
			logger.info("根据门口机code查询对应的配置信息,接收参数:mkj_code="+mkj_code+",返回结果=="+jsonStr);
			return jsonStr;
		} catch (Exception e) {
			logger.error("根据门口机code查询对应的配置信息发生异常,接收参数:mkj_code="+mkj_code+",异常信息:",e);
		}
		return "[]";
	}
	
	/**
	 * 根据手术室id获取患者信息
	 * @param id 手术室id
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getPatients.action")
	public @ResponseBody String getPatients(String id,HttpServletRequest request){
		List<MkjView> viewList= new ArrayList<>();
		logger.info("根据手术室id获取患者信息,接收参数:id="+id);
		try {
			if(StringUtils.isBlank(id)){
				String jsonStr = JSONArray.fromObject(viewList).toString();
				return jsonStr;
			}
			List<PatientVo> list = mkjViewService.findPatientsByRoomId(id);
			if(list!=null && list.size()>0){
				for (PatientVo vo : list) {
					//只有状态不是手术结束后的状态并且 没有结束时间时才能在门口机页面上显示
//					if((vo.getOps_status()==null||vo.getOps_status()<25)&&vo.getOps_endtime()==null){勿删！！！！
						if(vo.getOps_status()!=null &&vo.getOps_status()==30){
							continue;
						}
						MkjView mkjView = new MkjView();
						mkjView.setId(vo.getId());//id
						mkjView.setArea_name(vo.getArea_name());//手术区域名称
						mkjView.setRoom_id(vo.getRoom_id());//手术是编号
						mkjView.setRoom_name(vo.getRoom_name());//手术室名称
						mkjView.setP_name(vo.getP_name());//患者姓名
						mkjView.setP_num(vo.getP_num());//患者住院号
						mkjView.setP_dept(vo.getP_dept());//患者所在科室
						mkjView.setP_sex(vo.getP_sex());//患者性别
						mkjView.setP_old(vo.getP_old());//患者年龄
						mkjView.setOps_doctor(vo.getOps_doctor());//主刀医生
						mkjView.setOps_mzdoctor(vo.getOps_mzdoctor());//麻醉医生
						mkjView.setOps_name(vo.getOps_anme());//手术名
						SimpleDateFormat dateFormat =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
						if(vo.getOps_starttime()!=null){
							mkjView.setOps_starttime(dateFormat.format(vo.getOps_starttime()));//开始时间
						}
						if(vo.getOps_endtime()!=null){
							mkjView.setOps_endtime(dateFormat.format(vo.getOps_endtime()));//结束时间
						}
						if(vo.getOps_stilltime()!=null){//持续时间
							String ops_stilltime = vo.getOps_stilltime().toString();
							mkjView.setOps_stilltime(ops_stilltime);
						}
						mkjView.setOps_status(vo.getOps_status());//手术状态
						mkjView.setOps_state(vo.getOps_state());//排班状态
						T_config_doctor doctor = mkjViewService.findDoctorById(vo.getOps_doctor_id());//查询出主刀医生
						T_config_doctor mzDoctor = mkjViewService.findDoctorById(vo.getOps_mzdoctor_id());//查询出麻醉医生
						
						//获取请求路径
						String path = request.getServletContext().getRealPath("/")+"/images/doctorheadimg/";
						if(doctor!=null){
							if(doctor.getDr_pic()!=null){
								mkjView.setOps_doctor_pic(setDorPic(path,doctor));//设置主刀医生头像路径
							}
							mkjView.setOps_doctitle(doctor.getDt_name());//设置主刀医生职称
						}
						if(mzDoctor!=null){
							if(mzDoctor.getDr_pic()!=null){
								mkjView.setOps_mzdoctor_pic(setDorPic(path,mzDoctor));//设置麻醉医生头像路径
							}
							mkjView.setOps_mzdoctitle(mzDoctor.getDt_name());//设置麻醉医生职称
						}
						viewList.add(mkjView);
//					}
				}
			}else{
				PatientVo vo = mkjViewService.findLastRecord(id);
				MkjView mkjView = new MkjView();
				mkjView.setId(vo.getId());//id
				mkjView.setArea_name(vo.getArea_name());//手术区域名称
				mkjView.setRoom_id(vo.getRoom_id());//手术是编号
				mkjView.setRoom_name(vo.getRoom_name());//手术室名称
				mkjView.setP_name(vo.getP_name());//患者姓名
				mkjView.setP_num(vo.getP_num());//患者住院号
				mkjView.setP_dept(vo.getP_dept());//患者所在科室
				mkjView.setP_sex(vo.getP_sex());//患者性别
				mkjView.setP_old(vo.getP_old());//患者年龄
				mkjView.setOps_doctor(vo.getOps_doctor());//主刀医生
				mkjView.setOps_mzdoctor(vo.getOps_mzdoctor());//麻醉医生
				mkjView.setOps_name(vo.getOps_anme());//手术名
				SimpleDateFormat dateFormat =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
				if(vo.getOps_starttime()!=null){
					mkjView.setOps_starttime(dateFormat.format(vo.getOps_starttime()));//开始时间
				}
				if(vo.getOps_endtime()!=null){
					mkjView.setOps_endtime(dateFormat.format(vo.getOps_endtime()));//结束时间
				}
				if(vo.getOps_stilltime()!=null){
					String ops_stilltime = vo.getOps_stilltime().toString();//持续时间
					mkjView.setOps_stilltime(ops_stilltime);
				}
				mkjView.setOps_status(vo.getOps_status());//手术状态
				mkjView.setOps_state(vo.getOps_state());//排班状态
				T_config_doctor doctor = mkjViewService.findDoctorById(vo.getOps_doctor_id());//查询出主刀医生
				T_config_doctor mzDoctor = mkjViewService.findDoctorById(vo.getOps_mzdoctor_id());//查询出麻醉医生
				
				//获取请求路径
				String path = request.getServletContext().getRealPath("/")+"/images/doctorheadimg/";
				if(doctor!=null){
					if(doctor.getDr_pic()!=null){
						mkjView.setOps_doctor_pic(setDorPic(path,doctor));//设置主刀医生头像路径
					}
					mkjView.setOps_doctitle(doctor.getDt_name());//设置主刀医生职称
				}
				if(mzDoctor!=null){
					if(mzDoctor.getDr_pic()!=null){
						mkjView.setOps_mzdoctor_pic(setDorPic(path,mzDoctor));//设置麻醉医生头像路径
					}
					mkjView.setOps_mzdoctitle(mzDoctor.getDt_name());//设置麻醉医生职称
				}
				viewList.add(mkjView);
				
			}
			String jsonStr = JSONArray.fromObject(viewList).toString();
			logger.info("根据手术室id获取患者信息,接收参数:id="+id+",返回结果=="+jsonStr);
			return jsonStr;
		} catch (Exception e) {
			logger.error("根据手术室id获取患者信息发生异常,接收参数:id="+id+",异常信息:", e);
		}
		return "[]";
	}
	
	/**
	 * 获取职称map(key为职称id,value为职称名称)
	 * 用于渲染医生职称(页面中暂时没用到)
	 * @return
	 */
	@RequestMapping("/findDocTitleMap.action")
	public @ResponseBody String findDocTitleMap(){
		Map<String, String> map =new HashMap<>();
		List<Map<String, String>> list = mkjViewService.findDocTitleMap();
		for (Map<String, String> map1 : list) {
			String key = map1.get("DT_ID");
			String value = map1.get("DT_Name");
			map.put(key, value);
		}
		String jsonStr = JSONArray.fromObject(map).toString();
		return jsonStr;
	}
	/**
	 * 获取sql查询条件列表
	 */
	@RequestMapping("/selectAllSql.action")
	public @ResponseBody String selectAllSql(){
		List<T_OPImportSet> sql = mkjViewService.selectAllSql();
		String jsonStr = JSONArray.fromObject(sql).toString();
		return jsonStr;
	}
	
	/**
	 * 根据id查询sql信息
	 * @param imp_id sql信息ID
	 * @param request
	 * @return
	 */
	@RequestMapping("/findSqlById.action")
	public String findSqlById(String imp_id,HttpServletRequest request){
		if(imp_id!=null&&imp_id!=""){
			T_OPImportSet importSet = mkjViewService.findSqlById(imp_id);
			request.setAttribute("importSet", importSet);
		}
		return "forward:/WEB-INF/sqlEditUI.jsp";
	}
	/**
	 * 修改sql信息数据
	 * @param importSet sql信息对象
	 * @return
	 */
	@RequestMapping("/sqlEdit.action")
	public @ResponseBody String sqlEdit(T_OPImportSet importSet){
		mkjViewService.updateSql(importSet);
		return "ok";
	}
}
