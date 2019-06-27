<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="js/jquery-1.11.3.min.js" /></script>
<script src="js/jquery.autocomplete.js" /></script>
<link href="css/main.css" rel="stylesheet"/>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<t:styleHeader></t:styleHeader>
</head>
<body>
	<t:header></t:header>
	<t:nav></t:nav>
	<h1>Lista de productos a la venta:</h1>
		<c:forEach items="${listaProductos}" var="productos">
		<h3>Descripcion: ${productos.descripcion}</h3>
		<h3>Stock: ${productos.stock}</h3>
		<h3><a href="http://localhost:8080/grocery-store/agregar-carrito?id=${productos.id}"><button>agregar al carrito</button></a></h3>
		<br>
	</c:forEach>
	<t:footer>
	</t:footer>
			<!-- jQuery Plugins -->
		<script src="../js/template/jquery.min.js"></script>
		<script src="../js/template/bootstrap.min.js"></script>
		<script src="../js/template/slick.min.js"></script>
		<script src="../js/template/nouislider.min.js"></script>
		<script src="../js/template/jquery.zoom.min.js"></script>
		<script src="../js/template/main.js"></script>
  
</body>
</html>