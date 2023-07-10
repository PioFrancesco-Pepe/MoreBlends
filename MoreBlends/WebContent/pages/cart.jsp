<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, control.* ,model.*, java.lang.*"%>
    
<%
	Cart cart = (Cart) request.getSession().getAttribute("cart"); 
	Cliente currentCliente = (Cliente) request.getSession().getAttribute("currentUtente");
%>    
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<title>Carrello</title>
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/redirect.js"></script>
<script src="../scripts/jquery-3.7.0.js" charset="utf-8"></script>
<link rel="stylesheet" href="../styles/all.min.css">
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="../styles/cart.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
</head>

<body>
<%@ include file="../fragments/header.jsp"%>
</form>
<form id="checkoutForm" method="POST" action="../checkout">
<div id="all">

<div class="shopping-cart">
  <!-- Title -->
  <div class="title">
    Shopping Bag
  </div>
 
  <%if(cart!=null)
  {
	  List<Prodotto> prodotti = cart.getProducts();
	  List<Integer> quantita = cart.getQuantita();
	  Iterator<Prodotto> iter=prodotti.iterator();
	  Iterator<Integer> q=quantita.iterator(); 	
	  float somma=0;
	  while(iter.hasNext() && q.hasNext())
	  {
		  Prodotto prodotto= iter.next();
		  Integer n= q.next();
		  out.write("<div class=\"item\"><div class=\"buttons\"><a href=\"../cart?action=deleteC&id="+prodotto.getId()+
				  "&quantita="+n.intValue()+"\"><span class=\"delete-btn\"></span></a></div>"
		  		 +"<div class=\"image\"><img class=\"imgprod\" src=\"../getImage?id="+prodotto.getId()
		  		 +"\" alt=\"\" /></div><div class=\"description\"><span>"+prodotto.getNome()+
		  		 "</span></div><div class=\"quantity\"><input id=\"max\"type=\"hidden\" value=\""+prodotto.getQuantita()+
		  		 "\"><button class=\"plus-btn\" type=\"button\" name=\"button\">"+
		  		 "<img class=\"plus\" src=\"../images/plus.svg\" alt=\"\" /></button>"
		  		 +"<input type=\"hidden\" id=\"idP\" name=\"idP\" value=\""
		  		 +prodotto.getId()+"\"><input id=\"q\" readonly type=\"text\" name=\"name\" value=\""
		  		 +n.intValue()+"\"><button class=\"minus-btn\" type=\"button\" name=\"button\">"
		  		 +"<img src=\"../images/minus.svg\" alt=\"\" /></button></div><div class=\"total-price\">&euro;"
		  		 +prodotto.getPrezzoVendita()*n.intValue()+"</div></div>");
		  somma=somma+(prodotto.getPrezzoVendita()*n.intValue());
	  }%>

	  <%
	  if(request.getSession().getAttribute("idCliente") != null)
	  {	
		  if(!cart.isEmpty()){
			//out.write("<form id=\"checkoutForm\" method=\"POST\" action=\"../checkout\">");
	  		out.write("<div id=\"somma\">");	  	
	  		out.write("<input type=\"submit\" value=\"Checkout\">&euro;"+somma+"</div>");
	  		//out.write("<input type=\"hidden\" name=\"totale\" id=\"totale\" value=\""+somma+"\">");
	  		}
		  else
			  out.write("<div><p>Carrello vuoto</p></div>");
	  }
	  else
		  out.write("<div id=\"no-log\"><p>Effettua il login per effettuare il checkout.</p><p>&euro;"+somma+"</p></div>");
  }
  else
	  out.write("<div id=\"no-log\"><p>Carrello vuoto.</p></div>");
  %>
</div>
<%if(currentCliente != null && cart!=null)
		{	
		if(!cart.isEmpty()){
			out.write("<section id=\"p-i\">");
			out.write("<div id=\"indirizzo\">");
			out.write("<label for=\"indirizzo\">Seleziona un indirizzo</label>");
			out.write("<select name=\"indirizzo\" id=\"indirizzo\" required>");
			List<Indirizzo> indirizziCliente = currentCliente.getIndirizzi();
			List<Telefono> telefoniCliente = currentCliente.getNumeriTelefono();
			Iterator<Indirizzo> iterI= indirizziCliente.iterator();
			Iterator<Telefono> iterT= telefoniCliente.iterator();
			while(iterI.hasNext())
			{
				Indirizzo ind= iterI.next();
				out.write("<option id=\"indirizzo\" value=\""+ind.getIdIndirizzo()+"\">"+ind.getVia()+" "+ind.getCivico()+" "+ind.getCAP()+" "+ind.getLocalita()+" "+ind.getSiglaProvincia()+"</option>");
			}
			out.write("</select></div>");
			out.write("<div id=\"telefono\">");
			out.write("<label for=\"telefono\">Seleziona un numero di telefono</label>");
			out.write("<select name=\"telefono\" id=\"telefono\" required>");
			while(iterT.hasNext())
			{
				Telefono tel=iterT.next();
				out.write("<option value=\""+tel.getIdCliente()+"\">"+tel.getNumTelefono()+"</option>");
			}
			out.write("</select></div>");
		
			out.write("<div id=\"metodopagamento\">");
			out.write("<label for=\"idmetodopagamento\">Seleziona un metodo di pagamento <br>");
			out.write("<input type=\"radio\" id=\"paypal\" name=\"idmetodopagamento\" value=\"1\">PayPal");
			out.write("<input type=\"radio\" id=\"cartadicredito\" name=\"idmetodopagamento\" value=\"2\">Carta di Credito");
			out.write("<input type=\"radio\" id=\"Contrassegno\" name=\"idmetodopagamento\" value=\"3\"> Contrassegno");
			out.write("</label></div>");
			
			out.write("<div id=\"metodospedizione\">");
			out.write("<p>Spedizione gratuita</p>");
			if(request.getSession().getAttribute("error")!= null)
			{	
				out.write("<span>"+request.getSession().getAttribute("error")+"</span>");
				request.getSession().removeAttribute("error");
			}	
			out.write("</div>");
			}
		}
	%>

</div><!-- div#all -->
</form>

<%@ include file="../fragments/footer.jsp"%>
<script src="../scripts/cart.js"></script>
</body>
</html>