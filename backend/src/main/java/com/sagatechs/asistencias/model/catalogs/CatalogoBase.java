package com.sagatechs.asistencias.model.catalogs;

import com.sagatechs.generics.persistence.model.BaseEntity;
import com.sagatechs.generics.persistence.model.State;

import javax.persistence.*;

@MappedSuperclass
public abstract class CatalogoBase extends BaseEntity<Long> {


	

	public CatalogoBase() {
		super();
	}

	public CatalogoBase(String nombre, State estado) {
		super();
		this.nombre = nombre;
		this.estado = estado;
	}

	
	@Column(name = "nombre", nullable = false, length = 150, unique = true)
	protected String nombre;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false, length = 12)
	protected State estado;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public State getEstado() {
		return estado;
	}

	public void setEstado(State estado) {
		this.estado = estado;
	}

	@Id
	public abstract Long getId();

	public abstract void setId(Long id);

	@Override
	public String toString() {
		return "Catalogo [id=" + getId() + ", nombre=" + nombre + ", estado=" + estado + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatalogoBase other = (CatalogoBase) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	
}
