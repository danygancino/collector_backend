package com.sagatechs.asistencias.model;

import com.sagatechs.asistencias.model.catalogs.NecesidadEspecificaHabitabilidad;
import com.sagatechs.asistencias.model.catalogs.NecesidadEspecificaProteccion;
import com.sagatechs.generics.persistence.model.BaseEntity;
import com.sagatechs.generics.persistence.model.audit.AuditListener;
import com.sagatechs.generics.persistence.model.audit.Auditable;
import com.sagatechs.generics.persistence.model.audit.Auditoria;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "necesidades_especificas", schema = "asistencias")
@EntityListeners(AuditListener.class)
public class NecesidadEspecifica extends BaseEntity<UUID> implements Auditable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "ultima_fecha_actualizacion", nullable = false)
    public LocalDateTime ultimaFechaActualizacion;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "grupo_familiar_id", foreignKey = @ForeignKey(name = "fk_necesidad_grupo_familiar"))
    private GrupoFamiliar grupoFamiliar;


    @OneToMany(
            mappedBy = "necesidadEspecifica",
            cascade = CascadeType.ALL
    )
    private Set<NecesidadEspecificaNecesidadProteccion> necesidadEspecificaNecesidadProtecciones = new HashSet<>();

    @OneToMany(
            mappedBy = "necesidadEspecifica", cascade = CascadeType.ALL
    )
    private Set<NecesidadEspecificaNecesidadHabitabilidad> necesidadEspecificaNecesidadHabitabilidades = new HashSet<>();


    @Column(name = "observaciones", columnDefinition = "text")
    private String observaciones;


    @Embedded
    protected Auditoria auditoria;


    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public Auditoria getAuditoria() {
        return auditoria;
    }

    @Override
    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Set<NecesidadEspecificaNecesidadProteccion> getNecesidadEspecificaNecesidadProtecciones() {
        return necesidadEspecificaNecesidadProtecciones;
    }

    public void setNecesidadEspecificaNecesidadProtecciones(Set<NecesidadEspecificaNecesidadProteccion> necesidadEspecificaNecesidadProtecciones) {
        this.necesidadEspecificaNecesidadProtecciones = necesidadEspecificaNecesidadProtecciones;
    }

    public Set<NecesidadEspecificaNecesidadHabitabilidad> getNecesidadEspecificaNecesidadHabitabilidades() {
        return necesidadEspecificaNecesidadHabitabilidades;
    }

    public void setNecesidadEspecificaNecesidadHabitabilidades(Set<NecesidadEspecificaNecesidadHabitabilidad> necesidadEspecificaNecesidadHabitabilidades) {
        this.necesidadEspecificaNecesidadHabitabilidades = necesidadEspecificaNecesidadHabitabilidades;
    }

    public GrupoFamiliar getGrupoFamiliar() {
        return grupoFamiliar;
    }

    public void setGrupoFamiliar(GrupoFamiliar grupoFamiliar) {
        this.grupoFamiliar = grupoFamiliar;
    }

    public LocalDateTime getUltimaFechaActualizacion() {
        return ultimaFechaActualizacion;
    }

    public void setUltimaFechaActualizacion(LocalDateTime ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        NecesidadEspecifica that = (NecesidadEspecifica) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(id).toHashCode();
    }

    public NecesidadEspecificaNecesidadProteccion addNecesidadEspecificaProteccion(NecesidadEspecificaProteccion necesidadEspecificaProteccion, Integer cantidad) {
        NecesidadEspecificaNecesidadProteccion necesidadEspecificaNecesidadProteccion = new NecesidadEspecificaNecesidadProteccion(this, necesidadEspecificaProteccion);
        necesidadEspecificaNecesidadProteccion.setCantidad(cantidad);
        necesidadEspecificaNecesidadProtecciones.add(necesidadEspecificaNecesidadProteccion);
        necesidadEspecificaNecesidadProtecciones.forEach(neh -> {
            if(neh.getNecesidadEspecificaProteccion().getNombre() == necesidadEspecificaProteccion.getNombre()){
                neh.setCantidad(cantidad);
            }
        });
        return necesidadEspecificaNecesidadProteccion;
    }

    public void removeNecesidadEspecificaProteccion(NecesidadEspecificaProteccion necesidadEspecificaProteccion) {
        for (Iterator<NecesidadEspecificaNecesidadProteccion> iterator = necesidadEspecificaNecesidadProtecciones.iterator();
             iterator.hasNext(); ) {
            NecesidadEspecificaNecesidadProteccion necesidadEspecificaNecesidadProteccion = iterator.next();

            if (necesidadEspecificaNecesidadProteccion.getNecesidadEspecifica().equals(this) &&
                    necesidadEspecificaNecesidadProteccion.getNecesidadEspecificaProteccion().equals(necesidadEspecificaProteccion)) {
                iterator.remove();
                necesidadEspecificaNecesidadProteccion.setNecesidadEspecifica(null);
                necesidadEspecificaNecesidadProteccion.setNecesidadEspecificaProteccion(null);
            }
        }
    }

    public void removeNecesidadEspecificaProteccionAll() {
        for (Iterator<NecesidadEspecificaNecesidadProteccion> iterator = necesidadEspecificaNecesidadProtecciones.iterator();
             iterator.hasNext(); ) {
            NecesidadEspecificaNecesidadProteccion necesidadEspecificaNecesidadProteccion = iterator.next();

            iterator.remove();
            necesidadEspecificaNecesidadProteccion.setNecesidadEspecifica(null);
            necesidadEspecificaNecesidadProteccion.setNecesidadEspecificaProteccion(null);

        }
    }

    public NecesidadEspecificaNecesidadHabitabilidad addNecesidadEspecificaHabitabilidad(NecesidadEspecificaHabitabilidad necesidadEspecificanecesidadEspecificaHabitabilidad, boolean cantidad) {
        NecesidadEspecificaNecesidadHabitabilidad necesidadEspecificaNecesidadHabitabilidad = new NecesidadEspecificaNecesidadHabitabilidad(this, necesidadEspecificanecesidadEspecificaHabitabilidad);
        necesidadEspecificaNecesidadHabitabilidad.setCantidad(cantidad);
        necesidadEspecificaNecesidadHabitabilidades.add(necesidadEspecificaNecesidadHabitabilidad);
        necesidadEspecificaNecesidadHabitabilidades.forEach(neh -> {
            if(neh.getNecesidadEspecificaHabitabilidad().getNombre() == necesidadEspecificanecesidadEspecificaHabitabilidad.getNombre()){
                neh.setCantidad(cantidad);
            }
        });
        return necesidadEspecificaNecesidadHabitabilidad;
    }

    public void removeNecesidadEspecificaHabitabilidad(NecesidadEspecificaHabitabilidad necesidadEspecificaHabitabilidad) {
        for (Iterator<NecesidadEspecificaNecesidadHabitabilidad> iterator = necesidadEspecificaNecesidadHabitabilidades.iterator();
             iterator.hasNext(); ) {
            NecesidadEspecificaNecesidadHabitabilidad necesidadEspecificaNecesidadHabitabilidad = iterator.next();

            if (necesidadEspecificaNecesidadHabitabilidad.getNecesidadEspecifica().equals(this) &&
                    necesidadEspecificaNecesidadHabitabilidad.getNecesidadEspecificaHabitabilidad().equals(necesidadEspecificaHabitabilidad)) {
                iterator.remove();
                necesidadEspecificaNecesidadHabitabilidad.setNecesidadEspecifica(null);
                necesidadEspecificaNecesidadHabitabilidad.setNecesidadEspecificaHabitabilidad(null);
            }
        }
    }

    public void removeNecesidadEspecificaHabitabilidadAll() {
        for (Iterator<NecesidadEspecificaNecesidadHabitabilidad> iterator = necesidadEspecificaNecesidadHabitabilidades.iterator();
             iterator.hasNext(); ) {
            NecesidadEspecificaNecesidadHabitabilidad necesidadEspecificaNecesidadHabitabilidad = iterator.next();

            iterator.remove();
            necesidadEspecificaNecesidadHabitabilidad.setNecesidadEspecifica(null);
            necesidadEspecificaNecesidadHabitabilidad.setNecesidadEspecificaHabitabilidad(null);

        }
    }
}
