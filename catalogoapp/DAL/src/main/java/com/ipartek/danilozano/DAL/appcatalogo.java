package com.ipartek.danilozano.DAL;

import com.ipartek.danilozano.Tipos.Factura;
import com.ipartek.danilozano.Tipos.Producto;
import com.ipartek.danilozano.Tipos.Usuario;

public class appcatalogo {
	public static TiendaDAO dao = null;

	public static void main(String[] args) {
		try {

			dao = new TiendaDAOMySQL("jdbc:mysql://localhost/catalogoapp", "root", "");

			dao.abrir();

			listadofacturas1();

			listadofacturas2();

			Factura factura = new Factura();

			int id_facturas = 15;
			factura = dao.findByIdFactura(id_facturas);
			System.out.println("factura: " + id_facturas + " = " + factura);

		} catch (DAOException e) {
			e.printStackTrace();
		} finally {
			dao.cerrar();
		}
	}

	public static void mainusuariofunciona(String[] args) {
		try {

			dao = new TiendaDAOMySQL("jdbc:mysql://localhost/catalogoapp", "root", "");

			dao.abrir();

			listadousuario();

			Usuario usuario = new Usuario("usuario14", "pass4");

			String nombre = usuario.getNombre();
			dao.insert(usuario);
			System.out.println("Se ha insertado un nuevo registro con el nombre: " + nombre);

			usuario = dao.findByNombreUsuario(nombre);
			System.out.println("Usuario: " + nombre + " = " + usuario);

			listadousuario();

			usuario.setPass("MODIFICADO");
			dao.update(usuario);
			System.out.println("Se ha modificado el registro " + nombre);

			listadousuario();

			dao.delete(usuario);
			System.out.println("Se ha borrado el registro " + nombre);

			listadousuario();

		} catch (DAOException e) {
			e.printStackTrace();
		} finally {
			dao.cerrar();
		}
	}

	public static void mainproductofunciona(String[] args) {
		try {

			dao = new TiendaDAOMySQL("jdbc:mysql://localhost/catalogoapp", "root", "");

			dao.abrir();

			listadoproducto();

			Producto producto = new Producto("producto1", "descripcion 1", 3, 8);

			int id = dao.insert(producto);
			System.out.println("Se ha insertado un nuevo registro con el id: " + id);

			producto = dao.findByIdProducto(id);
			System.out.println("producto:" + id + "=" + producto);

			listadoproducto();

			producto.setNombre("MODIFICADO");
			dao.update(producto);
			System.out.println("Se ha modificado el registro " + id);

			listadoproducto();

			dao.delete(producto);
			System.out.println("Se ha borrado el registro " + producto);

			listadoproducto();

		} catch (DAOException e) {
			e.printStackTrace();
		} finally {
			dao.cerrar();
		}
	}

	// metodo para mostrar la lista
	private static void listadousuario() {
		System.out.println("\nLISTADO\n=======");

		for (Usuario u : dao.findAllUsuario())
			System.out.println(u);

		System.out.println();

	}

	private static void listadoproducto() {
		System.out.println("\nLISTADO\n=======");

		for (Producto p : dao.findAllProducto())
			System.out.println(p);

		System.out.println();

	}

	private static void listadofacturas1() {
		System.out.println("\nLISTADO\n=======");

		for (Factura f1 : dao.findallfacturas())
			System.out.println(f1);

		System.out.println();

	}

	private static void listadofacturas2() {
		System.out.println("\nLISTADO\n=======");

		for (Factura f2 : dao.findallfacturastotal())
			System.out.println(f2);

		System.out.println();

	}
	// private static void listadofacturas3() {
	// System.out.println("\nLISTADO\n=======");
	// int id_facturas=15;
	// for (Factura f3 : dao.findByIdFactura(_))
	// System.out.println(f3);
	//
	// System.out.println();
	//
	// }

}
