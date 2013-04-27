<%@ include file="/common/includes/tagLibs.jspf"%>

<c:if test='${not empty user}'>
	<script type="text/javascript">	
		$(document).ready( function() {
			clock("liveclock");
			showInfoWeb();
		});	

		function clock(divid){
			if( divid != null ){
				var Digital=new Date();
				var hours=Digital.getHours();
				var minutes=Digital.getMinutes();
				var seconds=Digital.getSeconds();
				var dn="AM";
				if (hours>12){
					dn="PM";
					hours=hours-12;
				}
				if (hours==0)
					hours=12;
				if (minutes<=9)
					minutes="0"+minutes;
				if (seconds<=9)
					seconds="0"+seconds;
			
				myclock="<font size='5' face='Arial' ><b><font size='2'>Hora Actual:  </font>"
					+hours
					+":"+minutes
					//+":"+seconds
					+" "+dn+
					"</b></font>";
			
			 	$("#"+divid).html(myclock);
				setTimeout("clock()",30000);
			}
		}
	</script>
</c:if>

<div id="infowebheader" style="display: none;">
	<%@ include file="infoweb.jsp" %>
</div>
<div class="contenido">
	<div id="cabtitulo1" >
	SocialFiles
	</div>
	<div id="cabtitulo2" >
	.dnsalias.net
	</div>
	<c:if test='${not empty user}'>
		<div id="headercontent">
			<time id="liveclock"></time>
			<div id="avatar"></div>
		</div>
	</c:if>
	<c:if test='${empty user}'>
		<div id="headernologed"></div>
	</c:if>
</div>