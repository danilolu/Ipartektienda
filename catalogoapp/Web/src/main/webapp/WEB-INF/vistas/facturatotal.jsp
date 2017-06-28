<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Todos los pedidos</h2>

<table border="1" style="margin: 0 auto;">
	<thead>
		<tr>
			<th>Id Factura</th>
			<th>Nombre Usuario</th>
			<th>Total</th>
			<th>Fecha</th>
			<th>Imprimir Factura</th>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.facturastotal}" var="factura">
			<tr>
				
				</td>
				<td><a href="${applicationScope.rutaBase}/admin/facturacrud?op=desglosar&id_facturas=${factura.id_facturas}">Desglosar </a>${factura.id_facturas}</td>
				<td>${factura.nombre_usuario}</td>
				<td>${factura.total} &euro;</td>
				<td>${factura.fecha} </td>
				<td><a href="${applicationScope.rutaBase}/admin/facturacrud?op=imprimir&id_facturas=${factura.id_facturas}"target="_blank" onclick="window.open(this.href, this.target, 'width=500,height=500'); return false;"><img src="${applicationScope.rutaBase}/img/print.png" width="50" height="50px" > </a></td>
				
				
			</tr>
		</c:forEach>
	</tbody>
</table>


					

<%@ include file="includes/pie.jsp"%>
