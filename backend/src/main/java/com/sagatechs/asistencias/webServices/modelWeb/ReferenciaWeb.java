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

public class ReferenciaWeb {

    private String id;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime ultimaFechaActualizacion;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fecha;

    private Long organizacionesReferenciaIds;

    private String observacion;

    private Set<Long> motivosReferenciaIds = new HashSet<>();


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

    public Long getOrganizacionesReferenciaIds() {
        return organizacionesReferenciaIds;
    }

    public void setOrganizacionesReferenciaIds(Long organizacionesReferenciaIds) {
        this.organizacionesReferenciaIds = organizacionesReferenciaIds;
    }

    public Set<Long> getMotivosReferenciaIds() {
        return motivosReferenciaIds;
    }

    public void setMotivosReferenciaIds(Set<Long> motivosReferenciaIds) {
        this.motivosReferenciaIds = motivosReferenciaIds;
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
