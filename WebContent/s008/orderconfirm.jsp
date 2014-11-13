<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.lang.String"  %>
<%@taglib prefix="s" uri="/struts-tags" %>

<form name="" action="/orderConfirmation/pay.do" method="post">
<s:set name="pre_order_id" value=""/>
<s:set name="all_fee" value="0.00"/>
<s:set name="ship_fee" value="0.00"/>
<s:set name="pro_fee" value="0.00"/>
<s:set name="fav_fee" value="0.00"/>
<s:iterator var="map" value="#request.orderlist">
	<s:set name="order_id" value="#map.order_id"/>
	<s:if test="#pre_order_id!=#order_id">
		<s:if test="#pre_order_id!=null">
			<p></p><p></p>
		</s:if>
	<input type="hidden" name="orderid" value="<s:property value="order_id"/>"/>
	<s:set name="pre_order_id" value="order_id"/>
		<div class="order_title">
			<table width=100%>
				<s:set name="all_fee" value="#map.order_trans_price"/>
				<s:set name="ship_fee" value="#map.postage"/>
				<s:set name="pro_fee" value="#map.order_fixed_price"/>
				<s:set name="fav_fee" value="#map.order_fav_price"/>

				<!-- 收货人信息 -->
				<tr>
					<td colspan='6'>
					<div><strong>Consignee information</strong></div>
					<div style="border:1px solid #ddd;">
						<s:iterator var="adrmap" value="#request.adrrlist">
							<div class="con_info">
								<input type="radio" name="default_addr" value="<s:property value="#adrmap.addr_id"/>" <s:if test="#adrmap.is_default==1">checked</s:if> />
								<s:property value="#adrmap.recipient"/>
								<s:property value="#adrmap.address"/>
								<s:property value="#adrmap.phone_no"/>
								<s:property value="#adrmap.postal_code"/>
							</div>
						</s:iterator>
					</div>
					</td>
				</tr>
				
				<!-- 支付及配送方式 -->
				<!--
				<tr>
					<td colspan='6'>
					<div>支付及配送方式 </div>
					<div>x</div>	
					</td>
				</tr>		
				-->
			</table>
		</div>
	</s:if>
	
	<table class="datalist1">
		<tr>
			<td width="80px">
				<img src="<s:property value="#map.pic_name_1"/>" width="80px" height="80px"/>
			</td>
			
			<td>
				<s:property value="#map.product_name"/>
				<p style="color:#666666;"><s:property value="#map.remark"/></p>
			</td>
			
			<td width="80px" style="text-align:right">
				$<s:property value="#map.trans_price"/>/<s:property value="#map.value_en"/>
			</td>
			
			<td width="80px" style="text-align:right">
				<s:property value="#map.product_count"/><s:property value="#map.value_en"/>
			</td>
			<td width="80px" style="text-align:right;color:#900;">
				<b>$<s:property value="#map.sum_price"/>
				<!-- 
					<s:text name="format.number">
						<s:param name="value" value="987654321"/>
					</s:text>
				 -->
				</b>
			</td>
		</tr>
	</table>	 
</s:iterator>

	

	<table width="100%">
		<tr>
			<td style="text-align:right;">
				<div style="color:#900;padding-right:10px;line-height:20px;">
					$<s:property value="#ship_fee"/>
				</div>
				<div style="color:#900;padding-right:10px;line-height:20px;">
					+ $<s:property value="#pro_fee"/>
				</div>
				<div style="color:#900;padding-right:10px;line-height:20px;">
					- $<s:property value="#fav_fee"/>
				</div>
				<div style="color:#900;font-size:20px;padding-right:10px;">
					$<s:property value="#all_fee"/>
				</div>
		 	</td>
		 </tr>
		<tr><td align="right" ><input type="submit" value="Confirm & Pay" /></td></tr>
	</table>
</form>