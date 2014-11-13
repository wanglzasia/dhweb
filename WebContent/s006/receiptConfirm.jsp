<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="form_dialog" style="width:350px;margin-top:150px;">
	
	<div class="form_dialog_title">
		<span class="form_title"><strong>Confirm Receipt</strong></span>
		<span class="form_botton"><a href="javascript:closeWindow();" title="Close" class="close">×</a></span>
	</div>
	
	<div class="form_content">
		<form method="post" action="/member/confirmReceipt.do">
	 		<table width="100%">
				<tr style="text-align:center;">
					<%String orderid = (String)request.getParameter("oid");%>
					<td>Order No:<%=orderid %>
						<input type="hidden" name="order_id" value="<%=orderid %>"/>
					</td>
				</tr>
				
				 
				
				<tr>
					<td style="text-align:center;">
						<input type="submit" value="Confirm"/>
						<input type="button" value="Cancel" onclick="closeWindow()"/>
					</td>
				</tr>
			</table>
		</form>
	</div>	
</div>
