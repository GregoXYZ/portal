<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">
	$(document).ready(function() {
		/*
		var audioElement = document.createElement('audio');
		audioElement.setAttribute('src', '${pageContext.request.contextPath}/play?asset=${asset}');
		audioElement.load()
		audioElement.addEventListener("load", function() { 
			audioElement.play(); 
			$(".duration span").html(audioElement.duration);
			$(".filename span").html(audioElement.src);
		}, true);
		
		$('.play').click(function() {
			audioElement.play();
			
		});
		$('.pause').click(function() {
			audioElement.pause();
		});
		$('.volumeMax').click(function() {
			audioElement.volume=1;
		});
			$('.volumestop').click(function() {
			audioElement.volume=0;
		});
		$('.playatTime').click(function() {
			audioElement.currentTime= 35;
			audioElement.play();
		});
		*/			
	});
</script>

<audio src="${pageContext.request.contextPath}/play/sound.${assetformat}?asset=${asset}" controls autoplay autobuffer>
	<div class="hmtl5-fallback">
		<br>You must have an HTML5 capable browser.
	</div>
</audio>

<%--
<div class="filename">Filename: <span></span></div>
<div class="duration">Duration: <span></span></div>
<div>
	<a href="#" class="play buttons">play</a>
	<a href="#" class="pause buttons">pause</a>
	<a href="#" class="playatTime buttons">play at 35 secondes</a>
	<a href="#" class="volumestop buttons">Volume to 0</a>
	<a href="#" class="volumeMax buttons">Volume open</a>
</div>	
 --%>