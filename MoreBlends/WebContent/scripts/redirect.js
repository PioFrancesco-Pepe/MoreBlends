function redirect()
{
	var url=document.URL;
	if(url.substring(34)== "catalogo.jsp")
		location.replace("../pages/cart.jsp");
	else if(url.substring(34)=="pageProduct.jsp")
		location.replace("../pages/cart.jsp");
	else if(url.substring(34)=="newProdotto.jsp")
		location.replace("../pages/cart.jsp");
	else if(url.substring(17)=="MoreBlends/")	
		location.replace("./pages/cart.jsp");
	else if(url.substring(34)=="report.jsp")
		location.replace("../pages/cart.jsp");
	else if((url.substring(34) == "viewProduct.jsp"))
  		location.replace("../pages/cart.jsp");	
  	else if((url.substring(34) == "viewOrders.jsp"))
  		location.replace("../pages/cart.jsp");
  	else if((url.substring(34) == "editProduct.jsp"))
  		location.replace("../pages/cart.jsp");		
  	else if((url.substring(34) == "complessivoOrdine.jsp"))
  		location.replace("../pages/cart.jsp");		
}