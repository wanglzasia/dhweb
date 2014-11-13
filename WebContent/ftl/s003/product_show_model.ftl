<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"> 
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT">
<meta name="Keywords" content="${simple_desc}">
${meta_list}

<script type="text/javascript" src="/HTML/public/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/HTML/public/js/item.js"></script>
<link rel="stylesheet" href="/HTML/public/css/item.css" type="text/css" />
<title>${product_name}</title>
<style type="text/css">			
.windowdiv {
	position: absolute;
	background-color: gray;
	height: 400px;		
	border: 0;			
}

.windowtable {
	padding: 0;
	margin: 0;
	width: 100%;
	height: 100%;
}

.closetd {				
	background-color: purple;
	font-family: "黑体";
	cursor: pointer;
	width: 1%;
	height: 1%;
}

.windowframe {
	width: 100%;
	height: 100%;
	border: 0;
}

</style>
<script type="text/javascript" >
function chgImg(obj)
{
	obj = "#"+obj;
	pic = $(obj).attr("src");
	$("#bigImg").attr("src",pic);
}
</script>
</head>

<body>
<form method="post" name="form1" id="form1" action="/orderConfirmation/orderConfirmationSingle.do">
<div id="header">
	<div id="logo"></div>
	<div id="buy_info">
		<span id="reg_span"><a href="/reg.do">Regist</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span id="log_span"><a href="/log.do">Sign in</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span id="member_span"><a href="/member/center.do">Buyer</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span id="member_span"><a href="/member/queryShoppingCart.do">Shop Cart</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span id="tip_span"></span>
		
	</div>
</div>
<!--<div id="navigation">navigation</div>-->

<div>
	<div id="left_menu_box"></div>
	
	<div id="content">
		<!--<div id="nav">${product_name}</div>-->
		
		<div id="pro_detail_left">
		
			<div class="jqzoom" align="center">
				<img src="${pic_name_1}" class="big_pic" id="bigImg"/>
			</div>
			
			<div id="smallimg">
				<ul class="imgList">
					<li><img id="img2" src="${pic_name_2}" alt="" onclick="chgImg('img2')"/></li>
					<li><img id="img3" src="${pic_name_3}" alt="" onclick="chgImg('img3')"/></li>
					<li><img id="img4" src="${pic_name_4}" alt="" onclick="chgImg('img4')"/></li>
					<li><img id="img5" src="${pic_name_5}" alt="" onclick="chgImg('img5')"/></li>
					<li><img id="img6" src="${pic_name_6}" alt="" onclick="chgImg('img6')"/></li>
				</ul>
			</div>
	 
		</div>
		
		<div id="pro_detail_right">
			<div id="pro_no_name">${product_name}
				<input type="hidden" name="product_id" id="product_id" value="${product_id}"/>
				<input type="hidden" name="seller_id" id="seller_id" value="${user_id}"/>
			</div>
			<div id="pro_no_desc">${simple_desc}</div>
			<div id="pro_no_cell"><label>No:</label>&nbsp;&nbsp;${product_no}</div>
			<div id="pro_no_brand"><label>Brand:</label>&nbsp;&nbsp;${brand_code}</div>
			
			<div id="pro_no_price">
			${priceHtml}
			</div>
			
			<div id="pro_no_brand"><label>Quantity:</label>
				<button type="button" id="subcbtn">−</button>
					<input type="text" name="product_count" id="product_count" autocomplete="off"  style="ime-mode: disabled" value="1" maxlength="9" data-max="1000" onkeydown="return false" onkeyup="return false" oncontextmenu="return false"/>
				<button type="button" id="addcbtn">+</button>
			</div>
			<div id="pro_no_brand">
				<input id="buyProduct" name="buyProduct" type="submit" value="Buy Now" />
				<input id="addShoppingCart" name="addShoppingCart" type="button" value="Add To Cart" />
			</div>
		</div>
		
		<div class="clear"></div>
		<div id="nav1">
			<ul id="tabnav">
				<li class="selected">Product Detail</li>
				<li>Package Message</li>
				<li>Company profile</li>
			</ul>
		
		</div>
		
		 <p id="#pro_info_msg">
			<div id="pro_detail_des"  class="show_info">${attrHtmlValue} <br> ${detail_desc}</div>
			<div id="pro_package"     class="hidden_info">
				Length:${package_length} CM <br>
				Width:${package_width} CM <br>
				Height:${package_height} CM <br>
				Weight:${package_weight} Kg
			</div>
			<div id="pro_com_profile" class="hidden_info">${delivery_key}</div>		
		<p>
		
	</div>
</div>




<div id="footer">
	<div id="footer">
		${footermsg}
	<div>
</div>
</form>
</body>
</html>


