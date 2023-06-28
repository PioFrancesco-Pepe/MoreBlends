function redirect() {
	var url = document.URL;
	if (url.includes("catalogo.jsp"))
		location.replace("../pages/cart.jsp");	
	else if (url.includes("newProdotto.jsp"))
		location.replace("../pages/cart.jsp");
	else if (url.includes("pageProduct.jsp"))
		location.replace("../pages/cart.jsp");
	else if (url.includes("cart.jsp"))
		location.replace("../pages/cart.jsp");
	else if (url.includes("report.jsp"))
		location.replace("../pages/cart.jsp");
	else if (url.includes("viewProduct.jsp"))
		location.replace("../pages/cart.jsp");
	else if (url.includes("editProduct.jsp"))
		location.replace("../pages/cart.jsp");
	else if (url.includes("viewOrders.jsp"))
		location.replace("../pages/cart.jsp");
	else if (url.includes("complessivoOrdine.jsp"))
		location.replace("../pages/cart.jsp");
	else if (url.includes("Profilo.jsp"))
		location.replace("../pages/cart.jsp");
	else if (url.includes("editProfile.jsp"))
		location.replace("../pages/cart.jsp");
	else if (url.includes("insertOtherTelefono.jsp"))
		location.replace("../pages/cart.jsp");
	else if (url.includes("insertOtherIndirizzo.jsp"))
		location.replace("../pages/cart.jsp");
	else if (url.includes("newMorS.jsp"))
		location.replace("../pages/cart.jsp");
	else
		location.replace("./pages/cart.jsp");

}