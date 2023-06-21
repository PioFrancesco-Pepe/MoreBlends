<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="model.Cliente,java.util.Collection,java.util.Iterator,model.Ordine"%>
<%
Cliente cliente = (Cliente) request.getSession().getAttribute("currentUtente");
if (cliente == null || cliente.isAdmin() == 0)
	response.sendRedirect(application.getContextPath());
else {
	Collection<?> ordini = (Collection<?>) request.getAttribute("ordini");

	if (ordini == null) {
		request.getRequestDispatcher("../getOrdini").forward(request, response);
		return;
	}
	Collection<?> clienti = (Collection<?>) request.getAttribute("clienti");
	if (clienti == null) {
		request.getRequestDispatcher("../getClienti").forward(request, response);
		return;
	}
%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="../styles/orders.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/redirect.js"></script>
<script src="https://code.jquery.com/jquery-2.2.4.js" charset="utf-8"></script>
<script src="../scripts/findOrder.js"></script>

<title>Visualizza gli Ordini</title>
</head>
<body>
	<%@ include file="../fragments/header.jsp"%>
	<div id="all">
		<div>
			<div class="table-orderLegend">
				<div id="no">Numero Ordine</div>
				<div id="do">Data Ordine</div>
				<div id="c">Cliente</div>
				<div id="so">Stato Ordine</div>
			</div>
			<ul class="list" id="list" data-current-page="1" aria-live="polite">
				
			</ul>
		</div>
		<div id="divCerca">
				<div id="datexy">
					<label for="datex">Da: </label><input type="date" id="datex"
						name="datex"><br> <label for="datey"> A: </label><input
						type="date" id="datey" name="datey">
				</div>
				<div id="utenti">
					<label for="user">Cliente </label><select name="user" id="user">
						<option value=0>Tutti i clienti</option>
						<%
						Iterator<?> iter = clienti.iterator();
						while (iter.hasNext()) {
							Cliente temp = (Cliente) iter.next();
						%>
						<option value="<%=temp.getId()%>"><%=temp.getId() + " " + temp.getNome() + " " + temp.getCognome()%></option>
						<%
						}
						%>
					</select>
				</div>
				<div>
					<button type="button" id="find">Cerca</button>
				</div>
		</div>
	</div>
	<%@ include file="../fragments/footer.jsp"%>
</body>
</html>
<%}%>