package com.sagatechs.asistencias.model;

import com.sagatechs.generics.persistence.model.BaseEntity;
import com.sagatechs.generics.persistence.model.audit.AuditListener;
import com.sagatechs.generics.persistence.model.audit.Auditable;
import com.sagatechs.generics.persistence.model.audit.Auditoria;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(schema = "asistencias", name = "grupo_familiar")
@EntityListeners(AuditListener.class)
public class GrupoFamiliar extends BaseEntity<UUID> implements Auditable {

    @Id
/*    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )*/
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "pg-uuid")
    private UUID id;

    //@Field(index = Index.YES)
    @Column(name = "grupo_familiar_id", unique = true, nullable = false)
    private String grupoFamiliarId;

    @Column(name = "ultima_fecha_actualizacion", nullable = false)
    public LocalDateTime ultimaFechaActualizacion;


    @Column(name = "monto_ingreso_mensual")
    private BigDecimal montoIngresosMensual;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "grupoFamiliar")
    private Set<Beneficiario> beneficiarios = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "grupoFamiliar")
    private Set<IntegracionSocioeconomica> integracionesSocioEconomicas = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "grupoFamiliar")
    private NecesidadEspecifica necesidadesEspecifica ;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "grupoFamiliar")
    private Set<Seguimiento> seguimientos = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "grupoFamiliar")
    private Set<Asistencia> asistencias = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "grupoFamiliar")
    private Set<Referencia> referencias = new HashSet<>();


    @Embedded
    protected Auditoria auditoria;


    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getGrupoFamiliarId() {
        return grupoFamiliarId;
    }

    public void setGrupoFamiliarId(String grupoFamiliarId) {
        this.grupoFamiliarId = grupoFamiliarId;
    }

    @Override
    public Auditoria getAuditoria() {
        return auditoria;
    }

    @Override
    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    public Set<Beneficiario> getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(Set<Beneficiario> beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

    public void addBeneficiario(Beneficiario beneficiario){
        beneficiario.setGrupoFamiliar(this);
        if(!this.beneficiarios.add(beneficiario)){
            this.beneficiarios.remove(beneficiario);
            this.beneficiarios.add(beneficiario);
        }
    }

    public BigDecimal getMontoIngresosMensual() {
        return montoIngresosMensual;
    }

    public void setMontoIngresosMensual(BigDecimal montoIngresosMensual) {
        this.montoIngresosMensual = montoIngresosMensual;
    }

    public Set<IntegracionSocioeconomica> getIntegracionesSocioEconomicas() {
        return integracionesSocioEconomicas;
    }

    public void setIntegracionesSocioEconomicas(Set<IntegracionSocioeconomica> integracionesSocioEconomicas) {
        this.integracionesSocioEconomicas = integracionesSocioEconomicas;
    }

    public void addIntegracionSocioeconomica(IntegracionSocioeconomica integracionSocioeconomica){
        integracionSocioeconomica.setGrupoFamiliar(this);
        if(!this.integracionesSocioEconomicas.add(integracionSocioeconomica)){
            this.integracionesSocioEconomicas.remove(integracionSocioeconomica);
            this.integracionesSocioEconomicas.add(integracionSocioeconomica);
        }
    }

    public NecesidadEspecifica getNecesidadesEspecifica() {
        return necesidadesEspecifica;
    }

    public void setNecesidadesEspecifica(NecesidadEspecifica necesidadesEspecifica) {
        this.necesidadesEspecifica = necesidadesEspecifica;
    }

    public Set<Seguimiento> getSeguimientos() {
        return seguimientos;
    }

    public void setSeguimientos(Set<Seguimiento> seguimientos) {
        this.seguimientos = seguimientos;
    }

    public void addSeguimiento(Seguimiento seguimiento){
        seguimiento.setGrupoFamiliar(this);
        if(!this.seguimientos.add(seguimiento)){
            this.seguimientos.remove(seguimiento);
            this.seguimientos.add(seguimiento);
        }
    }

    public LocalDateTime getUltimaFechaActualizacion() {
        return ultimaFechaActualizacion;
    }

    public void setUltimaFechaActualizacion(LocalDateTime ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    public Set<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(Set<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }

    public void addAsistencia(Asistencia asistencia){
        asistencia.setGrupoFamiliar(this);
        if(!this.asistencias.add(asistencia)){
            this.asistencias.remove(asistencia);
            this.asistencias.add(asistencia);
        }
    }

    public Set<Referencia> getReferencias() {
        return referencias;
    }

    public void setReferencias(Set<Referencia> referencias) {
        this.referencias = referencias;
    }

    public void addReferencia(Referencia referencia){
        referencia.setGrupoFamiliar(this);
        if(!this.referencias.add(referencia)){
            this.referencias.remove(referencia);
            this.referencias.add(referencia);
        }
    }
}
