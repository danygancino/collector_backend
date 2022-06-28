package com.sagatechs.asistencias.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class NecesidadesEspecificasNecesidadHabitabilidadId implements Serializable {
    @Column(name ="necesidadEspecifica_id")
    @Type(type = "pg-uuid")
    private UUID necesidadEspecificaId;

    @Column(name ="necesidadEspecificaHabitabilidad_id")
    private Long necesidadEspecificaHabitabilidadId;



    public NecesidadesEspecificasNecesidadHabitabilidadId(UUID necesidadEspecificaId, Long necesidadEspecificaProteccionId) {
        this.necesidadEspecificaId = necesidadEspecificaId;
        this.necesidadEspecificaHabitabilidadId = necesidadEspecificaProteccionId;
    }

    public NecesidadesEspecificasNecesidadHabitabilidadId() {

    }

    public UUID getNecesidadEspecificaId() {
        return necesidadEspecificaId;
    }

    public void setNecesidadEspecificaId(UUID necesidadEspecificaId) {
        this.necesidadEspecificaId = necesidadEspecificaId;
    }

    public Long getNecesidadEspecificaHabitabilidadId() {
        return necesidadEspecificaHabitabilidadId;
    }

    public void setNecesidadEspecificaHabitabilidadId(Long necesidadEspecificaProteccionId) {
        this.necesidadEspecificaHabitabilidadId = necesidadEspecificaProteccionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        NecesidadesEspecificasNecesidadHabitabilidadId that = (NecesidadesEspecificasNecesidadHabitabilidadId) o;
        return Objects.equals(necesidadEspecificaId, that.necesidadEspecificaId) &&
                Objects.equals(necesidadEspecificaHabitabilidadId, that.necesidadEspecificaHabitabilidadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(necesidadEspecificaId, necesidadEspecificaHabitabilidadId);
    }
}
