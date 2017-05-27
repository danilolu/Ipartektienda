package com.ipartek.danilozano.Web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.ipartek.danilozano.DAL.DAL;
import com.ipartek.danilozano.DAL.DALFactory;
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
		 DAL dal = (DAL) application.getAttribute("dal");
			
			 dal = DALFactory.getProductosDAL();
			 dal = DALFactory.getUsuariosDAL();
			 dal.alta(new Producto(1, "sandia", "descripcion1", 1));
			 dal.alta(new Producto(2, "manzana", "descripcion2", 2));
			 dal.alta(new Usuario("admin", "pass"));
			 dal.alta(new Usuario("usuario1", "pass1"));
			 application.setAttribute("dal", dal);
			 

		
		
		
	}

	private ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

}