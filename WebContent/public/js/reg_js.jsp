<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
function validForm(){
 var loginno = $('#login_no').val();var email = $('#e_mail').val();var password = $('#pass_word').val();var password1=$('#confirm_password').val();
 if(loginno=="")  {$('#for_login_no').html('<s:property value="%{getText('REG_CHK_USER_NULL')}" />');$('#for_login_no').removeClass("note");$('#for_login_no').addClass("errmsg");}
 if(email=="")    {$('#for_e_mail').html('<s:property value="%{getText('REG_CHK_EMAIL_NULL')}" />');$('#for_e_mail').removeClass("note");$('#for_e_mail').addClass("errmsg");	}
 if(password=="") {$('#for_pass_word').html('<s:property value="%{getText('REG_CHK_PWD_NULL')}" />');$('#for_pass_word').removeClass("note");$('#for_pass_word').addClass("errmsg");		}
 if(password1==""){$('#for_confirm_password').html('<s:property value="%{getText('REG_CHK_CFRMPWD_NULL')}" />');$('#for_confirm_password').removeClass("note");$('#for_confirm_password').addClass("errmsg");} 
 if($('#for_login_no').attr('class')=="errmsg" ){return;}if($('#for_e_mail').attr('class')=="errmsg"){return;}if($('#for_pass_word').attr('class')=="errmsg"){return;}if($('#for_confirm_password').attr('class')=="errmsg"){return;}
 $("#regfrm").submit();
}
function resetstatus(obj){
	var objname="#"+obj;
	$(objname).html("");
	$(objname).removeClass("errmsg");
	$(objname).addClass("note");
}
function chkpassword(){
	var pwrd1=$("#pass_word").val();
	var pwrd2=$("#confirm_password").val();
	if(pwrd1!=pwrd2){
		$('#for_pass_word').html('<s:property value="%{getText('REG_CHK_PWD_COMPARE')}" />');
		$('#for_pass_word').removeClass("note");
		$('#for_pass_word').addClass("errmsg");
		$('#for_confirm_password').html('<s:property value="%{getText('REG_CHK_PWD_COMPARE')}" />');
		$('#for_confirm_password').removeClass("note");
		$('#for_confirm_password').addClass("errmsg");
	}else{
		$('#for_pass_word').html("");
		$('#for_pass_word').removeClass("errmsg");
		$('#for_pass_word').addClass("note");
		$('#for_confirm_password').html("");
		$('#for_confirm_password').removeClass("errmsg");
		$('#for_confirm_password').addClass("note");		
	}
	
}
function chkExist(type){
	var chkurl="/chkuser.do";
	var chkparam = "loginno";
	var objname="";
	var value = "";
	switch(type)
	{
		case 1:
			chkparam="loginno";
			objname ="#for_login_no";
			value = $('#login_no').val();
		break;
		case 2:
			chkparam="email";
			objname ="#for_e_mail";
			value = $('#e_mail').val();
		break;
		default:
			chkurl="loginno";
			objname ="#for_login_no";
			value = $('#e_mail').val();
		break;
	}
	if(value!="")
	{	
		$.ajax({
			url: chkurl,
			type: 'POST',
			data:{
				chkparam: chkparam,
				chkvalue: value
			},
			dataType: 'text',
			timeout: 1000,
			error: function(){alert('Error loading document');},
			success: function(result){ 
				if(result=="1"){
					$(objname).html(value+"<s:property value="%{getText('REG_CHK_EXIST')}" />");
					$(objname).removeClass("note");
					$(objname).addClass("errmsg");
				}else{
					$(objname).html("");
					$(objname).removeClass("errmsg");
					$(objname).addClass("note");
				}
			}
		});
	}
		
}