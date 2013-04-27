function getOS() {
	if (navigator.userAgent.indexOf('Linux') != -1) {
		var OpSys = "Linux";
	} else if ((navigator.userAgent.indexOf('Win') != -1)
			&& (navigator.userAgent.indexOf('95') != -1)) {
		var OpSys = "Windows95";
	} else if ((navigator.userAgent.indexOf('Win') != -1)
			&& (navigator.userAgent.indexOf('NT') != -1)) {
		var OpSys = "Windows NT";
	} else if (navigator.userAgent.indexOf('Win') != -1) {
		var OpSys = "Windows 3.1";
	} else if (navigator.userAgent.indexOf('Mac') != -1) {
		var OpSys = "Macintosh";
	} else {
		var OpSys = "operating system not recognised";
	}
	return OpSys;
}

function getUserAgent()
{
	return navigator.userAgent;
}

function setDisplay(obj, valor)
{
	if(valor == "none")
	{
    	obj.style.display = "none";
    } 
    else 
    {
    	obj.style.display = "";
	}
}

function setClass(obj,className)
{
	obj.className =className;
}
