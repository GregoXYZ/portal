<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<div class="values">
	<div id="messageactions" class="actionsdropdown">
	</div>
	<table id="headers">
		<tbody>
			<c:if test='${not empty pathabatar}'>
			<tr>
				<td class="avatar" rowspan="5">
					<img src="${pathabatar}"/>
				</td>
			</tr>
			</c:if>
			<tr>
				<th>Asunto:</th>
				<td>
				<c:out value="${MensajeForm.entrada}"/>
				</td>
				<td class="actions">
				<c:if test="${not empty MensajeForm.conPk}">
				<span class="actions mklink" onclick="javascript:nuevoMensaje(<c:out value="${MensajeForm.entPk}"/>, <c:out value="${MensajeForm.conPk}"/>);">Responder</span>
				</c:if>
				</td>
			</tr>
			<tr>
				<th>De:</th>
				<td colspan="2">
				<c:out value="${MensajeForm.from}"/>
				</td>
			</tr>
			<tr>
				<th>Cuando:</th>
				<td colspan="2">
				<c:out value="${MensajeForm.fechaAlta}"/>
				</td>
			</tr>
		</tbody>
	</table>
	<div id="body" class="messagebody">
		<div class="pws"><c:out value="${MensajeForm.contenido}"/></div>
	</div>
</div>
