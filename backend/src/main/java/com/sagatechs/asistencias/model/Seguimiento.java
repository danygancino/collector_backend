package com.sagatechs.asistencias.model;

import com.sagatechs.asistencias.model.catalogs.Componente;
import com.sagatechs.asistencias.model.catalogs.MotivoReferencia;
import com.sagatechs.asistencias.model.catalogs.OrganizacionReferencia;
import com.sagatechs.asistencias.model.catalogs.TipoSeguimiento;
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
@Table(name = "seguimientos", schema = "asistencias")
@EntityListeners(AuditListener.class)
public class Seguimiento extends BaseEntity<UUID> implements Auditable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "ultima_fecha_actualizacion", nullable = false)
    public LocalDateTime ultimaFechaActualizacion;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "grupo_familiar_id")
    private GrupoFamiliar grupoFamiliar;

    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "componente_id", foreignKey = @ForeignKey(name = "fk_seguimiento_componente"))
    private Componente componente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_seguimiento_id", foreignKey = @ForeignKey(name = "fk_seguimiento_tipo_seguimiento"))
    private TipoSeguimiento tipoSeguimiento;

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public TipoSeguimiento getTipoSeguimiento() {
        return tipoSeguimiento;
    }

    public void setTipoSeguimiento(TipoSeguimiento tipoSeguimiento) {
        this.tipoSeguimiento = tipoSeguimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
}
