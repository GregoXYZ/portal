<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">
	$(document).ready( function() {
		setTimeout(back,5000);
	});

	function back()
	{
		//goUrl("${pageContext.request.contextPath}/home.do");
	}
</script>

<div id="webinfodata" class="textarea">
	<frm:avisoweb info="${info}"/>
</div>