package com.ipartek.danilozano.Tipos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "proveedores")
public class Proveedor implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Nombre", unique = true, nullable = false)
	private String nombre_p;
	@Column(name = "Comentarios")
	private String comentarios;
	@Column(name = "Telefono")
	private int telefono;
	@Transient
	private String errores;

	public Proveedor() {

	}

	public Proveedor(String nombre, String comentarios, int telefono) {

		this.nombre_p = nombre;
		this.comentarios = comentarios;

		this.telefono = telefono;
	}

	public String getNombre_p() {
		return nombre_p;
	}

	public void setNombre_p(String nombre) {
		this.nombre_p = nombre;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getErrores() {
		return errores;
	}

	public void setErrores(String errores) {
		this.errores = errores;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Proveedor [nombre=" + nombre_p + ", comentarios=" + comentarios + ", errores=" + errores + ", telefono=" + telefono + "]";
	}

}
