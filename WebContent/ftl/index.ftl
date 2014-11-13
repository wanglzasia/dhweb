<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${resourcepath}public/css/default.css" type="text/css" />
<script type="text/javascript" src="${resourcepath}public/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${resourcepath}public/js/item.js"></script>

<title>${title}</title>
<script language="javascript">
var times = 5000;
var picList = null;
var autoStart;
<!--
$(document).ready(function(){
	picList = $(".show_pic");
	setAuto(); 
	$(".nav_box_inc").css("opacity",0.8);
	$(".nav_box_inc").click(function(){
		curId = parseInt($(this).html())-1;
		showCur(curId);
	});
});

function showCur(curId){
	nextPic = "#p"+curId;
	nextIndic = "#indicator_"+curId;
	curPic = $(".show_cur_pic");
	
	//当前图片隐藏
	curPic.removeClass("show_cur_pic");
	curPic.addClass("hidden_cur_pic");
	curPic.css("display","none");	
	//下张图片显示
	$(nextPic).fadeIn();
	$(nextPic).removeClass("hidden_cur_pic");
	$(nextPic).addClass("show_cur_pic");
	$(nextPic).css("display","block");	
	//指示器
	$(".show_indicator").removeClass("show_indicator");
	$(nextIndic).addClass("show_indicator");	
	
	clearInterval(autoStart);
	setAuto(); 
	
}
function playNext(){
	iCount = picList.length;
	var curPic = $(".show_cur_pic");
	var curId = curPic.attr("cls_id");
	var nextId= parseInt(curId)+1;
	if(nextId==iCount){
		nextId=0;
	}
	//当前图片隐藏
	curPic.removeClass("show_cur_pic");
	curPic.addClass("hidden_cur_pic");
	curPic.css("display","none");
	//curPic.fadeOut();
	//下一张图片显示
	var nextPic = "#p"+nextId;
	$(nextPic).fadeIn();
	$(nextPic).removeClass("hidden_cur_pic");
	$(nextPic).addClass("show_cur_pic");
	$(nextPic).css("display","block");
	
	//指示器
	$(".show_indicator").removeClass("show_indicator");
	var nextIndicor="#indicator_"+nextId;
	$(nextIndicor).addClass("show_indicator");		
}

function setAuto(){
	autoStart=setInterval("playNext()", times)
}
	
//-->
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
				<!--
				<span id="reg_span"><a href="/reg.do">注册</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span id="log_span"><a href="/log.do">登陆</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span id="member_span"><a href="/seller/center.do">卖家中心</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span id="member_span"><a href="/member/center.do">买家中心</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span><a href="/member/shopchart.do">退出</a></span>-->
				<!--<span id="reg_span"><a href="/reg.do"></a>&nbsp;&nbsp;&nbsp;&nbsp;</span>-->
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
		 
		<div id="head_action">
			
		</div>
		 
		 <div id="buy_cart" class="head-cart-pro head-cart-prompt" style="display:none;z-index:10;">
            <b class="cart-up"></b>
            <div class="js-loading" style="display:none;">loading</div>
            <div class="js-empty" stype="dispaly:none"><p></p><strong>Your Shopping Cart is empty.</strong><p>If you already have an account, <a href="/log.do">sign in</a>.</p></div>
            <div class="js-list" style="display: none;"></div>
        </div>
        
		</div>
		
		<!--section 3-->
		<div class="w">
			<div id="menu_section">
				<div id="CATEGORIES_SECTION">
					<div>ALL CATEGORIES</div>
				</div>
				
				<div class="mc">
						<div class="nav-cateList">
		          			<ul>
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
		 					
							<#list mainkindlist as mainkinditem>
								<#if mainkinditem_index?if_exists<14 >
			          				<li id="kind_${mainkinditem.kind_code?if_exists}">
			          					<a href="/${mainkinditem.kind_code?if_exists}.htm">${mainkinditem.kind_name_en?if_exists}</a>
				          				<div class="sub-categorys" id="sub_kind_${mainkinditem.kind_code}">
											<dl class="nav-subcate-le">
				          						<dt>${mainkinditem.kind_name_en}</dt>
											   		<#assign subkindlist = sub_kind_list>
													<#list subkindlist as subkinditem>
														<#if mainkinditem.kind_code==subkinditem.parent_kind_code>
															<dd><a href="/psearch.do?kc=${subkinditem.kind_code}">${subkinditem.kind_name_en?if_exists}</a></dd>
														</#if>
													</#list>
											</dl>		          					
				          				</div>
			          				</li>
		          				</#if>
							</#list>
							
								<li id="navCat14"><a href="">More Categories</a></li>
							</ul>
		         		</div>
				</div>
				

				<!--menu-->
				<ul id="navitems-2013">
				
					<#if subMenuList?exists>
						<#assign sub_menu_list = subMenuList>
					<#else>
						<#assign sub_menu_list = Request["subMenuList"]>
					</#if>
					
					<#list sub_menu_list as sub_menu_item>
						<li id="nav-home"><a href="${sub_menu_item.http_addr?if_exists}">${sub_menu_item.menu_name_en?if_exists}</a></li>
				    </#list>
				</ul>
				
				<!--section 4-->
				<div class="adv_box">
					
					<div>	
						<#if advlist?exists>
		          			<#assign p_advlist = advlist>
		 				<#else>
		 					<#assign p_advlist = Request["advlist"]>
		 				</#if>
		 				<#list p_advlist as advItem>
		 					<#if advItem_index?if_exists==0>
								<div id="p${advItem_index}" cls_id="${advItem_index}" class="show_pic show_cur_pic" >
								<a href="${advItem.pic_link?if_exists}" target="_blank">
								<img src="${advItem.pic_addr?if_exists}" /></a>
								</div>	
		 					<#else>
								<div id="p${advItem_index}" cls_id="${advItem_index}" class="show_pic hidden_cur_pic">
								<a  href="${advItem.pic_link?if_exists}" target="_blank" >
								<img src="${advItem.pic_addr?if_exists}"/>
								</a>
								</div>	
		 					</#if>
		 				</#list>
					</div>
	
					<div id="nav_box">
		 				<#list p_advlist as advItem>
		 					<#if advItem_index?if_exists==p_advlist?size-1>
								<div id="indicator_${p_advlist?size-advItem_index-1}" class="nav_box_inc show_indicator">${p_advlist?size-advItem_index}</div>	
		 					<#else>
								<div id="indicator_${p_advlist?size-advItem_index-1}" class="nav_box_inc">${p_advlist?size-advItem_index}</div>
		 					</#if>
		 				</#list>
					</div>
					
				</div>
				
				<!--section 5-->
				<div class="right_box">
					<div>
						<#if left_top_pic1?exists>
							<#assign left_top_pic1 = left_top_pic1>
						<#else>
							<#assign left_top_pic1 = Request['left_top_pic1']>
						</#if>
						
						<a href="${left_top_pic1}">
							<img height="100" src="${left_top_pic1}" width="200">
						</a>
					</div>
					<div>
						<#if left_top_pic2?exists>
							<#assign left_top_pic2 = left_top_pic2>
						<#else>
							<#assign left_top_pic2 = Request['left_top_pic2']>
						</#if>
						<a href="${left_top_pic2}">
							<img height="100" src="${left_top_pic2}" width="200">
						</a>
					</div>
					<div>
						<#if left_top_pic3?exists>
							<#assign left_top_pic3 = left_top_pic3>
						<#else>
							<#assign left_top_pic3 = Request['left_top_pic3']>
						</#if>
						<a href="${left_top_pic3}">
							<img height="100" src="${left_top_pic3}" width="200">
						</a>
					</div>
				</div>
			</div>
		</div>
		
		
		<!--section 6 begin-->
		<#if row_title_1?exists>
			<#assign row_title_1 = row_title_1>
		<#else>
			<#assign row_title_1 = Request['row_title_1']>
		</#if>
		<div id="content_body">
			<div class="show_box">
				<div class="show_title">${row_title_1}</div>
				
				<div class='fb'>
					<#if productherf_1_1?exists>
						<#assign productherf_1_1 = productherf_1_1>
					<#else>
						<#assign productherf_1_1 = Request['productherf_1_1']>
					</#if>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
					<#if productname_1_1?exists>
						<#assign productname_1_1 = productname_1_1>
					<#else>
						<#assign productname_1_1 = Request['productname_1_1']>
					</#if>
				  	<p class="bs-pro-name">${productname_1_1}</p> 
					<#if productprice_1_1?exists>
						<#assign productprice_1_1 = productprice_1_1>
					<#else>
						<#assign productprice_1_1 = Request['productprice_1_1']>
					</#if>
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
					<#if productbuy_1_1?exists>
						<#assign productbuy_1_1 = productbuy_1_1>
					<#else>
						<#assign productbuy_1_1 = Request['productbuy_1_1']>
					</#if>
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
							<#if productpic_1_2?exists>
								<#assign productpic_1_2 = productpic_1_2>
							<#else>
								<#assign productpic_1_2 = Request['productpic_1_2']>
							</#if>
	           				<img height="160" src="${productpic_1_2}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				
				<div class="clear"></div>
			</div>
			
			<!--part2-->
			<div class="show_box">
				<div class="show_title">${row_title_1}</div>
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				<div class="clear"></div>
			</div>
			
			<!--part3-->
			<div class="show_box">
			
				<div class="show_title">${row_title_1}</div>
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				
				<div class='fb'>
					<a rel="nofollow" href="${productherf_1_1}">
	          			<div class="bs-pro-img"> 
	           				<img height="160" src="${productpic_1_1}" width="160"> 
					  	</div> 
				  	<p class="bs-pro-name">${productname_1_1}</p> 
				  	<p class="bs-pro-piece"> From<strong>US $${productprice_1_1}</strong> / Piece</p> 
				  	<p class="bs-pro-shopnow"> <img height="21" src="${productbuy_1_1}" width="86"> </p> 
					</a>
				</div>
				<div class="clear"></div>
			</div>
		<!--section 6 end-->
		<#if footermsg?exists>
			<#assign footermsg = footermsg>
		<#else>
			<#assign footermsg = Request['footermsg']>
		</#if>
		 <div id="footer">${footermsg}</div>

</div>
</div>
</body>
</html>