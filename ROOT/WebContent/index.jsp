<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="author" content="Matt Everson of Astuteo, LLC – http://astuteo.com/slickmap" />
		<title>Site Map</title>
		<link href="/favicon.ico" rel="shortcut icon"/>
		<link type="image/x-icon" href="/portal/images/favicon.png" rel="shortcut icon"/>
		<link rel="stylesheet" type="text/css" media="screen, print" href="css/slickmap.css" />	
		<script src="/portal/common/js/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>

		<script type="text/javascript">
			var _gaq = _gaq || [];
			_gaq.push(['_setAccount', 'UA-901869-2']);
			_gaq.push(['_trackPageview']);

			(function() {
				var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
				ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
				var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
			})();
		</script>

		<script type="text/javascript">
			$(document).ready(function() {
				var now = new Date();
				$("#date").html(now.toString() + " ");
			});
		</script>
	</head>
	
	<body>
		<div class="sitemap">
			<h1>SocialFiles Site Map</h1>
			<h2><span id="date"></span>&mdash; Version 1.0</h2>
		
			<ul id="utilityNav">
				<li><a href="/portal/register.do">Register</a></li>
				<li><a href="/portal/login.do">Log In</a></li>
				<li><a href="/">Site Map</a></li>
			</ul>
	
			<ul id="primaryNav">
				<li id="home"><a href="https://SocialFiles.dnsalias.net:8443">/</a></li>
				<li><a href="/portal">SocialFiles</a>
					<ul>
						<li><a href="/portal/home.do">Inicio</a></li>
						<li><a href="">Backend</a>
							<ul>
								<li><a href="/portal/backend/userlist.do">Usuarios</a></li>
								<li><a href="/portal/backend/grouplist.do">Grupos deusuarios</a></li>
								<li><a href="/portal/backend/menulist.do">Menus</a></li>
								<li><a href="/portal/backend/urllist.do">Urls</a></li>
								<li><a href="/portal/backend/zonalist.do">Zonas</a></li>
							</ul>
						</li>
					</ul>
				</li>
				<li><a href="/contenidos">Contenidos</a>
					<ul>
						<li><a href="/contenidos/home.do">Gestor de contenidos</a>
							<ul>
								<li><a href="/contenidos/compartidos.do">Gestor de compartidos</a></li>
							</ul>
						</li>
						<li><a href="/contenidos/compartidos.do">Gestor de compartidos</a></li>
						<li><a href="">Backend</a>
							<ul>
								<li><a href="/contenidos/mimefilelist.do">Tipos de archivos</a></li>
								<li><a href="/contenidos/editcuota.do">Cuotas</a></li>
							</ul>
						</li>
					</ul>
				</li>
				<li><a href="/foros">Foros</a>
					<ul>
						<li><a href="/foros/home.do">Vista mail</a></li>
						<li><a href="/foros/foro.do">Vista foro</a></li>
					</ul>
				</li>
				<li><a href="/contact">Contact</a>
					<ul>
						<li><a href="/contact/offices">Offices</a>
							<ul>
								<li><a href="contact/map">Map</a></li>
								<li><a href="contact/form">Contact Form</a></li>
							</ul>
						</li>
					</ul>
				</li>	
			</ul>
	
		</div>
		
	</body>
</html>
