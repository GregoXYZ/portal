<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">	
	$(document).ready(function() {
		cargaDiv("links", "${pageContext.request.contextPath}/loadlinks.do", ocultaOpciones, null, null, "assPk=" + ${LinkForm.assPk});
	});
	
	function borraLink(link, asset)
	{
		deleteAsset(3, link, "Va a eliminar el link.\n¿Desea continuar?");
		cargaDiv("links", "${pageContext.request.contextPath}/loadlinks.do", ocultaOpciones, null, null, "assPk=" + asset);
	}

	function createLink()
	{
		var qs = $("form[name=LinkForm]").serialize();
		cargaDiv("links", "${pageContext.request.contextPath}/createlink.do", ocultaOpciones, null, null, qs);		
	}

	function refresh()
	{
		var qs = $("form[name=LinkForm]").serialize();
		cargaDiv("links", "${pageContext.request.contextPath}/loadlinks.do", ocultaOpciones, null, null, qs);
	}

	function ocultaOpciones()
	{
		<c:if test='${empty myAsset}'>
			$(".deletelink").each(function (i){
				this.innerHTML="";
			});
		</c:if>
	}
</script>

<div id="linksdata" class="newcontent">
	<div id="popupcontenidos" >
		<div id="cmenu" class="tabs">
			<span style="margin-left: 20px; font-weight: bold;">
				<a href="javascript:void(0);" onclick="javascript:clearPopup();">
				<img src="${pageContext.request.contextPath}/images/close.png"/>
				Cerrar
				</a>
			</span>
			<span style="margin-left: 10px; font-weight: bold;">
				<a href="javascript:void(0);" onclick="javascript:refresh();">
				<img src="${pageContext.request.contextPath}/images/refresh.png" title="Actualiza la vista de links."/>
				Actualiza lista
				</a>
			</span>
		</div>
		<html:form action="/savelinks" method="post" >
			<html:hidden property="assPk" />
			<c:if test='${not empty myAsset}'>		
				<div id="newlink">
						<table class="center">
							<tr>
								<td>Url:</td>
								<td><html:text property="nombre" size="50" maxlength="50" /></td>
							</tr>
							<tr>
								<td>Descripción:</td>
								<td><html:text property="descripcion" size="50" maxlength="100" /></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<input type="button" class="boton" name="createlink" value="Aceptar" onclick="javascript:createLink();"/>
								</td>
							</tr>
						</table>
				</div>
			</c:if>
		</html:form>
		<div id="links" class="marco" >
			<!-- %@ include file="links.jsp" % -->
			<img src='/portal/images/ajax-loader.gif'/>
		</div>
	</div>
</div>
	