<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
 
<script type="text/javascript">
	$(document).ready( function() {		
		cargaDiv("duplicados", "${pageContext.request.contextPath}/buscarduplicados.do", null, pensandoAjax);
	});

	function buscaContenido() {
		cargaDiv("duplicados", "${pageContext.request.contextPath}/buscarduplicados.do", null, pensandoAjax);
	}
</script>

<div id="popupcontenidos" >
	<div id="cmenu" class="tabs">
		<span style="margin-left: 20px; font-weight: bold;">
			<a href="javascript:void(0);" onclick="javascript:clearPopup();">
			<img src="${pageContext.request.contextPath}/images/close.png"/>
			Cerrar
			</a>
		</span>
	</div>
	<div id="duplicados" class="marco" >
	</div>
</div>
