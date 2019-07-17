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
							<h3 class="title">Carritos De Comprar</h3>
						</div>

						<div class="order-summary">
						<div class="section-title">
							<h4>Pedidos Nuevos</h4>
						</div>
						<c:forEach items="${carritos}" var="carrito">
							<div class="order-products">
								<div class="col-md-5 order-col">

									<div><c:out value="${carrito.direccion.localidad.descripcion }" /></div>
									<div><c:out value="${carrito.direccion.calle }" /></div>
									<div><c:out value="${carrito.direccion.numero }" /></div>
									<div>
										<c:if test="${carrito.estado == null}">
		        							<h4><a href="http://localhost:8080/grocery-store/detalle-carrito?id=${carrito.id}"><button>Detalle</button></a></h4>	        
   										</c:if> 								
    								</div>
        
									
    								<dir>
    									<c:if test="${not empty error}">
			        						<h4><span>${error}</span></h4>
			        						<br>
		        						</c:if>	
    								</dir>

								</div>
							</div>
						</c:forEach>

						</div>
		<!-- -------------------------------------------------------------------------------------------------------------------------------------------------------- -->
		<div class="order-summary">
						<div class="section-title">
							<h4>Pedidos Confirmados</h4>
						</div>
						<c:forEach items="${carritosConfirmados}" var="carritoConfirmado">
							<div class="order-products">
								<div class="col-md-5 order-col">

									<div><c:out value="${carritoConfirmado.direccion.localidad.descripcion }" /></div>
									<div><c:out value="${carritoConfirmado.direccion.calle }" /></div>
									<div><c:out value="${carritoConfirmado.direccion.numero }" /></div>
									<div>
										<c:if test="${carrito.estado == true}">
		        							<h4><a href="http://localhost:8080/grocery-store/detalle-carrito?id=${carrito.id}"><button>Detalle</button></a></h4>	        
   										</c:if> 								
    								</div>
        
									
    								<dir>
    									<c:if test="${not empty error}">
			        						<h4><span>${error}</span></h4>
			        						<br>
		        						</c:if>	
    								</dir>

								</div>
							</div>
						</c:forEach>

						</div>
		<!-- -------------------------------------------------------------------------------------------------------------------------------------------------------- -->
					</div>
				</div>
					<!-- /Order Details -->
	

</body>
</html>