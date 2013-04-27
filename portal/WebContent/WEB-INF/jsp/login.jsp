<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>

<script type="text/javascript">	
	$(document).ready(function() {
		focus("user");
	});
</script>

<html:form action="/initSession" method="post" styleClass="expose">
	<div id="loginform" class="textarea">
		<div class="notice">
			<div class="title">
				<h2>
					<img alt="" src="/portal/images/login.png">
					Identificación de usuario
				</h2>
			</div>
			<div class="content">
				<html:hidden property="redirectContext" />
				<table class="center">
					<tr>
						<td>Usuario:</td>
						<td><html:text property="user" styleId="user" maxlength="30" /></td>
					</tr>
					<tr>
						<td>Contraseña:</td>
						<td><html:password property="password" maxlength="25" /></td>
					</tr>
					<tr>
						<td colspan="2">
							<div style="font-size: 0.8em; text-align: right;">
								<b>Si has olvidado tu contraseña pulsa 
								<a href="javascript:void(0);" onclick="javascript:ajaxPopup('newpass.do');">
									aquí
								</a>.
								</b>
							</div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="2">
							<br/>
							<input type="submit" class="boton" name="aceptar" value="Aceptar" onclick="return submit()"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%--
		<div>
			<div class="webinfo" style="font-size: 0.75em; max-width: 100%;">
			    - Dos horas de banda ancha por FTP para descargar el paquete de Linux, $0.15 centavos.
			    <br/> 
			    - CDs para grabar los archivos, $1.00. 
			    <br/> 
			    - Saber que nada en tu computadora es de Microsoft, NO TIENE PRECIO.
			    <br/> 
			    <br/> 			
			    Hay sistemas operativos que uno no puede comprar con dinero.
			    <br/> 
			    <br/> 			
			    Para todos los demás, está Windows.
			</div>
		</div>
		 --%>
	</div>
</html:form>
<div id="popup_password" style="z-index: 115;" ></div>
