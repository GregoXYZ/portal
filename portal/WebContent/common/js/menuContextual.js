// JavaScript Document

function mostrarMenu() {
	var div = document.getElementById("menuctx");
	div.style.visibility = "visible";
	div.style.left = tempX + "px";
	div.style.top = tempY + "px";

	return false;
}

function ocultarMenu() {
	var div = document.getElementById("menuctx");
	div.style.visibility = "hidden";
}

/*
 ============================================================
 Capturing The Mouse Position in IE4-6 & NS4-6
 (C) 2000 www.CodeLifter.com
 Free for all users, but leave in this  header
 */

// Temporary variables to hold mouse x-y pos.s
var tempX = 0;
var tempY = 0;

window.onload = function() {

	// Detect if the browser is IE or not.
	// If it is not IE, we assume that the browser is NS.
	var IE = document.all ? true : false;

	// If NS -- that is, !IE -- then set up for mouse capture
	if (!IE)
		document.captureEvents(Event.MOUSEMOVE);

	// Set-up to use getMouseXY function onMouseMove
	document.onmousemove = getMouseXY;

	// Main function to retrieve mouse x-y pos.s

	function getMouseXY(e) {
		if (IE) { // grab the x-y pos.s if browser is IE
			tempX = event.clientX + document.body.scrollLeft;
			tempY = event.clientY + document.body.scrollTop;
		} else { // grab the x-y pos.s if browser is NS
			tempX = e.pageX;
			tempY = e.pageY;
		}
		// catch possible negative values in NS4
		if (tempX < 0)
			tempX = 0;
		if (tempY < 0)
			tempY = 0;

		return true;
	}
}

/**
 * Esta funcion devuelve el texto seleccionado
 * Para expandir el Script con botones "Copiar", "Cortar" y "Pegar"
 * obtenido de: http://www.codetoad.com/javascript_get_selected_text.asp
 * 
 * - Article by:	 Jeff Anderson (9/1/2006) 
 * - Sponsored by:	Neil Matthews Hypnotherapy, Wilmslow
 * - Summary:	A cross-browser script to get text selected by the user
 */
function getSelText() {
	var txt = '';

	if (window.getSelection)
		txt = window.getSelection();
	else if (document.getSelection)
		txt = document.getSelection();
	else if (document.selection)
		txt = document.selection.createRange().text;
	else
		return;

	return txt;
}