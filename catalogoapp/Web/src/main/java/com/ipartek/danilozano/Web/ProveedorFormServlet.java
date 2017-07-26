package com.ipartek.danilozano.Web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.danilozano.DAL.ProveedorDalHibernate;
import com.ipartek.danilozano.Tipos.Proveedor;

@WebServlet("/admin/proveedorform")
public class ProveedorFormServlet extends HttpServlet {

	private static Logger log = Logger.getLogger(ProductoFormServlet.class);

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// instanciar el dal hibernate
		ProveedorDalHibernate provedorDAL = new ProveedorDalHibernate();
		// definir ruteo
		RequestDispatcher rutaListado = request.getRequestDispatcher(ProveedorCRUDServlet.RUTA_SERVLET_LISTADO);
		RequestDispatcher rutaFormulario = request.getRequestDispatcher(ProveedorCRUDServlet.RUTA_FORMULARIO);

		// recojer la opcion que se carga en la url
		String op = request.getParameter("opform");

		// variables del objeto Proveedor
		String nombre_p = request.getParameter("nombre_p");
		String comentarios = request.getParameter("comentarios");
		int telefono;
		if (request.getParameter("telefono") == null || request.getParameter("telefono") == "") {
			telefono = 0;
		} else {
			telefono = Integer.parseInt(request.getParameter("telefono"));
		}

		// crear objeto Pproducto
		Proveedor proveedor = new Proveedor(nombre_p, comentarios, telefono);

		// actuar en consecuencia de la opcion recogida anteriormente

		if (op == null) {
			rutaListado.forward(request, response);
			return;
		}
		switch (op) {
		case "alta":
			if (nombre_p != null || comentarios != null || telefono != 0) {

				provedorDAL.alta(proveedor);
				rutaListado.forward(request, response);

			} else {
				proveedor.setErrores("- Todos los campos deben estar rellenados ");

				request.setAttribute("proveedor", proveedor);
				rutaFormulario.forward(request, response);
			}
			break;

		case "modificar":

			proveedor = provedorDAL.buscarPorNombre(nombre_p);
			proveedor.setComentarios(comentarios);
			proveedor.setTelefono(telefono);
			log.info("PROVEEDORUPDATE es: " + proveedor);
			provedorDAL.modificar(proveedor);

			rutaListado.forward(request, response);
			break;
		case "borrar":

			provedorDAL.borrar(nombre_p);

			rutaListado.forward(request, response);

			break;

		}
	}

}
