<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="common.jsp" %>

<script language="javascript">
$(document).ready(function(){
	$("#add_btn").click(function(){
		openForm("#dialog-add-attr","/s004/attr_add.jsp",300,350);
	});
	$("#btn-add-section").click(function() {
		openForm("#dialog-add-section","/s004/kind_section.jsp?a="+Math.random(),200,300);
	});
});

function openkind(){
	dialog_model('/admin/load_tree.do?code=kind_code&value=kind_name','产品类型');
}

function opensection(){
	dialog_model("/admin/section_tree.do?code=section_code&value=section_name&kind_code="+$('#kind_code').val()+"",'属性分段');
}

</script>

</head>
<body>

<div id="search_box">
<%
	String kindCode = (String)request.getAttribute("kindcode");
	String kindName = (String)request.getAttribute("kindname");
	String sectioncode = (String)request.getAttribute("sectioncode");
	String sectionname = (String)request.getAttribute("sectionname");
%>
	<form action="/admin/kind_attr_list.do" method="post">
		<label>产品类型:</label>
			<input type="text" name="kind_name"  id="kind_name" value="<%=kindName %>" ondblclick="openkind()" >
			<input type="hidden" name="kind_code" id="kind_code" value="<%=kindCode %>"/>
		<label>属性分段:</label>
			<input type="text" name="section_name" id="section_name" value="<%=sectionname%>" ondblclick="opensection()" />
			<input type="hidden" name="section_code" id="section_code" value="<%=sectioncode%>"/>
		<input type="submit" value="查询"/> 
		<input type="button" id="btn-add-section" value="增加分段"/>
		<input type="button" id="add_btn" value="增加属性"/> 
	</form>
	
</div>

<div id="dialog-add-attr" style="display:none" title="产品属性"></div>
<div id="dialog-add-section" title="类型分段"></div>

<div id="msg_dialog"  title="提示信息" ></div>
 
 	<div id="model_list_frame">
		<table class="data_list">
				<tr>
					<th >种类代码</th>
				    <th >种类名称</th>
				    <th >种类英文名称</th>
				    <th >分区代码</th>
				    <th >属性名称</th>
				    <th >属性英文名称</th>
				    <th >属性类型</th>
				    <th >属性名称</th>
				    <th >属性标签</th>
				    <th >属性标签英文</th>
				    <th >默认值</th>
				    <th >备注</th>
				    <th >操作</th>
			  	</tr>
			<s:iterator var="map" value="#request.list">  
				  <tr>
				    <td ><s:property value="#map.kind_code" /></td>
				    <td ><s:property value="#map.kind_name" /></td>
				    <td ><s:property value="#map.kind_name_en" /></td>
				    <td ><s:property value="#map.section_code" /></td>
				    <td ><s:property value="#map.section_name" /></td>
				    <td ><s:property value="#map.section_name_en" /></td>
				    <td >
				    	<s:if test="#map.attr_type==1">check</s:if>
				    	<s:if test="#map.attr_type==2">input</s:if>
				    	<s:if test="#map.attr_type==3">select</s:if>
				    </td>
				    
				    <td ><s:property value="#map.attr_name" /></td>
				    <td ><s:property value="#map.attr_label" /></td>
				    <td ><s:property value="#map.attr_label_en" /></td>
				    <td ><s:property value="#map.defaule_value" /></td>
				    <td ><s:property value="#map.note" /></td>
				    <td >修改 删除</td>
				  </tr>	    	
			</s:iterator>
		</table>
	</div>
	


</body>
</html>