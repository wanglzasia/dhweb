<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>

<head>
<jsp:include page="common.jsp"/>
<link rel="StyleSheet" href="/public/dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="/public/dtree/dtree.js"></script>

<script language="javascript">


function openkind(){
	dialog_model('/admin/load_tree.do?code=parent_kind_code&value=parent_kind_name','产品类型');
}
function add()
{
	$('#parent_kind_code').val('');
	$('#parent_kind_name').val('');
	$('#kind_name').val('');
	$('#kind_name_en').val('');
	
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
			$('#parent_kind_code').val('');
			$('#parent_kind_name').val('');
			$('#kind_name').val('');
			$('#kind_name_en').val('');
			$("#kind_frm").submit();
		}
	});
}
function mod()
{
}
function remove()
{
	
}
</script>

</head>
<body>


<div>
	<input type="button" id="btn-add" value="增加"  class="btn" data-toggle="modal" data-target="#dialog-add-kind"/>
	<!--
	<input type="button" id="btn-add-section" value="修改"  class="btn" data-toggle="modal" data-target="#dialog-add-kind"/>
	<input type="button" id="btn-add-section1" value="删除" class="btn" data-toggle="modal" data-target="#dialog-add-kind"/>
	-->
</div>


<div id="dialog-add-kind" title="产品类型" style="display:none">
	<form action="/admin/productkind.do" method="post" id="kind_frm">
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
</div>
	
<div id="dialog-add-section" title="类型分段"></div>
<div id="msg_dialog"  title="提示信息" ></div>






<div class="dtree">
<p><a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a></p>
<script type="text/javascript">	
d = new dTree('d');
d.add(1000,-1,'根目录',"javascript:selectnode('1000','根目录')");
${dtree}
document.write(d);

//function selectnode(nodecode ,nodename){
//	
//	window.opener.document.getElementById("${code}").value = nodecode;
//	window.opener.document.getElementById("${value}").value = nodename;
//	window.close();
//}
</script>
</div>

</body>
</html>

 
