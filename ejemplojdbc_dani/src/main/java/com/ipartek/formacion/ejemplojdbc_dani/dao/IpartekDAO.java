package com.ipartek.formacion.ejemplojdbc_dani.dao;

public interface IpartekDAO {
	public void abrir();

	public void cerrar();

	public void iniciarTransaccion();

	public void confirmarTransaccion();

	public void deshacerTransaccion();

}
