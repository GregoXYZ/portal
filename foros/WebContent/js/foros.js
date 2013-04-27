
function nuenaEntrada()
{
	ajaxPopup('newlist.do', null);
}

function guardaEntrada()
{
	var qs = $("form[name=MensajeForm]").serialize();
	cargaDiv(null, "savelist.do", callbackEntrada, null, null, qs);
}

function nuevoMensaje(entrada, contenido)
{
	ajaxPopup("newmessage.do", null, "entPk=" + entrada + "&conFk=" + contenido);
}

function guardaMensaje(entrada, contenido)
{
	var qs = $("form[name=MensajeForm]").serialize();
	ajaxPopup("savemessage.do", callbackContenido, qs);
}

function invita()
{
}

function avandonaEntrada()
{
	if (!confirm("Vas a avndonar la conversación actual.\n¿Deseas continuar?"))
		return false;
		
	cargaDiv(null, "salirEntrada.do", callbackSalir);
}

function creaInvitacion()
{
	ajaxPopup("invita.do", null);
}

function guardaInvitacion()
{
	var qs = $("form[name=MensajeForm]").serialize();
	ajaxPopup("saveinvitaciones.do", clearPopup, qs);
}