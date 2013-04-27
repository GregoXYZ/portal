<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">	
	$(document).ready(function() {
		focus("focused");
	});
</script>

<div id="procesando" ></div>
<div id="defaultpopup" class="textarea newcontent" >
<div class="notice">
	<div class="title">
	<h2>Nueva carpeta</h2>
	</div>
	<div class="content">
	<html:form action="/createfolder" method="post" >
		<html:hidden property="carPk" />
		<table class="center">
			<tr>
				<td>Nombre:</td>
				<td><html:text property="nombre" styleId="focused" size="50" maxlength="50" /></td>
			</tr>
			<tr>
				<td>Descripción:</td>
				<td><html:text property="descripcion" size="70" maxlength="100" /></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<br/>
					<input type="button" class="boton" name="createfolder" value="Aceptar" onclick="javascript:createFolder()"/>
					<input type="button" class="btncancelar" name="cancelar" value="Cancelar" onclick="javascript:clearPopup();"/>
				</td>
			</tr>
		</table>
	</html:form>
	</div>
</div>
</div>
