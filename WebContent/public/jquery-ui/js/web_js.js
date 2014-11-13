var VERSION = "webx js version 1.0.0";

function openForm(dialog,url,d_height,d_width)
{
	$(dialog).dialog({autoOpen: false, height: d_height, width: d_width, modal: true});
	$(dialog).load(url,null,function(){});
	$(dialog).dialog("open");
}

function openFrm(dialog,url){
	openForm(dialog,url,300,350);
}

function dialog_model(url,title){ 
	var height='500';
	var width='300';
	var params="height="+height+", width="+width+", top=200, left=200,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no";
	window.open(url,title,params); 
}

function countrylist(url,title)
{
	var height='550';
	var width='450';
	var params="height="+height+", width="+width+", top=80, left=350,toolbar=no, menubar=no, scrollbars=yes, resizable=yew, location=no, status=no";
	window.open(url,title,params); 
}
function modelDialog(dialog,d_height,d_width)
{
	//$( "#create-user" )
	//.button()
	//.click(function() {
	//	$( "#dialog-form" ).dialog( "open" );
	//});
	
	$(dialog).dialog({autoOpen: false, height: d_height, width: d_width, modal: true});
	$(dialog).dialog("open");
}

function msgbox(){
	var msg = arguments[0] ? arguments[0] : '消息';
	var title = arguments[1] ? arguments[1] : '消息';
	var html = "<div id='dialog' title='"+title+"'>";
	html = html + "<p>"+msg+"</p>";
	html = html + "</div>";
	$(body).append(html);
	$("#dialog").dialog();
}

function msg_box(msg){
	$("#msg_dialog").html("");
	$("#msg_dialog").append("<p>"+msg+"</p>");
	//$("#msg_dialog").dialog();
	$("#msg_dialog").dialog({
		modal: true,
		buttons: {
			Ok: function() {http://127.0.0.1:8080/admin/index.html
				$( this ).dialog( "close" );
			}
		}
	});
}


function auditpass(productid){
	$("#audit_frm_1").attr("action","/admin/auditPass.do");
	$("#af_proid").val(productid);
	$("#audit_frm_1").submit();
}

function auditnopass(productid){
	$("#audit_frm_1").attr("action","/admin/auditNoPass.do");
	$("#af_proid").val(productid);
	$("#audit_frm_1").submit();
}


