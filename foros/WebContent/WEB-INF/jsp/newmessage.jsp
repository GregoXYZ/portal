<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">	
	$(document).ready(function() {
		focus("contenido");
	});
</script>

<div id="newmessage" class="textarea newcontent">
	<div class="notice">
		<div class="title">
		<h2><c:out value="${MensajeForm.from}"/></h2>
		</div>
		<div class="content">
		<html:form action="/savemessage" method="post">
			<html:hidden property="entPk" />
			<html:hidden property="conFk" />
			
			<table class="center">
				<tr>
					<td colspan="2">
						Mensaje:
						<br/>
						<html:textarea property="contenido" styleId="contenido" cols="80" rows="10" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<br/>
						<input type="button" class="boton" name="aceptar" value="Aceptar" onclick="javascript:guardaMensaje(<c:out value="${MensajeForm.entPk}"/>, <c:out value="${MensajeForm.conFk}"/>);"/>
						<input type="button" class="btncancelar" name="cancelar" value="Cancelar" onclick="javascript:clearPopup();"/>
					</td>
				</tr>
			</table>
		</html:form>
		</div>
	</div>
</div>
