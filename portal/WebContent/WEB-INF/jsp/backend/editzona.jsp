<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>

<div id="defaultpopup" class="textarea newcontent">
	<div class="notice">
		<div class="title">
		<h2>Edición de zonas</h2>
		</div>
		<div class="content">
		<html:form action="/savezona" method="post">
			<html:hidden property="zonPk"/>
			<table class="center">
				<tr>
					<td>Código:</td>
					<td><html:text property="zonCodigo" /><b> *</b></td>
				</tr>
				<tr>
					<td>Descripción:</td>
					<td><html:text property="zonDesc" /><b> *</b></td>
				</tr>
				<tr>
					<td colspan="2">
						<br/>
						<input type="submit" class="boton" name="aceptar"	value="Aceptar" onclick="return submit()"/>
						<input type="button" class="btncancelar" name="cancelar"	value="Cancelar" onclick="javascript:clearPopup(0);"/>
					</td>
				</tr>
			</table>
		</html:form>
		</div>
	</div>
</div>
