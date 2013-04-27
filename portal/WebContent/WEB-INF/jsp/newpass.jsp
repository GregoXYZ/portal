<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">	
	$(document).ready(function() {
		focus("userPass");
	});
</script>

<div id="defaultpopup" class="textarea newcontent">
	<div class="notice">
		<div class="title">
		<h2>Solicitud de nueva contraeña</h2>
		</div>
		<div class="content">
		<html:form action="/sendpass" method="post">
			<div style="width: 100% ;text-align: center;">
				<h3>Introduzca su código de usuario y el mail y recibirá una nueva contraseña.</h3>
			</div>
			<table class="center">
				<tr>
					<td>Usuario:</td>
					<td><html:text property="user" styleId="userPass" maxlength="30" /></td>
				</tr>
				<tr>
					<td>Mail:</td>
					<td><html:text property="mail" size="50" maxlength="100" /></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<br/>
						<!-- input type="submit" class="boton" name="solicitar"	value="Aceptar" onmouseover="javascript:ajax_tooltip(this, 'test.do', 'infopopup');"/ -->
						<input type="submit" class="boton" name="solicitar"	value="Aceptar" onmouseover="javascript:ajax_tooltip(this, 'test.do', 'infopopup');"/>
						<input type="button" class="btncancelar" name="cancelar" value="Cancelar" onclick="javascript:clearPopup(0);"/>
					</td>
				</tr>
			</table>
		</html:form>
		</div>
	</div>
</div>
