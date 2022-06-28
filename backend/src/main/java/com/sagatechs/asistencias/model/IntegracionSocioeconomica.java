package com.sagatechs.asistencias.model;

import com.sagatechs.asistencias.model.catalogs.Oficio;
import com.sagatechs.asistencias.model.catalogs.OrganizacionApoyo;
import com.sagatechs.asistencias.model.catalogs.TipoAyuda;
import com.sagatechs.generics.persistence.model.BaseEntity;
import com.sagatechs.generics.persistence.model.audit.AuditListener;
import com.sagatechs.generics.persistence.model.audit.Auditable;
import com.sagatechs.generics.persistence.model.audit.Auditoria;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "integraciones_socioeconomicas", schema = "asistencias")
@EntityListeners(AuditListener.class)
public class IntegracionSocioeconomica extends BaseEntity<UUID> implements Auditable {

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


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grupo_familiar_id", foreignKey = @ForeignKey(name = "fk_integracion_grupo_familiar"))
    private GrupoFamiliar grupoFamiliar;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organizacion_apoyo_id", foreignKey = @ForeignKey(name = "fk_integracion_organizacion_apoyo"))
    private OrganizacionApoyo organizacionApoyo;

    @Embedded
    protected Auditoria auditoria;

    @ManyToMany
    @JoinTable(name = "integraciones_socioeconomicas_tipos_ayudas",
            joinColumns = @JoinColumn(name = "integracion_socioeconomica_id",foreignKey = @ForeignKey(name = "fk_integracion_socioeconomica_tipos_ayudas")),
            inverseJoinColumns = @JoinColumn(name = "tipos_ayudas_id",foreignKey = @ForeignKey(name = "fk_tipos_ayudas_integracion_socioeconomica"))
    )
    private Set<TipoAyuda> tiposAyuda = new HashSet<>();

    @Column(name = "observaciones", columnDefinition = "text")
    private String observaciones;


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

    public OrganizacionApoyo getOrganizacionApoyo() {
        return organizacionApoyo;
    }

    public void setOrganizacionApoyo(OrganizacionApoyo organizacionApoyo) {
        this.organizacionApoyo = organizacionApoyo;
    }

    public Set<TipoAyuda> getTiposAyuda() {
        return tiposAyuda;
    }

    public void setTiposAyuda(Set<TipoAyuda> tiposAyuda) {
        this.tiposAyuda = tiposAyuda;
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
