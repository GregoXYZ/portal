
function deleteAsset(tipo, asset, mensaje)
{
	if (confirm(mensaje))
	{
		if (tipo==1 || tipo==2)
		{
			cargaDiv(null, "deleteasset.do", null, null, null, "assPk=" + asset);
			$("#"+asset).fadeOut("slow");
		}
		else if (tipo==3)
		{
			cargaDiv(null, "deleteasset.do", null, null, null, "assPk=" + asset);
		}
	}
}

function addOperation(asset)
{
	cargaDiv("operaciones", "addoper.do", null, null, null, "assPk=" + asset);
}
