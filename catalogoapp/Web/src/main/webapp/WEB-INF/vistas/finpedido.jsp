<%@ include file="includes/noadmincabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="producto" scope="request"
		class="com.ipartek.danilozano.Tipos.Carrito" />
<nav>
	<ul>
	
	<li><a href="${applicationScope.rutaBase}/carrito">Volver al catalogo</a></li>
	<li><a href="${applicationScope.rutaBase}/finpedido?op=vaciar">Vaciar carrito</a></li>
	</ul>
	</ul>
</nav>
	<h2>Finalizar pedido</h2>
<p class="errores" >${carrito.errores}</p>

<table>
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
			<th><a href="${applicationScope.rutaBase}/carrito" target="_blank" onclick="window.open('${applicationScope.rutaBase}/carrito', '_self'); window.open('${applicationScope.rutaBase}/finpedido?op=pagar', '_blank', 'width=400,height=400')"; return false;">Pagar</a> </a></th>
	
	</tbody>
</table>



					

<%@ include file="includes/pie.jsp"%>
