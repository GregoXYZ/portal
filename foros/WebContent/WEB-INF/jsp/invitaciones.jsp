<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<div id="invitaciones" class="textarea newcontent">
	<div class="notice">
		<div class="title">
		<h2><c:out value="${MensajeForm.entrada}"/></h2>
		</div>
		<div class="content">
		<html:form action="/saveinvitaciones" method="post">
			<table class="center" style="width: 100%;">
				<c:if test='${not empty listUsers}'>
					<tr>
						<td>Invitar a:</td>
						<td>
						<html:select property="to" multiple="true" size="10" styleClass="listusers">
							<html:options styleClass="activo" collection="listUsers" property="usuPk" labelProperty="usuUkUsuario"/>
						</html:select>
						</td>
					</tr>
				</c:if>
				<c:if test='${empty listUsers && empty restringida}'>
				<tr>
					<td>
						<div class="webinfo" style="max-width: 100%;">
							<div class="webmessage">
								<c:if test='${MensajeForm.entPk == 0}'>
									<p>No has seleccionado ningún mensaje.</p>
								</c:if>
								<c:if test='${MensajeForm.entPk > 0}'>
									<p>No hay usuarios disponibles.</p>
									<br/>
									<p>Todos tus amigos ya están participando en esta entrada.</p>
								</c:if>
							</div>
						</div>
					</td>
				</tr>
				</c:if>
				<c:if test='${not empty restringida}'>
				<tr>
					<td>
						<div class="webinfo" style="max-width: 100%;">
							<div class="webwarning">
								<p>Esta entrada no admite invitaciones.</p>
								<br/>
								<p>Al crearla fue declarada como restringida.</p>
							</div>
						</div>
					</td>
				</tr>
				</c:if>
				<tr>
					<td colspan="2">
						<br/>
						<c:if test='${not empty listUsers}'>
						<input type="button" class="boton" name="aceptar" value="Aceptar" onclick="javascript:guardaInvitacion();"/>
						</c:if>
						<input type="button" class="btncancelar" name="cancelar" value="Cancelar" onclick="javascript:clearPopup();"/>
					</td>
				</tr>
			</table>
		</html:form>
		</div>
	</div>
</div>
