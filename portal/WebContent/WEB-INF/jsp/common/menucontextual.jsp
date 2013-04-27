<%@ include file="/common/includes/tagLibs.jspf"%>

<div onclick="javascript:history.go(-1);" class="opcionctx">Atrás</div>
<div onclick="javascript:history.go(+1);" class="opcionctx">Adelante</div>
<div onclick="javascript:location.reload();" class="opcionctx">Recargar</div>
<div>
	<hr />
</div>
<div onclick="javascript:window.location.href='menuContextual.js'" class="opcionctx">Ver código</div>
<div onclick="javascript:window.location.href='estilo.css'"	class="opcionctx">Ver estilo</div>
<div>
	<hr />
</div>
<div onclick="javascript:window.location.href='${pageContext.request.contextPath}'" class="opcionctx">Root</div>
<div>
	<hr />
</div>
<div id="sizer" style="display: block;">
	<a class="increaseFont" href="javascript:void(0);">
		<img border="0" title="Increase text size" src="/portal/images/zoom-in.png" alt="+" />
	</a>
	<a class="decreaseFont" href="javascript:void(0);">
		<img border="0"	title="Decrease text size" src="/portal/images/zoom-out.png" alt="-" />
	</a>
	<a class="resetFont" href="javascript:void(0);">
		<img border="0" title="Restore default text size" src="/portal/images/zoom-original.png" alt="=" />
	</a>
</div>