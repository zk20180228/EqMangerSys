<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String mkj_code=request.getParameter("mkj_code");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>门口机页面</title>
<link rel="stylesheet" type="text/css" href="css/operation.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var arr=['准备手术','入手术室','麻醉开始','手术开始','','手术结束','麻醉结束'];
	var a = 0;//起始患者ID
	var b = 0;//总患者数量
	var c = 5000;//切屏间隔
	var pData=new Array();//存放预加载的数据
	var timeIndex = 0;
	var roomId='';
	var time = "";
	var times = 0;
	var starttime = "";
	var endtime = "";
	var code='${param.mkj_code}';
	$(function(){
		var url = '${pageContext.request.contextPath}/mkjview/findMkjByCode.action?mkj_code='+code;
		$.post(url,function(data){
			$.each(data,function(i,n){
				$("#mkj_prompt").html(n.mkj_prompt);
				c =1000* n.mkj_cutscreen_fre;
				roomId=n.oproom_id;//手术室id
				$("#room_name").html(n.room_name+'<span id="ops_stauts1">未安排</span>');
				showMsgIcon();
			});
		},"json");
		showLeftTime();
		window.setInterval(showLeftTime, 1000); 
	});
	
	function showMsgIcon(){
		if(a==b){
			var url = '${pageContext.request.contextPath}/mkjview/getPatients.action?id='+roomId;
			$.post(url,function(data){
				pData=data;
				b=data.length;
			    a=0;
				showValue();
				if(a<b){
				    a++;
				}
			},"json");
		}
		if(pData.length-1>=a){
			showValue();
			a++;
			}
		setTimeout("showMsgIcon()", c);
	}
	
	function showValue(){
		var n=pData[a];
		if(n!=null){
			$("#ops_doctor").html(n.ops_doctor);
			$("#ops_mzdoctor").html(n.ops_mzdoctor);
			$("#p_name").html(n.p_name);
			$("#p_sex").html(n.p_sex);
			$("#p_old").html(n.p_old);
			$("#p_dept").html(n.p_dept);
			if(n.ops_name!=null && n.ops_name.length<=12){
				$("#ops_name").html(n.ops_name);
			}else{
				$("#ops_name").html('<marquee scrollamount="5" scrolldelay="80" direction="left" align="left" width="100%" >'+n.ops_name+'</marquee>');
			}
			$("#ops_starttime").html(n.ops_starttime);
			$("#ops_endtime").html(n.ops_endtime);
			$("#ops_zzTitle").html(n.ops_doctitle);
			$("#ops_mzTitle").html(n.ops_mzdoctitle);
			if(typeof(n.ops_stilltime)!='undefined'&& n.ops_stilltime!=""){
				timeIndex= parseInt(n.ops_stilltime);
			}
			setTime();
			if(n.ops_state==1){
				$("#ops_stauts1").html("已安排");
			}
			if(n.ops_state==3){
				$("#ops_stauts1").html("已入室");
			}
			if(n.ops_status==null||typeof(n.ops_status)=='undefined'){
				$("#ops_stauts2").html("准备手术");
			}else{
				var s=arr[n.ops_status/5];
				$("#ops_stauts2").html(s);
			}
			var u='<%=basePath %>images/no_photo2.jpg';
			if(n.ops_doctor_pic!=null && n.ops_doctor_pic.length>0){
			    $("#ops_doctor_pic").attr("src",n.ops_doctor_pic);
			}else{
				$("#ops_doctor_pic").attr("src",u);
			}
			if(n.ops_mzdoctor_pic!=null && n.ops_mzdoctor_pic.length>0){
				$("#ops_mzdoctor_pic").attr("src",n.ops_mzdoctor_pic);
			}else{
				$("#ops_mzdoctor_pic").attr("src",u);
			}
		}
		
	}
	
	
	function showLeftTime(){
		var now=new Date();
		var strDate;
		var weekday = now.getDay();
		    switch (weekday) {
		        case 0:
		             strDate = "星期日";
		             break;
		        case 1:
		            strDate = "星期一";
		            break;
		        case 2:
		             strDate = "星期二";
		             break;
		        case 3:
		            strDate = "星期三";
		            break;
		        case 4:
		            strDate = "星期四";
		            break;
		        case 5:
		           strDate = "星期五";
		            break;
		        case 6:
		            strDate = "星期六";
		             break;
		    }
		var year=now.getFullYear();
		var month=now.getMonth()< 10 ? "0" + (now.getMonth()+1) : (now.getMonth()+1);
		var day=now.getDate() < 10 ? "0"+ now.getDate() : now.getDate();
	    var hour = now.getHours() < 10 ? "0" + now.getHours() : now.getHours();
	    var minute = now.getMinutes() < 10 ? "0" + now.getMinutes() : now.getMinutes();
	    var second = now.getSeconds() < 10 ? "0" + now.getSeconds() : now.getSeconds();
		$("#ymd").html(""+year+"年"+month+"月"+day+"日 ");
		$("#xingqiID").html(strDate);
		$("#hmsID").html(hour+":"+minute+":"+second);
		time = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
		
	}
	 function setTime(){
		 var hour = parseInt(timeIndex / 60);    // 计算时 
		 var minutes = parseInt((timeIndex % 60) % 60);    // 计算分 
		 hour = hour < 10 ? "0" + hour : hour;
		 minutes = minutes < 10 ? "0" + minutes : minutes;
		 $("#ops_stilltime").html(hour + "时" + minutes + "分");
		 }
</script>

</head>
<body>
	<div class="oper">
		<h2 class="logo"><img src="images/logo1.jpg" alt="郑州大学第一附属医院"></h2>
		<div class="content">
			<h3 id="room_name" class="title">手术室</h3>
			<div class="doc_pat">
				<dl>
					<dt><img id="ops_doctor_pic" src='${pageContext.request.contextPath}/images/no_photo2.jpg' onerror="this.src='<%=basePath %>images/no_photo2.jpg'" style="height:400px;width: 300px"/></dt>
					<dd>
						<h2 id="ops_doctor"></h2>
						<h3>主治医生</h3>
						<h4 id="ops_zzTitle" ></h4>
					</dd>
				</dl>
				<dl>
					<dt><img id="ops_mzdoctor_pic" src='<%=basePath %>/images/no_photo2.jpg' style="height:400px;width: 300px" onerror="this.src='<%=basePath %>images/no_photo2.jpg'"/></dt>
					<dd>
						<h2 id="ops_mzdoctor"></h2>
						<h3>麻醉医生</h3>
						<h4 id="ops_mzTitle"></h4>
					</dd>
				</dl>
				<ul>
					<li class="tit"><span>患者</span><strong id="p_name"></strong><span id="p_sex"></span><span class="age" id="p_old"></span></li>
					<li class="con"><span class="span1">科&nbsp;室&nbsp;名：</span><i id="p_dept"></i></li>
					<li class="con"><span class="span1">手&nbsp;术&nbsp;名：</span><i id="ops_name"></i></li>
					<li class="con"><span>手术状态：</span><i class="red" id="ops_stauts2"></i></li>
					<li class="con"><span>开始时间：</span><i id="ops_starttime"></i></li>
					<li class="con"><span>持续时间：</span><i id="ops_stilltime"></i></li>
					<li class="con"><span>预估结束：</span><i id="ops_endtime"></i></li>
				</ul>
			</div>
		</div>
		<div class="operbottom">
			<div class="martext">
				<marquee scrollamount="3" scrolldelay="80" direction="left" align="left" width="100%" id="mkj_prompt"></marquee>
			</div>
			<div class="date">
				<p id="ymd"></p>
				<p id="xingqi" class="p1">
					<span id="xingqiID"></span>
					<span id="hmsID"></span>
				</p>
			</div>
		</div>
	</div>
</body>
</html>

