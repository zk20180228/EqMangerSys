<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
		<title>手术门口机维护系统</title>
		<%@ include file="/common/metas.jsp"%>
</head>
<body>

 <div id="loader-wrapper">
    <div id="loader"></div>
    <div class="loader-section section-left"></div>
    <div class="loader-section section-right"></div>
    <div class="load_title">手术门口机维护系统<br><span>Loading...</span></div>
</div> 
<div id="cc" class="easyui-layout" data-options="fit:true">   
    <div data-options="region:'north'"  style="height:14%;font-size:20;width: 100%;background-image: url('../images/title.png')">
    	<font style="font-size:22px">欢迎你,</font><font style="color:red;font-size:18px">${SYS_USER.uname}</font>&nbsp;<a href="${pageContext.request.contextPath}/user/logout.action" style='font-size:12px;text-decoration:none; font-weight:bold;'>退出</a><h1 align="center"><a style="font-size: 50px">手术门口机维护系统</a></h1><br/>
	</div>
	<div data-options="region:'west',border:false" style="width:15%;height:90%;background-color:#778899">
		<div id="aa" class="easyui-accordion"  data-options="fit:true" style="background-color:#778899;">  
			<div title="用户管理"   style="overflow:auto;padding:20px;background-color:#E6E6FA" >  <!-- data-options="selected:true" -->
				<h3><a href="#" style="font-size:16px ;text-decoration:none;" id="user" onClick="acceptsName()">用户列表</a></h3><br/> <!-- javascript:acceptsName();void(0) -->
			</div>
			<div title="门口机管理" style="padding:10px;background-color:#E6E6FA" >
				<h3><a href="#" style="font-size: 16px ;text-decoration:none;" id="mkj" onClick="display()">门口机列表</a></h3><br/>  
				<!-- <h3><a href="#" style="font-size: 16px" id="btn" onClick="set()">门口机配置</a></h3><br/>  -->      				  
			</div> 
			<div title="本地库手术信息" style="padding:10px;background-color:#E6E6FA" >
				<h3><a href="#" style="font-size: 16px ;text-decoration:none;" id="btn" onClick="
				()">本地库手术患者信息</a></h3><br/>
				<h3><a href="#" style="font-size: 16px ;text-decoration:none;" id="oproom" onClick="opRoomList()">本地库手术室信息</a></h3><br/>
				<h3><a href="#" style="font-size: 16px ;text-decoration:none;" id="opwork" onClick="opWorkList()">本地库手术排班信息</a></h3><br/>
			</div> 
			
			<div title="目标库手术信息" style="padding:10px;background-color:#E6E6FA" >
				<h3><a href="#" style="font-size: 16px ;text-decoration:none;" id="or_btn" onClick="OrPatientList()">目标库手术患者信息</a></h3><br/>  
				<h3><a href="#" style="font-size: 16px ;text-decoration:none;" id="or_oproom" onClick="OrOpRoomList()">目标库手术室信息</a></h3><br/>
				<h3><a href="#" style="font-size: 16px ;text-decoration:none;" id="or_opwork" onClick="OrOpWorkList()">目标库手术排班信息</a></h3><br/>
			</div> 
			
			 <div title="日志信息" style="padding:10px;background-color:#E6E6FA" >
				<h3><a href="#" style="font-size: 16px ;text-decoration:none;" id="logInfo" onClick="LogInfoList()">日志信息</a></h3><br/>  
			 </div> 
			
			 <div title="语句维护" style="padding:10px;background-color:#E6E6FA" >
				<h3><a href="#" style="font-size: 16px ;text-decoration:none;" id="updateOrSelectSql" onClick="updateOrSelectSql()">语句维护</a></h3><br/>  
			 </div> 
			
		</div>
	</div>  
	<div data-options="region:'center'" style="width: 85%;height: 90% ;background-color:#66FFCC">
		<div id="tabs" class="easyui-tabs" style="width: 100%; height: 100%;" data-options="border:false,tabHeight:'24',fit:true">
		</div>
	</div>
</div>

	<div id="win"></div>

	<!-- 鼠标右击菜单 -->
	<div id="mm" class="easyui-menu cs-tab-menu" style="width: 100px;">
		<div id="mm-tabupdate">刷新</div>
		<div id="mm-tabclose">关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div id="mm-tabcloseall">关闭全部</div>
	</div>

</body>
<script type="text/javascript">

 $(window).load(function(){
    $('body').addClass('loaded');
    $('#loader-wrapper .load_title').remove();
});
 $(function(){
	tabCloseEven();
	
});
 //绑定右键菜单事件
 function tabCloseEven(){
	 $(".tabs-header").bind('contextmenu',function(e){
			e.preventDefault();
			$('#mm').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		});
	 //刷新
	 $('#mm-tabupdate').click(function() {
		 	//由于选项卡href=null，所以只能刷新datagrid组件
 	        var currtab_title = $('#tabs').tabs('getSelected');
			//得到   content       
			var re_content = currtab_title.panel('options').content;
			//截取字符串，得到datagrid的id
			var startIndex = $.trim(re_content).lastIndexOf("='");
			var lastIndex = $.trim(re_content).lastIndexOf("'");
			var i_d = $.trim(re_content).substring(startIndex+2, lastIndex);//包头不包尾
			//刷新datagrid
			$('#'+i_d).datagrid('load');
		
	 });

	//关闭当前
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#tabs').tabs('getSelected');
		//不存在遍历问题，根据索引关闭
		var index = $('#tabs').tabs('getTabIndex', currtab_title);
		$('#tabs').tabs('close', index);
	});

	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
		var currtab_title = $('#tabs').tabs('getSelected');
		var title = currtab_title.panel('options').title;
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			if (t != title)
				$('#tabs').tabs('close', t);
		});
	});

	//全部关闭
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			$('#tabs').tabs('close', t);
		});
	});
}
	//操作门口机的js的方法的抽取,url为可变，可带参数
	function Option(url) {
		var eids = new Array();
		var checkeds = $('#equipment_datagrid').datagrid('getChecked');
		for (var i = 0; i < checkeds.length; i++) {
			eids.push(checkeds[i].eid)
		}
	if (eids.length != 0) {
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/" + url,
				data : "eids=" + eids,
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(msg) {
					if (msg == "true") {
						//刷新当前页面
						$('#equipment_datagrid').datagrid('load');
					}
				}
			});
		}else{
			$.messager.confirm('确认', "请至少选择一个门口机!", function(r) {
				if (r) {
				}
			});
		}

	}
	//用户列表
	function acceptsName() {
		var url = "${pageContext.request.contextPath}/user/userList.action";
		//如果存在选中用户列表选项卡
		if ($('#tabs').tabs('exists', '用户列表')) {
			$('#tabs').tabs('select', '用户列表');
			//刷新当前页面
			$('#user_datagrid').datagrid('load');

			return;
		}
		//如果不存在则创建
		$('#tabs').tabs('add',{
						title : '用户列表',
						selected : true,
						// 新内容的URl
						content : "用户账号:<input id='uaccount' style='width:140px;height:20px'></input><a href='#'  style='font-size:14px;text-decoration:none; font-weight:bold;' onclick='doSearchUser()'>搜索</a><table id='user_datagrid' ></table>",
						closable : true
					});

		//加载用户列表数据
		if ($('#tabs').tabs('exists', '用户列表')) {
			$('#tabs').tabs('select', '用户列表');
			//创建datagrid组件
			$('#user_datagrid').datagrid(
							{
								url : '${pageContext.request.contextPath}/user/userList.action',
								fitColumns : true, //自适应
								pagination : true, //分页工具栏
								/*  fit:true,  *///满填充
								idField : "uid",//指定id字段为行标记字段,复选框选中的值
								rownumbers : true,//行号
								nowrapL : true,//同行显示数据
								/* title: '用户信息',  */
								height : 'auto',
								pageList : [ 10, 20, 30, 50, 100, 200 ],//可选的页面的大小 
								pageSize : 20,//初始化页面大小
								checkOnSelect : false,
								columns : [ [ {
									field : 'uid',
									checkbox : true
								}, {
									field : 'uaccount',
									title : '账号',
									width : 100
								}, {
									field : 'upwd',
									title : '密码',
									width : 100
								}, {
									field : 'uname',
									title : '用户名',
									width : 100
								}, {
									field : 'user_sex',
									title : '性别',
									width : 100
								}, {
									field : 'ustate',
									title : '状态',
									width : 100
								} ] ],
								toolbar : [{
											text : '添加',
											iconCls : 'icon-add',
											handler : function() {
												//弹出一个添加用户的窗口
												$('#win').window({
																	width : 500,
																	height : 650,
																	title : "添加用户",
																	modal : true,
																	minimizable : false,
																	maximizable : false,
																	content : "<iframe src='${pageContext.request.contextPath}/user/userAddUI.action' height='100%' width='100%' frameborder='0px' ></iframe>"
																});
											}
										},'-',{
											text : '修改',
											iconCls : 'icon-edit',
											handler : function() {
												var checkeds = $('#user_datagrid').datagrid('getChecked');
												var rows = $('#user_datagrid').datagrid('getRows');
												if (checkeds.length != 1) {
													$.messager.confirm('确认','请选择一项纪录!',
																	function(r) {
																		if (r) {
																			checkeds = 0;
																		}
																	});
												} else {
													//弹出一个添加用户的窗口
													$('#win').window({
																		width : 500,
																		height : 650,
																		title : "修改用户",
																		modal : true,
																		minimizable : false,
																		maximizable : false,
																		content : "<iframe src='${pageContext.request.contextPath}/user/userEditUI.action?uid="+ checkeds[0].uid+ "'"+ " height='100%' width='100%' frameborder='0px' ></iframe>"
																	});
												}
											}
										},'-',{
											text : '删除',
											iconCls : 'icon-remove',
											handler : function() {
												var uids = new Array();
												var checkeds = $('#user_datagrid').datagrid('getChecked');
												var rows = $('#user_datagrid').datagrid('getRows');
												for (var i = 0; i < checkeds.length; i++) {
													uids.push(checkeds[i].uid);
												}
												if (uids.length != 0) {
													$.messager.confirm('确认',"你确定要删除这些用户吗？",function(r) {
																		if (r) {
																			$.ajax({
																						type : "POST",
																						url : "${pageContext.request.contextPath}/user/deleteUser.action",
																						data : "uids="+ uids,
																						async : false,
																						contentType : "application/x-www-form-urlencoded; charset=utf-8",
																						success : function(msg) {
																							$('#user_datagrid').datagrid('uncheckAll');
																							//刷新当前页面
																							$('#user_datagrid').datagrid('load');
																						}
																					});
																		}
																	});
												} else {
													$.messager.confirm('确认',"请至少选择一个门口机！",function(r) {
																if (r) {
																}
															});
												}
											}
										} 
								]
							});
		}

	}
	//搜索用户
	function doSearchUser() {
		//load从新加载并显示第一页的行
		$('#user_datagrid').datagrid('load', {
			uaccount : '%' + $.trim($('#uaccount').val()) + '%'
		});
	}

	//门口机列表
	function display() {
		//如果存在选中门口机列表选项卡,并刷新datagr组件
		if ($('#tabs').tabs('exists', '门口机列表')) {
			$('#tabs').tabs('select', '门口机列表');
			//刷新当前页面
			$('#equipment_datagrid').datagrid('load');
			return;
		}
		//如果不存在则创建
		$('#tabs').tabs('add',{
							title : '门口机列表',
							selected : true,
							// 新内容的URL
							content : "&nbsp;&nbsp;&nbsp;&nbsp;门口机编号:<input type='text' id='mkj_enumber' name='enumber' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手术室编号:<input type='text' id='mkj_opRoomId' name='opRoomId' /> <a href='#'  style='font-size:14px;text-decoration:none; font-weight:bold;' onclick='doSearchMkj()'>搜索</a><table id='equipment_datagrid'></table>",
							closable : true
						});

		//加载门口机列表数据
		if ($('#tabs').tabs('exists', '门口机列表')) {
			$('#tabs').tabs('select', '门口机列表');
			//创建datagrid组件
			$('#equipment_datagrid').datagrid({
								url : '${pageContext.request.contextPath}/equipment/equipmentList.action',
								fitColumns : true, //自适应
								pagination : true, //分页工具栏
								/*  fit:true,  *///满填充"src/com/zkhv/equipment/entity/Equipment.java"
								idField : "eid",//指定id字段为行标记字段,复选框选中的值
								rownumbers : true,//行号
								nowrapL : true,//同行显示数据
								/*  title: '用户信息', */
								height : 'auto',
								pageList : [ 10, 20, 30, 50, 100, 200 ],//可选的页面的大小 
								pageSize : 20,//初始化页面大小
								checkOnSelect : false,
								columns : [ [ {
									field : 'eid',
									checkbox : true
								}, {
									field : 'enumber',
									title : '门口机编号',
									width : 100
								}, {
									field : 'eip',
									title : '门口机IP',
									width : 100
								},
								/* {field:'offTime',title:'关机时间',width:100},*/
								{
									field : 'cutScreen',
									title : '切屏频次(秒)',
									width : 60
								}, {
									field : 'estate',
									title : '门口机状态',
									width : 60
								}, {
									field : 'eurl',
									title : '门口机页面地址',
									width : 300
								}, {
									field : 'opRoomId',
									title : '手术室编号',
									width : 100
								}, {
									field : 'edesc',
									title : '门口机描述',
									width : 100
								}, {
									field : 'prompt',
									title : '门口机提示信息',
									width : 100
								}

								] ],
								toolbar : [
										{
											text : '刷新',
											iconCls : 'icon-arrow_refresh',
											handler : function() {
												Option('equipment/refresh.action');
											}
										},
										'-',
										{
											text : '重启',
											iconCls : 'icon-reload',
											handler : function() {
												$.messager.confirm('确认','您确认想要重启吗？',function(r) {
																	if (r) {
																		Option('equipment/reboot.action');
																	}
																});
											}
										},
										'-',
										{
											text : '检查网络',
											iconCls : 'icon-computer_go',
											handler : function() {
												Option('equipment/checkConnect.action');
											}
										},
										'-',
										{
											text : '应用重启',
											iconCls : 'icon-arrow_rotate_clockwise',
											handler : function() {
												$.messager.confirm('确认','您确认想要重启应用吗？',function(r) {
																	if (r) {
																		Option('equipment/applyReboot.action');
																	}
																});
											}
										},
										'-',
										{
											text : '版本更新',
											iconCls : 'icon-down',
											handler : function() {
												$.messager.confirm('确认','您确认想要更新版本吗？',function(r) {
																	if (r) {
																		Option('equipment/updateVersion.action');
																	}
																});
											}
										},
										'-',
										{
											text : '校正时间',
											iconCls : 'icon-time_green',
											handler : function() {
												$.messager.confirm('确认','您确认想要校正时间吗？',function(r) {
																	if (r) {
																		Option('equipment/correctTime.action');
																	}
																});
											}
										},
										'-',
										{
											text : '发布节目',
											iconCls : 'icon-arrow_up',
											handler : function() {
												var eids = new Array();
												var checkeds = $('#equipment_datagrid').datagrid('getChecked');
												for (var i = 0; i < checkeds.length; i++) {
													eids.push(checkeds[i].eid);
												}
												if (eids.length != 0) {
													$.messager.prompt('发布节目','请输入新节目的地址:',function(sendData) {
																		if (sendData) {
																			if (sendData != null|| $.trim(sendData) != "") {
																				Option('equipment/editUrl.action?sendData='+ sendData);
																			}
																		}
																	});
												} else {
													$.messager.confirm('确认',"请至少选择一个门口机!",function(r) {
																if (r) {
																}
															});
												}

											}
										},
										'-',
										{
											text : '初始化',
											iconCls : 'icon-application_put',
											handler : function() {
												$.messager.confirm('确认','您确认想要恢复初始化页面吗？',function(r) {
																	if (r) {
																		Option('equipment/initLoad.action');
																	}
																});
											}
										},
										'-',
										{
											text : '重置指令',
											iconCls : 'icon-application_xp_terminal',
											handler : function() {
												$.messager.confirm('确认','你确认要重置命令吗？',function(r) {
																	if (r) {
																		$.ajax({
																					type : "POST",
																					url : "${pageContext.request.contextPath}/sysComand/reLoadCommand.action",
																					async : false,
																					contentType : "application/x-www-form-urlencoded; charset=utf-8",
																					success : function(msg) {
																						if (msg == "true") {
																							$.messager.confirm('确认','已重置指令！',function(r) {
																												if (r) {
																												}
																											});
																							//刷新当前页面
																							$('#user_datagrid').datagrid('reload');
																						} else {
																							$.messager.confirm('确认','失败,请检查原因！',function(r) {
																												if (r) {
																												}
																											});

																						}
																					}
																				});
																	}
																});
											}
										},
										'-',
										{
											text : '切屏频次',
											iconCls : 'icon-photo_edit',
											handler : function() {
												var eids = new Array();
												var checkeds = $('#equipment_datagrid').datagrid('getChecked');
												for (var i = 0; i < checkeds.length; i++) {
													eids.push(checkeds[i].eid);
												}
												if (eids.length != 0) {
													$.messager.prompt('切屏频次','请输入大于0的整数,作为新的切屏频次!',
																	function(sendData) {
																		if (sendData) {
																			if (sendData != null|| $.trim(sendData) != "") {
																				var rg = /^([1-9]\d*|[0]{1,1})$/;
																				if (rg.test(sendData)){
																					$.ajax({
																								type : "POST",
																								url : "${pageContext.request.contextPath}/equipment/editCutScreen.action?sendData="+sendData,
																								data : "eids="+ eids,
																								contentType : "application/x-www-form-urlencoded; charset=utf-8",
																								success : function(msg) {
																									if (msg == "true") {
																										//刷新当前页面
																										$('#equipment_datagrid').datagrid('load');
																									}
																								}
																							});
																				} else {
																					$.messager.confirm('确认','请输入大于0的整数！',function(r) {
																										if (r) {

																										}
																									});

																				}
																			}
																		}
																	});
												} else {
													$.messager.confirm('确认',"请至少选择一个门口机!",function(r) {
																if (r) {
																}
															});
												}

											}
										},
										'-',
										{
											text : '提示修改',
											iconCls : 'icon-table_edit',
											handler : function() {
												var eids = new Array();
												var checkeds = $('#equipment_datagrid').datagrid('getChecked');
												for (var i = 0; i < checkeds.length; i++) {
													eids.push(checkeds[i].eid);
												}
												if (eids.length != 0) {
													$.messager.prompt('提示修改','请输入门口机提示信息:',function(sendData) {
																		if (sendData) {
																			if (sendData != null|| $.trim(sendData) != "") {
																				sendData = encodeURI(sendData); //根据mata中的ContentType来决定用什么编码
																				Option('equipment/editPrompt.action?sendData='+sendData);
																			}
																		}
																	});
												} else {
													$.messager.confirm('确认',"请至少选择一个门口机!",function(r) {
																if (r) {
																}
															});
												}

											}
										},
										'-',
										{
											text : '添加',
											iconCls : 'icon-add',
											handler : function() {
												//弹出一个添加医疗项目的窗口
												$('#win').window({
																	width : 550,
																	height : 780,
																	title : "新增门口机",
																	modal : true,
																	minimizable : false,
																	maximizable : false,
																	content : "<iframe src='${pageContext.request.contextPath}/equipment/equipmentAddUI.action' height='100%' width='100%' frameborder='0px' ></iframe>"

																});

											}
										},
										'-',
										{
											text : '修改',
											iconCls : 'icon-edit',
											handler : function() {
												var checkeds = $('#equipment_datagrid').datagrid('getChecked');
												if (checkeds.length != 1) {
													$.messager.confirm('确认','请选择一项纪录!',function(r) {
																		if (r) {
																			checkeds = 0;
																		}
																	});
												} else {
													//弹出一个添加医疗项目的窗口
													$('#win').window({
																		width : 550,
																		height : 780,
																		title : "修改门口机",
																		modal : true,
																		minimizable : false,
																		maximizable : false,
																		content : "<iframe src='${pageContext.request.contextPath}/equipment/equipmentEditUI.action?eid="+ checkeds[0].eid+"'"+ " height='100%' width='100%' frameborder='0px' ></iframe>"
																	});

												}
											}
										},
										'-',
										{
											text : '删除',
											iconCls : 'icon-remove',
											handler : function() {
												$.messager.confirm('确认','您确认想要删除记录吗？',function(r) {
																	if (r) {
																		var eids = new Array();
																		var checkeds = $('#equipment_datagrid').datagrid('getChecked');
																		for (var i = 0; i < checkeds.length; i++) {
																			eids.push(checkeds[i].eid);
																		}
																		if (eids.length != 0) {
																			$.ajax({
																						type : "POST",
																						url : "${pageContext.request.contextPath}/equipment/deleteEquipment.action",
																						data : "eids="+ eids,
																						async : true,
																						contentType : "application/x-www-form-urlencoded; charset=utf-8",
																						success : function(msg) {
																							if (msg == "true") {
																								$.messager.confirm('确定',"删除成功！",function(r) {
																													if (r) {
																														$('#equipment_datagrid').datagrid('uncheckAll');
																														//刷新当前页面
																														$('#equipment_datagrid').datagrid('load');
																													}
																												});
																							}
																						}
																					});
																		} else {
																			$.messager.confirm('确认',"请至少选择一个门口机!",function(r) {
																								if (r) {
																									
																								}
																							});
																		}

																	}
																});

											}
										} ],
								onDblClickCell : function(index, field, value) {
									if (field == "prompt" || field == "edesc"&& value != null&& $.trim(value) != "") {
										$('#win').window({
											width : 600,
											height : 180,
											modal : false,
											collapsible : false,
											minimizable : false,
											maximizable : false,
											title : "详细信息",
											content : value
										});
									}

								}
							});
		}

	}
	//doSearchMkj()
	//门口机搜索框
	function doSearchMkj() {
		//load从新加载并显示第一页的行
		$('#equipment_datagrid').datagrid('load', {
			opRoomId : $.trim($('#mkj_opRoomId').val()),
			enumber : $.trim($('#mkj_enumber').val())
		});
	}

	//加载患者手术信息
	function patientList() {
		if ($('#tabs').tabs('exists', '本地库手术患者信息')) {
			$('#tabs').tabs('select', '本地库手术患者信息');
			//刷新当前页面
			$('#patient_datagrid').datagrid('reload');
			return;
		}
		//如果不存在则创建
		$('#tabs').tabs('add',{
							title : "本地库手术患者信息",
							selected : true,
							content : "&nbsp;&nbsp;&nbsp;&nbsp;患者姓名 :<input type='text' id='p_p_name' name='p_name'/>&nbsp;&nbsp;&nbsp;&nbsp;手术室名:<input type='text' id='room_p_name' name='room_name' />&nbsp;&nbsp;&nbsp;&nbsp;所在科室:<input type='text' id='room_p_dept' name='p_dept' />&nbsp;&nbsp;&nbsp;&nbsp;手术时间:<input type='text' id='room_opst_time' name='ops_time' />&nbsp;&nbsp;&nbsp;<a href='#'  style='font-size:14px;text-decoration:none; font-weight:bold;' onclick='doSearch()'>搜索</a><table id='patient_datagrid'></table>",
							closable : true
						});
		//获取数据
		if ($('#tabs').tabs('exists', '本地库手术患者信息')) {
			$('#tabs').tabs('select', '本地库手术患者信息');
			//创建datagrid组件
			$('#patient_datagrid').datagrid({
								url : '${pageContext.request.contextPath}/t_Patient/selectPageUseDyc.action?time='+ new Date().getTime(),
								fitColumns : false, //自适应
								pagination : true, //分页工具栏
								/* fit:true,  *///满填充
								idField : "id",//指定id字段为行标记字段,复选框选中的值
								rownumbers : true,//行号
								nowrapL : true,//同行显示数据
								height : 'auto',
								pageList : [ 10, 20, 30, 50, 100, 200 ],//可选的页面的大小 
								pageSize : 20,//初始化页面大小
								checkOnSelect : false,
								frozenColumns : [ [
										{
											field : 'id',
											title : '患者编号',
											width : 200
										},
										{
											field : 'room_name',
											title : '手术室名称',
											width : 100
										},
										{
											field : 'room_id',
											title : '手术室编号',
											width : 100
										},
										{
											field : 'ops_anme',
											title : '手术名称',
											width : 400
										},
										{
											field : 'ops_status',
											title : '手术状态',
											width : 100,
											formatter : function(value, row,index) {
												if (value != null && value != 0
														&& value != "") {
													if (value == 5) {
														return "入手术室";
													} else if (value == 10) {
														return "麻醉开始";
													} else if (value == 15) {
														return "手术开始";
													} else if (value == 25) {
														return "手术结束";
													} else {
														return "麻醉结束";
													}
												} else {
													return "准备手术";
												}
											}
										} ] ],
								columns : [ [
										/* {field:'id',title:'患者编号',width:200}, 
										{field:'room_name',title:'手术室名称',width:100}, 
										{field:'room_id',title:'手术室编号',width:100},
										{field:'ops_anme',title:'手术名称',width:400}, 
										{field:'ops_status',title:'手术状态',width:100,formatter:function(value,row,index){
										 if(value!=null&&value!=0&&value !=""){
										       	if(value==5){
										       		return "入手术室";
										       	}else if(value==10){
										       		return "麻醉开始";
										       	}else if(value==15){
										       		return "手术开始";
										       	}else if(value==25){
										       		return "手术结束";
										       	}else{
										       		return "麻醉结束";
										       	}
										  	 }else{
										  		 return "准备手术";
										  	 }
										  	}},*/
										{
											field : 'ops_time',
											title : '手术时间',
											width : 160,
											formatter : function(value, row,index) {
												if (value != null) {
													var unixTimestamp = new Date(value);
													/* unixTimestamp.getFullYear()+'-'+unixTimestamp.getMonth()+'-'+unixTimestamp.getDate()+' '+unixTimestamp.getHours()+':'+unixTimestamp.getMinutes() *//* +':'+unixTimestamp.getSeconds() */;
													return unixTimestamp.toLocaleString();
												}
											}
										},
										{
											field : 'ops_starttime',
											title : '手术开始时间',
											width : 160,
											formatter : function(value, row,index) {
												if (value != null) {
													var unixTimestamp = new Date(value);
													return unixTimestamp.toLocaleString();
												}

											}
										},
										{
											field : 'ops_stilltime',
											title : '手术持续时间',
											width : 100
										/* , formatter:function(value,row,index){  
													                       var unixTimestamp = new Date(value);  
													                       return unixTimestamp.toLocaleString();  
													                        } */
										},
										{
											field : 'ops_endtime',
											title : '手术结束时间',
											width : 160,
											formatter : function(value, row,index) {
												if (value != null) {
													var unixTimestamp = new Date(value);
													return unixTimestamp.toLocaleString();
												}
											}
										},
										{
											field : 'p_name',
											title : '患者姓名',
											width : 100
										},
										{
											field : 'p_num',
											title : '患者住院号',
											width : 100
										},
										{
											field : 'p_dept',
											title : '患者所在科室',
											width : 150
										},
										{
											field : 'p_sex',
											title : '患者性别',
											width : 80
										},
										{
											field : 'p_old',
											title : '患者年龄',
											width : 80
										},
										{
											field : 'p_diagnose',
											title : '患者诊断',
											width : 200
										},
										{
											field : 'ops_doctor',
											title : '主刀医生',
											width : 100
										},
										{
											field : 'ops_doctor_id',
											title : '主刀医生编号',
											width : 100
										},
										{
											field : 'ops_mzdoctor',
											title : '麻醉医生',
											width : 100
										},
										{
											field : 'ops_mzdoctor_id',
											title : '麻醉医生编号',
											width : 100
										},
										{
											field : 'area_name',
											title : '手术区域名称',
											width : 100
										},
										{
											field : 'update_time',
											title : '信息更新时间',
											width : 160,
											formatter : function(value, row,index) {
												if (value != null) {
													var unixTimestamp = new Date(value);
													return unixTimestamp.toLocaleString();
												}
											}
										} ] ],
								onLoadSuccess : function() {
									$('#room_opst_time').datebox({

									});

								},
								onDblClickRow : function(index, row) {
									$('#win').window({
														width : 700,
														height : 600,
														modal : false,
														collapsible : false,
														minimizable : false,
														maximizable : false,
														title : "详细信息",
														/*  */
														content : "<iframe src='${pageContext.request.contextPath}/t_Patient/select.action?id="+row.id+ "'"+" height='100%' width='100%' frameborder='0px' ></iframe>"
													});

								}
							});

		}
	}

	//搜索框
	function doSearch() {
		var date = $("#room_opst_time").datebox("getValue");
		//都为空刷新
		if ($.trim(date) != "") {
			if (date.indexOf("-") != -1) {
				$('#patient_datagrid').datagrid('load', {
					p_name : $.trim($('#p_p_name').val()),
					room_name : $.trim($('#room_p_name').val()),
					p_dept : $.trim($('#room_p_dept').val()),
					ops_time : date
				});
				//datagrid加载完毕，回显时间
				$('#patient_datagrid').datagrid({
					onLoadSuccess : function() {
						$("#room_opst_time").datebox("setValue", date);

					}
				});

			} else {
				$.messager.confirm('确认', '请使用给定的日历进行选择！', function(r) {
					if (r) {

					}
				});
			}

		} else {

			//当不需要时间条件时，由于上一次的时间暂存，可能还会回显时间，那这就不对了，因此时间置; 
			$('#patient_datagrid').datagrid('load', {
				p_name : $.trim($('#p_p_name').val()),
				room_name : $.trim($('#room_p_name').val()),
				p_dept : $.trim($('#room_p_dept').val()),
			});
			$('#patient_datagrid').datagrid({
				onLoadSuccess : function() {
					$("#room_opst_time").datebox("setValue", "");

				}
			});
		}

	}

	//加载手术室信息
	function opRoomList() {
		if ($('#tabs').tabs('exists', '本地库手术室信息')) {
			$('#tabs').tabs('select', '本地库手术室信息');
			//刷新当前页面
			$('#opRoom_datagrid').datagrid('reload');
			return;
		}
		//如果不存在则创建
		$('#tabs').tabs('add',{
							title : "本地库手术室信息",
							selected : true,
							content : "&nbsp;&nbsp;&nbsp;&nbsp;手术室编号 :<input type='text' id='room_r_id' name='room_id'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手术室名:<input type='text' id='room_r_name' name='room_name' /> <a href='#'  style='font-size:14px;text-decoration:none; font-weight:bold;' onclick='doSearchRoom()'>搜索</a><table id='opRoom_datagrid'></table>",
							closable : true
						});
		//获取数据
		if ($('#tabs').tabs('exists', '本地库手术室信息')) {
			$('#tabs').tabs('select', '本地库手术室信息');
			//创建datagrid组件
			$('#opRoom_datagrid').datagrid(
							{
								url : '${pageContext.request.contextPath}/t_OPRoom/selectPageUseDyc.action?time='+ new Date().getTime(),
								fitColumns : true, //自适应
								pagination : true, //分页工具栏
								/* 	 fit:true, *///满填充
								idField : "id",//指定id字段为行标记字段,复选框选中的值
								rownumbers : true,//行号
								nowrapL : true,//同行显示数据
								height : 'auto',
								pageList : [ 10, 20, 30, 50, 100, 200 ],//可选的页面的大小 
								pageSize : 20,//初始化页面大小
								checkOnSelect : false,
								columns : [ [
										{
											field : 'id',
											title : '手术室ID',
											width : 200
										},
										{
											field : 'room_id',
											title : '手术室编号',
											width : 100
										},
										{
											field : 'room_name',
											title : '手术室名称',
											width : 100
										},
										{
											field : 'area_name',
											title : '手术区域名称',
											width : 100
										},
										{
											field : 'update_time',
											title : '更新时间',
											width : 100,
											formatter : function(value, row,index) {
												if (value != null) {
													var unixTimestamp = new Date(value);
													/* unixTimestamp.getFullYear()+'-'+unixTimestamp.getMonth()+'-'+unixTimestamp.getDate()+' '+unixTimestamp.getHours()+':'+unixTimestamp.getMinutes() *//* +':'+unixTimestamp.getSeconds() */;
													return unixTimestamp.toLocaleString();
												}
											}
										} ] ]
							});

		}
	}

	//搜索框
	function doSearchRoom() {
		//load从新加载并显示第一页的行
		$('#opRoom_datagrid').datagrid('load', {
			room_id : $.trim($('#room_r_id').val()),
			room_name : $.trim($('#room_r_name').val())
		});
	}

	//本地库目标库手术排班信息
	function opWorkList() {
		if ($('#tabs').tabs('exists', '本地库手术排班信息')) {
			$('#tabs').tabs('select', '本地库手术排班信息');
			//刷新当前页面
			$('#opWork_datagrid').datagrid('reload');
			return;
		}
		//如果不存在则创建
		$('#tabs').tabs('add',{
							title : "本地库手术排班信息",
							selected : true,
							content : "&nbsp;&nbsp;&nbsp;&nbsp;主刀医生:<input type='text' id='orr_w_doctor' name='ops_doctor'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手术室名:<input type='text' id='or_w_name' name='room_name' /> &nbsp;&nbsp;&nbsp;&nbsp;所在科室:<input type='text' id='work_p_dept' name='p_dept' />&nbsp;&nbsp;&nbsp;&nbsp;手术时间:<input type='text' id='work_opst_time' name='ops_time' />&nbsp;&nbsp;&nbsp;<a href='#'  style='font-size:14px;text-decoration:none; font-weight:bold;' onclick='doSearchWork()'>搜索</a><table id='opWork_datagrid'></table>",
							closable : true
						});
		//获取数据
		if ($('#tabs').tabs('exists', '本地库手术排班信息')) {
			$('#tabs').tabs('select', '本地库手术排班信息');
			//创建datagrid组件
			$('#opWork_datagrid').datagrid(
							{
								url : '${pageContext.request.contextPath}/t_OPWork/selectPageUseDyc.action?time='+ new Date().getTime(),
								fitColumns : false, //自适应
								pagination : true, //分页工具栏
								/* fit:true,  *///满填充
								idField : "id",//指定id字段为行标记字段,复选框选中的值
								rownumbers : true,//行号
								nowrapL : true,//同行显示数据
								height : 'auto',
								pageList : [ 10, 20, 30, 50, 100, 200 ],//可选的页面的大小 
								pageSize : 20,//初始化页面大小
								checkOnSelect : false,
								frozenColumns : [ [ {
									field : 'id',
									title : '手术排班编号',
									width : 200
								}, {
									field : 'room_id',
									title : '手术室编号',
									width : 100
								}, {
									field : 'room_name',
									title : '手术室名称',
									width : 100
								}, {
									field : 'ops_anme',
									title : '手术名称',
									width : 200
								}, {
									field : 'ops_state',
									title : '手术状态',
									width : 100,
									formatter : function(value, row, index) {
										if (value == 1) {
											return "已安排";
										} else {
											return "已入室";
										}
									}
								} ] ],
								columns : [ [
										/*   {field:'id',title:'手术排班编号',width:200 },    
										  {field:'room_id',title:'手术室编号',width:100},  
										  {field:'room_name',title:'手术室名称',width:100},
										  {field:'ops_anme',title:'手术名称',width:200}, 
										  {field:'ops_state',title:'手术状态',width:100,formatter:function(value,row,index){
										  	 if(value==1){
										           	return "已安排";
										      	 }else{
										      		 return "已入室";
										      	 }
										      	}}, */
										{
											field : 'ops_time',
											title : '手术时间',
											width : 160,
											formatter : function(value, row,index) {
												if (value != null) {
													var unixTimestamp = new Date(value);
													return unixTimestamp.toLocaleString();
												}
											}
										},
										{
											field : 'p_name',
											title : '患者姓名',
											width : 100
										},
										{
											field : 'p_num',
											title : '患者住院号',
											width : 100
										},
										{
											field : 'p_dept',
											title : '患者所在科室',
											width : 150
										},
										{
											field : 'p_sex',
											title : '患者性别',
											width : 100
										},
										{
											field : 'p_old',
											title : '患者年龄',
											width : 100
										},
										{
											field : 'p_diagnose',
											title : '患者诊断',
											width : 450
										},
										{
											field : 'ops_doctor',
											title : '主刀医生',
											width : 100
										},
										{
											field : 'ops_doctor_id',
											title : '主刀医生编号',
											width : 100
										},
										{
											field : 'ops_mzdoctor',
											title : '麻醉医生',
											width : 100
										},
										{
											field : 'ops_mzdoctor_id',
											title : '麻醉医生编号',
											width : 100
										},
										{
											field : 'area_name',
											title : '区域名称',
											width : 100
										},
										{
											field : 'update_time',
											title : '信息更新时间',
											width : 160,
											formatter : function(value, row,index) {
												if (value != null) {
													var unixTimestamp = new Date(value);
													return unixTimestamp.toLocaleString();
												}
											}
										} ] ],
								onLoadSuccess : function() {
									$('#work_opst_time').datebox({

									});

								},
								onDblClickRow : function(index, row) {
									$('#win').window(
													{
														width : 550,
														height : 550,
														modal : false,
														collapsible : false,
														minimizable : false,
														maximizable : false,
														title : "详细信息",
														/*  */
														content : "<iframe src='${pageContext.request.contextPath}/t_OPWork/select.action?id="+ row.id+ "'"+" height='100%' width='100%' frameborder='0px' ></iframe>"
													});

								}
							});

		}
	}

	//搜索框
	function doSearchWork() {
		var date = $("#work_opst_time").datebox("getValue");
		//都为空刷新
		if ($.trim(date) != "") {
			if (date.indexOf("-") != -1) {
				$('#opWork_datagrid').datagrid('load', {
					ops_doctor : $.trim($('#orr_w_doctor').val()),
					room_name : $.trim($('#or_w_name').val()),
					p_dept : $.trim($('#work_p_dept').val()),
					ops_time : date
				});
				//datagrid加载完毕，回显时间
				$('#opWork_datagrid').datagrid({
					onLoadSuccess : function() {
						$("#work_opst_time").datebox("setValue", date);
					}
				});

			} else {
				$.messager.confirm('确认', '请使用给定的日历进行选择！', function(r) {
					if (r) {

					}
				});
			}
		} else {
			$('#opWork_datagrid').datagrid('load', {
				ops_doctor : $.trim($('#orr_w_doctor').val()),
				room_name : $.trim($('#or_w_name').val()),
				p_dept : $.trim($('#work_p_dept').val()),

			});
			$('#opWork_datagrid').datagrid({
				onLoadSuccess : function() {
					$("#work_opst_time").datebox("setValue", "");
				}
			});
		}
	}

	//加载or患者手术信息===or
	function OrPatientList() {
		if ($('#tabs').tabs('exists', '目标库手术患者信息')) {
			$('#tabs').tabs('select', '目标库手术患者信息');
			//刷新当前页面
			$('#or_patient_datagrid').datagrid('load');
			return;
		}
		//如果不存在则创建
		$('#tabs').tabs('add',{
							title : "目标库手术患者信息",
							selected : true,
							content : "&nbsp;&nbsp;&nbsp;&nbsp;患者姓名 :<input type='text' id='p_orp_name' name='p_name'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手术室名:<input type='text' id='room_orp_name' name='room_name' />所在科室:<input type='text' id='or_p_dept' name='p_dept' />&nbsp;&nbsp;&nbsp;&nbsp;手术时间:<input type='text' id='or_opst_time' name='ops_time' />&nbsp;&nbsp;&nbsp; <a href='#'  style='font-size:14px;text-decoration:none; font-weight:bold;' onclick='doOr_PSearch()'>搜索</a><table id='or_patient_datagrid'></table>",
							closable : true
						});
		//获取数据
		if ($('#tabs').tabs('exists', '目标库手术患者信息')) {
			$('#tabs').tabs('select', '目标库手术患者信息');
			//创建datagrid组件
			$('#or_patient_datagrid').datagrid(
							{
								url : '${pageContext.request.contextPath}/or_Patient/selectPageUseDyc.action?time='
										+ new Date().getTime(),
								fitColumns : false, //自适应
								pagination : true, //分页工具栏
								/* fit:true, *///满填充
								idField : "id",//指定id字段为行标记字段,复选框选中的值
								rownumbers : true,//行号
								nowrapL : true,//同行显示数据
								height : 'auto',
								pageList : [ 10, 20, 30, 50, 100, 200 ],//可选的页面的大小 
								pageSize : 20,//初始化页面大小
								checkOnSelect : false,
								frozenColumns : [ [
										{
											field : 'id',
											title : '患者编号',
											width : 200
										},
										{
											field : 'room_name',
											title : '手术室名称',
											width : 100
										},
										{
											field : 'room_id',
											title : '手术室编号',
											width : 100
										},
										{
											field : 'ops_anme',
											title : '手术名称',
											width : 400
										},
										{
											field : 'ops_status',
											title : '手术状态',
											width : 100,
											formatter : function(value, row,index) {
												if (value != null && value != 0&& value != "") {
													if (value == 5) {
														return "入手术室";
													} else if (value == 10) {
														return "麻醉开始";
													} else if (value == 15) {
														return "手术开始";
													} else if (value == 25) {
														return "手术结束";
													} else {
														return "麻醉结束";
													}
												} else {
													return "准备手术";
												}
											}
										} ] ],
								columns : [ [
										/* {field:'id',title:'患者编号',width:200}, 
										{field:'room_name',title:'手术室名称',width:100}, 
										{field:'room_id',title:'手术室编号',width:100},
										{field:'ops_anme',title:'手术名称',width:400}, 
										{field:'ops_status',title:'手术状态',width:100,formatter:function(value,row,index){
										 if(value!=null&&value!=0&&value !=""){
										       	if(value==5){
										       		return "入手术室";
										       	}else if(value==10){
										       		return "麻醉开始";
										       	}else if(value==15){
										       		return "手术开始";
										       	}else if(value==25){
										       		return "手术结束";
										       	}else{
										       		return "麻醉结束";
										       	}
										  	 }else{
										  		 return "准备手术";
										  	 }
										  	}}, */
										{
											field : 'ops_time',
											title : '手术时间',
											width : 160,
											formatter : function(value, row,index) {
												if (value != null) {
													var unixTimestamp = new Date(value);
													/* unixTimestamp.getFullYear()+'-'+unixTimestamp.getMonth()+'-'+unixTimestamp.getDate()+' '+unixTimestamp.getHours()+':'+unixTimestamp.getMinutes() *//* +':'+unixTimestamp.getSeconds() */;
													return unixTimestamp.toLocaleString();
												}
											}
										},
										{
											field : 'ops_starttime',
											title : '手术开始时间',
											width : 160,
											formatter : function(value, row,index) {
												if (value != null) {
													var unixTimestamp = new Date(value);
													return unixTimestamp.toLocaleString();
												}

											}
										},
										{
											field : 'ops_stilltime',
											title : '手术持续时间',
											width : 100
										/* , formatter:function(value,row,index){  
													                       var unixTimestamp = new Date(value);  
													                       return unixTimestamp.toLocaleString();  
													                        } */},
										{
											field : 'ops_endtime',
											title : '手术结束时间',
											width : 160,
											formatter : function(value, row,index) {
												if (value != null) {
													var unixTimestamp = new Date(value);
													return unixTimestamp.toLocaleString();
												}
											}
										},
										{
											field : 'p_name',
											title : '患者姓名',
											width : 100
										},
										{
											field : 'p_num',
											title : '患者住院号',
											width : 100
										},
										{
											field : 'p_dept',
											title : '患者所在科室',
											width : 150
										},
										{
											field : 'p_sex',
											title : '患者性别',
											width : 80
										},
										{
											field : 'p_old',
											title : '患者年龄',
											width : 80
										},
										{
											field : 'p_diagnose',
											title : '患者诊断',
											width : 200
										},
										{
											field : 'ops_doctor',
											title : '主刀医生',
											width : 100
										},
										{
											field : 'ops_doctor_id',
											title : '主刀医生编号',
											width : 100
										},
										{
											field : 'ops_mzdoctor',
											title : '麻醉医生',
											width : 100
										},
										{
											field : 'ops_mzdoctor_id',
											title : '麻醉医生编号',
											width : 100
										},
										{
											field : 'area_name',
											title : '手术区域名称',
											width : 100
										},
										{
											field : 'update_time',
											title : '信息更新时间',
											width : 160,
											formatter : function(value, row,index) {
												if (value != null) {
													var unixTimestamp = new Date(value);
													return unixTimestamp.toLocaleString();
												}
											}
										} ] ],
								onLoadSuccess : function() {
									$('#or_opst_time').datebox({

									});

								},
								onDblClickRow : function(index, row) {
									$('#win').window(
													{
														width : 700,
														height : 600,
														modal : false,
														collapsible : false,
														minimizable : false,
														maximizable : false,
														title : "详细信息",
														/*  */
														content : "<iframe src='${pageContext.request.contextPath}/or_Patient/select.action?id="
																+ row.id
																+ "'"
																+ " height='100%' width='100%' frameborder='0px' ></iframe>"
													});

								}
							});

		}
	}

	//搜索框
	function doOr_PSearch() {
		var date = $("#or_opst_time").datebox("getValue");
		//都为空刷新
		if ($.trim(date) != "") {
			if (date.indexOf("-") != -1) {
				$('#or_patient_datagrid').datagrid('load', {
					p_name : $.trim($('#p_orp_name').val()),
					room_name : $.trim($('#room_orp_name').val()),
					p_dept : $.trim($('#or_p_dept').val()),
					ops_time : date
				});
				//datagrid加载完毕，回显时间
				$('#or_patient_datagrid').datagrid({
					onLoadSuccess : function() {
						$("#or_opst_time").datebox("setValue", date);
					}
				});
			} else {
				$.messager.confirm('确认', '请使用给定的日历进行选择！', function(r) {
					if (r) {

					}
				});
			}
		} else {
			$('#or_patient_datagrid').datagrid('load', {
				p_name : $.trim($('#p_orp_name').val()),
				room_name : $.trim($('#room_orp_name').val()),
				p_dept : $.trim($('#or_p_dept').val()),
			});
			$('#or_patient_datagrid').datagrid({
				onLoadSuccess : function() {
					$("#or_opst_time").datebox("setValue", "");
				}
			});
		}
	}

	//加载or目标库手术室信息===========or
	function OrOpRoomList() {
		if ($('#tabs').tabs('exists', '目标库手术室信息')) {
			$('#tabs').tabs('select', '目标库手术室信息');
			//刷新当前页面
			$('#or_OpRoom_datagrid').datagrid('load');
			return;
		}
		//如果不存在则创建
		$('#tabs').tabs('add',
						{
							title : "目标库手术室信息",
							selected : true,
							content : "&nbsp;&nbsp;&nbsp;&nbsp;手术室编号 :<input type='text' id='room_orr_id' name='room_id'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手术室名:<input type='text' id='room_orr_name' name='room_name' /> <a href='#'  style='font-size:14px;text-decoration:none; font-weight:bold; ' onclick='doOrSearchRoom()'>搜索</a><table id='or_OpRoom_datagrid'></table>",
							closable : true
						});
		//获取数据
		if ($('#tabs').tabs('exists', '目标库手术室信息')) {
			$('#tabs').tabs('select', '目标库手术室信息');
			//创建datagrid组件
			$('#or_OpRoom_datagrid').datagrid(
							{
								url : '${pageContext.request.contextPath}/or_OPRoom/selectPageUseDyc.action?time='+ new Date().getTime(),
								fitColumns : true, //自适应
								pagination : true, //分页工具栏
								/*  fit:true, *///满填充
								idField : "id",//指定id字段为行标记字段,复选框选中的值
								rownumbers : true,//行号
								nowrapL : true,//同行显示数据
								height : 'auto',
								pageList : [ 10, 20, 30, 50, 100, 200 ],//可选的页面的大小 
								pageSize : 20,//初始化页面大小
								checkOnSelect : false,
								columns : [ [
										{
											field : 'id',
											title : '手术室ID',
											width : 200
										},
										{
											field : 'room_id',
											title : '手术室编号',
											width : 100
										},
										{
											field : 'room_name',
											title : '手术室名称',
											width : 100
										},
										{
											field : 'area_name',
											title : '手术区域名称',
											width : 100
										},
										{
											field : 'update_time',
											title : '更新时间',
											width : 100,
											formatter : function(value, row,index) {
												if (value != null) {
													var unixTimestamp = new Date(value);
													/* unixTimestamp.getFullYear()+'-'+unixTimestamp.getMonth()+'-'+unixTimestamp.getDate()+' '+unixTimestamp.getHours()+':'+unixTimestamp.getMinutes() *//* +':'+unixTimestamp.getSeconds() */;
													return unixTimestamp.toLocaleString();
												}
											}
										} ] ]
							});

		}
	}

	//搜索框
	function doOrSearchRoom() {
		//load从新加载并显示第一页的行
		$('#or_OpRoom_datagrid').datagrid('load', {
			room_id : $.trim($('#room_orr_id').val()),
			room_name : $.trim($('#room_orr_name').val())
		});
	}

	//=========or
	function OrOpWorkList() {
		if ($('#tabs').tabs('exists', '目标库手术排班信息')) {
			$('#tabs').tabs('select', '目标库手术排班信息');
			//刷新当前页面
			$('#or_OpWork_datagrid').datagrid('load');
			return;
		}
		//如果不存在则创建
		$('#tabs').tabs('add',
						{
							title : "目标库手术排班信息",
							selected : true,
							content : "&nbsp;&nbsp;&nbsp;&nbsp;主刀医生:<input type='text' id='ops_orw_doctor' name='ops_doctor'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手术室名:<input type='text' id='room_orw_name' name='room_name' /> &nbsp;&nbsp;&nbsp;&nbsp;所在科室:<input type='text' id='or_work_p_dept' name='p_dept' />&nbsp;&nbsp;&nbsp;&nbsp;手术时间:<input type='text' id='or_work_opst_time' name='ops_time' /><a href='#'  style='font-size:14px;text-decoration:none; font-weight:bold;' onclick='doOrSearchWork()'>搜索</a><table id='or_OpWork_datagrid'></table>",
							closable : true
						});
		//获取数据
		if ($('#tabs').tabs('exists', '目标库手术排班信息')) {
			$('#tabs').tabs('select', '目标库手术排班信息');
			//创建datagrid组件
			$('#or_OpWork_datagrid').datagrid(
							{
								url : '${pageContext.request.contextPath}/or_OPWork/selectPageUseDyc.action?time='+ new Date().getTime(),
								fitColumns : false, //自适应
								pagination : true, //分页工具栏
								/* 	 fit:true,  *///满填充
								idField : "id",//指定id字段为行标记字段,复选框选中的值
								rownumbers : true,//行号
								nowrapL : true,//同行显示数据
								height : 'auto',
								pageList : [ 10, 20, 30, 50, 100, 200 ],//可选的页面的大小 
								pageSize : 20,//初始化页面大小
								checkOnSelect : false,
								frozenColumns : [ [//冻结列
								{
									field : 'id',
									title : '手术排班编号',
									width : 200
								}, {
									field : 'room_id',
									title : '手术室编号',
									width : 100
								}, {
									field : 'room_name',
									title : '手术室名称',
									width : 100
								}, {
									field : 'ops_anme',
									title : '手术名称',
									width : 200
								}, {
									field : 'ops_state',
									title : '手术状态',
									width : 100,
									formatter : function(value, row, index) {
										if (value == 1) {
											return "已安排";
										} else {
											return "已入室";
										}
									}
								} ] ],
								columns : [ [
										/*  {field:'id',title:'手术排班编号',width:200 },    
										 {field:'room_id',title:'手术室编号',width:100},  
										 {field:'room_name',title:'手术室名称',width:100},
										 {field:'ops_anme',title:'手术名称',width:200}, 
										 {field:'ops_state',title:'手术状态',width:100,formatter:function(value,row,index){
										 	 if(value==1){
										          	return "已安排";
										     	 }else{
										     		 return "已入室";
										     	 }
										     	}}, */
										{
											field : 'ops_time',
											title : '手术时间',
											width : 160,
											formatter : function(value, row,index) {
												if (value != null) {
													var unixTimestamp = new Date(value);
													return unixTimestamp.toLocaleString();
												}
											}
										},
										{
											field : 'p_name',
											title : '患者姓名',
											width : 100
										},
										{
											field : 'p_num',
											title : '患者住院号',
											width : 100
										},
										{
											field : 'p_dept',
											title : '患者所在科室',
											width : 150
										},
										{
											field : 'p_sex',
											title : '患者性别',
											width : 100
										},
										{
											field : 'p_old',
											title : '患者年龄',
											width : 100
										},
										{
											field : 'p_diagnose',
											title : '患者诊断',
											width : 450
										},
										{
											field : 'ops_doctor',
											title : '主刀医生',
											width : 100
										},
										{
											field : 'ops_doctor_id',
											title : '主刀医生编号',
											width : 100
										},
										{
											field : 'ops_mzdoctor',
											title : '麻醉医生',
											width : 100
										},
										{
											field : 'ops_mzdoctor_id',
											title : '麻醉医生编号',
											width : 100
										},
										{
											field : 'area_name',
											title : '区域名称',
											width : 100
										},
										{
											field : 'update_time',
											title : '信息更新时间',
											width : 160,
											formatter : function(value, row,index) {
												if (value != null) {
													var unixTimestamp = new Date(value);
													return unixTimestamp.toLocaleString();
												}
											}
										} ] ],
								onLoadSuccess : function() {
									$('#or_work_opst_time').datebox({

									});
								},
								onDblClickRow : function(index, row) {
									$('#win').window(
													{
														width : 550,
														height : 550,
														modal : false,
														collapsible : false,
														minimizable : false,
														maximizable : false,
														title : "详细信息",
														/*  */
														content : "<iframe src='${pageContext.request.contextPath}/or_OPWork/select.action?id="
																+ row.id
																+ "'"
																+ " height='100%' width='100%' frameborder='0px' ></iframe>"
													});

								}
							});

		}
	}

	//搜索框
	function doOrSearchWork() {
		var date = $("#or_work_opst_time").datebox("getValue");
		//都为空刷新
		if ($.trim(date) != "") {
			if (date.indexOf("-") != -1) {
				$('#or_OpWork_datagrid').datagrid('load', {
					ops_doctor : $.trim($('#ops_orw_doctor').val()),
					room_name : $.trim($('#room_orw_name').val()),
					p_dept : $.trim($('#or_work_p_dept').val()),
					ops_time : date
				});
				//datagrid加载完毕，回显时间
				$('#or_OpWork_datagrid').datagrid({
					onLoadSuccess : function() {
						$("#or_work_opst_time").datebox("setValue", date);
					}
				});
			} else {
				$.messager.confirm('确认', '请使用给定的日历进行选择！', function(r) {
					if (r) {

					}
				});
			}
		} else {
			date = "";
			$('#or_OpWork_datagrid').datagrid('load', {
				ops_doctor : $.trim($('#ops_orw_doctor').val()),
				room_name : $.trim($('#room_orw_name').val()),
				p_dept : $.trim($('#or_work_p_dept').val()),
				ops_time : date
			});
			$('#or_OpWork_datagrid').datagrid({
				onLoadSuccess : function() {
					$("#or_work_opst_time").datebox("setValue", "");
				}
			});
		}
	}

	//日志信息列表 
	//日志=========
	function LogInfoList() {
		if ($('#tabs').tabs('exists', '日志信息列表')) {
			$('#tabs').tabs('select', '日志信息列表');
			//刷新当前页面
			$('#log_datagrid').datagrid('load');
			return;
		}
		//如果不存在则创建
		$('#tabs').tabs('add',
						{
							title : "日志信息列表",
							selected : true,
							content : "&nbsp;&nbsp;&nbsp;&nbsp;门口机编号:<input type='text' id='mkj_code' name='remark'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日志时间:<input  id='loginfo_logTime'  name='log_time' type='text' ></input> <a href='#'  style='font-size:14px;text-decoration:none; font-weight:bold;'  id='a_info' onclick='doOrSearchLogInfo()'>搜索</a><table id='log_datagrid'></table>",
							closable : true
						});
		//获取数据
		if ($('#tabs').tabs('exists', '日志信息列表')) {
			$('#tabs').tabs('select', '日志信息列表');
			//创建datagrid组件
			$('#log_datagrid').datagrid(
							{
								url : '${pageContext.request.contextPath}/logAction/selectPageUseDyc.action?time='+ new Date().getTime(),
								fitColumns : true, //自适应
								pagination : true, //分页工具栏
								/*  fit:true,  *///满填充
								idField : "logId",//指定id字段为行标记字段,复选框选中的值
								rownumbers : true,//行号
								nowrapL : true,//同行显示数据
								height : 'auto',
								pageList : [ 10, 20, 30, 50, 100, 200 ],//可选的页面的大小 
								pageSize : 20,//初始化页面大小
								checkOnSelect : false,
								columns : [ [
										{
											field : 'logId',
											checkbox : true
										},
										{
											field : 'remark',
											title : '门口机编号',
											width : 100
										},
										{
											field : 'logTime',
											title : '日志时间',
											width : 100,
											formatter : function(value, row,index) {
												if (value != null) {
													var unixTimestamp = new Date(value);
													return unixTimestamp.toLocaleString();
												}
											}
										}, {
											field : 'logContent',
											title : '日志内容',
											width : 100
										}

								] ],
								onLoadSuccess : function() {
									$('#loginfo_logTime').datebox({});
								},
								onDblClickCell : function(index, field, value) {
									if (field == "logContent" && value != null&& $.trim(value)) {
										$('#win').window({
											width : 960,
											height : 500,
											modal : false,
											collapsible : false,
											minimizable : false,
											maximizable : false,
											title : "日志内容",
											content : value
										});
									}

								}
							});

		}
	}

	//搜索框
	function doOrSearchLogInfo() {
		var date = $("#loginfo_logTime").datebox("getValue");
		//都为空刷新
		if ((date == undefined || $.trim(date) == "")
				&& $.trim($("#mkj_code").val()) == "") {
			$('#log_datagrid').datagrid('load', {
				time : new Date().getTime()
			});
			$('#log_datagrid').datagrid({
				onLoadSuccess : function() {
					$("#loginfo_logTime").datebox("setValue", "");
				}
			});
			//时间不为空，必须符合一定格式
		} else if ($.trim(date) != "" && date.indexOf("-") != -1) {
			//load从新加载并显示第一页的行
			$('#log_datagrid').datagrid('load', {
				remark : $.trim($("#mkj_code").val()),
				log_time : date
			});
			//datagrid加载完毕，回显时间
			$('#log_datagrid').datagrid({
				onLoadSuccess : function() {
					$("#loginfo_logTime").datebox("setValue", date);
				}
			});
			//时间为空，门口机编号不为空
		} else if ($.trim(date) == "" && $.trim($("#mkj_code").val()) != "") {
			//load从新加载并显示第一页的行
			$('#log_datagrid').datagrid('load', {
				remark : $.trim($("#mkj_code").val()),
				log_time : date
			});
			$('#log_datagrid').datagrid({
				onLoadSuccess : function() {
					$("#loginfo_logTime").datebox("setValue", "");
				}
			});
		} else {
			$.messager.confirm('确认', '请使用给定的日历进行选择！', function(r) {
				if (r) {

				}
			});
		}
	}

	function updateOrSelectSql() {
		if ($('#tabs').tabs('exists', 'sql语句维护')) {
			$('#tabs').tabs('select', 'sql语句维护');
			//刷新当前页面
			$('#sql_datagrid').datagrid('load');
			return;
		}
		//如果不存在则创建
		$('#tabs').tabs('add', {
			title : "sql语句维护",
			selected : true,
			content : "<table id='sql_datagrid'></table>",
			closable : true
		});
		//获取数据
		if ($('#tabs').tabs('exists', 'sql语句维护')) {
			$('#tabs').tabs('select', 'sql语句维护');
			//创建datagrid组件
			$('#sql_datagrid').datagrid(
							{
								url : '${pageContext.request.contextPath}/mkjview/selectAllSql.action',
								fitColumns : true, //自适应
								/* 	 fit:true, *///满填充
								idField : "imp_id",//指定id字段为行标记字段,复选框选中的值
								rownumbers : true,//行号
								nowrapL : true,//同行显示数据
								height : 'auto',
								columns : [ [ {
									field : 'imp_id',
									checkbox : true
								}, {
									field : 'imp_name',
									title : '目标表中文名称',
									width : 100
								}, {
									field : 'imp_code',
									title : '目标表名称',
									width : 70
								}, {
									field : 'imp_starttime',
									title : '更新开始时间',
									width : 100
								}, {
									field : 'imp_endtime',
									title : '更新结束时间',
									width : 100
								}, {
									field : 'imp_field',
									title : '目标数据库字段',
									width : 100
								}, {
									field : 'imp_tempfield',
									title : '查询条件',
									width : 100
								}, {
									field : 'imp_table',
									title : '源表名称',
									width : 100
								}, {
									field : 'imp_where',
									title : 'where条件',
									width : 100
								}, {
									field : 'imp_count',
									title : '每次对接执行条数',
									width : 100
								}, {
									field : 'imp_orderby',
									title : '排序依据',
									width : 100
								}, {
									field : 'imp_wholesql',
									title : '完整语句',
									width : 150
								}, {
									field : 'imp_isprocedure',
									title : '是否可用(0否/1是)',
									width : 100
								} ] ],
								onDblClickRow : function(index, row) {
									var userName = "${SYS_USER.uaccount}";
									if (userName == "Root") {
										$('#win').window({
															width : 800,
															height : 850,
															modal : false,
															collapsible : false,
															minimizable : false,
															maximizable : false,
															title : "修改sql语句信息",
															content : "<iframe src='/EqMangerSys/mkjview/findSqlById.action?imp_id="
																	+ row.imp_id
																	+ "'"
																	+ " height='100%' width='100%' frameborder='0px' ></iframe>"
														});
									} else {
										$.messager.confirm('确认',
												'对不起，你没有该权限！不能修改', function(r) {
													if (r) {
														
													}
												});
									}

								},
								toolbar : [
										{
											text : '修改',
											iconCls : 'icon-edit',
											handler : function() {
												var userName = "${SYS_USER.uaccount}";
												if (userName == "Root") {
													var checkeds = $('#sql_datagrid').datagrid('getChecked');
													var rows = $('#sql_datagrid').datagrid('getRows');
													if (checkeds.length != 1) {
														$.messager.confirm('确认','请选择一条纪录!',function(r) {
																			if (r) {
																				checkeds = 0;
																			}
																		});
													} else {
														$('#win').window(
																		{
																			width : 800,
																			height : 900,
																			title : "修改sql语句信息",
																			modal : true,
																			minimizable : false,
																			maximizable : false,
																			content : "<iframe src='/EqMangerSys/mkjview/findSqlById.action?imp_id="
																					+ checkeds[0].imp_id
																					+ "'"
																					+ " height='100%' width='100%' frameborder='0px' ></iframe>"
																		});
													}
												} else {
													$.messager.confirm('确认','对不起，你没有该权限！不能修改',function(r) {
																		if (r) {
																			checkeds = 0;
																		}
																	});
												}

											}
										},
										'-',
										{
											text : '同步数据',
											iconCls : 'icon-reload',
											handler : function() {
												var userName = "${SYS_USER.uaccount}";
												if (userName == "Root") {
													var rows = $('#sql_datagrid').datagrid('getSelections');
													var nameList = new Array();
													if (rows.length > 0) {
														$.each(rows,function(i,n) {
																			nameList.push(n.imp_name);
																		});
														$.messager.progress({
																	text : '正在同步，请稍等....',
																	modal : true
																});
														var url = "${pageContext.request.contextPath}/utils/run.action";
														$.ajax({
																	type : "POST",
																	url : url,
																	data : "nameList="+ nameList,
																	contentType : "application/x-www-form-urlencoded; charset=utf-8",
																	success : function(msg) {
																		$.messager.progress('close');
																		$.messager.alert('消息',msg);
																		$("#sql_datagrid").datagrid("load");
																	}
																});

													} else {
														$.messager.alert('提示','请选择至少一条记录！','warning');
													}
												} else {
													$.messager.confirm('确认','对不起，你没有该权限！',function(r) {
																		if (r) {
																			checkeds = 0;
																		}
																	});
												}
											}
										},
										'-',
										{
											text : '同步所有数据',
											iconCls : 'icon-arrow_rotate_clockwise',
											handler : function() {
												var userName = "${SYS_USER.uaccount}";
												if (userName == "Root") {
													$.messager.progress({
														text : '正在同步，请稍等....',
														modal : true
													});
													var url = "${pageContext.request.contextPath}/utils/run.action";
													$.post(url,function(data) {
																		$.messager.progress('close');
																		$.messager.alert('消息',data);
																		$("#sql_datagrid").datagrid("load");
																	});
												} else {
													$.messager.confirm('确认','对不起，你没有该权限！',function(r) {
																		if (r) {
																			checkeds = 0;
																		}
																	});
												}
											}
										},
										'-',
										{
											text : '刷新',
											iconCls : 'icon-arrow_refresh',
											handler : function() {
												$("#sql_datagrid").datagrid("load");
											}
										} ]
							});

		}
	}
</script>
</html>