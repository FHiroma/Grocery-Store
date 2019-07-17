<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registro</title>
<t:styleHeader></t:styleHeader>
</head>
<body>
	<t:header></t:header>
	<t:nav></t:nav>
				<div class="container" style="width:60%;">
				<div class="section-title text-center">
					<h3 class="title">Registrate</h3>
				</div>
				<div class="card bg-light">
				<article class="card-body mx-auto">
					<form:form action="registrarCuenta" method="POST" modelAttribute="usuario">
					<div class="form-group input-group">
						<div class="input-group-prepend">
						    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
						 </div>
						 <form:input path="nombre" id="nombre" type="string" placeholder="Nombre" class="form-control" />
				    </div> <!-- form-group// -->
				    <div class="form-group input-group">
				    	<div class="input-group-prepend">
						    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
						 </div>
				        <form:input path="apellido" id="apellido" type="string" placeholder="Apellido" class="form-control" />
				    </div> <!-- form-group// -->
				    <div class="form-group input-group">
				    	<div class="input-group-prepend">
						    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
						 </div>
				        <form:input path="email" id="email" type="email" placeholder="Email" class="form-control" />
				    </div> <!-- form-group// -->
   				    <div class="form-group input-group">
				    	<div class="input-group-prepend">
						    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
						</div>
				        <form:input path="password" type="password" id="password" placeholder="Contraseña" class="form-control"/> 
				    </div> <!-- form-group// -->  
				    <div class="form-group input-group">
				    	<div class="input-group-prepend">
						    <span class="input-group-text"><i class="fa fa-map-marker"></i></span>
						</div>
						<select name="localidad" class="form-control">
						<c:forEach items="${localidades}" var="localidad">
		        			<option value="${localidad.id}">${localidad.descripcion}</option>
  						</c:forEach>
						</select>
						<input name="calle" id="calle" type="text" placeholder="Calle">
						</input>
						<input name="numero" id="numero" type="number" placeholder="Numero">
						</input>
				    </div> <!-- form-group// -->                            
				    <div class="form-group">
				        <button type="submit" class="btn btn-primary btn-block"> Crear cuenta </button>
				        </form:form>
				    </div> <!-- form-group// -->
				</article>
				</div> <!-- card.// -->
				<!--container end.//-->

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