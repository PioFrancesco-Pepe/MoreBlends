<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.Cliente,model.Ordine,java.util.Collection,java.util.Iterator,java.util.Map"%>
    <%Cliente cliente=(Cliente)request.getSession().getAttribute("currentUtente");
	if (cliente == null || cliente.isAdmin()== 0)
			response.sendRedirect(application.getContextPath());
	else{
			Collection<?> ordini = (Collection<?>)request.getAttribute("ordini");
			Collection<?> status = (Collection<?>)request.getAttribute("status");
			
			
			if(ordini == null)
			{
				request.getRequestDispatcher("../getOrdini").forward(request, response);
				return;
			}
			
			if(status == null)
			{
				request.getRequestDispatcher("../getStatusOrdine").forward(request, response);
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
<link rel="stylesheet" href="../styles/ar.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/redirect.js"></script>
<title>Gestione Ordini</title>
</head>
<body>
	<%@ include file="../fragments/header.jsp"%>
	</form>
<div id="blocco">	
	<form action="../UpdateStatusOrder" method="post">
		<fieldset>
			<legend>Informazioni</legend>
			
				<select name="Ordine">
					<%
					Iterator<?> iterO = ordini.iterator();
					while (iterO.hasNext()) {
						Ordine temp = (Ordine) iterO.next();
					%>
					<option value="<%=temp.getIdOrdine()%>"><%=temp.getIdOrdine() + " " + temp.getDataInserimento()%></option>
					<%
					}
					%>
				</select> <select name="Status">
					<%
					Iterator<?> iterS = status.iterator();
					int i = 1;
					while (iterS.hasNext()) {
						Map<Integer, String> temp = (Map<Integer, String>) iterS.next();
					%>
					<option value="<%=i%>"><%=temp.get(i++)%></option>
					<%
					}
					%>
				</select>
				<hr>
				<div id="pulsanti">
					<input type="submit" value="Aggiorna Status">
					<input type="reset" value="Reset">
				</div>
				<div>
					<span>
					<% if(request.getSession().getAttribute("error")!=null)
					{
						out.write((String)request.getSession().getAttribute("error"));
						request.getSession().removeAttribute("error");
					}	
					%>
					</span>
				</div>
			</fieldset>
		</form>
	</div>
	<%@ include file="../fragments/footer.jsp"%>
</body>
</html>
<%}%>