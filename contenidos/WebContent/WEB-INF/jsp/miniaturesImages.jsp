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
		$("#miniaturas").css({
			height: $("#popupdata").height() - 90
		});	
	}

	function cargaScroll() {
		$("#carrousel").carousel( { 
			/*autoSlide: true, 
			autoSlideInterval: 10000,
			delayAutoSlide: 2000,*/
			dispItems: 4,
			loop: true,
			pagination: true,
			//effect: "fade",
			nextBtn: '<img src="images/buttons/next.png" alt="next" width="34" height="33" />',
			prevBtn: '<img src="images/buttons/prev.png" alt="prev" width="34" height="33" />'
		} );
	};
	
	function cargaCarrouselImage(asset)
	{
		contenedor =  $("#amplia");
		contenedor.html("<img src='resizer?asset=" + asset + "&width=500'>");
	}
</script>

<div id="popupcontenidos" >
	<div id="cmenu" class="tabs">
		<c:if test='${not empty listMiniaturas}'>
			<span>
				<c:if test='${not empty myimages}'>
				<a href="javascript:void(0);" onclick="javascript:cargaDiv('miniaturas', '${pageContext.request.contextPath}/expose.do?tag=expose', null);">
				<img src="${pageContext.request.contextPath}/images/expose.png"/>
				Vista en miniaturas
				</a>
				</c:if>
				<c:if test='${not empty sharedimages}'>
				<a href="javascript:void(0);" onclick="javascript:cargaDiv('miniaturas', '${pageContext.request.contextPath}/exposeshared.do?tag=expose', null);">
				<img src="${pageContext.request.contextPath}/images/expose.png"/>
				Vista en miniaturas
				</a>
				</c:if>
			</span>
			<span>
				<c:if test='${not empty myimages}'>
				<a href="javascript:void(0);" onclick="javascript:cargaDiv('miniaturas', '${pageContext.request.contextPath}/carrousel.do?tag=carrousel', cargaScroll);">
				<img src="${pageContext.request.contextPath}/images/carousel.png"/>
				Vista en carrousel
				</a>
				</c:if>
				<c:if test='${not empty sharedimages}'>
				<a href="javascript:void(0);" onclick="javascript:cargaDiv('miniaturas', '${pageContext.request.contextPath}/carrouselshared.do?tag=carrousel', cargaScroll);">
				<img src="${pageContext.request.contextPath}/images/carousel.png"/>
				Vista en carrousel
				</a>
				</c:if>
			</span>
		</c:if>
		<span style="margin-left: 20px; font-weight: bold;">
			<a href="javascript:void(0);" onclick="javascript:clearPopup();">
			<img src="${pageContext.request.contextPath}/images/close.png"/>
			Cerrar
			</a>
		</span>
	</div>
	<div id="miniaturas" class="marco" >
		<div class="webinfo">
			<div class="webwarning">
				Dependiendo del número de de imagenes a mostrar la presentación podría tardar.
				<br/>
				Pulsa el tipo de visulaización que desees...
			</div>
		</div>										
	</div>
</div>
