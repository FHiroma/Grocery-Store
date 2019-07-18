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

	<c:if test="${not empty pedido}">
		<h4>Productos Con Stock Minimo</h4>
			<table>
			<form:form action="organizarPedidos" method="POST" modelAttribute="pedido">
	  		<c:forEach items="${pedido.pp}" varStatus="status">
	  		<form:hidden path="pp[${status.index}].producto"/>
    		<tr>
      			<td><h4>${pp[${status.index}].notificacion.descripcion}</h4></td>
      			<td>
      			<form:select path="pp[${status.index}].proveedor">
      					<form:option value="NONE">--Selecciona--</form:option>
     					<c:forEach items="${proveedores}" var="proveedor">
		    			<form:option value="${proveedor.id}">${proveedor.descripcion}</form:option>
	  					</c:forEach>
	   			</form:select>
	   			</td>
	   			<td><form:input path="pp[${status.index}].cantidad" type="number" id="numero"/></td>
    		</tr>
  			</c:forEach>
			</table>
			<button class="btn btn-lg btn-primary btn-block" value="Realizar pedidos" Type="Submit"/>Realizar pedidos</button>
			</form:form>
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