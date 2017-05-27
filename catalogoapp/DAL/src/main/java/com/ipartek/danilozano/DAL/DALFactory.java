package com.ipartek.danilozano.DAL;

public class DALFactory {
	public static DAL getProductosDAL() {

		return new DALColeccion();
	}

	public static DAL getUsuariosDAL() {

		return new DALColeccion();
	}
}
