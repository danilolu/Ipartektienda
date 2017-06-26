package com.ipartek.danilozano.Web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.danilozano.DAL.TiendaDAO;
import com.ipartek.danilozano.DAL.TiendaDAOMySQL;
import com.ipartek.danilozano.Tipos.Factura;

@WebServlet("/admin/usuarioform")
public class nousadoFacturaFormServlet extends HttpServlet {
	public static TiendaDAO dao = null;
	private static Logger log = Logger.getLogger(ProductoFormServlet.class);

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		dao = new TiendaDAOMySQL("jdbc:mysql://localhost/catalogoapp", "root", "");
		// definir ruteo
		RequestDispatcher rutaListado = request.getRequestDispatcher(UsuarioCRUDServlet.RUTA_SERVLET_LISTADO);
		RequestDispatcher rutaFormulario = request.getRequestDispatcher(UsuarioCRUDServlet.RUTA_FORMULARIO);

		// recojer la opcion que se carga en la url
		String op = request.getParameter("opform");

		// variables del objeto Usuario

		double precio, total;
		String nombre_usuario, nombre_producto;
		Date fecha;

		int id_facturas;
		if (request.getParameter("id_facturas") == null || request.getParameter("id_facturas") == "") {
			id_facturas = 0;
		} else {
			id_facturas = Integer.parseInt(request.getParameter("id_facturas"));
		}

		int cant;

		if (request.getParameter("cant") == null || request.getParameter("cant") == "") {
			cant = 0;
		} else {
			cant = Integer.parseInt(request.getParameter("cant"));
		}

		// crear objeto Pproducto
		Factura factura = new Factura();

		// actuar en consecuencia de la opcion recogida anteriormente

		if (op == null) {
			rutaListado.forward(request, response);
			return;
		}

		switch (op) {
		case "factotal":
			log.info("paso por factoal");
			Factura[] facturastotal = dao.findallfacturastotal();

			dao.cerrar();

			request.setAttribute("facturastotal", facturastotal);

			request.getRequestDispatcher("/WEB-INF/vistas/facturacrud.jsp").forward(request, response);

			// case "facporid":
			//
			// TreeMap<Integer, Factura> facturas = null;
			// dao.abrir();
			// for (Factura f : dao.findallfacturastotal())
			// if (f.getId_facturas() == id_facturas) {
			// facturas.put(f.getId_facturas(), factura);
			//
			// }
			// dao.cerrar();

		}

	}
}
