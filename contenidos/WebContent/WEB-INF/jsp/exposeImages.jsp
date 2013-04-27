<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<c:choose>
	<c:when test='${not empty listMiniaturas}'>
		<div id="expose">
			<cnt:miniatura elements="listMiniaturas" id="mural" type="mural"/>
		</div>
	</c:when>
	<c:otherwise>
		<div class="webinfo">
			<div class="webmessage">
				No hay imagenes
			</div>
		</div>
	</c:otherwise>
</c:choose>
