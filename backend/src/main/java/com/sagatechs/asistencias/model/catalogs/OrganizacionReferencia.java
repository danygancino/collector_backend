package com.sagatechs.asistencias.model.catalogs;

import javax.persistence.*;

@Entity
@Table(schema = "catalogs", name = "organizaciones_referencias")
public class OrganizacionReferencia extends CatalogoBase {

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
