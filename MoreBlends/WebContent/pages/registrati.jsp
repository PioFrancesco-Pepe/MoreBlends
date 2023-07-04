<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/validate.js"></script>
<script src="../scripts/redirect.js"></script>
<link rel="stylesheet" href="../styles/all.min.css">
<meta name="viewport" content="initial-scale=1, width=device-width">
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="../styles/ar.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
<title>Registrati</title>
</head>
<body>
	<%@ include file="../fragments/header.jsp"%>
	</form>
	<h3>Registrati</h3>
	<%
	String checkEmail = (String) request.getSession().getAttribute("checkEmail");
	%>
	<div id="blocco">
		<form id="regForm" action="../Registration" method="post">
			<fieldset>
				<legend>Informazioni</legend>
				<div>
					<label for="firstname">Nome:</label><input type="text"
						name="firstname" id="firstname" required pattern="^[A-Za-z]+$"
						onchange="validateFormElem(this, document.getElementById('errorName'), nameOrLastnameErrorMessage)"><span
						id="errorName"></span>
				</div>
				<div>
					<label for="lastname">Cognome:</label><input type="text"
						name="lastname" id="lastname" required pattern="^[A-Za-z]+$"
						onchange="validateFormElem(this, document.getElementById('errorLastname'), nameOrLastnameErrorMessage)"><span
						id="errorLastname"></span>
				</div>
				<div>
					<label for="email">Email:</label><input type="email" name="email"
						required
						onchange="validateFormElem(this, document.getElementById('errorEmail'), emailErrorMessage)"
						id="email"><span id="errorEmail"> <%
 if (checkEmail != null) {
 	out.write(checkEmail);
 	request.getSession().removeAttribute("checkEmail");
 }
 %>
					</span>
				</div>
				<div>
					<label for="password">Password:</label><input type="password"
						name="password" required
						pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
						onchange="validateFormElem(this, document.getElementById('errorPassword'), passwordErrorMessage)"
						id="password"><span id="errorPassword"></span>
				</div>
				<hr>
				<div id="phoneRow0">
					<label for="phone">Telefono:</label><input type="tel" name="phone"
						id="phone" placeholder="###-#######" required
						pattern="^([0-9]{3}-[0-9]{7})$"
						onchange="validateFormElem(this, document.getElementById('errorPhone0'), phoneErrorMessage)"><span
						id="errorPhone"></span>
				</div>
				<hr>
				<div id="indirizzo">
					<div>
						<label for="via">Via</label> <input type="text" name="via"
							id="via" required 
							pattern="(^[A-z]+)(\s{1}([A-z]|\d)+){0,}$"
							onchange="validateFormElem(this, document.getElementById('errorVia'), viaErrorMessage)"><span
							id="errorVia"></span>
					</div>
					<div>
						<label for="civico">Civico</label><input type="text" name="civico"
							id="civico" required
							pattern="^((\d+)([A-z]?))$"
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
							name="localita" id="localita"
							required pattern="^([A-z]+)(\s{1}[A-z]+){0,}$"
							onchange="validateFormElem(this, document.getElementById('errorCitta'), cittaErrorMessage)"><span
							id="errorCitta"></span>
					</div>
					<div>
						<label for="sp">Sigla Provincia</label><input type="text"
							name="sp" id="sp" required
							pattern="^[A-Z]{2}$"
							onchange="validateFormElem(this, document.getElementById('errorSP'), spErrorMessage)"><span
							id="errorSP"></span>
					</div>
				</div>
				<hr>
				<div id="pulsanti">
					<input type="submit" value="Registrati" onclick="return validateReg()">
					<input type="reset" value="Reset">
				</div>
			</fieldset>
		</form>
	</div>
	<%@ include file="../fragments/footer.jsp"%>
	
</body>
</html>