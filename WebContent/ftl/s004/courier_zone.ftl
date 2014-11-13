<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="/public/admin/css/model.css" type="text/css" />
<script type="text/javascript" src="/public/js/jquery-1.9.1.js"></script>
<script type="text/javascript"  src="/public/jquery-ui/js/web_js.js?a=09"></script>
<script>
function loadCountry()
{
	countrylist("/admin/courier_countrylist.do?code=country&value=country_name","国家列表");
}
</script>
</head>
<body>

<div id="model_nav_box">
	<div id="box_nav_title">快递公司管理</div>
	<div id="box_nav_op"><a href="./courier_list.do">快递公司列表</a></div>
</div>

<div id="model_list_box">	
	<div id="model_list_frame">
	   <p></p>
		<table width="100%" class="input_table">
			<form  action="./courier_opzone.do" method="post">
				<tr>
					<td class="label">快递公司</td>
					<td>
						${Request["courier_en_name"]}
						<input type="hidden" name="courier_id" value="${Request["courier_id"]}"/>
					</td>
				<tr>
				
		   <#if Request["list"]?exists>
		   <#assign list = Request["list"]>
				<#list list as item>
				<tr>
					<td class="label">分区名称(中文)</td>
					<td><input type="text" name="zone_name" value="${item.zone_name?if_exists}" size="40"/>
						<input type="hidden" name="zone_id" value="${item.zone_id?if_exists}"/>
					</td>
				<tr>
				<tr>
					<td class="label">分区名称(英文)</td>
					<td><input type="text" name="zone_name_en" value="${item.zone_name_en?if_exists}" size="40"/></td>
				<tr>			
				<tr>
					<td class="label">起重(克)</td>
					<td><input type="text" name="basic_weight" size="40" value="${item.basic_weight?if_exists}"/></td>
				<tr>
				<tr>
					<td class="label">运费</td>
					<td>
						<input type="text" name="basic_freight_USD" size="10" value="${item.basic_freight_USD?if_exists}"/>(美元)
						<input type="text" name="basic_freight_RMB" size="10" value="${item.basic_freight_RMB?if_exists}"/>(人民币)
					</td>
				<tr>
				
				<tr>
					<td class="label">续重(克)</td>
					<td><input type="text" name="step_weight" size="40" value="${item.step_weight?if_exists}"/></td>
				<tr>
				<tr>
					<td class="label">续费</td>
					<td>
						<input type="text" name="step_freight_USD" size="10" value="${item.step_freight_USD?if_exists}"/>(美元)
						<input type="text" name="step_freight_RMB" size="10" value="${item.step_freight_RMB?if_exists}"/>(人民币)
					</td>
				<tr>				
				
				<tr>
					<td class="label">起泡长度</td>
					<td>
						<input type="text" name="min_length" size="5" value="${item.min_length?if_exists}"/>(厘米)
						包装长，宽，高任一单边超过此值，进行计算体积重
					</td>
				<tr>
				<tr>
					<td class="label">计泡公式</td>
					<td>
						长(厘米) x 宽(厘米) x 高(厘米)
						<select name="bulb_weight_op">
							<option value='除' >除</option>
							<option value='乘' >乘</option>
						</select>
						<input type="text" name="bulb_weight_param" size="5" value="${item.bulb_weight_param?if_exists}"/> 结果单位(Kg)
					</td>
				<tr>				

				<tr>
					<td class="label" valign="top">国家列表</td>
					<td>
						<div>
							<div>
								<a href="javascript:loadCountry();">编辑</a>
								<input type="hidden" name="country" id="country" value="${item.country?if_exists}"/>
								<input type="hidden" name="country_name" id="country_name" value="${item.country_name?if_exists}"/>
							</div>
							<div id="country_name_v">${item.country_name?if_exists}</div>
						</div>
					</td>
				<tr>
				</#list>
				</#if>			
				
				<!--	
				<tr>
					<td class="label">备注</td>
					<td><input type="text" name="note"  size="40" value=""/></td>
				<tr>
				-->
								
				  <tr align="center">
				    <td colspan="2" >
				      <input type="hidden" name="act" value="${Request["act"]}">
				      <input type="submit" class="button" name="Submit" value=" 确定 ">
				      <input type="reset" class="button" name="Reset" value=" 重置 ">
				    </td>
				  </tr>
				  
			</form>
		</table>
	</div>
<div>

</body>
</html>