package com.sagatechs.asistencias.model;

import com.sagatechs.asistencias.model.catalogs.NecesidadEspecificaProteccion;
import com.sagatechs.asistencias.model.catalogs.NecesidadEspecificaHabitabilidad;
import com.sagatechs.generics.persistence.model.BaseEntity;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "NecesidadEspecificNecesidadHabitabilidad")
@Table(name = "NecesidadesEspecificas_NecesidadesHabitabilidad")
public class NecesidadEspecificaNecesidadHabitabilidad extends BaseEntity<NecesidadesEspecificasNecesidadHabitabilidadId> {

    @EmbeddedId
    private NecesidadesEspecificasNecesidadHabitabilidadId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("necesidadEspecificaId")
    private NecesidadEspecifica necesidadEspecifica;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("necesidadEspecificaHabitabilidadId")
    private NecesidadEspecificaHabitabilidad necesidadEspecificaHabitabilidad;

    @Column(name = "cantidad")
    private boolean cantidad=false;

    public NecesidadEspecificaNecesidadHabitabilidad() {
    }

    public NecesidadEspecificaNecesidadHabitabilidad(NecesidadEspecifica necesidadEspecifica, NecesidadEspecificaHabitabilidad necesidadEspecificanecesidadEspecificaHabitabilidad) {
        this.necesidadEspecifica = necesidadEspecifica;
        this.necesidadEspecificaHabitabilidad = necesidadEspecificanecesidadEspecificaHabitabilidad;
        this.id = new NecesidadesEspecificasNecesidadHabitabilidadId(necesidadEspecifica.getId(), necesidadEspecificanecesidadEspecificaHabitabilidad.getId());
    }

    public NecesidadesEspecificasNecesidadHabitabilidadId getId() {
        return id;
    }

    public void setId(NecesidadesEspecificasNecesidadHabitabilidadId id) {
        this.id = id;
    }

    public NecesidadEspecifica getNecesidadEspecifica() {
        return necesidadEspecifica;
    }

    public void setNecesidadEspecifica(NecesidadEspecifica necesidadEspecifica) {
        this.necesidadEspecifica = necesidadEspecifica;
    }

    public NecesidadEspecificaHabitabilidad getNecesidadEspecificaHabitabilidad() {
        return necesidadEspecificaHabitabilidad;
    }

    public void setNecesidadEspecificaHabitabilidad(NecesidadEspecificaHabitabilidad necesidadEspecificaHabitabilidad) {
        this.necesidadEspecificaHabitabilidad = necesidadEspecificaHabitabilidad;
    }

    public boolean getCantidad() {
        return cantidad;
    }

    public void setCantidad(boolean cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        NecesidadEspecificaNecesidadHabitabilidad that = (NecesidadEspecificaNecesidadHabitabilidad) o;
        return Objects.equals(necesidadEspecifica, that.necesidadEspecifica) &&
                Objects.equals(necesidadEspecificaHabitabilidad, that.necesidadEspecificaHabitabilidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(necesidadEspecifica, necesidadEspecificaHabitabilidad);
    }

}
