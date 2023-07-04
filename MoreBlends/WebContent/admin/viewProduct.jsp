<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, control.* ,model.*, java.lang.*"%>
<%
	Cliente c=(Cliente)request.getSession().getAttribute("currentUtente");
	if (c == null || c.isAdmin()== 0)
		response.sendRedirect(application.getContextPath());
	else
	{
		Collection<?> prodotti =(Collection<?>)request.getSession().getAttribute("prodottiV2");
		Collection<?> cerca = (Collection<?>)request.getSession().getAttribute("cercaP");
		Collection <?> categoria=(Collection<?>) request.getSession().getAttribute("Categoria");
		Collection<?> sottoCategoria=(Collection<?>) request.getSession().getAttribute("SottoCategoria"); 
		
		if (categoria == null) {
			request.getRequestDispatcher("../getCategoria?admin=2").forward(request, response);
			return;
		}
		if (sottoCategoria == null) {
			request.getRequestDispatcher("../getSottoCategoria?admin=2").forward(request, response);
			return;
		}
		if(cerca!=null && !cerca.isEmpty())
			prodotti=(Collection<?>)request.getSession().getAttribute("cercaP");
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
<script src="https://code.jquery.com/jquery-2.2.4.js" charset="utf-8"></script>
<title>Visualizza tutti i prodotti</title>
</head>
<body>
	<%@ include file="../fragments/header.jsp"%>
	<div id="containerAll">
	<div id="containerFilters">
		<p>Filtri</p>
		<div id="marcaF">
			<label for="marca">Marca: </label> <select id="marca" name="marca">
				<option value="0">Tutte le marche</option>
				<%
				Categoria m;
				if (categoria != null && categoria.size() > 0) {
					Iterator<?> i = categoria.iterator();
					while (i.hasNext()) {
						m = (Categoria) i.next();
						out.print("<option value=\"" + m.getIdCategoria() + "\">" + m.getNomeCategoria() + "</option>");
					}
				}
				%>
			</select>
		</div>
		<div id="sistemaF">
			<label for="Sistema">Sistema: </label> <select id="Sistema"
				name="sistema">
				<option value="0">Tutti i sistemi</option>
				<%
				Iterator<?> iterSC = sottoCategoria.iterator();
				while (iterSC.hasNext()) {
					SottoCategoria temp = (SottoCategoria) iterSC.next();
				%>
			<option value="<%=temp.getIdSottoCategoria()%>"><%=temp.getNomeSottoCategoria()%></option>
				<%
				}
				%>
			</select>
		</div>
		</form>
	</div>
	<div id="containerProductsAdmin">
		<%if(request.getSession().getAttribute("status")!=null){ %>
		<span><%=request.getSession().getAttribute("status") %></span>
		<%} request.getSession().removeAttribute("status"); %>
		<ul class="listAdmin" id="list" data-current-page="1"
			aria-live="polite">
			<%
			Iterator<?> iterP = prodotti.iterator();
			while (iterP.hasNext()) {
				Prodotto item = (Prodotto) iterP.next();
				if(item.getStato()==1)
				{
			%>
			<li><div class="itemProductA">
					<div class="polaroid">
						<img src="../getImage?id=<%=item.getId()%> alt=<%=item.getNome()%>">
						<div class="container">
							<div id="edit-trash">
								<a
									href="../editDeleteProduct?action=edit&code=<%=item.getId()%>"><i
									class="fa fa-pencil"> </i></a><a
									href="../editDeleteProduct?action=delete&code=<%=item.getId()%>"><i
									class="fa-regular fa-trash-can"></i></a>
							</div>
							<p><%=item.getNome()%>&nbsp;-&nbsp;<%=item.getPrezzoVendita()%>&euro;
							</p>
						</div>
					</div>
				</div></li>
			<%
			}}
			%>
		</ul>
	</div>
	</div>
	<div id="containerPag">
		<nav class="pagination-container">
			<button class="pagination-button" id="prev-button"
				aria-label="Previous page" title="Previous page">&lt;</button>

			<div id="pagination-numbers"></div>

			<button class="pagination-button" id="next-button"
				aria-label="Next page" title="Next page">&gt;</button>
		</nav>
	</div>
	<%@ include file="../fragments/footer.jsp"%>
</body>
<script type="text/javascript" src="../scripts/pagination.js"></script>
<% }request.getSession().removeAttribute("cercaP");request.getSession().removeAttribute("status");
%>
</html>