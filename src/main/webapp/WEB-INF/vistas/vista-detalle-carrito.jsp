<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
					<div class="section-title">
					<div class="col-md-12 order-details">
						<div class="section-title text-center">
							<h3 class="title">Detalle de carrito</h3>
						</div>
						<h3><fmt:formatNumber type="number" maxFractionDigits="0" value="${distanciaTiempo.tiempo}"/> minutos</h3>
						<h3><fmt:formatNumber type="number" maxFractionDigits="2" value="${distanciaTiempo.distancia}"/> Km</h3>
						
						<div class="order-summary">
							
						<c:forEach items="${listaDetalleVenta}" var="detalleVenta">
							<div class="order-products">
								<div class="col-md-5 order-col">

									<div><c:out value="${detalleVenta.producto.descripcion }" /></div>
									<div><c:out value="${detalleVenta.cantidad }" /></div>
									<div><c:out value="${detalleVenta.subtotal }" /></div>
				
								</div>
							</div>
						</c:forEach>
									<div>
										<c:if test="${carrito.estado == null}">
		        							<h4><a href="http://localhost:8080/grocery-store/enviar-carrito?id=${carrito.id}"><button>Confirmar</button></a></h4>	        
   										</c:if> 								
    								</div>
    								<div>
										<c:if test="${carrito.estado == null}">
		        							<h4><a href="http://localhost:8080/grocery-store/cancelar-carrito?id=${carrito.id}"><button>Cancelar</button></a></h4>	        
   										</c:if> 								
    								</div>
 									
						</div>								
					</div>
				</div>
					<!-- /Order Details -->
	

</body>
</html>