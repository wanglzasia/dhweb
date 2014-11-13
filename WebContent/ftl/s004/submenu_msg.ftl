<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../public/admin/css/model.css" type="text/css" />
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
</head>
<body>

<div id="model_nav_box">
	<div id="box_nav_title">二级菜单</div>
	<div id="box_nav_op"><a href="/admin/sm_sb_menu.do?">添加列表</a></div>
</div>
<div id="model_list_box">
	
	<div id="model_list_frame">

	<p></p>
	       <form action="./sm_op_menu.do"  method="post">	
	       		
			<table width="100%" class="input_table">
			  <tr>
			    <td class="label">英文名称</td>
			    <td><input type="text" name="menu_name_en" value="${Request["menu_name_en"]}"/></td>
			  </tr>
			  <tr>
			    <td class="label">中文名称</td>
			    <td><input type="text" name="menu_name" value="${Request["menu_name"]}"/></td>
			  </tr>
			  			
			  <tr>
			    <td class="label">连接地址</td>
			    <td><input type="text" name="http_addr" value="${Request["http_addr"]}"/></td>
			  </tr>

			  <tr>
			    <td class="label">排序</td>
			    <td><input type="text" name="m_index" value="${Request["m_index"]}"/></td>
			  </tr>			  			
			  			  
			  <tr align="center">
			    <td colspan="2">
			      <input type="hidden" name="menu_id" value="${Request["menu_id"]}"/>
			      <input type="hidden" name="act" value="${Request["act"]}"/>
			      <input type="submit" class="button" name="Submit" value=" 确定 ">
			      <input type="reset"  class="button" name="Reset" value=" 重置 ">
			    </td>
			  </tr>
			  
			</table>
		</form>

	</div>
	
<div>

</body>
</html>