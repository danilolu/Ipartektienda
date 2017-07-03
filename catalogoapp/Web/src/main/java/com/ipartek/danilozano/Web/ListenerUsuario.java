package com.ipartek.danilozano.Web;

//import javax.servlet.ServletContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

//import com.ipartek.danilozano.DAL.TiendaDAL;
//import com.ipartek.danilozano.DAL.TiendaDALFactory;
import com.ipartek.danilozano.DAL.TiendaDAO;
import com.ipartek.danilozano.DAL.TiendaDAOMySQL;
import com.ipartek.danilozano.Tipos.Producto;
import com.ipartek.danilozano.Tipos.Usuario;

@WebListener
public class ListenerUsuario implements ServletContextListener, HttpSessionListener {
	private static Logger log = Logger.getLogger(ListenerUsuario.class);
	public static TiendaDAO dao = null;

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
		ServletContext application = sc.getServletContext();

		String path = sc.getServletContext().getContextPath();
		application.setAttribute("rutaBase", path);
		log.info("El path es:      " + path);

		PropertyConfigurator.configure(ListenerUsuario.class.getClassLoader().getResource("log4j.properties"));

		dao = new TiendaDAOMySQL("jdbc:mysql://localhost/catalogoapp", "root", "");

		dao.abrir();
		for (Usuario u : dao.findAllUsuario())
			log.info(u);
		for (Producto p : dao.findAllProducto())
			log.info(p);
		dao.cerrar();
	}

}