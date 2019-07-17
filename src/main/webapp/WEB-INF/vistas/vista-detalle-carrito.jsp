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
	<t:header user="${usuario}"></t:header>
	<t:nav></t:nav>
<!-- Order Details -->
					<div class="col-md-12 order-details">
						<div class="section-title text-center">
							<h3 class="title">Detalle de carrito</h3>
						</div>	
						<c:forEach items="${listaDetalleVenta}" var="detalleVenta">
							<div class="row">
  								<div class="col-md-2"><c:out value="${detalleVenta.producto.descripcion }" /></div>
  								<div class="col-md-2"><c:out value="${detalleVenta.cantidad }" /></div>
  								<div class="col-md-2"><c:out value="${detalleVenta.subtotal }" /></div>
  								<div class="col-md-2"><c:out value="${distanciaYTiempo}" /></div>
							</div>
						</c:forEach>
						<div class="col-md-2">
									<c:if test="${carrito.estado ne true }">
		        							<a href="http://localhost:8080/grocery-store/enviar-carrito?id=${carrito.id}" class="btn btn-primary">Confirmar</a>	        
   									</c:if>
    							</div>
    							<div class="col-md-2">
									<c:if test="${carrito.estado ne true }">
		        							<a href="http://localhost:8080/grocery-store/cancelar-carrito?id=${carrito.id}" class="btn btn-danger">Cancelar</a>	        
   									</c:if>
    							</div>				
					</div>								
					<!-- /Order Details -->
</body>
</html>