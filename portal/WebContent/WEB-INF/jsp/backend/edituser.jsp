<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>

<%--
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/datePicker.css">
<script src="${pageContext.request.contextPath}/common/js/jquery/addons/jquery.datePicker.min-2.1.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/addons/date.js" type="text/javascript"></script>
 --%>
 
<script type="text/javascript">
	$(document).ready( function() {
		$("#fechaBaja").datepicker({
			//changeMonth: true,
			//changeYear: true,
			showButtonPanel: true,
			numberOfMonths: 3
			//showOn: 'button', buttonImage: '../images/calendar.png'
		});
	});
	
	function borraFecha() {
		var element=$("#fechaBaja");
		element.value = "";
		element.defaultValue="";
	}
</script>

<div id="defaultpopup" class="textarea newcontent">
	<div class="notice">
		<div class="title">
			<h2>Registro de usuario</h2>
		</div>
		<div class="content">
		<html:form action="/saveuser" method="post">
			<html:hidden property="usuPk"/>
			<table class="center">
				<tr>
					<td>Usuario:</td>
					<td><html:text property="user" /><b> *</b></td>
				</tr>
				<tr>
					<td>Contraseña:</td>
					<td><html:password property="password" /><b> *</b></td>
				</tr>
				<tr>
					<td>Nombre:</td>
					<td><html:text property="nombre" /><b> *</b></td>
				</tr>
				<tr>
					<td>Primer apellido:</td>
					<td><html:text property="apellido1" /></td>
				</tr>
				<tr>
					<td>Segundo apellido:</td>
					<td><html:text property="apellido2" /></td>
				</tr>
				<tr>
					<td>Mail:</td>
					<td><html:text property="mail" size="50"/><b> *</b></td>
				</tr>
				<tr>
					<td>Fecha de baja:</td>
					<td>
					<html:text property="fechaBaja" styleId="fechaBaja" styleClass="date-pick dp-applied" style="float:left;" size="8" maxlength="10" />
					<a onclick='javascript:borraFecha()' href="javascript:void(0);">
						<img src="/portal/images/edit-clear.png"/>
					</a>
					</td>
				</tr>
				<tr>
					<td>Activo:</td>
					<td><html:checkbox property="activo" /></td>
				</tr>
				<tr>
					<td>Publicable:</td>
					<td><html:checkbox property="publicable" /></td>
				</tr>
				<c:if test='${not empty listGrupos}'>
				<tr>
					<td>Perfil:</td>
					<td>
						<html:select property="perfil" multiple="true" >
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
						<input type="button" class="btncancelar" name="cancelar" value="Cancelar" onclick="javascript:clearPopup(0);"/>
					</td>
				</tr>
			</table>
		</html:form>
		</div>
	</div>
</div>
