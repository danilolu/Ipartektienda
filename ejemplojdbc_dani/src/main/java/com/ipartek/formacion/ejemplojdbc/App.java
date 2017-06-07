package com.ipartek.formacion.ejemplojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ipartek.formacion.ejemplojdbc.dao.UsuarioDAO;
import com.ipartek.formacion.ejemplojdbc.dao.UsuarioDaoMySQL;
import com.ipartek.formacion.ejemplojdbc.tipos.Usuario;

public class App {
	public static void main(String[] args) {
		UsuarioDAO dao = new UsuarioDaoMySQL();// crear objeto dao
		for (Usuario u : dao.findAll())
			// recorrer el metodo dao findall
			System.out.println(u);
	}

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
