package com.ipartek.formacion.ejemplojdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplojdbc.tipos.Usuario;

public class UsuarioDaoMySQL implements UsuarioDAO {

	private Connection con;
	private String url = "jdbc:mysql://localhost/ipartek";
	private String mysqlUser = "root";
	private String mysqlPass = "";

	private final static String FIND_ALL = "SELECT * FROM usuarios";
	private final static String FIND_BY_ID = "SELECT * FROM usuarios Where id=?";
	private final static String INSERT = "INSERT INTO usuarios (username, password, nombre_completo, id_roles) VALUES (?,?,?,?)";
	private final static String UPDATE = "UPDATE usuarios SET username=?, password=?, nombre_completo=?, id_roles=? WHERE id=?";
	private final static String DELETE = "DELETE FROM usuarios WHERE id=?";

	private PreparedStatement psFindAll, psFindById, psInsert, psUpdate, psDelete;

	public UsuarioDaoMySQL() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, mysqlUser, mysqlPass);

			psFindAll = con.prepareStatement(FIND_ALL);
			psFindById = con.prepareStatement(FIND_BY_ID);
			psInsert = con.prepareStatement(INSERT);
			psUpdate = con.prepareStatement(UPDATE);
			psDelete = con.prepareStatement(DELETE);

		} catch (InstantiationException e) {
			throw new DAOExcepcion(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new DAOExcepcion(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			throw new DAOExcepcion("No se ha encontrado el diver de MySQL", e);
		} catch (SQLException e) {
			throw new DAOExcepcion("Error de conexion a Base de Datos", e);
		} catch (Exception e) {
			throw new DAOExcepcion("ERROR NO ESPERADO", e);
		} finally {
			// if (con!= null){
			// try {
			// con.close();
			// } catch (SQLException e) {
			// throw new DAOExcepcion("Error al cerrar" ,e);
			// }
			// }
		}
	}

	public Usuario[] findAll() {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			ResultSet rs = psFindAll.executeQuery();
			Usuario usuario;
			while (rs.next()) {
				// System.out.println(rs.getString("username"));//esto era para
				// mostrar por consola

				// crear objeto usuario
				usuario = new Usuario();
				// rellenar el objeto usuario
				usuario.setId(rs.getInt("id"));
				usuario.setId_roles(rs.getInt("id_roles"));
				usuario.setNombre_completo(rs.getString("nombre_completo"));
				usuario.setPassword(rs.getString("password"));
				usuario.setUsername(rs.getString("username"));
				// guardarlo en el arraylist
				usuarios.add(usuario);
			}

		} catch (SQLException e) {
			throw new DAOExcepcion("Error en findAll", e);
		} finally {
			// if(con!=null)
			// try {
			// con.close();
			// } catch (SQLException e) {
			// throw new DAOExcepcion("Error al cerrar findAll" ,e);
			// }
		}
		return usuarios.toArray(new Usuario[usuarios.size()]);
	}

	public Usuario findById(int id) {
		Usuario usuario = null;

		try {
			psFindById.setInt(1, id);// poner en la interrogacion 1 el valor id
			ResultSet rs = psFindById.executeQuery();
			if (rs.next()) {
				// crear objeto usuario
				usuario = new Usuario();
				// rellenar el objeto usuario
				usuario.setId(rs.getInt("id"));
				usuario.setId_roles(rs.getInt("id_roles"));
				usuario.setNombre_completo(rs.getString("nombre_completo"));
				usuario.setPassword(rs.getString("password"));
				usuario.setUsername(rs.getString("username"));

			}

		} catch (SQLException e) {
			throw new DAOExcepcion("Error en findById", e);
		}
		return usuario;
	}

	public void insert(Usuario usuario) {

		try {

			psInsert.setString(1, usuario.getUsername());
			psInsert.setString(2, usuario.getPassword());
			psInsert.setString(3, usuario.getNombre_completo());
			psInsert.setInt(4, usuario.getId_roles());

			int res = psInsert.executeUpdate();

			if (res != 1)
				throw new DAOExcepcion("la insercion ha devuelto un valor distinto de 1" + res);
		} catch (SQLException e) {
			throw new DAOExcepcion("Error en Insert", e);
		}

	}

	public void update(Usuario usuario) {
		// TODO Auto-generated method stub

	}

	public void delete(Usuario usuario) {
		// TODO Auto-generated method stub

	}

	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
