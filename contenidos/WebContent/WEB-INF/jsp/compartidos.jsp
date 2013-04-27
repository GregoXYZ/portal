<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>

<script type="text/javascript">	
	function cargaUser(user)
	{
		var usuPk = $("form[name=CompartidosForm]").find("input[name=usuPk]")[0];
		usuPk.value = user;
		cargaDiv("compartirdata", "${pageContext.request.contextPath}/opensharedfiles.do", odenaFicheros, pensandoAjax, null, "usuPk=" + user);
		return false;
	}

	function recarga(carpeta)
	{
		var usuPk = $("form[name=CompartidosForm]").find("input[name=usuPk]")[0];
		cargaDiv("compartirdata", "${pageContext.request.contextPath}/opensharedfiles.do", odenaFicheros, pensandoAjax, null, "usuPk=" + usuPk.value + "&carFk=" + carpeta);
		return false;
	}

	function compartirfolder()
	{
		cargaDiv("compartirdata", "${pageContext.request.contextPath}/opensharedfiles.do", odenaFicheros, pensandoAjax, null, "usuPk=" + user);
		return false;
	}

	function odenaFicheros()
	{
		tableorder(".orderfiles");
	}


	function deleteShared(asset, user, mensaje)
	{
		if (confirm(mensaje))
		{
			cargaDiv(null, "${pageContext.request.contextPath}/borracompartido.do", null, null, null, "assPk=" + asset + "&usuPk=" + user);
			$("#"+asset).fadeOut(2000);
		}
	}

	function updateFile()
	{
		//'¿Deseas continuar?'
		var qs = $("form[name=UpdateFileForm]").serialize();
		cargaDiv(null, "${pageContext.request.contextPath}/updatesharedfile.do", callbackUpdateFile, null, null, qs);
	 	//formDest.submit();
		return false;
	}

	function callbackUpdateFile()
	{
		var carFk = $("form[name=CompartidosForm]").find("select[name=carFk]")[0];
		recarga(carFk.value);
		clearPopup(0);
	}

	function delMyShared(user, asset)
	{
		if (confirm("Va a anular la compartición.\n¿Desea continuar?"))
		{
			var myform = document.forms["borracompartido"];
			myform.usuPk.value = user;
			myform.assPk.value = asset;
			myform.submit();
			return false;		
		}
	}
</script>

<div id="directorio" class="marco" >
	<div id="compartidos">
		<c:if test='${empty listUsers}'>
			<div class="webinfo">
				<div class="webmessage">
					No hay datos
				</div>
			</div>
		</c:if>
		<c:if test='${not empty listUsers}'>
			<div id="compartirusers" class="center">
				<cnt:miniatura elements="listUsers" id="shareusers" type="mural"/>
			</div>
		</c:if>
		<div id="compartirdata">
			<%@ include file="ficheroscompartidos.jsp" %>
		</div>
	</div>
	
	<form name="download" action="${pageContext.request.contextPath}/opensharedfile.do" method="post" >
		<input type="hidden" name="assPk"/>
	</form>	
</div>
