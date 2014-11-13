 var zone_country_list={};
 var curSelzonelit ;
 var sel_country_list_a= new Array();
 var sel_country_list_b= new Array();
 var sel_country_list_c= new Array();
 var sel_country_list_d= new Array();
 
 function loadCourierZone(zonelist,courier_id)
 {
	 var shipzone="";
     var titlecountry="";
     var sc=0;//已经选择的国家数量
     var countrylist = "";
     var allsel="";
     var partname=$("#des_part").val();
	 $.each(zonelist, function(i, item) {
		//获取sc
		sc = getSelCtryCount(item.zone_id,courier_id);

		shipzone+="<div class=\"zone_box\" id=\""+item.zone_id+"_zone_box\">";
		if(sc==item.countryCount){
			allsel="checked";
		}else{
			allsel="";
		}
		shipzone+="<span><input type=\"checkbox\" "+allsel+" id=\"chk_zone"+item.zone_id+"\" onclick=\"toggleZone('1',this.checked);\"></span>";
     	titlecountry="";
     	$.each(item.countries,function(j,cinfo){
     		if(j<3){
     			titlecountry+=cinfo.cn_name+",";
     		}
     	});
     	

     	shipzone+="<span>"+item.zone_name+"(<b id=\"sel_count_"+item.zone_id+"\">"+sc+"</b>/"+item.countryCount+")</span>";
     	shipzone+="<span class=\"pos_rel\"><a href=\"javascript:showCountries('"+item.zone_id+"','"+courier_id+"');\" id=\"1_a\">";
     	shipzone+=titlecountry;
     	shipzone+="</a></span>";
     	
     	/*加载国家列表*/
     	
     	countrylist = appendCountry(item.zone_id,courier_id);
		shipzone+="<div id=\"selectBoxarea"+item.zone_id+"\" style=\"display:none\">"+countrylist+"</div>";	
     	shipzone+="</div>";
     	
     });

	//<input type="hidden" name="self_country_basicweight" id="ctry_<s:property value="#map.courier_id"/>_C_basicweight"/>						
	//<input type="hidden" name="self_country_basicfee" id="ctry_<s:property value="#map.courier_id"/>_C_basicfee"/>
	//<input type="hidden" name="self_country_stepweight" id="ctry_<s:property value="#map.courier_id"/>_C_stepweight"/>
	//<input type="hidden" name="self_country_stepfee" id="ctry_<s:property value="#map.courier_id"/>_C_stepfee"/>
	 
	if(partname=="C"){
		var tmpid="#ctry_"+courier_id+"_C_basicweight";
		shipzone+="<div class=\"sel_input_box\">";
		shipzone+="起重:<input type=\"text\" size=5 id=\"start_piece\" value=\""+$(tmpid).val()+"\" /> ";
		tmpid="#ctry_"+courier_id+"_C_basicfee";
		shipzone+="起重邮费:<input type=\"text\" size=5 id=\"start_fee\" value=\""+$(tmpid).val()+"\" />";
		tmpid="#ctry_"+courier_id+"_C_stepweight";
		shipzone+="续重:<input type=\"text\" size=5 id=\"step_piece\"  value=\""+$(tmpid).val()+"\"/>  ";
		tmpid="#ctry_"+courier_id+"_C_stepfee";
		shipzone+="续重邮费:<input type=\"text\" size=5 id=\"step_fee\"  value=\""+$(tmpid).val()+"\" />";
		shipzone+="</div>";
	}
	
    $("#info").html(shipzone);
    
    
    
	var tmpId="";
	for(var i=0;i<sel_country_list_a.length;i++){
		tmpId = "#id_"+sel_country_list_a[i];
		$(tmpId).prop("checked","true");
		if(partname!="A"){
			$(tmpId).prop("disabled","true");
		}
	}
	for(var i=0;i<sel_country_list_b.length;i++){
		tmpId = "#id_"+sel_country_list_b[i];
		$(tmpId).prop("checked","true");
		if(partname!="B"){
			$(tmpId).prop("disabled","true");
		}
	}
	for(var i=0;i<sel_country_list_c.length;i++){
		tmpId = "#id_"+sel_country_list_c[i];
		$(tmpId).prop("checked","true");
		if(partname!="C"){
			$(tmpId).prop("disabled","true");
		}
	}
	for(var i=0;i<sel_country_list_d.length;i++){
		tmpId = "#id_"+sel_country_list_d[i];
		$(tmpId).prop("checked","true");
		if(partname!="D"){
			$(tmpId).prop("disabled","true");
		}
	}
 }

 
function showCountries(zoneid,cor_id)
{
	var boxid = "#selectBoxarea"+zoneid;
	$(boxid).css("display","block");
}
  
function appendCountry(zoneid,cor_id)
{
	//var partname=$("#des_part").val();
	var selectBox = "<div id=\"zone_sel_box"+zoneid+"\" style=\"position: absolute;background:#ededed;width:210px;padding-left:10px; padding-bottom:10px;border:1px solid #d6d5d5; z-index:10000;\">";
	//title
	selectBox +="<div>";
	selectBox +="<span style='border-bottom:1px solid #ddd;position: relative;'><input type=\"checkbox\"/>全选</span>";
	selectBox +="<span style='float:right;color:#999;padding:5px;text-decoration: none;outline:none;cursor:pointer' onclick=\"closebox('"+zoneid+"')\">X&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>";
	selectBox +="</div>";
	//content
	selectBox +="<div style=\"width:200px;height:150px;overflow-x: hidden;overflow-y: scroll;margin: 5px 0;background:#FFF;\">";
	//var data = ;
	//var isCheck="";
    $.each(zone_country_list[cor_id], function(i, item) {
    	if(item.zone_id==zoneid){
    		$.each(item.countries,function(j,cinfo){
    			selectBox +="<div style=\"padding-top:10px;padding-left:5px;clear:both;\">";
    			selectBox +="<div style=\"width:20px;float:left;\">";
    			selectBox +="<input name=\"sel_country\" type=\"checkbox\" ";
    			selectBox +=" courier_id=\""+cor_id+"\" short_name=\""+cinfo.short_name+"\" id=\"id_"+cinfo.short_name+"\" ";
    			selectBox +=" onclick=\"selcountry('"+zoneid+"',this)\">";
    			selectBox +="</div>";
    			selectBox +="<div style=\"float:left;padding-top:3px;\"><span class=\"flag "+cinfo.short_name+"\"></span></div>";
    			selectBox +="<div style=\"width:100px;float:left;padding-top:3px;\"  id=\"full_name_"+cinfo.short_name+"\">"+cinfo.cn_name+"</div>";
    			selectBox +="</div>";
    		});
    	}
    });
    selectBox+="</div>";
    selectBox +="<div style='text-align:right;'><button onclick=\"closebox('"+zoneid+"')\">关闭</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>";
	selectBox+="</div>";
	return selectBox;
}


function getSelCtryCount(zoneid,cor_id)
{
	var i_count = 0;
    $.each(zone_country_list[cor_id], function(i, item) {
    	if(item.zone_id==zoneid){
    		$.each(item.countries,function(j,cinfo){
    			if(isSelCountry(cinfo.short_name)){
    				i_count = parseInt(i_count) + 1;
    			}
    		});
    	}
    });
    return i_count;
}

function isSelCountry(shortname)
{
	var is_sel = false;
	var tmp_array={};

	tmp_array=sel_country_list_a;
	for(var i=0;i<tmp_array.length ;i++){
		if(tmp_array[i]==shortname){
			is_sel =  true;
			break;
		}
	}
	 
	tmp_array=sel_country_list_b;
	for(var i=0;i<tmp_array.length ;i++){
		if(tmp_array[i]==shortname){
			is_sel =  true;
			break;
		}
	}
	
	tmp_array=sel_country_list_c;
	for(var i=0;i<tmp_array.length ;i++){
		if(tmp_array[i]==shortname){
			is_sel =  true;
			break;
		}
	}
	
	tmp_array=sel_country_list_d;
	for(var i=0;i<tmp_array.length ;i++){
		if(tmp_array[i]==shortname){
			is_sel =  true;
			break;
		}
	}
	return is_sel;
}
function selectCount(shortname)
{
	var is_sel = 0;
	var tmp_array={};

	tmp_array=sel_country_list_a;
	for(var i=0;i<tmp_array.length ;i++){
		if(tmp_array[i]==shortname){
			is_sel =  parseInt(is_sel)+1;
		}
	}
	 
	tmp_array=sel_country_list_b;
	for(var i=0;i<tmp_array.length ;i++){
		if(tmp_array[i]==shortname){
			is_sel =  parseInt(is_sel)+1;
		}
	}
	
	tmp_array=sel_country_list_c;
	for(var i=0;i<tmp_array.length ;i++){
		if(tmp_array[i]==shortname){
			is_sel =  parseInt(is_sel)+1;
		}
	}
	
	tmp_array=sel_country_list_d;
	for(var i=0;i<tmp_array.length ;i++){
		if(tmp_array[i]==shortname){
			is_sel =  parseInt(is_sel)+1;
		}
	}
	
	return is_sel;
}

function selcountry(zoneid,checkbox){
	var selccid = "#sel_count_"+zoneid;
	var selcount = $(selccid).html();
	
	if(checkbox.checked){
		selcount = parseInt(selcount)+1;
	}else{
		selcount = parseInt(selcount)-1;
	}
	$(selccid).html(selcount);
}

function closebox(zoneid)
{
	var boxid = "#selectBoxarea"+zoneid;
	$(boxid).css("display","none");
}


function confirm_ok()
{
	var shortname="";
	var fullname="";
	var fullnameid="";
	var partname=$("#des_part").val();

	if(partname=="A"){
		emptyArray(sel_country_list_a);
	}
	if(partname=="B"){
		emptyArray(sel_country_list_b);
	}
	if(partname=="C"){
		emptyArray(sel_country_list_c);
	}
	if(partname=="D"){
		emptyArray(sel_country_list_d);
	}
	var is_disable=false;
	var sel_count = 0;
	$('input[type="checkbox"][name="sel_country"]:checked').each(
			function() {
				is_disable = $(this).prop("disabled");
				if(!is_disable){
					addArray(partname,$(this).attr("short_name"));
					shortname+=$(this).attr("short_name")+",";
					fullnameid="#full_name_"+$(this).attr("short_name");
					fullname+=$(fullnameid).html()+",";
					sel_count = parseInt(sel_count)+sel_count;
				}
			}
	);	

	shortname = shortname.substring(0, shortname.length-1);
	fullname = fullname.substring(0, fullname.length-1);
	
	var ctry_X = "#ctry_"+partname;
	var countylist_X  ="#countylist_"+partname;
	$(ctry_X).val(shortname);
	
	if(shortname!=""){
		ctry_X="#title_"+partname;
		$(ctry_X).css("display","block");
	}
	
	$(countylist_X).html(fullname);
	ctry_X="#ctry_name_"+partname;
	$(ctry_X).val(fullname);

	var c_id = $("#c_id").val();
	var tmpid="";
	if(partname=="C"){
		if(sel_country_list_c.length>0){
			tmpid="#ctry_"+c_id+"_C_basicweight";
			$(tmpid).val($("#start_piece").val());
			
			tmpid="#ctry_"+c_id+"_C_basicfee";
			$(tmpid).val($("#start_fee").val());
			
			tmpid="#ctry_"+c_id+"_C_stepweight"; 
			$(tmpid).val($("#step_piece").val());
			
			tmpid="#ctry_"+c_id+"_C_stepfee";
			$(tmpid).val($("#step_fee").val());
		}
	}
	closeWindow();
}
function emptyArray(in_array)
{
	in_array.splice(0,in_array.length);
}

function addArray(partname,var_value)
{
	if(partname=="A"){
		sel_country_list_a.push(var_value);
	}
	if(partname=="B"){
		sel_country_list_b.push(var_value);
	}
	if(partname=="C"){
		sel_country_list_c.push(var_value);
	}
	if(partname=="D"){
		sel_country_list_d.push(var_value);
	}
}

function initDetail()
{

	$("input[name='set_ship_detail']").prop("checked",false);
	var c_id = $("#c_id").val();
	var tmpId="";
	tmpId = "#ctry_"+c_id+"_A";
	$("#ctry_A").val($(tmpId).val());
	tmpId="#ctry_name_"+c_id+"_A";
	$("#ctry_name_A").val($(tmpId).val());
	if($("#ctry_A").val()!=""){ $("#set_ship_detail_A").prop("checked",true); $("#countylist_A").html($("#ctry_name_A").val()); $("#title_A").css("display","block");} else{ $("#countylist_A").html(""); $("#title_A").css("display","none");}

	
	tmpId = "#ctry_"+c_id+"_B";
	$("#ctry_B").val($(tmpId).val());
	tmpId="#ctry_name_"+c_id+"_B";
	$("#ctry_name_B").val($(tmpId).val());
	if( $("#ctry_B").val()!="" ){ $("#set_ship_detail_B").prop("checked",true); $("#countylist_B").html($("#ctry_name_B").val()); $("#title_B").css("display","block");} else{ $("#countylist_B").html(""); $("#title_B").css("display","none");} 

	
	tmpId = "#ctry_"+c_id+"_C";
	$("#ctry_C").val($(tmpId).val());
	tmpId="#ctry_name_"+c_id+"_C";
	$("#ctry_name_C").val($(tmpId).val());
	if($("#ctry_C").val()!=""){$("#set_ship_detail_C").prop("checked",true); $("#countylist_C").html($("#ctry_name_C").val()); $("#title_C").css("display","block");} else{ $("#countylist_C").html(""); $("#title_C").css("display","none");}

	
	tmpId = "#ctry_"+c_id+"_D";
	$("#ctry_D").val($(tmpId).val());
	tmpId="#ctry_name_"+c_id+"_D";
	$("#ctry_name_D").val($(tmpId).val());
	if($("#ctry_D").val()!=""){$("#set_ship_detail_D").prop("checked",true); $("#countylist_D").html($("#ctry_name_D").val()); $("#title_D").css("display","block");} else{ $("#countylist_D").html(""); $("#title_D").css("display","none");}
	
	
	emptyArray(sel_country_list_a);
	emptyArray(sel_country_list_b);
	emptyArray(sel_country_list_c);
	emptyArray(sel_country_list_d);
	
	
	var shortnamestr="";
	var shortnamelist={};
	
	shortnamestr = $("#ctry_A").val();
	if(shortnamestr!=""){
		shortnamelist = shortnamestr.split(",");
		for(var i =0 ;i<shortnamelist.length ;i++){
			sel_country_list_a.push(shortnamelist[i]);
		}
	}
	
	//emptyArray(shortnamelist);
	shortnamestr = $("#ctry_B").val();
	if(shortnamestr!=""){
		shortnamelist = shortnamestr.split(",");
		for(var i =0 ;i<shortnamelist.length ;i++){
			sel_country_list_b.push(shortnamelist[i]);
		}
	}
	
	//emptyArray(shortnamelist);
	shortnamestr = $("#ctry_C").val();
	if(shortnamestr!=""){
		shortnamelist = shortnamestr.split(",");
		for(var i =0 ;i<shortnamelist.length ;i++){
			sel_country_list_c.push(shortnamelist[i]);
		}
	}
	
	//emptyArray(shortnamelist);
	shortnamestr = $("#ctry_D").val();
	if(shortnamestr!=""){
		shortnamelist = shortnamestr.split(",");
		for(var i =0 ;i<shortnamelist.length ;i++){
			sel_country_list_d.push(shortnamelist[i]);
		}
	}
	
}


function setDetail()
{
	//A-D进行数据初始化//
	var c_id = $("#c_id").val();

	var tmpId = "#ctry_"+c_id+"_A";
	$(tmpId).val($("#ctry_A").val());
	tmpId="#ctry_name_"+c_id+"_A";
	$(tmpId).val($("#ctry_name_A").val());
	
	tmpId = "#ctry_"+c_id+"_B";
	$(tmpId).val($("#ctry_B").val());
	tmpId="#ctry_name_"+c_id+"_B";
	$(tmpId).val($("#ctry_name_B").val());
	
	tmpId = "#ctry_"+c_id+"_C";
	$(tmpId).val($("#ctry_C").val());
	tmpId="#ctry_name_"+c_id+"_C";
	$(tmpId).val($("#ctry_name_C").val());
	
	tmpId = "#ctry_"+c_id+"_D";
	$(tmpId).val($("#ctry_D").val());
	tmpId="#ctry_name_"+c_id+"_D";
	$(tmpId).val($("#ctry_name_D").val());
	
	if($("#ctry_A").val()!=""){$("#title_A").css("display","block");  $("#countylist_A").html($("#ctry_name_A").val());}
	if($("#ctry_B").val()!=""){$("#title_B").css("display","block");  $("#countylist_B").html($("#ctry_name_B").val());}
	if($("#ctry_C").val()!=""){$("#title_C").css("display","block");  $("#countylist_C").html($("#ctry_name_C").val());}
	if($("#ctry_D").val()!=""){$("#title_D").css("display","block");  $("#countylist_D").html($("#ctry_name_D").val());}
	
	var tipMsg="";
	//alert(sel_country_list_a.length);
	if(sel_country_list_a.length>0){
		tipMsg+="免运费 "+sel_country_list_a.length+"个国家"+"<br>";
	}
	if(sel_country_list_b.length>0){
		tipMsg+="标准运费 "+sel_country_list_b.length+"个国家"+"<br>";
	}
	if(sel_country_list_c.length>0){
		tipMsg+="自定义运费 "+sel_country_list_c.length+"个国家"+"<br>";
	}
	if(sel_country_list_d.length>0){
		tipMsg+="不发货 "+sel_country_list_d.length+"个国家"+"<br>";
	}
	if(tipMsg!=""){
		tmpId="#note_"+c_id;
		$(tmpId).html(tipMsg);
	}
	$("#step_two").css("display","none");
	$("#step_one").css("display","block");
	
}

function shipdetail(courierid,cname)
{
	$("#c_id").val(courierid);
	$("#c_name").val(cname);
	initDetail();
	$("#step_one").css("display","none");
	$("#step_two").css("display","block");

}



function clearSelect(selpart)
{
	if(selpart=="A"){
		emptyArray(sel_country_list_a);
	}
	if(selpart=="B"){
		emptyArray(sel_country_list_b);
	}
	if(selpart=="C"){
		emptyArray(sel_country_list_c);
	}
	if(selpart=="D"){
		emptyArray(sel_country_list_d);
	}
}
function isSel(shortname)
{
	/*
	var tmpshortname="";
	var matchcount = 0;
	for(var i=0;i<sel_country_list.length;i++){
		tmpshortname = sel_country_list[i];
		if(tmpshortname==shortname){
			matchcount=1;
			break;
		}
	}
	if(matchcount==0){
		return false;
	}else{
		return true;
	}*/
}


function removeSel(shortname)
{
	var tmpshortname="";
	for(var i=0;i<sel_country_list.length;i++){
		tmpshortname = sel_country_list[i];
		if(tmpshortname==shortname){
			//sel_country_list[i].
			//break;
		}
	}
}

function addSel(shortname)
{
	sel_country_list.push(shortname);
}



