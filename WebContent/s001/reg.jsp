<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login</title>
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../public/js/reg_js.jsp"></script>
<style type="text/css">
body{margin:0 auto;display: block;font: 13px Helvetica, arial, freesans, clean, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol";line-height: 1.4;min-width: 1020px;color: #333333;background-color: #fff;}
#top{height:80px;border-bottom: 1px solid #e5e5e5;}
#footer{height:80px;}
#body_section{ }
#center{margin:0 auto;width:750px;position: relative;}
.section_column{width:500px;padding-bottom:5px;padding-top:20px;}
.section_column label{display:block;width:100px;float:left;padding-top:2px;text-align:right;padding-right:5px;}
.input{float:left;}
.hidden{display:none;}
.form{}
dt{display: block;margin:0; padding:0;}
dd{ margin:0; padding:0; }
label{font-weight: bold;}
.field-with-errors{display: inline;}
dl.form>dd input[type="text"], dl.form>dd input[type="password"], dl.form>dd input[type="email"] {width: 100%;max-width:430px;margin-right: 5px;background-color: #fafafa;padding-left:10px;padding-right:10px;padding-top:10px;padding-bottom:10px;color: #333;vertical-align: middle;background-color: #fff;background-position: right center;border: 1px solid #ccc;border-radius: 3px;outline: none;box-shadow: inset 0 1px 2px rgba(0,0,0,0.075);}
.note{display:none;}
.errmsg{color:red;}
.errbox{border:1px solid red;}
.form-actions{padding-top: 0;padding-bottom: 0;text-align: left;display: block;}
.button{position: relative;display: inline-block;padding: 7px 12px;font-size: 13px;font-weight: bold;white-space: nowrap;vertical-align: middle;cursor: pointer;border: 1px solid #d5d5d5;border-radius: 3px;}
.button.primary, .minibutton.primary {color: #fff;text-shadow: 0 -1px 0 rgba(0,0,0,0.25);background-color: #60b044;background-image: linear-gradient(#8add6d, #60b044);background-repeat: repeat-x;border-color: #5ca941;}
.steps {display: table;margin: 30px auto 0;padding: 0;overflow: hidden;list-style: none;border: 1px solid #ddd;border-radius: 3px;box-shadow: 0 1px 3px rgba(0,0,0,0.05);width: 750px;}
.steps li:first-child {border-left: 0;}
.steps li {display: table-cell;width: 33.3%;padding: 10px 15px;cursor: default;border-left: 1px solid #ddd;background-color: #fff;color: #333;}
.steps li .step {display: block;}
ul ol{padding:0px;}
</style>

<script language="javascript">
$(document).ready(function(){
	$("#signup_button").click(function(){
		validForm();
	});
	$("#login_no").blur(function(){
		chkExist(1);
	}); 
	$("#e_mail").blur(function(){
		chkExist(2);
	});
	$("#confirm_password").blur(function(){
		chkpassword();
	});
});
</script>
</head>
<body>

	<div id="top">
		<div style="width:750px;margin:0 auto;">
			
			<div style="width:200px;display:block;float:left;"><img src="/public/img/logo.png"/></div>
			
			
			
			<div style="float:right;padding-top:40px;"><a href="#">Sign in</a></div>
			
			<div style="clear:both"></div>
		</div>
		
		
	</div>
			<div id="body_section">
					<div id="center">
					<form id="regfrm" action="/saveusr.do" method="post">
							<div>
								<h1><s:property value="%{getText('REG_PAGE_TITLE')}" /></h1>
		      					<p class="lead"><s:property value="%{getText('REG_PAGE_TITLE2')}" /></p>
		      					
		      					<ol class="steps">
						          <li>
						            <span class="mega-octicon octicon-person"></span>
						            <strong class="step"><s:property value="%{getText('REG_PAGE_SETP_1')}" /></strong>
						            <s:property value="%{getText('REG_PAGE_SETP_1_DESC')}" />
						          </li>
						          <li>
						            <span class="mega-octicon octicon-versions"></span>
						            <strong class="step"><s:property value="%{getText('REG_PAGE_SETP_2')}" /></strong>
						            <s:property value="%{getText('REG_PAGE_SETP_2_DESC')}" />
						          </li>
						          <li>
						            <span class="mega-octicon octicon-dashboard"></span>
						            <strong class="step"><s:property value="%{getText('REG_PAGE_SETP_3')}" /></strong>
						            <s:property value="%{getText('REG_PAGE_SETP_3_DESC')}" />
						          </li>
						        </ol>
							</div>
							
							<!-- username -->
							<dl class='form'>
								<dt>
									<div>
										<label><s:property value="%{getText('REG_PAGE_USERNAME')}" /></label>
									</div>
								</dt>
								<dd>
									<div>
										<input type="text" name="login_no" id="login_no" autocomplete="off" />
									</div>
									<p class="note" id="for_login_no"></p>
								</dd>
							</dl>
		
							<!-- Email Address -->
							<dl class='form'>
								<dt>
									<div>
										<label><s:property value="%{getText('REG_PAGE_EMAIL')}" /></label>
									</div>
								</dt>
								<dd>
									<div>
										<input type="text" name="e_mail" id="e_mail" autocomplete="off" />
									</div>
									<p class="note" id="for_e_mail"></p>
								</dd>
							</dl>					
							
							
							<!-- Password -->
							<dl class='form'>
								<dt>
									<div>
										<label>
											<s:property value="%{getText('REG_PAGE_PASSWORD')}" />										
										</label>
									</div>
								</dt>
								<dd>
									<div>
										<input type="password" name="pass_word" id="pass_word"/>
									</div>
									<p class="note"  id="for_pass_word"></p>
								</dd>
							</dl>					
										
							<!-- Confirm your password -->
							<dl class='form'>
								<dt>
									<div>
										<label><s:property value="%{getText('REG_PAGE_CONFIRMPWD')}" /></label>
									</div>
								</dt>
								<dd>
									<div>
										<input type="password" name="confirm_password" id="confirm_password"/>
									</div>
									<p class="note"  id="for_confirm_password"></p>
								</dd>
							</dl>						
							
							<!-- valid_code -->
							<dl class='form' style="display:none;">
								<dt>
									<div>
										<label>valid_code</label>
									</div>
								</dt>
								<dd>
									<div>
										<input type="text"/>
									</div>
									<p class="note"></p>
								</dd>
							</dl>			
						<div class="form-actions">
				    		<button type="button" class="button primary" id="signup_button" ><s:property value="%{getText('REG_PAGE_BTNVALUE')}" /></button>
				  		</div>
	  		</form>
		</div>
	</div>		
	 <div id="footer"></div>
 </body>
</html>