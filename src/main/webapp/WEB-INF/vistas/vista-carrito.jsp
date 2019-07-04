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
				<h2>Carrito de compras</h2>
	<table>	
		<c:if test="${empty carrito}">
			        <h4>
			    		<a href="http://localhost:8080/grocery-store/"><button>Seguir Comprando</button></a>
    				</h4>
		</c:if>
  		<c:forEach items="${carrito}" var="detalle">
    		<tr>
      			<td><c:out value="${detalle.producto.descripcion}" /></td>
      			<td><a href="http://localhost:8080/grocery-store/disminuir-producto?id=${detalle.producto.id}">
    			<button>-</button></a></td>
      			<td><c:out value="${detalle.cantidad}" /></td>
      			<td><a href="http://localhost:8080/grocery-store/agregar-carrito?id=${detalle.producto.id}">
    			<button>+</button></a></td>
      			<td><c:out value="${detalle.subtotal}" /></td>
    			<td><a href="http://localhost:8080/grocery-store/eliminar-producto-carrito?id=${detalle.producto.id}">
    			<button>EliminarProducto</button></a></td>    			
    			<td><a href="http://localhost:8080/grocery-store/">
    			<button>SeguirComprando</button></a></td>
    		</tr>
  		</c:forEach>
	</table>       
				
</body>
</html>