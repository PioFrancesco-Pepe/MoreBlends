<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.Cliente,java.util.Collection,java.util.Iterator"%>
<%
	Cliente cliente=(Cliente)request.getSession().getAttribute("currentUtente");
	if(cliente==null)
		response.sendRedirect(application.getContextPath());
	else{
%>   
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<link rel="stylesheet" 
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="../styles/ar.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/redirect.js"></script>
<script src="../scripts/validate.js"></script>
<title>Modifica Profilo</title>
</head>
<body>
	<%@ include file="../fragments/header.jsp"%>
	</form>
	<div id="blocco">
		<form id="logForm" action="../updateEP" method="post">
			<fieldset>
				<legend>Informazioni</legend>
				<div>
					<label for="email">Email:</label><input type="email" name="email"
						required value="<%=cliente.getEmail()%>"
						onchange="validateFormElem(this, document.getElementById('errorEmail'), emailErrorMessage)"
						id="email"><span id="errorEmail">
					</span>
				</div>
				<div>
					<label for="password">Password:</label><input type="password"
						name="password" value=" "
						pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
						onchange="validateFormElem(this, document.getElementById('errorPassword'), passwordErrorMessage)"
						id="password"><span id="errorPassword"></span>
				</div>
				<hr>
				<div id="pulsanti">
					<input type="submit" value="Modifica Email e/o Password"
						onclick="return validateLog()"> <input type="reset"
						value="Reset">
				</div>
				</fieldset>
		</form>
	</div>	
	<%@ include file="../fragments/footer.jsp"%>
</body>
</html>
<%}%>