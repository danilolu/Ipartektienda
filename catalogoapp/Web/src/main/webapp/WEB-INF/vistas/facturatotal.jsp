<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Mantenimiento de Productos</h2>

<table border="1" style="margin: 0 auto;">
	<thead>
		<tr>
			<th>Id Factura</th>
			<th>Nombre Usuario</th>
			<th>Total</th>
			<th>Fecha</th>
			
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.facturastotal}" var="factura">
			<tr>
				
				</td>
				<td><a href="facturacrud?op=desglosar&id_facturas=${factura.id_facturas}">Desglosar </a>${factura.id_facturas}</td>
				<td>${factura.nombre_usuario}</td>
				<td>${factura.total} &euro;</td>
				<td>${factura.fecha} </td>
				
				
			</tr>
		</c:forEach>
	</tbody>
</table>


					

<%@ include file="includes/pie.jsp"%>
