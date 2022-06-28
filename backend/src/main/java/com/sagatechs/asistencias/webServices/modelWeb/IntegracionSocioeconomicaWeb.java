package com.sagatechs.asistencias.webServices.modelWeb;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateTimeDeserializer;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class IntegracionSocioeconomicaWeb {

    private String id;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime ultimaFechaActualizacion;

    private Long organizacionApoyoId;

    private Set<Long> tiposAyudaIds = new HashSet<>();

    private String observaciones;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getOrganizacionApoyoId() {
        return organizacionApoyoId;
    }

    public void setOrganizacionApoyoId(Long organizacionApoyoId) {
        this.organizacionApoyoId = organizacionApoyoId;
    }

    public Set<Long> getTiposAyudaIds() {
        return tiposAyudaIds;
    }

    public void setTiposAyudaIds(Set<Long> tiposAyudaIds) {
        this.tiposAyudaIds = tiposAyudaIds;
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
}
