<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">
	$(document).ready( function() {
		$("#operaciones").hover( function() {
			$("#opercontent").css({
				visibility :"visible",
				display :"none"
			}).slideDown("slow", hideOperOpen);
		}, function() {
			$("#opercontent").css({
				visibility :"hidden",
				display :"none"
			}).slideUp("slow", showOperOpen);
		});
	});

	function hideOperOpen()
	{
		$("#operopen").hide();
	}

	function showOperOpen()
	{
		$("#operopen").show();
	}
</script>

<div id="opercontent">
	<html:form action="/assetoper" method="post">
		<cnt:operaciones elements="operationAssets"/>
	</html:form>
</div>
<c:if test='${not empty operationAssets}'>
	<div id="operopen" title="Muestra operaciones">
	Operciones
	</div>
</c:if>
