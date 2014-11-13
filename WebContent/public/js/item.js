$(document).ready(function(){
    var $div_li =$(".nav-cateList ul li");
    var subid="";
   	$div_li.hover(function(){
	    subid = "#sub_"+this.id;
	    $(subid).css("display","block");
	},function(){
		subid = "#sub_"+this.id;
		$(subid).css("display","none");
	});
   	
   	var name="loginno";
	var loginName="";
	var userid="";
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg)){
    	loginName = unescape(arr[2]);
    }
    name = "userid";
    arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg)){
    	userid = unescape(arr[2]);
    }
    
    
    
	//alert(loginName);
	if(loginName!=""){
		$("#weltip").html("Welcome "+loginName);
		$("#reg_span").html("");
		//$("#log_span").html("");
		$("#logout_tip").attr("display:block");
		
	}else{
		$("#welcell").html("");
		$("#logout_tip").attr("display:none");
	}
	
   	
	$("#head_action").mouseover(function(){
		if(loginName!=""){
			$.ajax({
				url: '/goodlist.do',
				type: 'POST',
				data:{
						userid:userid
					},
				dataType: 'json',
				timeout: 1000,
				error: function(){alert('Error loading document');},
				success: function(result){
					var jsonstatus = result.status[0].status;
					var htmltable="<table class=\"cart_style\">";
					if(jsonstatus==0){
						//没有登陆
						$("#buy_cart").css("display","block");
						$(".js-loading").css("display","none");
						$(".js-list").css("display","none");
						$(".js-empty").css("display","block");
					}else if(jsonstatus==1){
						//登陆了 有列表
						$(".js-list").html("");
						//alert(result.prodcuts.length);
						$("#buy_cart").css("display","block");
						$(".js-loading").css("display","block");
						$(".js-empty").css("display","none");
						$.each(result.prodcuts,function(idx,item){
							   htmltable = htmltable + "<tr>";
							   htmltable = htmltable + "<td width=40px > <img src=\"" +item.pic+"\" width=40px height=40px>" +"</td>";
							   htmltable = htmltable + "<td align=left class=\"char_pname\" >"+item.pname+"</td>";
							   htmltable = htmltable + "<td width=40px >"+item.pct+"</td>";
							   htmltable = htmltable + "<td width=40px class=\"cart_price\" >$"+item.pvalue+"</td>";
							   htmltable = htmltable + "</tr>";
						});
						
						htmltable = htmltable +"<tr><td colspan=4 align=right class=\"btn_cart\"> <a href=\"/member/queryShoppingCart.do\">View Cart</a></td> </tr>";
						htmltable = htmltable +"</table>";
						if(result.prodcuts.length>0){
							$(".js-list").html(htmltable);
							$(".js-loading").css("display","none");
							$(".js-list").css("display","block");
							$(".js-empty").css("display","none");
						}else{
							$(".js-list").html("");
							$("#buy_cart").css("display","block");
							$(".js-loading").css("display","none");
							$(".js-list").css("display","block");
							$(".js-empty").css("display","none");
						}
						
					}else if(jsonstatus==2){
						//登陆 无列表
						$(".js-list").html("<p></p><p></p><p></p>Your Shopping Cart is empty.");
						$("#buy_cart").css("display","block");
						$(".js-loading").css("display","none");
						$(".js-list").css("display","block");
						$(".js-empty").css("display","none");						
					}
				}
			});
		}else{
			$("#buy_cart").css("display","block");
			$(".js-loading").css("display","none");
			$(".js-list").css("display","none");
			$(".js-empty").css("display","block");
		}
		});
	$("#buy_cart").mouseleave(function(){
		$("#buy_cart").css("display","none");
		$(".js-loading").css("display","none");
		$(".js-list").css("display","none");
		$(".js-empty").css("display","none");
	});
	
   	
  	

});