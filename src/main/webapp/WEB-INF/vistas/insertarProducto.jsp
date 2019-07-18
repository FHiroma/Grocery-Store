<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<t:styleHeader></t:styleHeader>
</head>
<body>
		<t:header  user="${usuario}"></t:header>
		<t:nav></t:nav>
			<div class="container" style="width:60%;">
				<div class="section-title text-center">
					<h3 class="title">Insertar Producto</h3>
				</div>
				<form:form action="guardarProducto" method="POST" modelAttribute="producto" enctype="multipart/form-data">				
					<div class="form-group input-group">
						<div class="input-group-prepend">
						    <span class="input-group-text"> <i class="fa fa-image"></i> </span>
						 </div>
						 <input type="file" accept="image/*" name="file" class="form-control" />
				    </div> 
				    <div class="form-group input-group">
				    	<div class="input-group-prepend">
						    <span class="input-group-text"> <i class="fa fa-info"></i> </span>
						 </div>
				        <form:input path="descripcion" id="descripcion" type="string" placeholder="Descripcion" class="form-control" />
				    </div>
					<div class="form-group input-group">
				    	<div class="input-group-prepend">
						    <span class="input-group-text"> <i class="fa fa-info"></i> </span>
						 </div>
				        <form:input path="precio" id="precio" type="integer" placeholder="Precio" class="form-control" />
				    </div>
				    <div class="form-group input-group">
				    	<div class="input-group-prepend">
						    <span class="input-group-text"> <i class="fa fa-info"></i> </span>
						 </div>
				        <form:input path="diasCaducidad" id="diasCaducidad" placeholder="Dias de caducidad" type="Integer" class="form-control" />
				    </div>
					<div class="form-group input-group">
				    	<div class="input-group-prepend">
						    <span class="input-group-text"><i class="fa fa-list"></i></span>
						</div>
						<select name="idCategoria" class="form-control">
						<c:forEach items="${categorias}" var="categoria">
		        			<option value="${categoria.id}">${categoria.descripcion}</option>
  						</c:forEach>
						</select>
					</div>
					<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Insertar</button>
				</form:form>
				<a href="#" onclick="history.go(-1)"> <button class="btn btn-lg btn-primary btn-block">Atras</button> </a>		
			</div>
			
	<t:footer>
	</t:footer>
</body>
</html>