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
				<h2>Carrito de compras</h2>
				
			    		
		<table border="1">
                <thead>
                <th>Descripcion</th>
                <th>Precio</th>
                <th>Cantidad</th>  
                </thead>
                <c:forEach items="${carrito}" var="detalle">
                <tbody>               
                <tr>
                   <td>${detalle.producto.descripcion}</td>
                   <td>${detalle.producto.precio}</td>
                   <td>${detalle.cantidad}</td>
                   <td></td>
                </tr>                
                </tbody>  
                </c:forEach>         
            </table>
					
	
</body>
</html>