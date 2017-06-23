package com.ipartek.danilozano.Tipos;

import java.util.Date;
import java.util.TreeMap;

import org.apache.log4j.Logger;

public class Carrito {

	private int idcarrito;
	private String nombre_usuarios;
	private Date fecha = new Date();

	private static Logger log = Logger.getLogger(Carrito.class);

	public TreeMap<Integer, Producto> carritoLista;

	public Carrito() {
		super();
		this.carritoLista = new TreeMap<>();
		log.info("Creado carrito vacio");

	}

	public Carrito(int idcarrito, String nombre_usuarios, Date fecha) {
		super();
		this.idcarrito = idcarrito;
		this.nombre_usuarios = nombre_usuarios;
		this.fecha = fecha;
		this.carritoLista = new TreeMap<>();

		log.info("Creado carrito vacio bueno");
	}

	public Producto buscarPorId(Integer id) {

		return carritoLista.get(id);

	}

	public void anadirAlCarrito(Producto producto) {

		carritoLista.put(producto.getId(), producto);

	}

	public void quitarDelCarrito(Integer id) {

		carritoLista.remove(id);

	}

	public void quitarDelCarrito(Producto p) {
		carritoLista.remove(p.getId());

	}

	public Producto[] buscarTodosLosProductos() {
		return carritoLista.values().toArray(new Producto[carritoLista.size()]);
	}

	public int totalProductos() {

		Producto[] carritoArr = this.buscarTodosLosProductos();

		int tolalProductos = 0;

		for (Producto p : carritoArr) {

			tolalProductos += p.getCant();

		}

		return tolalProductos;
	}

	public Double precioTotal() {

		Producto[] carritoArr = this.buscarTodosLosProductos();

		Double precioTotal = 0.0;

		for (Producto p : carritoArr) {
			Double precio = p.getPrecio() * p.getCant();
			precioTotal += precio;

		}

		return precioTotal;
	}

	public TreeMap<Integer, Producto> getListaProductos() {
		return carritoLista;
	}

	public void setListaProductos(TreeMap<Integer, Producto> listaProductos) {
		this.carritoLista = listaProductos;
	}

	@Override
	public String toString() {
		return "carritoLista=" + carritoLista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carritoLista == null) ? 0 : carritoLista.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + idcarrito;
		result = prime * result + ((nombre_usuarios == null) ? 0 : nombre_usuarios.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrito other = (Carrito) obj;
		if (carritoLista == null) {
			if (other.carritoLista != null)
				return false;
		} else if (!carritoLista.equals(other.carritoLista))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (idcarrito != other.idcarrito)
			return false;
		if (nombre_usuarios == null) {
			if (other.nombre_usuarios != null)
				return false;
		} else if (!nombre_usuarios.equals(other.nombre_usuarios))
			return false;
		return true;
	}

	public String getNombre_usuarios() {
		return nombre_usuarios;
	}

	public void setNombre_usuarios(String nombre_usuarios) {
		this.nombre_usuarios = nombre_usuarios;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdCarrito() {
		return idcarrito;
	}

	public void setIdCarrito(int id) {
		this.idcarrito = id;
	}
}
