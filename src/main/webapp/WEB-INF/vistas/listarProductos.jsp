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
							<h3 class="title">Lista De Productos</h3>
						</div>

						
						<div class="order-summary">
							<div class="col-md-8 order-col">
								<div class="col-md-5"><strong>PRODUCT</strong></div>
								<div><strong>TOTAL</strong></div>
							</div>
						<c:forEach items="${lista}" var="productos">
							<div class="order-products">
								<div class="col-md-7 order-col">
									<div>
									<div><c:out value="${productos.descripcion}" /></div>
									<div><c:out value="${productos.estado}" /></div>
									</div>
									<div>
										<c:if test="${productos.estado == false}">
											<a href="http://localhost:8080/grocery-store/publicar-producto?id=${productos.id}" class="btn btn-danger"> <i class="fa fa-close">
    										Publicar</i></a>
										</c:if>
    								</div>
    								<div>
										<c:if test="${productos.estado == true}">
											<a href="http://localhost:8080/grocery-store/quitar-producto?id=${productos.id}" class="btn btn-danger"> <i class="fa fa-close">
    										Quitar</i></a>
										</c:if>
    								</div>
    								<div>
										<c:if test="${productos.estado == true}">
											<a href="http://localhost:8080/grocery-store/mostrar-formulario?id=${productos.id}" class="btn btn-danger"> <i class="fa fa-close">
    										Formulario</i></a>
										</c:if>
    								</div>	
								</div>
							</div>
						</c:forEach>		
						</div>			
					</div>
				</div>
					<!-- /Order Details -->
</body>
</html>