package com.ipartek.danilozano.Tipos;

import java.util.Date;
import java.util.HashMap;

public class Factura {
	// Constructores, getters y setters, hashCode y equals y toString
	private int  id_facturas,cant;
	private double precio,total;
	private String nombre_usuario,nombre_producto;
	private Date fecha;
	
	
	//constructores
	public Factura() {
		super();
		
	}
	//getters y setters
	
	public int getCant() {
		return cant;
	}
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}
	public int getId_facturas() {
		return id_facturas;
	}
	public void setId_facturas(int id_facturas) {
		this.id_facturas = id_facturas;
	}
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public String getNombre_producto() {
		return nombre_producto;
	}
	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cant;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + id_facturas;
		result = prime * result
				+ ((nombre_producto == null) ? 0 : nombre_producto.hashCode());
		result = prime * result
				+ ((nombre_usuario == null) ? 0 : nombre_usuario.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (cant != other.cant)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id_facturas != other.id_facturas)
			return false;
		if (nombre_producto == null) {
			if (other.nombre_producto != null)
				return false;
		} else if (!nombre_producto.equals(other.nombre_producto))
			return false;
		if (nombre_usuario == null) {
			if (other.nombre_usuario != null)
				return false;
		} else if (!nombre_usuario.equals(other.nombre_usuario))
			return false;
		if (Double.doubleToLongBits(precio) != Double
				.doubleToLongBits(other.precio))
			return false;
		if (Double.doubleToLongBits(total) != Double
				.doubleToLongBits(other.total))
			return false;
		return true;
	}
	//tostring

	@Override
	public String toString() {
		return "Factura [id_facturas=" + id_facturas + ", cant=" + cant
				+ ", precio=" + precio + ", total=" + total
				+ ", nombre_usuario=" + nombre_usuario + ", nombre_producto="
				+ nombre_producto + ", fecha=" + fecha + "]";
	}
	
	

}

	 

