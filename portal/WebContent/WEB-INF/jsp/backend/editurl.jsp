<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>

<div id="defaultpopup" class="textarea newcontent">
	<div class="notice">
		<div class="title">
		<h2>Edición de urls</h2>
		</div>
		<div class="content">
		<html:form action="/saveurl" method="post">
			<html:hidden property="urlPk"/>
			<table class="center">
				<tr>
					<td>Código:</td>
					<td><html:text property="urlCodigo" /><b> *</b></td>
				</tr>
				<tr>
					<td>Descripción:</td>
					<td><html:text property="urlDescripcion" /><b> *</b></td>
				</tr>
				<tr>
					<td>Link:</td>
					<td><html:text property="urlDireccion" /><b> *</b></td>
				</tr>
				<c:if test='${not empty listZonas}'>
				<tr>
					<td>Zona:</td>
					<td>
							<html:select property="zonFk" onchange="">
								<html:option value="0">---</html:option>
								<html:options styleClass="activo" collection="listZonas" property="zonPk" labelProperty="zonDesc"/>
							</html:select>
							<b> *</b>
					</td>
				</tr>
				</c:if>
				<c:if test='${not empty listGrupos}'>
				<tr>
					<td>Grupos:</td>
					<td>
						<html:select property="grupos" multiple="true" >
							<html:option value="0">---</html:option>
							<html:options styleClass="activo" collection="listGrupos" property="gruPk" labelProperty="gruDescripcion"/>
						</html:select>
					</td>
				</tr>
				</c:if>
				<tr>
					<td></td>
					<td>
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

<form name="cancelar" action="${pageContext.request.contextPath}/backend/urllist.do " method="post">
</form>
