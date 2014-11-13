<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*"  %>
<%@ taglib uri="/WEB-INF/tld/OptionListTag.tld" prefix="OptionList" %>
<%@ page import="com.dh.ora.s003.service.OrderSrvImpl"  %>
<style type="text/css">
.orderfrm{border:1px #eceded solid; padding:10px 10px 10px 10px;margin-top:10px;overflow:hidden;}
.orderfrm:hover{border:1px #ffbd7f solid ;}
.item1{color:#5f6f81;overflow:hidden;background-color:#F5F8FD;padding:10px 5px 10px 5px;}
.item2{padding-top:10px;padding-bottom:10px;color:#5f6f81;overflow:hidden;}
.item3{color:#5f6f81;overflow:hidden;}
.item4{color:#5f6f81;overflow:hidden;padding-top:10px;text-align:right;}
.order_info{float:left;}
.order_op{float:right;}
.jj1{width:150px;}
.c_table{border-collapse:collapse; cell-padding:0; cell-spacing:0;color: #5f6f81;width: 100%;}
.c_table tr td {border-bottom:1px solid #eceded; text-align:left; text-valign:center;display: table-cell;vertical-align: inherit;padding:0px;}
</style>
<div style="width:780px;padding:10px 10px 10px 10px;border:1px solid #eceded;">
<div>
	<form action="/seller/orderlist.do" method="post">
		<div style="margin: 0px auto;background-color:#fbfbfb;border:1px solid #eceded;">
		<div style="padding:20px; ">
			<div style="line-height:30px;">
				<label>订单号码：</label><input type="text" name="pro_kind" style="width:100px;" value=""/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<label>产品编号：</label><input type="text" name="pro_kind" style="width:100px;" value=""/>	
				&nbsp;&nbsp;&nbsp;&nbsp;
				<label>产品名称：</label><input type="text" name="pro_kind" style="width:100px;" value=""/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<label>买家名称：</label><input type="text" name="pro_kind" style="width:100px;" value=""/>
			</div>
			<div style="line-height:30px;">
				<label>运单号码：</label><input type="text" name="pro_kind" style="width:100px;" value=""/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<label>产品名称：</label><input type="text" name="pro_kind" style="width:100px;" value=""/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<label>订单状态：</label>
				<OptionList:OptionList name="order_stat" id="testa" cssclass="jj" style="width2:100px;" code="order_status" order="order_status" key="status_name as value" table="dorderstatmsg" keyAlis="value"/>		
			</div>
			
			<div style="line-height:30px;">
			<label>开始日期：</label><input type="text" id="datepicker1"  name="pro_name" style="width:100px;"  value=""/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<label>结束日期：</label><input type="text" id="datepicker2" name="pro_no" style="width:100px;"  value=""/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<label>运抵国家：</label>
			<OptionList:OptionList name="ship_country" id="testa" cssclass="jj1" style="width2:100px;" code="short_name" key="cn_name as value" table="dcountrymsg" keyAlis="value"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查询&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"/>
			</div>
		</div>
		</div>
	</form>
</div>




<div id="list_cell"> 
  <%
 	
 	List<Map<String,Object>> list = (List)request.getAttribute("list");
    List<Map<String,Object>> prolist = (List)request.getAttribute("proList");
   
 	Map<String,Object> map = null;
 	OrderSrvImpl orderSrv = new OrderSrvImpl();
 	List<Map<String,Object>> proList = null;
 	Map<String,Object> proMap = null;
 	String tmpOrder1 = "";
 	String tmpOrder2 = "";
 	String orderStatus = "";
 	int proSize = 0;
 	if(null!=list){
		for(int i = 0 ;i<list.size();i++){
			map = list.get(i);
			tmpOrder1 = (String)map.get("order_id");
			orderStatus = (String)map.get("status_code");
			out.println("<div class=\"orderfrm\">");
			
				//订单信息
				out.println("<div class=\"item1\"> <div class='order_info'>订单号: "+tmpOrder1);
				out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				out.println("订单日期: "+(String)map.get("order_time"));
				
				out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				
				out.println("<span style=\"\">");//color:#53BA54;
				out.println("订单优惠:$"+map.get("fav_price")+"元");
				//out.println("<input type='button' value='调整'>");
				out.println("</span>");
				
				
				out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				out.println("订单状态: "+(String)map.get("order_status"));
				out.println("</div>");
				
				out.println("<div class='order_op'>");
				
				if(orderStatus.equals("1")){
					out.println("<a href='/seller/orderfav.do?order_id="+tmpOrder1+"'>调整优惠</a>");
					out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='/seller/todelorder.do?order_id="+tmpOrder1+"'>删除</a>");
				}
				
				out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='/seller/os_updateShipMsg.do?order_id="+tmpOrder1+"'>物流</a>");

				out.println("</div>");
				
				out.println("<div style='clear:both;'></div>");

				out.println("</div>");
				
				out.println("<div class=\"item2\">");
				out.println("<table width=100"+"%"+" class=\"c_table\">");
				
				if(null!=prolist){
					proSize = prolist.size();
					for(int j = 0 ;j<proSize; j++){
							proMap = prolist.get(j);

							tmpOrder2 = (String)proMap.get("order_id");
							if(tmpOrder1.equals(tmpOrder2)){
								out.println("<tr>");							
								out.println("<td width=\"40"+"%"+"\" >");
								 out.println(proMap.get("product_name"));
								 //proMap.get("product_id");
								out.println("</td>");
								
								
								out.println("<td>");
								 out.println("<span style=\"text-decoration:line-through;\">");
								 out.println("$"+proMap.get("fixed_price")+"元");
								 out.println("</span>");
								 out.println("<br>");
								 out.println("<span style=\"font-size:13px;\">");//color:#FF4400;
								 out.println("$"+proMap.get("trans_price")+"元");
								 out.println("</span>");
								out.println("</td>");
								
								out.println("<td>");
								 out.println((String)proMap.get("product_count")+(String)proMap.get("value"));
								out.println("</td>");
								
								
								out.println("<td>");
								 out.println("<span style=\"font-size:13px;\">");
								 out.println("$"+proMap.get("sum_price")+"元");
								 out.println("</span>");
								out.println("</td>");
								out.println("</tr>");
								
							}else{
								//break;
							}
							//prolist.remove(j);
							//proSize = prolist.size();
							//j = 0;
						}
				}
				
				out.println("</table>");
				out.println("</div>");
				//邮寄信息
				out.println("<div class=\"item3\">收件人:&nbsp;&nbsp;"+map.get("user_name"));
				out.println("&nbsp;&nbsp;&nbsp;&nbsp;");
				out.println(map.get("addr"));
				out.println("&nbsp;&nbsp;&nbsp;&nbsp;");
				out.println(map.get("mobile_phone"));
				out.println("</div>");
				//价格信息
				out.println("<div class=\"item4\">");
				
				out.println("定价:");
				out.println("<span style=\"text-decoration:line-through;\">");
				out.println("$"+map.get("fixed_price")+"元");
				out.println("</span>");
			
				out.println("&nbsp;&nbsp;");
				
				out.println("<span style=\"\">");
				out.println("$"+map.get("finnal_fee")+"元");
				out.println("</span>");
				
				out.println("<span style=\"color:#FF4400;\">");
				out.println("+");
				out.println("</span>");

				out.println("<span style=\"\">");
				out.println("邮费:");
				out.println("$"+map.get("postage")+"元");
				out.println("</span>");
				
				out.println("<span style=\"color:#FF4400;\">");
				out.println("=");
				out.println("</span>");
				//#e4393c;
				out.println("<span style=\"color:#FF4400;font-weight:bold;font-size:14px;\">");
				out.println("$"+map.get("trans_price")+"元");
				out.println("</span>");
				
				out.println("</div>");
				
			out.println("</div>");
 	}
 }
 
 %>
 
 </div>
 
	<div>
		<jsp:include page="/common/page_nav.jsp"/>
	</div>
 
	
</div>


<script language="javascript">
<!--
$( "#datepicker1" ).datepicker();
$( "#datepicker1" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
$( "#datepicker2" ).datepicker();
$( "#datepicker2" ).datepicker( "option", "dateFormat", "yy-mm-dd" );

//-->
</script>
