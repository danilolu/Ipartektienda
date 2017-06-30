package com.ipartek.danilozano.Web;

import java.io.IOException;
import java.util.Date;
import java.util.TreeMap;

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

	private TreeMap<Integer, Producto> carritolista;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recoger dados cargados en context y en sesion
		ServletContext application = getServletContext();
		HttpSession session = request.getSession();
		// variables
		int id;
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		double precio;
		int stock;
		int cant;
		int idcarrito;
		String nombre_usuarios = (String) session.getAttribute("nombre");

		log.info("nombre usuario = " + nombre_usuarios);
		Date fecha = new Date();

		// recoger valores para inicializar variables
		if (request.getParameter("id") == null || request.getParameter("id") == "") {
			id = 0;
		} else {
			id = Integer.parseInt(request.getParameter("id"));// pasar de String
																// a int
		}
		if (request.getParameter("idcarrito") == null || request.getParameter("idcarrito") == "") {
			idcarrito = 0;
		} else {
			idcarrito = Integer.parseInt(request.getParameter("idcarrito"));// pasar
																			// de
																			// String
																			// a
																			// int
		}
		if (request.getParameter("precio") == null || request.getParameter("precio") == "") {
			precio = 0;
		} else {
			precio = Double.parseDouble(request.getParameter("precio"));// pasar
																		// de
																		// String
																		// a
																		// double

		}

		if (request.getParameter("stock") == null || request.getParameter("stock") == "") {
			stock = 0;
		} else {
			stock = Integer.parseInt(request.getParameter("stock"));
		}
		if (request.getParameter("cant") == null || request.getParameter("cant") == "") {
			cant = 0;
		} else {
			cant = Integer.parseInt(request.getParameter("cant"));
		}

		// cargar arrays de los productos y del carrito
		dao = new TiendaDAOMySQL("jdbc:mysql://localhost/catalogoapp", "root", "");
		dao.abrir();

		Producto[] catalogo = dao.findAllProducto();

		dao.cerrar();

		application.setAttribute("catalogo", catalogo);

		// crear objeto de Carrito si el carrito es null
		Carrito carrito = (Carrito) session.getAttribute("carrito");

		if (carrito == null) {

			carrito = new Carrito(idcarrito, nombre_usuarios, fecha);
			log.info(" carritoservlet descripcion carrito = " + carrito.getNombre_usuarios() + "  " + carrito);
		}

		String op = request.getParameter("op");

		// actuar segun la opcion que halla llegado
		if (op == null) {

			session.setAttribute("carrito", carrito);

			session.setAttribute("numeroProductos", carrito.buscarTodosLosProductos().length);

			session.setAttribute("numeroProductostotal", carrito.totalProductos());

			request.getRequestDispatcher("/WEB-INF/vistas/catalogo.jsp").forward(request, response);

		} else {
			Producto producto = new Producto(id, nombre, descripcion, precio, stock, cant);

			switch (op) {
			case "logout":

				dao.abrir();
				dao.resetCant(producto);
				dao.cerrar();
				session.invalidate();

				session = request.getSession();

				carrito = new Carrito(idcarrito, nombre_usuarios, fecha);

				session.setAttribute("carrito", carrito);

				session.setAttribute("numeroProductos", carrito.buscarTodosLosProductos().length);

				request.getRequestDispatcher("/WEB-INF/vistas/catalogo.jsp").forward(request, response);

				break;

			case "anadir":
				carrito.setErrores("");
				request.setAttribute("carrito", carrito);
				dao.abrir();
				dao.updateCant(producto);
				producto = dao.findByIdProducto(id);

				if (carrito.carritoLista.containsKey(producto.getId())) {// buscar
																			// elementos
																			// repetidos

					log.info(" repetido");
					producto.setErrores("Ya has añadido este producto, si quieres modificar la cantidad debes quitarlo del carrito");
					request.setAttribute("producto", producto);
					request.getRequestDispatcher("/WEB-INF/vistas/catalogo.jsp").forward(request, response);
					dao.cerrar();
					break;
				}

				dao.updateStockAnadido(producto);

				catalogo = dao.findAllProducto();

				application.setAttribute("catalogo", catalogo);

				carrito.anadirAlCarrito(producto);

				log.info("Añadido " + producto + " al carrito");

				session.setAttribute("carrito", carrito);

				session.setAttribute("numeroProductos", carrito.buscarTodosLosProductos().length);
				session.setAttribute("numeroProductostotal", carrito.totalProductos());

				request.getRequestDispatcher("/WEB-INF/vistas/catalogo.jsp").forward(request, response);
				dao.cerrar();
				break;

			default:

				request.getRequestDispatcher("/WEB-INF/vistas/catalogo.jsp").forward(request, response);

			}
		}
	}

}