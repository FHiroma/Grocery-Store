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
							<h3 class="title">Lista De Productos</h3>
						</div>

						
					
							
						<c:forEach items="${lista}" var="productos">
							
							<div class="row">
  								<div class="col-md-2"><c:out value="${productos.descripcion}" /></div>
  								<div class="col-md-2"><c:out value="${productos.estado}" /></div>
        						<div class="col-md-2">
									<c:if test="${productos.estado == false}">
											<a href="http://localhost:8080/grocery-store/publicar-producto?id=${productos.id}" class="btn btn-primary"> <i class="fa fa-close">
    										Publicar</i></a>
									</c:if>
    							</div>
    							<div class="col-md-2">
									<c:if test="${productos.estado == true}">
											<a href="http://localhost:8080/grocery-store/quitar-producto?id=${productos.id}" class="btn btn-danger"> <i class="fa fa-close">
    										Quitar</i></a>
									</c:if>
    							</div>
    							<div class="col-md-2">
									<c:if test="${productos.estado == true}">
											<a href="http://localhost:8080/grocery-store/mostrar-formulario?id=${productos.id}" class="btn btn-primary"> <i class="fa fa-close">
    										Formulario</i></a>
									</c:if>
    							</div>
							</div>	
							<br>
						</c:forEach>		
									
					</div>
					<!-- /Order Details -->
</body>
</html>