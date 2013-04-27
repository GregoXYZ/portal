<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<div id="procesando" ></div>
<div id="defaultpopup" class="textarea  newcontent" >
	<div class="notice">
		<div class="title">
		<h2>Actualiza fichero</h2>
		</div>
		<div class="content">
			<html:form action="/updatefile" method="post" >
				<%@ include file="intoUpdateFile.jsp" %>
			</html:form>
		</div>
	</div>
</div>
