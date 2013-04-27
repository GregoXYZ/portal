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
		$("#viewdata").css({
			height: $("#popupdata").height() - 110
		});	
	}
</script>

<div id="viewdata">
	<img alt="" src="resizer?asset=${assetpreview}&width=750"/>
</div>
