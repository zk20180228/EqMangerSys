<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">   
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery-1.7.2.min.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/easyui-lang-zh_CN.js"></script>



<body style="background-color:#0099FF" >
<form id="fm" class="easyui-form"  method="post" action="#"  data-options="novalidate:true"  >   
   
   <div style="position:absolute; top:200px; left:120px;">
	  
	   <table align="center">
	   				 <tr >  
				        <td>门口机编号&nbsp;&nbsp;</td>  
				       	<td><input id="enumber" class="easyui-validatebox" type="text" name="enumber"  data-options="required:true" /></td>    
				      </tr>
			    
				       <tr > 
				        <td>手术室编号</td>  
				        <td><input   type="text" name="opRoomId"  id="opRoomId" /> 
					        	<select id="select" >
					        		<option>--请选择--</option>
					        		<c:forEach items="${op_room}" var="room" >
					        			<option value="${room.room_id}">${room.room_id}</option>
					        		</c:forEach>
					        	</select>
				        </td>   
				      </tr>
				      <tr >  
				        <td>页面地址 </td>   
				        <td><input  id="eurl"  type="text" name="eurl" />
				        </td>   
				      </tr> 
			 
				      <tr > 
				        <td>门口机ip</td>  
				        <td><input id="eip" class="easyui-validatebox" type="text" name="eip" data-options="required:true" /></td>   
				      </tr>
				
				      <tr > 
				        <td>提示信息</td>  
				         <td><textarea rows="2" cols="14"  name="prompt"  ></textarea></td>  
				      </tr>
				       <tr > 
				        <td>描述</td>  
				        <td><textarea rows="2" cols="14"  name="edesc" ></textarea></td>   
				      </tr>
			    	  <tr >  
			        	<td align="center" colspan="2"><input  type="button"  id="bt" value="提交" /></td>  
			    	  </tr>
	   </table>
   </div>
</form>  
</body>

<script type="text/javascript">
		
	$("#select").change(function(){
		$("#opRoomId").val($("select option:selected").val());
	});
		
	

		$(function(){
			//刚进去，禁用验证
		/* 	$("#fm").form("disableValidation"); */
			$("#bt").click(function(){
				//开启表单验证
				$("#fm").form("enableValidation");
				if($("#fm").form("validate")){
					var IsRp=0;//0代表有重复,1代表不重复
					var _enumber= $("#enumber").val();
					var _eip= $("#eip").val();
					$.ajax({
						url:"${pageContext.request.contextPath}/equipment/checkEquipment.action",
						async:false,//同步
						data:'enumber='+_enumber+"&eip="+_eip,
						type:"POST",
						success:function(msg){
							if(msg=="true"){
								IsRp=1;//重复
							}
						}
					});
					if(IsRp==1){
						$.messager.confirm('确认',"编号或ip,重复请重新设置！",function(r){    
						    if (r){ 
						    	return ;
						    }   
						});
						return ;
					}else{
						//异步的提交表单
						$('#fm').form('submit',{    
						    url:'${pageContext.request.contextPath}/equipment/equipmentAdd.action',
						    async:false,
						    onSubmit: function(){    
						          return true;//返回true，提交表单
						    },    
						    success:function(msg){ 
								if(msg=="true"){
									//重新加载当前页面数据
									parent.$("#equipment_datagrid").datagrid("load");
									//关闭当前窗口
								    parent.$("#win").window("close");
								}
						    }    
						});
					}
					
				}
				
				});
		});
		
		
</script>

</html>