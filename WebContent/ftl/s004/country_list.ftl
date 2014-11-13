<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../public/admin/css/model.css" type="text/css" />
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
<link rel="stylesheet" type="text/css" href="/public/msdropdown/css/msdropdown/flags.css" />

<script>
	
function checkselect()
{
	var shortname="";
	var cnname="";
	var cnnameid="";
	var i = 0;
	$('input[type="checkbox"][name="country_name"]:checked').each(
	function() {
		shortname+=$(this).val()+",";
		cnnameid = "#cn_name_"+$(this).val();
		cnname+=$(cnnameid).val()+",";
		i++;
	}
	);
	
	if(i>0)
	{
		shortname=shortname.substr(0,shortname.length-1);
		cnname=cnname.substr (0,cnname.length-1);
	}
		
	window.opener.document.getElementById("${Request["code"]}").value = shortname;
    window.opener.document.getElementById("${Request["value"]}").value = cnname;
	window.opener.document.getElementById("country_name_v").innerHTML = cnname;
	window.close();
}
function selectAll()
{
	 if($("#sel_btn").is(":checked")){
		$("input[name='country_name']").prop("checked",true); 
	 }else{
		$("input[name='country_name']").prop("checked",false);
	 }
}

</script>

</head>

<body>
<#if Request["list"]?exists>
	<#assign list = Request["list"]>
	<table>
	<#list list as item>
		<tr>
			<td >
				<input type="checkbox" name="country_name" value="${item.SHORT_NAME?lower_case}"/>
				<input type="hidden" name="cn_name" id="cn_name_${item.SHORT_NAME?lower_case}" value="${item.CN_NAME?if_exists}"/>
			</td>
			<td ><span class="flag ${item.SHORT_NAME?lower_case}"></span></td>
			<td >${item.SHORT_NAME?if_exists}</td>
			<td >${item.FULL_NAME?if_exists}</td>
			<td >${item.CN_NAME?if_exists}</td>
		<tr>
		
	</#list>
	<tr>
		<td colspan="4" align="left">
			全选<input type="checkbox" id="sel_btn" onchange="selectAll()"/>
		</td>
		<td align="right">
			<input type="button" value="确定" onclick="checkselect()"/>		
		</td>
	</tr>
	</table>
	
	
</#if>
</body>
</html>