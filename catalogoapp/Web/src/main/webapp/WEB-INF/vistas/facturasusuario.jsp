<%@ include file="includes/noadmincabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav>
	<ul>
	
	<li><a href="/finpedido?op=facturas&nombre_usuario=${usuario.nombre}">Mis Pedidos</a></li>
	
	</ul>
</nav>
<h2>Pedido desglosado</h2>
<table border="1" style="margin: 0 auto;">
	<thead>
		<tr>
			<th>Id Factura</th>
			<th>Nombre Usuario</th>
			<th>Producto</th>
			<th>Imagen</th>
			<th>Precio unitario</th>
			<th>Cantidad</th>
			<th>Total</th>
			<th>Fecha</th>
			
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.facturasporid}" var="factura">
			<tr>
				
				</td>
				<td>${factura.id_facturas}</td>
				<td>${factura.nombre_usuario}</td>
				<td>${factura.nombre_producto}</td>
				<td><IMG SRC="/img/${factura.nombre_producto}.jpg" width="100" height="100px" /></td>
				<td>${factura.precio} &euro;</td>
				<td>${factura.cant} </td>
				<td>${factura.total} &euro;</td>
				<td>${factura.fecha} </td>
				
				
			</tr>
		</c:forEach>
	</tbody>
</table>


					

<%@ include file="includes/pie.jsp"%>
