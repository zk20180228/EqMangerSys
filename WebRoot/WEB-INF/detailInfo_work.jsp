<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">   
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery-1.7.2.min.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/easyui-lang-zh_CN.js"></script>
<body style="background-color:#AFEEEE">
	<form id="fm" method="post" action="#" >   
		<table border="1" cellpadding="0" cellspacing="0" align="center">
			<tr align="center"><td>手术排班编号</td><td>${w.id}</td></tr>
			<tr align="center"><td>手术室编号</td><td>${w.room_id}</td></tr>
			<tr align="center"><td>手术室名称</td><td>${w.room_name}</td></tr>
			<tr align="center"><td>患者姓名</td><td>${w.p_name}</td></tr>
			<tr align="center"><td>手术名称</td><td>${w.ops_anme}</td></tr>
			<tr align="center"><td>手术状态</td>
				<td>
					<c:choose>
						<c:when test="${w.ops_state==1}">已安排</c:when>
						<c:when test="${w.ops_state==3}">已入室</c:when>
					</c:choose> 
				</td>
			</tr>
			<tr align="center"><td>手术时间</td><td><fmt:formatDate value="${w.ops_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td></tr>
			<tr align="center"><td>患者住院号</td><td>${p_num}</td></tr>
			<tr align="center"><td>患者所在科室</td><td>${w.p_dept}</td></tr>
			<tr align="center"><td>患者性别</td><td>${w.p_sex}</td></tr>
			<tr align="center"><td>患者年龄</td><td>${w.p_old}</td></tr>
			<tr align="center"><td>患者诊断</td><td>${w.p_diagnose}</td></tr>
			<tr align="center"><td>主刀医生</td><td>${w.ops_doctor}</td></tr>
			<tr align="center"><td>主刀医生编号</td><td>${w.ops_doctor_id}</td></tr>
			<tr align="center"><td>麻醉医生</td><td>${w.ops_mzdoctor}</td></tr>
			<tr align="center"><td>麻醉医生编号</td><td>${w.ops_mzdoctor_id} </td></tr>
			<tr align="center"><td>区域名称</td><td>${w.area_name}</td></tr>
			<tr align="center"><td>信息更新时间</td><td><fmt:formatDate value="${w.update_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td></tr>
			<tr>
				<td align="center" colspan="2"><a href="#" class="easyui-linkbutton" id="btn" iconCls="icon-ok" style="width:30%;height:32px" >关闭</a></td>
			</tr>
		</table>
	</form>  
		
</body>
	<script type="text/javascript">
		$("#btn").click(function(){
			//关闭当前窗口
		    parent.$("#win").window("close");		
			});
	</script>
	
</html>