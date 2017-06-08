package com.ipartek.formacion.ejemplojdbc_dani.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IpartekDAOMySQL implements IpartekDAO {

	protected Connection con;

	private String url = "jdbc:mysql://localhost/ipartek";
	private String mysqlUser = "root";
	private String mysqlPass = "";

	public IpartekDAOMySQL(String url, String mysqlUser, String mysqlPass) {
		this();
		this.url = url;
		this.mysqlUser = mysqlUser;
		this.mysqlPass = mysqlPass;
	}

	public IpartekDAOMySQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			throw new DAOExcepcion(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new DAOExcepcion(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			throw new DAOExcepcion("No se ha encontrado el driver de MySQL", e);
		} catch (Exception e) {
			throw new DAOExcepcion("ERROR NO ESPERADO", e);
		}
	}

	public void abrir() {
		try {
			con = DriverManager.getConnection(url, mysqlUser, mysqlPass);
		} catch (SQLException e) {
			throw new DAOExcepcion("Error de conexión a la base de datos", e);
		} catch (Exception e) {
			throw new DAOExcepcion("ERROR NO ESPERADO", e);
		}
	}

	public void cerrar() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
			con = null;
		} catch (SQLException e) {
			throw new DAOExcepcion("Error de cierre de conexión a la base de datos", e);
		} catch (Exception e) {
			throw new DAOExcepcion("ERROR NO ESPERADO", e);
		}
	}

}
