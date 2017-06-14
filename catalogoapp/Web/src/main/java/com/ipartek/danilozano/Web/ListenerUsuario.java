package com.ipartek.danilozano.Web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ipartek.danilozano.DAL.TiendaDAL;
import com.ipartek.danilozano.DAL.TiendaDALFactory;
import com.ipartek.danilozano.DAL.TiendaDAO;
import com.ipartek.danilozano.DAL.TiendaDAOMySQL;
import com.ipartek.danilozano.Tipos.Producto;
import com.ipartek.danilozano.Tipos.Usuario;

@WebListener
public class ListenerUsuario implements ServletContextListener, HttpSessionListener {
	private static Logger log = Logger.getLogger(LoginServlet.class);
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

		// cargar la configuracion de log4j en el context cuando no usabamos
		// base de datos

		// cargar usuarios y productos en el context
		log.info("productos y usuarios creados ");
		ServletContext application = sc.getServletContext();
		TiendaDAL tiendaDAL = (TiendaDAL) application.getAttribute("dal");

		tiendaDAL = TiendaDALFactory.getProductosDAL1();
		// tiendaDAL = TiendaDALFactory.getUsuariosDAL1();
		// tiendaDAL.alta(new Producto("Judas", "Ale", 1.8));
		// tiendaDAL.alta(new Producto("Affligem Triple", "Abadia", 1.95));
		// tiendaDAL.alta(new Producto("Paulaner", "Trigo", 1.6));
		// tiendaDAL.alta(new Producto("Guinnes", "Negra", 1.59));
		// tiendaDAL.alta(new Producto("Murphys", "Roja", 1.53));
		// tiendaDAL.alta(new Producto("Chimay roja", "Abadia", 2.1));
		// tiendaDAL.alta(new Producto("Super Bock", "Lager ", 0.80));
		// tiendaDAL.alta(new Usuario("admin", "pass"));
		// tiendaDAL.alta(new Usuario("usuario1", "pass1"));
		application.setAttribute("dal", tiendaDAL);

		// cargar la configuracion de log4j en el context
		PropertyConfigurator.configure(ListenerUsuario.class.getClassLoader().getResource("log4j.properties"));

		dao = new TiendaDAOMySQL("jdbc:mysql://localhost/catalogoapp", "root", "");

		dao.abrir();
		for (Usuario u : dao.findAllUsuario())
			log.info(u);
		for (Producto p : dao.findAllProducto())
			tiendaDAL.alta(p);
		application.setAttribute("dal", tiendaDAL);
		dao.cerrar();
	}

	private ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

}