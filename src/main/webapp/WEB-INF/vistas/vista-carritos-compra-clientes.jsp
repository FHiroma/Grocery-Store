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
							<h3 class="title">Carritos De Comprar</h3>
						</div>
						<div class="section-title">
							<h4>Pedidos Nuevos</h4>
						</div>
						<c:forEach items="${carritos}" var="carrito">
							<div class="row">
  								<div class="col-md-3"><c:out value="${carrito.direccion.localidad.descripcion }" /></div>
  								<div class="col-md-3"><c:out value="${carrito.direccion.calle }" /></div>
  								<div class="col-md-3"><c:out value="${carrito.direccion.numero }" /></div>
        						<div class="col-md-3">
									<c:if test="${empty carrito.estado }">
										<a href="http://localhost:8080/grocery-store/detalle-carrito?id=${carrito.id}" class="btn btn-primary">Detalle</a>
									</c:if>
    							</div>
							</div>	
						</c:forEach>
						
						<div class="section-title">
							<h4>Carritos de compra confirmados</h4>
						</div>
						<c:forEach items="${carritosConfirmados}" var="carritoConfirmado">
							<div class="row">
  								<div class="col-md-3"><c:out value="${carritoConfirmado.direccion.localidad.descripcion }" /></div>
  								<div class="col-md-3"><c:out value="${carritoConfirmado.direccion.calle }" /></div>
  								<div class="col-md-3"><c:out value="${carritoConfirmado.direccion.numero }" /></div>
        						<div class="col-md-3">
									<c:if test="${carritoConfirmado.estado == true }">
										<a href="http://localhost:8080/grocery-store/detalle-carrito?id=${carritoConfirmado.id}" class="btn btn-primary">Detalle</a>
									</c:if>
    							</div>
							</div>	
						</c:forEach>
					</div>
					<!-- /Order Details -->
</body>
</html>