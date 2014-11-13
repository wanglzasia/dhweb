<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="/public/css/default_kind.css" type="text/css" />
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

<div id="body_div">
		
		<!---section 1-->
		<div id="top_nav">
			<div id="welcell">
				<span id="weltip"></span>
				<span id="logout_tip" stype="display:none;">&nbsp;&nbsp;&nbsp;&nbsp;<a href="/logout.do">Quit!</a></span>
			</div>
			<div id="regcell">
				<span id="log_span"><a href="/log.do">Sign in</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span id="seller_span"><a href="/seller/center.do">Seller</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span id="buyer_span"><a href="/member/center.do">Buyer</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span id="buycar_span"><a href="/member/queryShoppingCart.do">Shopping Cart</a></span>
			</div>
		</div>
		
		<!--section 2-->
		<div id="top_main">
			
			<div id="head_logo"></div>
			
			<div id="head_search">
				<div>
					<form id="head_search_form" action="/psearch.do" >
						<input type="text" name="keyword" id="head_search_input" value=""/>
						<input type="submit" value="Search" id="head_search_btn"/>
					</form>
				</div>
			</div>
			
			<!--section 2-1-->
			<div id="head_action">
				
			</div>
		
			<!--section 2-2-->
			<div id="buy_cart" class="head-cart-pro head-cart-prompt" style="display:none;z-index:10;">
		       <b class="cart-up"></b>
		       <div class="js-loading" style="display:none;">loading</div>
		       <div class="js-empty" stype="dispaly:none"><p></p><strong>Your Shopping Cart is empty.</strong><p>If you already have an account, <a href="/log.do">sign in</a>.</p></div>
		       <div class="js-list" style="display: none;"></div>
		    </div>
		    
		</div>
<div>

<!--菜单-->
<div id="menu_bar">
	<div id="menu_inner_bar">
		<span class="span_1">
			<!--种类菜单-->
			<span class="CATEGORIES">
				<div>ALL CATEGORIES</div>
			</span>
			<!--二级菜单-->
			<#if subMenuList?exists>
				<#assign sub_menu_list = subMenuList>
			<#else>
				<#assign sub_menu_list = Request["subMenuList"]>
			</#if>
					
			<#list sub_menu_list as sub_menu_item>
				<span><a href="${sub_menu_item.http_addr?if_exists}">${sub_menu_item.menu_name_en?if_exists}</a></span>
		    </#list>
		
		</span>
		
		
		<!--种类科目数-->
		<#if main_kind_list?exists>
			<#assign mainkindlist = main_kind_list>
		<#else>
			<#assign mainkindlist = Request["main_kind_list"]>
		</#if>
		
		<#if sub_kind_list?exists>
			<#assign subkindlist = sub_kind_list>
		<#else>
			<#assign subkindlist = Request["sub_kind_list"]>
		</#if>
		<div class="kind_tree" >
			<ul>
				
				<#list mainkindlist as mainkinditem>
				<li class="kind_tree_li" kind_code="${mainkinditem.kind_code?if_exists}">
					<a href="/${mainkinditem.kind_code?if_exists}.htm">${mainkinditem.kind_name_en?if_exists}</a>
					<div class="sub-category" id="sub_kind_${mainkinditem.kind_code?if_exists}">
						<ul class="nav-subcate-le" >
      							<li class="nav_subcate_le_title"><span>${mainkinditem.kind_name_en?if_exists}</span></li>
								<#assign subkindlist = sub_kind_list>
									<#list subkindlist as subkinditem>
										<#if mainkinditem.kind_code==subkinditem.parent_kind_code>
											<li><a href="/psearch.do?kc=${subkinditem.kind_code}">${subkinditem.kind_name_en?if_exists}</a></li>
										</#if>
									</#list>
						</ul>		          					
		          	</div>
		         </li>
		         </#list>
		         
			</ul>
		</div>
	</div>
</div>

<div class="show_center_body">
	<div class="show_box">
		<!--导航-->
		<div class="show_box_nav">
		
			<a href="/">Home</a> > ${kind_name_en}
		
		</div>
		<div id="show_box_body">
		
			<div id="left_box">
				<div class="title_head"><h3>Best Sellers</h3></div>
				<div class="body_head">
					<a href="http://www.dhgate.com/store/15173153#st-navigation-storehome#dcp-103_featuredseller-6|ff808081482fd018014858fa7c7e2652:00000000000000000000000000000000#dcp-103_featuredseller-6|ff808081489cc3ae0148bf93d22c2c76:00000000000000000000000000000000" class="simg">
					<img src="http://www.dhresource.com/upload/php/ad2/1411963408.jpg" width="188" height="108">
					</a>
				</div>
				
				<div class="body_head">
					<a href="http://www.dhgate.com/store/14775359#st-navigation-storehome#dcp-103_featuredseller-6|ff808081489cc4500148bf9838c32660:00000000000000000000000000000000" class="simg">
					<img src="http://www.dhresource.com/upload/php/ad2/1411963697.jpg" width="188" height="108">
					</a>
				</div>
				
				<div class="body_head"> 
					<a href="http://www.dhgate.com/store/doitbest#dcp-103_featuredseller-6|ff808081482fd018014835115956204f:00000000000000000000000000000000" class="simg">
					<img src="http://www.dhresource.com/upload/php/ad2/1409639602.jpg" width="188" height="108">
					</a>
				</div>
				
				<div class="body_head">
					<a href="http://www.dhgate.com/store/14775755#dcp-103_featuredseller-6|ff80808148306c6001485e261d3e529b:00000000000000000000000000000000#dcp-103_featuredseller-6|ff808081489cc4500148bf9b7302266a:00000000000000000000000000000000" class="simg">
					<img src="http://www.dhresource.com/upload/php/ad2/1411963908.jpg" width="188" height="108">
					</a>
				</div>
				
				<div class="body_head">
					<a href="http://www.dhgate.com/store/aloha224#dcp-103_featuredseller-6|ff808081482fd0180148350ea9c7202f:00000000000000000000000000000000" class="simg">
					<img src="http://www.dhresource.com/upload/php/ad2/1409639426.JPG" width="188" height="108">
					</a>
				</div>
			</div>
			
			<div id="center_box">
			
				<!--column1-->
				<div class="show_column t_center t_column_p_20">
				
					<div class="show_cell">
						<a href="http://www.dhgate.com/wholesale/electronic-cigarettes/c103030.html#dcp_--">
							<img src="http://www.dhresource.com/fc/s023/123/Electronics/DCP/20140922/21.jpg" width="80" height="80">
							<span class="catename" href="http://www.dhgate.com/wholesale/electronic-cigarettes/c103030.html#dcp_--">E-cigarettes</span>
						</a>			
					</div>
					
					<div class="show_cell">
						<a href="http://www.dhgate.com/wholesale+headphones.html#dcp_--">
							<img src="http://www.dhresource.com/fc/s023/123/Electronics/DCP/20140922/22.jpg" width="80" height="80">
							<span class="catename" href="http://www.dhgate.com/wholesale+headphones.html#dcp_--">Headphones</span>
						</a>
					</div>
					
					<div class="show_cell">
						<a href="http://www.dhgate.com/wholesale/MIni+Speaker/s103008005.html#dcp_--" rel="nofollow">
							<img src="http://www.dhresource.com/fc/s023/123/Electronics/DCP/20140922/23.jpg" width="80" height="80">
							<span class="catename" href="http://www.dhgate.com/wholesale/MIni+Speaker/s103008005.html#dcp_--">Mini Speakers</span>
						</a> 
					</div>
					
				
					<div class="show_cell">
						<a href="http://www.dhgate.com/wholesale/Android+TV+Box/s103012008.html#dcp_--" rel="nofollow">
							<img src="http://www.dhresource.com/fc/s023/123/Electronics/DCP/20140922/24.jpg" width="80" height="80">
							<span class="catename" href="http://www.dhgate.com/wholesale/Android+TV+Box/s103012008.html#dcp_--">Android TV boxes</span>
						</a>
					</div>
					
					<div style="clear:both;"></div>
				</div>
				
				
				<!--column2-->
				<div class="show_column t_center t_column_m_20">
				
					<div class="col_2_cell t_column_pt_16">
						<a class="pimg" href="http://www.dhgate.com/wholesale/Bluetooth+E+Cigarette/s103.html#dcp_--" rel="nofollow">
							<img src="http://www.dhresource.com/fc/s023/123/Electronics/DCP/20140922/31.jpg" width="280px" height="200px"> 
	        				<div class="pbg"></div> 
	        				<span class="pname" href="http://www.dhgate.com/wholesale/Bluetooth+E+Cigarette/s103.html#dcp_--">Bluetooth E Cigarettes</span>
        				</a>
					</div>
					
					<div class="col_2_cell t_column_pt_16">
				 		<a class="pimg" href="http://www.dhgate.com/wholesale/IPX4+speaker/s103008005.html#dcp_--" rel="nofollow">
					 		<img src="http://www.dhresource.com/fc/s023/123/Electronics/DCP/20140922/32.jpg"  width="280px" height="200px"> 
	        			 	<div class="pbg"></div> 
	        			 	<span class="pname" href="http://www.dhgate.com/wholesale/IPX4+speaker/s103008005.html#dcp_--">Waterproof Bluetooth Speakers</span>
        			 	</a>
					</div>
					
					<div class="col_2_cell t_column_pt_16">
						 <a class="pimg" href="http://www.dhgate.com/wholesale/mxiii/s103.html#dcp_--" rel="nofollow">
						 	<img src="http://www.dhresource.com/fc/s023/123/Electronics/DCP/20140922/33.jpg"  width="280px" height="200px"> 
	        			 	<div class="pbg"></div> 
	        			 	<span class="pname" href="http://www.dhgate.com/wholesale/mxiii/s103.html#dcp_--">MXIII Amlogic S802 Android TV Boxes</span>
	         			</a>
					</div>
					
					<div style="clear:both;"></div>
				</div>
				
				
			<!--title-->		
				<div class="title">Promotions</div>
				
			<!--column3-->
				<div class="t_center t_column_m_20">
				
					<div class="col_3_cell">
						<a href="http://www.dhgate.com/activities/promotion/0624factoryelectronics.html#dcp_--" class="vimg" rel="nofollow">
							<img src="http://www.dhresource.com/fc/s023/123/Electronics/DCP/20140928/81.jpg">
							<span class="vname" href="http://www.dhgate.com/activities/promotion/0624factoryelectronics.html#dcp_--"> Big Deals...UP to $100 off</span>
						</a>
					</div>
					
					<div class="col_3_cell t_column_ml_10">
						<a href="http://www.dhgate.com/activities/promotion/electronics.html#dcp_--" class="vimg" rel="nofollow">
							<img src="http://www.dhresource.com/fc/s023/123/Electronics/DCP/20140928/82.jpg">
							<span class="vname" href="http://www.dhgate.com/activities/promotion/electronics.html#dcp_--"> Featuring our newest lines of Electronics&amp;Accessories</span>
						</a>
					</div>
					
					<div class="col_3_cell t_column_ml_10">
						 <a href="http://www.dhgate.com/activity/promotion/DailyEcigaretteGiveaway20140617.html#dcp_--" class="vimg" rel="nofollow">
						 <img src="http://www.dhresource.com/fc/s023/123/Electronics/DCP/20140928/83.jpg">
						 <span class="vname" href="http://www.dhgate.com/activity/promotion/DailyEcigaretteGiveaway20140617.html#dcp_--"> Brand of E-cigarettes Zone</span>
						 </a>
					</div>
					<div style="clear:both;"></div>
				</div>
				
				
			<!--column4-->
				<div class="t_center show_column t_column_p_20 t_column_m_20">
				
					<div class="col_4_cell">
						 <a href="http://www.dhgate.com/product/newest-tesla-spider-battery-1300mah-3-3-4/198360933.html#dcp_008newdcp-superdealscate-1" rel="nofollow">
						 <img src="http://www.dhresource.com/fc/s023/123/Electronics/DCP/20140922/sp4.jpg">
						 	<span class="sname">Tesla Spider Battery</span>
						 </a>
						 <div class="price"><strong>US $13.75</strong> / Piece </div>
					</div>
					
					<div class="col_4_cell t_column_ml_55">
						<a href="http://www.dhgate.com/product/christmas-gift-portable-sport-wear-comfortable/155848769.html#dcp_008newdcp-superdealscate-2" rel="nofollow">
							<img src="http://www.dhresource.com/fc/s023/123/Electronics/DCP/20140922/sp2.jpg"> 
							<span class="sname">AX-610 Bluetooth Headset</span>
						</a>
						<div class="price"><strong>US $13.75</strong> / Piece </div>
					</div>
					
					<div class="col_4_cell t_column_ml_55">
		 				<a href="http://www.dhgate.com/product/2pcs-lot-children-walkie-talkie-with-light/202349499.html#dcp_008newdcp-superdealscate-3" rel="nofollow">
			 				<img src="http://www.dhresource.com/fc/s023/123/Electronics/DCP/20140922/sp3.jpg">
			 				<span class="sname">Children Walkie Talkie</span>
		 				</a>
		 				<div class="price"><strong>US $13.75</strong> / Piece </div>
					</div>
					
					<div class="col_4_cell t_column_ml_55">
		 				<a href="http://www.dhgate.com/product/qfx-bluetooth-speaker-mini-speaker-portable/200840242.html#dcp_008newdcp-superdealscate-4" rel="nofollow">
			 				<img src="http://www.dhresource.com/fc/s023/123/Electronics/DCP/20140922/102b.jpg"> 
			 				<span class="sname">Bluetooth Speakers</span>
		 				</a>
		 				<div class="price"><strong>US $13.75</strong> / Piece </div>
					</div>
					
					<div style="clear:both;"></div>
				</div>
				
			</div>
		
			<div style="clear:both;"></div>
		</div>
	</div>
</div>

<div id="footer">
	${footermsg}
</div>

</body>
</html>