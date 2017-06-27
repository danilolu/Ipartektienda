package com.ipartek.danilozano.Web;

import java.io.IOException;
import java.util.ArrayList;

//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

//import com.ipartek.danilozano.DAL.TiendaDAL;
import com.ipartek.danilozano.DAL.TiendaDAO;
import com.ipartek.danilozano.DAL.TiendaDAOMySQL;
import com.ipartek.danilozano.Tipos.Factura;

@WebServlet("/admin/facturacrud")
public class FacturaCRUDServlet extends HttpServlet {
	public static TiendaDAO dao = null;
	private static Logger log = Logger.getLogger(ProductoFormServlet.class);
	static final String RUTA_FORMULARIO = "/WEB-INF/vistas/facturatotal.jsp";
	static final String RUTA_LISTADO = "/WEB-INF/vistas/facturacrud.jsp";
	static final String RUTA_SERVLET_LISTADO = "/admin/facturacrud";

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao = new TiendaDAOMySQL("jdbc:mysql://localhost/catalogoapp", "root", "");

		String op = request.getParameter("op");

		// actuar en consecuencia de la opcion
		dao.abrir();
		if (op == null) {// si op el null se cargan los productos
			// si op el null se cargan los productos
			Factura[] facturas = dao.findallfacturas();

			dao.cerrar();

			request.setAttribute("facturas", facturas);

			request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);

		} else {

			switch (op) {
			case "factotal":
				log.info("paso por factoal");
				Factura[] facturastotal = dao.findallfacturastotal();

				dao.cerrar();

				request.setAttribute("facturastotal", facturastotal);
				request.getRequestDispatcher(RUTA_FORMULARIO).forward(request, response);
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
				request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);
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
				request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);
			}
		}
	}

}
