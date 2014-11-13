<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
<link rel="stylesheet" href="../public/css/default.css" type="text/css" />
<title>增加新地址</title>

<script type="text/javascript">
	function add_addr(){
		$.post("/addr/addAddr_ajax.do",
				{
		    		recipient:$("#recipient").val(),
		    		address:$("#address").val(),
		    		postal_code:$("#postal_code").val(),
		    		phone_no:$("#phone_no").val()
		    		
		    	},
		    	function(msg){
		    		
		    		var arry = msg.split("|");
		    		
		    		if(arry[0] == "ok"){
		    			alert("添加新地址成功！");
		    			
		    			//$("#addr_id").value=arry[1];
		    			
		    			var addr_id = arry[1];
		    			
		    			//alert(addr_id);
		    			
		    			var recipient = $("#recipient").val();
		    			var address = $("#address").val();
		    			var postal_code = $("#postal_code").val();
		    			var phone_no = $("#phone_no").val();
		    			
		    			/////返回父窗口值
		    			
		    			var return_msg = "";
		    			
		    			return_msg += "<tr >";
						return_msg += "<td align='left'>";
						return_msg += "	<input type='radio' id='addr_id_" + addr_id + "' name='addr_id' value='" + addr_id + "'/>";
						return_msg += recipient+"&nbsp;";
						return_msg += phone_no+"&nbsp;";
						return_msg += address+"&nbsp;";
						return_msg += postal_code+"&nbsp;";
						return_msg += "</td>";
						return_msg += "</tr>";
						//return_msg += "<tr id='new_addr'></tr>";
		    			
						window.returnValue=return_msg;
						
		    			window.close();
		    		}else{
		    			alert("添加新地址失败！");
		    			window.close();
		    		}
		    		
		    	});
	}
	
</script>

</head>
<body>
	<form id="form4" name="form4" action="/addr/addAddr.do">
		<input type="hidden" id="addr_id" name="addr_id" value="">
		
		<div >
			<table style="color:black" width="60%" align="center">
			
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td></td>
				</tr>
				<tr>
					<td align="right" width="40%">收件人姓名</td>
					<td align="left" width="60%">
						<input type="text" id="recipient" name="recipient" value="" size="40">
					</td>
				</tr>
				
				<tr>
					<td align="right">收件地址</td>
					<td align="left">
						<input type="text" id="address" name="address" value="" size="60">
					</td>
				</tr>
				
				<tr>
					<td align="right">邮政编码</td>
					<td align="left">
						<input type="text" id="postal_code" name="postal_code" value="" size="20">
					</td>
				</tr>
				
				<tr>
					<td align="right">联系电话</td>
					<td align="left">
						<input type="text" id="phone_no" name="phone_no" value="" size="20">
					</td>
				</tr>
				
				<tr >
					<td colspan="2" align="center">
						<input type="button" id="submit" name="submit" value="Submit">
						<input type="reset" id="reset" name="reset" value="Reset">
					</td>
				</tr>
			
			</table>
		</div>
	</form>
</body>
</html>