$('input#giornaliero').on('click', function() {
	
	$(document).attr("title", "Report Giornaliero");
	$("#setDate").css("display","block");
	$("#divG").css("display","block");
	$("#divM").css("display","none");
	$("#divA").css("display","none");
	
	removeRequired();
	
	$("#dateG").prop("required","true");
})

$('input#mensile').on('click', function() {
	$(document).attr("title", "Report Mensile");
	$("#setDate").css("display","block");
	$("#divG").css("display","none");
	$("#divM").css("display","block");
	$("#divA").css("display","none");
	
	removeRequired();

	$("#mese").prop("required","true");
})

$('input#annuale').on('click', function() {
	$(document).attr("title", "Report Annuale");
	$("#setDate").css("display","block");
	$("#divG").css("display","none");
	$("#divM").css("display","none");
	$("#divA").css("display","block");
	
	removeRequired();
	
	$("#anno").prop("required","true");
})

$('#reset').on("click", function(){
	$(document).attr("title", "Report");
	$("#setDate").css("display","none");
	$("#divG").css("display","none");
	$("#divM").css("display","none");
	$("#divA").css("display","none");
	
	removeRequired();
})

function removeRequired()
{
	$("#dateG").removeAttr("required");
	$("#mese").removeAttr("required");
	$("#anno").removeAttr("required");
}