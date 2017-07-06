<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body background="${applicationScope.rutaBase}/img/beer.jpg">
<head>
<meta charset="UTF-8" />
<title>tienda</title>
<link rel="stylesheet" href="${applicationScope.rutaBase}/css/estilo.css" />
<link rel="stylesheet" href="${applicationScope.rutaBase}/bootstrap/bootstrap.min.css" />

<script src="js/funciones.js"></script>
<link rel="shortcut icon" href="${applicationScope.rutaBase}/img/favicon.png" type="image/png">
</head>
<body>
	<header>
		<h1 align="center"><IMG SRC="${applicationScope.rutaBase}/img/danilogo.png" width="500" height="100px" /></h1>
			</header>
	

	
	<jsp:useBean id="usuario" scope="request"
		class="com.ipartek.danilozano.Tipos.Usuario" />


	<form action="${applicationScope.rutaBase}/login" method="post" style="text-align: right;
    width: 50%;
    display: inline-block;
">
<div class="form-group">

		</br>
			<label class="btn btn-default disabled" for="nombre"><b>Nombre</b></label> <input id="nombre" name="nombre" required="required" minlength="4" value="">
		 </div>
  <div class="form-group">
			<label class="btn btn-default disabled" for="pass"><b>Contraseña</b></label> <input type="password" id="pass" name="pass">
		 </div>
 
			<input type="submit" class="btn btn-success" value="Login">
			<p class="errores"></p>
		
	</form>
	
	
	
<p style="text-align: left;width: 50%;display: inline;"><a href="${applicationScope.rutaBase}/noadmin/login?op=alta">
<img src="${applicationScope.rutaBase}/img/registrate.png" width="300px" height="150px" ></a>
</p>

<nav>
		
	<a button type="button" class="btn btn-info"href="${applicationScope.rutaBase}/carrito">Ver catalogo como invitado</a>
			
</nav>


	
<%@ include file="includes/pie.jsp" %>