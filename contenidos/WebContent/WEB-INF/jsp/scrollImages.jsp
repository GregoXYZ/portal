<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/addons/jquery.carousel.pack.js"></script>

<c:choose>
	<c:when test='${not empty listMiniaturas}'>
		<div id="carrousel" class="center" style="text-align: center">
			<cnt:miniatura elements="listMiniaturas" id="scroller" type="scroll"/>
	   	</div>
		<div id="amplia">
			Situa el ratón sobre la imagen que deseas ampliar
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
