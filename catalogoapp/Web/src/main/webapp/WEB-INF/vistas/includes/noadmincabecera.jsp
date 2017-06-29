<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>tienda</title>
<link rel="stylesheet" href="${applicationScope.rutaBase}/css/estilo.css" />
<script src="js/funciones.js"></script>
<link rel="shortcut icon" href="${applicationScope.rutaBase}/img/favicon.png" type="image/png">
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<header>
		<h1><IMG SRC="${applicationScope.rutaBase}/img/danilogo.png" width="500" height="100px" /></h1>
			</header>
	<h4>Bienvenido ${usuario.nombre} </h4>
	<h4<c:if test="${usuario.nombre != null }">
			  	Style="display:none";
			  </c:if>>Invitado, para iniciar una compra debes iniciar sesion en la tienda</h4>
	<nav>
		
			<li><a href="${applicationScope.rutaBase}/login?opcion=logout"<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if> >Salir</a></li>
			<li><a href="${applicationScope.rutaBase}/login"<c:if test="${usuario.nombre != null }">
			  	Style="display:none";
			  </c:if> >Log in/ Sign in</a></li>
			
		</ul>
	</nav>
