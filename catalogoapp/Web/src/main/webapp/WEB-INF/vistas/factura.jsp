<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Su factura</title>
</head>
<body>

<c:forEach items="${requestScope.facturasnombre}" var="factura">
<table>
	<thead>
	<tr>
		<th style="text-align: left">Factura ${factura.id_facturas}</th>
		<th style="text-align: right">${factura.fecha}</th>
		
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>Cervezas DANI S.L</td><td></td>
		<td>CIF A46103884</td>
	</tr>
	<tr>
	<td>Cliente:</td>
	<td >${usuario.nombre}</td>
	</tr>
	</tbody>
</table>
</c:forEach>


<div>
<table>
	<thead>
		<tr>
			<th>Producto</th>
			<th style="text-align: right">Precio</th>
			<th style="text-align: right">Cantidad</th>
			<th style="text-align: right">Total</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.facturasid}" var="factura">
				<tr>
				<td>${factura.nombre_producto}</td>
				<td style="text-align: right">${factura.precio} €</td>
				<td style="text-align: right">${factura.cant} €</td>
				<td style="text-align: right">${factura.total} €</td>
				</tr>
		
		</c:forEach>
		</br>
		<tr></tr><tr>
		<td> </td>
		<td></td>
		
			<td>Total: </td>
			<c:forEach items="${requestScope.facturasnombre}" var="factura">
			<td> ${factura.total} €</td>
			</c:forEach>
		</tr>
	</tbody>
</table>
</div>
</div>

</body>
</html>