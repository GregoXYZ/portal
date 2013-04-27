<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<html:form action="/createUser" method="post" styleClass="expose">
	<div class="textarea">
		<div class="notice">
			<div class="title">
				<h2>
					<img alt="" src="/portal/images/formdata.png">
					Registro de usuario
				</h2>
			</div>
			<div class="content">
				<table class="center">
					<tr>
						<td>Usuario:</td>
						<td><html:text property="user" styleClass="obligatorio" errorStyleClass="error" maxlength="30" /><b> *</b></td>
					</tr>
					<tr>
						<td>Contraseña:</td>
						<td><html:password property="contrasena" styleClass="obligatorio" errorStyleClass="error" maxlength="25" /><b> *</b></td>
					</tr>
					<tr>
						<td>Repita la contraseña:</td>
						<td><html:password property="contrasena2" styleClass="obligatorio" errorStyleClass="error" maxlength="25" /><b> *</b></td>
					</tr>
					<tr>
						<td>Nombre:</td>
						<td><html:text property="nombre" styleClass="obligatorio" errorStyleClass="error" maxlength="30" /><b> *</b></td>
					</tr>
					<tr>
						<td>Primer apellido:</td>
						<td><html:text property="apellido1" maxlength="30" /></td>
					</tr>
					<tr>
						<td>Segundo apellido:</td>
						<td><html:text property="apellido2" maxlength="30" /></td>
					</tr>
					<tr>
						<td>Mail:</td>
						<td><html:text property="mail" size="50" styleClass="obligatorio" errorStyleClass="error" maxlength="100" /><b> *</b></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<br/>
							<input type="submit" class="boton" name="aceptar" value="Aceptar" />
							<!-- input type="submit" class="boton" name="aceptar" value="Aceptar" onmouseover="javascript:ajax_tooltip(this, 'test.do', 'infopopup');"/ -->
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</html:form>
