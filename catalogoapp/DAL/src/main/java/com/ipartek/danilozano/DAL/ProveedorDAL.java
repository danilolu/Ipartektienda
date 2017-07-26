package com.ipartek.danilozano.DAL;

import com.ipartek.danilozano.Tipos.Proveedor;

public interface ProveedorDAL {
	public void alta(Proveedor proveedor);

	public void modificar(Proveedor proveedor);

	public void borrar(Proveedor proveedor);

	public void borrar(String nombre_p);

	public Proveedor buscarPorNombre(String nombre);

	public Proveedor[] buscarTodosLosProveedores();

}
