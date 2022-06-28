package com.sagatechs.asistencias.webServices.modelWeb;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateTimeDeserializer;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class NecesidadEspecificaProteccionWeb {

    private Long necesidadEspecificaProteccionId;

    private Integer cantidad;

    public Long getNecesidadEspecificaProteccionId() {
        return necesidadEspecificaProteccionId;
    }

    public void setNecesidadEspecificaProteccionId(Long necesidadEspecificaProteccionId) {
        this.necesidadEspecificaProteccionId = necesidadEspecificaProteccionId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
