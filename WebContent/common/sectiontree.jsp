<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>

<head>
	<title></title>
	<link rel="StyleSheet" href="../public/dtree/dtree.css" type="text/css" />
	<script type="text/javascript" src="../public/dtree/dtree.js"></script>
</head>

<body>

<div class="dtree">
	<p><a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a></p>
	<script type="text/javascript">	
	${dtree}
	function selectnode(nodecode ,nodename){
		
		window.opener.document.getElementById("${code}").value = nodecode;
		window.opener.document.getElementById("${value}").value = nodename;
		window.close();
	}
	</script>
</div>
</body>
</html>