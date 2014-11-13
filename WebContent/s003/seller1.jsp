<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.lang.String"  %>
<%@taglib prefix="s" uri="/struts-tags" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/> 
<meta http-equiv="expires" content="0"/>
<link rel="stylesheet" href="../public/css/default1.css" type="text/css" />
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script><title>用户中心</title>
<script type="text/javascript" >


function _loadPage(url){
	$("#context_section").load(url);
}
</script>
</head>

<body>
<div id="top_nav"><jsp:include page="/common/header.jsp"/></div>
<div id="top_main"><jsp:include page="/common/seller/seller_top_main.jsp"/></div>
<div id="top_menu"><jsp:include page="/common/seller/seller_top_menu.jsp"/></div>

<div id="body_section">
	<div id="left_menu">
		<jsp:include page="/common/seller/seller_left_menu.jsp"/>
	</div>
	<div id="show_body">
		<div id="show_nav">导航栏</div>
		<div id="context_section" >
					<s:action name="load" namespace="/product" executeResult="true"/>
 		</div>
	</div>
	<div class="clear"></div>
</div>

<div id="footer"><%@include file="/common/footer.jsp"%></div>
</body>
</html>