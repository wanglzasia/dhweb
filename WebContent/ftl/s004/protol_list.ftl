<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../public/admin/css/model.css" type="text/css" />
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script language="javascript">
<!--
$(document).ready(function(){
	CKEDITOR.replace('pro_content_en');
	CKEDITOR.replace('pro_content');
});
//-->
</script>
</head>
<body>

<div id="model_nav_box">
	<div id="box_nav_title">协议管理</div>
</div>
<div id="model_list_box">
	
	<div id="model_list_frame">
		<p></p>
		<form action="./protoco_upProtolmsg.do" enctype="multipart/form-data" method="post">
			<input name="pro_id" type="hidden" value="1">
			<#if Request["list"]?exists>
		    <#assign list = Request["list"]>
			<#list list as item>		
			<table width="100%" class="input_table">
			  <tr>
			    <td width="100px" >协议中文名称</td>
			    <td><input name="pro_name" type="text" value="${item.pro_name?if_exists}" size="60">
			    </td>
			  </tr>
			  
			  <tr>
			    <td width="100px" >协议英文名称</td>
			    <td><input name="pro_name_en" type="text" value="${item.pro_name_en?if_exists}" size="60"></td>
			  </tr>
			  
			  <tr>
			    <td width="100px" >协议内容(英文)</td>
			    <td>
 					<textarea cols='50' rows='6' name='pro_content_en' id='pro_content_en'>${item.pro_content_en?if_exists}</textarea>
			    </td>
			  </tr>

			  <tr>
			    <td width="100px" >协议内容(中文)</td>
			    <td>
 					<textarea cols='50' rows='6' name='pro_content' id='pro_content'>${item.pro_content?if_exists}</textarea>
			    </td>
			  </tr>
		
			  <tr align="center">
			    <td colspan="2">
			      <input type="hidden" name="act" value="mod">
			      <input type="submit" class="button" name="Submit" value=" 确定 ">
			      <input type="reset" class="button" name="Reset" value=" 重置 ">
			    </td>
			  </tr>
			</table>
			</#list>
			</#if>
		</form>
	
	</div>
	
<div>

</body>
</html>