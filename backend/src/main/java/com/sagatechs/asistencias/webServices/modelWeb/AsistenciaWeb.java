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

public class AsistenciaWeb {

    private String id;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime ultimaFechaActualizacion;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fecha;

    private String observacion;

    private Long componenteId;

    private Long tipoAsistenciaId;

    private String nombrePropietarioVivienda;

    private String numeroDocumentoPropietarioVivienda;

    private String telefonoPropietarioVivienda;

    private Integer numeroEspacioHabitables;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getComponenteId() {
        return componenteId;
    }

    public void setComponenteId(Long componenteId) {
        this.componenteId = componenteId;
    }

    public Long getTipoAsistenciaId() {
        return tipoAsistenciaId;
    }

    public void setTipoAsistenciaId(Long tipoAsistenciaId) {
        this.tipoAsistenciaId = tipoAsistenciaId;
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
