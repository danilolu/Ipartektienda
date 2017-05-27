package com.ipartek.danilozano.Web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;









import com.ipartek.danilozano.DAL.DAL;
import com.ipartek.danilozano.DAL.DALException;
import com.ipartek.danilozano.DAL.DALFactory;
import com.ipartek.danilozano.DAL.IdProductoYaExistenteDALException;
import com.ipartek.danilozano.Tipos.Producto;
import com.ipartek.danilozano.Tipos.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginServlet.class);
	/* package */static final String RUTA = "/WEB-INF/vistas/";
	private static final String RUTA_PRINCIPAL = "/admin/productocrud";
	private static final String RUTA_LOGIN = RUTA + "/login.jsp";
	static final String RUTA_SERVLET_LISTADO = "/login";
	public static final int TIEMPO_INACTIVIDAD = 30 * 60;

	/* package */static final int MINIMO_CARACTERES = 4;
	static final String USUARIOS_DAL = "dal";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombresesion = request.getParameter("nombre");
		
		HttpSession session = request.getSession();

		session.setAttribute("nombre", nombresesion);
		
		nombresesion = (String) session.getAttribute("nombre");		

		ServletContext application = request.getServletContext();
		log.info( nombresesion+" pasa por aqui  " );

		// Recoger datos de vistas
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		String opcion = request.getParameter("opcion");

		// Crear modelos en base a los datos
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setPass(pass);

		// Llamada a l�gica de negocio

		DAL usuariosDAL = (DAL) application.getAttribute(USUARIOS_DAL);

		if (usuariosDAL == null) {
			usuariosDAL = DALFactory.getUsuariosDAL();
		}

		// S�lo para crear una base de datos falsa con el
		// contenido de un usuario "javi", "lete"
		// usuarioDAL.alta(new Usuario("javi", "lete"));

		
		session.setMaxInactiveInterval(TIEMPO_INACTIVIDAD);

		Cookie cookie = new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(TIEMPO_INACTIVIDAD);
		response.addCookie(cookie);

		

		// ESTADOS
		boolean esValido = usuariosDAL.validar(usuario);

		boolean sinParametros = usuario.getNombre() == null;

		boolean sesionsinParametros = nombresesion == null;
		
		boolean esUsuarioYaRegistrado = session.getAttribute("usuario") != null;

		boolean quiereSalir = "logout".equals(opcion);

		boolean nombreValido = usuario.getNombre() != null && usuario.getNombre().length() >= MINIMO_CARACTERES;
		boolean passValido = !(usuario.getPass() == null || usuario.getPass().length() < MINIMO_CARACTERES);

		// Redirigir a una nueva vista
		if (quiereSalir) {
			session.invalidate();// para hacer el logout
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
			log.info(  " sesion finalizada" );

		} else if (esUsuarioYaRegistrado) {
			log.info( nombresesion+" pasa por es usuarioregistrado  " );
			request.getRequestDispatcher(RUTA_PRINCIPAL).forward(request, response);
			
		} else if (sinParametros) {
			log.info( nombresesion+" pasa por sinparametros  " );
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
		} else if (sesionsinParametros) {
			log.info( nombresesion+" pasa por sesionsinparametros  " );
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
		} else if (!nombreValido || !passValido) {
			log.info( nombresesion+" pasa por nombre no valido  " );
			usuario.setErrores("El nombre y la pass deben tener como m�nimo " + MINIMO_CARACTERES + " caracteres y son ambos requeridos");
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
		} else if (esValido) {
			log.info( nombresesion+" ha iniciado sesion " );
			session.setAttribute("usuario", usuario);
			response.sendRedirect(RUTA_PRINCIPAL);//.forward(request, response);
			
		} else {
			log.info( "inicio de sesion erronea" );

			usuario.setErrores("El usuario y contrase�a introducidos no son v�lidos");
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
			
		}
	}
	}
