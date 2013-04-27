<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">	
	$(document).ready(function() {
		focus("allFiles");
	});
</script>

<div id="procesando" ></div>
<div id="sharedata" class="textarea  newcontent" >
	<div class="notice">
		<div class="title">
		<h2>Comparte ficheros</h2>
		</div>
		<div class="content">
			<html:form action="/sharefiles" method="post">
				<table width="100%" class="center">
					<c:if test='${not empty allFiles && not empty listUsers}'>
					<tr>
						<td>Ficheros disponibles:</td>
						<td>
							<table>
								<tr>
								<td style="text-align: center;">
									<h3>Ficheros disponibles</h3>
								</td>
								<td>
								</td>
								<td style="text-align: center;">
									<h3>Ficheros seleccionados</h3>
								</td>
								</tr>
								<tr>
								<td>
									<html:select styleId="allFiles" property="allFiles" multiple="true" size="10" styleClass="listfiles">
										<html:options styleClass="activo" collection="allFiles" property="assPk" labelProperty="assNombre"/>
									</html:select>
								</td>
								<td style="text-align: center;">
									<a href="javascript:void(0)" onclick="javascript:moveListElements('allFiles', 'files', true);">
										<img alt="" src="images/select-all.png"/>
									</a>
									<br/>
									<a href="javascript:void(0)" onclick="javascript:moveListElements('allFiles', 'files', false);">
										<img alt="" src="images/select-selected.png"/>
									</a>
									<br/>
									<a href="javascript:void(0)" onclick="javascript:moveListElements('files', 'allFiles', false);">
										<img alt="" src="images/unselect-selected.png"/>
									</a>
									<br/>
									<a href="javascript:void(0)" onclick="javascript:moveListElements('files', 'allFiles', true);">
										<img alt="" src="images/unselect-all.png"/>
									</a>
								</td>
								<td>
									<html:select styleId="files" property="files" multiple="true" size="10" styleClass="listfiles">
										<html:options styleClass="activo" collection="listFicherosSeleccion" property="assPk" labelProperty="assNombre"/>
									</html:select>
								</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr style="vertical-align: top;">
						<td>Compartir con:</td>
						<td>
							<table style="width: 600px;">
								<tr>
								<td rowspan="4">
									<html:select property="users" multiple="true" size="10" styleClass="listusers">
										<html:options styleClass="activo" collection="listUsers" property="usuPk" labelProperty="usuUkUsuario"/>
									</html:select>
								</td>
								</tr>
								<tr style="height: 25px; text-align: center;">
								<td colspan="2" style="width: 100%;">
									<h3>Opciones</h3>
								</td>
								</tr>
								<tr style="width: 100%;">
								<td style="padding-left: 25px;">
									<c:out value="Reemplaza usuarios:"></c:out>
								</td>
								<td>
									<html:checkbox property="reemplaza" />
								</td>
								</tr>
								<tr class="tags">
								<td style="text-align: right;">	
									<img title="Tags" src="images/tags/tag_blue.png">
								</td>
								<td>
									<html:text property="tags" size="30" maxlength="100" />
								</td>
								</tr>
							</table>
						</td>
					</tr>
					</c:if>
					<c:if test='${empty allFiles}'>
					<tr>
						<td>
							<div class="webinfo">
								<div class="webwarning">
									No existen ficheros en esta carpeta.
								</div>
							</div>							
						</td>
					</tr>
					</c:if>
					<c:if test='${empty listUsers}'>
					<tr>
						<td>
							<div class="webinfo">
								<div class="webwarning">
									No conoces a nadie con quien compartir.
								</div>
							</div>							
						</td>
					</tr>
					</c:if>
					<tr>
						<td colspan="2" style="text-align: center;">
							<br/>
							<c:if test='${not empty allFiles && not empty listUsers}'>
							<input type="button"" class="boton" name="update" value="Actualizar" onclick="javascript:shareListElements();"/>
							</c:if>
							<input type="button" class="btncancelar" name="cancelar" value="Cancelar" onclick="javascript:clearPopup();"/>
						</td>
					</tr>
				</table>
			</html:form>
		</div>
	</div>
</div>
