package com.sagatechs.asistencias.model.catalogs;

import com.sagatechs.generics.persistence.model.State;

import javax.persistence.*;

@Entity
@Table(schema = "catalogs", name = "tipos_ayudas")
public class TipoAyuda extends CatalogoBase {

    public TipoAyuda() {
    }

    public TipoAyuda(String nombre, State estado) {
        super(nombre, estado);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


}
