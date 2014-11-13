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
	d = new dTree('d');
	d.add(1000,-1,'根目录',"javascript:selectnode('1000','根目录')");
	${dtree}
	document.write(d);
	
	function selectnode(nodecode ,nodename){
		
		window.opener.document.getElementById("${code}").value = nodecode;
		window.opener.document.getElementById("${value}").value = nodename;
		window.close();
	}
	</script>
</div>
</body>
</html>

		<!--
		d = new dTree('d');
		d.add(0,-1,'My example tree');
		d.add(1,0,'Node 1','example01.html');
		d.add(2,0,'Node 2','example01.html');
		
		
		d.add(3,1,'Node 1.1','example01.html');
		d.add(4,0,'Node 3','example01.html');
		d.add(5,3,'Node 1.1.1','example01.html');
		d.add(6,5,'Node 1.1.1.1','example01.html');
		d.add(7,0,'Node 4','example01.html');
		d.add(8,1,'Node 1.2','example01.html');
		d.add(9,0,'My Pictures','example01.html','Pictures I\'ve taken over the years','','','img/imgfolder.gif');
		d.add(10,9,'The trip to Iceland','example01.html','Pictures of Gullfoss and Geysir');
		d.add(11,9,'Mom\'s birthday','example01.html');
		d.add(12,0,'Recycle Bin','example01.html','','','img/trash.gif');
		document.write(d);
		//-->