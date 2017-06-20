package com.ipartek.danilozano.Web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.danilozano.DAL.TiendaDAO;
import com.ipartek.danilozano.DAL.TiendaDAOMySQL;
import com.ipartek.danilozano.Tipos.Carrito;
import com.ipartek.danilozano.Tipos.Producto;

@WebServlet("/carrito")
public class CarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(CarritoServlet.class);
	public static TiendaDAO dao = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recoger dados cargados en context y en sesion
		ServletContext application = getServletContext();
		HttpSession session = request.getSession();
		// cargar arrays de los productos y del carrito
		dao = new TiendaDAOMySQL("jdbc:mysql://localhost/catalogoapp", "root", "");
		dao.abrir();

		Producto[] catalogo = dao.findAllProducto();

		dao.cerrar();

		application.setAttribute("catalogo", catalogo);

		// crear objeto de Carrito si el carrito es null
		Carrito carrito = (Carrito) session.getAttribute("carrito");

		if (carrito == null) {

			carrito = new Carrito();

		}

		String op = request.getParameter("op");

		// actuar segun la opcion que halla llegado
		if (op == null) {

			session.setAttribute("carrito", carrito);

			session.setAttribute("numeroProductos", carrito.buscarTodosLosProductos().length);

			request.getRequestDispatcher("/WEB-INF/vistas/catalogo.jsp").forward(request, response);

		} else {

			switch (op) {

			case "logout":

				session.invalidate();

				session = request.getSession();

				carrito = new Carrito();

				session.setAttribute("carrito", carrito);

				session.setAttribute("numeroProductos", carrito.buscarTodosLosProductos().length);

				request.getRequestDispatcher("/WEB-INF/vistas/catalogo.jsp").forward(request, response);

				break;

			case "anadir":

				Producto producto;
				int id = Integer.parseInt(request.getParameter("id"));

				dao.abrir();
				producto = dao.findByIdProducto(id);
				dao.updateStockAnadido(producto);
				dao.cerrar();

				carrito.anadirAlCarrito(producto);

				log.info("Añadido " + producto + " al carrito");

				session.setAttribute("carrito", carrito);

				session.setAttribute("numeroProductos", carrito.buscarTodosLosProductos().length);

				request.getRequestDispatcher("/WEB-INF/vistas/catalogo.jsp").forward(request, response);

				break;

			default:

				request.getRequestDispatcher("/WEB-INF/vistas/catalogo.jsp").forward(request, response);

			}
		}
	}

}