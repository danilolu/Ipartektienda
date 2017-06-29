<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>tienda</title>
<link rel="stylesheet" href="${applicationScope.rutaBase}/css/estilo.css" />
<script src="js/funciones.js"></script>
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
<fieldset>
	<h1 style="text-align:  right";>Login</h1>
		</fieldset>
		<fieldset>
			<label for="nombre">Nombre</label> <input id="nombre" name="nombre" required="required" minlength="4" value="">
		</fieldset>
		<fieldset>
			<label for="pass">Contraseña</label> <input type="password" id="pass" name="pass">
		</fieldset>
		<fieldset>
			<input type="submit" value="Login">
			<p class="errores"></p>
		</fieldset>
	</form>
	
	
	
<p style="text-align: left;width: 50%;display: inline;"><a href="${applicationScope.rutaBase}/noadmin/login?op=alta">
<img src="${applicationScope.rutaBase}/img/registrate.png" width="300px" height="150px" ></a>
	
</p>

<nav>
		
	<li><a href="${applicationScope.rutaBase}/carrito">Ver catalogo como invitado</a></li>
			
</nav>


	
<%@ include file="includes/pie.jsp" %>