package com.ipartek.danilozano.DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ipartek.danilozano.Tipos.Carrito;
import com.ipartek.danilozano.Tipos.Factura;
import com.ipartek.danilozano.Tipos.Producto;
import com.ipartek.danilozano.Tipos.Usuario;

public class TiendaDAOMySQL extends CatalogoAppDAOMySQL implements TiendaDAO {
	private static Logger log = Logger.getLogger(Carrito.class);

	private final static String FIND_ALL_USUARIO = "SELECT * FROM usuarios";
	private final static String FIND_BY_NOMBRE_USUARIO = "SELECT * FROM usuarios Where nombre=?";
	private final static String INSERT_USUARIO = "INSERT INTO usuarios (nombre, password) VALUES (?,?)";
	private final static String UPDATE_USUARIO = "UPDATE usuarios SET password=? WHERE nombre=?";
	private final static String DELETE_USUARIO = "DELETE FROM usuarios WHERE nombre=?";

	private final static String FIND_ALL_PRODUCTO = "SELECT * FROM productos";
	private final static String FIND_BY_ID_PRODUCTO = "SELECT * FROM productos Where id=?";
	private final static String FIND_BY_NOMBRE_PRODUCTO = "SELECT * FROM productos Where nombre=?";
	private final static String INSERT_PRODUCTO = "INSERT INTO productos (nombre, descripcion, precio, stock) VALUES (?,?,?,?)";
	private final static String UPDATE_PRODUCTO = "UPDATE productos SET nombre=?, descripcion=?, precio=?, stock=? WHERE id=?";
	private final static String UPDATE_PRODUCTO_ANADIDO = "UPDATE productos SET  stock=?  WHERE id=?";
	private final static String UPDATE_CANT = "UPDATE productos SET  cant=?  WHERE id=?";
	private final static String DELETE_PRODUCTO = "DELETE FROM productos WHERE id=?";

	private final static String INSERT_FACTURA = "INSERT INTO facturas (nombre_usuario, fecha) VALUES (?,?)";
	private final static String INSERT_FACTURA_PRODUCTOS = "INSERT INTO facturas_productos (id_facturas, id_productos, cantidad) VALUES (?,?,?)";
	private final static String FIND_ALL_FACTURAS = "select id_facturas,nombre_usuario, nombre, precio, cantidad, precio*cantidad as total,fecha from facturas, facturas_productos, productos where facturas.id=facturas_Productos.id_facturas and productos.id=facturas_productos.id_productos ORDER BY `facturas_productos`.`id_facturas` ASC ";
	private final static String FIND_ALL_FACTURAS_TOTAL = "select id_facturas, nombre_usuario, SUM(precio*cantidad) as total,fecha from facturas, facturas_productos, productos WHERE facturas.id=facturas_productos.id_facturas and productos.id=facturas_productos.id_productos GROUP BY facturas_productos.id_facturas";
	private final static String FIND_BY_ID_FACTURAS = "SELECT id_facturas, facturas.nombre_usuario,productos.nombre,productos.precio,cantidad,precio*cantidad as total FROM facturas_productos,productos, facturas WHERE id_facturas = ? AND facturas.id=facturas_Productos.id_facturas and productos.id=facturas_productos.id_productos";

	private PreparedStatement psFindAllUsuario, psFindByNombreUsuario, psInsertUsuario, psUpdateUsuario, psDeleteUsuario;
	private PreparedStatement psUpdateCant, psFindAllProducto, psFindByNombreProducto, psFindByIdProducto, psInsertProducto, psUpdateProducto, psUpdateProductoAnadido, psUpdateProductoQuitado,
			psDeleteProducto;
	private PreparedStatement psInsertFacturas, psInsertFacturasProductos, psFindAllFacturas, psFindAllFacturasTotal, psFindByFacturas;

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
				producto.setStock(rs.getInt("stock"));
				producto.setCant(rs.getInt("cant"));
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
				producto.setStock(rs.getInt("stock"));
				producto.setCant(rs.getInt("cant"));
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
				producto.setStock(rs.getInt("stock"));
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
			psInsertProducto.setInt(4, producto.getStock());
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
			psUpdateProducto.setInt(4, producto.getStock());

			psUpdateProducto.setInt(5, producto.getId());

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
	public void updateStockAnadido(Producto producto) {
		try {
			psUpdateProductoAnadido = con.prepareStatement(UPDATE_PRODUCTO_ANADIDO);

			psUpdateProductoAnadido.setInt(1, producto.getStock() - producto.getCant());

			psUpdateProductoAnadido.setInt(2, producto.getId());

			int res = psUpdateProductoAnadido.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualización ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error en StockAnadido ", e);
		} finally {
			cerrarProducto(psUpdateProducto);
		}
	}

	@Override
	public void updateStockQuitado(Producto producto) {
		try {
			psUpdateProductoQuitado = con.prepareStatement(UPDATE_PRODUCTO_ANADIDO);

			psUpdateProductoQuitado.setInt(1, producto.getStock() + producto.getCant());

			psUpdateProductoQuitado.setInt(2, producto.getId());

			int res = psUpdateProductoQuitado.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualización ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error en update StockQuitado", e);
		} finally {
			cerrarProducto(psUpdateProducto);
		}
	}

	@Override
	public void updateCant(Producto producto) {
		try {
			psUpdateCant = con.prepareStatement(UPDATE_CANT);

			psUpdateCant.setInt(1, producto.getCant());

			psUpdateCant.setInt(2, producto.getId());

			int res = psUpdateCant.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualización ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error en update StockQuitado", e);
		} finally {
			cerrarProducto(psUpdateProducto);
		}

	}

	@Override
	public void resetCant(Producto producto) {
		try {
			psUpdateCant = con.prepareStatement(UPDATE_CANT);

			psUpdateCant.setInt(1, 0);

			psUpdateCant.setInt(2, producto.getId());

			int res = psUpdateCant.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualización ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error en update StockQuitado", e);
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

	// Facturas
	private void cerrarFactura(PreparedStatement ps) {
		cerrarFactura(ps, null);
	}

	private void cerrarFactura(PreparedStatement ps, ResultSet rs) {
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
	public int insertfactura(Carrito carrito) {
		ResultSet generatedKeys = null;

		try {
			psInsertFacturas = con.prepareStatement(INSERT_FACTURA, Statement.RETURN_GENERATED_KEYS);

			psInsertFacturas.setString(1, carrito.getNombre_usuarios());
			psInsertFacturas.setDate(2, new java.sql.Date(carrito.getFecha().getTime()));

			int res = psInsertFacturas.executeUpdate();

			if (res != 1)
				throw new DAOException("La inserción ha devuelto un valor " + res);

			generatedKeys = psInsertFacturas.getGeneratedKeys();

			if (generatedKeys.next())
				return generatedKeys.getInt(1);
			else
				throw new DAOException("No se ha recibido la clave generada");

		} catch (Exception e) {
			throw new DAOException("Error en insert", e);
		} finally {
			cerrarFactura(psInsertFacturas, generatedKeys);
		}
	}

	@Override
	public int insertFacturasProductos(Carrito carrito) {

		int id = insertfactura(carrito);

		try {
			psInsertFacturasProductos = con.prepareStatement(INSERT_FACTURA_PRODUCTOS);

			for (Producto p : carrito.buscarTodosLosProductos()) {

				psInsertFacturasProductos.setInt(1, id);
				psInsertFacturasProductos.setInt(2, p.getId());
				resetCant(p);
				psInsertFacturasProductos.setInt(3, p.getCant());
				int res = psInsertFacturasProductos.executeUpdate();
				log.info("carritoid= " + id);
				log.info("productoid= " + p.getId());
				log.info("productoid= " + p.getCant());

				if (res != 1)
					throw new DAOException("La inserción ha devuelto un valor " + res);

			}
		} catch (Exception e) {
			throw new DAOException("Error en insert", e);
		} finally {
			cerrarFactura(psInsertFacturas);
		}
		return id;
	}

	@Override
	public Factura[] findallfacturas() {
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		ResultSet rs = null;

		try {
			psFindAllFacturas = con.prepareStatement(FIND_ALL_FACTURAS);

			rs = psFindAllFacturas.executeQuery();

			Factura factura;

			while (rs.next()) {
				// System.out.println(rs.getString("username"));
				factura = new Factura();

				factura.setId_facturas(rs.getInt("id_facturas"));
				factura.setNombre_usuario(rs.getString("nombre_usuario"));
				factura.setNombre_producto(rs.getString("nombre"));
				factura.setPrecio(rs.getDouble("precio"));
				factura.setCant(rs.getInt("cantidad"));
				factura.setTotal(rs.getDouble("total"));
				factura.setFecha(rs.getDate("fecha"));

				facturas.add(factura);
			}
		} catch (SQLException e) {
			throw new DAOException("Error en findAll", e);
		} finally {
			cerrarProducto(psFindAllFacturas, rs);
		}
		return facturas.toArray(new Factura[facturas.size()]);
	}

	@Override
	public Factura[] findallfacturastotal() {
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		ResultSet rs = null;

		try {
			psFindAllFacturasTotal = con.prepareStatement(FIND_ALL_FACTURAS_TOTAL);

			rs = psFindAllFacturasTotal.executeQuery();

			Factura factura;

			while (rs.next()) {
				// System.out.println(rs.getString("username"));
				factura = new Factura();
				factura.setId_facturas(rs.getInt("id_facturas"));
				factura.setNombre_usuario(rs.getString("nombre_usuario"));
				factura.setTotal(rs.getDouble("total"));
				factura.setFecha(rs.getDate("fecha"));

				facturas.add(factura);
			}
		} catch (SQLException e) {
			throw new DAOException("Error en findAll", e);
		} finally {
			cerrarProducto(psFindAllFacturasTotal, rs);
		}
		return facturas.toArray(new Factura[facturas.size()]);
	}

	@Override
	public Factura findByIdFactura(int id_facturas) {
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		Factura factura = null;
		ResultSet rs = null;

		try {
			psFindByFacturas = con.prepareStatement(FIND_BY_ID_FACTURAS);

			psFindByFacturas.setInt(1, id_facturas);
			rs = psFindByFacturas.executeQuery();

			if (rs.next()) {
				factura = new Factura();
				factura.setId_facturas(rs.getInt("id_facturas"));
				factura.setNombre_usuario(rs.getString("nombre_usuario"));
				factura.setNombre_producto(rs.getString("nombre"));
				factura.setPrecio(rs.getDouble("precio"));
				factura.setCant(rs.getInt("cantidad"));
				factura.setTotal(rs.getDouble("total"));

				facturas.add(factura);
			}

		} catch (Exception e) {
			throw new DAOException("Error en findById", e);
		} finally {
			cerrarFactura(psFindByFacturas, rs);
		}

		return factura;
	}
}
