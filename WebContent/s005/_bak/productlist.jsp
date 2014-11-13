<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../public/css/default.css" type="text/css" />
<title>China </title>
</head>
<body>
<div id="body_div">
	<div id="top_nav">
	<a href="/reg.do">注册</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/log.do">登陆</a>
	</div>
	<div id="top_main">
		<div id="head_logo">12</div>
		<div id="head_search">
			<div>
				<form id="head_search_form">
					<input type="text" name="search" id="head_search_input"/>
					<input type="submit" value="Search" id="head_search_btn"/>
				</form>
			</div>
		</div>
		<div id="head_action">
			<div></div> 
			<div></div>
			<div></div>
			<div></div>
		</div>
	</div>
	
	<table border="1px" style="color:black">
			<tr>
				<th >序号</th>
			    <th >产品编号</th>
			    <th >产品名称</th>
			    <th >品牌</th>
		  	</tr>
		<s:iterator var="map" value="#session.list">  
			  <tr>
			  	<td ><img src="<s:property value="#map.pic_name_1" />" width=100px height=100px/></td>
			    <td ><s:property value="#map.product_id" /></td>
			    <td ><s:property value="#map.product_no" /></td>
			    <td ><a href="http://127.0.0.1:8080/HTML/1001/1001-1000000035.htm">查看详情</a></td>
			  </tr>	    	
		</s:iterator>
	</table>
	
	
	<div id="footer"> footer </div> 
</div>
</body>
</html>