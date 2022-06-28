package com.sagatechs.asistencias.webServices.modelWeb;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateTimeDeserializer;
import com.sagatechs.generics.webservice.jsonSerializers.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class NecesidadEspecificaWeb {

    private String id;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime ultimaFechaActualizacion;

    private Set<NecesidadEspecificaProteccionWeb> necesidadesEspecificasProteccion = new HashSet<>();

    private Set<NecesidadEspecificaHabitabilidadWeb> necesidadesEspecificasHabitabilidad = new HashSet<>();

    private String observaciones;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<NecesidadEspecificaProteccionWeb> getNecesidadesEspecificasProteccion() {
        return necesidadesEspecificasProteccion;
    }

    public void setNecesidadesEspecificasProteccion(Set<NecesidadEspecificaProteccionWeb> necesidadesEspecificasProteccion) {
        this.necesidadesEspecificasProteccion = necesidadesEspecificasProteccion;
    }

    public Set<NecesidadEspecificaHabitabilidadWeb> getNecesidadesEspecificasHabitabilidad() {
        return necesidadesEspecificasHabitabilidad;
    }

    public void setNecesidadesEspecificasHabitabilidad(Set<NecesidadEspecificaHabitabilidadWeb> necesidadesEspecificasHabitabilidad) {
        this.necesidadesEspecificasHabitabilidad = necesidadesEspecificasHabitabilidad;
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

    public void addNecesidadEspecificaProteccion(NecesidadEspecificaProteccionWeb necesidadEspecificaProteccionWeb){
        if(!this.necesidadesEspecificasProteccion.add(necesidadEspecificaProteccionWeb)){
            this.necesidadesEspecificasProteccion.remove(necesidadEspecificaProteccionWeb);
            this.necesidadesEspecificasProteccion.add(necesidadEspecificaProteccionWeb);
        }
    }
    public void addNecesidadEspecificaHabitabilidad(NecesidadEspecificaHabitabilidadWeb necesidadEspecificaHabitabilidadWeb){
        if(!this.necesidadesEspecificasHabitabilidad.add(necesidadEspecificaHabitabilidadWeb)){
            this.necesidadesEspecificasHabitabilidad.remove(necesidadEspecificaHabitabilidadWeb);
            this.necesidadesEspecificasHabitabilidad.add(necesidadEspecificaHabitabilidadWeb);
        }
    }


}
