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
<link rel="stylesheet" href="../public/css/default3.css" type="text/css" />
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../public/js/web_js.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="../public/js/shoppingCart_js.js"></script>
<script language="javascript">
	//version();
</script>

<title>Shooping Cart</title>
<style type="text/css">
.tip_index{line-height:30px;font-size:12px;}
.my-product{list-style:none;padding:0;}
.my-product li{line-height:26px;width:150px;float:left;height:26px;overflow:hidden;}
</style>
</head>

<body>

<div id="top_nav">
	<div style="padding-top:8px;" class="top_nav_inner">
		<div STYLE="float:right;"><a href="/member/center.do">Buyer Center</a></div>
		<div style="float:right;">&nbsp;</div>
		<div style="float:right;"></div>
		<div style="float:left;text-align:right;">
			<jsp:include page="/common/header.jsp"/>
		</div>
		<div style="clear:both;"></div>
	</div>
</div>


<div id="top_main">
	<div class="top_main_logo">
		<jsp:include page="/common/seller/seller_top_main.jsp"/>
	</div>
</div>

<div id="body_section"> 
	<div class="show_nav" >Settle Center</div>
	
	<div class="main_content">
		<jsp:include page="orderconfirm.jsp"/>
	</div>
</div>


<div id="footer"><%@include file="/common/footer.jsp"%></div>
</body>
</html>
