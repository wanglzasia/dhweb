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
	String kindCode = null==((String)request.getParameter("kind_code"))? "":(String)request.getParameter("kind_code");
	String kindName = null==((String)request.getParameter("kind_name"))? "":(String)request.getParameter("kind_name");
	String productname = null==((String)request.getParameter("product_name"))? "":(String)request.getParameter("product_name");
	String status = null==((String)request.getParameter("status"))? "":(String)request.getParameter("status");

%>
	
	<form id="audit_frm_1" action="/admin/auditPass.do" method="post" target='_blank'>
		<input type="hidden" name="pro_id" id="af_proid"/>
	</form>
	
	<div id="search_box">
		<form action="/admin/product_list.do" method="post">
		 	<label>产品类型</label>
		 	<input type="hidden" name="kind_code" id="kind_code" value="<%=kindCode %>" />
		 	<input type="text" name="kind_name" id="kind_name" value="<%=kindName %>"  ondblclick="openkind()"/>
		 	<label>产品名称</label><input type="text" name="product_name" id="product_name_id" value="<%=productname%>" />
		 	
		 	<label>状态</label>
		 	<select name="status">
		 		<option></option>
		 		<option value="0" <%if(status.equals("0")){%>selected<%}%>>待审核</option>
		 		<option value="1" <%if(status.equals("1")){%>selected<%}%>>审核通过</option>
		 		<option value="2" <%if(status.equals("2")){%>selected<%}%>>审核未通过</option>
		 		<option value="3" <%if(status.equals("3")){%>selected<%}%>>上架</option>
		 		<option value="4" <%if(status.equals("4")){%>selected<%}%>>下架</option>
		 	</select>
		 	<input type="submit" value="查询"/>
	 	</form>
	</div>
	
 
	<div id="msg_dialog"  title="提示信息" ></div>
	
	
	<div id="model_list_box">
	  <div id="model_list_frame">
		<table class="data_list">
				<tr>
					
					<th width="200px" >产品名称</th>
				    <th width="100px" >产品编码</th> 
				    <th width="200px">类型名称</th>
				    <th width="60px">单位</th>

				    
					<th width="60px">用户</th>
				    <th >录入时间</th>
				    <th >更新时间</th>
				    <th >状态</th>
				    
				    <th width="130px">操作</th>
			  	</tr>
			<s:iterator var="map" value="#request.list">  
				  <tr>
				    <td style="white-space:nowrap;overflow:hidden;" title="<s:property value="#map.product_name" />">
				    	<font style="font-size:14px;"><s:property value="#map.product_name" /></font>
				    </td>
				    <td align="center">
				    	<s:property value="#map.product_no" />
				    </td>
				    <td style="white-space:nowrap;overflow:hidden;">
				    	<s:property value="#map.kind_name" />
				    	(<s:property value="#map.kind_name_en" />)
				    </td>
				    <td style="white-space:nowrap;overflow:hidden;"><s:property value="#map.value" />(<s:property value="#map.value_en" />)</td>
				    
				    <td style="white-space:nowrap;overflow:hidden;"><s:property value="#map.user_id" /></td>
				    <td style="white-space:nowrap;overflow:hidden;"><s:property value="#map.init_date" /></td>
				    <td style="white-space:nowrap;overflow:hidden;"><s:property value="#map.update_date" /></td>
				    <td style="white-space:nowrap;overflow:hidden;"><s:property value="#map.status_name" /></td>
				    <td >
				    	<a href="javascript:view('<s:property value="#map.product_id" />')">查看</a>&nbsp;&nbsp;&nbsp;
				    	<a href="javascript:auditpass('<s:property value="#map.product_id" />')">通过</a>&nbsp;&nbsp;&nbsp;
				    	<a href="javascript:auditnopass('<s:property value="#map.product_id" />')">不通过</a>
				    	</td>
				  </tr>	    	
			</s:iterator>
		</table>
	</div>
		<div>
			<jsp:include page="/common/page_nav.jsp"/>
		</div>
	</div>	
</body>
</html>