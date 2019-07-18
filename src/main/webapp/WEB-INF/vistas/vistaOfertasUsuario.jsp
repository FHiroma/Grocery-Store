<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Grocery-Store</title>
<t:styleHeader></t:styleHeader>
</head>
<body>
	<t:header user="${usuario}"></t:header>
	<t:nav></t:nav>
<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
				<c:if test="${not empty listaOfertas}">
						<h4>Ofertas Disponibles:</h4>
						<br>
					</c:if>
					<!-- Products tab & slick -->
					<div class="col-md-12 d-flex">
						<div class="row">
							<div class="products-tabs">
								<!-- tab -->
								<div id="tab1" class="tab-pane active">
									<div class="products-slick" data-nav="#slick-nav-1">
									<c:forEach  items="${listaOfertas}" var="productos">
										<!-- product -->
										<div class="product">
											<a href="producto?producto=${productos.descripcion}">
											<div class="product-img">
												<img src="<%= request.getContextPath() %>${productos.imagen}">
												<c:if test="${productos.oferta eq true}">
												<div class="product-label">
													<span class="new">Oferta</span>
												</div>
												</c:if>
											</div>
											</a>
											<div class="product-body">
												<p class="product-category">${productos.categoria.descripcion}</p>
												<h3 class="product-name">  ${productos.descripcion} </h3>
												<h3 class="product-stock">  ${productos.stockDeOferta} </h3>
												<h4 class="product-price"> ${productos.precio} <del class="product-old-price">$990.00 si es una oferta!!!</del> </h4>
												<div class="product-rating">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
												</div>
												<div class="product-btns">
													<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
													<button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
													<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
												</div>
											</div>
											<div class="add-to-cart">
												<button class="add-to-cart-btn"><a href="http://localhost:8080/grocery-store/agregar-carrito?id=${productos.id}"><i class="fa fa-shopping-cart"></i>agregar al carrito</a></button>
											</div>
										</div>
										<!-- /product -->
									</c:forEach>
									</div>
									<div id="slick-nav-1" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
							</div>
						</div>
					</div>
					<!-- Products tab & slick -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->
	<t:footer>
	</t:footer>
	
</body>
</html>