<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Mantenimiento de usuarios</h2>

<table border="1">
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
					<a href="${applicationScope.rutaBase}/admin/usuarioform?op=modificar&nombre=${usuario.nombre}">Modificar</a>
					<a href="${applicationScope.rutaBase}/admin/usuarioform?op=borrar&nombre=${usuario.nombre}">Borrar</a>
				</td>
				<td>${usuario.nombre}</td>
				<td>${usuario.pass}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>



<%@ include file="includes/pie.jsp"%>
