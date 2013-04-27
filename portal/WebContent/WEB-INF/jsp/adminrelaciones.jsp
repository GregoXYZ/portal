<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">	
	$(document).ready(function() {
		focus("campo1");
	});

	//
	// Usuarios realcionados
	//
	function updateRelacion(user, evento) {
		cargaDiv('mis-relaciones', '/portal/tratarelacion.do', null, null, null, 'usuPk=' + user + '&evento=' + evento);
		updateBuscador();
	}

	function updateBuscador() {
		var campo1 = document.getElementById("campo1");
		cargaDiv('buscarelaciones', '/portal/buscarelaciones.do', null, null, null, 'campo1=' + campo1.value);
	}
</script>

<div id="adminrelaciones" class="textarea newcontent">
	<div id="relacionesactuales" class="izquierda">
		<div class="relaciones">
			<div style="width: 100%;">
				<h2>Amigos</h2>
			</div>
			<div id="mis-relaciones" class="relacionespersonales">
				<%@ include file="relacionespersonales.jsp" %>
			</div>
			<div style="width: 100%;">
				<input type="button" class="boton" name="cerrar" value="Cerrar" onclick="javascript:clearPopup();"/>
			</div>
		</div>
	</div>
	<div id="buscarelacion"  class="derecha">
		<div style="text-align: center;">
			<input type="text" id="campo1" name="campo1" maxlength="50"/>
			<a class='boton' href='javascript:void(0);' style="padding-right: 5px;" onclick='javascript:updateBuscador()'>
				<img src='/portal/images/menus/search-tool.png'> Buscador
			</a>
		</div>
		<div id="buscarelaciones">
		<!-- %@ include file="buscarelaciones.jsp" % -->
		</div>
	</div>
</div>
