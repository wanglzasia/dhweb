<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.dh.ora.s001.bean.User"  %>

<div style="border:1px solid #D0DFEB;width:780px;background-color:#EFF8FF;padding:10px 10px 10px 10px;">
<%
	User user =(User)session.getAttribute("usr");
%>

	<div class="tip_index">您好，<%=user.getLoginNo()%> !  您有(43)封未读站内信 (0)封未读客服留言</div>
	<div class="tip_index">商户评级：标准商户 </div>
	<div class="tip_index">纠纷率:0.00% 商户责任纠纷率:0.00% 退款率:0.00% 商户原因退款率:0.00%</div>
	<div class="tip_index">查看纠纷率/退款率规则</div>
	<div class="tip_index">[密码安全] 账号等级：低 修改密码 上次登录：2014-07-20 11:26 安全提示 </div>

</div>

<div style="border:1px solid #E6E6E6;width:800px;margin-top:20px;">
	<div style="height:20px;background-color:#F3F3F3;padding-top:10px;padding-left:10px;font-size:12px;border-bottom:1px solid #E6E6E6;">我的关注</div>
	<div style="padding:0px 10px 10px 10px;">
		        
	                        <ul class="my-product">          
	                            <li  id="">3天内将过期(0)</li>
								<li  id="">30天内已过期(0)</li>
	                            <li  id="">30天内有问题被下架(0)</li>
	                            <li  id="">被品牌商投诉(0)</li>
	                            <li  id="">重复产品管理(0)</li>
								<li  id="">需补充备货(0)</li>
	                        </ul>
 
          <div style="clear:both;"></div>
	</div>
</div>

 <div style="border:1px solid #E6E6E6;width:800px;margin-top:20px;">
	<div style="height:20px;background-color:#F3F3F3;padding-top:10px;padding-left:10px;font-size:12px;border-bottom:1px solid #E6E6E6;">营销资源</div>
	<div style="padding:0px 10px 10px 10px;">
	                        <ul class="my-product">          
	                            <li  id="">3天内将过期(0)</li>
								<li  id="">30天内已过期(0)</li>
	                            <li  id="">30天内有问题被下架(0)</li>
	                            <li  id="">被品牌商投诉(0)</li>
	                            <li  id="">重复产品管理(0)</li>
								<li  id="">需补充备货(0)</li>
	                        </ul>
          <div style="clear:both;"></div>
	</div>
</div>

 <div style="border:1px solid #E6E6E6;width:800px;margin-top:20px;">
	<div style="height:20px;background-color:#F3F3F3;padding-top:10px;padding-left:10px;font-size:12px;border-bottom:1px solid #E6E6E6;">供求信息</div>
	<div style="padding:0px 10px 10px 10px;">
	                        <ul class="my-product">          
	                            <li  id="">3天内将过期(0)</li>
								<li  id="">30天内已过期(0)</li>
	                            <li  id="">30天内有问题被下架(0)</li>
	                            <li  id="">被品牌商投诉(0)</li>
	                            <li  id="">重复产品管理(0)</li>
								<li  id="">需补充备货(0)</li>
	                        </ul>
          <div style="clear:both;"></div>
	</div>
</div>


 