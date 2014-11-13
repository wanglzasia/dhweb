<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../public/admin/css/model.css" type="text/css" />
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
</head>
<body>

<div id="model_nav_box">
	<div id="box_nav_title">轮播广告管理</div>
	<div id="box_nav_op"><a href="./toadvmsg.do?act=toadd&id=0">添加广告</a></div>
</div>
<div id="model_list_box">
	
	<div id="model_list_frame">
				<table class="data_list">
					<tr>
				      <th width="38%">轮播图片地址</th>
				      <th width="32%">轮播图片链接</th>
				      <th>图片说明</th>
				      <th width="50px">排序</th>
				      <th width="80px">操作</th>				      
				    </tr>
				    
				   <#if Request["list"]?exists>
				   <#assign list = Request["list"]>
						<#list list as item>
						    <tr>
						      <td>${item.pic_addr?if_exists}</td>
						      <td><a href="${item.pic_link?if_exists}" target="_brank">${item.pic_link?if_exists}</a></td>
						      <td align="left" >${item.pic_note?if_exists}</td>
						      <td align="right">${item.pic_order?if_exists}</td>
						      <td align="center">
						        <a href="./toadvmsg.do?act=tomod&id=${item.adv_id?if_exists}&img_src=${item.pic_addr?if_exists}&img_url=${item.pic_link?if_exists}&img_text=${item.pic_note?if_exists}&img_sort=${item.pic_order?if_exists}" title="编辑">编辑</a> |
						        <a href="./toadvmsg.do?act=todel&id=${item.adv_id?if_exists}&img_src=${item.pic_addr?if_exists}&img_url=${item.pic_link?if_exists}&img_text=${item.pic_note?if_exists}&img_sort=${item.pic_order?if_exists}" title="删除">删除</a> 
						      </td>
						    </tr>
						  </#list>
					</#if>
				</table>
	</div>
	
<div>

</body>
</html>