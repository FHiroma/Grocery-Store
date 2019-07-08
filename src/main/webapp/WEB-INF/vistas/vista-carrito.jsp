<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<t:styleHeader></t:styleHeader>
</head>
<body>
	<t:header></t:header>
	<t:nav></t:nav>
	
	<!-- Order Details -->
					<div class="col-md-5 order-details">
						<div class="section-title text-center">
							<h3 class="title">Your Order</h3>
						</div>

						<c:forEach items="${carrito}" var="detalle">
						<div class="order-summary">
							<div class="order-col">
								<div><strong>PRODUCT</strong></div>
								<div><strong>TOTAL</strong></div>
							</div>
							<div class="col-md-5 order-products">
								<div class="order-col">

									<div><c:out value="${detalle.producto.descripcion}" /></div>
									<div><c:out value="${detalle.subtotal}" /></div>
									<div>
										<form  action="modificar-cantidad-producto" method="GET">
											<input type="hidden" name="id" value="${detalle.producto.id}" required="required"> 
											<input type="number" name="cantidad" value="${detalle.cantidad}" required="required">
											<input type="submit" value="cantidad">
										</form>
									</div>
									<div> 
    								<a href="http://localhost:8080/grocery-store/eliminar-producto-carrito?id=${detalle.producto.id}" class="primary-btn order-submit">EliminarProductos</a>
    								</div>

								</div>
							</div>
							<div class="order-col">
								<div>Shiping</div>
								<div><strong>FREE</strong></div>
							</div>
							<div class="order-col">
								<div><strong>TOTAL</strong></div>
								<div><strong class="order-total">$2940.00</strong></div>
							</div>
						</div>
						</c:forEach>

						<div class="payment-method">
							<div class="input-radio">
								<input type="radio" name="payment" id="payment-1">
								<label for="payment-1">
									<span></span>
									Direct Bank Transfer
								</label>
								<div class="caption">
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
								</div>
							</div>
							<div class="input-radio">
								<input type="radio" name="payment" id="payment-2">
								<label for="payment-2">
									<span></span>
									Cheque Payment
								</label>
								<div class="caption">
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
								</div>
							</div>
							<div class="input-radio">
								<input type="radio" name="payment" id="payment-3">
								<label for="payment-3">
									<span></span>
									Paypal System
								</label>
								<div class="caption">
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
								</div>
							</div>
						</div>
						<div class="input-checkbox">
							<input type="checkbox" id="terms">
							<label for="terms">
								<span></span>
								I've read and accept the <a href="#">terms & conditions</a>
							</label>
						</div>
								<c:if test="${empty carrito}">
			       					<h4>
			       						<a href="http://localhost:8080/grocery-store/" class="primary-btn order-submit">Seguir Comprando</a>
    								</h4>
								</c:if>
						<a href="#" class="primary-btn order-submit">Place order</a>
					</div>
					<!-- /Order Details -->
	
	<t:footer>
	</t:footer>
</body>
</html>