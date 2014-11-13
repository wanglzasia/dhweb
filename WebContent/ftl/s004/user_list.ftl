<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../public/admin/css/model.css" type="text/css" />
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
</head>
<body>

<div id="model_nav_box">
	<div id="box_nav_title">工号信息</div>
	<div id="box_nav_op"><a href="./user_msg.do?act=toadd">添加工号</a></div>
</div>
<div id="model_list_box">
	<div id="model_list_frame">
		 <table class="data_list">
		<tr>
			<th >工号编号</th>
			<th >登陆工号</th>
			<th >注册日期</th>
			<th >更新日期</th>
			<th width="8%">操作</th>
		</tr>
		<#if Request["usrlist"]?exists>
		<#assign usrlist = Request["usrlist"]>
		<#list usrlist as usritem>
			<tr>
				<td>${usritem.user_id}</td>
				<td>${usritem.user_name}</td>
				<td>${usritem.reg_date}</td>
				<td>${usritem.update_date}</td>				
				<td>
					<a href="./user_msg.do?act=tomod&uid=${usritem.user_id}&uname=${usritem.user_name}">修改</a> 
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="./user_msg.do?act=todel&uid=${usritem.user_id}&uname=${usritem.user_name}">删除</a>
				</td>
			</tr>
		</#list>
		</#if>
	</table>
	</div>
<div>
</body>
</html>