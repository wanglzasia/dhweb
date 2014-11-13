<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login</title>
<style type="text/css">
body{margin:0 auto;font-size:14px;}
#top{height:60px;margin:0 auto;width:1000px;}
/*url(http://mimg.127.net/index/163/themes/140624_1ydb_bg.jpg);*/
/*url(http://mimg.127.net/index/163/themes/140624_1ydb_cnt.jpg);*/
#center{
	height:600px;
	background-image: 
	background-color: rgb(105, 140, 194);
	background-position: 0% 0%;
	background-repeat: repeat-x;
}

#footer{height:80px;}
#footer_inner{margin:0 auto;width:1000px;}
a {
text-decoration: none;
color: #959595;
}
#marklayer{
	background-image: url(../public/img/login_bb0821.jpg);
	background-position: 15% 40%;
	background-repeat: no-repeat;height:600px;
}

#loginbox{width: 295px;height: 400px;padding: 13px 14px 15px;top: 62px;left: 50%;margin-left: 90px;text-align: left;position: relative;background: url(../public/img/login_v1.png) no-repeat;z-index: 2;}
.btn{background: url(../public/img/bg_v2.png);background-position:0 -208px;color: #fff;box-shadow: 0 2px 5px rgba(0,28,88,.3);width: 110px;height: 38px;text-align: center;	cursor: pointer;border: 0;padding: 0;font-weight: 700;font-size: 14px;display: inline-block;vertical-align: baseline;line-height: 38px;outline: 0;background-color: transparent;} 
.loginForm{width:240px;line-height: 42px;margin: 0 0 20px 25px;padding-right: 5px;clear: both;}
.btn-reg {background-position: -117px -208px;float: right;}
.btn-side {color: #6d798c;box-shadow: 0 2px 5px rgba(0,0,0,.1);}
.iptusr{padding-top:0px;}
.tst{background: url(../public/img/bg_v2.png);background-position:0 -352px;width:210px;height:42px;padding-left:28px;padding-right:7px;}
.userico{position: relative;left: 9px;background: url(../public/img/bg_v2.png);width: 14px;height: 16px;background-position: -154px -64px;z-index:2;float:left;top:12px;	}
.pwdico{position: relative;left: 9px;background: url(../public/img/bg_v2.png);width: 14px;height: 16px;background-position: -178px -64px;z-index:2;float:left;top:12px;}
.logfrmipt{width: 205px;padding: 9px 0 10px;ime-mode: disabled;	height: 21px;top: 1px;left: 28px;color: #333;font-size: 14px;font-weight: 700;border: none;font-family: verdana;line-height: 21px;background: transparent!important;hidefocus:hidefocus;outline:none;}
.btn_section{padding-top:20px;}
.hidden{display:none;}
.nav{color:#626262;font-size: 14px;}
.note{display:none;}
.errmsg{color:red;}
.top_pic{width:300px;float:left;height:41px;margin-top:10px;}
.top_right{width:100px;float:right;height:41px;margin-top:10px;padding-right:60px;}
.mid_pic{width:10px ;height:41px;margin-top:10px; float:left;}
</style>
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#login_no").click(function(){ $("#tipmsg").html("");});
	$("#pass_word").click(function(){$("#tipmsg").html("");});

	$("#sumitbtn").click(function(){
		if($("#login_no").val()=="") {$('#for_login_no').html('<s:property value="%{getText('LOGIN_PAGE_CHK_USER_NULL')}" />');$('#for_login_no').removeClass("note");$('#for_login_no').addClass("errmsg");}
		if($("#pass_word").val()==""){$('#for_pass_word').html('<s:property value="%{getText('LOGIN_PAGE_CHK_PWD_NULL')}" />');$('#for_pass_word').removeClass("note");$('#for_pass_word').addClass("errmsg");}
		if($("#login_no").val()!=""){$('#for_login_no').html('');$('#for_login_no').removeClass("errmsg");$('#for_login_no').addClass("note");};
		if($("#pass_word").val()!=""){$('#for_pass_word').html('');$('#for_pass_word').removeClass("errmsg");$('#for_pass_word').addClass("note");};
		if($('#for_login_no').attr('class')=="errmsg" ){return;}if($('#for_pass_word').attr('class')=="errmsg"){return;}
		 $("#logfrm").submit();
	});
});
</script>
</head>
<body>

	<div id="top">
		<center>
		<div class="top_pic"><img src="/public/img/logo.png"/></div>
		<div class="mid_pic"></div>
		<div class="top_right"><a href="/">Home</a></div>
		</center>
	</div>
		<div id="center">
				<div id="marklayer">
					<div id="loginbox">
						<div class="nav"></div>
							<div class="loginForm">
							
							<form action="/login.do" method="post" id="logfrm">
								
								<div class="nav"><s:property value="%{getText('LOGIN_PAGE_LABEL_LOGIN')}" /></div>
								<div style="padding-top:40px;">
										<div class="iptusr">
											<div class="userico"></div>
											<div class="tst">
												<input class="logfrmipt" type="text" name="login_no" id="login_no" title="请输入账号" autocomplete="off"  value="${username}lenovo"/>
											</div>
											<div style="height:40px;">
												<div class="note"  id="for_login_no"></div>
												<div style="color:red;font-size:12px;" id="tipmsg">${errmsg}</div>
											</div>
										</div>
										
										<div  class="iptusr">
											<div class="pwdico"></div>
											<div class="tst">
												<input class="logfrmipt" type="password" name="pass_word" id="pass_word" value="abc123"/>
											</div>
											<div style="height:40px;">
												<div class="note"  id="for_pass_word"></div>
											</div>
										</div>
										
										<div  class="hidden">
											<div class="tst">
												<input class="logfrmipt" type="text" name="mask_code" value=""/>
											</div>
										</div>		
										<div style="line-height:14px;color:#555;font-size: 12px; text-align:right;padding-top:10px;"><a href="#"><s:property value="%{getText('LOGIN_PAGE_FORGETPWD')}" /></a></div>
										<div  class="btn_section">
											<input id="sumitbtn" class="btn" type="button" value="<s:property value="%{getText('LOGIN_PAGE_BTN_LOGIN')}" />"/>
											<a class="btn btn-side btn-reg" href="/reg.do"><s:property value="%{getText('LOGIN_PAGE_BTN_REGIST')}" /></a>
										</div>
										<div style='clear:both;'></div>
								</div>
								</form>
							</div>
					 </div>
				 </div>
		</div>
 </body>
</html>