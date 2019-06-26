<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery-1.11.3.min.js" /></script>
<script src="js/jquery.autocomplete.js" /></script>
<link href="css/main.css" rel="stylesheet"/>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3> Bienvenido Mail: ${user.email}</h3>
	<a href="http://localhost:8080/grocery-store/homeUser"><button>Home User</button></a>
	<a href="http://localhost:8080/proyecto-limpio-spring/logout"> 
				<button>Log out</button> </a><br><br>
	  <div>
	  <form action="busqueda" method="GET">
	<input type="text"  id="w-input-search" name="busqueda">
	<span>
	  <button id="button-id" type="submit">Search</button>
	</span>
	</form>
  </div>
	<h1>Lista de productos a la venta:</h1>
		<c:forEach items="${listaProductos}" var="productos">
		<h3>Descripcion: ${productos.descripcion}</h3>
		<h3>Stock: ${productos.stock}</h3>
		<br>
	</c:forEach>
	 <script>
  $(document).ready(function() {

	$('#w-input-search').autocomplete({
		serviceUrl: '${pageContext.request.contextPath}/autocompletado',
		paramName: "nombreProducto",
		delimiter: ",",
	   transformResult: function(response) {
		    	
		return {      	
		  //must convert json to javascript object before process
		  suggestions: $.map($.parseJSON(response), function(item) {
		            	
		      return { value: item.descripcion, data: item.descripcion };
		   })
		            
		 };
		        
            }
		    
	 });
				
  });
  </script>
</body>
</html>