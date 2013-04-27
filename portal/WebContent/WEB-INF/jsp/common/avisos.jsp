<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">
	$(document).ready(function() { 
		$(".text-notification").bind("mouseover", function(e)
		{
			$(this).fadeOut("slow");
		});
	});		
</script>

<frm:notificacion view="TypeView.ICON"/>
