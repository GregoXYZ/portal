<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/player.css">

<script type="text/javascript">
</script>

<center>
<div id="video-player">
	<video src="${pageContext.request.contextPath}/play/video.${assetformat}?asset=${assetpreview}" autobuffer autoplay >
		<div class="hmtl5-fallback">
			<br>You must have an HTML5 capable browser.
		</div>
	</video>
<%--
	<div class="controls">
		<button class="play-button"></button>
		<div class="progress-bar">
			<div class="progress-list">
				<progress class="load-progress" value="0" style="width: 0%;"></progress>
				<progress class="play-progress" value="0" style="width: 0%;"></progress>
				<!-- I do not like using a transparent gif, but have not figured out how to prevent the image from dragging - czacharias -->
				<a href="#" class="scrubber-button" style="left: 0%;">
					<img src="/images/buttons/transp.gif" height="16" width="16">
				</a>
			</div>
			<span class="progress-text">
				<time class="current-time">00:00</time> / <time class="duration-time">00:00</time>
			</span>
		</div>
		<button class="volume-button" value="max">
			<div class="volume-panel">
				<div class="volume-channel">
					<a href="#" class="volume-slider" style="top: 0%;">
						<img src="/images/buttons/transp.gif" height="10" width="20">
					</a>
				</div>
			</div>
		</button>
		<button class="hd-button" value="on"></button>
		<button class="fullscreen-button"></button>
		<button class="menu-button"></button>
	</div>
 --%>
</div>
</center>