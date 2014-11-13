<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="/public/css/default_search.css" type="text/css" />
<script type="text/javascript" src="/public/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/public/js/item.js"></script>
<title>${title}</title>
<script type="text/javascript">
<!--
$(document).ready(function(){
	 $(".CATEGORIES").mouseover(function(){
	 	$(".kind_tree").css("display","block");
	 });
	 
	 $(".kind_tree").mouseleave(function(){
	 	$(this).css("display","none");
	 });
	 
	 
	var $div_li =$(".kind_tree_li");
    var subid="";
   	$div_li.hover(function(){
	    subid = "#sub_kind_"+$(this).attr("kind_code");
	    $(subid).css("display","block");
	},function(){
	    subid = "#sub_kind_"+$(this).attr("kind_code");
		$(subid).css("display","none");
	});
	 
});

-->
</script>
</head>
<body>
 
<jsp:include  page="/ftl/common/head.htm"/>

<div class="show_center_body">
	<div class="show_box">
		<!--导航-->
		<div class="show_box_nav">
			<a href="/">Home</a> > 123
		</div>
		
		
		<div id="show_box_body">
			<!--左侧界面-->
			<div id="left_box">
				<jsp:include  page="/ftl/common/left.htm"/>
			</div>
			
			<!--产品列表-->
			<div id="center_box">
				<div id="pro_list">
					<s:iterator var="map" value="#request.plist">  
						<div class="p_msg">
							<!--图片-->
							<div class="p-img">
								<a href="/HTML/<s:property value="#map.user_id"/>/<s:property value="#map.user_id"/>-<s:property value="#map.product_id"/>.htm">
								<img src="<s:property value="pic_name_1" />" alt="<s:property value="#map.simple_desc" />"> 
								</a>		
							</div>
							<!--产品名称-->
							<div class="p_title">
								<s:property value="#map.simple_desc" />
							</div>
							<!--价格-->
							<div class="p_price margin_t_10">
								<span>US $<s:property value="#map.min_price" /> - <s:property value="#map.max_price" /></span> /<s:property value="#map.value_en" />
							</div>
							<!--货运信息-->
							<!--
							<div>????</div>
							-->
							<!--最小起送数量-->
							<div class="margin_t_10">Min. Order: <s:property value="#map.min_count" /> <s:property value="#map.value_en" /></div>
							<!--卖家-->
							<div class="margin_t_10">
								<span class="seller">
							<span>Seller:</span>
							<a href="http://www.dhgate.com/store/shinystore88"><s:property value="#map.login_no" /></a>
							</span>
						</div>
						<!--操作区域-->
							<div class="p_op margin_t_10">
								<a href="/member/addCart.do?seller_id=<s:property value="#map.user_id"/>&product_id=<s:property value="#map.product_id"/>&product_count=1">
									Add to Cart
								</a>
							</div>
						</div>
					</s:iterator>
					<div style="clear:both;"></div>
					
					
									
					<!-- 导航 -->
					<div style="border:1px solid #e9e9e9;background-color:#fff;margin-right:20px;margin-bottom:20px;">
						<jsp:include page="/common/page_nav_en.jsp"/>
					</div>
					
				</div>

			</div>
		

				<div style="clear:both;"></div>
		</div>
	</div>
</div>


						
<div id="footer">
	<jsp:include  page="/ftl/common/footer_en.htm"/>
</div>
</body>
</html>