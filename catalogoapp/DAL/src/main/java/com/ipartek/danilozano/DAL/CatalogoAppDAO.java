package com.ipartek.danilozano.DAL;

public interface CatalogoAppDAO {
	public void abrir();

	public void cerrar();

	public void iniciarTransaccion();

	public void confirmarTransaccion();

	public void deshacerTransaccion();

}