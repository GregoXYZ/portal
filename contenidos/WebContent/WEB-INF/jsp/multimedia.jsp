<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">
	$(document).ready( function() {
		canvasSize();
		$(window).resize(function () {
			canvasSize();
		});
	});

	function canvasSize()
	{
		$("#multimedia").css({
			height: $("#popupdata").height() - 90
		});	
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
	<div id="multimedia" class="marco" >
		<%@ include file="soundmanager.jsp" %>
	</div>
</div>
