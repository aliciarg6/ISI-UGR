<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="java.util.ArrayList"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comparador de Precios</title>

<style type = "text/css">
h1{
font-size: 300%;
text-align: center;
}

h2{
font-size: 200%;
text-align: center;
}

</style>

</head>
<body>

<h1>Comprarador de precios</h1>
<br/>



 <%String nombre= (String)request.getAttribute("nombre");
 ArrayList<String> resultado_final= (ArrayList<String>)request.getAttribute("resultado_final");
 ArrayList<String> capacidades= (ArrayList<String>)request.getAttribute("capacidades");
 ArrayList<String> precios= (ArrayList<String>)request.getAttribute("precios");
 ArrayList<String> marca= (ArrayList<String>)request.getAttribute("marca");
 ArrayList<String> url= (ArrayList<String>)request.getAttribute("url");
 %>
 
<h2>Producto: <% out.print(nombre); %></h2>

        <%
        if(resultado_final.size()>0){
        %>
        <h3>El producto más económico que se ajusta a su búsqueda es:</h3>
		<br/>
        <% 
 			for(int i=0; i<resultado_final.size(); i++){
 		%> 
        
       	<h4>Dimensión: <% out.print(capacidades.get(i)); %> </h4>
   		<h4>Marca: <% out.print(marca.get(i)); %></h4>
        <h4>Precio: <% out.print(precios.get(i)); %> €</h4>
        <a href="<% out.print(url.get(i)); %>">URL</a>
       	<br/>
       	<br/>
       	 <%
       	 	}
 		}else{%>
 			<h3>No se han encontrado productos comunes en los tres supermercado para poder hacer una comparación razonable</h3>
 		<%} %>

  
  <a href="/index.html">Nueva Búsqueda</a>

</body>
</html>