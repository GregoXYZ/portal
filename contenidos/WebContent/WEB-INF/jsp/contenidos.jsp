<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>

<script type="text/javascript">
	$(document).ready( function() {
		$("#userinfo").mouseover(function() {
			ajax_tooltip("#userinfo", "${pageContext.request.contextPath}/userinfo.do", "userinfo");			
		});
		
		cargaDiv('operaciones', '${pageContext.request.contextPath}/assetoper.do');
		cargaDiv('path', '${pageContext.request.contextPath}/ruta.do');
		
		cargaDiv('carpetas', '${pageContext.request.contextPath}/openfolder.do', null, pensandoAjax);
		cargaDiv('ficheros', '${pageContext.request.contextPath}/openfiles.do', null, pensandoAjax);
	});

	function cargaAjax(carpeta)
	{
		var carPk = $("form[name=ContenidosForm]").find("input[name=carPk]")[0];
		carPk.value = carpeta;
		//cargaDiv('userinfo', '${pageContext.request.contextPath}/userinfo.do');
		cargaDiv('path', '${pageContext.request.contextPath}/ruta.do', null, null, null, 'carPk=' + carpeta);
		
		cargaDiv('carpetas', '${pageContext.request.contextPath}/openfolder.do', null, pensandoAjax, null, 'carPk=' + carpeta);
		cargaDiv('ficheros', '${pageContext.request.contextPath}/openfiles.do', refrescaOperaciones, pensandoAjax, null, 'carPk=' + carpeta);		
	}

	function refrescaCarpetas(thinking)
	{
		var carPk = $("form[name=ContenidosForm]").find("input[name=carPk]")[0];
		cargaDiv('carpetas', '${pageContext.request.contextPath}/openfolder.do', refrescaOperaciones, thinking, null, 'carPk=' + carPk.value);
	}

	function moverAsset(asset, divId)
	{
		cargaDiv(divId, '${pageContext.request.contextPath}/moveoper.do', null, null, null, 'assPk=' + asset);
	}

	function refrescaFicheros(thinking)
	{
		var carPk = $("form[name=ContenidosForm]").find("input[name=carPk]")[0];
		cargaDiv('ficheros', '${pageContext.request.contextPath}/openfiles.do', refrescaOperaciones, thinking, null, 'carPk=' + carPk.value);
	}

	function refrescaLinks()
	{
		var carPk = $("form[name=ContenidosForm]").find("input[name=carPk]")[0];
		cargaDiv('ficheros', '${pageContext.request.contextPath}/openlinks.do', refrescaOperaciones, null, null, 'carPk=' + carPk.value);
	}

	function createFolder()
	{
		var carOrigen = $("form[name=ContenidosForm]").find("input[name=carPk]")[0];
		var carDestino = $("form[name=FolderForm]").find("input[name=carPk]")[0];	 	
	 	carDestino.value = carOrigen.value;
	 	
	 	ajaxUpdateGetSyncron('procesando', 'procesando.do');

		var qs = $("form[name=FolderForm]").serialize();
		cargaDiv(null, '${pageContext.request.contextPath}/createfolder.do', callbackCreateFolder, null, null, qs);
		return false;
	}

	function refrescaOperaciones()
	{
		cargaDiv('operaciones', '${pageContext.request.contextPath}/assetoper.do');
	}
	
	function updateFolder()
	{
	 	ajaxUpdateGetSyncron('procesando', 'procesando.do');

		var qs = $("form[name=FolderForm]").serialize();
		cargaDiv(null, '${pageContext.request.contextPath}/updatefolder.do', callbackCreateFolder, null, null, qs);
		return false;
	}

	function callbackCreateFolder()
	{
		refrescaCarpetas();
		clearPopup();
	}

	function updateFile()
	{
		//'¿Deseas continuar?'
		var qs = $("form[name=UpdateFileForm]").serialize();
		cargaDiv(null, '${pageContext.request.contextPath}/updatefile.do', callbackUpdateFile, null, null, qs);
		return false;
	}

	function callbackUpdateFile()
	{
		refrescaFicheros();
		clearPopup(0);
	}

	function comparteFiles()
	{
		var carPk = $("form[name=ContenidosForm]").find("input[name=carPk]")[0];
	 	ajaxPopup('${pageContext.request.contextPath}/selectionfiles.do', null, 'carPk=' + carPk.value);
	 	//ajaxPopup('/portal/underconstruction.do', null);
		return false;
	}

	function shareListElements()
	{
		selectListElements('files');
		var qs = $("form[name=SelectionFilesForm]").serialize();
		cargaDiv(null, '${pageContext.request.contextPath}/sharefiles.do', clearPopup, null, null, qs);
	}
</script>

<div id="directorio" class="marco">
	<html:form action="/home" method="post" >
		<html:hidden property="carPk" />
		<div id="contenido">	
			<div class="topmenu">
				<span id="path" ></span>
				<span id="userinfo">
					<img alt="" src="images/info.png"/>
				</span>
			</div>
			<div id="operaciones" class="center"></div>
			<div id="dirdata">
				<div id="carpetas" class="marco" >
				</div>
				<div id="ficheros" class="marco">
				</div>
			</div>
		</div>
	</html:form>

	<form name="download" action="${pageContext.request.contextPath}/openfile.do " method="post" >
		<input type="hidden" name="assPk"/>
	</form>
</div>
