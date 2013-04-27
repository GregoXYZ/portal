
function tableorder(style)
{
	// call the tablesorter plugin, the magic happens in the markup 
    $(style).tablesorter(); 
    //assign the sortStart event 
    $(style).bind("sortStart",function() { 
        $("#overlay").show(); 
    }).bind("sortEnd",function() { 
        $("#overlay").hide(); 
    }); 
}

function tablepager(style)
{
	$(style).tablesorterPager( {
		container: $("#pager"),
		size: 20,
		positionFixed: false
	});
}

function marca(divid, style)
{
	$(divid).live("click", function () {
		$(this).toggleClass(style);
	});
}

function activa(divid, style)
{
	$(divid).live("click", function () {
		$(divid).each(function (i){
			$(this).removeClass(style);
		});
		$(this).addClass(style);
	});	
}

$(document).ready(function(){
	// Reset Font Size
	$(".resetFont").click(function(){
		$('#pagina').css('font-size', 1 + "em");
		$('#popup').css('font-size', $('#pagina').css('font-size'));
		return false;
	});
	// Increase Font Size
	$(".increaseFont").click(function(){
		var currentFontSize = $('#pagina').css('font-size');
		var currentFontSizeNum = parseFloat(currentFontSize, 10);
		var newFontSize = currentFontSizeNum*1.2;
		$('#pagina').css('font-size', newFontSize);
		$('#popup').css('font-size', $('#pagina').css('font-size'));
		return false;
	});
	// Decrease Font Size
	$(".decreaseFont").click(function(){
		var currentFontSize = $('#pagina').css('font-size');
		var currentFontSizeNum = parseFloat(currentFontSize, 10);
		var newFontSize = currentFontSizeNum*0.8;
		$('#pagina').css('font-size', newFontSize);
		$('#popup').css('font-size', $('#pagina').css('font-size'));
		return false;
	});
});

/*
$(document).ready(function() { 
	$("html").addClass("js");
});
*/ 
