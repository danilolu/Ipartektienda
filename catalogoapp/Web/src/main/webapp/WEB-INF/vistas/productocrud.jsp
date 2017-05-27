<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Mantenimiento de Productos</h2>

<table border="1" style="margin: 0 auto;">
	<thead>
		<tr>
			
			<th>ID</th>
			<th>Nombre</th>
			<th>Imagen</th>
			<th>Descripcion</th>
			<th>Precio</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.productos}" var="producto">
			<tr>
				
				<td>${producto.id}</td>
				<td>${producto.nombre}</td>
				<td><IMG SRC="${producto.nombre}.jpg" width="100" height="100px" /></td>
				<td>${producto.descripcion}</td>
				<td>${producto.precio}</td>
				
			</tr>
		</c:forEach>
	</tbody>
</table>


					

<%@ include file="includes/pie.jsp"%>
