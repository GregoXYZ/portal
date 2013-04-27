<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">	
	$(document).ready(function() {
		focus("sites");
	});
</script>

<div id="reportbug" class="textarea newcontent">
	<div class="notice">
		<div class="title">
		<h2>Gracias por ayudarnos a mejorar</h2>
		</div>
		<div class="content">
		<html:form action="/saveBug" method="post">
			<table class="center">
				<c:if test='${not empty listZonas}'>
				<tr>
					<td>Site:</td>
					<td>
						<html:select property="bugSitePk" styleId="sites" >
							<html:option value="0">---</html:option>
							<html:options styleClass="activo" collection="listZonas" property="zonPk" labelProperty="zonDesc"/>
						</html:select>
						<b> *</b>
					</td>
				</tr>
				</c:if>
				<tr>
					<td colspan="2">
					Mensaje de error:
					<br/>
					<html:textarea property="bugMessage" cols="100" rows="8" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
					Descripción del error:
					<br/>
					<html:textarea property="bugDescripcion" cols="100" rows="8" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<br/>
						<input type="button" class="boton" name="aceptar" value="Aceptar" onclick="javascript:saveBug();"/>
						<input type="button" class="btncancelar" name="cancelar" value="Cancelar" onclick="javascript:clearPopup();"/>
					</td>
				</tr>
			</table>
		</html:form>
		</div>
	</div>
</div>

<form name="cancelar" action="${pageContext.request.contextPath}/home.do " method="post">
	<input type="hidden" name="id"/>
</form>
