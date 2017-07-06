<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>





<script>$(document).ready(function(){
    $('#myTable').DataTable();
});
</script>
<h2 class="text-center">Mantenimiento de Productos</h2>

<table  id="myTable" class="display">
	<thead>
		<tr>
			<th>Operaciones</th>
			<th>ID</th>
			<th>Nombre</th>
			<th>Imagen</th>
			<th>Descripcion</th>
			<th>Precio</th>
			<th>Stock</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.productos}" var="producto">
			<tr>
				<td>
				<a button type="button" class="btn btn-primary" href="${applicationScope.rutaBase}/admin/productoform?op=modificar&id=${producto.id}">Modificar</a>
					</br>
					<a button type="button" class="btn btn-danger" href="${applicationScope.rutaBase}/admin/productoform?op=borrar&id=${producto.id}">Borrar</a>
				</td>
				<td>${producto.id}</td>
				<td>${producto.nombre}</td>
				<td><IMG SRC="${applicationScope.rutaBase}/img/${producto.nombre}.jpg" width="100" height="100px" /></td>
				<td>${producto.descripcion}</td>
				<td>${producto.precio} &euro;</td>
				<td>${producto.stock}</td>
				
			</tr>
		</c:forEach>
	</tbody>
</table>


					

<%@ include file="includes/pie.jsp"%>