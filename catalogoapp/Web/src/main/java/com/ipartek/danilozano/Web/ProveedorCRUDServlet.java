package com.ipartek.danilozano.Web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.danilozano.DAL.ProveedorDalHibernate;
import com.ipartek.danilozano.Tipos.Proveedor;

@WebServlet("/admin/proveedorcrud")
public class ProveedorCRUDServlet extends HttpServlet {

	private static Logger log = Logger.getLogger(ProveedorCRUDServlet.class);
	static final String RUTA_FORMULARIO = "/WEB-INF/vistas/proveedorform.jsp";
	static final String RUTA_LISTADO = "/WEB-INF/vistas/proveedorcrud.jsp";
	static final String RUTA_SERVLET_LISTADO = "/admin/proveedorcrud";

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// instanciar el dao de hibernate
		ProveedorDalHibernate provedorDAL = new ProveedorDalHibernate();
		// instanciar variables
		String nombre_p = request.getParameter("nombre_p");
		// instanciar el parametro op
		String op = request.getParameter("op");
		// crear un objeto proveedor nuevo
		Proveedor proveedor = new Proveedor();
		// actuar en consecuencia de la opcion

		if (op == null) {// si op el null se cargan los productos
			// si op el null se cargan los productos

			Proveedor[] proveedores;
			proveedores = provedorDAL.buscarTodosLosProveedores();

			request.setAttribute("proveedores", proveedores);

			request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);

		} else {

			switch (op) {
			case "modificar":

			case "borrar":

				proveedor = provedorDAL.buscarPorNombre(nombre_p);

				log.info(proveedor + " buscado por nombre");
				request.setAttribute("proveedor", proveedor);
			case "alta":
				request.getRequestDispatcher(RUTA_FORMULARIO).forward(request, response);
			default:

			}
		}
	}

}
