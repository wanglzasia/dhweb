<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理平台</title>
<script type="Text/Javascript" language="JavaScript">
<!--
if (window.top != window)
{
  window.top.location.href = document.location.href;
}
//-->

</script>

	<frameset rows="76,*" cols="*" frameborder="no" border="0" framespacing="0">
	  <frame src="/s004/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame">
	  <frameset  cols="180, 10, *" framespacing="0" border="0" id="frame-body" name="frame-body">
	    <frame src="/s004/left.jsp"    id="menu-frame" name="menu-frame"  frameborder="no" scrolling="yes">
	    <frame src="/s004/drag.jsp"    id="drag-frame" name="drag-frame"  frameborder="no" scrolling="no">
	    <frame src="/s004/content.jsp" id="main-frame" name="main-frame"  frameborder="no" scrolling="yes">
	  </frameset>
	</frameset>
</head>
<body></body>
	
</html>