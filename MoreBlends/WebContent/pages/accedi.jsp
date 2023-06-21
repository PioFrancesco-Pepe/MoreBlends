<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/validate.js"></script>
<link rel="stylesheet" 
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="../styles/ar.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
<title>Accedi</title>
</head>
<body>
<%@ include file="../fragments/header.jsp"%>
</form>
<%
	if(request.getSession().getAttribute("popupR")!=null && (int)request.getSession().getAttribute("popupR") == 1){
		out.write("<div class=\"overlay\" id=\"pop\"><div class=\"popup\"><p>Registrazione effettuata ora puoi accedere.</p><span id=\"close\">X</span></div></div>");
		request.getSession().removeAttribute("popupR");	
	}
	if(request.getSession().getAttribute("popupE")!=null && (int)request.getSession().getAttribute("popupE") == 1)
	{
		String s = (String)request.getSession().getAttribute("error");
		if(s!=null)
		{
			out.write("<div class=\"overlay\" id=\"pop\"><div class=\"popup\"><p>"+s+"</p><span id=\"close\">X</span></div></div>");
			request.getSession().removeAttribute("popupE");	
		}
	}
	%>
<h3>Accedi</h3>
<div id="blocco">
<form id="logForm" action="../Login" method="post">
			<fieldset>
				<legend>Informazioni</legend>
				<div>
					<label for="email">Email:</label><input type="email" name="email"
						required
						onchange="validateFormElem(this, document.getElementById('errorEmail'), emailErrorMessage)"
						id="email"><span id="errorEmail"></span>
				</div>
				<div>
					<label for="password">Password:</label><input type="password"
						name="password" required
						pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
						onchange="validateFormElem(this, document.getElementById('errorPassword'), passwordErrorMessage)"
						id="password"><span id="errorPassword"></span>
				</div>
				<hr>
				<div id="pulsanti">
					<input type="submit" value="Accedi" onclick="return validate()">
					<input type="reset" value="Reset">
				</div>
			</fieldset>
		</form>
	</div>
<%@ include file="../fragments/footer.jsp"%>
<script src="../scripts/popup.js"></script>
</body>
</html>