<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="#" onclick="history.go(-1)">
	<button>Atras</button> </a>
	<!-- <a href="http://localhost:8080/proyecto-limpio-spring/logout"> 
				<button>Log out</button> </a> -->
	<table>
  		<c:forEach items="${lista}" var="notificacion">
    		<tr>
      			<td><c:out value="${notificacion.descripcion}" /></td>
      			<br>
      			<td><c:out value="${notificacion.producto.descripcion}" /></td>
    		</tr>
  		</c:forEach>
	</table>
	
</body>
</html>