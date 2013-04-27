<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">	
	$(document).ready(function() {
		focus("pass");
	});
</script>

<div id="defaultpopup" class="textarea newcontent">
	<div class="notice">
		<div class="title">
		<h2>Modicficación de la contraseña</h2>
		</div>
		<div class="content">
		<html:form action="/savepass" method="post">
			<table class="center">
				<tr>
					<td>Contraseña actual:</td>
					<td>
					<html:password property="oldPassword" styleId="pass" maxlength="25" />
					<b>*</b>
					</td>
				</tr>
				<tr>
					<td>Contraseña nueva:</td>
					<td>
					<html:password property="newPassword" maxlength="25" />
					<b>*</b>
					</td>
				</tr>
				<tr>
					<td>Repita la contraseña:</td>
					<td>
					<html:password property="repeatPassword" maxlength="25" />
					<b>*</b>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<br/>
						<input type="submit" class="boton" name="solicitar"	value="Aceptar"/>
						<input type="button" class="btncancelar" name="cancelar" value="Cancelar" onclick="javascript:clearPopup(0);"/>
					</td>
				</tr>
			</table>
		</html:form>
		</div>
	</div>
</div>
