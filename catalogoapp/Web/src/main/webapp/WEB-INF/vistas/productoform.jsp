<%@ include file="includes/cabecera.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<h2>${fn:toUpperCase(param.op)} PRODUCTOS </h2>
	
	<jsp:useBean id="producto" scope="request"
		class="com.ipartek.danilozano.Tipos.Producto" />



<div class="container-fluid">
	<form action="${applicationScope.rutaBase}/admin/productoform" method="post">
	<div class="form-group">
		
			<label for="id"<c:if test="${ param.op == 'alta'}">
			  	Style="display:none";
			  </c:if>  
				
				>ID</label> </br>
				
			<input id="id"	name="id" required="required"  value="${producto.id}"
			  	Style=background-color:lightgray; readonly="readonly" 
			  		  <c:if test="${ param.op == 'alta'}">
			  	Type="hidden";
			  </c:if>  
				
				/>
			
		
		
		</div>
				<div class="form-group">
		
			<label for="nombre"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				>Nombre</label></br>
			 <input id="nombre" name="nombre" value="${producto.nombre}"<c:if test="${ param.op == 'borrar'}">
			  	Type="hidden";
			  </c:if>  
				
				/>
	
		</div>
				<div class="form-group">
	
			<label for="descripcion"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				>Descripcion</label> </br>
				<input  id="descripcion"
				name="descripcion" value="${producto.descripcion}"<c:if test="${ param.op == 'borrar'}">
			  	Type="hidden";
			  </c:if>  
				
				/>
		
		</div>
				<div class="form-group">
		
			<label for="precio"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				>Precio</label> </br><input type="number" step="any" id="precio"
				name="precio"  value="${producto.precio}"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				/>

		</div>
				<div class="form-group">
	
			<label for="stock"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				>Stock</label></br> <input type="number" step="any" id="stock"
				name="stock"  value="${producto.stock}"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				/>
	
		</div>
				<div class="form-group">
		
				<input type="submit"class="btn btn-success" value="${fn:toUpperCase(param.op)}" 
				<c:if test="${param.op == null or param.op == ''}">
			  	Style="display:none;"
			  </c:if>  
				
				/>
			<p class="errores" >${producto.errores}</p>
			
			<input type="hidden" name="opform" value="${param.op}" />
		</div></div>
	</form>
	
	<c:if test="${param.op == 'borrar'}">
		
	</c:if>
	
<%@ include file="includes/pie.jsp" %>