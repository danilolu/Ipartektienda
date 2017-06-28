<%@ include file="includes/cabecera.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<h2>${fn:toUpperCase(param.op)} USUARIOS</h2>
	
	<jsp:useBean id="usuario" scope="request"
		class="com.ipartek.danilozano.Tipos.Usuario" />

	<form action="${applicationScope.rutaBase}/admin/usuarioform" method="post">
		<fieldset>
			<label for="nombre">Nombre</label> 
			
			<input id="nombre" name="nombre"
			  required="required" minlength="4" value="${usuario.nombre}" 
			  
			  <c:if test="${param.op == 'modificar' or param.op == 'borrar'}">
			  	readonly="readonly"Style=background-color:lightgray;
			  </c:if>   
		  	/>
		</fieldset>
		<fieldset>
			<label for="pass"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				>Contraseņa</label> <input type="password" id="pass"
				name="pass" minlength="4"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  
				
				/>
		</fieldset>
		<fieldset>
			<label for="pass2"<c:if test="${ param.op == 'borrar'}">
			  	Style="display:none";
			  </c:if>  >Contraseņa otra vez</label> <input type="password" id="pass2"
				name="pass2" minlength="4"<c:if test="${ param.op == 'borrar'}">
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
			<p class="errores">${usuario.errores}</p>
			
			<input type="hidden" name="opform" value="${param.op}" />
		</fieldset>
	</form>
	
	
	
<%@ include file="includes/pie.jsp" %>