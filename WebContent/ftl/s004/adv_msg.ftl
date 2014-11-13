<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../public/admin/css/model.css" type="text/css" />
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
</head>
<body>

<div id="model_nav_box">
	<div id="box_nav_title">轮播广告</div>
	<div id="box_nav_op"><a href="./toadvlist.do">轮播广告列表</a></div>
</div>
<div id="model_list_box">
	<div id="model_list_frame">
		<p></p>
	       <form action="./toadvmsg.do" enctype="multipart/form-data" method="post">			
			<table width="100%" class="input_table">
			  <tr>
			    <td class="label">图片地址</td>
			    <td><input type="file" name="upload" value="" id="some_name" size="40">
			    <br><input name="img_src" type="text" value="${Request["img_src"]}" size="60">
			    <br><span class="notice-span" style="display:block" id="width_height">此模板的图片标准宽度为：700 标准高度为：398</span>
			    </td>
			  </tr>
			  
			  <tr>
			    <td class="label">图片链接</td>
			    <td><input name="img_url" type="text" value="${Request["img_url"]}" size="60"></td>
			  </tr>
			  
			  <tr>
			    <td class="label">图片说明</td>
			    <td><input name="img_text" type="text" value="${Request["img_text"]}" size="60"></td>
			  </tr>
			  
			  <tr>
			    <td class="label">排序</td>
			    <td><input name="img_sort" type="text" value="${Request["img_sort"]}" size="4" maxlength="3"></td>
			  </tr>
			  
			  <tr align="center">
			    <td colspan="2">
			      <input type="hidden" name="id" value="${Request["id"]}">
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