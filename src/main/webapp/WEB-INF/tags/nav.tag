<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<!-- NAVIGATION -->
		<nav id="navigation">
			<!-- container -->
			<div class="container">
				<!-- responsive-nav -->
				<div id="responsive-nav">
					<!-- NAV -->
					<ul class="main-nav nav navbar-nav">
					<c:if test="${usuario.rol != 'admin'}">
						<li class="active"><a href="http://localhost:8080/grocery-store/">Home</a></li>
						<li><a href="http://localhost:8080/grocery-store/ver-productos-en-oferta">Ofertas</a></li>
						<c:forEach items="${listaCategorias}" var="categoria">
						<li><a href="http://localhost:8080/grocery-store/categoria?id=${categoria.id}">${categoria.descripcion }</a></li>
						</c:forEach>
						<li><a href="#">Mi Cuenta</a></li>
					</c:if>
					<c:if test="${usuario.rol == 'admin'}">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#">admin1</a></li>
						<li><a href="#">admin2</a></li>
						<li><a href="#">Mi admin3</a></li>
					</c:if>
					</ul>	
					<!-- /NAV -->
				</div>
				<!-- /responsive-nav -->
			</div>
			<!-- /container -->
		</nav>
		<!-- /NAVIGATION -->
