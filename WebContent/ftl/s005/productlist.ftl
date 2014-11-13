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
 
<#include "/ftl/common/head.htm">

<div class="show_center_body">
	<div class="show_box">
		<!--导航-->
		<div class="show_box_nav">
		
			<a href="/">Home</a> > 123
		
		</div>
		<div id="show_box_body">
			
			<!--左侧界面-->
			<div id="left_box">
				<#include "/ftl/common/left.htm">
			</div>
			
			<!--产品列表-->
			<div id="center_box">
				<div id="pro_list">
						<#assign list = Request["plist"]?exists>
						<#list plist as listitem>
							
							<div class="p_msg">
								<!--图片-->
								<div class="p-img">
									<a href="http://www.dhgate.com/product/s5-i9600-quad-core-mtk6582-sm-g900-android/187877243.html#s1-1-1c|1540289481" supplierid="ff80808127dc433f0127fab4a6bb422c" itemcode="187877243" class="pic">
									<img src="http://www.dhresource.com/albu_781135291_00-1.200x200/s5-i9600-quad-core-mtk6582-sm-g900-android.jpg" alt="Wholesale S5 i9600 Quad Core MTK6582 SM G900 Android Inch USB GHZ GB RAM GB ROM Gesture G GPS Android Cell Phone mail MP G900F"> 
									</a>		
								</div>
								<!--产品名称-->
								<div class="p_title">
									S5 i9600 Quad Core MTK6582 SM-G900 Android 4.4 5.1 Inch USB 3.0 1.3GHZ 2GB RAM 32GB ROM Gesture 3G GPS Android Cell Phone mail-400MP G900F
								</div>
								<!--价格-->
								<div class="p_price margin_t_10">
									<span>US $96.25 - 103.0</span> /Piece
								</div>
								<!--货运信息-->
								<!--
								<div>????</div>
								-->
								<!--最小起送数量-->
								<div class="margin_t_10">Min. Order: 1 Piece</div>
								<!--卖家-->
								<div class="margin_t_10">
									<span class="seller">
										<span>Seller:</span>
										<a href="http://www.dhgate.com/store/shinystore88">shinystore88</a>
									</span>
								</div>
								<!--操作区域-->
								<div class="p_op margin_t_10">Add to Cart</div>
							</div>
							
						</#list>
					<div style="clear:both;"></div>
				</div>
			</div>
			<div style="clear:both;"></div>
		</div>
	</div>
</div>

<div id="footer">
	<#include "/ftl/common/footer_en.htm">
</div>

</body>
</html>