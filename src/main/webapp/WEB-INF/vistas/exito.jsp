<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exito</title>
<t:styleHeader></t:styleHeader>
</head>
	<body>
		<t:header>
		</t:header>
		<t:nav>
		</t:nav>
		
		<c:if test="${usuario.rol eq 'admin'}">
		<c:if test="${not empty exito}">
			        <h4><span>${exito}</span></h4>
			        <br>
		</c:if>
		<div class="container" style="width:60%;">
		<a href="http://localhost:8080/grocery-store/homeAdmin"> <button class="btn btn-primary btn-block"> Continuar </button></a>
		</div>
		</c:if>
		<br>
		
		<t:footer>
		</t:footer>
				<!-- jQuery Plugins -->
		<script src="../js/template/jquery.min.js"></script>
		<script src="../js/template/bootstrap.min.js"></script>
		<script src="../js/template/slick.min.js"></script>
		<script src="../js/template/nouislider.min.js"></script>
		<script src="../js/template/jquery.zoom.min.js"></script>
		<script src="../js/template/main.js"></script>
		
</body>
</html>