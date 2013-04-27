<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">
	$(document).ready( function() {
		initDrag();
	});
	
	function buscaTag(tag) {
		cargaDiv("resultado", "${pageContext.request.contextPath}/buscatag.do", ordenaNTag, pensandoAjax, null, "tagPk=" + tag);
		cargaNube($("#tiponube").val());
	}
</script>

<cnt:nube elements="listTags" style="tagnube drag"/>
