<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">
	function reproduce(asset)
	{
		clearDiv("multimediaplugin");
		cargaDiv("multimediaplugin", "${pageContext.request.contextPath}/soundplugin.do", null, null, null, "asset=" + asset);		
	}
</script>

<c:if test='${not empty songs}'>
	<ul class="graphic">
		<c:forEach items='${songs}' var="song" varStatus="status">
			<li>
				<a href="javascript:void(0);" onclick="javascript:reproduce('${song.asset}');" class="sm2_link">
					<c:out value='${song.name}'/>
				</a>
			</li>
		</c:forEach>
	</ul>
	<div id="multimediaplugin" >
	</div>
</c:if>
<c:if test='${empty songs}'>
	<div class="webinfo">
		<div class="webwarning">
			No mp3 files :(.
		</div>
	</div>
</c:if>
