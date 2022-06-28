package com.sagatechs.asistencias.model.catalogs;

import com.sagatechs.generics.persistence.model.State;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "catalogs", name = "componentes")
public class Componente extends CatalogoBase {

    public Componente() {
    }

    public Componente(String nombre, State estado) {
        super(nombre, estado);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "componente")
    private Set<TipoAsistencia> tiposAsistencias = new HashSet<>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Set<TipoAsistencia> getTiposAsistencias() {
        return tiposAsistencias;
    }

    public void setTiposAsistencias(Set<TipoAsistencia> tiposAsistencias) {
        this.tiposAsistencias = tiposAsistencias;
    }

    public void addTipoAsistencia(TipoAsistencia tipoAsistencia){
        tipoAsistencia.setComponente(this);
        if(!this.tiposAsistencias.add(tipoAsistencia)){
            this.tiposAsistencias.remove(tipoAsistencia);
            this.tiposAsistencias.add(tipoAsistencia);
        }
    }
}
