package com.ipartek.danilozano.Web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.ipartek.danilozano.DAL.TiendaDAL;
import com.ipartek.danilozano.DAL.TiendaDALFactory;
import com.ipartek.danilozano.Tipos.Producto;
import com.ipartek.danilozano.Tipos.Usuario;

@WebListener
public class ListenerUsuario implements ServletContextListener, HttpSessionListener {
	private static Logger log = Logger.getLogger(LoginServlet.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent sc) {
		log.info( "productos y usuarios creados " ); 
		ServletContext application = sc.getServletContext();
		 TiendaDAL tiendaDAL = (TiendaDAL) application.getAttribute("dal");
			
			 tiendaDAL = TiendaDALFactory.getProductosDAL();
			 tiendaDAL = TiendaDALFactory.getUsuariosDAL();
			 tiendaDAL.alta(new Producto( "sandia", "descripcion1", 1.8));
			 tiendaDAL.alta(new Producto( "manzana", "descripcion2", 0.73));
			 tiendaDAL.alta(new Producto( "limon", "descripcion3", 0.6));
			 tiendaDAL.alta(new Producto( "naranja", "descripcion4", 0.69));
			 tiendaDAL.alta(new Producto( "kiwi", "descripcion5", 1.1));
			 tiendaDAL.alta(new Producto( "pera", "descripcion6", 0.81));
			 tiendaDAL.alta(new Usuario("admin", "pass"));
			 tiendaDAL.alta(new Usuario("usuario1", "pass1"));
			 application.setAttribute("dal", tiendaDAL);
			 

		
		
		
	}

	private ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

}