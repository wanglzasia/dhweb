<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<div class="frame_box" >
	<div id="frame_box">
	
		<div class="frame_kind_box">
			<select name="" size="10" onclick="selkind(this,0)" id="kind_sel_0">
				<s:iterator var="map" value="#request.kindlist">  
			  		<option value="<s:property value="#map.kind_code" />"><s:property value="#map.kind_name" /></option>
			  	</s:iterator>
			</select>
		</div>
	
	
	</div>
	 <div class="clear"></div>
</div>

<form action="/seller/addpro.do" method="post">
<div style="margin-top:10px;border:1px solid #b1cae8;background-color:#eef5fd;width:780px;padding:10px 10px 10px 10px">您当前选择的目录是：
	<input type="text" style="border:0px;background-color:transparent;outline:none;width:500px;" readonly id="kind_name_ipt" name="kindtree"/>
	<input type="hidden" name="kind_code" id="kind_code" />
</div>
<div class="btn" style="text-align:center;">
	<input type="submit" disabled="disabled" value="发布产品" id="submitbtn">
</div>
</form>

















