package com.sagatechs.asistencias.model.catalogs;


import com.sagatechs.generics.persistence.model.State;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "paises", schema = "catalogs")
public class Pais extends CatalogoBase {

	public Pais() {
		super();
	}

	public Pais(String nombre, State estado) {
		super(nombre, estado);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pais")
	private Set<Provincia> provincias = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Set<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(Set<Provincia> provincias) {
		this.provincias = provincias;
	}

	public void addProvincia(Provincia provincia){
		provincia.setPais(this);
		if(!this.provincias.add(provincia)){
			this.provincias.remove(provincia);
			this.provincias.add(provincia);
		}
	}
	

}
