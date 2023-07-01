<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, control.* ,model.*, java.lang.*"%>

<%
Collection<?> model = (Collection<?>) request.getAttribute("prodotti");
Collection<?> newProduct = (Collection<?>) request.getAttribute("newProduct");
if (model == null) {
	request.getRequestDispatcher("./getProdotto?current=1").forward(request, response);
	return;
}
if (newProduct == null) {
	request.getRequestDispatcher("./GetNewProduct").forward(request, response);
	return;
}
request.getSession().setAttribute("prodottiV2", model);
%>
<!DOCTYPE html>
<html lang="it">
<head>

<meta charset="ISO-8859-1">
<script src="./scripts/setSrcIndex.js"></script>
<script src="./scripts/redirect.js"></script>
<meta name="viewport" content="initial-scale=1, width=device-width">
<link rel="stylesheet" href="./styles/header.css" type="text/css">
<link rel="stylesheet" 
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" href="./styles/style.css" type="text/css">
<link rel="stylesheet" href="./styles/footer.css" type="text/css">
<title>MoreBlends</title>
</head>
<body>


	<%@ include file="./fragments/header.jsp"%>
	<div class="Container">
		<div id="capsule-cialdeContainer">
			<h1 id="capsule-cialde">Cialde e Capsule Compatibili</h1>
			<p>Su MoreBlends trovi il meglio della selezione del caffè. Tutti
				i migliori marchi di caffè, per i sistemi di macchina da caffè più
				diffusi, a prezzi speciali e con spedizione gratuita. Naviga il sito
				e scopri le capsule compatibili con il tuo sistema espresso o scegli
				le cialde in carta, le più desiderate dagli amanti del caffè
				espresso fatto in casa.</p>
		</div>
		<div id="prova">
			<div id="allSystem">
				<div class="systemFeatured">
					<a href="./search?sistema=1&marca=0&search="><img
						src="./images/AllSystem/cialde-cat-e1634101288563-300x300.webp" alt="Cialda ESE 44mm"></a>
				</div>
				<div class="grid-container">
					<div class="grid-item">
						<a href="./search?sistema=2&marca=0&search="><img
							src="./images/AllSystem/lavazza-a-modo-mio-cat-100x100.webp" alt="Lavazza A Modo Mio"></a>
					</div>
					<div class="grid-item">
						<a href="./search?sistema=6&marca=0&search="><img
							src="./images/AllSystem/dolce-gusto-cat-100x100.webp" alt="Nescafè Dolce Gusto"></a>
					</div>
					<div class="grid-item">
						<a href="./search?sistema=5&marca=0&search="><img
							src="./images/AllSystem/espresso-point-cat-100x100.webp" alt="Lavazza Espresso Point"></a>
					</div>
					<div class="grid-item">
						<a href="./search?sistema=3&marca=0&search="><img
							src="./images/AllSystem/nespresso-cat-100x100.webp" alt="Nespresso"></a>
					</div>
					<div class="grid-item">
						<a href="./search?sistema=7&marca=0&search="><img
							src="./images/AllSystem/iperespresso-cat-100x100.webp" alt="illyespresso"></a>
					</div>
					<div class="grid-item">
						<a href="./search?sistema=4&marca=0&search="><img src="./images/AllSystem/bialetti100x100.webp" alt="Bialetti"></a>
					</div>
				</div>
			</div>
		</div>
		<div class="textbar">
			<h3>Nuovi prodotti</h3>
		</div>
		<%
		List<Prodotto> p = new ArrayList<>();
		if (newProduct != null && newProduct.size() > 0) {
			Iterator<?> i = newProduct.iterator();
			while (i.hasNext())
				p.add((Prodotto) i.next());
		} 
		for(int i=0; i<4; i++){
		%>
		<div class="responsive">
			<div class="gallery">
				<a href="./redirectNewProduct?id=<%=p.get(i).getId()%>"> <img alt="image" class="newProduct"
					src="./getImage?id=<%=p.get(i).getId()%>">
				</a>
				<div class="desc"><%=p.get(i).getNome()%>&nbsp;&nbsp;&nbsp;<%=p.get(i).getPrezzoVendita()%>&euro;
				</div>
			</div>
		</div>
			<%}%>
		
		<div class="clearfix"></div>
	</div>
	<%@ include file="./fragments/footer.jsp"%>
	
</body>

</html>