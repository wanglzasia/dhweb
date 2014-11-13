<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="common.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
body{background-color:#f7fbfc;}
</style>
</head>
<body>
<h3 class="f14">订单管理</h3>
<ul style="display: block;" class="admin_menu">
	<li ><a href="#"  style="outline:none;" target="main-frame">订单管理</a></li>
</ul>
<h3 class="f14">产品管理</h3>
<ul style="display: block;" class="admin_menu">
	<li ><a href="/admin/init_product_list.do"  style="outline:none;" target="main-frame">产品审核</a></li>
	<li ><a href="/admin/productkind.do"  style="outline:none;" target="main-frame">产品目录</a></li>
	<li ><a href="/admin/productattr.do"  style="outline:none;" target="main-frame">产品属性</a></li>
</ul>

<h3 class="f14">首页管理</h3>
<ul style="display: block;" class="admin_menu">
	<li ><a href="/admin/toadvlist.do"      style="outline:none;" target="main-frame">轮播广告管理</a></li>
	<li ><a href="/admin/ftl_atrlist.do"    style="outline:none;" target="main-frame">模板管理</a></li>
	<li ><a href="/admin/default_page.do"   style="outline:none;" target="blank">首页预览</a></li>
	<li ><a href="/admin/topage.do"         style="outline:none;" target="main-frame">生成首页</a></li>
	<li ><a href="/admin/sm_sb_menu.do"     style="outline:none;" target="main-frame">二级菜单</a></li>
	<li ><a href="/admin/model_kindlist.do" style="outline:none;" target="main-frame">产品目录模板</a></li>
	<!-- 
	<li ><a href="/admin/search_spage.do"   style="outline:none;" target="main-frame">搜索界面</a></li>
	 -->
</ul>
<h3 class="f14">基本管理</h3>
<ul style="display: block;" class="admin_menu">
	<li ><a href="/admin/shopcfg.do"  style="outline:none;" target="main-frame">基本信息</a></li>
	<li ><a href="/admin/meta_list.do"  style="outline:none;" target="main-frame">搜索优化</a></li>
	<li ><a href="/admin/user_list.do"  style="outline:none;" target="main-frame">工号管理</a></li>
	<li ><a href="/admin/mailcfg.do"  style="outline:none;" target="main-frame">邮件管理</a></li>
	<li ><a href="/admin/protoco_protolist.do"  style="outline:none;" target="main-frame">协议管理</a></li>
	<li ><a href="/admin/courier_list.do"  style="outline:none;" target="main-frame">运费管理</a></li>
</ul>

 

</body>
</html>