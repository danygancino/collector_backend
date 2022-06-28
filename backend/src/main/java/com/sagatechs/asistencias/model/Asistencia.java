package com.sagatechs.asistencias.model;

import com.sagatechs.asistencias.model.catalogs.*;
import com.sagatechs.generics.persistence.model.BaseEntity;
import com.sagatechs.generics.persistence.model.audit.AuditListener;
import com.sagatechs.generics.persistence.model.audit.Auditable;
import com.sagatechs.generics.persistence.model.audit.Auditoria;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "asistencias", schema = "asistencias")
@EntityListeners(AuditListener.class)
public class Asistencia extends BaseEntity<UUID> implements Auditable {
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

    @Column(name = "ultima_fecha_actualizacion", nullable = false)
    public LocalDateTime ultimaFechaActualizacion;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "componente_id", foreignKey = @ForeignKey(name = "fk_asistencia_componente"))
    private Componente componente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_asistencia_id", foreignKey = @ForeignKey(name = "fk_asistencia_tipo_asistencia"))
    private TipoAsistencia tipoAsistencia;

    @Column(name = "nombre_propietario_vivienda")
    private String nombrePropietarioVivienda;

    @Column(name = "numero_documento_propietario_vivienda")
    private String numeroDocumentoPropietarioVivienda;

    @Column(name = "telefono_propietario_vivienda")
    private String telefonoPropietarioVivienda;

    @Column(name = "numero_espacios_habitables")
    private Integer numeroEspacioHabitables;

    @Column(name = "observacion", columnDefinition = "text")
    private String observacion;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
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

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public TipoAsistencia getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(TipoAsistencia tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

    public String getNombrePropietarioVivienda() {
        return nombrePropietarioVivienda;
    }

    public void setNombrePropietarioVivienda(String nombrePropietarioVivienda) {
        this.nombrePropietarioVivienda = nombrePropietarioVivienda;
    }

    public String getNumeroDocumentoPropietarioVivienda() {
        return numeroDocumentoPropietarioVivienda;
    }

    public void setNumeroDocumentoPropietarioVivienda(String numeroDocumentoPropietarioVivienda) {
        this.numeroDocumentoPropietarioVivienda = numeroDocumentoPropietarioVivienda;
    }

    public String getTelefonoPropietarioVivienda() {
        return telefonoPropietarioVivienda;
    }

    public void setTelefonoPropietarioVivienda(String telefonoPropietarioVivienda) {
        this.telefonoPropietarioVivienda = telefonoPropietarioVivienda;
    }

    public Integer getNumeroEspacioHabitables() {
        return numeroEspacioHabitables;
    }

    public void setNumeroEspacioHabitables(Integer numeroEspacioHabitables) {
        this.numeroEspacioHabitables = numeroEspacioHabitables;
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
