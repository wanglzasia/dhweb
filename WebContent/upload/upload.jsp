<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/> 
<meta http-equiv="expires" content="0"/>
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../public/js/web_js.js"></script>
<title>文件上传</title>
</head>
<body>
<%
String picid=(String)request.getParameter("picid");
%>
<form action="/save.do"  enctype="multipart/form-data" method="post">
	请选择文件：<input name="upload" type="file"/>
	<input type="hidden" name="picid" value="<%=picid%>"/> 
	<input type="submit" value="上传"/> 
</form>
</body> 
</html>