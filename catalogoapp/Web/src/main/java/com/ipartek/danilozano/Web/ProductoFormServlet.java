package com.ipartek.danilozano.Web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.danilozano.DAL.DALException;
import com.ipartek.danilozano.DAL.IdProductoYaExistenteDALException;
import com.ipartek.danilozano.DAL.DAL;
import com.ipartek.danilozano.Tipos.Producto;

@WebServlet("/admin/productoform")
public class ProductoFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("opform");

		int id;
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");

		double precio;

		if (request.getParameter("id") == null || request.getParameter("id") == "") {
			id = 0;
		} else {
			id = Integer.parseInt(request.getParameter("id"));
		}
		if (request.getParameter("precio") == null || request.getParameter("precio") == "") {
			precio = 0;
		} else {
			precio = Double.parseDouble(request.getParameter("precio"));
		}
		RequestDispatcher rutaListado = request.getRequestDispatcher(ProductoCRUDServlet.RUTA_SERVLET_LISTADO);
		RequestDispatcher rutaFormulario = request.getRequestDispatcher(ProductoCRUDServlet.RUTA_FORMULARIO);

		if (op == null) {
			rutaListado.forward(request, response);
			return;
		}

		Producto producto = new Producto(id,nombre, descripcion, precio);
		producto.setId(id);
		ServletContext application = getServletContext();
		DAL dal = (DAL) application.getAttribute("dal");

		switch (op) {
		case "alta":

			if (id == 0 || nombre == null || nombre == null || descripcion == null || precio == 0) {
				producto.setErrores("- Los campos deben estar rellenados </br> - ID y precio deben ser numericos y no tener valor 0");

				request.setAttribute("producto", producto);
				rutaFormulario.forward(request, response);

			} else {
				try {
					dal.alta(producto);
					rutaListado.forward(request, response);
				} catch (IdProductoYaExistenteDALException a) {
					producto.setErrores("ID ya existente");
					request.setAttribute("producto", producto);
					rutaFormulario.forward(request, response);

				}

			}

			break;
		case "modificar":
			if (id == 0 || nombre == null || descripcion == null || precio == 0) {
				producto.setErrores("Los campos deben estar rellenados y no deben tener valor 0");
				request.setAttribute("producto", producto);
				rutaFormulario.forward(request, response);

			} else
				try {
					dal.modificar(producto);
				} catch (DALException de) {
					producto.setErrores(de.getMessage());
					request.setAttribute("producto", producto);
					rutaFormulario.forward(request, response);
					return;
				}
			rutaListado.forward(request, response);

			break;
		case "borrar":
			dal.borrar(producto);
			rutaListado.forward(request, response);

			break;
		}
	}

}
