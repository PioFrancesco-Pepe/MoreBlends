<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.Prodotto,model.Categoria,model.SottoCategoria,model.Cliente,java.util.Iterator,java.util.Collection"%>
    <%Cliente cliente=(Cliente)request.getSession().getAttribute("currentUtente");
	if (cliente == null || cliente.isAdmin()== 0)
			response.sendRedirect(application.getContextPath()); 
    else{
    		if(request.getSession().getAttribute("ProductEdit")!=null){
    			Prodotto editProduct = (Prodotto)request.getSession().getAttribute("ProductEdit");
    			Collection<?> categoria = (Collection<?>) request.getSession().getAttribute("Categoria");
    			Collection<?> sottoCategoria = (Collection<?>) request.getSession().getAttribute("SottoCategoria");
				Categoria editC= (Categoria) request.getSession().getAttribute("editC");
				SottoCategoria editSC= (SottoCategoria) request.getSession().getAttribute("editSC");
				
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
<title>Modifica prodotto</title>
</head>
<body>
	<%@ include file="../fragments/header.jsp"%>
	</form>
	<div id="all">
		<form action="../editProduct" method="POST" enctype="multipart/form-data">
			<fieldset>
				<legend>Informazioni Prodotto da Modificare</legend>
				<div>
					<label for="nameProd">Nome Prodotto: </label><input type="text"
						name="NomeProdotto" required
						onchange="validateFormElem(this, document.getElementById('errorNameProd'), nameprodErrorMessage)"
						id="nameProd" value="<%=editProduct.getNome()%>"><span id="errorNameProd"></span>
				</div>
				<div>
					<label for="desc">Descrizione: </label><input type="text"
						name="desc" required
						onchange="validateFormElem(this, document.getElementById('errorDesc'), descErrorMessage)"
						id="desc" value="<%=editProduct.getDescrizione()%>"><span id="errorDesc"></span>
				</div>
				<div>
					<label for="descA">Descrizione Ampia: </label><input type="text"
						name="descA" required
						onchange="validateFormElem(this, document.getElementById('errorDescA'), descAErrorMessage)"
						id="descA" value="<%=editProduct.getDescrizioneAmpia()%>"><span id="errorDescA"></span>
				</div>
				<div>
					<label for="costo">Costo Prodotto: </label><input type="text"
						name="costo" required pattern="^(\d)+(.){1}(\d)+$"
						onchange="validateFormElem(this, document.getElementById('errorCosto'), costoErrorMessage)"
						id="costo" value="<%=editProduct.getCosto()%>"><span id="errorCosto"></span>
				</div>
				<div>
					<label for="pv">Prezzo di Vendita: </label><input type="text"
						name="pv" required pattern="^(\d)+(.){1}(\d)+$"
						onchange="validateFormElem(this, document.getElementById('errorPrezzoVendita'), pvErrorMessage)"
						id="pv" value="<%=editProduct.getPrezzoVendita()%>"><span id="errorPrezzoVendita"></span>
				</div>
				<div>
					<label for="q">Quantit&agrave;: </label><input type="text" name="q"
						required pattern="^(\d)+$"
						onchange="validateFormElem(this, document.getElementById('errorQuantita'), qErrorMessage)"
						id="q" value="<%=editProduct.getQuantita()%>"><span id="errorQuantita"></span>
				</div>
				<div>
					<label for="categoria">Marca: </label> <select name="categoria"
						id="categoria" required>
						<option value="<%=editProduct.getIdCategoria()%>"><%=editC.getNomeCategoria()%></option>
						<%
						Iterator<?> iterC = categoria.iterator();
						while (iterC.hasNext()) {
							Categoria c = (Categoria) iterC.next();
							if(editC.getIdCategoria()!=c.getIdCategoria())
								out.write("<option value=\"" + c.getIdCategoria() + "\"" + ">" + c.getNomeCategoria() + "</option>");
						}
						%>
					</select>
				</div>
				<div>
					<label for="sottocategoria">Sotto Categoria: </label> <select name="sottocategoria"
						id="sottocategoria" required>
						<option value="<%=editProduct.getIdSottoCategoria()%>"><%=editSC.getNomeSottoCategoria()%></option>
						<%
						Iterator<?> iterSC = sottoCategoria.iterator();
						while (iterSC.hasNext()) {
							SottoCategoria sc = (SottoCategoria) iterSC.next();
							if(editSC.getIdSottoCategoria()!=sc.getIdSottoCategoria())
								out.write("<option value=\"" + sc.getIdSottoCategoria() + "\"" + ">" + sc.getNomeSottoCategoria() + "</option>");
						}
						%>
					</select>
				</div>
				<div>
					<label for="talkPhoto">Carica un'immagine: </label><input
						id="talkPhoto" class="file" type="file" name="talkPhoto"
						value="" maxlength="255">
				</div>
				<hr>
				<div id="pulsanti">
					<input type="submit" value="Modifica prodotto"
						onclick="return validate()"> <input type="reset"
						value="Reset">
				</div>
				</fieldset>
		</form>
	</div>	
	<%@ include file="../fragments/footer.jsp"%>
</body>
</html>
<%} 			
}%>