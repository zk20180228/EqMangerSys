<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">   
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery-1.7.2.min.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/easyui-lang-zh_CN.js"></script>



<body style="background-color:#0099FF" >
<form id="fm" method="post" action="#" >   
     <!--隐藏的门口机的id 传递给后台 -->
   <input type="hidden" name="imp_id" value="${importSet.imp_id}" >
   <div style="fit:true" align="center">
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="sql信息" style="width:750px;height:820px;padding:30px 60px">
		<div style="margin-bottom:16px">
			<div>目标表中文名称:</div>
			<input class="easyui-textbox" readonly="readonly" value="${importSet.imp_name}" name="imp_name" style="width:100%;height:32px">
		</div>
		<div style="margin-bottom:20px">
			<div>目标表名称:</div>
			<input class="easyui-textbox" readonly="readonly" value="${importSet.imp_code}" name="imp_code" style="width:100%;height:32px">
		</div>
		<div style="margin-bottom:20px">
			<div>目标数据库字段:</div>
			<input class="easyui-textbox" data-options="multiline:true"  name="imp_field" value="${importSet.imp_field}" style="width:100%;height:60px">
		</div>
		<div style="margin-bottom:20px">
			<div>查询条件:</div>
			<input class="easyui-textbox" data-options="multiline:true" name="imp_tempfield" id="tempfield" value="${importSet.imp_tempfield}" style="width:100%;height:110px">
		</div>
		<div style="margin-bottom:20px">
			<div>源表:</div>
			<input class="easyui-textbox" name="imp_table" id="table" value="${importSet.imp_table}" style="width:100%;height:32px">
		</div>
		<div style="margin-bottom:20px">
			<div>where条件:</div>
			<input class="easyui-textbox" value="${importSet.imp_where}"name="imp_where" id="where" style="width:100%;height:32px">
		</div>
		<div style="margin-bottom:20px">
			<div>排序依据:</div>
			<input class="easyui-textbox" value="${importSet.imp_orderby}"name="imp_orderby" id="orderby" style="width:100%;height:32px">
		</div>
		<div style="margin-bottom:20px">
			<div>完整语句:
				<a href="#" class="easyui-linkbutton" id="btn1"  style="width:10%;height:32px">生成sql:</a>
			</div>
			<input class="easyui-textbox" data-options="multiline:true" value="${importSet.imp_wholesql}"name="imp_wholesql" id="wholesql" style="width:100%;height:110px">
		</div>
		<div style="margin-bottom:20px">
			<div>是否可用:</div>
			 <input type="radio" name="imp_isprocedure" id="is1"  value="1">是
             <input type="radio" name="imp_isprocedure" id="is2"  value="0">否
		</div>
		<div>
			<a href="#" class="easyui-linkbutton" id="btn" iconCls="icon-ok" style="width:100%;height:32px">修改</a>
		</div>
	</div>
   </div>
</form>  
</body>
<script type="text/javascript">
	$("#btn").click(function(){
		$('#fm').form('submit',{    
		    url:'${pageContext.request.contextPath}/mkjview/sqlEdit.action',
		    async:false,
		    onSubmit: function(){    
		          return true;//返回true，提交表单
		    },    
		    success:function(msg){ 
				if(msg=="ok"){
					//重新加载当前页面数据
					parent.$("#sql_datagrid").datagrid("load");
					//关闭当前窗口
				    parent.$("#win").window("close");
				}
		    }    
		});
	});
	
	$("#btn1").click(function(){
		var pre= "select "+$("#tempfield").val()+" from " +$("#table").val();
		var post = "";
		var post1 = "";
		if($("#where").val()!=""&&$("#where").val()!=null){
			post = " where "+$("#where").val();
		}
		if($("#orderby").val()!=""&&$("#orderby").val()!=null){
			post1 = " order by "+$("#orderby").val();
		}
		console.log(pre+post+post1);
		$("#wholesql").textbox("setValue",pre+post+post1 );
	});
	
	$(function(){
		var a = "${importSet.imp_isprocedure}";
		if(a=="1"){
			$("#is1").attr("checked","checked");
		}
		if(a=="0"){
			$("#is2").attr("checked","checked");
		}
	})
</script>

</html>