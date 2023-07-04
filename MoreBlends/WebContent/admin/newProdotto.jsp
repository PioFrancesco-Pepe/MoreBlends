<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*,control.*,model.*, java.lang.*"%>
<%
Cliente cliente=(Cliente)request.getSession().getAttribute("currentUtente");
if (cliente == null || cliente.isAdmin() == 0)
	response.sendRedirect(application.getContextPath());
else {
	Collection<?> categoria = (Collection<?>) request.getSession().getAttribute("Categoria");
	Collection<?> sottoCategoria = (Collection<?>) request.getSession().getAttribute("SottoCategoria");

	if (categoria == null) {
		request.getRequestDispatcher("../getCategoria?admin=1").forward(request, response);
		return;
	}
	if (sottoCategoria == null) {
		request.getRequestDispatcher("../getSottoCategoria?admin=1").forward(request, response);
		return;
	}
%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<link rel="stylesheet" href="../styles/all.min.css">
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="../styles/newProd.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/redirect.js"></script>
<script src="../scripts/validateNewProduct.js"></script>
<title>Inserisci un nuovo Prodotto</title>
</head>
<body>
	<%@ include file="../fragments/header.jsp"%>
	</form>
	<div id="all">
		<form action="../InsertProduct" id="newProd" method="POST"
			enctype="multipart/form-data">
			<fieldset>
				<legend>Informazioni Prodotto</legend>
				<div>
					<label for="nameProd">Nome Prodotto: </label><input type="text"
						name="NomeProdotto" required
						onchange="validateFormElem(this, document.getElementById('errorNameProd'), nameprodErrorMessage)"
						id="nameProd"><span id="errorNameProd"></span>
				</div>
				<div>
					<label for="desc">Descrizione: </label><input type="text"
						name="desc" required
						onchange="validateFormElem(this, document.getElementById('errorDesc'), descErrorMessage)"
						id="desc"><span id="errorDesc"></span>
				</div>
				<div>
					<label for="descA">Descrizione Ampia: </label><input type="text"
						name="descA" required
						onchange="validateFormElem(this, document.getElementById('errorDescA'), descAErrorMessage)"
						id="descA"><span id="errorDescA"></span>
				</div>
				<div>
					<label for="costo">Costo Prodotto: </label><input type="text"
						name="costo" required pattern="^(\d)+(.){1}(\d)+$"
						onchange="validateFormElem(this, document.getElementById('errorCosto'), costoErrorMessage)"
						id="costo"><span id="errorCosto"></span>
				</div>
				<div>
					<label for="pv">Prezzo di Vendita: </label><input type="text"
						name="pv" required pattern="^(\d)+(.){1}(\d)+$"
						onchange="validateFormElem(this, document.getElementById('errorPrezzoVendita'), pvErrorMessage)"
						id="pv"><span id="errorPrezzoVendita"></span>
				</div>
				<div>
					<label for="q">Quantit&agrave;: </label><input type="text" name="q"
						required pattern="^(\d)+$"
						onchange="validateFormElem(this, document.getElementById('errorQuantita'), qErrorMessage)"
						id="q"><span id="errorQuantita"></span>
				</div>
				<div>
					<label for="categoria">Marca: </label> <select name="categoria"
						id="categoria" required>
						<%
						if(categoria!=null && !categoria.isEmpty()){
						Iterator<?> iterC = categoria.iterator();
						while (iterC.hasNext()) {
							Categoria c = (Categoria) iterC.next();
							out.write("<option value=\"" + c.getIdCategoria() + "\"" + ">" + c.getNomeCategoria() + "</option>");
						}
						}
						%>
					</select>
				</div>
				<div>
					<label for="sottocategoria">Sotto Categoria: </label> <select
						name="sottocategoria" id="sottocategoria" required>
						<%
						if(sottoCategoria!=null && !sottoCategoria.isEmpty()){
						Iterator<?> iterSC = sottoCategoria.iterator();
						while (iterSC.hasNext()) {
							SottoCategoria sc = (SottoCategoria) iterSC.next();
							out.write("<option value=\"" + sc.getIdSottoCategoria() + "\"" + ">" + sc.getNomeSottoCategoria() + "</option>");
						}
						}
						%>
					</select>
				</div>
				<div>
					<label for="talkPhoto">Carica un'immagine: </label><input
						id="talkPhoto" required class="file" type="file" name="talkPhoto"
						value="" maxlength="255">
				</div>
				<hr>
				<div id="pulsanti">
					<input type="submit" value="Aggiungi prodotto"
						onclick="return validate()"> <input type="reset"
						value="Reset">
				</div>
			</fieldset>
		</form>
	</div>
	<%@ include file="../fragments/footer.jsp"%>
</body>
<%} %>
</html>