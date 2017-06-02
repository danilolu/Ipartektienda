<%@ include file="includes/noadmincabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<h2>Checkout</h2>


<table>
	<thead>
		<tr>
			
			<th>Nombre producto</th>
			<th>Descripci�n</th>
			<th>Imagen</th>
			<th>Precio</th>
			<th>Quitar de carrito</th>
			
			</tr>
	</thead>
	<tbody>
		<c:forEach items="${sessionScope.productosArr}" var="producto">
			<tr>
				<td>${producto.nombre}</td>
				<td>${producto.descripcion}</td>
				<td><IMG SRC="/${producto.nombre}.jpg" width="100" height="100px" /></td>
				<td>${producto.precio} &euro;</td>
				<td><a href="?op=quitar&id=${producto.id}"><img src="/quitarcarrito.png" width="100" height="100px" ></a></td>
			</tr>
		</c:forEach>
		<table>
	<thead>
		<tr>
			
			<th>Numero de productos ${sessionScope.numeroProductos}</th>
			<th>Total a pagar:${sessionScope.precioTotal} &euro;</a></th>
			<th><a href="?op=pagar">PAGAR </a></th>
	</tbody>
</table>



					

<%@ include file="includes/pie.jsp"%>
