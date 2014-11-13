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
	CKEDITOR.replace('mail_body');
});
//-->
</script>
</head>

<body>

<div id="model_nav_box">
	<div id="box_nav_title">邮件管理</div>
</div>
<div id="model_list_box">
	<div id="model_list_frame">
		<p></p>
	       <form action="./mailcfg.do"  method="post">			
			<table width="100%" class="input_table">
			  <tr>
			    <td class="label">邮件标题</td>
			    <td>
			    	<input name="mail_title" type="text" value="${Request["mail_title"]}" size="60">
 			    </td>
			  </tr>
			  
			  <tr>
			    <td class="label">邮件正文</td>
			    <td>
			    	<textarea cols='50' rows='6' name='mail_body' id='mail_body'>${Request["mail_body"]}</textarea>
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
		</form>
	</div>
<div>
</body>
</html>