<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<form:form action="guardarProducto" method="POST" modelAttribute="producto">

					<label>Descripcion:</label>
					<form:input path="descripcion" id="descripcion" type="string" class="form-control" />
					<br><br>
					<label>Precio:</label>
					<form:input path="precio" id="precio" type="integer" class="form-control" />
					<br><br>
					<label>Dias de caducidad:</label>
					<form:input path="diasCaducidad" id="diasCaducidad" type="Integer" class="form-control" />
					<br><br>
					<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Insertar</button>
				</form:form>

</body>
</html>