package com.ipartek.danilozano.DAL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ipartek.danilozano.Tipos.Proveedor;

public class ProveedorDalHibernate implements ProveedorDAL {
	private static EntityManager manager;

	private static EntityManagerFactory emf;

	@Override
	public void alta(Proveedor proveedor) {
		emf = Persistence.createEntityManagerFactory("persistencia");
		manager = emf.createEntityManager();

		manager.getTransaction().begin();
		manager.merge(proveedor);
		manager.getTransaction().commit();

	}

	@Override
	public void modificar(Proveedor proveedor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrar(Proveedor proveedor) {

		// manager.getTransaction().begin();
		// manager.remove(proveedor);
		// manager.getTransaction().commit();

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
		// TODO Auto-generated method stub
		return manager.find(Proveedor.class, nombre_p);
	}

	@Override
	public Proveedor[] buscarTodosLosUsuarios() {
		@SuppressWarnings("unchecked")
		List<Proveedor> proveedores = (List<Proveedor>) manager.createQuery("FROM Proveedor").getResultList();
		return proveedores.toArray(new Proveedor[proveedores.size()]);
	}

}
