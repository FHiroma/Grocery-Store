<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Ingrese los datos de la direccion del envio</h1>

	<form:form action="verificar-direccion" method="POST" modelAttribute="direccion">
		<select name="idLocalidad" required>
			<c:forEach items="${localidades}" var="localidad">
		    	<option value="${localidad.id}">${localidad.descripcion}</option>
  			</c:forEach>
		</select>
		<br>
		<br>
		<form:input path="calle" type="text" placeholder="Calle" />
		<br>
		<br>
		<form:input path="numero" type="number" placeholder="Numero" />
		<br>
		<br>
		<button class="btn btn-lg btn-primary btn-block" Type="Submit">Direccion</button>
	</form:form>
</body>
</html>