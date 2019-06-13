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

	<c:if test="${not empty listaOfertas}">
		<h4>Ofertas Disponibles:</h4>
		<br>
	</c:if>	

	<table>
  		<c:forEach items="${listaOfertas}" var="compra">
    		<tr>
      			<td><c:out value="${compra.producto.descripcion}" /></td>
      			<br>
      			<td><c:out value="${compra.producto.precio}" /></td>
    		</tr>
  		</c:forEach>
	</table>

</body>
</html>