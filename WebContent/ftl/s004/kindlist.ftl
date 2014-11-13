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


<div id="model_list_box">
	<div id="model_list_frame">
	<table class="data_list">
		<tr>
			<th width="8%">属性编号</th>
			<th width="15%">属性名称</th>
			<th width="15%">属性标示</th>
			<th width="5%">层级</th>
			<th width="5%">模板ID</th>
			<th width="15%">模板名称</th>
			<th width="10%">模板文件</th>
			<th width="15%">操作</th>
		</tr>
		<#if Request["kindlist"]?exists>
		<#assign kindlist = Request["kindlist"]>
		<#list kindlist as kindlist_item>
			<tr>
				<td>${kindlist_item.kind_code}</td>
				<td>${kindlist_item.kind_name}</td>
				<td>${kindlist_item.kind_name_en}</td>
				<td>${kindlist_item.denorm_level}</td>
				<td>${kindlist_item.ftl_id}</td>
				<td>${kindlist_item.ftl_name}</td>
				<td>${kindlist_item.file_name}</td>
				<td>
					<a href="/admin/model_toupdatekm.do?kind_code=${kindlist_item.kind_code}&kind_name=${kindlist_item.kind_name}&ftl=${kindlist_item.ftl_id}">关联模板</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="/admin/emodel_prview_${kindlist_item.kind_code}.do?kind_code=${kindlist_item.kind_code}" target="_blank">预览</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="/admin/emodel_genHtml_${kindlist_item.kind_code}.do?kind_code=${kindlist_item.kind_code}" target="_blank">生成文件</a>
				</td>
			</tr>
		</#list>
		</#if>
	</table>
	</div>
<div>

</body>
</html>
