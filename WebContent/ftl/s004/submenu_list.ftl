<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../public/admin/css/model.css" type="text/css" />
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
</head>
<body>

<div id="model_nav_box">
	<div id="box_nav_title">二级菜单</div>
	<div id="box_nav_op"><a href="./sm_sb_menu_msg.do?act=toadd&menu_id=xxx">添加菜单</a></div>
</div>
<div id="model_list_box">
	
	<div id="model_list_frame">
				<table class="data_list">
					<tr>
				      <th width="50px">菜单ID</th>
				      <th width="20%">英文名</th>
				      <th width="20%">中文名</th>
				      <th width="30%">连接地址</th>
				      <th width="50px">排序</th>
				      <th width="80px">操作</th>				      
				    </tr>
				    
				   <#if Request["list"]?exists>
				   <#assign list = Request["list"]>
						<#list list as item>
						    <tr>
						      <td>${item.menu_id?if_exists}</td>
						      <td align="left">${item.menu_name_en?if_exists}</td>
						      <td align="left" >${item.menu_name?if_exists}</td>
						      <td align="left">${item.http_addr?if_exists}</td>
						      <td align="center">${item.m_index?if_exists}</td>
						      <td align="center">
						       		<a href="./sm_sb_menu_msg.do?act=tomod&menu_id=${item.menu_id?if_exists}">修改</a>
						       		&nbsp;&nbsp;&nbsp;&nbsp;
						       		<a href="./sm_sb_menu_msg.do?act=todel&menu_id${item.menu_id?if_exists}">删除</a>
						      </td>
						    </tr>
						  </#list>
					</#if>
				</table>
	</div>
	
<div>

</body>
</html>