// mostra il popup dopo 1k millisecondi (1secondi)
setTimeout(
    function() {
    	console.log("run");
      document.getElementById('pop').style.display="block"	;
   	}, 1000);


// chiudi il popup quando clicchi sulla X
document.getElementById("close").onclick = function(e){
    document.getElementById('pop').style.display="none";
}

// chiudi il popup quando clicchi sullo sfondo nero
document.getElementById("pop").onclick = function(e){
	document.getElementById('pop').style.display="none";	
}