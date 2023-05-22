<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,control.*,model.*"%>
	
	<%
	Collection<?> model = (Collection<?>)request.getAttribute("prodotti");
	if(model == null) {
		request.getRequestDispatcher("./getProdotto").forward(request, response);
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<link rel="stylesheet" href="./styles/header.css" type="text/css">
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
						<a href="#"><img
							src="./images/AllSystem/bialetti100x100.webp"></a>
					</div>
				</div>
			</div>
		</div>
		<div class="textbar">
			<h3>Nuovi prodotti</h3>
		</div>
		<div class="responsive">
			<div class="gallery">
				<a target="_blank" href="img_5terre.jpg"> <img
					src="./images/img_5terre.jpg" alt="Cinque Terre" width="600" height="400">
				</a>
				<div class="desc">Add a description of the image here</div>
			</div>
		</div>

		<div class="responsive">
			<div class="gallery">
				<a target="_blank" href=""> 
				<img src="./getImage?id=1" width="600" height="400">
				</a>
				<div class="desc">Add a description of the image here</div>
			</div>
		</div>

		<div class="responsive">
			<div class="gallery">
				<a target="_blank" href=""> <img
					src="./images/img_lights.jpg" alt="Northern Lights" width="600" height="400">
				</a>
				<div class="desc">Add a description of the image here</div>
			</div>
		</div>

		<div class="responsive">
			<div class="gallery">
				<a target="_blank" href=""> <img
					src="" alt="Mountains" width="600" height="400">
				</a>
				<div class="desc">Add a description of the image here</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<h3>Upload photo:</h3>
<form action="UploadPhoto" enctype="multipart/form-data" method="post">
	Name-surname:
	<select name="id">
<%
	if(model != null && model.size() > 0) {
		Iterator<?> it = model.iterator(); 
		while(it.hasNext()) {
			Prodotto item = (Prodotto)it.next();
%>	
		<option value="<%=item.getId()%>"><%=item.getNome()%> <%=item.getPrezzo()%></option>
<%
		}
	}	
%>		
	</select>
	<br>
	<input class="file" type="file" name="talkPhoto" value="" maxlength="255">	
	<br>		
	<input type="submit" value="Upload"><input type="reset">
</form>
	<%@ include file="./fragments/footer.jsp"%>
</body>
</html>