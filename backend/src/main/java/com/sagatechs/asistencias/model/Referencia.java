package com.sagatechs.asistencias.model;

import com.sagatechs.asistencias.model.catalogs.MotivoReferencia;
import com.sagatechs.asistencias.model.catalogs.OrganizacionReferencia;
import com.sagatechs.generics.persistence.model.BaseEntity;
import com.sagatechs.generics.persistence.model.audit.AuditListener;
import com.sagatechs.generics.persistence.model.audit.Auditable;
import com.sagatechs.generics.persistence.model.audit.Auditoria;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "referencias", schema = "asistencias")
@EntityListeners(AuditListener.class)
public class Referencia extends BaseEntity<UUID> implements Auditable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "observacion", columnDefinition = "text")
    private String observacion;

    @Column(name = "ultima_fecha_actualizacion", nullable = false)
    public LocalDateTime ultimaFechaActualizacion;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organizacion_referencia_id", foreignKey = @ForeignKey(name = "fk_referencia_organizacion_referencia"))
    private OrganizacionReferencia organizacionReferencia;

    @ManyToMany
    @JoinTable(name = "referencia_motivos_referencias",
            joinColumns = @JoinColumn(name = "referencia_id", foreignKey = @ForeignKey(name = "fk_referencia_motivo_referencia")),
            inverseJoinColumns = @JoinColumn(name = "motivo_referencia_id", foreignKey = @ForeignKey(name = "fk_motivo_referencia_referencia"))
    )
    private Set<MotivoReferencia> motivosReferencia = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "grupo_familiar_id")
    private GrupoFamiliar grupoFamiliar;

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public OrganizacionReferencia getOrganizacionReferencia() {
        return organizacionReferencia;
    }

    public void setOrganizacionReferencia(OrganizacionReferencia organizacionReferencia) {
        this.organizacionReferencia = organizacionReferencia;
    }

    public Set<MotivoReferencia> getMotivosReferencia() {
        return motivosReferencia;
    }

    public void setMotivosReferencia(Set<MotivoReferencia> motivosReferencia) {
        this.motivosReferencia = motivosReferencia;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
