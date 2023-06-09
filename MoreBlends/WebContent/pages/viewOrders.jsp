<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.Cliente"%>
<%
Cliente cliente = (Cliente) request.getSession().getAttribute("currentUtente");
if (cliente == null)
	response.sendRedirect(application.getContextPath());
else {
	request.getSession().setAttribute("common", ""+cliente.getId());
%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<script src="../scripts/jquery-3.7.0.js" charset="utf-8"></script>
<link rel="stylesheet" href="../styles/all.min.css">
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="../styles/orders.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/redirect.js"></script>
<script src="../scripts/findOrder.js"></script>

<title>Visualizza gli Ordini</title>
</head>
<body>
	<%@ include file="../fragments/header.jsp"%>
	</form>
	<div id="all">
		<div id="container">
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
				<div>
					<button type="button" id="find">Cerca</button>
				</div>
				<div>
					<span>
						<%if(request.getSession().getAttribute("error")!=null)
						{
							out.print(request.getSession().getAttribute("error"));
							request.getSession().removeAttribute("error");
						}
						%>
					</span>
				</div>
		</div>
	</div>
	<%@ include file="../fragments/footer.jsp"%>
</body>
</html>
<%}%>