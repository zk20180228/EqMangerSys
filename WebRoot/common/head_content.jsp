<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="<%=basePath%>easyui1.4.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>easyui1.4.5/themes/icon.css" />

<!-- 需要整理  start-->
<%-- <link rel="stylesheet" type="text/css" href="<%=basePath%>themes/system/css/public.css">--%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>javascript/js/uploadify/uploadify.css"> 
<!-- 需要整理  end-->

<script type="text/javascript" src="<%=basePath%>easyui1.4.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>easyui1.4.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>easyui1.4.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>easyui1.4.5/easyloader.js"></script>
<script type="text/javascript" src="<%=basePath%>javascript/js/jquery.hoverIntent.minified.js"></script>
<script type="text/javascript" src="<%=basePath%>javascript/js/hisUtil.js"></script>
<script type="text/javascript" src="<%=basePath%>javascript/js/public.js"></script>
<script type="text/javascript" src="<%=basePath%>easyui1.4.5/custom_js/home_page.js"></script>

<!-- 需要整理  start-->
<script type="text/javascript" src="<%=basePath%>javascript/js/jquery.json-2.3.js"></script>
<script type="text/javascript" src="<%=basePath%>javascript/js/publicUtils.js"></script>
<script type="text/javascript" src="<%=basePath%>javascript/js/json2.js"></script>
<%-- <script type="text/javascript" src="<%=basePath%>javascript/js/uploadify/jquery.uploadify.js"></script> --%>
<script type="text/javascript" src="<%=basePath%>javascript/js/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="<%=basePath%>javascript/js/keydown.js"></script>
<!-- 需要整理  end-->



