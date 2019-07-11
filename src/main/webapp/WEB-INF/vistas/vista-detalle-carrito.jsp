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

	<c:forEach items="${listaDetalleVenta}" var="detalleVenta">
		<h3>${detalleVenta.producto.descripcion }</h3>
		<h3>${detalleVenta.cantidad }</h3>
		<h3>${detalleVenta.subtotal }</h3>
	</c:forEach>
	<c:if test="${carrito.estado == null}">
		<h4>
			<a href="http://localhost:8080/grocery-store/enviar-carrito?id=${carrito.id}"><button>Aceptar</button></a>
		</h4>
	</c:if>

	<c:if test="${carrito.estado == null}">
		<h4>
			<a href="http://localhost:8080/grocery-store/cancelar-carrito?id=${carrito.id}"><button>Cancelar</button></a>
		</h4>
	</c:if>

</body>
</html>