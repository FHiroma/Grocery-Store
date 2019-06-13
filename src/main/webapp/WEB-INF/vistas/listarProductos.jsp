<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- <a href="http://localhost:8080/proyecto-limpio-spring/logout"> 
				<button>Log out</button> </a> -->

	<c:forEach items="${lista}" var="productos">
		<h3>Descripcion: ${productos.descripcion}</h3>
		<h3>Estado: ${productos.estado}</h3>
		<c:if test="${productos.estado == false}">
		        <h4><a href="http://localhost:8080/grocery-store/publicar-producto?id=${productos.id}"><button>Publicar</button></a></h4>
        </c:if>
        <c:if test="${productos.estado == true}">
		        <h4><a href="http://localhost:8080/grocery-store/quitar-producto?id=${productos.id}"><button>Quitar</button></a></h4>
        </c:if>
        
        <c:if test="${productos.estado == true}">
		        <h4><a href="http://localhost:8080/grocery-store/mostrar-formulario?id=${productos.id}"><button>Formulario</button></a></h4>
        </c:if>
        
		<br>
	</c:forEach>
</body>
</html>