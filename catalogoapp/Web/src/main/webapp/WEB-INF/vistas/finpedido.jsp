<%@ include file="includes/noadmincabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="producto" scope="request"
		class="com.ipartek.danilozano.Tipos.Carrito" />
		<link rel="stylesheet" type="text/css" href="${applicationScope.rutaBase}/bootstrap/datatables.min.css">
  
<script type="text/javascript" charset="utf8" src="${applicationScope.rutaBase}/bootstrap/datatables.min.js"></script>



<script>$(document).ready(function(){
    $('#myTable').DataTable();
});
</script>
<div class="btn-group btn-group-">
  <a href="${applicationScope.rutaBase}/carrito" class="btn btn-success">Volver al catalogo</a>
  <a href="${applicationScope.rutaBase}/finpedido?op=vaciar" class="btn btn-success">Vaciar carrito</a>
  
</div>


	<h2>Finalizar pedido</h2>
	<p class="errores">
			<div class="alert alert-danger"<c:if test="${carrito.errores == null or carrito.errores == ''}">
			  	Style="display:none;"</strong>
			  </c:if>  >
    <strong>Error <span class="glyphicon glyphicon-exclamation-sign"> ${carrito.errores}
  </div>


<table id="myTable" class="table table-hover">
	<thead>
		<tr>
			
			<th>Nombre producto</th>
			<th>Descripción</th>
			<th>Imagen</th>
			<th>Precio</th>
			<th>Cantidad</th>
			<th>Quitar de carrito</th>
			
			</tr>
	</thead>
	<tbody>
		<c:forEach items="${sessionScope.productosArr}" var="producto">
			<tr>
				<td>${producto.nombre}</td>
				<td>${producto.descripcion}</td>
				<td><IMG SRC="${applicationScope.rutaBase}/img/${producto.nombre}.jpg" width="100" height="100px" /></td>
				<td>${producto.precio} &euro;</td>
				<td>${producto.cant}</td>
				
				<td><a href="${applicationScope.rutaBase}/finpedido?op=quitar&id=${producto.id}"><img src="${applicationScope.rutaBase}/img/quitarcarrito.png" width="100" height="100px" ></a></td>
			</tr>
		</c:forEach>
		<table>
	<thead>
		<tr>
			
			<th>Numero de productos ${sessionScope.numeroProductostotal}</th>
			<th>Total a pagar:${sessionScope.precioTotal} &euro;</a></th>
			<th><a type="button" class="btn btn-warning" href="${applicationScope.rutaBase}/carrito" target="_blank" onclick="window.open('${applicationScope.rutaBase}/carrito', '_self'); window.open('${applicationScope.rutaBase}/finpedido?op=pagar', '_blank', 'width=400,height=400')"; return false;">Pagar</a> </a></th>
	
	</tbody>
</table>



					

<%@ include file="includes/pie.jsp"%>
