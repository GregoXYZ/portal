<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<div class="menutitle">
	<h3>Cuotas</h3>
</div>
<div style="text-align: center;">
	<meter style="display: block;">
		<img alt="" src="images/drive-harddisk.png"/> <b><c:out value="${diskcuota}"></c:out></b> KB
	</meter>
	<meter style="display: block;">
		<img alt="" src="images/empty.png"/> <b><c:out value="${filecuota}"></c:out></b> KB
	</meter>
</div>
