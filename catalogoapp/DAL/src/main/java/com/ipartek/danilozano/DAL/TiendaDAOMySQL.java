package com.ipartek.danilozano.DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.danilozano.Tipos.Producto;
import com.ipartek.danilozano.Tipos.Usuario;

public class TiendaDAOMySQL extends CatalogoAppDAOMySQL implements TiendaDAO {

	private final static String FIND_ALL_USUARIO = "SELECT * FROM usuarios";
	private final static String FIND_BY_NOMBRE_USUARIO = "SELECT * FROM usuarios Where nombre=?";
	private final static String INSERT_USUARIO = "INSERT INTO usuarios (nombre, password) VALUES (?,?)";
	private final static String UPDATE_USUARIO = "UPDATE usuarios SET password=? WHERE nombre=?";
	private final static String DELETE_USUARIO = "DELETE FROM usuarios WHERE nombre=?";

	private final static String FIND_ALL_PRODUCTO = "SELECT * FROM productos";
	private final static String FIND_BY_ID_PRODUCTO = "SELECT * FROM productos Where id=?";
	private final static String FIND_BY_NOMBRE_PRODUCTO = "SELECT * FROM productos Where nombre=?";
	private final static String INSERT_PRODUCTO = "INSERT INTO productos (nombre, descripcion, precio) VALUES (?,?,?)";
	private final static String UPDATE_PRODUCTO = "UPDATE productos SET nombre=?, descripcion=?, precio=? WHERE id=?";
	private final static String DELETE_PRODUCTO = "DELETE FROM productos WHERE id=?";

	private PreparedStatement psFindAllUsuario, psFindByNombreUsuario, psInsertUsuario, psUpdateUsuario, psDeleteUsuario;
	private PreparedStatement psFindAllProducto, psFindByNombreProducto, psFindByIdProducto, psInsertProducto, psUpdateProducto, psDeleteProducto;

	public TiendaDAOMySQL() {
		super();

	}

	public TiendaDAOMySQL(String url, String mysqlUser, String mysqlPass) {
		super(url, mysqlUser, mysqlPass);
	}

	// Usuario
	@Override
	public Usuario[] findAllUsuario() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		ResultSet rs = null;

		try {
			psFindAllUsuario = con.prepareStatement(FIND_ALL_USUARIO);

			rs = psFindAllUsuario.executeQuery();

			Usuario usuario;

			while (rs.next()) {

				usuario = new Usuario();

				usuario.setNombre(rs.getString("nombre"));
				usuario.setPass(rs.getString("password"));

				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			throw new DAOException("Error en findAll", e);
		} finally {
			cerrarUsuario(psFindAllUsuario, rs);
		}
		return usuarios.toArray(new Usuario[usuarios.size()]);
	}

	private void cerrarUsuario(PreparedStatement ps) {
		cerrarUsuario(ps, null);
	}

	private void cerrarUsuario(PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			throw new DAOException("Error en el cierre de ps o rs", e);
		}
	}

	@Override
	public Usuario findByNombreUsuario(String nombre) {
		Usuario usuario = null;
		ResultSet rs = null;

		try {
			psFindByNombreUsuario = con.prepareStatement(FIND_BY_NOMBRE_USUARIO);

			psFindByNombreUsuario.setString(1, nombre);
			rs = psFindByNombreUsuario.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();

				usuario.setNombre(rs.getString("nombre"));
				usuario.setPass(rs.getString("password"));
			}

		} catch (Exception e) {
			throw new DAOException("Error en findById", e);
		} finally {
			cerrarUsuario(psFindByNombreUsuario, rs);
		}

		return usuario;
	}

	@Override
	public void insert(Usuario usuario) {

		try {
			psInsertUsuario = con.prepareStatement(INSERT_USUARIO);

			psInsertUsuario.setString(1, usuario.getNombre());
			psInsertUsuario.setString(2, usuario.getPass());

			psInsertUsuario.executeUpdate();
		}

		catch (Exception e) {
			throw new DAOException("Error en insert", e);
		} finally {
			cerrarUsuario(psInsertUsuario);
		}

	}

	@Override
	public void update(Usuario usuario) {
		try {
			psUpdateUsuario = con.prepareStatement(UPDATE_USUARIO);

			psUpdateUsuario.setString(1, usuario.getPass());

			psUpdateUsuario.setString(2, usuario.getNombre());

			int res = psUpdateUsuario.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualización ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error en update", e);
		} finally {
			cerrarUsuario(psUpdateUsuario);
		}
	}

	@Override
	public void delete(Usuario usuario) {
		deleteUsuario(usuario.getNombre());

	}

	@Override
	public void deleteUsuario(String nombre) {
		try {
			psDeleteUsuario = con.prepareStatement(DELETE_USUARIO);

			psDeleteUsuario.setString(1, nombre);

			int res = psDeleteUsuario.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualización ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error en update", e);
		} finally {
			cerrarUsuario(psDeleteUsuario);
		}

	}

	@Override
	public boolean validar(Usuario usuario) {
		this.abrir();
		Usuario[] usuariosArr = this.findAllUsuario();
		this.cerrar();

		if (usuario.getNombre() != null) {

			for (Usuario u : usuariosArr) {

				if (usuario.getNombre().equals(u.getNombre()) && usuario.getPass().equals(u.getPass())) {

					return true;
				}
			}
		}
		return false;
	}

	// Producto
	@Override
	public Producto[] findAllProducto() {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		ResultSet rs = null;

		try {
			psFindAllProducto = con.prepareStatement(FIND_ALL_PRODUCTO);

			rs = psFindAllProducto.executeQuery();

			Producto producto;

			while (rs.next()) {
				// System.out.println(rs.getString("username"));
				producto = new Producto();

				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));

				productos.add(producto);
			}
		} catch (SQLException e) {
			throw new DAOException("Error en findAll", e);
		} finally {
			cerrarProducto(psFindAllProducto, rs);
		}
		return productos.toArray(new Producto[productos.size()]);
	}

	private void cerrarProducto(PreparedStatement ps) {
		cerrarProducto(ps, null);
	}

	private void cerrarProducto(PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			throw new DAOException("Error en el cierre de ps o rs", e);
		}
	}

	@Override
	public Producto findByIdProducto(int id) {
		Producto producto = null;
		ResultSet rs = null;

		try {
			psFindByIdProducto = con.prepareStatement(FIND_BY_ID_PRODUCTO);

			psFindByIdProducto.setInt(1, id);
			rs = psFindByIdProducto.executeQuery();

			if (rs.next()) {
				producto = new Producto();

				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
			}

		} catch (Exception e) {
			throw new DAOException("Error en findById", e);
		} finally {
			cerrarProducto(psFindByIdProducto, rs);
		}

		return producto;
	}

	@Override
	public Producto findByNombreProducto(String nombre) {
		Producto producto = null;
		ResultSet rs = null;

		try {
			psFindByNombreProducto = con.prepareStatement(FIND_BY_NOMBRE_PRODUCTO);

			psFindByNombreProducto.setString(1, nombre);
			rs = psFindByNombreProducto.executeQuery();

			if (rs.next()) {
				producto = new Producto();

				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
			}

		} catch (Exception e) {
			throw new DAOException("Error en findBynombre", e);
		} finally {
			cerrarProducto(psFindByNombreProducto, rs);
		}

		return producto;
	}

	@Override
	public int insert(Producto producto) {
		ResultSet generatedKeys = null;

		try {
			psInsertProducto = con.prepareStatement(INSERT_PRODUCTO, Statement.RETURN_GENERATED_KEYS);

			psInsertProducto.setString(1, producto.getNombre());
			psInsertProducto.setString(2, producto.getDescripcion());
			psInsertProducto.setDouble(3, producto.getPrecio());

			int res = psInsertProducto.executeUpdate();

			if (res != 1)
				throw new DAOException("La inserción ha devuelto un valor " + res);

			generatedKeys = psInsertProducto.getGeneratedKeys();

			if (generatedKeys.next())
				return generatedKeys.getInt(1);
			else
				throw new DAOException("No se ha recibido la clave generada");

		} catch (Exception e) {
			throw new DAOException("Error en insert", e);
		} finally {
			cerrarProducto(psInsertProducto, generatedKeys);
		}
	}

	@Override
	public void update(Producto producto) {
		try {
			psUpdateProducto = con.prepareStatement(UPDATE_PRODUCTO);

			psUpdateProducto.setString(1, producto.getNombre());
			psUpdateProducto.setString(2, producto.getDescripcion());
			psUpdateProducto.setDouble(3, producto.getPrecio());

			psUpdateProducto.setInt(4, producto.getId());

			int res = psUpdateProducto.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualización ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error en update", e);
		} finally {
			cerrarProducto(psUpdateProducto);
		}
	}

	@Override
	public void delete(Producto producto) {
		deleteProducto(producto.getId());
	}

	@Override
	public void deleteProducto(int id) {
		try {
			psDeleteProducto = con.prepareStatement(DELETE_PRODUCTO);

			psDeleteProducto.setInt(1, id);

			int res = psDeleteProducto.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualización ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error en update", e);
		} finally {
			cerrarProducto(psDeleteProducto);
		}

	}

}
