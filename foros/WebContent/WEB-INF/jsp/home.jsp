<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>

<script type="text/javascript">	
	$(document).ready(function() {
		cargaResults();
		var qs = $("form[name=MensajeForm]").serialize();
		cargaDiv('message', '${pageContext.request.contextPath}/contenido.do', canvasSize, pensandoAjax, null, qs);
	});
	
	$(window).resize(function () {
		canvasSize();
	});
		
	function canvasSize()
	{
		$("#message div.values").removeAttr("style");
		var height = $("#message div.values").height();
		var heightFutura = $("#messagecol").height() - $("#thread").height() - $(".tabs").height();

		if (height < heightFutura)
		{
			$("#message").css({
				height: heightFutura
			});
			$("#message div.values").css({
				height: heightFutura - 21 
			});
		}
		else
		{
			$("#message").css({
				height: heightFutura - 7
			});
		}
		
		$("#results").css({
			height: $("#resultscol").height() - $("#lmenu").height()
		});
		$("#results div.values").css({
			height: $("#resultscol").height() - $("#lmenu").height() - $(".tabs").height() + 8
		});
	}
	
	function cargaResults()
	{
		cargaDiv('results', '${pageContext.request.contextPath}/results.do', canvasSize, pensandoAjax);
		setTimeout("cargaResults()",300000);
	}
	
	function cargaThreats(entrada, contenido)
	{
		cargaDiv('thread', '${pageContext.request.contextPath}/threads.do', null, pensandoAjax, null, 'entPk=' + entrada);
		cargaDiv('message', '${pageContext.request.contextPath}/contenido.do', canvasSize, pensandoAjax, null, 'conPk=' + contenido);
	}

	function cargaContenido(id)
	{
		cargaDiv('message', '${pageContext.request.contextPath}/contenido.do', canvasSize, pensandoAjax, null, 'conPk=' + id);
	}

	function limpia()
	{
		clearDiv('thread');
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
		cargaDiv('results', '${pageContext.request.contextPath}/results.do', canvasSize, pensandoAjax);
		cargaDiv('thread', '${pageContext.request.contextPath}/threads.do', null, pensandoAjax, null, qs);
		clearPopup();
	}

	function callbackSalir()
	{
		clearDiv("thread");
		clearDiv("message");
		cargaDiv('results', '${pageContext.request.contextPath}/results.do', canvasSize, pensandoAjax);
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
				<div id="thread">
					<%@ include file="threads.jsp" %>
				</div>
				<div class="sep"></div>
				<div id="message">
					<%@ include file="message.jsp" %>
				</div>
			</div>
		</div>
	</div>
</div>