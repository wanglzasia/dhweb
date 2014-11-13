<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.lang.String"  %>
<%@taglib prefix="s" uri="/struts-tags" %>

<s:set name="pre_order_id" value=""/>

<form method="post" action="/seller/os_sendOk.do">

<s:iterator var="map" value="#request.orderlist">
	<s:set name="order_id" value="#map.order_id"/>
	<s:if test="#pre_order_id!=#order_id">
		<s:if test="#pre_order_id!=null">
			<p></p><p></p>
		</s:if>
	<s:set name="pre_order_id" value="order_id"/>
		<div class="order_title">
			<table width=100%>
				<!-- 订单详情 -->
				<tr>
					<td class="order_title_login" 	width=10%>
						<s:property value="login_no"/>
					</td>
					<td class="order_title_orderid" width=12%>No:
						<s:property value="order_id"/>
						<input type="hidden" name="order_id" value="<s:property value="order_id"/>"/>
					</td>
					<td class="order_title_ordertime"  width=16%>
						<s:property value="order_time"/>
					</td>
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
					<td class="order_title_opreate" width=12% style="text-align:right">
						 &nbsp;
					</td>
				</tr>
				<!-- 地址信息 -->
				<tr>
					<td colspan="6">
						<s:property value="user_name"/>
						<s:property value="addr"/>
						<s:property value="mobile_phone"/>
						<s:property value="postal_code"/>
					</td>
				</tr>
			</table>				
			
			
			<table style="background-color:#fff;width:100%;border:1px solid #ddd;padding-top:10px;padding-left:10px;padding-right:10px;padding-bottom:10px;">
				<!-- 订单流转信息 -->			
				<tr>
					<td colspan="2" style="height:30px;line-height:30px;"><strong>物流信息:${c_name}</strong>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<strong>物流单号:<s:property value="waybill_no"/></strong>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<s:iterator var="map1" value="#request.flowlist">
							<div>
								<span><s:property value="#map1.op_time"/></span>
								<span><s:property value="#map1.op_login"/></span>
								<span><s:property value="#map1.func_name"/></span>
							</div>
						</s:iterator>
					</td>
				</tr>
				
				<s:if  test="#map.order_status==2">
					<tr>
						<td colspan="2">
							<label>快递公司:</label>
								<select name="courier_id">
									<s:iterator var="map_c" value="#request.courierlist">
										<option value="<s:property value="#map_c.courier_id"/>"><s:property value="#map_c.courier_cn_name"/></option>
									</s:iterator>
								</select>
							<label>运单号码:</label>
								<input type="text" name="waybill_no" value=""/>
								<input type="submit" value="       确定        "/>
						</td>
					</tr>
				</s:if>
			</table>									
			
		</div>
	</s:if>
	
	
	
	
	<!-- 产品列表 -->
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


</form>

