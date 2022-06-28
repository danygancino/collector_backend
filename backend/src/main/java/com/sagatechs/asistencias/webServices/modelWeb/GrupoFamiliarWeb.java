package com.sagatechs.asistencias.webServices.modelWeb;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateTimeDeserializer;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateTimeSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class GrupoFamiliarWeb {

    private String id;

    private String grupoFamiliarId;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime ultimaFechaActualizacion;

    private List<BeneficiarioWeb> beneficiarios;

    private BigDecimal montoIngresosMensual;

    private List<AsistenciaWeb> asistencias;

    private List<ReferenciaWeb> referencias;

    private List<IntegracionSocioeconomicaWeb> integracionesSocioEconomicas;

    private NecesidadEspecificaWeb necesidadesEspecifica;

    private List<SeguimientoWeb> seguimientos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrupoFamiliarId() {
        return grupoFamiliarId;
    }

    public void setGrupoFamiliarId(String grupoFamiliarId) {
        this.grupoFamiliarId = grupoFamiliarId;
    }

    public List<BeneficiarioWeb> getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(List<BeneficiarioWeb> beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

    public BigDecimal getMontoIngresosMensual() {
        return montoIngresosMensual;
    }

    public void setMontoIngresosMensual(BigDecimal montoIngresosMensual) {
        this.montoIngresosMensual = montoIngresosMensual;
    }

    public List<AsistenciaWeb> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<AsistenciaWeb> asistencias) {
        this.asistencias = asistencias;
    }

    public List<ReferenciaWeb> getReferencias() {
        return referencias;
    }

    public void setReferencias(List<ReferenciaWeb> referencias) {
        this.referencias = referencias;
    }

    public List<IntegracionSocioeconomicaWeb> getIntegracionesSocioEconomicas() {
        return integracionesSocioEconomicas;
    }

    public void setIntegracionesSocioEconomicas(List<IntegracionSocioeconomicaWeb> integracionesSocioEconomicas) {
        this.integracionesSocioEconomicas = integracionesSocioEconomicas;
    }

    public NecesidadEspecificaWeb getNecesidadesEspecifica() {
        return necesidadesEspecifica;
    }

    public void setNecesidadesEspecifica(NecesidadEspecificaWeb necesidadesEspecifica) {
        this.necesidadesEspecifica = necesidadesEspecifica;
    }

    public List<SeguimientoWeb> getSeguimientos() {
        return seguimientos;
    }

    public void setSeguimientos(List<SeguimientoWeb> seguimientos) {
        this.seguimientos = seguimientos;
    }

    public LocalDateTime getUltimaFechaActualizacion() {
        return ultimaFechaActualizacion;
    }

    public void setUltimaFechaActualizacion(LocalDateTime ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }
}
