<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>

<script src="${pageContext.request.contextPath}/js/enviroment.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready( function() {
		$("#useragent").html(getUserAgent());
	});
</script>

<div id="sessioninfo" class="textarea newcontent">
	<div class="notice">
		<div class="title">
			<h2>Información</h2>
		</div>
		<div class="content">
			<p><span id="useragent"></span></p>
			<table>
				<tr>
					<td>Host name:</td>
					<td></td>
				</tr>
				<tr>
					<td>IP remota:</td>
					<td><%=request.getRemoteAddr()%></td>
				</tr>
				<tr>
					<td>Host remoto:</td>
					<td><%=request.getRemoteHost()%></td>
				</tr>
				<tr>
					<td>Puerto remoto:</td>
					<td><%=request.getRemotePort()%></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="boton" name="login" value="Volver" onclick="javascript:clearPopup();" /> 
						<input type="button" class="boton" name="source" value="view source"
							onclick="document.location = 'view-source:' + document.location" /> 
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
