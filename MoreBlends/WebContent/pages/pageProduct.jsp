<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*,control.*,model.*, java.lang.*"%>
<!DOCTYPE html>
<html lang="it">
<%
int idproduct = Integer.parseInt((String) request.getSession().getAttribute("idproduct"));
int flag = 0;
Prodotto item = new Prodotto();
Collection<?> model = (Collection<?>) request.getAttribute("prodotti");
Cart cart = (Cart) request.getSession().getAttribute("cart");

if(model == null){
	request.getRequestDispatcher("../getProdotto?current=4").forward(request, response);
	return;
}

if (model != null && model.size() > 0) {
	Iterator<?> it = model.iterator();
	while (it.hasNext() && flag!=1) {
		item = (Prodotto) it.next();
		if (item.getId() == idproduct)
			flag=1;
	}
}
int i=-1;
if(cart!=null)
{
	Iterator<Prodotto> iterP= cart.getProducts().iterator();
	while(iterP.hasNext()){
		i++;
		Prodotto p = iterP.next();
		if(p.getId()== idproduct);
			break;
	}
}	
if(i!=-1 && item.getQuantita()-cart.getQuantita().get(i)==0)
	response.sendRedirect("/MoreBlends");
if(flag==0)
	response.sendRedirect("/MoreBlends");

%>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<script src="../scripts/setSrcOther.js"></script>
<script src="../scripts/redirect.js"></script>
<link rel="stylesheet" 
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" href="../styles/header.css" type="text/css">
<link rel="stylesheet" href="../styles/style.css" type="text/css">
<link rel="stylesheet" href="../styles/footer.css" type="text/css">
<link rel="stylesheet" href="../styles/style2.css" type="text/css">
<title><%=item.getNome() %></title>
</head>
<body>
	<%@ include file="../fragments/header.jsp"%>
	</form>
	<%
	int popup=0;
	if(request.getSession().getAttribute("popup")!=null)
		popup=(int)request.getSession().getAttribute("popup");
	if(popup == 1){
		out.write("<div class=\"overlay\" id=\"pop\"><div class=\"popup\"><p>Prodotto aggiunto al carello</p><span id=\"close\">X</span></div></div>");
		request.getSession().removeAttribute("popup");
	}
	%>
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
								Prezzo di vendita&nbsp;&nbsp;&nbsp;<%=item.getPrezzoVendita()%>&euro;
							</p>
						<%
						int quantita=item.getQuantita();
							if(i!=-1)
								quantita-=cart.getQuantita().get(i);
						%>	
							<form method="get" action="../cart">
							<label>Quantità <input type="number" id="quantita" name="quantita"
								min="1" max="<%=quantita%>"> <input type="submit" id="AddCart" value="Aggiungi al carrello">
							</label>	
							<input type="hidden" id="IDProduct" name="id" value="<%=item.getId()%>">
							<input type="hidden" id="AddCart" name="action" value="addC">
							</form>
							<div id="desc"><p id="descrizione"><%=item.getDescrizioneAmpia()%></p></div>
						</div> <!--DIV inrow-->
					</div> <!--DIV row-->
				</div> <!--DIV NameProduct-->
			</div> <!--DIV table-->
		</div><!--DIV prova-->
	</div> <!--DIV Container-->
	<%@ include file="../fragments/footer.jsp"%>
<script src="../scripts/popup.js"></script>
</body>
</html>