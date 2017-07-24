package com.ipartek.danilozano.Web;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

//import com.ipartek.danilozano.DAL.TiendaDAL;
import com.ipartek.danilozano.DAL.TiendaDAO;
import com.ipartek.danilozano.Tipos.Proveedor;

@WebServlet("/admin/proveedorcrud")
public class ProveedorCRUDServlet extends HttpServlet {
	public static TiendaDAO dao = null;
	private static Logger log = Logger.getLogger(ProductoFormServlet.class);
	static final String RUTA_FORMULARIO = "/WEB-INF/vistas/proveedorform.jsp";
	static final String RUTA_LISTADO = "/WEB-INF/vistas/proveedorcrud.jsp";
	static final String RUTA_SERVLET_LISTADO = "/admin/proveedorcrud";
	private static EntityManager manager;

	private static EntityManagerFactory emf;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		emf = Persistence.createEntityManagerFactory("persistencia");
		manager = emf.createEntityManager();

		String nombre_p = request.getParameter("nombre_p");
		String comentarios = request.getParameter("comentarios");
		int telefono;
		if (request.getParameter("telefono") == null || request.getParameter("telefono") == "") {
			telefono = 0;
		} else {
			telefono = Integer.parseInt(request.getParameter("telefono"));
		}

		String op = request.getParameter("op");
		Proveedor proveedor = new Proveedor();
		// actuar en consecuencia de la opcion

		if (op == null) {// si op el null se cargan los productos
			// si op el null se cargan los productos
			@SuppressWarnings("unchecked")
			List<Proveedor> proveedores = (List<Proveedor>) manager.createQuery("FROM Proveedor").getResultList();
			request.setAttribute("proveedores", proveedores);
			log.info(proveedores);
			request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);

		} else {

			switch (op) {
			case "modificar":

			case "borrar":

				proveedor = manager.find(Proveedor.class, nombre_p);
				request.setAttribute("proveedor", proveedor);
			case "alta":
				request.getRequestDispatcher(RUTA_FORMULARIO).forward(request, response);
			default:

			}
		}
	}

}
