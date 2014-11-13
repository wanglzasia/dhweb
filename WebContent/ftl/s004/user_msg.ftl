<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../public/admin/css/model.css" type="text/css" />
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
</head>
<body>

<div id="model_nav_box">
	<div id="box_nav_title">工号信息</div>
	<div id="box_nav_op"><a href="./user_list.do">工号列表</a></div>
</div>
<div id="model_list_box">
	<div id="model_list_frame">
		 <p></p>
		 <form action="./user_msg.do"  method="post">			
			<table width="100%" class="input_table">
			  <tr>
			    <td class="label">用户名称</td>
			    <td>
			       	<input name="uname" type="text" value="${Request["uname"]}" size="60">
			    	<input name="uid" type="hidden" value="${Request["uid"]}" size="60">
 			    </td>
			  </tr>
			 	
			  
			  
			  <tr>
			    <td class="label">密码</td>
			    <td>
			    	<input name="pwd" type="text" value="" size="60">
			    </td>
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