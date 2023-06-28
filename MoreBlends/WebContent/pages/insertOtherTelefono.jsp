<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Nuovo numero di telefono</title>
</head>
<body>
<%@ include file="../fragments/header.jsp"%>
</form>
<%
	int popup=0;
	if(request.getSession().getAttribute("popupP")!=null)
		popup=(int)request.getSession().getAttribute("popupP");
	if(popup == 1){
		out.write("<div class=\"overlay\" id=\"pop\"><div class=\"popup\"><p>"+request.getSession().getAttribute("error")+"</p><span id=\"close\">X</span></div></div>");
		request.getSession().removeAttribute("popupP");
	}
	%>
<div id="blocco">
		<form id="phoneForm" action="../addPhone" method="post">
			<fieldset>
				<legend>Informazioni</legend>
				<div id="phoneRow0">
					<label for="phone">Telefono:</label><input type="tel" name="phone"
						id="phone" placeholder="###-#######" required
						pattern="^([0-9]{3}-[0-9]{7})$"
						onchange="validateFormElem(this, document.getElementById('errorPhone0'), phoneErrorMessage)"><span
						id="errorPhone"></span>
				</div>
				<hr>
				<div id="pulsanti">
					<input type="submit" value="Inserisci" onclick="return validatePhone()">
					<input type="reset" value="Reset">
				</div>
			</fieldset>
		</form>
		</div>
		<%@ include file="../fragments/footer.jsp"%>
		<script src="../scripts/popup.js"></script>
</body>
</html>