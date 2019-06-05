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
	<h3> Bienvenido Mail: ${user.email}</h3>
	<a href="http://localhost:8080/grocery-store/homeUser"><button>Home User</button></a>
	
	<h1>Lista de productos a la venta:</h1>
	<br>
		<c:forEach items="${listaProductos}" var="productos">
		<h3>Descripcion: ${productos.descripcion}</h3>
		<h3>Stock: ${productos.stock}</h3>
		<br>
	</c:forEach>
	
</body>
</html>