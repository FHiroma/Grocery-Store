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
		<h3>Precio: ${productos.precio}</h3>
		<h3>Stock: ${productos.stock}</h3>
        
		<br>
	</c:forEach>
</body>
</html>