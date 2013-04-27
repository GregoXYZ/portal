<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15"%>
<%
	response.setHeader("Cache-Control", "no-cache");	//HTTP 1.1
	response.setHeader("Pragma", "no-cache");			//HTTP 1.0
	response.setDateHeader("Expires", 0);				//prevents caching at the proxy server
%>

<%@ include file="/common/includes/tagLibs.jspf"%>
<%@ include file="/common/includes/styles.jspf"%>
<%@ include file="/common/includes/javascripts.jspf"%>

<html:html>
	<head>
	<meta name="description" content="Comparte archivos con tu comunidad de amigos y más (No IE)">
	<meta name="keywords" content="portal contenidos archivos social comparte share files">
	<meta name="language" content="es">
	<meta name="verify-v1" content="4E6KgBzX8qzNAEOlcwzFFWOy8kJC5YE/H/cikCCEF/U=" >
	
	<link href="/favicon.ico" rel="shortcut icon"/>
	<link type="image/x-icon" href="/portal/images/favicon.png" rel="shortcut icon"/>
	
	<title>
		<tiles:importAttribute name="title" />
		<bean:message key="portal.titulo"/>
	</title>
	<script type="text/javascript">
		<c:if test='${not empty user}'>
		$(document).ready(function() { 
			document.oncontextmenu = mostrarMenu;
			document.onclick = ocultarMenu;
		});
		</c:if>
	</script>
	
	</head>
	<body>
		<header id="encabezado">
			<tiles:insert attribute="encabezado" />
		</header>
		
		<div id="menu">
			<tiles:insert attribute="menu" />
		</div>

		<article id="general">
			<div id="pagina" class="pagina">
				<tiles:insert attribute="principal" />
			</div>
		</article>

		<footer id="footer">
			<tiles:insert attribute="footer" />
		</footer>
		
		<div id="menuctx" style="visibility: hidden; position: absolute;">
			<c:if test='${not empty user}'>
				<%@ include file="../menucontextual.jsp" %>
			</c:if>
		</div>
	</body>
</html:html>