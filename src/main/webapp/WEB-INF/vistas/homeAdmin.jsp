<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	</head>
	<body>
		<div class = "container">
			<h1>Bienvenidos Admin</h1>
		</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<h3>
			<!-- <a href="http://localhost:8080/proyecto-limpio-spring/logout"> 
				<button>Log out</button> </a> -->
				
			<c:if test="${listaNotificaciones.size() > 0}">
				<h4>Notificaciones: ${listaNotificaciones.size()}</h4>
				<a href="http://localhost:8080/grocery-store/consultarNotificaciones">notificaciones</a>
			<br><br>
			</c:if>
			
			
			<a href="http://localhost:8080/grocery-store/listarProductos">Listar Productos</a>
			<br><br>
			<a href="http://localhost:8080/grocery-store/insertarProducto">Insertar Producto</a>
			
		</h3>
		
	</body>
</html>