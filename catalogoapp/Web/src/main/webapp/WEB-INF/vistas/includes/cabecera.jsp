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
<body>
<body background="${applicationScope.rutaBase}/img/beer.jpg">
	<header>
		<h1><IMG  SRC="${applicationScope.rutaBase}/img/danilogo.png" width="500" height="100px" /></h1>
			</header>
	<nav>
		
			<div class="btn-group">
			 <div class="btn-group">
    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
    Productos <span class="caret"></span></button>
    <ul class="dropdown-menu" role="menu">
			<li><a href="${applicationScope.rutaBase}/admin/productoform?op=alta">Alta productos</a></li>
			<li><a href="${applicationScope.rutaBase}/admin/productocrud">Mantenimiento Productos</a></li>
    </ul>
  </div>
		<div class="btn-group">
    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
    Usuarios <span class="caret"></span></button>
    <ul class="dropdown-menu" role="menu">
			<li><a href="${applicationScope.rutaBase}/admin/usuarioform?op=alta">Alta usuarios</a></li>
			<li><a href="${applicationScope.rutaBase}/admin/usuariocrud">Mantenimiento usuarios</a></li>
    </ul>
  </div>	
  <a button type="button" class="btn btn-primary" href="${applicationScope.rutaBase}/admin/facturacrud?op=factotal">Pedidos</a>
<a button type="button" class="btn btn-danger" href="${applicationScope.rutaBase}/login?opcion=logout">Salir</a>
		
		
	</nav>
	<button type="button" class="btn btn-success btn-lg  disabled" role="button">Bienvenido ${usuario.nombre} </button>