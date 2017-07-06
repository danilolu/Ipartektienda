<%@ include file="includes/noadmincabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<a href="${applicationScope.rutaBase}/carrito" class="btn btn-success">Catalogo</a>
	
<script>$(document).ready(function(){
    $('#myTable').DataTable();
});
</script>
<h2>Todos mis pedidos</h2>

<table id="myTable" class="table table-hover">
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
		<c:forEach items="${requestScope.facturaspornombre}" var="factura">
			<tr>
				
				</td>
				<td>
				<a  button type="button" class="btn btn-success" href="${applicationScope.rutaBase}/finpedido?op=desglosar&id_facturas=${factura.id_facturas}">Desglosar factura ${factura.id_facturas}
				</td>
				<td>${factura.nombre_usuario}</td>
				<td>${factura.total} &euro;</td>
				<td>${factura.fecha} </td>
				<td><a href="${applicationScope.rutaBase}/finpedido?op=imprimir&id_facturas=${factura.id_facturas}"target="_blank" onclick="window.open(this.href, this.target, 'width=500,height=500'); return false;"><img src="${applicationScope.rutaBase}/img/print.png" width="50" height="50px" > </a></td>
				
			</tr>
		</c:forEach>
	</tbody>
</table>


					

<%@ include file="includes/pie.jsp"%>
