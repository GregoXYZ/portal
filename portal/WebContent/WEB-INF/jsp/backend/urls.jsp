<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>

<%--
<script src="${pageContext.request.contextPath}/common/js/jquery/addons/jquery.tablesorter.pager.js" type="text/javascript"></script>
 --%>

<script type="text/javascript">
	$(document).ready(function() { 
		tableorder(".tablesorter");
		//tablepager(".tablesorter");
	}); 
</script>

<div class="marco">
	<frm:grid id="gridBackEndUrls"/>
	<%--
	<c:import url="/WEB-INF/jsp/common/tablepager.jsp"></c:import>
	 --%>
</div>

<form name="deleteUrl" action="${pageContext.request.contextPath}/backend/deleteurl.do " method="post">
	<input type="hidden" name="urlPk" />
</form>
