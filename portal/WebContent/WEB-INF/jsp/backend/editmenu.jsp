<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>

<div id="defaultpopup" class="textarea newcontent">
	<div class="notice">
		<div class="title">
		<h2>Edición de menús</h2>
		</div>
		<div class="content">
		<html:form action="/savemenu" enctype="multipart/form-data" method="post">
			<html:hidden property="menPk"/>
			<table class="center">
				<tr>
					<td>Código:</td>
					<td><html:text property="menCodigo" maxlength="10" size="10"/><b> *</b></td>
				</tr>
				<tr>
					<td>Descripción:</td>
					<td><html:text property="menDescripcion" maxlength="200"/><b> *</b></td>
				</tr>
				<tr>
					<td>Título:</td>
					<td><html:text property="menTitulo" maxlength="200"/><b> *</b></td>
				</tr>
				<tr>
					<td>Orden:</td>
					<td><html:text property="menOrden" /><b> *</b></td>
				</tr>
				<tr>
					<td>Icono:</td>
					<td><html:file property="menIcon" /></td>
				</tr>
				<tr>
					<td>Parent:</td>
					<td>
						<c:if test='${not empty listMenus}'>
							<html:select property="menFk" onchange="">
								<html:option value="0">---</html:option>
								<html:options styleClass="activo" collection="listMenus" property="menPk" labelProperty="menDescripcion"/>
							</html:select>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>Url:</td>
					<td>
						<c:if test='${not empty listUrls}'>
							<html:select property="urlFk" onchange="">
								<html:option value="0">---</html:option>
								<html:options styleClass="activo" collection="listUrls" property="urlPk" labelProperty="urlDescripcion"/>
							</html:select>
						</c:if>
					</td>
				</tr>
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
					<td>Popup:</td>
					<td><html:checkbox property="menPopup" /></td>
				</tr>
				<tr>
					<td>Restringido a la zona:</td>
					<td><html:checkbox property="menRestringido" /></td>
				</tr>
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
