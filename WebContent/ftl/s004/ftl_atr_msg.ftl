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
	<div id="box_nav_title">属性管理</div>
</div>


<div id="model_list_box">
	<div id="model_list_frame">
	<p></p>
	<table width="100%" class="input_table">
		<form  action="./ftlatr.do" method="post">
		
			<tr>
				<td class="label">属性名称</td>
				<td><input type="text" name="attr_name" value="${Request["attr_name"]}"/></td>
			<tr>
			<tr>
				<td class="label">属性标签</td>
				<td><input type="text" name="attr_label" value="${Request["attr_label"]}"/></td>
			<tr>			
			<tr>
				<td class="label">属性值</td>
				<td><input type="text" name="attr_value" size="60" value="${Request["attr_value"]}"/></td>
			<tr>				
			<tr>
				<td class="label">备注</td>
				<td><input type="text" name="note" value="${Request["attr_note"]}"/></td>
			<tr>				
			  <tr align="center">
			    <td colspan="2" >
			      <input type="hidden" name="ftl_id" value="${Request["ftlid"]}">
			      <input type="hidden" name="act" value="${Request["act"]}">
			      <input type="hidden" name="attr_id" value="${Request["attr_id"]}">
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
