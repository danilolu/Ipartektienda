<%@ include file="includes/noadmincabecera.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<h2>${fn:toUpperCase(param.op)} USUARIOS</h2>
	
	<jsp:useBean id="usuario" scope="request"
		class="com.ipartek.danilozano.Tipos.Usuario" />
<div class="container-fluid">
	<form action="${applicationScope.rutaBase}/noadmin/usuarioform" method="post">
		
				<div class="form-group">
			<label for="nombre">Nombre</label></br> 
			
			<input id="nombre" name="nombre"
			  required="required" minlength="4" value="${usuario.nombre}"/>
		</div>
				<div class="form-group">
			<label for="pass">Contraseña</label> </br> <input type="password" id="pass"
				name="pass" minlength="4" />
		</div>
				<div class="form-group">
			<label for="pass2">Contraseña otra vez</label></br>  <input type="password" id="pass2"
				name="pass2" minlength="4" />
		</div>
				<div class="form-group">
			<input type="submit" class="btn btn-success"value="${fn:toUpperCase(param.op)}"
<c:if test="${param.op == null or param.op == ''}">
			  	Style="display:none;"
			  </c:if>  
					
				/>
			<p class="errores">
			<div class="alert alert-danger"<c:if test="${usuario.errores == null or usuario.errores == ''}">
			  	Style="display:none;"</strong>
			  </c:if>  >
    <strong>Error <span class="glyphicon glyphicon-exclamation-sign"> ${usuario.errores}
  <a href="${applicationScope.rutaBase}/noadmin/login?op=alta">
 
			<a href="${applicationScope.rutaBase}/noadmin/login?op=alta"  class="btn btn-info"><span class="glyphicon glyphicon-repeat"> Reintentar</a>
		</div>
			 </div>	
			 			<input type="hidden" name="opform" value="${param.op}" />
			 
	</form>
	</div>
	
	
<%@ include file="includes/pie.jsp" %>