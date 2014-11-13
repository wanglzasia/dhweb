<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../public/admin/css/model.css" type="text/css" />
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
</head>
<body>

<div id="model_nav_box">
	<div id="box_nav_title">快递公司管理</div>
	<div id="box_nav_op"><a href="./courier_view.do?act=toadd&id=0">添加快递公司</a></div>
</div>
<div id="model_list_box">
	
	<div id="model_list_frame">
				<table class="data_list">
					<tr>
				      <th width="30px">编码</th>
				      <th width="20%">公司名称</th>
				      <th width="50px">标准运费</th>
				      <th width="50px">运送时间</th>
				      <th width="250px">备注</th>
				      <th width="120px">操作</th>				      
				    </tr>
				    
				   <#if Request["list"]?exists>
				   <#assign list = Request["list"]>
						<#list list as item>
						    <tr>
						      <td>${item.courier_id?if_exists}</td>
						      <td>${item.courier_en_name?if_exists}</td>
						      <td align="right" >$${item.freight?if_exists}元</td>
						      <td align="right">${item.deliery_day?if_exists}天</td>
						      <td align="left">${item.note?if_exists}</td>
						      <td align="center">
						      	<a href="./courier_zonelist.do?act=tomod&courier_id=${item.courier_id?if_exists}" title="编辑运费">编辑运费</a>  |
						        <a href="./courier_view.do?act=tomod&id=${item.courier_id?if_exists}" title="编辑">编辑</a>  |
						        <a href="./courier_view.do?act=todel&id=${item.courier_id?if_exists}" title="删除">删除</a> 
						      </td>
						    </tr>
						  </#list>
					</#if>
				</table>
	</div>
	
<div>

</body>
</html>