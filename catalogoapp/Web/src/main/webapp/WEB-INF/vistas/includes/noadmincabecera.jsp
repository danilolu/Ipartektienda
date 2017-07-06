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
<body background="${applicationScope.rutaBase}/img/beer.jpg">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<header>
		<h1><IMG SRC="${applicationScope.rutaBase}/img/danilogo.png" width="500" height="100px" /></h1>
			</header>
	<button type="button" class="btn btn-success btn-lg  disabled" role="button">Bienvenido ${usuario.nombre} </button> <a button type="button" class="btn btn-danger"  href="${applicationScope.rutaBase}/login?opcion=logout"<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if> >Salir</a>
			  <a button type="button" class="btn btn-success"  href="${applicationScope.rutaBase}/login?opcion=logout"<c:if test="${usuario.nombre != null }">
			  	Style="display:none";
			  </c:if> >Log in/ Sign in</a></h4>
	
	
		
			 
