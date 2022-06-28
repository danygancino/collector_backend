package com.sagatechs.asistencias.webServices.modelWeb;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateDeserializer;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateSerializer;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateTimeDeserializer;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class BeneficiarioWeb {

    private String id;

    private String grupoFamiliarId;
    private String grupoFamiliarCode;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime ultimaFechaActualizacion;

    private String nombres;

    private Long nivelParentescoId;

    private Long sexoId;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fechaNacimiento;

    private Integer edad;

    private Long generoId;

    private Long lgbtiId;

    private Long etniaId;

    private Long nacionalidadId;

    private Long discapacidadId;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fechaIngresoPais;

    private Long tipoDocumentoId;

    private String numeroDocumento;

    private Long estatutoMigratorioId;

    private Long situacionMigratoriaId;

    private Long motivoSalidaPaisId;

    private Long ciudadId;

    private String sector;

    private String direccion;

    private String telefono;

    private Long nivelEscolaridadId;

    private Set<Long> profesionesIds = new HashSet<>();

    private Set<Long> oficiosIds = new HashSet<>();

    private String observaciones;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Long getNivelParentescoId() {
        return nivelParentescoId;
    }

    public void setNivelParentescoId(Long nivelParentescoId) {
        this.nivelParentescoId = nivelParentescoId;
    }

    public Long getSexoId() {
        return sexoId;
    }

    public void setSexoId(Long sexoId) {
        this.sexoId = sexoId;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Long getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Long generoId) {
        this.generoId = generoId;
    }

    public Long getLgbtiId() {
        return lgbtiId;
    }

    public void setLgbtiId(Long lgbtiId) {
        this.lgbtiId = lgbtiId;
    }

    public Long getEtniaId() {
        return etniaId;
    }

    public void setEtniaId(Long etniaId) {
        this.etniaId = etniaId;
    }

    public Long getNacionalidadId() {
        return nacionalidadId;
    }

    public void setNacionalidadId(Long nacionalidadId) {
        this.nacionalidadId = nacionalidadId;
    }

    public Long getDiscapacidadId() {
        return discapacidadId;
    }

    public void setDiscapacidadId(Long discapacidadId) {
        this.discapacidadId = discapacidadId;
    }

    public LocalDate getFechaIngresoPais() {
        return fechaIngresoPais;
    }

    public void setFechaIngresoPais(LocalDate fechaIngresoPais) {
        this.fechaIngresoPais = fechaIngresoPais;
    }

    public Long getTipoDocumentoId() {
        return tipoDocumentoId;
    }

    public void setTipoDocumentoId(Long tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Long getEstatutoMigratorioId() {
        return estatutoMigratorioId;
    }

    public void setEstatutoMigratorioId(Long estatutoMigratorioId) {
        this.estatutoMigratorioId = estatutoMigratorioId;
    }

    public Long getSituacionMigratoriaId() {
        return situacionMigratoriaId;
    }

    public void setSituacionMigratoriaId(Long situacionMigratoriaId) {
        this.situacionMigratoriaId = situacionMigratoriaId;
    }

    public Long getMotivoSalidaPaisId() {
        return motivoSalidaPaisId;
    }

    public void setMotivoSalidaPaisId(Long motivoSalidaPaisId) {
        this.motivoSalidaPaisId = motivoSalidaPaisId;
    }

    public Long getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Long ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getNivelEscolaridadId() {
        return nivelEscolaridadId;
    }

    public void setNivelEscolaridadId(Long nivelEscolaridadId) {
        this.nivelEscolaridadId = nivelEscolaridadId;
    }

    public Set<Long> getProfesionesIds() {
        return profesionesIds;
    }

    public void setProfesionesIds(Set<Long> profesionesIds) {
        this.profesionesIds = profesionesIds;
    }

    public Set<Long> getOficiosIds() {
        return oficiosIds;
    }

    public void setOficiosIds(Set<Long> oficiosIds) {
        this.oficiosIds = oficiosIds;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDateTime getUltimaFechaActualizacion() {
        return ultimaFechaActualizacion;
    }

    public void setUltimaFechaActualizacion(LocalDateTime ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    public String getGrupoFamiliarId() {
        return grupoFamiliarId;
    }

    public void setGrupoFamiliarId(String grupoFamiliarId) {
        this.grupoFamiliarId = grupoFamiliarId;
    }

    public String getGrupoFamiliarCode() {
        return grupoFamiliarCode;
    }

    public void setGrupoFamiliarCode(String grupoFamiliarCode) {
        this.grupoFamiliarCode = grupoFamiliarCode;
    }
}
