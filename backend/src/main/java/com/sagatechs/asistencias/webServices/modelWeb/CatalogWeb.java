package com.sagatechs.asistencias.webServices.modelWeb;

import com.sagatechs.generics.persistence.model.State;

public class CatalogWeb {

    private Long id;
    private String nombre;
    private State state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
