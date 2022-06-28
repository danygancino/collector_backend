package com.sagatechs.asistencias.model;

import com.sagatechs.asistencias.model.catalogs.NecesidadEspecificaProteccion;
import com.sagatechs.generics.persistence.model.BaseEntity;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "NecesidadEspecificNecesidadProteccion")
@Table(name = "NecesidadesEspecificas_NecesidadesProteccion")
public class NecesidadEspecificaNecesidadProteccion extends BaseEntity<NecesidadesEspecificasNecesidadProteccionId> {

    @EmbeddedId
    private NecesidadesEspecificasNecesidadProteccionId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("necesidadEspecificaId")
    private NecesidadEspecifica necesidadEspecifica;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("necesidadEspecificaProteccionId")
    private NecesidadEspecificaProteccion necesidadEspecificaProteccion;

    @Column(name = "cantidad")
    private Integer cantidad;

    public NecesidadEspecificaNecesidadProteccion() {
    }

    public NecesidadEspecificaNecesidadProteccion(NecesidadEspecifica necesidadEspecifica, NecesidadEspecificaProteccion necesidadEspecificaProteccion) {
        this.necesidadEspecifica = necesidadEspecifica;
        this.necesidadEspecificaProteccion = necesidadEspecificaProteccion;
        this.id = new NecesidadesEspecificasNecesidadProteccionId(necesidadEspecifica.getId(), necesidadEspecificaProteccion.getId());
    }

    public NecesidadesEspecificasNecesidadProteccionId getId() {
        return id;
    }

    public void setId(NecesidadesEspecificasNecesidadProteccionId id) {
        this.id = id;
    }

    public NecesidadEspecifica getNecesidadEspecifica() {
        return necesidadEspecifica;
    }

    public void setNecesidadEspecifica(NecesidadEspecifica necesidadEspecifica) {
        this.necesidadEspecifica = necesidadEspecifica;
    }

    public NecesidadEspecificaProteccion getNecesidadEspecificaProteccion() {
        return necesidadEspecificaProteccion;
    }

    public void setNecesidadEspecificaProteccion(NecesidadEspecificaProteccion necesidadEspecificaProteccion) {
        this.necesidadEspecificaProteccion = necesidadEspecificaProteccion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        NecesidadEspecificaNecesidadProteccion that = (NecesidadEspecificaNecesidadProteccion) o;
        return Objects.equals(necesidadEspecifica, that.necesidadEspecifica) &&
                Objects.equals(necesidadEspecificaProteccion, that.necesidadEspecificaProteccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(necesidadEspecifica, necesidadEspecificaProteccion);
    }

}
