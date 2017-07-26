package com.ipartek.danilozano.DAL;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.ipartek.danilozano.Tipos.Proveedor;

public class ProveedorDalHibernate implements ProveedorDAL {
	private static Logger log = Logger.getLogger(ProveedorDalHibernate.class);
	private static EntityManager manager;

	private static EntityManagerFactory emf;

	public void abrir() {
		emf = Persistence.createEntityManagerFactory("persistencia");
		manager = emf.createEntityManager();
		manager.getTransaction().begin();

	}

	public void cerrar() {
		manager.getTransaction().commit();
	}

	@Override
	public void alta(Proveedor proveedor) {

		abrir();
		manager.merge(proveedor);
		cerrar();

	}

	@Override
	public void modificar(Proveedor proveedor) {

		abrir();
		manager.merge(proveedor);
		cerrar();
		log.info("telefono: " + proveedor.getTelefono());
	}

	@Override
	public void borrar(Proveedor proveedor) {

		manager.getTransaction().begin();
		manager.remove(proveedor);
		manager.getTransaction().commit();
	}

	@Override
	public void borrar(String nombre_p) {
		Proveedor proveedor = new Proveedor();
		proveedor = this.buscarPorNombre(nombre_p);

		this.borrar(proveedor);

	}

	@Override
	public Proveedor buscarPorNombre(String nombre_p) {
		abrir();
		Proveedor proveedor = manager.find(Proveedor.class, nombre_p);
		cerrar();
		log.info("el proveedor buscado por nombre es: " + proveedor);
		return proveedor;
	}

	@Override
	public Proveedor[] buscarTodosLosProveedores() {
		abrir();
		@SuppressWarnings("unchecked")
		ArrayList<Proveedor> proveedores = (ArrayList<Proveedor>) manager.createQuery("FROM Proveedor").getResultList();
		cerrar();
		return proveedores.toArray(new Proveedor[proveedores.size()]);
	}

}
