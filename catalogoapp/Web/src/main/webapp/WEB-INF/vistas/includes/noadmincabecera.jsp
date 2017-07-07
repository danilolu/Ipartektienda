<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>tienda</title>
<link rel="stylesheet" href="${applicationScope.rutaBase}/css/estilo.css" />
<link rel="stylesheet" href="${applicationScope.rutaBase}/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${applicationScope.rutaBase}/bootstrap/datatables.min.css">
  
<script type="text/javascript" charset="utf8" src="${applicationScope.rutaBase}/bootstrap/datatables.min.js"></script>
<link rel="shortcut icon" href="${applicationScope.rutaBase}/img/favicon.png" type="image/png"></head>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form style="background-color: black;margin: .0em .0em .0m 0em;" action="${applicationScope.rutaBase}/login" class="form-inline" >
  </br>
  <button align="right" type="button" class="btn btn-success   disabled" role="button"<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if>>Bienvenido ${usuario.nombre} </button>
 <a align="right" button type="button" class="btn btn-danger"  href="${applicationScope.rutaBase}/login?opcion=logout"<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if> >Salir</a>
  <div class="form-group"<c:if test="${usuario.nombre != null }">
			  	Style="display:none";
			  </c:if>>
			  
    <label class="btn btn-default disabled" for="nombre"><b>Nombre</b></label> 
    <input id="nombre" name="nombre" required="required" minlength="4" value="">
  </div>
  <div class="form-group"<c:if test="${usuario.nombre != null }">
			  	Style="display:none";
			  </c:if>>
    <label class="btn btn-default disabled" for="pass"><b>Contraseña</b></label>
    <input type="password" id="pass" name="pass">
  </div>
  <input type="submit" class="btn btn-success" value="Login" <c:if test="${usuario.nombre != null }">
			  	Style="display:none";
			  </c:if>>
			<p class="errores"></p>

			</br>
</form>













<body background="${applicationScope.rutaBase}/img/beer.jpg">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<header>
		<h1><IMG SRC="${applicationScope.rutaBase}/img/danilogo.png" width="500" height="100px" /></h1>
			</header>
	 
			  <a button type="button" class="btn btn-success"  href="${applicationScope.rutaBase}/login?opcion=logout"<c:if test="${usuario.nombre != null }">
			  	Style="display:none";
			  </c:if> >Log in/ Sign in</a>
	
	
		
			 
