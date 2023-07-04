<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.Cliente"%>
<%
	Cliente c = (Cliente)request.getSession().getAttribute("currentUtente");
	if(c == null)
		response.sendRedirect("./accedi.jsp");
	else{
%>   
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<title>Profilo</title>
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/redirect.js"></script>
<script src="../scripts/jquery-3.7.0.js" charset="utf-8"></script>
<link rel="stylesheet" href="../styles/all.min.css">
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="../styles/profilo.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
</head>
<body>
<%@ include file="../fragments/header.jsp"%>
<%
	int popup=0;
	if(request.getSession().getAttribute("popupS")!=null)
		popup=(int)request.getSession().getAttribute("popupS");
	if(popup == 1){
		out.write("<div class=\"overlay\" id=\"pop\"><div class=\"popup\"><p>"+request.getSession().getAttribute("successo")+"</p><span id=\"close\">X</span></div></div>");
		request.getSession().removeAttribute("popupS");
	}
	%>
 <div class="profile">
    <h1><%=c.getNome()+" "+c.getCognome()%></h1>
    <p>Benvenuto nel tuo profilo utente di MoreBlends. Qui puoi gestire le tue informazioni personali e gli ordini effettuati.</p>
     <a href="./insertOtherTelefono.jsp" class="btn">Inserisci un altro Numero di telefono</a>
     <a href="./editProfile.jsp" class="btn">Modifica Email e/o Password</a>
    <a href="./insertOtherIndirizzo.jsp" class="btn">Inserisci un altro Indirizzo</a>
    <a href="./viewOrders.jsp" class="btn">I Miei Ordini</a>
    <a href="../logout" class="btn">Logout</a>
  </div>
  <%@ include file="../fragments/footer.jsp"%>
  <script src="../scripts/popup.js"></script>
</body>
</html>
<% }%>