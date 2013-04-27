<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>
<head>
<script type="text/javascript">
	function redim()
	{
		var frame = document.getElementById("weblink");
		frame.height = window.innerHeight - 240;
	}
</script>
</head>
<body onload="redim();">
<div class="textarea">
<div class="notice">
	<div class="title">
		<h2>${param.uri}</h2>
	</div>
	<div class="content">
		<iframe name="weblink" id="weblink" src="${param.uri}" frameborder="0" scrolling="auto" >
		</iframe>
	</div>
</div>
</div>
</body>