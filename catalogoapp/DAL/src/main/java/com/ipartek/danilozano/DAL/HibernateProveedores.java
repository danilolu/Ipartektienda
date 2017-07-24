package com.ipartek.danilozano.DAL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ipartek.danilozano.Tipos.Proveedor;

public class HibernateProveedores {

	private static EntityManager manager;

	private static EntityManagerFactory emf;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("persistencia");
		manager = emf.createEntityManager();

		manager.getTransaction().begin();

		Proveedor proveedor = new Proveedor("nombre552", "comentarios", 99999999);

		manager.persist(proveedor);
		manager.getTransaction().commit();
		List<Proveedor> proveedores = (List<Proveedor>) manager.createQuery("FROM Proveedor").getResultList();
		System.out.println(proveedores.size());
		manager.close();
		emf.close();

	}

}
