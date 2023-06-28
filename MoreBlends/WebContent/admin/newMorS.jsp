<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.Cliente"%>
<%
Cliente cliente = (Cliente) request.getSession().getAttribute("currentUtente");
if (cliente == null || cliente.isAdmin() == 0)
	response.sendRedirect(application.getContextPath());
%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="../styles/newProd.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/redirect.js"></script>
<script src="../scripts/validateNewProduct.js"></script>
<title>
	<%
	if (request.getParameter("action").equalsIgnoreCase("M"))
		out.write("Inserisci una nuova marca");
	else
		out.write("Inserisci un nuovo sistema");
	%>
</title>
</head>
<body>

	<%@ include file="../fragments/header.jsp"%>
	</form>

	<%
	if (request.getParameter("action").equalsIgnoreCase("M")) {
		request.getSession().setAttribute("add", "M");
	%>
	<div id="all">
		<form action="../InsertMarcaORSistema" id="newMarca" method="POST">
			<fieldset>
				<legend>Informazioni Marca</legend>
				<div>
					<label for="nameM">Nome Marca: </label><input type="text"
						name="nameM" required pattern="^[A-z]+$"
						onchange="validateFormElem(this, document.getElementById('errorNameM'), namemErrorMessage)"
						id="nameM"><span id="errorNameM">
						<% if(request.getSession().getAttribute("errore")!=null){
							out.write((String)request.getSession().getAttribute("errore"));
							request.getSession().removeAttribute("errore");}
						%>
						</span>
				</div>
				<div>
					<label for="descM">Descrizione: </label><input type="text"
						name="descM" required pattern="^([A-z]+)([A-z]| |è)*$"
						onchange="validateFormElem(this, document.getElementById('errorDescM'), descmErrorMessage)"
						id="descM"><span id="errorDescM"></span>
				</div>
				<div id="pulsanti">
					<input type="submit" value="Aggiungi prodotto"
						onclick="return validateM()"> <input type="reset"
						value="Reset">
				</div>
			</fieldset>
		</form>
	</div>
	<%
	} else if (request.getParameter("action").equalsIgnoreCase("S")) {
	request.getSession().setAttribute("add", "S");
	%>
	<div id="all">
		<form action="../InsertMarcaORSistema" id="newSistema" method="POST">
			<fieldset>
				<legend>Informazioni Sistema</legend>
				<div>
					<label for="nameS">Nome Sistema: </label><input type="text"
						name="nameS" required pattern="([A-z]+)([A-z]| )*$"
						onchange="validateFormElem(this, document.getElementById('errorNameS'), namesErrorMessage)"
						id="nameS"><span id="errorNameS">
						<% if(request.getSession().getAttribute("errore")!=null){
							out.write((String)request.getSession().getAttribute("errore"));
							request.getSession().removeAttribute("errore");}
						%>
						</span>
				</div>
				<div id="pulsanti">
					<input type="submit" value="Aggiungi prodotto"
						onclick="return validateS()"> <input type="reset"
						value="Reset">
				</div>
			</fieldset>
		</form>
	</div>
	<%
	} else
	response.sendRedirect(application.getContextPath());
	%>
	<%@ include file="../fragments/footer.jsp"%>
</body>
</html>