<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../public/admin/css/model.css" type="text/css" />
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
</head>
<body>

<div id="model_nav_box">
	<div id="box_nav_title">产品模板关联</div>
	<div id="box_nav_op"><a href="./model_kindlist.do">列表</a></div>
</div>
<div id="model_list_box">
	<div id="model_list_frame">
		<p></p>
	       <form action="./model_updatekm.do"  method="post">			
			<table width="100%" class="input_table">
			  <tr>
			    <td class="label">产品类型</td>
			    <td>${Request["kindname"]}
			    	<input type="hidden" name="kind_code" value="${Request["kindcode"]}"/>
			    </td>
			  </tr>

			  <tr>
			    <td class="label">对应模板</td>
			    <td>
			    	<select name="ftl">
			    		<option value="0"></option>
			    	<#if Request["tpllist"]?exists>
					<#assign tlp_lst = Request["tpllist"]>
					<#list tlp_lst as tlp_lst_item>
						<option value="${tlp_lst_item.ftl_id}" <#if Request["tpl_id"]==tlp_lst_item.ftl_id>selected</#if> >${tlp_lst_item.ftl_name}--${tlp_lst_item.file_name}</option>
					</#list>
					</#if>
					</select>
			    </td>
			  </tr>
			  			  
			  <tr align="center">
			    <td colspan="2">
			      <input type="submit" class="button" name="Submit" value=" 确定 ">
			      <input type="reset" class="button" name="Reset" value=" 重置 ">
			    </td>
			  </tr>
			</table>
		</form>
	</div>
<div>
</body>
</html>