function setSrcOther() {
  document.getElementById("logoFooter").src = "../images/logo_transparent.png";
  document.getElementById("logoHeader").src = "../images/logo_transparent.png";
  var url=document.URL;
  if(url.includes("catalogo.jsp"))
  	document.Cerca.action="../search";
  else if(url.includes("newProdotto.jsp"))
  	document.Cerca.action="../search";
  else if(url.includes("pageProduct.jsp"))	
  	document.Cerca.action="../search";
  else if(url.includes("cart.jsp"))
  	document.Cerca.action="../search";
  else if(url.includes("report.jsp"))
  	document.Cerca.action="../search";
  else if(url.includes("viewProduct.jsp"))
  	document.Cerca.action="../searchAdmin";
  else if(url.includes("editProduct.jsp"))
  	document.Cerca.action="../search";
  else if(url.includes("viewOrders.jsp"))
  	document.Cerca.action="../search";
  else if(url.includes("complessivoOrdine.jsp"))
  	document.Cerca.action="../search";
  else if(url.includes("Profilo.jsp"))
  	document.Cerca.action="../search";
  else if(url.includes("editProfile.jsp"))
  	document.Cerca.action="../search";
  else if(url.includes("insertOtherTelefono.jsp"))
  	document.Cerca.action="../search";
  else if(url.includes("insertOtherIndirizzo.jsp"))
  	document.Cerca.action="../search";
  else if (url.includes("newMorS.jsp"))
  	document.Cerca.action="../search";

}

window.onload = setSrcOther;