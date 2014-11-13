<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>相册</title>
<%
	String picid = (String)request.getParameter("picid");
%>
<script>
function loadpic(obj){
	var url  = obj.src;
	window.opener.document.getElementById('single_pic<%=picid%>').innerHTML="<img src="+url+" width=80px height=80px />";
	window.opener.document.getElementById('single_pic_url_<%=picid%>').value=url;
	window.opener=null;
	window.close();
}
</script>
</head>
<body>
		
		<s:iterator var="img" value="#request.list">  
			  <div style="width:100px;height:100px;padding:5px 5px 5px 5px;border:1px solid #e8e8e8;float:left;margin-left:5px;cursor:pointer;">
			   	<img src="http://127.0.0.1:8080/<s:property value="#img" />" width="100px" height="100px" ondblclick="loadpic(this)"/>
			  </div>
		</s:iterator>
		

</body>
</html>