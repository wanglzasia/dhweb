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
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../public/js/web_js.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

<script language="javascript">
	//version();
</script>

<title>用户中心</title>
<style type="text/css">
.tip_index{line-height:30px;font-size:12px;}
.my-product{list-style:none;padding:0;}
.my-product li{line-height:26px;width:150px;float:left;height:26px;overflow:hidden;}
</style>
</head>

<body>
<div id="top_nav">
	<div style="width:800px;margin:0 auto;padding-top:8px;">
		<div style="width:100px; float:right;">1&nbsp;</div>
		<div style="width:100px; float:right;">&nbsp;</div>
		<div style="width:100px; float:right;"><a href="/seller/center.do">卖家中心</a></div>
		<div style="width:150px; float:left;"><jsp:include page="/common/header.jsp"/></div>
		<div style="clear:both;"></div>
	</div>
</div>
<div id="top_main">
	<div style="width:303px;float:left;">
		<jsp:include page="/common/seller/seller_top_main.jsp"/>
	</div>
	<div style="width:300px;float:left;">&nbsp;</div>
	<div style="width:300px;float:right;">&nbsp;</div>
	<div style="clear:both;"></div>
</div>
<!-- <div id="top_menu"><jsp:include page="/common/seller/seller_top_menu.jsp"/></div> -->

<div id="body_section">
	<div id="left_menu">
		<!--
			<jsp:include page="/common/seller/seller_left_menu.jsp"/>
		-->
	</div>
	<div id="show_body">
		<!-- #show_nav -->
		<div id="show_nav" class="good">导航栏</div>
		<div id="context_section">
			<jsp:include page="${uri}.jsp"/>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer"><%@include file="/common/footer.jsp"%></div>
</body>
</html>