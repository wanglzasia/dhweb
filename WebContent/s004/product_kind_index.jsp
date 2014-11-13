<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="common.jsp"/>
<script language="javascript">
$(document).ready(function(){
	$("#btn-add").click(function(){
		openForm("#dialog-add-kind","/s004/kind.jsp?a="+Math.random(),180,300);
	});
	$("#btn-add-section").click(function() {
		openForm("#dialog-add-section","/s004/kind_section.jsp?a="+Math.random(),200,300);
	});
});

function openkind(){
	dialog_model('/admin/load_tree.do?code=kind_code&value=kind_name','产品类型');
}
</script>
</head>
<body>
<%
	String kindCode = (String)request.getAttribute("kindcode");
	String kindName = (String)request.getAttribute("kindname");	
%>
	<div>
		<form action="/admin/kind_section_list.do" method="post">
		 	<label>产品类型</label>
		 	<input type="hidden" name="kind_code" id="kind_code" value="<%=kindCode %>" />
		 	<input type="text" name="kind_name" id="kind_name" value="<%=kindName %>"  ondblclick="openkind()"/>
		 	<input type="submit" value="查询"/>
		 	<input type="button" id="btn-add" value="增加类型"/>
		 	<input type="button" id="btn-add-section" value="增加分段"/>
	 	</form>
	</div>
	
	<div id="dialog-add-kind" title="产品类型"></div>
	<div id="dialog-add-section" title="类型分段"></div>
	<div id="msg_dialog"  title="提示信息" ></div>
	
	
	<div id="list_cell">
		<table id="c_table">
				<tr>
					<th >种类代码</th>
				    <th >种类名称</th>
				    <th >种类英文名称</th>
				    <th >分区代码</th>
				    <th >属性名称</th>
				    <th >属性英文名称</th>
				    <th >备注</th>
			  	</tr>
			<s:iterator var="map" value="#request.list">  
				  <tr>
				    <td ><s:property value="#map.kind_code" /></td>
				    <td ><s:property value="#map.kind_name" /></td>
				    <td ><s:property value="#map.kind_name_en" /></td>
				    <td ><s:property value="#map.section_code" /></td>
				    <td ><s:property value="#map.section_name" /></td>
				    <td ><s:property value="#map.section_name_en" /></td>
				    <td ><s:property value="#map.note" /></td>
				  </tr>	    	
			</s:iterator>
		</table>
	</div>
	
	
</body>
</html>