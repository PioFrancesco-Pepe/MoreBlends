function redirect()
{
	var url=document.URL;
	if(url.substring(34)== "catalogo.jsp")
		location.replace("../pages/cart.jsp");
	else if(url.substring(34)=="pageProduct.jsp")
		location.replace("../pages/cart.jsp");
	else if(url.substring(17)=="MoreBlends/")	
		location.replace("./pages/cart.jsp");
}