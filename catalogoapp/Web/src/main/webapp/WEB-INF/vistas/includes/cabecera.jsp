<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>tienda</title>
<link rel="stylesheet" href="${applicationScope.rutaBase}/css/estilo.css" />
<script src="js/funciones.js"></script>

<link rel="shortcut icon" href="${applicationScope.rutaBase}/img/favicon.png" type="image/png"></head>
<body>
	<header>
		<h1><IMG SRC="${applicationScope.rutaBase}/img/danilogo.png" width="500" height="100px" /></h1>
			</header>
	<nav>
		<ul>
			
			<li><a href="${applicationScope.rutaBase}/admin/productoform?op=alta">Alta productos</a></li>
			
			<li><a href="${applicationScope.rutaBase}/admin/productocrud">Mantenimiento Productos</a></li>
	
			
			
			<li><a href="${applicationScope.rutaBase}/login?opcion=logout">Salir</a></li>
			
		</ul>
	
		<ul>
			
			<li><a href="${applicationScope.rutaBase}/admin/usuarioform?op=alta">Alta usuarios</a></li>
			
			<li><a href="${applicationScope.rutaBase}/admin/usuariocrud">Mantenimiento usuarios</a></li>
		</ul>
		<ul>
			
			<li><a href="${applicationScope.rutaBase}/admin/facturacrud?op=factotal">Pedidos</a></li>
			
			
		</ul>
	</nav>
	<h5 >Bienvenido ${usuario.nombre} </h5>