<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, control.* ,model.*, java.lang.*"%>
<%
	Cliente c=(Cliente)request.getSession().getAttribute("currentUtente");
	if (c == null || c.isAdmin()== 0)
		response.sendRedirect(application.getContextPath());
	else
	{
		Collection<?> prodotti =(Collection<?>)request.getSession().getAttribute("prodottiV2");
%>    
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<link rel="stylesheet" 
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="../styles/catalogo.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/redirect.js"></script>
<title>Visualizza tutti i prodotti</title>
</head>
<body>
<%@ include file="../fragments/header.jsp"%>
<div id="containerProductsAdmin">
	<ul class="listAdmin" id="list" data-current-page="1" aria-live="polite">
	<% 
		Iterator<?> iterP= prodotti.iterator();
		while(iterP.hasNext())
		{
			Prodotto item =(Prodotto) iterP.next();
			//System.out.println(item);
		
	%>
		<li><div class="itemProductA"> <div class="polaroid"><img src="../getImage?id=<%=item.getId()%>"><div class="container"><div id="edit-trash"><a href="../editDeleteProduct?action=edit&code=<%=item.getId()%>"><i class="fa fa-pencil">
			</i></a><a href="../editDeleteProduct?action=delete&code=<%=item.getId()%>"><i class="fa-regular fa-trash-can"></i></a></div><p><%=item.getNome()%>&nbsp;-&nbsp;<%=item.getPrezzoVendita()%>&euro;</p></div></div></div></li>
	<% }%></ul>
</div>	
<div id="containerPag">
	<nav class="pagination-container">
    <button class="pagination-button" id="prev-button" aria-label="Previous page" title="Previous page">
      &lt;
    </button>

    <div id="pagination-numbers">

    </div>

    <button class="pagination-button" id="next-button" aria-label="Next page" title="Next page">
      &gt;
    </button>
  </nav>
  </div>
<%@ include file="../fragments/footer.jsp"%>
</body>
<script type="text/javascript" src="../scripts/pagination.js"></script>
<% }%>
</html>