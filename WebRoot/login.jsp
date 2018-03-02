<%@ page language="java"  contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>手术门口机维护系统</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">   
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">   
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery-1.7.2.min.js"></script>   
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/datagrid-detailview.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/easyui-lang-zh_CN.js"></script> 
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
	<script language="javascript">
			$(function(){
				$("#submitBtn").click(function(){
					var _account=$("#txtAccount").val();
					var _password=$("#txtPassword").val();
					if(_account==null||$.trim(_account)==''){
						//验证账号
					$.messager.confirm('确认','账号不能为空！',function(r){    
							    if (r){ 
							    	$("#txtAccount").focus();
							    }    
							});  
						return false;
					}
					
					if(_password==null||$.trim(_password)==''){
						//验证密码
					$.messager.confirm('确认','密码不能为空！',function(r){    
							   if (r){ 
									$("#txtPassword").focus();
							   }    
						}); 
						return false;
					}
					
					//校验用户名和密码
					$.ajax({
							url:"${pageContext.request.contextPath}/user/checkLogin.action",
							async:false,//同步
							data:'uaccount='+_account+'&'+'upwd='+_password,
							type:"POST",
							success:function(msg){
								if(msg=="true"){
									//提交表单
									document.forms[0].submit();
								}else{
									 $.messager.confirm('确认','用户名或者密码错误！',function(r){    
										   if (r){    
										   }    
									}); 
								}
							}
					});	
						
				});
			});
			//提升用户体验
			$(document).keyup(function(event){
					if(event.keyCode==13){
						$("#submitBtn").click();
					}
			});
	</script>
</head>
<body>
	<div class='signup_container'>
		<div class="w-load">
			<div class="spin"></div>
		</div>
		<h1 class='signup_title'>手术门口机维护系统</h1>
		<div id="userInfo">
			<span
				style="float: left; margin-left: 40px; height: 200px; border: 0px solid red"><img
				src='${pageContext.request.contextPath}images/honry.png' id='admin' /></span> <span
				style="float: left; margin-left: 40px; height: 200px; border: 0px solid red">
				<div id="signup_forms" class="signup_forms clearfix">
					<form class="signup_form_form" id="loginform" method="post" action="${pageContext.request.contextPath}user/login.action">
						<div class="form_row first_row">
							<label for="signup_email">请输入用户名</label> <input type="text"
								name="uaccount" placeholder="请输入用户名" id="txtAccount" class="easyui-validatebox" data-options="required:true">
						</div>
						<div class="form_row">
							<label for="signup_password">请输入密码</label> <input type="password"
								name="upwd" placeholder="请输入密码" id="txtPassword" class="easyui-validatebox" data-options="required:true">
						</div>
					</form>
				</div>
				<div id="foo"></div> <br/>
			</span>
		</div>
		<div class="login-btn-set">
			<!-- <div class='rem'>记住我</div> -->
			<a class='login-btn' id="submitBtn"></a>
		</div>
	</div>
</body>


</html>
