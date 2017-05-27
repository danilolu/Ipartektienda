package com.ipartek.danilozano.Web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.danilozano.Tipos.Usuario;

public class FiltroUsuarios implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("FILTER");
//		HttpServletRequest req = (HttpServletRequest) request;
//
//		HttpSession session = req.getSession();
//
//		Usuario usuario = null;
//
//		if (session != null) {
//			System.out.println("sesion es null");
//			usuario = (Usuario) session.getAttribute("usuario");
//
//		}
//
//		boolean nuevoUsuario = usuario == null;
//		boolean siAdmin = false;
//
//		if (!nuevoUsuario) {
//			System.out.println("no es nuevo usuario");
//			siAdmin = usuario.isAdmin();
//
//		}
//
//		if (!siAdmin) {
//			System.out.println("no soy admin");
//			session.setAttribute("errorLogin", "No tienes permiso para acceder a esa sección");
//			(req.getRequestDispatcher("/login")).forward(request, response);
//
//		} else {
//			System.out.println("soy admin");
//			chain.doFilter(request, response);
//
//		}
//	}
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		HttpSession session = httpReq.getSession();

		

		String nombresesion = (String) session.getAttribute("nombre");
		System.out.println("1" +nombresesion + " hola justo antes de comparar");
		String admin = "admin"; 
		System.out.println("2 compara "+nombresesion+ " con "+ admin);
		
		if (admin.equals(nombresesion)) {
			System.out.println("3 soy admin");
			chain.doFilter(request, response);
			System.out.println("4 se ha enviado a admin/productocrud");
			//request.getRequestDispatcher("/WEB-INF/vistas/admin/productocrud.jsp").forward(request,					response);

			
		} else {
			System.out.println("3 no soy admin");
			httpResp.sendRedirect("/login");//.forward(request, response);
			System.out.println("4 se ha enviado a login");
			
			//request.getRequestDispatcher("/WEB-INF/vistas/productocrud.jsp").forward(request,response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

}
