<%@ include file="includes/noadmincabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<nav>
	<ul>
	<li > <B>${sessionScope.numeroProductos} producto/s diferentes en el carrito</br>
	En total :${sessionScope.numeroProductostotal}</B></li>
	<li><a href="/finpedido">Finalizar pedido</a></li>
	<li><a href="/finpedido?op=vaciar">Vaciar carrito</a></li>
	<li><a href="/finpedido?op=facturas&nombre_usuario=${usuario.nombre}">Mis Facturas</a></li>
	
	</ul>
</nav>
<h2>Catalogo</h2>

<p class="errores">${producto.errores}</p>
<table border="1" style="margin: 0 auto;
   
">
	<thead>
		<tr>
			
			<th>ID</th>
			<th>Nombre</th>
			<th>Imagen</th>
			<th>Descripcion</th>
			<th>Precio</th>
			<th>Stock</th>
			<th>Cantidad</th>
			
			<!--  <th>Meter al carrito</th>-->
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${applicationScope.catalogo}" var="producto">
			
			<tr>
				
				<td>${producto.id}</td>
				<td>${producto.nombre}</td>
				<td><IMG SRC="/img/${producto.nombre}.jpg" width="100" height="100px" /></td>
				<td>${producto.descripcion}</td>
				<td>${producto.precio} &euro;</td>
				<td>${producto.stock}</td>
				<td><form action="/carrito?op=anadir&id=${producto.id}" method="post">
			<input type="hidden" name="id" value="${producto.id}"/>
				<input type="number" name="cant" 
						   value="${producto.cant}" 
						   min="1" max="${producto.stock}"
						   style="width: 3em" />
						   
						   <input type="submit" 
						   value="Meter al carrito" />
						   </form></td>
				
				<!--  <td><a href="/carrito?op=anadir&id=${producto.id}"><img src="/img/anadir.png" width="50" height="50px" ></a></td>-->
			</tr>
		</c:forEach>
	</tbody>
</table>

<nav>
	<ul>
	<li>${sessionScope.numeroProductos} productos en el carrito</li>
	<li><a href="/finpedido">Finalizar pedido</a></li>
	<li><a href="/finpedido?op=vaciar">Vaciar carrito</a></li>
	</ul>
</nav>
			
			
<%@ include file="includes/pie.jsp"%>
