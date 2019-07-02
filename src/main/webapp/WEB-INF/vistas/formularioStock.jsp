<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="#" onclick="history.go(-1)">
<button>Atras</button> </a>
<h3>Complete el formulario para el Producto:</h3>
<!-- <a href="http://localhost:8080/proyecto-limpio-spring/logout"> 
				<button>Log out</button> </a> -->

		<form:form action="guardarStock" method="POST" modelAttribute="stock">
			<label>Stock:</label>
			<form:input path="stock" id="stock" type="number" class="form-control" />
			<br><br>
			<input name="id" id="id" type="hidden" value="${producto}"/>
			Seleccione Proveedor:
			<br><br>
					<select name="idProveedor" required>
						<c:forEach items="${proveedores}" var="proveedor">
		        			<option value="${proveedor.id}">${proveedor.descripcion}</option>
  						</c:forEach>
					</select>
					<br><br>
			<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Insertar Stock</button>
		</form:form>

</body>
</html>