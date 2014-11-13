<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.lang.String"  %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/tld/OptionListTag.tld" prefix="OptionList" %>

<div style="margin: 0px auto;background-color:#fbfbfb;border:1px solid #eceded;">
	<div style="padding:20px;">
		<form name="form" action="#">
			Order Number:
			<input type="text" style="width:120px"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			Order Status:
			<OptionList:OptionList name="order_stat" id="testa" cssclass="jj" style="width:120px;" code="order_status" order="order_status" key="status_name_en as value" table="dorderstatmsg" keyAlis="value"/>		

			<input type="submit" value="Search"/>
		</form>
	</div>
</div>
<p></p><p></p>
<s:set name="pre_order_id" value=""/>

<s:iterator var="map" value="#request.orderlist">
	<s:set name="order_id" value="#map.order_id"/>
	<s:if test="#pre_order_id!=#order_id">
		<s:if test="#pre_order_id!=null">
			<p></p><p></p>
		</s:if>
	<s:set name="pre_order_id" value="order_id"/>
		<div class="order_title">
			<table width=100%>
				<tr>
					<!-- 卖家工号 -->
					<td class="order_title_login" 	width=10%><a href="#"><s:property value="login_no"/></a></td>
					<!-- 订单编号 -->
					<td class="order_title_orderid" width=12%>No:
					<a href="/member/orderdetail.do?oid=<s:property value="order_id"/>"><s:property value="order_id"/></a>
					</td>
					<!-- 下单时间 -->
					<td class="order_title_ordertime"  width=16%><s:property value="order_time"/></td>
					<!-- 价格信息 -->
					<td class="order_title_price" width=40% style="text-align:right" >
						$<s:property value="postage"/>(Post) +
						$<s:property value="order_fixed_price"/>(Fixed) -
						$<s:property value="order_fav_price"/>(Fav) =
						<b style="color:#900;">$<s:property value="order_trans_price"/></b>
					</td>
					<!-- 订单状态 -->
					<td class="order_title_status" width=10% style="text-align:right" >
						<s:property value="status_name_en"/>
					</td>
					<!-- 操作 -->
					<td class="order_title_opreate" width=12% style="text-align:right">
						<s:if test="order_status==1">
							<a href="/orderConfirmation/confirmorder.do?orderid=<s:property value="order_id"/>">Pay</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="/member/removeOrder.do?order=<s:property value="order_id"/>">Remove</a>
						</s:if>

					</td>
				</tr>
				<!--
				<tr>
					<td>Note</td>
					<td colspan="5" >Note</td>
				</tr>
				-->
			</table>
		</div>
	</s:if>
	
	<table class="datalist1">
		<tr>
			<td width="80px"><img src="<s:property value="#map.pic_name_1"/>" width="80px" height="80px"/></td>
			<td>
				<s:property value="#map.product_name"/>
				<p style="color:#666666;"><s:property value="#map.remark"/></p>
			</td>
			<td width="80px" style="text-align:right">$<s:property value="#map.trans_price"/>/<s:property value="#map.value_en"/></td>
			<td width="80px" style="text-align:right"><s:property value="#map.product_count"/><s:property value="#map.value_en"/></td>
			<td width="80px" style="text-align:right;color:#900;"><b>$<s:property value="#map.sum_price"/></b></td>
		</tr>
	</table>
</s:iterator>

