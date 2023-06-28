<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<% %>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/validate.js"></script>
<script src="../scripts/redirect.js"></script>
<link rel="stylesheet" 
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<meta name="viewport" content="initial-scale=1, width=device-width">
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="../styles/ar.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
<title>Nuovo Indirizzo</title>
</head>
<body>
	<%@ include file="../fragments/header.jsp"%>
	</form>
	<%
	int popup=0;
	if(request.getSession().getAttribute("popupI")!=null)
		popup=(int)request.getSession().getAttribute("popupI");
	if(popup == 1){
		out.write("<div class=\"overlay\" id=\"pop\"><div class=\"popup\"><p>"+request.getSession().getAttribute("error")+"</p><span id=\"close\">X</span></div></div>");
		request.getSession().removeAttribute("popupI");
	}
	%>
	<div id="blocco">
		<form id="indForm" action="../addIndirizzo" method="post">
			<fieldset>
				<legend>Informazioni</legend>
				<div id="indirizzo">
					<div>
						<label for="via">Via</label> <input type="text" name="via"
							id="via" required pattern="(^[A-z]+)(\s{1}([A-z]|\d)+){0,}$"
							onchange="validateFormElem(this, document.getElementById('errorVia'), viaErrorMessage)"><span
							id="errorVia"></span>
					</div>
					<div>
						<label for="civico">Civico</label><input type="text" name="civico"
							id="civico" required pattern="^((\d+)([A-z]?))$"
							onchange="validateFormElem(this, document.getElementById('errorCivico'), civicoErrorMessage)"><span
							id="errorCivico"></span>
					</div>
					<div>
						<label for="CAP">CAP</label><input type="text" name="CAP" id="CAP"
							required pattern="^[0-9]{5}$"
							onchange="validateFormElem(this, document.getElementById('errorCAP'), capErrorMessage)"><span
							id="errorCAP"></span>
					</div>
					<div>
						<label for="localita">Citt&agrave;/Paese</label><input type="text"
							name="localita" id="localita" required
							pattern="^([A-z]+)(\s{1}[A-z]+){0,}$"
							onchange="validateFormElem(this, document.getElementById('errorCitta'), cittaErrorMessage)"><span
							id="errorCitta"></span>
					</div>
					<div>
						<label for="sp">Sigla Provincia</label><input type="text"
							name="sp" id="sp" required pattern="^[A-Z]{2}$"
							onchange="validateFormElem(this, document.getElementById('errorSP'), spErrorMessage)"><span
							id="errorSP"></span>
					</div>
				</div>
				<hr>
				<div id="pulsanti">
					<input type="submit" value="Aggiungi Indirizzo"
						onclick="return validateInd()"> <input type="reset"
						value="Reset">
				</div>
			</fieldset>
		</form>
	</div>
	<%@ include file="../fragments/footer.jsp"%>
	<script src="../scripts/popup.js"></script>
</body>
</html>