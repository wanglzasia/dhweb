<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="common.jsp"/>
<script>
function add()
{
	$.ajax({
		url: '/admin/add_section.do',
		type: 'POST',
		data:{
			kind_code1: $("#kind_code1").val(), 
			section_name: $("#section_name").val(),
			section_name_en: $("#section_name_en").val(),
			note: $("#note").val()
			},
		dataType: 'text',
		timeout: 1000,
		error: function(){alert('Error loading document');},
		success: function(result){ 
			msg_box(result);
			$("#kind_code1").val('');$("#kind_name1").val('');$("#section_name").val('');
			$("#section_name_en").val('');$("#note").val('');
		}
	});
}

function openkind(){
	dialog_model('/admin/load_tree.do?code=kind_code1&value=kind_name1','产品类型');
}
</script>
</head>
<body>
 	<form action="aaa" class="data_form">
 		<div>
	 		<label>产品类型</label>
	 		<input type="hidden" name="kind_code" id="kind_code1" value=""/>
	 		<input type="text" name="kind_name" id="kind_name1" value="" style='width:200px;' ondblclick="openkind()"/>
		</div>
		
		<div>
			<label>分区名称</label>
			<input type="text" name="section_name" id="section_name" value="" style='width:200px;'/>
		</div>
		
		<div>
			<label>英文名称</label>
			<input type="text" name="section_name_en" id="section_name_en" value="" style='width:200px;'/>
		</div>
		<div>
			<label style="margin-left:26px;">备注</label>
			<input type="text" name="note" id="note" style='width:200px;' value=""/>
		</div>			
		<div>
			<input type="button" onclick="add()" value="提交" id="Submit1">
		</div>
 	</form>
</body>
</html>