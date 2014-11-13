<div class="form_dialog" style="width:800px;margin-top:150px;">
	
	<div class="form_dialog_title">
		<span class="form_title">Title</span>
		<span class="form_botton"><a href="javascript:closeWindow();" title="关闭" class="close">×</a></span>
	</div>
	
	<div class="form_content">
		<div id="info" class="countrybox f-pop-box1">
		
		</div>
	</div>	
	
	<div>
		<div><input type="button" value="确定" onclick="confirm_ok()"/></div>
	</div>
</div>


 <script>

 $(document).ready(function(){
	 
	 <%
	 	String courier_id = (String)request.getParameter("courier_id");
	 	String selTyle  = (String)request.getParameter("selTyle");
	 %>
	 
	 curSelzonelit = zone_country_list[<%=courier_id%>];
     $("#info").html("");//清空info内容
	 if(curSelzonelit){
		 //console.log("缓存里有物流公司的国家区数据，从缓存取");
		 loadCourierZone(curSelzonelit,<%=courier_id%>);
		 
	  }else{
		  //console.log("缓存里没有物流公司的国家区数据，从服务器取");
		  $.getJSON("/seller/courier_zoneJson.do?courier_id=<%=courier_id%>", function(data) {
		        zone_country_list[<%=courier_id%>] = data;
		        curSelzonelit = zone_country_list[<%=courier_id%>];
		        loadCourierZone(data,<%=courier_id%>);
		   });
	  }

 });
 </script>