<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../public/admin/css/model.css" type="text/css" />
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
</head>
<body>

<div id="model_nav_box">
	<div id="box_nav_title">meta属性</div>
	<div id="box_nav_op"><a href="./meta_list.do">meta列表</a></div>
</div>
<div id="model_list_box">
	<div id="model_list_frame">
		<p></p>
	      <form action="./meta_msg.do" method="post">			
			<table width="100%" class="input_table">
			  <tr>
			    <td class="label">属性类型</td>
			    <td>
				    <input type="hidden" name="msg_id" value="${Request["msg_id"]}" id="msg_id" size="40">
				    <select name="attrid">
				    	<option></option>
						<#if Request["attr_list"]?exists>
							<#assign attrlist = Request["attr_list"]>
						</#if>
						<#if Request["attrid"]?exists>
							<#assign attrid = Request["attrid"]>
						</#if>						
						<#list attrlist as attritem>
							<option value="${attritem.attr_id}" <#if attrid = attritem.attr_id>selected</#if>>${attritem.attr_name} - ${attritem.attr_value}</option>
				    	</#list>
				    </select>
 			    </td>
			  </tr>
			  
			  <tr>
			    <td class="label">属性值</td>
			    <td><input name="meta_content" type="text" value="${Request["meta_content"]}" size="60"></td>
			  </tr>
			 
			  <tr align="center">
			    <td colspan="2">
			      <input type="hidden" name="act" value="${Request["act"]}">
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