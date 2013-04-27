<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">	
	$(document).ready(function() {
		focus("entrada");
	});
</script>

<div id="newmessage" class="textarea newcontent">
	<div class="notice">
		<div class="title">
		<h2><c:out value="${MensajeForm.from}"/></h2>
		</div>
		<div class="content">
		<html:form action="/savelist" method="post">
			<table class="center">
				<tr>
					<td>
						Entrada:
					</td>
					<td>
						<html:text property="entrada" styleId="entrada" size="70" maxlength="100" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						Mensaje:
						<br/>
						<html:textarea property="contenido" cols="80" rows="10" />
					</td>
				</tr>
				<tr>
					<td>Restringida:</td>
					<td>
						<html:checkbox property="restringida" />
					</td>
				</tr>
				<c:if test='${not empty listUsers}'>
					<tr>
						<td>To:</td>
						<td>
						<html:select property="to" multiple="true" size="10" styleClass="listusers">
							<html:options styleClass="activo" collection="listUsers" property="usuPk" labelProperty="usuUkUsuario"/>
						</html:select>
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="2">
						<br/>
						<input type="button" class="boton" name="aceptar" value="Aceptar" onclick="javascript:guardaEntrada();"/>
						<input type="button" class="btncancelar" name="cancelar" value="Cancelar" onclick="javascript:clearPopup();"/>
					</td>
				</tr>
			</table>
		</html:form>
		</div>
	</div>
</div>
