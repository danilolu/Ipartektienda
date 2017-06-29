<%@ include file="includes/noadmincabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<nav>
	<ul>
	<li <c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if> > <B>${sessionScope.numeroProductos} producto/s diferentes en el carrito</br>
	En total :${sessionScope.numeroProductostotal}</B></li>
	<li><a href="${applicationScope.rutaBase}/finpedido"<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if> >Finalizar pedido</a></li>
	<li><a href="${applicationScope.rutaBase}/finpedido?op=vaciar"<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if> >Vaciar carrito</a></li>
	<li><a href="${applicationScope.rutaBase}/finpedido?op=facturas&nombre_usuario=${usuario.nombre}"<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if> >Mis Pedidos</a></li>
	
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
			<th<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if>>Stock</th>
			<th<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if>>Cantidad</th>
			
			
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${applicationScope.catalogo}" var="producto">
			
			<tr>
				
				<td>${producto.id}</td>
				<td>${producto.nombre}</td>
				<td><IMG SRC="${applicationScope.rutaBase}/img/${producto.nombre}.jpg" width="100" height="100px" /></td>
				<td>${producto.descripcion}</td>
				<td>${producto.precio} &euro;</td>
				<td<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if>>${producto.stock}</td>
				<td<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if>><form action="${applicationScope.rutaBase}/carrito?op=anadir&id=${producto.id}" method="post">
			<input type="hidden" name="id" value="${producto.id}"/>
				<input type="number" name="cant" 
						   value="${producto.cant}" 
						   min="1" max="${producto.stock}"
						   style="width: 3em" />
						   
						   <input type="submit" 
						   value="Meter al carrito" <c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if> />
						   </form></td>
				
				<!--  <td><a href="/carrito?op=anadir&id=${producto.id}"><img src="/img/anadir.png" width="50" height="50px" ></a></td>-->
			</tr>
		</c:forEach>
	</tbody>
</table>

<nav>
	<ul>
	<li<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if>  > <B>${sessionScope.numeroProductos} producto/s diferentes en el carrito</br>
	En total :${sessionScope.numeroProductostotal}</B></li>
	<li><a href="${applicationScope.rutaBase}/finpedido"<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if> >Finalizar pedido</a></li>
	<li><a href="${applicationScope.rutaBase}/finpedido?op=vaciar"<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if> >Vaciar carrito</a></li>
	</ul>
</nav>
			
			
<%@ include file="includes/pie.jsp"%>
