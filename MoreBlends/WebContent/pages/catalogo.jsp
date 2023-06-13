<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, control.* ,model.*, java.lang.*"%>
<!DOCTYPE html>
<html lang="it">
<%
String s="";
Prodotto item;
String search= (String)request.getSession().getAttribute("search");
String marca= (String)request.getSession().getAttribute("marca");
Collection<?> model=(Collection<?>) request.getAttribute("prodotti");
Collection<?> modelSearch=(Collection<?>) request.getAttribute("prodottiSearch");
Collection <?> modelMarca=(Collection<?>) request.getAttribute("MarcaProdotti");
int flag=0;
	if(model == null){
		request.getRequestDispatcher("../getProdotto?current=2").forward(request, response);
	return;
	}
	if(modelMarca == null){
		request.getRequestDispatcher("../getMarca").forward(request, response);
	return;
	}
	if(modelSearch == null){
		request.getRequestDispatcher("../getProdotto?current=3").forward(request, response);
		request.getSession().removeAttribute("search");
		request.getSession().removeAttribute("marca");
		flag=1;
		return;
	}
	model=modelSearch;
%>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="../styles/style.css" type="text/css">
<link rel="stylesheet" href="../styles/catalogo.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/redirect.js"></script>
<title>Catalogo</title>
</head>
<body>
	<%@include file="../fragments/header.jsp"%>
	<div id="containerAll">
	<div id="containerFilters">
		<p>Filtri</p>
		<select id="marca" name="marca">
		<option value="0">Tutte le marche</option>
		<% 
		String m;
		if (modelMarca != null && modelMarca.size() > 0) {
			Iterator<?> i = modelMarca.iterator();
			while (i.hasNext()){
				m= (String)i.next();
				out.print("<option value=\""+m+"\">"+m+"</option>");
				}
		}%>
		</select>	
		</form>
	</div>
	<div id="containerProducts">
	<ul id="list" data-current-page="1" aria-live="polite">
		<%
		if (model != null && model.size() > 0) {
			Iterator<?> it = model.iterator();
			while (it.hasNext()) {
				item = (Prodotto) it.next();
				if(item.getQuantita() >= 1){
					s="<li><div class=\"itemProduct\"> <div class=\"polaroid\">"+"<a href=\"../redirectNewProduct?id="+item.getId()+"\">"+"<img src=\"../getImage?id="+item.getId()+"\"></a><div class=\"container\"><p>"+item.getNome()+"&nbsp;-&nbsp;"+item.getPrezzoVendita()+"&euro;</p></div></div></div></li>";
					out.write(s);
				}
			}}%>
	</ul>
	</div>
	
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
	<script type="text/javascript" src="../scripts/pagination.js"></script>
</body>
</html>