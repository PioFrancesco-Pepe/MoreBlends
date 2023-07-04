<%@page import="org.apache.catalina.startup.ClassLoaderFactory.RepositoryType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,control.*,model.*, java.lang.*"%>
<%
Cliente c=(Cliente)request.getSession().getAttribute("currentUtente");
if (c == null || c.isAdmin()== 0)
	response.sendRedirect(application.getContextPath());
else
{
	Report r =(Report)request.getSession().getAttribute("reports");
	
%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<script src="../scripts/jquery-3.7.0.js" charset="utf-8"></script>
<link rel="stylesheet" href="../styles/all.min.css">
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="../styles/report.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/redirect.js"></script>
<script src="../scripts/validateReport.js"></script>
<title>Report</title>
</head>
<body>
	<%@ include file="../fragments/header.jsp"%>
	</form>
	<div id="container-all">
	<form action="../viewReportS" method="get" id="r">
	<fieldset>
		<legend>Report</legend>
		<div id="radio">

			<input type="radio" name="report" id="giornaliero" value="1"><label
				for="giornaliero">Giornaliero</label> <input type="radio"
				name="report" id="mensile" value="2"><label for="mensile">Mensile</label>
			<input type="radio" name="report" id="annuale" value="3"><label
				for="annuale">Annuale</label>
		</div>
		<div id="setDate">
			<div id="divG">
				<label for="dateG">Inserisci un giorno: </label><input type="date"
					name="dateG" id="dateG" onchange="validateFormElem(this, document.getElementById('errorGiorno'),giornoErrorMessage)">
					<span id="errorGiorno"></span>
			</div>
			<div id="divM">
				<label for="mese">Inserisci un mese: </label><select id="mese"
					name="mese">
						<option value="01">Gennaio</option>
						<option value="02">Febbraio</option>
						<option value="03">Marzo</option>
						<option value="04">Aprile</option>
						<option value="05">Maggio</option>
						<option value="06">Giugno</option>
						<option value="07">Luglio</option>
						<option value="08">Agosto</option>
						<option value="09">Settembre</option>
						<option value="10">Ottobre</option>
						<option value="11">Novembre</option>
						<option value="12">Dicembre</option>
				</select>

			</div>
			<div id="divA">
				<label for="anno">Inserisci un anno: </label><input id="anno"
					name="anno" type="text" placeholder="2023" pattern="^\d{4}$" 
					onchange="validateFormElem(this, document.getElementById('errorAnno'),annoErrorMessage)">
					<span id="errorAnno"></span>
			</div>
			<div id="pulsanti">
				<input type="submit" value="Invia" onclick="return validate()"> <input type="reset"
					value="Reset" id="reset">
			</div>

		</div>
		</fieldset>
	</form>
	<% 
	if(r!=null)
	{
			out.write("<div id=\"report\">");
			out.write("<p>Totale prodotti venduti: "+r.getProdottiVenduti()+"</p>");
			out.write("<p>Ricavo: "+r.getRicavo()+"&euro;</p>");
			out.write("<p>Costo: "+r.getCosto()+"&euro;</p>");
			out.write("<p>Guadagno: "+r.getGuadagno()+"&euro;</p></div>");
			request.getSession().removeAttribute("reports");
	}
	%>
	</div>
	<%@ include file="../fragments/footer.jsp"%>
	<script src="../scripts/viewReport.js"></script>
</body>
</html>
<%}%>