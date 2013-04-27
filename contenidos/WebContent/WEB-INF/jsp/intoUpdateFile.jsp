<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">
	$(document).ready( function() {
		refrescaTags();
		focus("focused");
	});

	function refrescaTags()
	{
		cargaDiv("tags", "loadtags.do", null, null, null, "assPk=" + ${UpdateFileForm.assPk});
	}

	function borraTag(tag, asset)
	{
		cargaDiv("tags", "deletetag.do", null, null, null, "tagPk=" + tag + "&assPk=" + asset);
	}
</script>

<html:hidden property="assPk" />
<table class="center">
	<tr>
		<td>Fichero:</td>
		<td colspan="2"><html:text property="file" styleId="focused" size="50" maxlength="250" /></td>
	</tr>
	<tr>
		<td>Descripción:</td>
		<td colspan="2"><html:text property="descripcion" size="50" maxlength="500" /></td>
	</tr>
	<c:if test='${not empty listUsers}'>
	<tr style="vertical-align: top;">
		<td>Compartir con:</td>
		<td>
			<span>
			<html:select property="users" multiple="true" size="10" styleClass="listusers">
				<html:options styleClass="activo" collection="listUsers" property="usuPk" labelProperty="usuUkUsuario"/>
			</html:select>
			</span>
			<c:if test='${assetpreviewtype == "image"}'>
			<span style="margin-left: 15px;">
				<img alt="" src="resizer?asset=${UpdateFileForm.assPk}&width=150&height=150"/>
			</span>
			</c:if>
		</td>
	</tr>
	</c:if>
	<tr>
		<td>
		</td>
		<td>
			<br/>
			<input type="button" class="boton" name="update" value="Actualizar" onclick="javascript:updateFile();"/>
			<input type="button" class="btncancelar" name="cancelar" value="Cancelar" onclick="javascript:clearPopup(0);"/>
		</td>
	</tr>
	<tr class="tags">
		<td colspan="2">	
			<br/>
		</td>
	</tr>
	<tr class="tags">
		<td>Etiquetas:</td>
		<td>	
			<div id="tags">
				<!-- %@ include file="tags.jsp" % -->
			</div>
		</td>
	</tr>
	<tr class="tags">
		<td style="text-align: right;">	
			<img title="Nuevos tags" src="images/tags/tag_blue.png">
		</td>
		<td>
			<html:text property="tags" size="50" maxlength="100" />
		</td>
	</tr>
</table>
