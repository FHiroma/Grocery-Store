<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
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
	<div class="container" style="width:50%;">
				<div class="section-title text-center">
					<h3 class="title">Direccion de Envio</h3>
				</div>
	<form:form action="verificar-direccion" method="POST" modelAttribute="direccion">
	<div class="form-group input-group">
				    	<div class="input-group-prepend">
						    <span class="input-group-text"><i class="fa fa-list"></i></span>
						</div>
						<select name="idLocalidad" class="form-control">
						<c:forEach items="${localidades}" var="localidad">
		        			<option value="${localidad.id}">${localidad.descripcion}</option>
  						</c:forEach>
						</select>
					</div>
					<div class="form-group input-group">
				    	<div class="input-group-prepend">
						    <span class="input-group-text"> <i class="fa fa-info"></i> </span>
						 </div>
				        <form:input path="calle" type="text" placeholder="Calle" class="form-control" />
				    </div>
					<div class="form-group input-group">
				    	<div class="input-group-prepend">
						    <span class="input-group-text"> <i class="fa fa-info"></i> </span>
						 </div>
				        <form:input path="numero" type="number" placeholder="Numero" class="form-control" />
				    </div>
		<button class="btn btn-lg btn-primary" Type="Submit">Direccion</button>
	</form:form>
	</div>
</body>
</html>