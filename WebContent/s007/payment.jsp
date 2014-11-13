<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>支付</title>
</head>
<body>
<form action="/s008/submit_orders.do">

	<input type="submit" name="payment" value="付款">
	<input type="hidden" name="pay_result" value="ok" >

</form>
</body>
</html>