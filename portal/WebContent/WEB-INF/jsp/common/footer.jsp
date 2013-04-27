<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<c:if test='${not empty user}'>
	<script type="text/javascript">
		$(document).ready(function() { 
			actualizaestado();

			var now = new Date();
			var year = now.getYear();
			if (year < 1900)
				year = year + 1900;
			
			$("#actualyear").html(year);
		});
	
		function actualizaestado()
		{
			footerupdate();
			setTimeout("actualizaestado()",600000);
		}

		function openGTalk()
		{
			// Dialog
			$('#dialog').dialog({
				autoOpen: false,
				width: 600,
				buttons: {	
					"Ok": function() {
						$(this).dialog("close");
					},
					"Cancel": function() {
						$(this).dialog("close");
					}
				}
			});

			$('.webinfoicon').click(function(){
				$('#dialog').dialog('open');
				return false;
			});

			 <%--
			var w = $.WM_open("/portal/mensajeria.do");	
			$(".windowcontent iframe").attr("src","http://www.gmodules.com/ig/ifr?url=http://www.google.com/ig/modules/googletalk.xml&amp;synd=open&amp;title=&amp;lang=en&amp;country=ES&amp;border=%23ffffff%7C0px%2C1px+solid+%23595959%7C0px%2C1px+solid+%23797979%7C0px%2C2px+solid+%23898989&amp;output=js");
			--%>		
		}
	</script>
</c:if>

<c:if test='${empty user}'>
	<script type="text/javascript">
		$(document).ready(function() {
			var now = new Date();
			var year = now.getYear();
			if (year < 1900)
				year = year + 1900;
			
			$("#actualyear").html(year);
		}); 		
	</script>
</c:if>
<div id="dialog">
</div>
<div class="contenido">
	<c:if test='${not empty user}'>
		<div id="rss" class="rss">
			<a type="application/rss+xml" href="/portal/rss.xml" target="_blank">
				<img src="/portal/images/feed.png"  style="margin-top: 3px;">
			</a>
		</div>
	</c:if>
	<div id="copywrite">
		<span>© 2009-<span id="actualyear"></span> Grego Logic Corporation. All rights reserved.</span>
	</div>
	<c:if test='${not empty user}'>
		<div class="webinfoicon" >
			<a href="javascript:void(0);" onclick="javascript:openGTalk();" title="Google Talk">
			<img alt="" src="/portal/images/mensajeria.png" width="22px" height="22px"/>
			</a>
		</div>
		<div id="messagecontrol" class="webinfoicon messageinfo">
			<!-- %@ include file="avisos.jsp" % -->
		</div>
		<div id="erroricon" class="webinfoicon" style="display: none;">
			<a href="javascript:void(0);" onclick="javascript:ajaxPopup('/portal/reportBug.do?bugMessage=' + $('#awebinfo').html());" title="Reporta error">
			<img alt="" src="/portal/images/webinfo.png" width="22px" height="22px"/>
			</a>
		</div>
	</c:if>
	<c:if test='${empty user}'>
		<div class="webinfoicon" >
			<a href="/" title="Sitemap">
				<img alt="" src="/portal/images/sitemaps20x20.gif" width="22px" height="22px"/>
			</a>
		</div>
	</c:if>	
</div>