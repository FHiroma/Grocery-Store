<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<t:styleHeader></t:styleHeader>
</head>
<body>
<t:header></t:header>

<!-- Order Details -->
					<div class="section-title justify-content-center">
					<div class="col-md-2">
					</div>
					<div class="col-md-8 text-center order-details">
						<div class="section-title text-center">
							<h3 class="title">Your Order</h3>
						</div>

						
						<div class="order-summary">
							<div class="col-md-8 order-col">
								<div class="col-md-5"><strong>PRODUCT</strong></div>
								<div><strong>TOTAL</strong></div>
							</div>
						<c:forEach items="${carrito}" var="detalle">
							<div class="order-products">
								<div class="col-md-7 order-col">
									<div>
									<div><c:out value="${detalle.producto.descripcion}" /></div>
									<div><c:out value="${detalle.subtotal}" /></div>
									</div>
									<div>
										<form  action="modificar-cantidad-producto" method="GET">
											<input type="hidden" name="id" value="${detalle.producto.id}" required="required"> 
											<input type="number" name="cantidad" value="${detalle.cantidad}" required="required" min="1" pattern="^[0-9]+">
											<input type="submit" class="btn btn-primary" value="Modificar">
										</form>
									</div>
									<div>
    								<a href="http://localhost:8080/grocery-store/eliminar-producto-carrito?id=${detalle.producto.id}" class="btn btn-danger"> <i class="fa fa-close">
    								Eliminar Productos</i></a>
    								</div>
    								<dir>
    										<c:if test="${detalle.producto.id eq producto.id}">
    											<c:if test="${not empty error}">
			        								<h4><span>${error}</span></h4>
			        								<br>
		        								</c:if>	
    										</c:if>	
    								</dir>
								</div>
							</div>
							
						</c:forEach>
						<div class="order-col">
								<div><strong>TOTAL</strong></div>
								<c:set var = "salary" scope = "session" value = "${salary + detalle.subtotal}"/>
								<div><strong class="order-total"><c:out value = "${salary}"/></strong></div>
							</div>
							
						</div>
				
								<c:if test="${empty carrito}">
			       					<h4>
			       						<a href="http://localhost:8080/grocery-store/" class="primary-btn order-submit">Seguir Comprando</a>
    								</h4>
								</c:if>
						<a href="http://localhost:8080/grocery-store/entrega" class="primary-btn order-submit">Continuar</a>
					</div>
				</div>
					<!-- /Order Details -->
</body>
</html>