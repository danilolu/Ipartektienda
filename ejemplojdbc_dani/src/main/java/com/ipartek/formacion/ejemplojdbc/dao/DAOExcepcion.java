package com.ipartek.formacion.ejemplojdbc.dao;

public class DAOExcepcion extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3665129722413057209L;

	/**
	 * 
	 */

	// source/constructor from superclass
	// generate serial id sobre DAOException
	public DAOExcepcion() {
		super();

	}

	public DAOExcepcion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public DAOExcepcion(String message, Throwable cause) {
		super(message, cause);

	}

	public DAOExcepcion(String message) {
		super(message);

	}

	public DAOExcepcion(Throwable cause) {
		super(cause);

	}

}
