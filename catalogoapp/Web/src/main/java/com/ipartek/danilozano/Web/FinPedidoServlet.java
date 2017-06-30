package com.ipartek.danilozano.Web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
import com.ipartek.danilozano.Tipos.Factura;
import com.ipartek.danilozano.Tipos.Producto;

@WebServlet("/finpedido")
public class FinPedidoServlet extends HttpServlet {
	public static TiendaDAO dao = null;
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(Carrito.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao = new TiendaDAOMySQL("jdbc:mysql://localhost/catalogoapp", "root", "");

		ServletContext application = getServletContext();
		HttpSession session = request.getSession();
		String op = request.getParameter("op");
		Carrito carrito = (Carrito) session.getAttribute("carrito");
		log.info(" finpedido descripcion carrito = " + carrito.getNombre_usuarios() + "  " + carrito);

		Producto[] carritoArr = null;
		Integer numeroProductos = 0;
		Double precioTotal = 0.0;

		int idcarrito;
		String nombre_usuarios = (String) session.getAttribute("nombre");
		Date fecha = new Date();

		if (request.getParameter("idcarrito") == null || request.getParameter("idcarrito") == "") {
			idcarrito = 0;
		} else {
			idcarrito = Integer.parseInt(request.getParameter("idcarrito"));// pasar
																			// de
																			// String
																			// a
																			// int
		}

		for (Producto p : carrito.buscarTodosLosProductos()) {

		}

		try {
			carritoArr = carrito.buscarTodosLosProductos();
			numeroProductos = carritoArr.length;
			precioTotal = carrito.precioTotal();

		} catch (NullPointerException npe) {
			request.getRequestDispatcher("/login");
		}

		session.setAttribute("productosArr", carritoArr);

		session.setAttribute("numeroProductos", numeroProductos);

		session.setAttribute("precioTotal", precioTotal);

		if (op == null) {

			try {
				session.setAttribute("numeroProductos", carrito.buscarTodosLosProductos().length);
			} catch (NullPointerException npe) {
				carrito = new Carrito(idcarrito, nombre_usuarios, fecha);
				session.setAttribute("carrito", carrito);
				session.setAttribute("productosArr", carrito.buscarTodosLosProductos());
				session.setAttribute("numeroProductos", carrito.buscarTodosLosProductos().length);
			}

			request.getRequestDispatcher("/WEB-INF/vistas/finpedido.jsp").forward(request, response);

		} else {

			Producto producto;
			switch (op) {
			case "vaciar":
				log.info("Pedido pagado, Carrito reseteadoo");

				for (Producto p : carrito.buscarTodosLosProductos()) {

					int id = p.getId();
					carrito.quitarDelCarrito(p);

					dao.abrir();
					producto = dao.findByIdProducto(id);
					dao.updateStockQuitado(producto);

					dao.resetCant(producto);

					dao.cerrar();
				}

				session.setAttribute("carrito", carrito);
				session.setAttribute("productosArr", carrito.buscarTodosLosProductos());
				session.setAttribute("numeroProductos", carrito.buscarTodosLosProductos().length);
				session.setAttribute("numeroProductostotal", carrito.totalProductos());

				request.getRequestDispatcher("/carrito").forward(request, response);
				break;
			case "quitar":
				log.info("Producto  quitado del carrito");
				int id = Integer.parseInt(request.getParameter("id"));
				dao.abrir();
				producto = dao.findByIdProducto(id);
				dao.resetCant(producto);

				dao.updateStockQuitado(producto);
				dao.cerrar();
				producto = carrito.buscarPorId(id);
				carrito.quitarDelCarrito(id);

				session.setAttribute("carrito", carrito);
				session.setAttribute("productosArr", carrito.buscarTodosLosProductos());
				session.setAttribute("numeroProductos", carrito.buscarTodosLosProductos().length);
				session.setAttribute("numeroProductostotal", carrito.totalProductos());

				session.setAttribute("precioTotal", carrito.precioTotal());
				request.getRequestDispatcher("/WEB-INF/vistas/finpedido.jsp").forward(request, response);
				break;
			case "pagar":
				if (precioTotal == 0) {
					carrito.setErrores("El carrito esta vacio");
					request.setAttribute("carrito", carrito);
					request.getRequestDispatcher("/WEB-INF/vistas/finpedido.jsp").forward(request, response);

				} else {

					dao.abrir();

					dao.insertFacturasProductos(carrito);

					dao.cerrar();
					carrito = new Carrito(idcarrito, nombre_usuarios, fecha);
					carrito.buscarTodosLosProductos();
					session.setAttribute("carrito", carrito);
					session.setAttribute("productosArr", carrito.buscarTodosLosProductos());
					session.setAttribute("numeroProductos", carrito.buscarTodosLosProductos().length);
					session.setAttribute("numeroProductostotal", carrito.totalProductos());

					request.getRequestDispatcher("/WEB-INF/vistas/pagado.jsp").forward(request, response);
				}

				break;
			case "facturas":

				ArrayList<Factura> facturaspornombre = new ArrayList<Factura>();
				dao.abrir();
				String nombre_usuario = request.getParameter("nombre_usuario");

				for (Factura f : dao.findallfacturastotal())
					if (f.getNombre_usuario().equals(nombre_usuario)) {
						facturaspornombre.add(f);
					}
				dao.cerrar();
				request.setAttribute("facturaspornombre", facturaspornombre);
				request.getRequestDispatcher("/WEB-INF/vistas/facturasusuariopornombre.jsp").forward(request, response);
				break;
			case "desglosar":

				ArrayList<Factura> facturasporid = new ArrayList<Factura>();
				dao.abrir();
				int id_facturas;

				id_facturas = Integer.parseInt(request.getParameter("id_facturas"));

				for (Factura f : dao.findallfacturas())
					if (f.getId_facturas() == id_facturas) {
						facturasporid.add(f);
					}
				dao.cerrar();
				request.setAttribute("facturasporid", facturasporid);
				request.getRequestDispatcher("/WEB-INF/vistas/facturasusuario.jsp").forward(request, response);
				break;
			case "imprimir":

				ArrayList<Factura> facturasid = new ArrayList<Factura>();
				dao.abrir();
				int id_facturas2;

				id_facturas2 = Integer.parseInt(request.getParameter("id_facturas"));

				for (Factura f : dao.findallfacturas())
					if (f.getId_facturas() == id_facturas2) {
						facturasid.add(f);
					}
				dao.cerrar();

				request.setAttribute("facturasid", facturasid);
				ArrayList<Factura> facturasnombre = new ArrayList<Factura>();
				dao.abrir();

				for (Factura f : dao.findallfacturastotal())
					if (f.getId_facturas() == id_facturas2) {
						facturasnombre.add(f);
					}
				dao.cerrar();
				request.setAttribute("facturasnombre", facturasnombre);

				request.getRequestDispatcher("/WEB-INF/vistas/factura.jsp").forward(request, response);
				break;

			default:
				request.getRequestDispatcher("/WEB-INF/vistas/finpedido.jsp").forward(request, response);

			}

		}
	}
}