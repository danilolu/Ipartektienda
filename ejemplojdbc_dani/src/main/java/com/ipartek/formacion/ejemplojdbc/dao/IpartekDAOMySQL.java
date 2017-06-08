package com.ipartek.formacion.ejemplojdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IpartekDAOMySQL implements IpartekDAO {

	protected Connection con;

	private String url = "jdbc:mysql://localhost/ipartek";
	private String mysqlUser = "root";
	private String mysqlPass = "";

	public IpartekDAOMySQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			throw new DAOException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new DAOException(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			throw new DAOException("No se ha encontrado el driver de MySQL", e);
		} catch (Exception e) {
			throw new DAOException("ERROR NO ESPERADO", e);
		}
	}

	public void abrir() {
		try {
			con = DriverManager.getConnection(url, mysqlUser, mysqlPass);
		} catch (SQLException e) {
			throw new DAOException("Error de conexión a la base de datos", e);
		} catch (Exception e) {
			throw new DAOException("ERROR NO ESPERADO", e);
		}
	}

	public void cerrar() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
			con = null;
		} catch (SQLException e) {
			throw new DAOException("Error de cierre de conexión a la base de datos", e);
		} catch (Exception e) {
			throw new DAOException("ERROR NO ESPERADO", e);
		}
	}

}
