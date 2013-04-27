<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>

<script type="text/javascript">
	$(document).ready( function() {
		ocultaOpciones();
		//cargaDiv('ini-relaciones', '/portal/relaciones.do', ocultaOpciones);
		canvasSize();
		refresh();
		$(window).resize(function () {
			canvasSize();
		});
	});

	function canvasSize()
	{
		$("#general").css("top","99px");
		
		var position = $("#ini-relaciones.relacionespersonales").position();
		var height = $("#general").height();
		$("#ini-relaciones.relacionespersonales").css({
			height: height - position.top - 8
		});	

		position = $("#ini-avisos.avisospersonales").position();
		height = $("#general").height();
		$("#ini-avisos.avisospersonales").css({
			height: height - position.top - 18
		});	
	}

	function ocultaOpciones()
	{
		$("#ini-relaciones .opcion").each(function (i){
			this.innerHTML="";
		});
		
		$("#ini-avisos .opcion").each(function (i){
			this.innerHTML="";
		});
	}

	function refresh()
	{
		cargaDiv('ini-relaciones', '/portal/relaciones.do', ocultaOpciones);
		cargaDiv('ini-avisos', '/portal/actualizaavisos.do', ocultaOpciones);
		setTimeout("refresh()",300000);
	}
</script>

<div class="fix-content center">
	<div id="statusbar" class="textarea izquierda">
		<div class="notice">
			<div class="title">
				<h2>Bienvenid@</h2>
			</div>
			<div class="content">
				<p>Gracias por conectarte, para cualquier duda ponte en contacto con el 
				<a href="mailto:MySocialFiles@gmail.com?Subject=Observaciones">administrador del sistema.</a></p>
				<br/>
				<p>Desde aquí intentaremos hacerte llegar las noticias, novedades referentes al portal.</p>
				<br/>
			</div>
		</div>
		<div class="notice">
			<div class="title">
				<h2>
					<a onclick="javascript:ajaxPopup('avisos.do', null);" href="javascript:void(0);">
						Avisos pendientes <img src="images/btn_window_pop.gif"></img>
					</a>
				</h2>
			</div>
			<div id="ini-avisos" class="content avisospersonales">
				<!-- %@ include file="avisos.jsp" % -->
			</div>
		</div>
	</div>
		
	<div id="sidebar" class="textarea derecha" style="text-align: center;">
		<div class="notice" >
			<div class="title">
				<h2>
					<a onclick="javascript:ajaxPopup('adminrelaciones.do', null);" href="javascript:void(0);">
						Mis conocidos <img src="images/btn_window_pop.gif"></img>
					</a>
				</h2>
			</div>
			<div id="ini-relaciones" class="content relacionespersonales" style="padding: 0;">
			</div>
		</div>
		<!-- script type="text/javascript" src="http://www.ubuntu.com/files/countdown/display.js"></script -->
	</div>
</div>