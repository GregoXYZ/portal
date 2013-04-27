function goUrl(url) {
	window.location.href = url;
}

function sendData(nomform, idValue, idFormName, confirmar, mensaje) {
	var myform = document.forms[nomform];
	if (idFormName != '') {
		var myid = myform.elements[idFormName];
		myid.value = idValue;
	}

	var tieneOnSubmit = (myform.onsubmit) ? true : false;
	if (tieneOnSubmit) {
		if (myform.onsubmit()) {
			myform.submit();
			return false;
		} else {
			return false;
		}
	} else {
		if (confirmar != "none") {
			if (!confirm(mensaje)) {
				return false;
			} else {
				myform.submit();
				return false;
			}
		} else {
			myform.submit();
			return false;
		}
	}
}

function validEmail(email){      
	var emailReg = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	return emailReg.test(email); 
}

//
// Avisos
//
function updateAviso(aviso) {
	cargaDiv('avisospersonales', '/portal/trataaviso.do', footerupdate, null, null, 'aviPk=' + aviso);
}

function footerupdate() {
	cargaDiv('messagecontrol', '/portal/footerupdate.do', null);
	setTimeout("$('.text-notification').fadeOut(3000);",10000);
}

function loadCSS(cssFile) {  
	var cssLink=document.createElement("link");  
	cssLink.setAttribute("rel", "stylesheet");  
	cssLink.setAttribute("type", "text/css");  
	cssLink.setAttribute("href", cssFile);  
	document.getElementsByTagName("head")[0].appendChild(cssLink);  
} 

function saveBug() {
	var qs = $("form[name=BugForm]").serialize();
	cargaDiv(null, '/portal/saveBug.do', clearPopup, null, null, qs);

}

// Dinamic loadcss files
var filesadded=""; //list of files already added

function loadjscssfile(filename, filetype) {
	if (filetype=="js"){ //if filename is a external JavaScript file
		var fileref=document.createElement('script');
		fileref.setAttribute("type","text/javascript");
		fileref.setAttribute("src", filename);
	}
	else if (filetype=="css"){ //if filename is an external CSS file
		var fileref=document.createElement("link");
		fileref.setAttribute("rel", "stylesheet");
		fileref.setAttribute("type", "text/css");
		fileref.setAttribute("href", filename);
	}
	if (typeof fileref!="undefined")
		document.getElementsByTagName("head")[0].appendChild(fileref);
}

function checkloadjscssfile(filename, filetype){
	if (filesadded.indexOf("["+filename+"]")==-1){
		loadjscssfile(filename, filetype);
		filesadded+="["+filename+"]"; //List of files added in the form "[filename1],[filename2],etc"
	}
	else
		alert("file already added!");
}

function urlencode(str) {
	return escape(str).replace(/\+/g,'%2B').replace(/%20/g, '+').replace(/\*/g, '%2A').replace(/\//g, '%2F').replace(/@/g, '%40');
}

/** move Options function 
*
* Parameters: 
*    fromid : id of initial select element
*    toid   : id of final select element
*    all    : (0|1) 1: all elements
*
* Examples:
*    move(id1,id2,1) -> will move all elements from select with id1 to select with id2
*    move(id1,id2,0) -> will move all selected elements from select with id1 to select with id2
*
*/
function moveListElements(fromid, toid, all){
	var from = document.getElementById(fromid);
	var to = document.getElementById(toid);
	for( var i=0; i<from.length; i++){
		if ( all || from.options[i].selected ){
			var opt = document.createElement('option');
			opt.text = from.options[i].text;
			opt.value = from.options[i].value;
			try {
			    to.add(opt, null); // standards compliant; doesn't work in IE
			}
				catch(ex) {
			    to.add(opt); // IE only
			}
			from.remove(i);
			i--;
		}
	}
}

function selectListElements(id) {
	var list = document.getElementById(id);
	var elements = "";
	var primero = true;
	for( var i=0; i<list.length; i++){
		list.options[i].selected = true;
		if (!primero)
		{
			elements=elements + ",";
		}
		elements = elements + list.options[i].value; 
		primero=false; 
	}
	return elements;
}

function focus(divid)
{
	$("#" + divid).focus();
}

function showInfoWeb()
{
	var data = $("#tempinfodata").html();
	if (data.length > 0)
	{
		$("#webinfodata").html($("#tempinfodata").html());
		$("#tempinfodata").html("");

		$("#infowebheader").fadeIn(1500);
		setTimeout(clearInfoWeb, 30000);
	}
}

function clearInfoWeb()
{	
	$("#infowebheader").fadeOut(1500);
}
