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

<form style="background-color: black;margin-bottom: 0px;border-bottom-width: 20px;height: 50px;" action="/login" class="form-inline">  
  <button  type="button" class="btn btn-success   disabled" role="button"<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if>style="
    margin-top: 6px;">Bienvenido ${usuario.nombre} </button>
 <a  button  type="button" class="btn btn-danger"  href="${applicationScope.rutaBase}/login?opcion=logout"<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if> style="
    margin-top: 6px;">Salir</a>


  <div class="form-group"<c:if test="${usuario.nombre != null }">
			  	Style="display:none";
			  </c:if>style="
    margin-top: 6px;">
			  
    <label class="btn btn-default disabled" for="nombre"><b>Nombre</b></label> 
    <input id="nombre" name="nombre" required="required" minlength="4" value="">
  </div>
  <div class="form-group"<c:if test="${usuario.nombre != null }">
			  	Style="display:none";
			  </c:if>style="
    margin-top: 6px;">
    <label class="btn btn-default disabled" for="pass"><b>Contraseña</b></label>
    <input type="password" id="pass" name="pass">
  </div>
  <input type="submit" class="btn btn-success" value="Login" <c:if test="${usuario.nombre != null }">
			  	Style="display:none";
			  </c:if>style="
    margin-top: 6px;">
			<a  button  type="button" class="btn btn-info"  href="${applicationScope.rutaBase}/noadmin/login?op=alta"<c:if test="${usuario.nombre != null }">
			  	Style="display:none";
			  </c:if> style="
    margin-top: 6px; align:right">Registrate</a>
    <img style="
     align:right" src="${applicationScope.rutaBase}/img/danilogo.png"align="right" width="150" height="50px">

			
</form>













<body background="${applicationScope.rutaBase}/img/beer.jpg">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
			 
	
	
		
			 
