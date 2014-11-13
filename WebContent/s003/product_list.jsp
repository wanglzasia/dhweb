<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.dh.ora.s001.bean.User" %>
<%@ taglib uri="/WEB-INF/tld/OptionListTag.tld" prefix="OptionList" %>
<div style="width:780px;padding:10px 10px 10px 10px;border:1px solid #eceded;"><!-- margin:0 auto; -->
<div>
	<form action="/seller/querypro.do" method="post">
		<div style="margin: 0px auto;background-color:#fbfbfb;border:1px solid #eceded;">
			<div style="padding:20px;">
				<div style="line-height:30px;">
					<label>产品编号：</label><input type="text" name="pro_no"   style="width:100px;"  value=""/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label>产品名称：</label><input type="text" name="pro_name" style="width:100px;"  value=""/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label>产品类型：</label><input type="text" name="pro_kind" style="width:100px;"  value=""/>
				</div>
				
							
				<div style="line-height:30px;">
					<label>&nbsp;&nbsp;关 键 词：</label><input type="text" name="key_words" style="width:218px;"  value=""/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label>&nbsp;&nbsp;状态：</label>
					<OptionList:OptionList name="pro_stat" id="testa" cssclass="jj" style="width:100px;" code="status_code" order="status_code" key="status_name as value" table="s_product_stat" keyAlis="value"/>		
				</div>
					
				<div style="line-height:30px;">
					<label>上架时间：</label><input id="datepicker1" type="text" name="pro_name" style="width:100px;"  value=""/>		
					<label>到</lable>
					<input id="datepicker2" type="text" name="pro_name" style="width:100px;"  value=""/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
					<label>更新日期：</label><input id="datepicker3" type="text" name="pro_name1" style="width:100px;"  value=""/>		
					<label>到</lable>
					<input id="datepicker4" type="text" name="pro_name2" style="width:100px;"  value=""/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" value="查询" style="width:80px;"/>
				</div>
			</div>
		</div>
	</form>
</div>

<%
 User usr = (User)session.getAttribute("usr");

%>
<div id="list_cell">
	<table id="c_table">
			<tr>
				<th >序号</th>
			    <th >产品编号</th>
			    <th >产品名称</th>
			    <th >品牌</th>
			    <th >状态</th>
			    <th >单位</th>
			    <th >操作</th>
		  	</tr>
		<s:iterator var="map" value="#request.list">  
			  <tr>
			    <td style="width:50px;"><s:property value="#map.rownum" /></td>
			    <td style="width:120px;"><s:property value="#map.product_no" /></td>
			    <td style="width:300px;"><s:property value="#map.product_name" /></td>
			    <td ><s:property value="#map.brand_code" /></td>
			    <td ><s:property value="#map.status_name" /></td>
			    <td ><s:property value="#map.value" /></td>
			    <td > 
			    	

			    	<s:if test="%{#map.product_stat==2}"><!-- 审核通过 -->
			    		<a href="/seller/view_<%=usr.getUserId()%>_<s:property value="#map.product_id"/>.do?p=<s:property value="#map.product_id"/>" target="_blank">预览</a>&nbsp;&nbsp;
			    		<a href="/seller/putOnShelves.html?pro_id=<s:property value="#map.product_id"/>">上架</a>&nbsp;&nbsp;
			    	</s:if>
			    	<s:if test="%{#map.product_stat==3}"><!-- 上架 -->
			    		<a href="/seller/view_<%=usr.getUserId()%>_<s:property value="#map.product_id"/>.do?p=<s:property value="#map.product_id"/>" target="_blank">预览</a>&nbsp;&nbsp;
			    		<a href="/seller/pullOffShelves.html?pro_id=<s:property value="#map.product_id"/>">下架</a>&nbsp;&nbsp;
			    	</s:if>			    	
			    	<s:if test="%{#map.product_stat==4}"><!-- 下架 -->
			    		<a href="/seller/view_<%=usr.getUserId()%>_<s:property value="#map.product_id"/>.do?p=<s:property value="#map.product_id"/>" target="_blank">预览</a>&nbsp;&nbsp;
			    		<a href="/seller/putOnShelves.html?pro_id=<s:property value="#map.product_id"/> ">上架</a>&nbsp;&nbsp;
			    	</s:if>	
			    	<a href="/seller/show.do?pro_id=<s:property value="#map.product_id"/>">修改</a>&nbsp;&nbsp;
			    	
			    	<input type="hidden" value="<s:property value="#map.product_id" />" >
			    	<input type="hidden" value="<s:property value="#map.product_kind" />" >
			    </td>
			  </tr>	    	
		</s:iterator>
	</table>
	
	<div>
		<jsp:include page="/common/page_nav.jsp"/>
	</div>
	
</div>
</div>


<script language="javascript">
<!--
$( "#datepicker1" ).datepicker();
$( "#datepicker1" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
$( "#datepicker2" ).datepicker();
$( "#datepicker2" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
$( "#datepicker3" ).datepicker();
$( "#datepicker3" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
$( "#datepicker4" ).datepicker();
$( "#datepicker4" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
//-->
</script>