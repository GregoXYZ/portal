<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<div id="adminavisos" class="textarea newcontent">
	<div id="avisosactuales" class="izquierda">
		<div class="avisos">
			<div style="width: 100%;">
				<h2>Avisos pendientes</h2>
			</div>
			<div id="avisospersonales" class="avisospersonales">
				<%@ include file="avisos.jsp" %>
			</div>
			<div style="width: 100%;">
				<input type="button" class="boton" name="cerrar" value="Cerrar" onclick="javascript:clearPopup();"/>
			</div>
		</div>
	</div>
	<div id="fichaaviso"  class="derecha">
	</div>
</div>
