function setSrcOther() {
  document.getElementById("logoFooter").src = "../images/logo_transparent.png";
  document.getElementById("logoHeader").src = "../images/logo_transparent.png";
  var url=document.URL;
  if(url.substring(34) == "catalogo.jsp")
  	document.Cerca.action="../search";
  else if(url.substring(34) == "pageProduct.jsp")	
  	document.Cerca.action="../search";
  else if((url.substring(34) == "cart.jsp"))
  	document.Cerca.action="../search";
}

window.onload = setSrcOther;