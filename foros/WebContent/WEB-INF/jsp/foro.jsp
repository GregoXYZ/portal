<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>

<script type="text/javascript">	
	$(document).ready(function() {
		cargaResults();
		var qs = $("form[name=MensajeForm]").serialize();
		cargaDiv("message", "${pageContext.request.contextPath}/contenidos.do", canvasSize, pensandoAjax, null, qs);
	});

	$(window).resize(function () {
		canvasSize();
	});
	
	function canvasSize()
	{
		$("#message").css({
			height: $("#messagecol").height() - 32
		});	
		
		$("#results").css({
			height: $("#resultscol").height() - $("#lmenu").height()
		});
		$("#results div.values").css({
			height: $("#resultscol").height() - $("#lmenu").height() - 20
		});
	}
	
	function cargaResults()
	{
		cargaDiv('results', '${pageContext.request.contextPath}/entradas.do', canvasSize, pensandoAjax);
		setTimeout("cargaResults()",300000);
	}

	function cargaContenido(entrada)
	{
		cargaDiv('message', '${pageContext.request.contextPath}/contenidos.do', canvasSize, pensandoAjax, null, 'entPk=' + entrada);
	}

	function limpia()
	{
		clearDiv('message');
	}

	function callbackEntrada()
	{
		cargaResults();
		clearPopup();
	}

	function callbackContenido()
	{
		var qs = $("form[name=MensajeForm]").serialize();
		cargaDiv('message', '${pageContext.request.contextPath}/contenidos.do', null, pensandoAjax, null, qs);
		cargaDiv('results', '${pageContext.request.contextPath}/entradas.do', canvasSize, pensandoAjax);
		clearPopup();
	}

	function callbackSalir()
	{
		clearDiv("message");
		cargaDiv('results', '${pageContext.request.contextPath}/entradas.do', canvasSize, pensandoAjax);
	}
</script>

<div id="main">
	<div id="window">
		<div id="resultscol">
			<div class="container">
				<div id="lmenu" class="tabs">
					<span class="opmenu">
						<a href="javascript:void(0);" onclick="javascript:nuenaEntrada();">
						<img src="${pageContext.request.contextPath}/images/mail-message-new.png"/>
						Nueva entrada
						</a>
					</span>
				</div>
				<div id="results">
					<!-- %@ include file="results.jsp" % -->
				</div>
			</div>
		</div>
		
		<div id="messagecol">
			<div class="container">
				<div id="rmenu" class="tabs">
					<span class="opmenu">
						<a href="javascript:void(0);" onclick="javascript:avandonaEntrada();">
						<img src="${pageContext.request.contextPath}/images/exit.png"/>
						Abandonar la conversación
						</a>
					</span>
					<span class="opmenu">
						<a href="javascript:void(0);" onclick="javascript:creaInvitacion();">
						<img src="${pageContext.request.contextPath}/images/invita.png"/>
						Invitar a la conversación
						</a>
					</span>
				</div>
				<div id="message">
				</div>
			</div>
		</div>
	</div>
</div>