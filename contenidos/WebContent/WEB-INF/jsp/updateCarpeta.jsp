<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">	
	$(document).ready(function() {
		focus("focused");
	});
</script>

<div id="procesando" ></div>
<div id="defaultpopup" class="textarea  newcontent" >
	<div class="notice">
		<div class="title">
		<h2>Actualiza carpeta</h2>
		</div>
		<div class="content">
		<html:form action="/updatefolder" method="post" >
			<html:hidden property="assPk" />
			<table class="center">
				<tr>
					<td>Carpeta:</td>
					<td><html:text property="nombre" styleId="focused" size="50" maxlength="250" /></td>
				</tr>
				<tr>
					<td>Descripción:</td>
					<td><html:text property="descripcion" size="50" maxlength="500" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<br/>
						<input type="button" class="boton" name="update" value="Actualizar" onclick="javascript:updateFolder();"/>
						<input type="button" class="btncancelar" name="cancelar" value="Cancelar" onclick="javascript:clearPopup();"/>
					</td>
				</tr>
			</table>
		</html:form>
		</div>
	</div>
	<form name="cancelar" action="${pageContext.request.contextPath}/home.do " method="post"></form>
</div>
