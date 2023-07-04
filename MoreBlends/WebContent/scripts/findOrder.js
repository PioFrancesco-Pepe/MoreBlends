$(document).ready(function(){
	
	$.ajaxSetup({timeout : 10000}); // Imposta (per tutte le richieste) un timeout di 10sec per ricevere la risposta HTTP
  
	$("#find").click(handleOrder);
	$("#datex").change(handleOrder);
	$("#datey").change(handleOrder);
	$("#user").change(handleOrder);
	
	function handleOrder(){
		let jqxhr = $.post("../findOrderJson", { "datex": $("#datex").val(), "datey": $("#datey").val(), "user": $("#user").val()}, function(data){
			$("#list").html(data.result);
		});
			
		jqxhr.fail(function(_jqXHR, textStatus, errorThrown){
			if(textStatus == "timeout"){
				console.log( "Problemi nell'esecuzione della richiesta: nessuna risposta ricevuta nel tempo limite");
        	} else {
	        	console.log("Problemi nell'esecuzione della richiesta:" + errorThrown);
			}
  		});
  		
	}
  	
});