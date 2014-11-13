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
		url: '/admin/add_attr.do',
		type: 'POST',
		data:{
				kind_code:   $("#kind_code2").val(), 
				section_code: $("#section_code2").val(),
				attr_type:  $("#attr_type").val(),
				attr_label:  $("#attr_label").val(),
				attr_label_en:	$("#attr_label_en").val(),
				default_value: $("#default_value").val(),
				note:$("#note").val(),
				is_keyword:$("#is_key_word").val()
			},
		dataType: 'text',
		timeout: 1000,
		error: function(){alert('Error loading document');},
		success: function(result){
			
			msg_box(result);
			
			$("#attr_label").val('');$("#attr_label_en").val('');$("#default_value").val('');
			$("#note").val('');
		
		}
	});
}

function openkind(){
	dialog_model('/admin/load_tree.do?code=kind_code2&value=kind_name2','产品类型');
}
function opensection(){
	dialog_model("/admin/section_tree.do?code=section_code2&value=section_name2&kind_code="+$('#kind_code2').val()+"",'属性分段');
}
/*
 * 	$.post(
			"/admin/addattr.do",
			{
				kind_code:   $("#kind_code").val(), 
				secion_code: $("#secion_code").val(),
				param_type:  $("#param_type").val(),
				param_name:  $("#param_name").val(),
				param_name_en:	$("#param_name_name").val()
			},
			function(data,textStatus)
			{
				alert(data);
				//alert(textStatus);
			},
			"html" 
		);
 */
 
/*
 "xml": 返回 XML 文档，可用 jQuery 处理。
 "html": 返回纯文本 HTML 信息；包含的 script 标签会在插入 dom 时执行。
 "script": 返回纯文本 JavaScript 代码。不会自动缓存结果。除非设置了 "cache" 参数。注意：在远程请求时(不在同一个域下)，所有 POST 请求都将转为 GET 请求。（因为将使用 DOM 的 script标签来加载）
 "json": 返回 JSON 数据 。
 "jsonp": JSONP 格式。使用 JSONP 形式调用函数时，如 "myurl?callback=?" jQuery 将自动替换 ? 为正确的函数名，以执行回调函数。
 "text": 返回纯文本字符串
*/
</script>
</head>
<body>
 	<form action="aaa">
 		<div>
	 		<label>产品类型</label>
			<input type="hidden" name="kind_code" id="kind_code2" value=""/>
			<input type="text" name="kind_name" id="kind_name2" value="" style='width:200px;' ondblclick="openkind()"/>
		</div>
		<div>
			<label>属性分段</label>
			<input type="hidden" name="section_code" id="section_code2" value="" />
			<input type="text" name="section_code" id="section_name2" value="" style='width:200px;' ondblclick="opensection()"/>
		</div>
		<div>
			<label>元素类型</label>
			<select id="attr_type" name="attr_type" style='width:200px;'>
				<option value='1'>check box</option>
				<option value='2'>input text</option>
				<option value='3'>select</option>
			</select>
		</div>
		<div>
			<label>属性名称</label>
			<input type="text" name="attr_label" id="attr_label" style='width:200px;'/>
		</div>
		<div>
			<label>英文名称</label>
			<input type="text" name="attr_label_en" id="attr_label_en" style='width:200px;'/>
		</div>
		
		<div>
			<label>关键字&nbsp;</label>
			是<input type="radio" name="is_key_word" value='1'/>
			否<input type="radio" name="is_key_word" value='0' checked/>
		</div>
		
		<div>
			<label style="margin-left:13px;">默认值</label>
			<textarea  name="default_value" id="default_value" style='width:200px;'></textarea>
		</div>
		<div>
			<label style="margin-left:26px;">备注</label>
			<input type="text" name="note" id="note" style='width:200px;'/>
		</div>
		<div>
			<input type="button" onclick="add()" value="提交" id="Submit1">
		</div>
 	</form>
</body>
</html>