<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${resourcepath}public/css/default.css" type="text/css" />
<script type="text/javascript" src="${resourcepath}public/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${resourcepath}public/js/item.js"></script>

<title>${title}</title>
 
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
					<form id="head_search_form" action="/productsearch.do" >
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
</body>
</html>