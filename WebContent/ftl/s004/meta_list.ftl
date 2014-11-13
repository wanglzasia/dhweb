<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../public/admin/css/model.css" type="text/css" />
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
</head>
<body>

<div id="model_nav_box">
	<div id="box_nav_title">meta信息</div>
	<div id="box_nav_op"><a href="./meta_msg.do?act=toadd">添加meta信息</a></div>
</div>
<div id="model_list_box">
	<div id="model_list_frame">
		 <table class="data_list">
		<tr>
			<th width="10%">属性编号</th>
			<th >属性名称</th>
			<th width="10%">属性标示</th>
			<th width="50%">属性值</th>
			<th >备注</th>
			<th width="8%">操作</th>
		</tr>
		<#if Request["list"]?exists>
		<#assign metalist = Request["list"]>
		<#list metalist as metaitem>
			<tr>
				<td>${metaitem.msg_id}</td>
				<td>${metaitem.attr_name}</td>
				<td>${metaitem.attr_value}</td>
				<td>${metaitem.meta_content}</td>
				<td>${metaitem.attr_note}</td>
				<td>
					<a href="./meta_msg.do?act=tomod&msg_id=${metaitem.msg_id}&attrid=${metaitem.attrid}&meta_content=${metaitem.meta_content}">修改</a> 
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="./meta_msg.do?act=todel&msg_id=${metaitem.msg_id}&attrid=${metaitem.attrid}&meta_content=${metaitem.meta_content}">删除</a>
				</td>
			</tr>
		</#list>
		</#if>
	</table>
	</div>
<div>
</body>
</html>