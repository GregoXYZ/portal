<%@ include file="/common/includes/tagLibs.jspf"%>

<script type="text/javascript"> 
	$(document).ready(function() { 
	   // Muestra y oculta los menús
	   mainmenu(); 
	});
	
	function mainmenu() {
		$("#topmenu .nav li").hover( function() {
			$(this).find('ul:first:hidden').css( {
				visibility :"visible",
				//display: "block"
				display :"none"
			}).show(250);
		}, function() {
			$(this).find('ul:first').css({
				visibility :"hidden",
				display :"none"
			}).hide("slow");
		});
	}
</script> 

<nav id="topmenu" class="menu">
	<frm:menu />
</nav>