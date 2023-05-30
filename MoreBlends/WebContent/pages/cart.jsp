<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, control.* ,model.*, java.lang.*"%>
    
<%Cart cart = (Cart) request.getSession().getAttribute("cart"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrello</title>
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/redirect.js"></script>
<script src="https://code.jquery.com/jquery-2.2.4.js" charset="utf-8"></script>
<meta name="viewport" content="initial-scale=1, width=device-width">
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../styles/cart.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
</head>

<body>
<%@ include file="../fragments/header.jsp"%>
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
	  while(iter.hasNext() && q.hasNext())
	  {
		  Prodotto prodotto= iter.next();
		  Integer n= q.next();
		  out.write("<div class=\"item\"><div class=\"buttons\"><a href=\"../cart?action=deleteC&id="+prodotto.getId()+"&quantita="+n.intValue()+"\"><span class=\"delete-btn\"></span></a></div><div class=\"image\"><img class=\"imgprod\" src=\"../getImage?id="+prodotto.getId()+"\" alt=\"\" /></div><div class=\"description\"><span>"+prodotto.getNome()+"</span></div><div class=\"quantity\"><button class=\"plus-btn\" type=\"button\" name=\"button\"><img src=\"../images/plus.svg\" alt=\"\" /></button> <input type=\"hidden\" id=\"idP\" name=\"idP\" value=\""+prodotto.getId()+"\"><input id=\"q\" readonly type=\"text\" name=\"name\" value=\""+n.intValue()+"\"><button class=\"minus-btn\" type=\"button\" name=\"button\"><img src=\"../images/minus.svg\" alt=\"\" /></button></div><div class=\"total-price\">&euro;"+prodotto.getPrezzoVendita()*n.intValue()+"</div></div>");
		  
	  }
	  
  }
  %>
</div>
<%@ include file="../fragments/footer.jsp"%>
<script src="../scripts/cart.js"></script>
</body>
</html>