<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>

<html>
<head>
</head>
<body>
<div class="textarea center" style="width: 600px;" >
<div class="notice">
	<div class="title">
	<h2>Edita cuotas</h2>
	</div>
	<div class="content">
	<html:form action="/saveCuota" method="post">
		<table class="center">
			<tr>
				<td>Usuario:</td>
				<td>
				<c:if test='${not empty listUsers}'>
				<html:select property="usuFkPk" onchange="sendData('recarga',this.value,'usuFkPk','none','')">
					<html:option value="0">---</html:option>
					<html:options styleClass="activo" collection="listUsers" property="usuPk" labelProperty="usuUkUsuario"/>
				</html:select>
				</c:if>
				</td>
			</tr>
			<tr>
				<td>Cuota de disco (MB):</td>
				<td><html:text property="cuoCuotaDisk" size="12" /></td>
			</tr>
			<tr>
				<td>Cuota de archivo (MB):</td>
				<td><html:text property="cuoCuotaFile" size="12" /></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<br/>
					<input type="submit" class="boton" name="savecuota" value="Aceptar" />
				</td>
			</tr>
		</table>
	</html:form>
	
	<form name="recarga" action="${pageContext.request.contextPath}/editcuota.do" method="post" >
		<input type="hidden" name="usuFkPk"/>
	</form>	
	</div>
</div>
</div>
</body>
</html>