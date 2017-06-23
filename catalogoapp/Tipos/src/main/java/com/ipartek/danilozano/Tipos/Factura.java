package com.ipartek.danilozano.Tipos;

import java.util.Date;
import java.util.HashMap;

public class Factura {
	// Constructores, getters y setters, hashCode y equals y toString
	private int id;
	private String nombre_usuarios;
	private Date fecha;

	

	private HashMap<Integer, Producto> listaProductos = new HashMap<>();

	public Double getPrecioTotal() {
		Double precioTotal = 0.0;
		if (listaProductos != null) {
			for (Producto p : listaProductos.values()) {
				precioTotal += p.getPrecio();
			}
		}
		return precioTotal;
	}

	public Factura(String nombre_usuarios, Date fecha) {
		super();
		
		this.nombre_usuarios = nombre_usuarios;
		this.fecha = fecha;
	}

	public Factura(int id,  String nombre_usuarios, Date fecha) {
		super();
		this.id = id;
		
		this.nombre_usuarios = nombre_usuarios;
		this.fecha = fecha;
	}

	
	

	public Factura() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public HashMap<Integer, Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(HashMap<Integer, Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((listaProductos == null) ? 0 : listaProductos.hashCode());
		result = prime * result
				+ ((nombre_usuarios == null) ? 0 : nombre_usuarios.hashCode());
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
		Factura other = (Factura) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id != other.id)
			return false;
		if (listaProductos == null) {
			if (other.listaProductos != null)
				return false;
		} else if (!listaProductos.equals(other.listaProductos))
			return false;
		if (nombre_usuarios == null) {
			if (other.nombre_usuarios != null)
				return false;
		} else if (!nombre_usuarios.equals(other.nombre_usuarios))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", nombre_usuarios=" + nombre_usuarios
				+ ", fecha=" + fecha + ", listaProductos=" + listaProductos
				+ "]";
	}

	
}

