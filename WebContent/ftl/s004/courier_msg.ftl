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
	<div id="box_nav_op"><a href="./courier_list.do">快递公司列表</a></div>
</div>
<div id="model_list_box">
	
	<div id="model_list_frame">
	   <p></p>
		<table width="100%" class="input_table">
			<form  action="./courier_opmsg.do" method="post">
			
				<tr>
					<td class="label">公司名称英文</td>
					<td><input type="text" name="courier_en_name" value="${Request["courier_en_name"]}" size="40"/></td>
				<tr>
				<tr>
					<td class="label">公司名称中文</td>
					<td><input type="text" name="courier_cn_name" value="${Request["courier_cn_name"]}" size="40"/></td>
				<tr>			
				<tr>
					<td class="label">标准运费</td>
					<td><input type="text" name="freight" size="40" value="${Request["freight"]}"/>
						500g物品运送到美国的费用
					</td>
				<tr>
				<tr>
					<td class="label">运输时间</td>
					<td><input type="text" name="deliery_day" size="40" value="${Request["deliery_day"]}"/>
					500g物品运动到美国的时间
					</td>
				<tr>				
				<tr>
					<td class="label">备注</td>
					<td><input type="text" name="note"  size="40" value="${Request["note"]}"/></td>
				<tr>				
				  <tr align="center">
				    <td colspan="2" >
				      <input type="hidden" name="courier_id" value="${Request["courier_id"]}">
				      <input type="hidden" name="act" value="${Request["act"]}">
				      <input type="submit" class="button" name="Submit" value=" 确定 ">
				      <input type="reset" class="button" name="Reset" value=" 重置 ">
				    </td>
				  </tr>
			</form>
		</table>
		
	</div>
	
<div>

</body>
</html>