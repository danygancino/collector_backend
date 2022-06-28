package com.sagatechs.asistencias.webServices.modelWeb;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateTimeDeserializer;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateTimeSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class GrupoFamiliarSyncWeb {
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime queryTime;


    List<GrupoFamiliarWeb> gruposFamiliares;

    public LocalDateTime getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(LocalDateTime queryTime) {
        this.queryTime = queryTime;
    }

    public List<GrupoFamiliarWeb> getGruposFamiliares() {
        return gruposFamiliares;
    }

    public void setGruposFamiliares(List<GrupoFamiliarWeb> gruposFamiliares) {
        this.gruposFamiliares = gruposFamiliares;
    }
}
