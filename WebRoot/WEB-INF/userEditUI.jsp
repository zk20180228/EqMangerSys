<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">   
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery-1.7.2.min.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/easyui-lang-zh_CN.js"></script>



<body style="background-color:#0099FF" >
<form id="fm" class="easyui-form"  method="post" action="#"  data-options="novalidate:true"  >   
     <!--隐藏的门口机的id 传递给后台 -->
   <input type="hidden" name="uid" value="${user.uid}" id="uid">
   <div style="position:absolute; top:200px; left:120px;">
	   <table align="center">
	   				 <tr >  
				        <td>账号</td>  
				       	<td><input id="uaccount" class="easyui-validatebox" type="text" name="uaccount"  data-options="required:true" value="${user.uaccount}" /></td>    
				      </tr>
			    
				      <tr >  
				        <td>密码</td>   
				        <td><input  id="upwd" class="easyui-validatebox" type="text" name="upwd" data-options="required:true"  value="${user.upwd}" /></td>   
				      </tr> 
				      
				     <tr>  
				        <td>用户名</td>   
				        <td><input  id="uname" class="easyui-validatebox" type="text" name="uname"  value="${user.uname}" data-options="required:true"/></td>   
				      </tr>
				      
				      <tr >  
				        <td>性别</td>   
				        <td><input type="radio" name="user_sex" value="男"  id="nan"/>男&nbsp;&nbsp; &nbsp;&nbsp; <input type="radio" name="user_sex" value="女"  id="nv"/>女</td>   
				      </tr>
				      
				      <tr > 
				        <td>状态</td>  
				        <td><input type="radio" name="ustate" value="有效"  id="valid" />有效&nbsp;&nbsp; <input type="radio" name="ustate" id="unvalid"  value="无效"/>无效</td>   
				      </tr>
			    	  <tr >  
			        	<td align="center" colspan="2"><input  type="button"  id="bt" value="提交" /></td>  
			    	  </tr>
	   </table>
   </div>
</form>  
</body>


	<!--单选框的回显  -->
	<script type="text/javascript">
		var user_sex = "${user.user_sex}"; 
		if(user_sex=="男"){
			$("#nan").attr("checked",true);
		}else{
			$("#nv").attr("checked",true);
		}
		
		var user_sex = "${user.ustate}"; 
		if(user_sex=="有效"){
			$("#valid").attr("checked",true);
		}else{
			$("#unvalid").attr("checked",true);
		}
	</script>


<script type="text/javascript">
$(function(){
	
	//刚进去，禁用验证
/* 	$("#fm").form("disableValidation"); */
	$("#bt").click(function(){
		var i=0;//0代表账号不重复
		var _uaccount= $("#uaccount").val();
		var _uid= $("#uid").val();
		$.ajax({
			url:"${pageContext.request.contextPath}/user/checkAccount.action",
			async:false,//同步
			data:'uaccount='+_uaccount+"&uid="+_uid,
			type:"POST",
			success:function(msg){
				if(msg=="true"){
					i=1;
				}
			}
		});	

		//开启表单验证
		$("#fm").form("enableValidation");
		if(i==1){
			$.messager.confirm('确认',"账号已存在,请重新设置！",function(r){    
			    if (r){ 
			    	$("#uaccount").focus();
			    	return ;
			    }   
			});
			return ;
		}
		i=0;
		if($("#fm").form("validate")){
			//异步的提交表单
			$('#fm').form('submit',{    
			    url:'${pageContext.request.contextPath}/user/userEdit.action',
			    async:false,
			    onSubmit: function(){    
			          return true;//返回true，提交表单
			    },    
			    success:function(msg){ 
					if(msg=="true"){
						//重新加载当前页面数据
						parent.$("#user_datagrid").datagrid("load");
						//关闭当前窗口
					    parent.$("#win").window("close");
					}
			    }    
			});
		}
		
		});
	});
		
</script>

</html>