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
					<div class="section-title">
					<div class="col-md-12 order-details">
						<div class="section-title text-center">
							<h3 class="title">Orden De Compra</h3>
						</div>

						<div class="order-summary">
						<div class="section-title">
							<h4>Pedidos Nuevos</h4>
						</div>
						<c:forEach items="${ordenCompra}" var="orden">
							<div class="order-products">
								<div class="col-md-5 order-col">

									<div>${orden.proveedor.descripcion}</div><br>
									<div>${orden.fecha}</div><br>
									<div>${orden.estado}</div><br>
									<div>
        								<form action="verDetalleDeOrden" method="post">
  											<button type="submit" name="id" value="${orden.id}" class="btn btn-primary btn-block">Ver detalles de Orden</button>
										</form>
    								</div>
									<div>
										<c:if test="${orden.estado eq true}">
		        							<h4><form action="emitirOrdenDeCompra" method="post">
    											<button type="submit" name="id" value="${orden.id}" class="btn btn-primary btn-block">Confirmar Orden</button>
												</form>
											</h4>	        
   										</c:if> 								
    								</div>
								</div>
							</div>
						</c:forEach>

						</div>
				</div>
				</div>
	
</body>
</html>