<%@ include file="includes/cabecera.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<h2>${fn:toUpperCase(param.op)} USUARIOS</h2>
	
	<jsp:useBean id="usuario" scope="request"
		class="com.ipartek.danilozano.Tipos.Proveedor" />
<div class="container-fluid">
	<form  action="${applicationScope.rutaBase}/admin/proveedorform" method="post">
		
  <div class="form-group">
			<label for="nombre_p">Nombre</label> </br>
			
			<input id="nombre_p" name="nombre_p"
			  required="required" minlength="4" value="${proveedor.nombre_p}" 
			  
			  <c:if test="${param.op == 'modificar' or param.op == 'borrar'}">
			  	readonly="readonly"Style=background-color:lightgray;
			  </c:if>   
		  	/>
		</div>
  <div class="form-group">
			<label for="comentarios"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				>Comentarios</label></br> <input  id="comentarios"
				name="comentarios" minlength="4"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				/>
		</div>
  <div class="form-group">
			<label for="telefono"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  >Telefono</label></br> <input type="number" id="telefono"
				name="telefono" minlength="4"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				/>
		</div>
  <div class="form-group">
			<input type="submit" class="btn btn-success"value="${fn:toUpperCase(param.op)}"
<c:if test="${param.op == null or param.op == ''}">
			  	Style="display:none;"
			  </c:if>  
				
				/>
				</div>
			<p class="errores">
			<div class="alert alert-danger"<c:if test="${usuario.errores == null or usuario.errores == ''}">
			  	Style="display:none;"</strong>
			  </c:if>  >
    <strong>Error <span class="glyphicon glyphicon-exclamation-sign"> ${usuario.errores}
  <a href="javascript:history.back()"  class="btn btn-info"><span class="glyphicon glyphicon-repeat"> Reintentar</a>
  </div>
			
			<input type="hidden" name="opform" value="${param.op}" />
		</div>
	</form>
	
	
	
<%@ include file="includes/pie.jsp" %>