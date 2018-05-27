<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.*,java.util.*" %>


<!DOCTYPE html>
<html>

<head>
    <title>Comparador de precios</title>
</head>

<body>


<h1>Comprarador de precios</h1>
<br/>

<h3>El producto más económico que se ajusta a su búsqueda es:</h3>
<br/>


<form method="post" action="<c:url value="/hello"/> ">
    <table>
        <tr>
        	<td>Producto:</td>
            <td><input type="text" name ="producto" value="<c:out value="${resultado.nombre}"/>" readonly="readonly"/></td>
            
        </tr>
        <c:forEach var="prod" items="${resultado}">     

    		<c:out value="${prod}" /><br/>      

  		</c:forEach>
    
        <tr>
        	<td>Dimensión:</td>
            <td><input type="text" value="<c:out value="${palabra}"/>"  readonly="readonly" id="dimension"></td>
        </tr>
         <tr>
         	<td>Precio más económico:</td>
            <td><input type="text" value="<c:out value="${num_dimensiones}"/>"  readonly="readonly" id="dimension"></td>
        </tr>
       
       	

    </table>
</form>


</body>

</html>