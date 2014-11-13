var VERSION = "shoppingCart js version 1.0.0";

//格式化金额  1234.1232 -> 1,234.12
function fmoney(s, n) {
	n = n > 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
	t = "";

	for (var i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	return t.split("").reverse().join("") + "." + r;
}

//格式化金额 1,234.12 -> 1234.12
function rmoney(s) {
	return parseFloat(s.replace(/[^\d\.-]/g, ""));
}

function change_product_count(arg) {

	var product_value = $("#product_value_" + arg).val();
	var product_count = $("#product_count_" + arg).val();

	var product_all_value_old = $("#product_all_value_" + arg).val();
	var accounts_money = $("#accounts_money").val();

	var product_all_value = product_value * product_count;

	$("#product_all_value_" + arg).val(product_all_value, 2);

	$("#product_all_value_div_" + arg).html(fmoney(product_all_value, 2));

	if ($("#product_selected_" + arg).is(':checked') == true) {

		var accounts_money_new = rmoney(accounts_money)
				- rmoney(product_all_value_old) + product_all_value;

		$("#accounts_money").val(fmoney(accounts_money_new, 2));

		$("#accounts_money_div").html(
				"合计(不含运费)：" + fmoney(accounts_money_new, 2));

	}

}

/*
function deleteProduct(arg) {

	$.post("/shoppingcart/deleteShoppingCart.do", {
		shoppingcart_id : arg

	},
			function(msg) {
				if (msg == "true") {

					//$("#product_selected_"+arg).attr("checked",false);

					var accounts_money = $("#accounts_money").val();
					//alert("accounts_money1:"+accounts_money);

					var product_all_value = $("#product_all_value_" + arg)
							.val();
					//alert("product_all_value:"+product_all_value);

					accounts_money = rmoney(accounts_money)
							- rmoney(product_all_value);
					//alert("accounts_money:"+accounts_money);

					$("#accounts_money").val(fmoney(accounts_money, 2));

					$("#accounts_money_div").html(
							"合计(不含运费)：" + fmoney(accounts_money, 2));

					$("#" + arg).remove();
				}

			});
}
*/
 
function changeAccountsMoney(arg) {

	var accounts_money = $("#accounts_money").val();

	var product_all_value = $("#product_all_value_" + arg).val();

	if ($("#product_selected_" + arg).is(':checked') == true) {

		var accounts_money = rmoney(accounts_money) + rmoney(product_all_value);

		$("#accounts_money").val(fmoney(accounts_money, 2));

		$("#accounts_money_div").html("合计(不含运费):" + fmoney(accounts_money, 2));

	} else {

		accounts_money = rmoney(accounts_money) - rmoney(product_all_value);

		$("#accounts_money").val(fmoney(accounts_money, 2));

		$("#accounts_money_div").html("合计(不含运费)：" + fmoney(accounts_money, 2));

		$("input[name='product_selected_all_head']").attr("checked", false);
		$("input[name='product_selected_all_end']").attr("checked", false);

	}

	//对全选框进行操作
	var isChecked = false;
	var chk_value = [];
	$('input[name="product_selected"]').each(function() {

		chk_value.push($(this).val());

	});

	for (var i = 0; i < chk_value.length; i++) {

		var arg = chk_value[i];

		if ($("#product_selected_" + arg).is(':checked') == true) {
			isChecked = true;
		} else {

			isChecked = false;
			break;

		}
	}

	if (isChecked) {

		$("input[name='product_selected_all_head']").attr("checked", true);
		$("input[name='product_selected_all_end']").attr("checked", true);
	} else {

		$("input[name='product_selected_all_head']").attr("checked", false);
		$("input[name='product_selected_all_end']").attr("checked", false);
	}

}

function allchecked(msg) {

	var tmp = "";

	if (msg == "head") {
		tmp = "end";
	} else {
		tmp = "head";
	}

	if ($("#product_selected_all_" + msg).is(':checked') == true) {

		$("input[name='product_selected_all_" + tmp + "']").attr("checked",true);

		//对计算进行操作
		var chk_value = [];
		$('input[name="product_selected"]').each(function() {
			chk_value.push($(this).val());
		});

		for (var i = 0; i < chk_value.length; i++) {
			var arg = chk_value[i];

			if ($("#product_selected_" + arg).is(':checked') == false) {
				$("#product_selected_" + arg).attr("checked", true);
				changeAccountsMoney(arg);
			}

		}

	} else {
		$("input[name='product_selected']").attr("checked", false);
		$("input[name='product_selected_all_" + tmp + "']").attr("checked",
				false);

		$("#accounts_money").val("0.00");
		$("#accounts_money_div").html("合计(不含运费)：0.00");
	}

}

function truncate() {
	//对计算进行操作
	var chk_value = [];
	$('input[name="product_selected"]:checked').each(function() {
		chk_value.push($(this).val());
	});

	for (var i = 0; i < chk_value.length; i++) {
		var arg = chk_value[i];
		deleteProduct(arg);
	}
}

function accounts() {
	//提交订单
	
}
function chkAll(checkbox){
	var valuelist = null;
	var value1 = 0.00;
	if(checkbox.checked){
		//全选
		$(".chart_css").prop("checked",true);
		valuelist = $(".proav");
		$.each(valuelist,function(i,item){
			value1 += parseFloat(item.value);
		});
	}else{
		//全不选
		$(".chart_css").prop("checked",false);
		value1=0.00;
	}
	settleAllfee();
}

function chkSingle(checkbox){
	settleAllfee();
}

function setfreight()
{
	var products="";
	var carts="";
	var prodctcounts="";
	var tmpid="";
	var tmpid1="";
	var shipcountry="";
	var feehtml="";
	var chklist = null;
	var value1 = 0.00;

	var shipfeelist=null;
	
	shipcountry = $("#countries").val();
	$(".chart_css:checked").each(function(i,item){
		 tmpid = "#product_id_" + $(item).attr("pro_id");
		 products+=$(tmpid).val()+",";
		 tmpid = "#product_count_" + $(item).attr("pro_id");
		 prodctcounts+=$(tmpid).val()+",";
	});	 
	
	var url="/seller/courier_shipFeeJson.do";
	$.ajax({
		url: url,
		type: 'POST',
		data:{
			product_id: products,
			buy_count:prodctcounts,
			ship_country:shipcountry
		},
		dataType: 'text',
		timeout: 1000,
		error: function(){alert('Error loading document');},
		success: function(result){
			result = eval(result);
			var tmpshipfee=0.00;
			$.each(result,function(i,item){
				$.each(item.shiplist,function(j,info){
					tmpid1="#"+info.product_id+"_ship";
					if(info.ship_type=="free_ship"){
						feehtml="<span style=\"color:green;\">Free Ship</span>";
						$(tmpid1).val("0.00");
					}
					if(info.ship_type=="no_ship"){
						feehtml="<span style=\"color:red;\">No Ship</span>";
						$(tmpid1).val("-");
					}
					if(info.ship_type=="std_ship"){
						feehtml="Shipping Cost:<strong>US $"+info.ship_fee+"</strong>";
						$(tmpid1).val(info.ship_fee);
					}
					if(info.ship_type=="ud_ship"){
						feehtml="Shipping Cost:<strong>"+info.ship_fee+"</strong>";
						$(tmpid1).val(info.ship_fee);
					}
					if(info.ship_type=="null"){
						feehtml="<span style=\"color:red;\">No Ship</span>";
						$(tmpid1).val("-");
					}
					tmpid="#ship_"+info.product_id;
					$(tmpid).html(feehtml);
				});
			});
			
			shipfeelist = $("input[name='ship_fee'");
			shipfee=0;
			$.each(shipfeelist,function(k,shipinfo){
				tmpshipfee = shipinfo.value;
				if(tmpshipfee=="-"){
					tmpshipfee="0.00";
				}
				shipfee+=parseFloat(tmpshipfee);
			});
			
			settleAllfee();
		}
	});
	
}


function settleAllfee()
{
	var prolist = null;
	var proprice=0.00;
	var shipfee=0.00;
	var allfee=0.00;
	var tmpvalue=0.00;
	prolist = $(".chart_css:checked");
	$.each(prolist,function(i,item){
		 tmpid = "#product_all_value_"+$(item).attr("pro_id");
		 proprice+=parseFloat($(tmpid).val());
		 tmpid = "#"+$(item).attr("pro_id")+"_ship";
		 if($(tmpid).val()=="-"){
			 shipfee+=parseFloat(0);
		 }else{
			 shipfee+=parseFloat($(tmpid).val());
		 }
	});
	tmpvalue = fmoney(proprice,2);
	//货物总价
	$("#accounts_money").val(proprice);
	tmpvalue = fmoney(proprice,2);
	$("#accounts_money_div").html("$"+tmpvalue);
	//运费总价
	tmpvalue = fmoney(shipfee,2);
	$("#ship_fee_div").html("$"+tmpvalue);
	//货物总价+运费总价
	allfee = proprice + shipfee;
	tmpvalue = fmoney(allfee,2);
	$("#accounts_money_all_div").html("$"+tmpvalue);
}

function chkAndSubmit(){
	var prolist = null;
	var tmpid="";
	var errcount=0;
	var products="";
	var buycounts="";
	var shipfees="";
	var profees="";
	var sellers="";
	var singleprices="";
	prolist = $(".chart_css:checked");
	$.each(prolist,function(i,item){
		 tmpid = "#"+$(item).attr("pro_id")+"_ship";
		 if($(tmpid).val()=="-"){
			 errcount = parseInt(errcount) +1;
		 }else{
			 products += $(item).attr("pro_id") +",";

			 tmpid = "#product_count_"+$(item).attr("pro_id");
			 buycounts += $(tmpid).val()+",";
			 
			 tmpid = "#"+$(item).attr("pro_id")+"_ship";
			 shipfees += $(tmpid).val()+",";
			 
			 tmpid = "#product_all_value_"+$(item).attr("pro_id");
			 profees +=$(tmpid).val()+",";
			 
			 tmpid = "#seller_id_"+$(item).attr("pro_id");
			 sellers +=$(tmpid).val()+",";
			 
			 tmpid = "#product_value_"+$(item).attr("pro_id");
			 singleprices +=$(tmpid).val()+",";
		 }
	});
 
	if(errcount==0){
		$("#products").val(products);
		$("#buycounts").val(buycounts);
		$("#shipfees").val(shipfees);
		$("#profees").val(profees);
		$("#sellers").val(sellers);
		$("#singlefees").val(singleprices);
		
		marklist = $("textarea");
		$.each(marklist,function(i,item){
			if(item.value=="Please add remark:(e.g. color, size...)"){
				item.value="";
			}
		});
		$("#cart_frm_settle").submit();
	}
}

function editMark(textarea){
	if(textarea.value=="Please add remark:(e.g. color, size...)"){
		textarea.value="";
	}
}
function chkValue(textarea){
	if(textarea.value==""){
		textarea.value="Please add remark:(e.g. color, size...)";
	}
}