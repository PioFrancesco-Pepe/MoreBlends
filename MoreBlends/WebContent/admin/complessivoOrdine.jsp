<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.Cliente,model.Prodotto,java.util.Collection,java.util.Iterator"%>
    <%Cliente cliente=(Cliente)request.getSession().getAttribute("currentUtente");
	if (cliente == null || cliente.isAdmin()== 0)
			response.sendRedirect(application.getContextPath());
	else
	{	
			Collection<?> prodotti = (Collection<?>)request.getSession().getAttribute("prodottiOrdine");
			Collection<?> quantita = (Collection<?>)request.getSession().getAttribute("quantitaOrdine");
			if(prodotti==null)
				response.sendRedirect(application.getContextPath());
			if(quantita==null)
				response.sendRedirect(application.getContextPath());
%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<link rel="stylesheet" href="../styles/all.min.css">
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="../styles/cart.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/redirect.js"></script>
<title>Complessivo Ordine</title>
</head>
<body>
<%@ include file="../fragments/header.jsp"%>
<div id="all">
	<div class="shopping-cart">
		<div class="title">Complessivo</div>
		<%
			float somma=0;
			Iterator<?> prod = prodotti.iterator();
			Iterator<?> qua = quantita.iterator();
			while(prod.hasNext() && qua.hasNext())
			{
				Prodotto itemP= (Prodotto)prod.next();
				Integer itemI=(Integer) qua.next();
				somma +=(itemP.getPrezzoVendita()*itemI.intValue());
			
		%>
		<div class="item">
			<div class="image">
				<img class="imgprod" src="../getImage?id=<%=itemP.getId()%>>" alt="<%=itemP.getNome()%>">
			</div>
			<div class="description"><span><%=itemP.getNome()%></span></div>
			<div class="quantity">
				<%= itemI.intValue()%>
			</div>
			<div class="total-price">
				&euro;<%= itemP.getPrezzoVendita()*itemI.intValue()%>
			</div>
		</div>
		<%} %>
		<div id="somma">
			<p>Totale</p><p>&euro;<%=somma %></p>
		</div>
	</div>
</div>
<%@ include file="../fragments/footer.jsp"%>
</body>
</html>
<% }%>