<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.dh.ora.s001.bean.User"  %>
Welcome ,
<%
	User user =(User)session.getAttribute("usr");
%>
<%
 if (null != user){
%>
<%=user.getLoginNo()%>
<%} %>
&nbsp;&nbsp;&nbsp;<a href="/logout.do">退出</a>