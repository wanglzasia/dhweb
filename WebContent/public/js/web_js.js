var VERSION = "webx js version 1.0.0";
//var g_price_id=1;
function version(){
	alert(VERSION);
}
function add_pricedetail()
{
	var g_price_id = $("#probprice_id").val();
	var html_cell="<div id=price_cell_add"+g_price_id+" class=\"multi_opr_area\" style=\"padding-bottom:5px;\">";
	html_cell = html_cell +"购买数量:<input type=\"text\"  style=\"width:100px;\" value=\"\" name=\"pro_buy_count"+g_price_id+"\"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	html_cell = html_cell +"购买价格:<input type=\"text\"  style=\"width:80px;\"  value=\"\" name=\"pro_buy_price"+g_price_id+"\"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $(美元)";
	html_cell = html_cell +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	html_cell = html_cell +"<a href=\"javascript:removeSegment("+g_price_id+")\">删除区间</a></div>";
	$("#price_cell_box").append(html_cell);
	g_price_id = parseInt(g_price_id) + 1;
	$("#probprice_id").val(g_price_id);
}
function removeSegment(g_price_id)
{
	var objId = "#"+"price_cell_add"+g_price_id;
	$(objId).remove();
}
function upload(picid,width,height)
{ 
	var pic_box_obj = "single_pic"+picid;
	var pic_url = "single_pic_url_"+picid;
	var innerHtml = $("#"+pic_box_obj).html();
	var params="height="+height+", width="+width+", top=200, left=200,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no";
	if(innerHtml=="双击,上传图片"){
		window.open("/load_page.do?picid="+picid,'上传图片',params);	
	}else
	{
		$("#"+pic_box_obj).html("双击,上传图片");
		$("#"+pic_url).val("");
	}
}
function load_pic(picid,url){
	alert(picid);
	alert(url);
}
function load_ablum(picid){
	var height = "400";
	var width = "630";
	var params="height="+height+", width="+width+", top=200, left=200,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no";
	window.open("/seller/album.do?picid="+picid,'上传图片',params);	
}

function selkind(obj,index){
	
	var kindcode = obj.value;
	var kindtree="";
	var divlist = $('.frame_kind_box');
	var i = 0;
	var curKindSelId="";
	var nextIndex = 0;
	nextIndex = index + 1;
	var tmpid="";
	for(i=0;i<divlist.length;i++){
		
		if(i>index){
			//divlist[i].remove();
			tmpid = '#kind_sel_'+i;
			$(tmpid).remove();
		}else{
			curKindSelId="#kind_sel_"+i;
			if(i==0){
				kindtree=$(curKindSelId).find("option:selected").text();
			}else{
				kindtree=kindtree+"->"+$(curKindSelId).find("option:selected").text();
			}
		}
	}
	$("#kind_name_ipt").val(kindtree);
	$("#kind_code").val(kindcode);
	if(kindcode!=""){
		$("#submitbtn").removeAttr("disabled");
	}else{
		$("#submitbtn").attr("disabled",true);
	}
	var kindurl = "/seller/kind_list_json.do";
	var selectHTML="";
	var oplistcount = 0;
	$.ajax({
		url: kindurl,
		type: 'POST',
		data:{
			kind_code: kindcode
		},
		dataType: 'json',
		timeout: 10000,
		error: function(){alert('Error loading document'); return ;},
		success: function(result){ 	
			
			selectHTML="<div class=\"frame_kind_box\"><select size=10 onclick=\"selkind(this,"+nextIndex+")\" id=\"kind_sel_"+nextIndex+"\">";
	   		$.each(result, function(index,values){
	   			$.each(values,function(index2,value){
	   				selectHTML = selectHTML +"<option value=\""+value.kind_code+"\">"+value.kind_name+"</option>";
	   				oplistcount ++;
	   			});
	   		});
	   		selectHTML = selectHTML +"</select></div>";
	   		if(oplistcount>0){
	   			$("#frame_box").append(selectHTML);
	   		}
	   		oplistcount =0;
		}
	});
}
/**
 * 
 */
function chgfav(orderid){
	var url = "/seller/getfavalue.do";
	var favvalue = $("#fav_price_id").val();

	$.ajax({
		url: url,
		type: 'POST',
		data:{
			order_id: orderid,
			fav_value: favvalue
		},
		dataType: 'json',
		timeout: 10000,
		error: function(){alert('Error loading document'); return ;},
		success: function(result){ 	
			$("#trans_price_1").html(result);
		}
	});
	
}
