<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

    
 
    
    
    

    
    
<script language="javascript">
$(document).ready(function(){
		
	$('#tstbtn').click(function(){
		//$('.theme-popover-mask').fadeIn(100);
		//$('.theme-popover').slideDown(200);
		$("#console_form").load("/s003/abcd.jsp?a=343434", function(){		
			$('#console_form').show();
			$('#theme-popover-mask').show();
		 });
	});
		
	$('#frm_title_btn .close').click(function(){
		//$('.theme-popover-mask').fadeOut(100);
		//$('.theme-popover').slideUp(200);
		$('#console_form').hide();
		$('#theme-popover-mask').hide();
		
	});
});
function closeWindow()
{
	$('#console_form').hide();
	$('#theme-popover-mask').hide();
	$('#console_form').html("");
}
</script>

<input type="button" value="Test" class="btn1 btn-primary" id="tstbtn"/>
