package com.ipartek.danilozano.DAL;

import com.ipartek.danilozano.Tipos.Producto;
import com.ipartek.danilozano.Tipos.Usuario;

public interface TiendaDAO extends CatalogoAppDAO {

	// DAO usuarios
	public Usuario[] findAllUsuario();// select

	public Usuario findByNombreUsuario(String nombre);// select

	public void insert(Usuario usuario);

	public void update(Usuario usuario);

	public void delete(Usuario usuario);

	public void deleteUsuario(String nombre);
	
	public boolean validar(Usuario usuario);

	public boolean validarNombre(Usuario usuario);

	// DAO productos
	public Producto[] findAllProducto();// select

	public Producto findByIdProducto(int id);// select

	public int insert(Producto producto);

	public void update(Producto producto);

	public void delete(Producto producto);

	public void deleteProducto(int id);

}
