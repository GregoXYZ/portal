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
		$(".code").css({
			height: $("#popupdata").height() - 110
		});	
	}
</script>

<pre id="viewdata" class="code" >
<cnt:writeAsset asset="${assetpreview}"/>
</pre>
