package com.sagatechs.asistencias.webServices.modelWeb;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateDeserializer;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateSerializer;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateTimeDeserializer;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SeguimientoWeb {

    private String id;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime ultimaFechaActualizacion;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fecha;

    private Long componenteId;

    private Long tipoSeguimientoId;

    private String observaciones;

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

    public Long getTipoSeguimientoId() {
        return tipoSeguimientoId;
    }

    public void setTipoSeguimientoId(Long tipoSeguimientoId) {
        this.tipoSeguimientoId = tipoSeguimientoId;
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
