<%
Integer idCliente = (Integer) request.getSession().getAttribute("idCliente");
Integer isAdmin=(Integer)request.getSession().getAttribute("isAdmin");
%>
<header class="top">
	<div class="LogoContainer">
		<img id="logoHeader" alt="logo MoreBlends" src="#">
	</div>
</header>

<nav>

	<ul>
		<li><a href="<%=application.getContextPath()%>">Home</a></li>
		<li><a
			href="<%=application.getContextPath() + "/pages/catalogo.jsp"%>">Catalogo</a></li>
		<li class="dropdown"><a href="#" class="dropbtn">Profilo</a> <%
 if (idCliente == null)
 	out.write("<div class=\"dropdown-content\"><a href=\"" + application.getContextPath()
 	+ "/pages/accedi.jsp\">Accedi</a><a href=\"" + application.getContextPath() + "/pages/registrati.jsp\""
 	+ ">Registrati</a></div>");
 %></li>
<%if(isAdmin!=null && isAdmin.intValue() == 1)
	out.write("<li class=\"dropdown\">Funzioni Admin");

	out.write("<div class=\"dropdown-content\"><a href=\"" + application.getContextPath()
 	+ "/admin/newProdotto.jsp\">Inserisci Nuovo Prodotto</a>");
	
	out.write("<a href=\"" + application.getContextPath()
 	+ "/admin/viewProduct.jsp\">Visualizza Prodotti</a>");
	
	out.write("<a href=\"" + application.getContextPath()
 	+ "/admin/viewOrders.jsp\">Visualizza Ordini</a>");
	
	out.write("<a href=\"" + application.getContextPath()
 	+ "/admin/report.jsp\">Report entrate</a></div></li>");
	%>
	</ul>
	<div class="search-container">
		<form action="./search" method="get" name="Cerca" id="Cerca">
			<input type="text" placeholder="Search.." name="search" id=cerca>
			<button type="submit">
				<i class="fa fa-search"></i>
			</button>
	</div>
	<button id="cart" type="button" onclick="redirect()">
		<i class="fa fa-shopping-cart"></i>
	</button>

</nav>