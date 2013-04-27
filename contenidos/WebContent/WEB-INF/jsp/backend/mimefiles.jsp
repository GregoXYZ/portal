<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>

<script type="text/javascript">
	$(document).ready(function() { 
		tableorder(".tablesorter");
		//tablepager(".tablesorter");
	}); 
</script>

<div class="marco" >
	<frm:grid id="gridBackEndMimeFiles"/>
	<%--
	<c:import url="/WEB-INF/jsp/common/tablepager.jsp"></c:import>
	 --%>

	<form name="editMimFil" action="${pageContext.request.contextPath}/editmimefile.do " method="post">
		<input type="hidden" name="mimFilPk" />
	</form>
	<form name="deleteMimFil" action="${pageContext.request.contextPath}/deletemimefile.do " method="post">
		<input type="hidden" name="mimFilPk" />
	</form>
	<form name="newMimFil" action="${pageContext.request.contextPath}/editmimefile.do " method="post">
	</form>
</div>
