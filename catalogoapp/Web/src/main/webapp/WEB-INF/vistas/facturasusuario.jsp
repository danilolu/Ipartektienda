<%@ include file="includes/noadmincabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	
	<a href="${applicationScope.rutaBase}/finpedido?op=facturas&nombre_usuario=${usuario.nombre}" class="btn btn-success">Mis Pedidos</a>
	
<script type="text/javascript" charset="utf8" src="${applicationScope.rutaBase}/bootstrap/datatables.min.js"></script>



<script>$(document).ready(function(){
    $('#myTable').DataTable();
});
</script>
<h2>Pedido desglosado</h2>
<table id="myTable" class="table table-hover">
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
				<td><IMG SRC="${applicationScope.rutaBase}/img/${factura.nombre_producto}.jpg" width="100" height="100px" /></td>
				<td>${factura.precio} &euro;</td>
				<td>${factura.cant} </td>
				<td>${factura.total} &euro;</td>
				<td>${factura.fecha} </td>
				
				
			</tr>
		</c:forEach>
	</tbody>
</table>


					

<%@ include file="includes/pie.jsp"%>
