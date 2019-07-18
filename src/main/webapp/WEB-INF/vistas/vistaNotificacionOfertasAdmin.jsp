<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<div class="container" style="width:60%;">
	<c:if test="${!empty pedido}">
		<div class="section-title text-center">
			<h3 class="title">Productos Con Poco Stock</h3>
		</div>
			<table>
			<form:form action="organizarPedidos" method="POST" modelAttribute="pedido">
	  		<c:forEach items="${pedido.pp}" varStatus="status">
	  		<form:hidden path="pp[${status.index}].producto"/>
    		<tr>
      			<td><h4>pp[${status.index}].notificacion.descripcion</h4></td>
      			<td>
      			<form:select path="pp[${status.index}].proveedor">
      					<form:option value="NONE">--Selecciona--</form:option>
     					<c:forEach items="${proveedores}" var="proveedor">
		    			<form:option value="${proveedor.id}">${proveedor.descripcion}</form:option>
	  					</c:forEach>
	   			</form:select>
	   			</td>
	   			<td><form:input path="pp[${status.index}].cantidad" type="number" id="numero" placeholder="Cantidad"/></td>
    		</tr>
  			</c:forEach>
			</table>
			<button class="btn btn-lg btn-primary btn-block" value="Realizar pedidos" Type="Submit">Realizar pedidos</button>
			</form:form>
	</c:if>	

	<c:if test="${not empty NotificacionProductosVencidos}">
		<div class="section-title text-center">
			<h3 class="title">Productos Vencidos</h3>
		</div>
		<c:forEach items="${NotificacionProductosVencidos}" var="notificacion">

			<div class="row">
				<div class="col-md-2">
					<c:out value="${notificacion.descripcion}" />
				</div>
				<div class="col-md-2">
					<c:out value="${notificacion.producto.descripcion}" />
				</div>

			</div>
			<br>
		</c:forEach>
	</c:if>

	<c:if test="${not empty NotificacionProductoEnOferta}">
		<div class="section-title text-center">
			<h3 class="title">Productos En Oferta</h3>
		</div>
		<c:forEach items="${NotificacionProductoEnOferta}" var="notificacion">

			<div class="row">
				<div class="col-md-2">
					<c:out value="${notificacion.descripcion}" />
				</div>
				<div class="col-md-2">
					<c:out value="${notificacion.producto.descripcion}" />
				</div>

			</div>
			<br>
		</c:forEach>
	</c:if>
	</div>
	<t:footer>
	</t:footer>
</body>
</html>