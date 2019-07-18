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
					<c:if test="${usuario.rol ne 'admin'}">
						<li class="active"><a href="http://localhost:8080/grocery-store/">Home</a></li>
						<li><a href="http://localhost:8080/grocery-store/ver-productos-en-oferta">Ofertas</a></li>
						<c:forEach items="${listaCategorias}" var="categoria">
						<li><a href="http://localhost:8080/grocery-store/categoria?id=${categoria.id}">${categoria.descripcion }</a></li>
						</c:forEach>
						<c:if test="${usuario.rol eq 'user'}">
						<li><a href="MiCuenta">Mi Cuenta</a></li>
						</c:if>
					</c:if>
					<c:if test="${usuario.rol eq 'admin'}">			
								<!-- <a href="http://localhost:8080/proyecto-limpio-spring/logout"> 
				<button>Log out</button> </a> -->	
			<c:if test="${listaNotificaciones.size() > 0}">
			<br>
				<h4>Notificaciones: ${listaNotificaciones.size()}</h4>
			<br>
				<li><a href="http://localhost:8080/grocery-store/consultarNotificaciones">Notificaciones</a></li>
			</c:if>
			<li><a href="http://localhost:8080/grocery-store/listarProductos">Listar Productos</a></li>
			<li><a href="http://localhost:8080/grocery-store/insertarProducto">Insertar Producto</a></li>
			<li><a href="http://localhost:8080/grocery-store/listarCarritosCompraClientes">Carritos Compra</a></li>
					</c:if>
					</ul>	
					<!-- /NAV -->
				</div>
				<!-- /responsive-nav -->
			</div>
			<!-- /container -->
		</nav>
		<!-- /NAVIGATION -->
