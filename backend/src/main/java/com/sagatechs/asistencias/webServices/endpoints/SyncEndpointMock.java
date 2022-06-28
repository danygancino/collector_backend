package com.sagatechs.asistencias.webServices.endpoints;

import com.sagatechs.asistencias.webServices.modelWeb.*;
import com.sagatechs.generics.appConfiguration.AppConfiguration;
import com.sagatechs.generics.appConfiguration.AppConfigurationDao;
import com.sagatechs.generics.appConfiguration.AppConfigurationKey;
import com.sagatechs.generics.appConfiguration.AppConfigurationService;
import com.sagatechs.generics.exceptions.GeneralAppException;
import com.sagatechs.generics.persistence.model.State;
import com.sagatechs.generics.utils.DateUtils;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Singleton
@Startup
public class SyncEndpointMock {


    private List<GrupoFamiliarWeb> grupoFamiliarWebs = new ArrayList<>();


    private static final Logger LOGGER = Logger.getLogger(SyncEndpointMock.class);



    @PostConstruct
    public void init() {
        // Arracar el sistema
        LOGGER.info("Cargando mocker");
        LOGGER.info("Terminado Cargando configuración del sistema");

    }

    public void addGfs(List<GrupoFamiliarWeb> gfs){
        this.grupoFamiliarWebs.addAll(gfs);
    }

    public List<GrupoFamiliarWeb>  getGfs(){
        return this.grupoFamiliarWebs;
    }

}
