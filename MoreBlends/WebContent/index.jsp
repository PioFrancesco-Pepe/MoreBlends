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
request.getSession().setAttribute("prodotti", model);
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<script src="./scripts/setSrcIndex.js"></script>
<script src="./scripts/redirect.js"></script>
<meta name="viewport" content="initial-scale=1, width=device-width">
<link rel="stylesheet" href="./styles/header.css" type="text/css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
					<a href="#"><img
						src="./images/AllSystem/cialde-cat-e1634101288563-300x300.webp"></a>
				</div>
				<div class="grid-container">
					<div class="grid-item">
						<a href="#"><img
							src="./images/AllSystem/lavazza-a-modo-mio-cat-100x100.webp"></a>
					</div>
					<div class="grid-item">
						<a href="#"><img
							src="./images/AllSystem/dolce-gusto-cat-100x100.webp"></a>
					</div>
					<div class="grid-item">
						<a href="#"><img
							src="./images/AllSystem/espresso-point-cat-100x100.webp"></a>
					</div>
					<div class="grid-item">
						<a href="#"><img
							src="./images/AllSystem/nespresso-cat-100x100.webp"></a>
					</div>
					<div class="grid-item">
						<a href="#"><img
							src="./images/AllSystem/iperespresso-cat-100x100.webp"></a>
					</div>
					<div class="grid-item">
						<a href="#"><img src="./images/AllSystem/bialetti100x100.webp"></a>
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
		%>
		<div class="responsive">
			<div class="gallery">
				<a href="./redirectNewProduct?id=<%=p.get(0).getId()%>"> <img alt="image" class="newProduct"
					src="./getImage?id=<%=p.get(0).getId()%>">
				</a>
				<div class="desc"><%=p.get(0).getNome()%>&nbsp;&nbsp;&nbsp;<%=p.get(0).getPrezzoVendita()%>&euro;
				</div>
			</div>
		</div>

		<div class="responsive">
			<div class="gallery">
				<a href="./redirectNewProduct?id=<%=p.get(1).getId()%>"> <img alt="image" class="newProduct"
					src="./getImage?id=<%=p.get(1).getId()%>">
				</a>
				<div class="desc"><%=p.get(1).getNome()%>&nbsp;&nbsp;&nbsp;<%=p.get(1).getPrezzoVendita()%>&euro;
				</div>
			</div>
		</div>

		<div class="responsive">
			<div class="gallery">
				<a href="./redirectNewProduct?id=<%=p.get(2).getId()%>"> <img alt="image" class="newProduct"
					src="./getImage?id=<%=p.get(2).getId()%>">
				</a>
				<div class="desc"><%=p.get(2).getNome()%>&nbsp;&nbsp;&nbsp;<%=p.get(2).getPrezzoVendita()%>&euro;
				</div>
			</div>
		</div>
		<div class="responsive">
			<div class="gallery">
				<a href="./redirectNewProduct?id=<%=p.get(3).getId()%>"> <img alt="image" class="newProduct"
					src="./getImage?id=<%=p.get(3).getId()%>">
				</a>
				<div class="desc"><%=p.get(3).getNome()%>&nbsp;&nbsp;&nbsp;<%=p.get(3).getPrezzoVendita()%>&euro;
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<h3>Upload photo:</h3>
	<form action="UploadPhoto" enctype="multipart/form-data" method="post">
		Name-surname: <select name="id">
			<%
			if (model != null && model.size() > 0) {
				Iterator<?> it = model.iterator();
				while (it.hasNext()) {
					Prodotto item = (Prodotto) it.next();
			%>
			<option value="<%=item.getId()%>"><%=item.getNome()%>
				<%=item.getPrezzoVendita()%></option>
			<%
				}
			}
			%>
		</select> <br> <input class="file" type="file" name="talkPhoto" value=""
			maxlength="255"> <br> <input type="submit"
			value="Upload"><input type="reset">
	</form>
	<%@ include file="./fragments/footer.jsp"%>
	
</body>

</html>