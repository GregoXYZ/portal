<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>

<div id="reportbug" class="textarea newcontent">
	<div class="notice">
		<div class="title">
		<h2>Edición de bugs</h2>
		</div>
		<div class="content">
		<html:form action="/savebug" method="post">
			<html:hidden property="bugPk"/>
			<html:hidden property="usuFk"/>
			<table class="center">
				<tr>
					<td>Informado por:</td>
					<td><html:text property="usuName" disabled="true"/></td>
				</tr>
				<tr>
					<td>Mensaje:</td>
					<td><html:textarea property="bugMessage" cols="100" rows="8" /></td>
				</tr>
				<tr>
					<td>Descripción:</td>
					<td><html:textarea property="bugDescripcion" cols="100" rows="8" /></td>
				</tr>
				<tr>
					<td>Fecha de alta:</td>
					<td><html:text property="bugFechaReport"/></td>
				</tr>
				<c:if test='${not empty listZonas}'>
				<tr>
					<td>Zona:</td>
					<td>
						<html:select property="bugSitePk" onchange="">
							<html:options styleClass="activo" collection="listZonas" property="zonPk" labelProperty="zonDesc"/>
						</html:select>
					</td>
				</tr>
				</c:if>
				<tr>
					<td>Estado:</td>
					<td>
						<html:select property="bugEstado">
							<html:option value="4">Desestimado</html:option>
							<html:option value="3">Informado</html:option>
							<html:option value="2">En curso</html:option>
							<html:option value="1">Solucionado</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>Prioridad:</td>
					<td>
						<html:select property="bugPrioridad">
							<html:option value="1">Crítico</html:option>
							<html:option value="2">Alto</html:option>
							<html:option value="3">Medio</html:option>
							<html:option value="4">Bajo</html:option>
							<html:option value="5">Sin clasificar</html:option>
							<html:option value="6">Sugerencia</html:option>
						</html:select>
					</td>
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
