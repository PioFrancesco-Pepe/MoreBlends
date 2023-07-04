<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, control.* ,model.*, java.lang.*"%>
<!DOCTYPE html>
<html lang="it">
<%
Prodotto item;

Collection<?> model=(Collection<?>) request.getAttribute("prodotti");
Collection<?> modelSearch=(Collection<?>) request.getAttribute("prodottiSearch");
Collection <?> modelMarca=(Collection<?>) request.getAttribute("Categoria");
Collection<?> modelSottoCategoria=(Collection<?>) request.getAttribute("SottoCategoria"); 

	if(model == null){
		request.getRequestDispatcher("../getProdotto?current=2").forward(request, response);
		return;
	}
	if(modelMarca == null){
		request.getRequestDispatcher("../getCategoria?common=1").forward(request, response);
		return;
	}
	if(modelSottoCategoria == null){
		request.getRequestDispatcher("../getSottoCategoria?common=1").forward(request, response);
		return;
	}
	if(modelSearch == null){
		request.getRequestDispatcher("../getProdotto?current=3").forward(request, response);
		request.getSession().removeAttribute("sistema");
		request.getSession().removeAttribute("search");
		request.getSession().removeAttribute("marca");
		return;
	}
	model=modelSearch;
%>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<script src="../scripts/jquery-3.7.0.js" charset="utf-8"></script>
<link rel="stylesheet" href="../styles/all.min.css">
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
		<div id="marcaF"><label for="marca">Marca: </label>
		<select id="marca" name="marca">
		<option value="0">Tutte le marche</option>
		<% 
		Categoria m;
		if (modelMarca != null && modelMarca.size() > 0) {
			Iterator<?> i = modelMarca.iterator();
			while (i.hasNext()){
				m = (Categoria)i.next();
				out.print("<option value=\""+m.getIdCategoria()+"\">"+m.getNomeCategoria()+"</option>");
				}
		}%>
		</select>
		</div>
		<div id="sistemaF">
		<label for="Sistema">Sistema: </label>
		<select id="Sistema" name="sistema">
			<option value="0">Tutti i sistemi</option>
			<% Iterator<?> iterSC = modelSottoCategoria.iterator();
					while(iterSC.hasNext())
					{
						SottoCategoria temp= (SottoCategoria)iterSC.next();
			%>
				<option value="<%=temp.getIdSottoCategoria()%>"><%=temp.getNomeSottoCategoria()%></option>
			<%} %>
		</select>
		</div>	
		</form>
	</div>
	<div id="containerProducts">
	<ul class="list" id="list" data-current-page="1" aria-live="polite">
		<%
		if (model != null && model.size() > 0) {
			Iterator<?> it = model.iterator();
			while (it.hasNext()) {
				item = (Prodotto) it.next();
				if(item.getQuantita() >= 1 && item.getStato()==1){
					String s="<li><div class=\"itemProduct\"> <div class=\"polaroid\">"+"<a href=\"../redirectNewProduct?id="+item.getId()+"\">"+"<img src=\"../getImage?id="+item.getId()+"\"></a><div class=\"container\"><p>"+item.getNome()+"&nbsp;-&nbsp;"+item.getPrezzoVendita()+"&euro;</p></div></div></div></li>";
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