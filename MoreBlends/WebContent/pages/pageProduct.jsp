<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*,control.*,model.*, java.lang.*"%>
<!DOCTYPE html>
<html>
<%
int idproduct;
if((String)request.getSession().getAttribute("idproduct") == null)
	response.sendRedirect("../index.jsp");
else{
idproduct= Integer.parseInt((String) request.getSession().getAttribute("idproduct"));
Prodotto item = new Prodotto();
boolean exit = true;

Collection<?> model = (Collection<?>) request.getSession().getAttribute("prodotti");

if (model != null && model.size() > 0) {
	Iterator<?> it = model.iterator();
	while (it.hasNext() && exit != false) {
		item = (Prodotto) it.next();
		if (item.getId() == idproduct)
	exit = false;
	}
}


	
%>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<script src="../scripts/setSrcOther.js"></script>
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="../styles/style.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
<link rel="stylesheet" href="../styles/style2.css" type="text/css">
<title><%=item.getNome() %></title>
</head>
<body>
	<%@ include file="../fragments/header.jsp"%>
	<div class="Container">
		<div id="prova">
			<div class="polaroid">
				<img src="../getImage?id=<%=item.getId()%>" alt="image">
				<div class="container">
					<h3><%=item.getDescrizione()%></h3>
				</div>
			</div>
			<div id="vuoto"></div>
			<div id="table">
				<div id="NameProduct">
					<h1><%=item.getNome()%></h1>
					<div id="row">
						<div id="inrow">
							<p>
								Prezzo di vendita&nbsp;&nbsp;&nbsp;<%=item.getCosto()%>&euro;
							</p>

							<label>Quantità <input type="number" id="quantita"
								min="1" max="5"> <input type="submit" id="AddCart" value="Aggiungi al carrello">
							</label>
							<div id="desc"><p id="descrizione"><%=item.getDescrizioneAmpia()%></p></div>
						</div> <!--DIV inrow-->
					</div> <!--DIV row-->
				</div> <!--DIV NameProduct-->
			</div> <!--DIV table-->
		</div><!--DIV prova-->
	</div> <!--DIV Container--><%} %>
	<%@ include file="../fragments/footer.jsp"%>
</body>
</html>