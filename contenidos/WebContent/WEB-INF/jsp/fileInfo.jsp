<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<html:form action="/fileinfo" method="post">
	<div id="info-title">
		<c:if test='${not AssetInfoForm.preview}'>
			<img alt="" src="images/downloadfile16.png">
			<span>Descargar</span>
		</c:if>
		<c:if test='${AssetInfoForm.preview}'>
			<img alt="" src="/portal/images/btn_window_pop.gif">
			<span>Previsualizar</span>
		</c:if>
	</div>
	<c:if test='${AssetInfoForm.makeMiniature}'>
		<c:choose>
			<c:when test='${assetpreviewtype == "image"}'>
				<div id="info-image">
					<img alt="" src="resizer?asset=${assetpreview}&width=150&height=150"/>
				</div>
			</c:when>
			<c:when test='${assetpreviewtype == "video"}'>
				<video style="max-width:150px; max-height:150px;" src="${pageContext.request.contextPath}/play/video.${assetformat}?asset=${assetpreview}" autobuffer autoplay>
					<div class="hmtl5-fallback">
						<br/>
						You must have an HTML5 capable browser.
						<br/>
					</div>
				</video>
			</c:when>
			<c:when test='${assetpreviewtype == "sound"}'>
				<audio style="max-width:150px; max-height:150px;" src="${pageContext.request.contextPath}/play/sound.${assetformat}?asset=${assetpreview}" autoplay autobuffer>
					<div class="hmtl5-fallback">
						<br/>
						You must have an HTML5 capable browser.
						<br/>
					</div>
				</audio>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
	</c:if>
	<div id="info-detalle">
		<span style="display: block;">
			Fecha de alta: <b>${AssetInfoForm.fechaCreacion}</b>
		</span>
		<span style="display: block;">
			Tamaño: <b>${AssetInfoForm.ficSize}</b>
		</span>
		<span style="display: block;">
			Tipo de archivo: <b>${AssetInfoForm.mimeType}</b>
		</span>
	</div>
</html:form>
