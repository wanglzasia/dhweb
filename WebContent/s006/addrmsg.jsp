<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.lang.String"  %>
<%@taglib prefix="s" uri="/struts-tags" %>

<form id="form4" name="form4" action="/addr/addAddr.do">

	<input type="hidden" id="addr_id" name="addr_id" value="">
	<table style="color:black;width:100%;" >
	<tr>
		<td align="right" width="20%">Recipient</td>
		<td align="left" width="80%">
			<input type="text" id="recipient" name="recipient" value="" size="40">
		</td>
	</tr>
	
	<tr>
		<td align="right">Address</td>
		<td align="left">
			<input type="text" id="address" name="address" value="" size="60">
		</td>
	</tr>
	
	<tr>
		<td align="right">Postal Code</td>
		<td align="left">
			<input type="text" id="postal_code" name="postal_code" value="" size="20">
		</td>
	</tr>
	
	<tr>
		<td align="right">Mobile Phone</td>
		<td align="left">
			<input type="text" id="phone_no" name="phone_no" value="" size="20">
		</td>
	</tr>
	
	<tr >
		<td colspan="2" align="center">
			<input type="submit" id="submit" name="submit" value="Submit">
				<input type="reset" id="reset" name="reset" value="Reset">
			</td>
		</tr>
	</table>
</form>