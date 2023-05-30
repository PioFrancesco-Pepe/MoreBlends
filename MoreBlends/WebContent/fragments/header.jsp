<header class="top">
	<div class="LogoContainer">
		<img id="logoHeader" alt="logo MoreBlends" src="#">
	</div>
</header>

<nav>
	
	<ul>
		<li><a href="<%=application.getContextPath()%>">Home</a></li>
		<li><a
			href="<%=application.getContextPath() + "/pages/catalogo.jsp"%>">Catalogo</a></li>
		<li><a href="#">Profilo</a></li>
	</ul>
	<div class="search-container">
		<form action="./search" method="get" name="Cerca" id="Cerca">
			<input type="text" placeholder="Search.." name="search">
			<button type="submit">
				<i class="fa fa-search"></i>
			</button>
		
	</div>
	<button id="cart" type="button" onclick="redirect()"><i class="fa fa-shopping-cart"></i></button>
	
</nav>