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
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" charset="utf8" src="${applicationScope.rutaBase}/bootstrap/datatables.min.js"></script>
<link rel="shortcut icon" href="${applicationScope.rutaBase}/img/favicon.png" type="image/png"></head>


<body>
<div style="background-color: black;margin-bottom: 0px;border-bottom-width: 20px;height: 50px;" action="/login" class="form-inline">  
  <button  type="button" class="btn btn-success   disabled" role="button"<c:if test="${usuario.nombre == null }">
			  	Style="display:none";  </c:if>style="margin-top: 6px;">Bienvenido ${usuario.nombre} </button>
			  	
 <a  button  type="button" class="btn btn-danger"  href="${applicationScope.rutaBase}/login?opcion=logout"<c:if test="${usuario.nombre == null }">
			  	Style="display:none";</c:if> style="margin-top: 6px;">Salir</a>

    <img style="
     align:right" src="${applicationScope.rutaBase}/img/danilogo.png"align="right" width="150" height="50px">

			
</div>

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
  <div class="btn-group">
    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
    Proveedores <span class="caret"></span></button>
    <ul class="dropdown-menu" role="menu">
			<li><a href="${applicationScope.rutaBase}/admin/proveedorform?op=alta">Alta Proveedores</a></li>
			<li><a href="${applicationScope.rutaBase}/admin/proveedorcrud">Mantenimiento Proveedores</a></li>
    </ul>
  </div>	
  <a button type="button" class="btn btn-primary" href="${applicationScope.rutaBase}/admin/facturacrud?op=factotal">Pedidos</a>

		
		
	</nav>
	<body background="${applicationScope.rutaBase}/img/beer.jpg">
	