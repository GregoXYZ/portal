<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<div class="marco" >
	<div id="popupcontenidos" >
		<div id="cmenu" class="tabs">
			<span style="margin-left: 20px; font-weight: bold;">
				<a href="javascript:void(0);" onclick="javascript:sendData('download','${assetpreview}','assPk','none','');">
					<img src="${pageContext.request.contextPath}/images/downloadfile.png"/>
					Descargar
				</a>
			</span>
			<span style="margin-left: 20px; font-weight: bold;">
				<a href="javascript:void(0);" onclick="javascript:clearPopup(0);">
					<img src="${pageContext.request.contextPath}/images/close.png"/>
					Cerrar
				</a>
			</span>
		</div>
		<div id="viewasset" class="marco" >
			<c:choose>
				<c:when test='${assetpreviewtype == "image"}'>
					<%@ include file="viewimgasset.jsp" %>
				</c:when>
				<c:when test='${assetpreviewtype == "text"}'>
					<%@ include file="viewtxtasset.jsp" %>
				</c:when>
				<c:when test='${assetpreviewtype == "video"}'>
					<%@ include file="viewvideo.jsp" %>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
