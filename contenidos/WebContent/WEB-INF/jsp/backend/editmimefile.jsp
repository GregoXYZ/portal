<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>

<html>
<head>
</head>
<body>
<div class="textarea">
<div class="notice">
	<div class="title">
	<h2>Edición de tipos de archivos</h2>
	</div>
	<div class="content">
	<html:form action="/savemimefile" enctype="multipart/form-data" method="post">
		<html:hidden property="mimFilPk"/>
		<table class="center">
			<tr>
				<td>Extensión:</td>
				<td><html:text property="mimFilExtension" /><b> *</b></td>
			</tr>
			<tr>
				<td>Mime File:</td>
				<td><html:text property="mimFilMime" /><b> *</b></td>
			</tr>
			<tr>
				<td>Icono:</td>
				<td>
				<html:text property="mimFilIcon" size="30" disabled="true" /> <br/>
				<html:file property="iconFile" size="30" />
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<br/>
					<input type="submit" class="boton" name="aceptar" value="Aceptar" onclick="return submit()"/>
					<input type="button" class="btncancelar" name="cancelar" value="Cancelar" onclick="sendData('cancelar','','','none','')"/>
				</td>
			</tr>
		</table>
	</html:form>
	</div>
</div>
</div>
<!-- <form target="popup" method="post" name="home" action="/home.do " autocomplete="off"> -->
<form name="cancelar" action="${pageContext.request.contextPath}/mimefilelist.do " method="post">
</form>
</body>
</html>
