<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag import="ar.edu.unlam.tallerweb1.modelo.Productos" %>
<%@tag import="java.util.List"%>
<%@attribute name="list" required="true" type="java.util.List"%>
<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<!-- Products tab & slick -->
					<div class="col-md-12 d-flex">
						<div class="row">
							<div class="products-tabs">
								<!-- tab -->
								<div id="tab1" class="tab-pane active">
									<div class="products-slick" data-nav="#slick-nav-1">
									<c:forEach  items="${list}" var="productos">
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
												<h3 class="product-stock">
												<c:if test="${productos.oferta eq true}">
												  ${productos.stockDeOferta}
												</c:if>
												<c:if test="${productos.oferta ne true}">
												  ${productos.stock}
												</c:if>
												 </h3>
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