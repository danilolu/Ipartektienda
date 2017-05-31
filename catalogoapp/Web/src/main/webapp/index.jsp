<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>tienda</title>
<link rel="stylesheet" href="css/estilo.css" />
<script src="js/funciones.js"></script>
</head>
<body>
	<header>
		<h1 align="center"><IMG SRC="danilogo.png" width="600" height="100px" /></h1>
			</header>
	<h2 style="text-align:center">Bienvenido a Cervezas DANI</h2>
	
	<A href="/login"><p style="text-align:center">
<IMG  src="index.png" ></a>
	
	
	<jsp:useBean id="usuario" scope="request"
		class="com.ipartek.danilozano.Tipos.Usuario" />

	
	
	<footer>
		<p>&copy;2017 Dani Lozano</p>
	</footer>
</body>
</html>