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
</div>

<!--菜单-->
<div id="menu_bar">
	<div id="menu_inner_bar">
		<span class="span_1">
			<!--种类菜单 -->
			<span class="CATEGORIES">
				<div>ALL CATEGORIES</div>
			</span>
			<!--二级菜单  -->
			<#if subMenuList?exists>
				<#assign sub_menu_list = subMenuList>
			<#else>
				<#assign sub_menu_list = Request["subMenuList"]>
			</#if>
					
			<#list sub_menu_list as sub_menu_item>
				<span><a href="${sub_menu_item.http_addr?if_exists}">${sub_menu_item.menu_name_en?if_exists}</a></span>
		    </#list>
		
		</span>
		
		
		<!--种类科目数 -->
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