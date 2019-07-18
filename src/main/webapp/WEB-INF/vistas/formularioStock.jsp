<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<t:styleHeader></t:styleHeader>
</head>
<body>
	<t:header></t:header>
	<t:nav></t:nav>
	<div class="container" style="width: 60%;">
		<div class="section-title text-center">
			<h3 class="title">Insertar Stock De La Compra Del Producto</h3>
		</div>
		<form:form action="guardarStock" method="POST" modelAttribute="stock">
			<div class="form-group input-group">
				<div class="input-group-prepend"> 
				<span class="input-group-text"> <i class="fa fa-info"></i></span>
				</div>
				<form:input path="stock" id="stock" type="number" placeholder="Stock" class="form-control" />
			</div>
			<input name="id" id="id" type="hidden" value="${productoId}" />
			<button class="btn btn-lg btn-primary btn-block" Type="Submit">Insertar
				Stock</button>
		</form:form>
	</div>
	<t:footer>
	</t:footer>
</body>
</html>