<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<a href="#" onclick="history.go(-1)">
	<button>Atras</button> </a>
	<br>

	<c:if test="${not empty NotificacionStockMinimo}">
		<h4>Productos Con Stock Minimo</h4>
	<table>
		<form:form action="organizarPedidos" method="POST" modelAttribute="pedido">
  		<c:forEach items="${NotificacionStockMinimo}" var="notificacion" varStatus="status">
  		<input type="hidden" name="pedido[${status.index}].producto" value="${notificacion.notificacion.producto.id}">
    		<tr>
      			<td><c:out value="${notificacion.notificacion.descripcion}" /></td>
      			<td><c:out value="${notificacion.notificacion.producto.descripcion}" /></td>
      			<td><c:out value="${notificacion.notificacion.producto.stock}" /></td>
      			<td>
      				<select  name="pedido[${status.index}].proveedor">
						<c:forEach items="${proveedores}" var="proveedor">
		        			<option value="${proveedor.id}" selected>${proveedor.descripcion}</option>
  						</c:forEach>
   					 </select>
    		</tr>
  		</c:forEach>
  			<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Login</button>
  		</form:form>
	</table>
	</c:if>	
	
		<c:if test="${not empty NotificacionProductosVencidos}">
		<h4>Productos Vencido</h4>
	<table>
  		<c:forEach items="${NotificacionProductosVencidos}" var="notificacion">
    		<tr>
      			<td><c:out value="${notificacion.descripcion}" /></td>
      			<td><c:out value="${notificacion.producto.descripcion}" /></td>
    		</tr>
  		</c:forEach>
	</table>
	</c:if>
		<c:if test="${not empty NotificacionProductoEnOferta}">
		<h4>Productos En Oferta</h4>
	<table>
  		<c:forEach items="${NotificacionProductoEnOferta}" var="notificacion">
    		<tr>
      			<td><c:out value="${notificacion.descripcion}" /></td>
      			<td><c:out value="${notificacion.producto.descripcion}" /></td>
    		</tr>
  		</c:forEach>
	</table>
	</c:if>	
</body>
</html>