<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<style type="text/css">
body{font-size:14px;}
label{display:block;width:100px;text-align:right;border:1px solid red;}
#log_box{width:350px;height:180px;}
#log_box{margin:0 auto;margin-top:200px;}
</style>
</head>
<body>
	<div id="log_box">
		<table>
		<form action="./login.do" method="post">
			<tr><td align="right">用户</td><td><input type="text" name="username" value=""/></td></tr>
			<tr><td align="right">密码</td><td><input type="password" name="password" value=""/></td></tr>
			<tr><td align="right">验证码</td><td><input type="text" name="validcode" value=""/><img src="/imageValid"/></td></tr>
			<tr><td colspan="2"><center><input type="submit" value="登陆"/><input type="reset" value="重置"/></center></td></tr>	
		</table>
		</form>

		${msg1}
		
	</div>
</body>
</html>