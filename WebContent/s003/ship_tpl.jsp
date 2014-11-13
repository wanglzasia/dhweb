<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
    
 
    

    
    
<script language="javascript">

$(document).ready(function(){	
	$('#frm_title_btn .close').click(function(){
		$('#console_form').hide();
		$('#theme-popover-mask').hide();
		
	});
});

function closeWindow()
{
	$('#console_form').hide();
	$('#theme-popover-mask').hide();
	$('#console_form').html("");
}

function openWindow(selTyle,checkbox)
{	
	var url = "/s003/shipcountry.jsp?courier_id="+$("#c_id").val()+"&selTyle="+selTyle;
	$("#des_part").val(selTyle);
	var tmpid="";

	if(checkbox.checked){
		$("#console_form").load(url, function(){		
			$('#theme-popover-mask').show();
			$('#console_form').show();
		 });		
	}else{
		
		tmpid="#title_"+selTyle;
		$(tmpid).css("display","none");
		tmpid="#ctry_"+selTyle;
		$(tmpid).val("");
		tmpid="#countylist_"+selTyle;
		$(tmpid).html("");
		clearSelect(selTyle);
	}
}

function modifyWindow(selType)
{
	var url = "/s003/shipcountry.jsp?courier_id="+$("#c_id").val();
	$("#des_part").val(selType);
	$("#console_form").load(url, function(){		
		$('#console_form').show();
		$('#theme-popover-mask').show();
	 });
}


function loadcountry()
{
	
	$.ajax({
		url: '/seller/courier_zoneJson.do',
		type: 'POST',
		data:{
			courier_id:$("#c_id").val()
			},
		dataType: 'text',
		timeout: 1000,
		error: function(){alert('Error loading document');},
		success: function(result){
			alert(result);
		}
	});
}

</script>


<div id="step_one">
 <form method="post" action="/seller/courier_saveshipmodel.do">
	<div>添加运费模板</div>
	<div><span>运费模板名称</span><input type="text" name="tpl_name" /></div>
	<div></div>
	<div>
		<table border="1px" width=100%>
			<tr>
				<td>物流方式</td>
				<td>物流价格(RMB)<br>0.5kg到美国为例(含燃油)</td>
				<td>运输时效</td>
				<td>收费方式</td>
				<td>操作</td>
			</tr>
		<s:iterator var="map" value="#request.courierlist">  
			<tr>
				<td>
					<s:property value="#map.courier_en_name"/><br>
					<s:property value="#map.courier_cn_name"/>
					<input type="hidden" name="courier_id" value="<s:property value="#map.courier_id"/>" />
				</td>
				<td><s:property value="#map.freight"/></td>
				<td><s:property value="#map.deliery_day"/>
						<input type="hidden" name="free_country_<s:property value="#map.courier_id"/>" id="ctry_<s:property value="#map.courier_id"/>_A"           value=""/>
						<input type="hidden" name="free_country_name_<s:property value="#map.courier_id"/>" id="ctry_name_<s:property value="#map.courier_id"/>_A" value=""/>
						<input type="hidden" name="stanard_country_<s:property value="#map.courier_id"/>" id="ctry_<s:property value="#map.courier_id"/>_B"         value="" />
						<input type="hidden" name="stanard_country_name_<s:property value="#map.courier_id"/>" id="ctry_name_<s:property value="#map.courier_id"/>_B" value=""/>
						<input type="hidden" name="self_country_<s:property value="#map.courier_id"/>" id="ctry_<s:property value="#map.courier_id"/>_C" value=""/>
						<input type="hidden" name="self_country_name_<s:property value="#map.courier_id"/>" id="ctry_name_<s:property value="#map.courier_id"/>_C" value=""/>
						<input type="hidden" name="self_country_basicweight_<s:property value="#map.courier_id"/>" id="ctry_<s:property value="#map.courier_id"/>_C_basicweight" value=""/>						
						<input type="hidden" name="self_country_basicfee_<s:property value="#map.courier_id"/>" id="ctry_<s:property value="#map.courier_id"/>_C_basicfee" value=""/>
						<input type="hidden" name="self_country_stepweight_<s:property value="#map.courier_id"/>" id="ctry_<s:property value="#map.courier_id"/>_C_stepweight" value=""/>
						<input type="hidden" name="self_country_stepfee_<s:property value="#map.courier_id"/>" id="ctry_<s:property value="#map.courier_id"/>_C_stepfee" value=""/>
						<input type="hidden" name="noship_country_<s:property value="#map.courier_id"/>" id="ctry_<s:property value="#map.courier_id"/>_D" value=""/>
						<input type="hidden" name="noship_country_name_<s:property value="#map.courier_id"/>" id="ctry_name_<s:property value="#map.courier_id"/>_D" value=""/>
				</td>
				
				<td id="note_<s:property value="#map.courier_id"/>">		
						
						<s:property value="#map.note"/>
				</td>
				<td><a href="javascript:shipdetail('<s:property value="#map.courier_id"/>','<s:property value="#map.courier_en_name"/>');">选择并设置</a></td>
			</tr>
		</s:iterator>
			<tr>
				<td colspan="5"><input type="submit" value="确定"/></td>
			</tr>
		</table>
	</div>
 </form>
</div>

<div id="step_two" style="display:none">

	<div>
		<input type="hidden" name="c_id" id="c_id" />
		<input type="hidden" name="c_name"  id="c_name"/>
		<input type="hidden" name="des_part"  id="des_part"/>
	</div>
		
	
	<div>
		<div><input type="checkbox" name="set_ship_detail" id="set_ship_detail_A" onclick="openWindow('A',this);"/>免运费</div>
		<div><input type="hidden" name="free_country" id="ctry_A"/>
			 <input type="hidden" name="free_country_name" id="ctry_name_A"/>
		</div>
		<div id="free_country">
			<div id="title_A" style="display:none"><a href="javascript:modifyWindow('A');">修改</a></div>
			<div id="countylist_A"></div>
		</div>
	</div>
	
	<div>
		<div><input type="checkbox" name="set_ship_detail" id="set_ship_detail_B"  onclick="openWindow('B',this);"/>标准运费</div>
		<div>
			<input type="hidden" name="stanard_country" id="ctry_B"/>
			<input type="hidden" name="stanard_country_name" id="ctry_name_B"/>
		</div>
		<div id="stanard_country">
			<div id="title_B" style="display:none"><a href="javascript:modifyWindow('B');">修改</a></div>
			<div id="countylist_B"></div>
		</div>
	</div>
	
	<div>
		<div><input type="checkbox" name="set_ship_detail" id="set_ship_detail_C"  onclick="openWindow('C',this);"/>自定义运费</div>
		<div>
			<input type="hidden" name="self_country" id="ctry_C"/>
			<input type="hidden" name="self_country_name" id="ctry_name_C"/>
		</div>
		<div id="self_country">
			<div id="title_C" style="display:none"><a href="javascript:modifyWindow('C');">修改</a></div>
			<div id="countylist_C"></div>
			<div id="ship_set_C"></div>
		</div>
		
	</div>

	<div>
		<div><input type="checkbox" name="set_ship_detail" id="set_ship_detail_D"  onclick="openWindow('D',this);"/>不发货</div>
		<div><input type="hidden" name="noship_country" id="ctry_D"/>
			 <input type="hidden" name="noship_country_name" id="ctry_name_D"/>
		</div>
		<div id="noship_country">
			<div id="title_D" style="display:none"><a href="javascript:modifyWindow('D');">修改</a></div>
			<div id="countylist_D"></div>
		</div>
	</div>
	
	<input type="button" value="确定" onclick="setDetail()"/>
</div>
