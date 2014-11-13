<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.lang.String"  %>
<%@taglib prefix="s" uri="/struts-tags" %>

	<form name="form" action="#">
		<table class="datalist">
			<tr>
				<th>Recipient</th>
				<th>Address</th>
				<th width="80px">PostalCode</th>
				<th width="120px" >Mobile Phone</th>
				<th width="130px">Date</th>
				<th width="50px">Default</th>		
			</tr>
			<s:iterator var="map" value="#request.adrrlist">
				<tr>
					<td style="text-align:left;"><s:property value="#map.recipient"/></td>
					<td><s:property value="#map.address"/></td>
					<td style="text-align:right;"><s:property value="#map.postal_code"/></td>
					<td style="text-align:right;"><s:property value="#map.phone_no"/></td>
					<td style="text-align:center;"><s:property value="#map.update_time"/></td>
					<td>
						<input type="radio" name="default_addr" <s:if test="#map.is_default==1">checked</s:if>/>
					</td>
				</tr>
			</s:iterator>
		</table>
	</form>
	<p></p>
	<div style="text-align:right;">
	<form action="./toaddress.do" method="post">
		<input type="submit" value="Add New Address"/>
	</form>
	</div>
