package com.sagatechs.asistencias.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class NecesidadesEspecificasNecesidadProteccionId implements Serializable {
    @Column(name ="necesidadEspecifica_id")
    @Type(type = "pg-uuid")
    private UUID necesidadEspecificaId;

    @Column(name ="necesidadEspecificaProteccion_id")
    private Long necesidadEspecificaProteccionId;



    public NecesidadesEspecificasNecesidadProteccionId(UUID necesidadEspecificaId, Long necesidadEspecificaProteccionId) {
        this.necesidadEspecificaId = necesidadEspecificaId;
        this.necesidadEspecificaProteccionId = necesidadEspecificaProteccionId;
    }

    public NecesidadesEspecificasNecesidadProteccionId() {

    }

    public UUID getNecesidadEspecificaId() {
        return necesidadEspecificaId;
    }

    public void setNecesidadEspecificaId(UUID necesidadEspecificaId) {
        this.necesidadEspecificaId = necesidadEspecificaId;
    }

    public Long getNecesidadEspecificaProteccionId() {
        return necesidadEspecificaProteccionId;
    }

    public void setNecesidadEspecificaProteccionId(Long necesidadEspecificaProteccionId) {
        this.necesidadEspecificaProteccionId = necesidadEspecificaProteccionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        NecesidadesEspecificasNecesidadProteccionId that = (NecesidadesEspecificasNecesidadProteccionId) o;
        return Objects.equals(necesidadEspecificaId, that.necesidadEspecificaId) &&
                Objects.equals(necesidadEspecificaProteccionId, that.necesidadEspecificaProteccionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(necesidadEspecificaId, necesidadEspecificaProteccionId);
    }
}
