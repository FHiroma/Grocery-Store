<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Grocery-Store</title>
<t:styleHeader></t:styleHeader>
</head>
<body>
	<t:header user="${usuario}"></t:header>
	<t:nav></t:nav>
	<div class="billing-details">
							<div class="section-title text-center">
								<h3 class="title">Billing address</h3>
							</div>
							<div class="row" style="width:50%; margin: 0 auto !important"">
								<div>
								<div class="form-group">
									<input class="input" type="text" name="Email" value="${usuario.email}" disabled>
								</div>
								<div class="form-group">
									<input class="input" type="text" name="nombre" value="${usuario.nombre}" disabled>
								</div>
								<div class="form-group">
									<input class="input" type="email" name="apellido" value="${usuario.apellido}" disabled">
								</div>
								<div class="form-group">
									<input class="input" type="text" name="address" placeholder="Address">
								</div>
								<div class="form-group">
									<input class="input" type="text" name="city" placeholder="City">
								</div>
								<div class="form-group">
									<input class="input" type="tel" name="tel" placeholder="Telephone">
								</div>
								</div>
							</div>
						</div>
						
	<t:footer>
	</t:footer>
</body>
</html>