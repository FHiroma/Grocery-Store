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

	<c:forEach items="${carritos}" var="carrito">
		<h3>${carrito.direccion.localidad.descripcion }</h3>
		<h3>${carrito.direccion.calle }</h3>
		<h3>${carrito.direccion.numero }</h3>
		<c:if test="${carrito.estado == null}">
		        <h4><a href="http://localhost:8080/grocery-store/detalle-carrito?id=${carrito.id}"><button>Detalle</button></a></h4>
        </c:if>
	</c:forEach>

</body>
</html>