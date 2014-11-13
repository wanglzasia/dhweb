<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*"  %>
<%@ page import="com.dh.ora.s003.service.OrderSrvImpl"  %>
<div style="width:780px;padding:10px 10px 10px 10px;border:1px solid #eceded;">

<style type="text/css">
.order_title {color:#5f6f81;background-color:#F5F8FD;height:20px;padding-top:10px;padding-left:10px;}
.order_title div{width:200px;float:left;}
.order_title2{line-height:20px;margin-top:20px;}
</style>


 <div>
 
 	<s:iterator var="map" value="#request.list">  
	 	<div class="order_title" >
	 		<div style='width:120px;'><label>订单编号:<s:property value="#map.order_id" /></label></div>
	 		<div><label>下单时间:<s:property value="#map.order_time" /></label></div>
	 		<div style='width:120px;'><label>订单状态:<s:property value="#map.order_status" /></label></div> 	 		
	 	</div>
	 	
	 	<div class="clear"></div>
	 	
	 	<table width=100%>
	 		<tr>
	 			<td>产品编号</td>
	 			<td>产品名称</td>
	 			<td style="text-align:right;">单价</td>
	 			<td style="text-align:right;">数量</td>
	 			<td style="text-align:right;">总价</td>
	 		</tr>
		 	<div class="order_title1">
		 	
			 	<s:iterator var="map1" value="#request.proList">  
					  <tr>
					    <td width="80px"><s:property value="#map1.product_id" /></td>
					    <td style="width:50%"><s:property value="#map1.product_name" /></td>
					    <td style="width:120px;text-align:right;">
					    	<span style="text-decoration:line-through;">
					    		$<s:property value="#map1.fixed_price" />
					    	</span>
					    	$<s:property value="#map1.trans_price" />
					    </td>
					    <td style="text-align:right;"><s:property value="#map1.product_count" /><s:property value="#map1.value" /></td>
					    <td style="text-align:right;">$<s:property value="#map1.sum_price" /></td>
					  </tr>	    	
				</s:iterator>		
				
			  	    				 
		 	</div>	 	
	 	</table>
	 	
	 	<form action="/seller/delorder.do" method="post">
	 		<input type="hidden" name="order_id" value="<s:property value="#map.order_id" />"/>
		 	<div  class="order_title2" style="text-align:right;"> 
		 				<div>产品价格：$<s:property value="#map.fixed_price" />
		 				</div>
		 				<div>+邮费：$<s:property value="#map.postage" /></div>
		 				<div>-订单优惠：$<s:property value="#map.fav_price" />
		 				</div>
		 				<div style="height:1px;background-color:#eceded; width:200px;float:right;margin-top:10px;"></div>
		 				<div class="clear"></div>
		 				<div style="color:#FF4400;font-weight:bold;font-size:14px;">
		 				应收金额：$ <label id="trans_price_1"><s:property value="#map.trans_price" /></label></div>
		 				<div><input type="submit" value="确认删除" /> </div>
		 	</div>
		</form>
		
		<div> 
			<div style="width:100%">
		    	<s:property value="#map.user_name" />&nbsp;&nbsp;&nbsp;&nbsp;
		    	<s:property value="#map.addr" />&nbsp;&nbsp;&nbsp;&nbsp;
		    	<s:property value="#map.mobile_phone" />
	    	</div>
	    </div>
			 
 	</s:iterator>
 </div>

</div>
