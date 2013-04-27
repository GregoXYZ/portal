<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">	
	$(document).ready(function() {
		focus("pass");
	});
</script>

<div id="defaultpopup" class="textarea newcontent">
	<div class="notice">
		<div class="title">
		<h2>Modicficaci�n de la contrase�a</h2>
		</div>
		<div class="content">
		<html:form action="/savepass" method="post">
			<table class="center">
				<tr>
					<td>Contrase�a actual:</td>
					<td>
					<html:password property="oldPassword" styleId="pass" maxlength="25" />
					<b>*</b>
					</td>
				</tr>
				<tr>
					<td>Contrase�a nueva:</td>
					<td>
					<html:password property="newPassword" maxlength="25" />
					<b>*</b>
					</td>
				</tr>
				<tr>
					<td>Repita la contrase�a:</td>
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
