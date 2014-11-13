$(document).ready($(function(){
	var g_buycount = 1;
    var $div_li =$("#tabnav li");
    var objArray =["#pro_detail_des","#pro_package","#pro_com_profile"];
    
    
   	var name="loginno";
	var loginName="";
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg)){
    	loginName = unescape(arr[2]);
    }
	if(loginName!=""){
		$("#reg_span").html("");
		$("#log_span").html("");
		$("#tip_span").html("Welcome "+loginName);
	}
      
    $("#addShoppingCart").click(function(){
    	
    	$("#form1").attr("action","/member/addCart.do"); 	
    	$("#form1").submit();
    	/*
    $.post("/member/addShoppingCart.do",
    	{
    		product_id:$("#product_id").val(),
    		product_img:$("#product_img").val(),
    		product_name:$("#product_name").val(),
    		product_note:$("#product_note").val(),
    		product_count:$("#product_count").val(),
    		product_value:$("#product_value").val(),
    		seller_id:$("#seller_id").val()
    		
    	},
    	function(msg){
    		alert(msg+"");
    	});
    	*/
    });
  
  	$("#subcbtn").click(function(){
  		g_buycount--;
  		if(g_buycount<=0){
  			g_buycount = 1;
  		}
  		$("#product_count").val(g_buycount);
  	});
   	$("#addcbtn").click(function(){
  		g_buycount++;

  		$("#product_count").val(g_buycount);
  	}); 
  
    $div_li.click(function()
    {
		$(this).addClass("selected").siblings().removeClass("selected");  
         var index =  $div_li.index(this);
         
         for(i = 0 ;i<objArray.length;i++){
         	if( i != index ){
         	    obj = objArray[i];
         		$(obj).attr("class", "hidden_info");
         	}
         }
         obj  = objArray[index];
         $(obj).attr("class", "show_info");         
          
	}).hover(function(){
		$(this).addClass("hover");
	},function(){
		$(this).removeClass("hover");
	})
})
);