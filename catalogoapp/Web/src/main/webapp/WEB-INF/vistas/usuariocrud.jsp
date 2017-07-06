<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>




<script>$(document).ready(function(){
    $('#myTable').DataTable();
});
</script>
<h2>Mantenimiento de usuarios</h2>

<table id="myTable" class="table table-hover">
	<thead>
		<tr>
			<th>Operaciones</th>
			<th>Usuario</th>
			<th>Contraseña</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.usuarios}" var="usuario">
			<tr>
				<td>
					
				<a button type="button" class="btn btn-primary" href="${applicationScope.rutaBase}/admin/usuarioform?op=modificar&nombre=${usuario.nombre}">Modificar</a>
					
					<a button type="button" class="btn btn-danger" href="${applicationScope.rutaBase}/admin/productoform?op=borrar&id=${producto.id}">Borrar</a>
				</td>
				<td>${usuario.nombre}</td>
				<td>${usuario.pass}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>



<%@ include file="includes/pie.jsp"%>
