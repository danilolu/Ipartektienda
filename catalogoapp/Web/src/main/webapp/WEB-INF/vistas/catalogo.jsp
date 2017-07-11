<%@ include file="includes/noadmincabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="producto" scope="request"
		class="com.ipartek.danilozano.Tipos.Producto" />
<link rel="stylesheet" type="text/css" href="${applicationScope.rutaBase}/bootstrap/datatables.min.css">
  
<script type="text/javascript" charset="utf8" src="${applicationScope.rutaBase}/bootstrap/datatables.min.js"></script>



<script>$(document).ready(function(){
    $('#myTable').DataTable();
});
</script>


<h2 style="
    margin-bottom: 0px;
    margin-top: 0px;
">Catalogo </h2>


<h4<c:if test="${usuario.nombre != null }">
			  	Style="display:none";
			  </c:if> style="
    margin-top: 0px;"
 >Invitado, para iniciar una compra debes iniciar sesion en la tienda</h4>
<h4
<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if>  style="
    margin-top: 0px;margin-bottom: 0px;text-align:right" > <B><span class="glyphicon glyphicon-shopping-cart">${sessionScope.numeroProductostotal} Productos 
     
	</B></h4>
<div class="row">
<div class="col-md-4 col-md-offset-4">
			<div class="btn-group btn-group-" >
  <a href="${applicationScope.rutaBase}/finpedido"<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if>  class="btn btn-success">Finalizar pedido</a>
  <a href="${applicationScope.rutaBase}/finpedido?op=vaciar"<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if>  class="btn btn-success">Vaciar carrito</a>
  <a href="${applicationScope.rutaBase}/finpedido?op=facturas&nombre_usuario=${usuario.nombre}"<c:if test="${usuario.nombre == null }">
			  	Style="display:none";
			  </c:if> class="btn btn-success">Mis pedidos</a>
</div>
   </div>
    </div>
    



<table id="myTable" class="table table-hover">
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
						   value="0" 
						   min="1" max="${producto.stock}"
						   style="width: 3em" />
						   
						   <input type="submit" class="btn btn-info"
						   value="Meter al carrito" <c:if test="${producto.stock == 0 }">
			  	Style="display:none";
			  </c:if> /><h4<c:if test="${producto.stock != 0 }">
			  	Style="display:none";
			  </c:if>>Producto agotado</h4>
						   </form></td>
				
			</tr>
		</c:forEach>
	</tbody>
</table>


			
			
<%@ include file="includes/pie.jsp"%>
