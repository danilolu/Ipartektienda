package com.ipartek.danilozano.Web;

import java.io.IOException;

//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.ipartek.danilozano.DAL.TiendaDAL;
import com.ipartek.danilozano.DAL.TiendaDAO;
import com.ipartek.danilozano.DAL.TiendaDAOMySQL;
import com.ipartek.danilozano.Tipos.Usuario;

@WebServlet("/admin/usuariocrud")
public class UsuarioCRUDServlet extends HttpServlet {
	public static TiendaDAO dao = null;
	static final String RUTA_FORMULARIO = "/WEB-INF/vistas/usuarioform.jsp";
	static final String RUTA_LISTADO = "/WEB-INF/vistas/usuariocrud.jsp";
	static final String RUTA_SERVLET_LISTADO = "/admin/usuariocrud";

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao = new TiendaDAOMySQL("jdbc:mysql://localhost/catalogoapp", "root", "");

		// recoger datos de la TiendaDAL cargada en contex

		//ServletContext application = request.getServletContext();
		//TiendaDAL dal = (TiendaDAL) application.getAttribute("dal");

		// recojer la opcion que se carga en la url

		String op = request.getParameter("op");

		// actuar en consecuencia de la opcion
		dao.abrir();
		if (op == null) {// si op el null se cargan los productos
			// si op el null se cargan los productos
			Usuario[] usuarios = dao.findAllUsuario();
			dao.cerrar();

			request.setAttribute("usuarios", usuarios);

			request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);
		} else {
			String nombre = request.getParameter("nombre");

			Usuario usuario;

			switch (op) {
			case "modificar":
			case "borrar":
				dao.abrir();
				usuario = dao.findByNombreUsuario(nombre);
				dao.cerrar();
				request.setAttribute("usuario", usuario);
			case "alta":
				request.getRequestDispatcher(RUTA_FORMULARIO).forward(request, response);
				break;
			default:
				request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);
			}
		}
	}

}
