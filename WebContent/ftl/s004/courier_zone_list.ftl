<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../public/admin/css/model.css" type="text/css" />
<script type="text/javascript" src="../public/js/jquery-1.9.1.js"></script>
</head>
<body>

<div id="model_nav_box">
	<div id="box_nav_title">快递公司管理</div>
	<div id="box_nav_op"><a href="./courier_view.do?act=toadd&id=0">添加快递公司</a></div>
</div>



<div id="model_list_box">
	<div id="model_nav_box">
		<div id="box_nav_title">${Request["courier_en_name"]}</div>
		<div id="box_nav_op"><a href="./courier_viewzone.do?act=toadd">增加分区</a></div>
	</div>
	<div id="model_list_frame">
				<table class="data_list">
					<tr>
				      <th width="50px">分区</th>
				      <th width="50px">起重</th>
				      <th width="80px">基本运费</th>
				      <th width="50px">续重</th>	
				      <th width="80px">续重费用</th>
				      <th width="80px">起泡长度</th>
				      <th width="260px">计泡公式(Kg)</th>
				      <th >国家列表</th>
				      <th width="100px">操作</th>
				    </tr>
				    
				   <#if Request["list"]?exists>
				   <#assign list = Request["list"]>
						<#list list as item>
						    <tr height="40px">
						      <td>
						      		${item.zone_name?if_exists}<br>
						      		${item.zone_name_en?if_exists}
						      </td>
						      <td align="right" >${item.basic_weight?if_exists}克</td>
						      <td align="right">
						      	$${item.basic_freight_USD?if_exists}元<br>
						      	￥${item.basic_freight_RMB?if_exists}元
						      	</td>
						      <td align="right">${item.step_weight?if_exists}克</td>
						      <td align="right">
						      	$${item.step_freight_USD?if_exists}元<br>
						      	￥${item.step_freight_RMB?if_exists}元
						      </td>
						      <td align="right">${item.min_length?if_exists}厘米</td>
						      <td align="left">
						      	长(厘米)x宽(厘米)x高(厘米)
						      	&nbsp;&nbsp;${item.bulb_weight_op?if_exists}
						      	&nbsp;&nbsp;${item.bulb_weight_param?if_exists}&nbsp;&nbsp;
						      </td>
						      <td align="left" style="overflow:hidden;" width="300px"> ${item.country_name?if_exists}</td>
						      
						      <td align="center">
						        <a href="./courier_viewzone.do?act=tomod&id=${item.courier_id?if_exists}&zone_id=${item.zone_id?if_exists}" title="编辑">编辑</a>  |
						        <a href="./courier_viewzone.do?act=todel&id=${item.courier_id?if_exists}&zone_id=${item.zone_id?if_exists}" title="删除">删除</a> 
						      </td>
						    </tr>
						  </#list>
					</#if>
				</table>
	</div>
	
<div>

</body>
</html>

