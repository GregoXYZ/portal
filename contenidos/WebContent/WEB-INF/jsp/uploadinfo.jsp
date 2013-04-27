<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<c:if test='${not empty uploadInfo}'>
	<div class="marco">
		<div>
			Total a subir: ${uploadInfo.totalSize} - Datos subidos: ${uploadInfo.bytesRead}
		</div>
		<div style="width: 100%; height: 15px; background-color: #fff;">	
			<div style="height: 100%; width:${(uploadInfo.bytesRead / uploadInfo.totalSize) * 100}%; background-color: #FF1400;">	
			</div>
		</div>
		<div>
			${uploadInfo.status}  
		</div>
	</div>
</c:if>