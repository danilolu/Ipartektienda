<%@ include file="includes/cabecera.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<h2>${fn:toUpperCase(param.op)} PRODUCTOS </h2>
	
	<jsp:useBean id="producto" scope="request"
		class="com.ipartek.danilozano.Tipos.Producto" />

	<form action="productoform" method="post">
		<fieldset>
			<label for="id"<c:if test="${ param.op == 'alta'}">
			  	Style="display:none";
			  </c:if>  
				
				>ID</label> 
			<input id="id"	name="id" required="required"  value="${producto.id}"
			  	Style=background-color:lightgray; readonly="readonly" 
			  		  <c:if test="${ param.op == 'alta'}">
			  	Type="hidden";
			  </c:if>  
				
				/>
			
		
		</fieldset>
		<fieldset>
			<label for="nombre"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				>Nombre</label>
			 <input id="nombre" name="nombre"<c:if test="${ param.op == 'borrar'}">
			  	Type="hidden";
			  </c:if>  
				
				/>
		</fieldset>
		<fieldset>
			<label for="descripcion"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				>Descripcion</label> 
				<input  id="descripcion"
				name="descripcion"<c:if test="${ param.op == 'borrar'}">
			  	Type="hidden";
			  </c:if>  
				
				/>
		</fieldset>
		<fieldset>
			<label for="precio"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				>Precio</label> <input type="number" step="any" id="precio"
				name="precio" <c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				/>
		</fieldset>
		<fieldset>
			<label for="stock"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				>Stock</label> <input type="number" step="any" id="stock"
				name="stock" <c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				/>
		</fieldset>
		<fieldset>
				<input type="submit" value="${fn:toUpperCase(param.op)}" 
				<c:if test="${param.op == null or param.op == ''}">
			  	Style="display:none;"
			  </c:if>  
				
				/>
			<p class="errores" >${producto.errores}</p>
			
			<input type="hidden" name="opform" value="${param.op}" />
		</fieldset>
	</form>
	
	<c:if test="${param.op == 'borrar'}">
		
	</c:if>
	
<%@ include file="includes/pie.jsp" %>