/* make a request object */
function makeRequestObject() {
	if (window.XMLHttpRequest) {
		// Mozilla, Safari ...
		http_request = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		// IE
		try {
			http_request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			// old versions IE
			try {
				http_request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
	
	return http_request;
}

/* end */


/* replace element with given text/html */

function updateElement(elemName, updateText) {
	if (elemName != "") {
		$("#" + elemName).html(updateText);
	}
}

/* end */


/* given an ajax object, update the divid with responseText */

function recvUpdate(ajax, divId, callback, thinking, callerror) {	
    if(ajax.readyState==1){
    	if (thinking != null && typeof thinking != "undefined" &&
			divId != null && typeof divId != "undefined") {
    		//document.body.style.cursor="wait";
			thinking.call(this, divId);
    	}
    } else if(ajax.readyState==4) {
    	//document.body.style.cursor="default"; 
    	if(ajax.status==200){
            //mostramos los datos dentro de la div
			if (divId != null && typeof divId != "undefined")
			{
				var response = ajax.responseText;
				updateElement(divId, response);				
			}
	    	if (callback != null && typeof callback != "undefined")
	    		callback.call(this);
        } else {
	    	if (callerror != null && typeof callerror != "undefined")
	    		callerror.call(this, ajax.status);
	    	else
	    		ajaxInfoWeb();
        }
    }
}

/* end */

/* ajax update - POST  */

function ajaxUpdatePost(divid, url, callback, thinking, callerror, parameters) {
	var ajax = makeRequestObject();
	ajax.onreadystatechange = function() { recvUpdate(ajax, divid, callback, thinking, callerror); };
	ajaxOpen(ajax, "POST", url, true);
	ajax.send(parameters);
}

/* end */

/* ajax update - GET */

function ajaxUpdateGet(divid, url, callback, thinking, callerror) {
	var ajax = makeRequestObject();
	ajax.onreadystatechange = function() { recvUpdate(ajax, divid, callback, thinking, callerror); };
	ajaxOpen(ajax, "GET", url, true);
	ajax.send(null);
}

function ajaxUpdateGetSyncron(divid, url) {
	var ajax = makeRequestObject();
	// Llamamos al servidor en modo sincrono
	ajaxOpen(ajax, "GET", url, false);
	ajax.send(null);
	// Interpretamos la respuesta del servidor
	if (ajax.status == 200) {
	    // Respuesta OK
		recvUpdate(ajax, divid);
	    return true;
	} else {
		ajaxInfoWeb();
		return false;
	}			
}

/* end */

/* ajax update - SEND ONE WAY */

function ajaxUpdateSend(url) {
	var ajax = makeRequestObject();
	ajax.onreadystatechange = function() { };
	ajaxOpen(ajax, "GET", url, true);
	ajax.send(null);
}

/* end */


function ajaxOpen(ajax, method, url, mode)
{
	ajax.open(method, url, mode);
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	ajax.setRequestHeader("X-Requested-With", "XMLHttpRequest");
}

/* end */

/* cargas ajax */
/**
 * Parameters: 
 * 		divId: 		Contenedor donde se carga el resultado de ejecutar la url
 * 		url:		Funcion a ejecutar
 * 		callback:	Funcion a ejecutar al finalizar correctamente la carga de la url
 * 		thinking:	Funcion a ejecutar al iniciarse la carga.
 * 		parameters:	Parámetros de la llamada.
 */
function cargaDiv(divId, url, callback, thinking, callerror, parameters)
{
	//ajaxUpdateGet(divId, url, callback, thinking, callerror);
	ajaxUpdatePost(divId, url, callback, thinking, callerror, parameters);
}

function clearDiv(divId)
{	
	$("#" + divId).each(function (i){
		$(this).html("");
	});
}

function ajaxPopup(func, callback, parameters)
{
	var contenedor = "popupdata";
	var popupcontent = "popupcontent";
	
	var popup = $("#popup");
	if( popup.length == 0 )
	{
		$("body").append("" +
			"<div class='popup' id='popup'>" +
			"<div class='popupcontent' id='popupcontent'>" +
			"<div class='backgroundpopup'></div>" +
			"<div id='popupdata' class='popupdata'></div>" +
			"</div></div>");
	}
	else
	{
		var div = $("#popupcontent");
		if (div.length > 0)
		{
			var finalWhile = false;
			var ind = 1;
			while (!finalWhile) 
			{
				popupcontent = "popupcontent" + ind;
				div = $("#" + popupcontent);
				if (div.length > 0)
				{
					ind=ind+1;				
				}
				else
				{
					finalWhile=true;
				}
			}
			contenedor = "popupdata" + ind;
		}
		
		var zIndex = 90 + ind * 10;
		var zIndexPopup = 95 + ind * 10;
		$("#popup").append("<div class='popupcontent' id='" + popupcontent + 
				"'><div class='backgroundpopup' style='z-index: " + zIndex + ";'></div>" +
				"<div id='" + contenedor + "' class='popupdata'  style='z-index: " + zIndexPopup + ";'>" +
				"<div id='cargapopup' class='newcontent'><img src='/portal/images/ajax-loader.gif'/></div>" +
				"</div></div>");
	}
	
	cargaDiv(contenedor, func, callback, null, null, parameters);
}

function clearPopup(ind)
{
	var popup;
	if (typeof ind == "undefined") {
		popup = $("#popup");
		if( popup.length > 0 ) {
			popup.html("");
		}	
	}
	else if (ind > 0) {
		popup = $("#popupcontent" + ind);
		if( popup.length > 0 ) {
			popup.html("");
		}	
	}
	else {
		var finalWhile = false;
		var i = 1;
		var contenedor;
		
		while (!finalWhile) 
		{
			contenedor = $("#popupcontent" + i);
			if (contenedor.length > 0)
			{
				i=i+1;				
			}
			else
			{
				finalWhile=true;
			}
		}
		if (i>1) {
			popup = $("#popupcontent" + (i - 1));
			if( popup.length > 0 ) {
				popup.html("");
			}		
		}
		else {
			clearPopup();
		}
	}
}

/* end */

/* Tooltip */

var lastToolTip=0;

function ajax_tooltip(source, func, style, callback, parameters){
	if( func != "" && func != "undefined" ){
		$("#tooltip").remove();
		$("body").append("" +
			"<div class='tooltip' id='tooltip'>" +
			"<div class='tooltip" + lastToolTip + "' id='tooltip_content'>" +
			"<div class='tooltiploading' style='text-align: center; pading: 5px;'>" +
			"<img src='/portal/images/loading.gif'/>" +
			"</div>" +
			"</div></div>");

		var tooltip_content = $("#tooltip #tooltip_content");
		
		var my_tooltip = $("#tooltip")
			.css({
				opacity:0.8,
				visibility :"hidden",
				display:"none",
				width: tooltip_content.width(),
		 		height: tooltip_content.height()
			}).fadeIn(400);
		
		if (style != null && style != "")
		{
			$('#tooltip #tooltip_content').addClass(style);
		}
		
		cargaDiv("tooltip .tooltip" + lastToolTip, func, callback, null, null, parameters);

		$(source).mouseover(function(){
			my_tooltip.css({
				opacity:0.8, 
				visibility:"visible",
				display:"none"
			}).show(); //fadeIn(400);
		})
		.mousemove(function(kmouse){
			var border_top = $(window).scrollTop(); 
			var border_right = $(window).width();
			var left_pos;
			var top_pos;
			var offset = 20;
			if(border_right - (offset *2) >= my_tooltip.width() + kmouse.pageX){
				left_pos = kmouse.pageX+offset;
			} else{
				left_pos = border_right-my_tooltip.width()-offset;
			}
				
			if(border_top + (offset *2)>= kmouse.pageY - my_tooltip.height()){
				top_pos = border_top +offset;
			} else{
				top_pos = kmouse.pageY-my_tooltip.height()-offset;
			}
			my_tooltip.css({
				visibility:"visible",
				left:left_pos, 
				top:top_pos
			});
		})
		.mouseout(function(){
			$("#tooltip").remove();
		});		
	}
}

/* end */


function ajaxInfoWeb()
{
	cargaDiv("infowebheader", "infoweb.do", showAjaxInfoWeb);
}

function showAjaxInfoWeb()
{
	$("#infowebheader").fadeIn(1500);
	setTimeout(clearAjaxInfoWeb, 30000);
}

function clearAjaxInfoWeb()
{	
	$("#infowebheader").fadeOut(1500);
}

function pensandoAjax(divId, imagen)
{
	var pensandoHTML = "<div class='center' style='text-align: center; padding: 25px;'>";
	if (imagen != null && typeof imagen != "undefined") {
		pensandoHTML = pensandoHTML + "<img src='" + imagen + "'/>";
	}
	else {
		pensandoHTML = pensandoHTML + "<img src='/portal/images/loading.gif'/>";
	}
	pensandoHTML = pensandoHTML + "<p>" +
						"Cargando..." +
						"</p>" +
						"</div>";

	$("#" + divId).html(pensandoHTML);
}
