<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">	
	$(document).ready(function() {
		focus("focused");
	});

	function uploadFile(form)
	{
		var carOrigen = $("form[name=ContenidosForm] :carPk")[0];
		var carDestino = $("form[name=" + form + "] :carPk")[0];
	
	 	carDestino.value = carOrigen.value;
	 	ajaxUpdateGetSyncron('procesando', '${pageContext.request.contextPath}/procesando.do');
	 	refreshUploadInfo();
	 	formDest.submit();
		return false;
	}

	function refreshUploadInfo()
	{
		ajaxUpdateGetSyncron('uploadinfo', '${pageContext.request.contextPath}/uploadinfo.do');
		setTimeout("refreshUploadInfo()",1000);
	}
</script>

<div id="procesando" ></div>
<div id="defaultpopup" class="textarea newcontent" >
<div class="notice">
	<div class="title">
	<h2>Carga fichero</h2>
	</div>
	<div class="content">
	<html:form action="/uploadFile" enctype="multipart/form-data" method="post" onsubmit="javascript:ajaxUpdateGet('procesando', 'procesando.do');">
		<html:hidden property="carPk"/>
		<table class="center">
			<tr>
				<td>Fichero:</td>
				<td><html:file property="file" size="30" /></td>
			</tr>
			<tr>
				<td>Descomprimir:</td>
				<td><html:checkbox property="descomprimir" /></td>
			</tr>
			<tr>
				<td>Descripción:</td>
				<td><html:text property="descripcion" styleId="focused" size="50" maxlength="100" /></td>
			</tr>
			<c:if test='${not empty listUsers}'>
			<tr>
				<td>Compartir con:</td>
				<td>
				<html:select property="users" multiple="true" size="10" styleClass="listusers">
					<html:options styleClass="activo" collection="listUsers" property="usuPk" labelProperty="usuUkUsuario"/>
				</html:select>
				</td>
			</tr>
			</c:if>
			<tr>
				<td>Forzar extensión:</td>
				<td>
					<c:if test='${not empty listMimeTipes}'>
					<table>
					<tr>
					<td>
						<html:select property="mimFilFk" onchange="" >
							<html:option value="0">---</html:option>
							<html:options styleClass="activo" collection="listMimeTipes" property="mimFilPk" labelProperty="mimFilExtension"/>
						</html:select>
					</td>
					<td style="font-size: 0.8em;" >
						<b> (Si lo deseas puedes forzar el tipo de archivo a cargar. <br/>
						Recuerda que de no reconocerse el tipo el fichero no se cargará)</b>
					</td>
					</tr>
					</table>
					</c:if>
					<c:if test='${empty listMimeTipes}'>
						<html:text property="mimFilPk"  readonly="true" styleClass="campoinactivo" errorStyleClass="error" size="30"/>			
					</c:if>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<br/>
					<input type="submit" class="boton" name="createfile" value="Aceptar" onclick="javascript:uploadFile('FileForm')"/>
					<input type="button" class="btncancelar" name="cancelar" value="Cancelar" onclick="javascript:clearPopup();"/>
				</td>
			</tr>
			<tr class="tags">
				<td colspan="2">	
					<br/>
				</td>
			</tr>
			<tr class="tags">
				<td style="text-align: right;">	
					<img title="Tags" src="images/tags/tag_blue.png">
				</td>
				<td>
					<html:text property="tags" size="50" maxlength="100" />
				</td>
			</tr>
		</table>
	</html:form>
	</div>
</div>
</div>
