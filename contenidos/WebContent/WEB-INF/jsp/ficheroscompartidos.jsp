<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<html:form action="/compartidos" method="post" >
	<html:hidden property="usuPk" />
	<c:if test='${not empty listCarpetas}'>
		<div id="carpetas">
			<html:select property="carFk" onchange="javascript:recarga(this.value);">
				<html:option value="0">---</html:option>
				<html:options styleClass="activo" collection="listCarpetas" property="carPk" labelProperty="assDescripcion"/>
			</html:select>
		</div>
	</c:if>

	<c:choose>
		<c:when test='${empty gridFicheros}'>
			<div class="webinfo">
				<div class="webmessage">
					Selecciona un usuario para ver los archivos que tiene compartidos contigo
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div id="dirdata" class="marco">
				<frm:grid id="gridFicheros"/>
			</div>
		</c:otherwise>
	</c:choose>
</html:form>
