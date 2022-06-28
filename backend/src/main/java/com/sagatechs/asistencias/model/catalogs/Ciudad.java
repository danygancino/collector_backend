package com.sagatechs.asistencias.model.catalogs;

import com.sagatechs.generics.persistence.model.State;

import javax.persistence.*;

@Entity
@Table(name = "ciudades", schema = "catalogs")
public class Ciudad extends CatalogoBase {

	public Ciudad() {
		super();

	}

	public Ciudad(String nombre, State estado) {
		super(nombre, estado);

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "provincia_id", foreignKey = @ForeignKey(name = "fk_ciudad_provincia"))
	private Provincia provincia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return
				"nombre='" + nombre + '\'' +
				", id=" + id ;
	}
}
