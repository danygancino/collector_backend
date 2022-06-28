package com.sagatechs.asistencias.model.catalogs;

import com.sagatechs.generics.persistence.model.State;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "provincias", schema = "catalogs")
public class Provincia extends CatalogoBase {

	public Provincia() {
		super();

	}

	public Provincia(String nombre, State estado) {
		super(nombre, estado);

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pais_id", foreignKey = @ForeignKey(name = "fk_ciudad_pais"))
	private Pais pais;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "provincia")
	private Set<Ciudad> ciudades = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
		
	}

	public Set<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(Set<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public void addCiudad(Ciudad ciudad){
		ciudad.setProvincia(this);
		if(!this.ciudades.add(ciudad)){
			this.ciudades.remove(ciudad);
			this.ciudades.add(ciudad);
		}
	}
}
