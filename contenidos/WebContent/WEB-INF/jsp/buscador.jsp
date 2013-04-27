<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
 
<script type="text/javascript">
	$(document).ready( function() {		
		cargaDiv('nube', '${pageContext.request.contextPath}/nube.do', initDrag, pensandoAjax, null, "tipo=3");

		focus("campo1");
		
		BuscadorCanvasSize();
		$(window).resize(function () {
			BuscadorCanvasSize();
		});

		initDrop();
	});

	function BuscadorCanvasSize()
	{
		$("#spacial").css({	height: $("#popupdata").height() - 90 });	
		$("#buscador").css({height: $("#popupdata").height() - 95});	

		var position = $("#buscador #resultado").position();
		var positionBuscador = $("#buscador").position();
		$("#buscador #resultado").css({	height: $("#buscador").height() - position.top + positionBuscador.top});	
	}

	function buscaContenido() {
		var qs = $("form[name=BuscarForm]").serialize();
		cargaDiv("resultado", "${pageContext.request.contextPath}/buscarcontenido.do", ordenaNTag, pensandoAjax, null, "multipletags=" + getMultiTags() + "&" + qs);
	}

	function cargaNube(tipo)
	{
		cargaDiv("nube", "${pageContext.request.contextPath}/nube.do", null, null, null, "tipo=" + tipo);
	}

	function ordenaNTag() { 
		tableorder(".ordersearch");
	} 

	function initDrop() {
		$(".drop.multitags").droppable({
			accept: '.tagnube.drag',
			activeClass: 'active',
			drop: function(ev, ui) {
				var existe = $("#" + (ui.draggable).children("a").attr("id") + ".multitag");
				
				if (existe.length == 0)
	    		{
		    		var $proxy = (ui.draggable).clone();

		    		$proxy.children("a").addClass("multitag setdrag");
		    		$( "#dropcontent" ).append($proxy.html());
		    		
					initDragMultiTags();
		    		$(".setdrag").removeClass("setdrag");
	    		}
			}
		});

		$(".drop.papelera").droppable({
			accept: '.multitag',
			activeClass: 'active',
			drop: function(ev, ui) {
				$("#" + (ui.draggable).attr("id") + ".multitag").remove();
			}
		});
	}

	function initDrag() {
		$(".tagnube").draggable({
			helper: 'clone'
		});			
	}

	function initDragMultiTags() {
		$(".setdrag").draggable({
			helper: 'clone'
		});
	}

	function getMultiTags()
	{
		var tags = "";
		
		$("a.multitag").each(function(i){
			tags = tags + " " + $(this).attr("id");
		});
		
		return tags;
	}
</script>

<div id="popupcontenidos" >
	<div id="cmenu" class="tabs">
		<span style="margin-left: 20px; font-weight: bold;">
			<a href="javascript:void(0);" onclick="javascript:clearPopup();">
			<img src="${pageContext.request.contextPath}/images/close.png"/>
			Cerrar
			</a>
		</span>
	</div>
	<div id="buscar" class="marco" >
		<div id="buscador">
			<html:form action="/buscarcontenido" method="post" >
				<div>
					<input type="text" id="campo1" name="campo1" maxlength="300"/>
					<a class='boton' href='javascript:void(0);' style="padding-right: 5px;" onclick='javascript:buscaContenido()'>
						<img src='/portal/images/menus/search-tool.png'> Buscador
					</a>
				</div>
			</html:form>
			<div id="grouptags">
				<table style="width: 100%" >
					<tr>
						<td style="width: 0;" >
						<img title="Tags" src="images/tags/tag_blue.png">
						</td>
						<td class="drop multitags">
							<div id="dropcontent">
							</div>
						</td>
						<td style="width: 0;" >
						<%--
						<a class="boton" href="javascript:void(0);" style="padding: 0 5px; display: block;" onclick="javascript:buscaContenido()">
							<img src='/portal/images/menus/search-tool.png'> Buscador
						</a>
						 --%>
						<div class="drop papelera" >
							<img src="images/papelera48.png" />
						</div>
						</td>
					</tr>
				</table>
			</div>
			<div id="resultado">
			</div>
		</div>			
		<div id="spacial">
			<div class="menutitle">
				<h3>Etiquetas</h3>
			</div>
			<div class="center">
				<select id="tiponube" onchange="cargaNube(this.value)">
					<option value="2">Cantidad de búsquedas globales</option>
					<option value="1">Cantidad de ficheros</option>
					<option value="3" selected="selected">Busquedas personales</option>
				</select>
			</div>
			<div id="nube" class="center">
			</div>
		</div>
	</div>
</div>
