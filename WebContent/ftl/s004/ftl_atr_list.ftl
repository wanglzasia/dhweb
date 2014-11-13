<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../public/admin/css/model.css" type="text/css" />
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
function loadatrlist(){
	if($("#ftl_id").val()!=""){
		$("#ftl_form").submit();
	}
}
</script>
</head>
<body>

<div id="model_nav_box">
	<div id="box_nav_title">模板属性管理</div>
</div>

<div id="model_nav_box">
	<div id="box_nav_title">
		<form action="./ftl_atrlist.do" method="post" id="ftl_form">
			<label>选择模板</label>
			<select name="ftl_id" onchange="loadatrlist()" id="ftl_id">
				<option></option>
				<#if Request["ftl_list"]?exists>
				<#assign ftllist = Request["ftl_list"]>
				<#list ftllist as ftllist_item>
					<option value="${ftllist_item.ftl_id}" <#if Request["ftl_id"]==ftllist_item.ftl_id>selected</#if> > ${ftllist_item.ftl_name}--${ftllist_item.file_name}</option>
				</#list>
				</#if>
			</select>
		</form>
	</div>

	<div id="box_nav_op"><a href="./ftlatr.do?act=toadd&ftl_id=${Request["ftl_id"]}">添加属性</a></div>
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
		<#if Request["ftl_attr_list"]?exists>
		<#assign ftlatrlist = Request["ftl_attr_list"]>
		<#list ftlatrlist as ftlatrlist_item>
			<tr>
				<td>${ftlatrlist_item.attr_id}</td>
				<td>${ftlatrlist_item.attr_name}</td>
				<td>${ftlatrlist_item.attr_label}</td>
				<td>${ftlatrlist_item.attr_value}</td>
				<td>${ftlatrlist_item.note}</td>
				<td>
					<a href="./ftlatr.do?act=tomod&ftl_id=${Request["ftl_id"]}&attr_id=${ftlatrlist_item.attr_id}">修改</a> 
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="./ftlatr.do?act=todel&ftl_id=${Request["ftl_id"]}&attr_id=${ftlatrlist_item.attr_id}">删除</a>
				</td>
			</tr>
		</#list>
		</#if>
	</table>
	</div>
<div>

</body>
</html>
