package com.ipartek.formacion.ejemplojdbc_dani;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ipartek.formacion.ejemplojdbc_dani.dao.DAOExcepcion;
import com.ipartek.formacion.ejemplojdbc_dani.dao.UsuarioDAO;
import com.ipartek.formacion.ejemplojdbc_dani.dao.UsuarioDAOMySQL;
import com.ipartek.formacion.ejemplojdbc_dani.tipos.Usuario;

public class App {
	public static UsuarioDAO dao = null;

	public static void maintransacciones(String[] args) {
		try {
			dao = new UsuarioDAOMySQL("jdbc:mysql://localhost/ipartek", "dani", "danipass");

			dao.abrir();

			dao.iniciarTransaccion();

			Usuario usuario;
			for (int i = 100; i < 200; i++) {

				usuario = new Usuario();
				usuario.setUsername("usuario" + i);
				usuario.setPassword("usuario" + i + "pass");
				usuario.setNombre_completo("usuario" + i + " Usuariez");
				usuario.setId_roles(2);

				// if para que no complete la insercion
				// if (i > 150)
				// throw new RuntimeException("casque accidental");
				// else

				// DELETE FROM `usuarios` WHERE username LIKE 'usuario1__'
				// ///borrar los 100 usuarios
				dao.insert(usuario);

			}

			dao.confirmarTransaccion();

		} catch (Exception e) {
			dao.deshacerTransaccion();
			System.out.println("ha cascado");
		} finally {
			dao.cerrar();

		}

	}

	public static void main(String[] args) {
		try {
			dao = new UsuarioDAOMySQL("jdbc:mysql://localhost/ipartek", "dani", "danipass");

			dao.abrir();

			listado();

			Usuario usuario = new Usuario(0, 2, "Nuevo nuevez", "nuevopass", "nuevo100");

			int id = dao.insert(usuario);
			System.out.println("Se ha insertado un nuevo registro con el id " + id);

			usuario = dao.findById(id);
			System.out.println("Usuario ID:" + id + "=" + usuario);

			listado();

			usuario.setNombre_completo("MODIFICADO");
			dao.update(usuario);
			System.out.println("Se ha modificado el registro " + id);

			listado();

			dao.delete(usuario);
			System.out.println("Se ha borrado el registro " + id);

			listado();

		} catch (DAOExcepcion e) {
			e.printStackTrace();
		} finally {
			dao.cerrar();
		}
	}

	// metodo para mostrar la lista
	private static void listado() {
		System.out.println("\nLISTADO\n=======");

		for (Usuario u : dao.findAll())
			System.out.println(u);

		System.out.println();
	}

	// try {
	// UsuarioDAO dao = new UsuarioDAOMySQL();// crear objeto dao
	// for (Usuario u : dao.findAll())//buscar todos// recorrer el
	// metodo dao findAll
	// System.out.println(u);
	//
	// int id=5;
	// Usuario usuario = dao.findById(5);//buscarporid
	// System.out.println("Usuario ID: "+id+ " + "+usuario);
	// /////////////////////////////////////////////////////////////////////////////
	// Usuario usuarioInsert = new Usuario(0, 2, "Nuevo2 Nuevez",
	// "nuevo pass", "nuevo2");// insertar
	// dao.insert(usuarioInsert);
	// } catch (DAOExcepcion e) {
	// e.printStackTrace();
	// if (e.getCause() != null)
	// e.getCause().printStackTrace();
	// }
	// //////////////////////////////////////////////////////////
	// Usuario usuarioUpdate = new Usuario(6, 2, " actualizado",
	// " actualizado", " actualizado");// update
	// dao.update(usuarioUpdate);
	// } catch (DAOExcepcion e) {
	// e.printStackTrace();
	// if (e.getCause() != null)
	// e.getCause().printStackTrace();
	// }
	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Usuario usuarioDelete = new Usuario();// //borrar
	// usuarioDelete.setId(1);
	// dao.delete(usuarioDelete);
	// } catch (DAOExcepcion e) {
	// e.printStackTrace();
	// }
	//
	// }

	public static void mainBasico(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();// cargar driver

		String url = "jdbc:mysql://localhost/ipartek?user=root&password=";// conectarse
																			// a
																			// la
																			// base
																			// de
																			// datos

		Connection con = DriverManager.getConnection(url);// interface conexion
		// Statement st = con.createStatement();//
		String sql = "SELECT * FROM usuarios WHERE id=?";// sentencia SQL
		PreparedStatement pst = con.prepareStatement(sql);//
		int id = 1;
		pst.setInt(1, id);// el 1 es la primera interrogacion en la sentencia
							// SQL
		// String sql = "SELECT * FROM usuarios WHERE id="+id;
		ResultSet rs = pst.executeQuery();// st.executeQuery(sql);//
		while (rs.next()) {
			System.out.println(rs.getString("username") + " , " + rs.getString("nombre_completo"));//
		}
		rs.close();
		pst.close();
		// con.close();
		// https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-statements.html

		// insert
		String sqlInsert = "INSERT INTO usuarios (username,password,nombre_completo) VALUES (?,?,?)";
		PreparedStatement pstInsert = con.prepareStatement(sqlInsert);
		String username = "jdbcnuevo2", password = "jdbcpass2", nombre_completo = "JDBC";
		pstInsert.setString(1, username);
		pstInsert.setString(2, password);
		pstInsert.setString(3, nombre_completo);
		int res = pstInsert.executeUpdate();

		System.out.println("se ha modificado " + res + " registros");
		// con.close();

		//
		String sqlUpdate = "UPDATE  usuarios SET username=?,password=?,nombre_completo=?WHERE id=5";
		PreparedStatement pstUpdate = con.prepareStatement(sqlUpdate);

		username = "jdbcnuevoupdate";
		password = "jdbcpassupdate";
		nombre_completo = "JDBCupdate";
		pstUpdate.setString(1, username);
		pstUpdate.setString(2, password);
		pstUpdate.setString(3, nombre_completo);
		pstUpdate.setInt(4, id);
		res = pstUpdate.executeUpdate();

		System.out.println("se ha modificado " + res + " registros");
		con.close();

	}
}
