<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<t:styleHeader></t:styleHeader>
</head>
<body>
	<t:header></t:header>
	<t:nav></t:nav>
	<div class = "container">
			<div id="loginbox" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
				<%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
					<%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
				<form:form action="validar-login" method="POST" modelAttribute="usuario">
				    	<h3 class="form-signin-heading">Login</h3>

					<%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
					<form:input path="email" id="email" type="email" class="form-control" />
					<form:input path="password" type="password" id="password" class="form-control"/>     		  
					
					<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Login</button>
				</form:form>
				<br>
				<a href="http://localhost:8080/proyecto-limpio-spring/registro"> 
				<h3 class="form-signin-heading">Registrate</h3> </a>
				
				<%--Bloque que es visible si el elemento error no está vacío	--%>
				<c:if test="${not empty error}">
			        <h4><span>${error}</span></h4>
			        <br>
		        </c:if>	
			</div>
		</div>
	<t:footer>
	</t:footer>
</body>
</html>