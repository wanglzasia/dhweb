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
	CKEDITOR.replace('shop_footer');
	CKEDITOR.replace('shop_footer_cn');
});
//-->
</script>
</head>
<body>

<div id="model_nav_box">
	<div id="box_nav_title">基本设置</div>
</div>
<div id="model_list_box">
	<div id="model_list_frame">
		<p></p>
	       <form action="./shopcfg.do" enctype="multipart/form-data" method="post">			
			<table width="100%" class="input_table">
			  <tr>
			    <td width="100px" >网站名称</td>
			    <td><input name="shop_name" type="text" value="${Request["shop_name"]}" size="60">
			    </td>
			  </tr>
			  
			  <tr>
			    <td width="100px" >网站域名</td>
			    <td><input name="shop_home_url" type="text" value="${Request["shop_home_url"]}" size="60"></td>
			  </tr>
			  
			  <tr>
			    <td width="100px" >页脚信息(英文)</td>
			    <td>
 					<textarea cols='50' rows='6' name='shop_footer' id='shop_footer'>${Request["shop_footer"]}</textarea>
			    </td>
			  </tr>

			  <tr>
			    <td width="100px" >页脚信息(中文)</td>
			    <td>
 					<textarea cols='50' rows='6' name='shop_footer_cn' id='shop_footer_cn'>${Request["shop_footer_cn"]}</textarea>
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