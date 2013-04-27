<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">	
	$(document).ready(function() {
		focus("name");
	});
</script>

<div id="userdata" class="textarea newcontent">
	<div class="notice">
		<div class="title">
			<h2>Registro de usuario</h2>
		</div>
		<div class="content">
		<html:form action="/updateUser" enctype="multipart/form-data" method="post">
			<table class="center">
				<tr>
					<td style="padding-right: 15px;"><frm:avatar/></td>
					<td>
						<table>
							<tr>
								<td>Foto de perfil:</td>
								<td>
								<html:file property="avatar" size="30" />
								</td>
							</tr>
							<tr>
								<td>Nombre:</td>
								<td><html:text property="nombre" styleId="name" maxlength="30"/><strong> *</strong></td>
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
								<td><html:text property="mail" size="50" maxlength="100"/><b> *</b></td>
							</tr>
							<tr>
								<td>Contraseña:</td>
								<td>
								<input type="button" class="boton" name="changepassword" value="Modifica tu contraseña" onclick="javascript:ajaxPopup('/portal/changePassword.do');"/>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									Permitir que otras personas se relacionen conmigo:
									<html:checkbox property="publicable" />
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									Recibir avisos del sistema via mail:
									<html:checkbox property="recibeAbisos" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<td style="text-align: center;">
						<br/>
						<input type="submit" class="boton" name="aceptar" value="Aceptar" />
						<input type="button" class="btncancelar" name="cancelar" value="Cancelar" onclick="javascript:clearPopup();"/>
					</td>
				</tr>
			</table>
		</html:form>
		</div>
	</div>
</div>
