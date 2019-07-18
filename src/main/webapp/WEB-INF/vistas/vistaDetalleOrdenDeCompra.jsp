<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalles de Orden</title>
<t:styleHeader></t:styleHeader>
</head>
<body>
	<t:header user="${usuario}"></t:header>
	<t:nav></t:nav>
<!-- Order Details -->
					<div class="section-title">
					<div class="col-md-12 order-details">
						<div class="section-title text-center">
							<h3 class="title">Orden de Compra para ${ordenCompra.proveedor.descripcion}</h3>
						</div>

						<div class="order-summary">
						<div class="section-title">
							<h4>Productos a pedir</h4>
						</div>
						<c:forEach items="${list}" var="orden">
							<div class="order-products">
								<div class="col-md-5 order-col">

									<div><c:out value="${orden.producto.descripcion}" /></div>
									<div><c:out value="${orden.cantidad}" /></div>
								</div>
							</div>
						</c:forEach>
						<c:if test="${ordenCompra.estado eq true}">
		        							<h4><form action="confirmarOrdenDeCompra" method="post">
    											<button type="submit" name="id" value="${ordenCompra.id}" class="btn btn-primary btn-block">Confirmar Orden</button>
												</form>
											</h4>	        
   										</c:if> 
						</div>
					</div>
				</div>
	
</body>
</html>