<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SelfStyle1</title>
</head>
<body>
<form action="/seller/upload.do" method="post" enctype="multipart/form-data">
	<input type="file" name="upload"/>
	<input type="text" name="langCode" value="1" />
	<input type="text" name="CKEditorFuncNum" value="2"/>
	<input type="text" name="CKEditor" value="3"/>
	<input type="submit" value="提交"/>
</form>
</body>
</html>