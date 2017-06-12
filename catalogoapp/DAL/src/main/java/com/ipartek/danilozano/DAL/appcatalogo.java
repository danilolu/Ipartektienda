package com.ipartek.danilozano.DAL;

import com.ipartek.danilozano.Tipos.Producto;
import com.ipartek.danilozano.Tipos.Usuario;

public class appcatalogo {
	public static TiendaDAO dao = null;

	public static void main(String[] args) {
		try {

			dao = new TiendaDAOMySQL("jdbc:mysql://localhost/catalogoapp", "root", "");

			dao.abrir();

			listado();

			Usuario usuario = new Usuario("usuario14", "pass4");

			String nombre = usuario.getNombre();
			dao.insert(usuario);
			System.out.println("Se ha insertado un nuevo registro con el nombre: " + nombre);

			usuario = dao.findByNombreUsuario(nombre);
			System.out.println("Usuario: " + nombre + " = " + usuario);

			listado();

			usuario.setPass("MODIFICADO");
			dao.update(usuario);
			System.out.println("Se ha modificado el registro " + nombre);

			listado();

			dao.delete(usuario);
			System.out.println("Se ha borrado el registro " + nombre);

			listado();

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

			listado();

			Producto producto = new Producto("producto1", "descripcion 1", 3);

			int id = dao.insert(producto);
			System.out.println("Se ha insertado un nuevo registro con el id: " + id);

			producto = dao.findByIdProducto(id);
			System.out.println("producto:" + id + "=" + producto);

			listado();

			producto.setNombre("MODIFICADO");
			dao.update(producto);
			System.out.println("Se ha modificado el registro " + id);

			listado();

			dao.delete(producto);
			System.out.println("Se ha borrado el registro " + producto);

			listado();

		} catch (DAOException e) {
			e.printStackTrace();
		} finally {
			dao.cerrar();
		}
	}

	// metodo para mostrar la lista
	private static void listado() {
		System.out.println("\nLISTADO\n=======");

		for (Usuario u : dao.findAllUsuario())
			System.out.println(u);

		System.out.println();
	}

}
