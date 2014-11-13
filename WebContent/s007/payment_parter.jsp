<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Pay</title>
</head>
<body>

	
		<form method="post" action="./pay_confirm.do">
			<input type="hidden" name="orderid" value="${orderid}"/>
		<div style="margin:0 auto;width:500px;height:200px; border:1px solid green;margin-top:150px;padding:20px;">
			<p>调用第三方机构接口,进行支付。</p>
			<p>OrderNo:${orderid}</p>			
			<p>Bank: CCB</p>
			<p>Card No: 6226 0000 1111 2222 675</p>
			<p>Price:${price}</p>
			<p><input type="submit" value="Pay"/></p>
		</div>
		</form>
</body>
</html>