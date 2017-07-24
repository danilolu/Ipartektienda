<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>




<script>$(document).ready(function(){
    $('#myTable').DataTable();
});
</script>
<h2>Mantenimiento de Proveedores</h2>

<table id="myTable" class="table table-hover">
	<thead>
		<tr>
			<th>Operaciones</th>
			<th>Proveedor</th>
			<th>Comentarios</th>
			<th>Telefono</th>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.proveedores}" var="proveedor">
			<tr>
				<td>
					
				<a button type="button" class="btn btn-primary" href="${applicationScope.rutaBase}/admin/proveedorcrud?op=modificar&nombre_p=${proveedor.nombre_p}">Modificar</a>
					
					<a button type="button" class="btn btn-danger" href="${applicationScope.rutaBase}/admin/proveedorcrud?op=borrar&nombre_p=${proveedor.nombre_p}">Borrar</a>
				</td>
				<td>${proveedor.nombre_p}</td>
				<td>${proveedor.comentarios}</td>
				<td>${proveedor.telefono}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>



<%@ include file="includes/pie.jsp"%>
