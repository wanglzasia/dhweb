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
		url: '/admin/add_kind.do',
		type: 'POST',
		data:{
				parent_kind_code:   $("#parent_kind_code").val(), 
				kind_name: $("#kind_name_id").val(),
				kind_name_en:  $("#kind_name_en").val()
			},
		dataType: 'text',
		timeout: 1000,
		error: function(){alert('Error loading document');},
		success: function(result){
			msg_box(result);
			$('#parent_kind_code').val('');
			$('#parent_kind_name').val('');
			$('#kind_name').val('');
			$('#kind_name_en').val('');
		}
	});
}

function openkind(){
	dialog_model('/admin/load_tree.do?code=parent_kind_code&value=parent_kind_name','产品种类');
}
</script>
</head>
<body>
	
 	<form action="aaa">
 		<div>
	 		<label>上级类型</label>
			<input type="hidden" name="parent_kind_code" id="parent_kind_code" value=""/>
			<input type="text" name="parent_kind_name" id="parent_kind_name" value="" style='width:200px;' ondblclick="openkind()"/>
		</div>
		
		<div>
			<label>类型名称</label>
			<input type="text" name="kind_name" id="kind_name_id" value="" style='width:200px;'/>
		</div>
		
		<div>
			<label>英文名称</label>
			<input type="text" name="kind_name_en" id="kind_name_en" value="" style='width:200px;'/>
		</div>
		
		<div>
			<input type="button" onclick="add()" value="提交" id="Submit1">
		</div>
 	</form>

</body>
</html>