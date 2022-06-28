package com.sagatechs.asistencias.webServices.endpoints;

import com.sagatechs.asistencias.model.GrupoFamiliar;
import com.sagatechs.asistencias.model.catalogs.*;
import com.sagatechs.asistencias.services.CatalogService;
import com.sagatechs.asistencias.services.GrupoFamiliarService;
import com.sagatechs.asistencias.services.SyncronizactionService;
import com.sagatechs.asistencias.webServices.modelWeb.*;
import com.sagatechs.generics.exceptions.GeneralAppException;
import com.sagatechs.generics.webservice.webModel.UserWeb;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Path("/administration")
@RequestScoped
public class AdmistrationEndpoint {

    @Inject
    private CatalogService catalogService;

    @Inject
    private GrupoFamiliarService grupoFamiliarService;


    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(AdmistrationEndpoint.class);


    @Path("/test")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void getGruposFamiliaresByDate() throws GeneralAppException {
        LOGGER.debug("ya");
    }



    @Path("/catalog/{catalogType}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CatalogWeb> getCatalog(@PathParam("catalogType") String catalogType) throws GeneralAppException {
        if(StringUtils.isBlank(catalogType)){
            throw new GeneralAppException("Tipo de Catalogo No Implementado", Response.Status.BAD_REQUEST.getStatusCode());
        }
        return this.catalogService.findAllWeb(catalogType);
    }

    @Path("/catalog/TipoAsistencia")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void createTipoAsistencia(TipoAsistenciaWeb tipoAsistenciaWeb) throws GeneralAppException {

        this.catalogService.createTipoAsistencia(tipoAsistenciaWeb);
    }

    @Path("/catalog/Provincia")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void createProvincia(ProvinciaWeb provinciaWeb) throws GeneralAppException {

        this.catalogService.createProvincia(provinciaWeb);
    }
    @Path("/catalog/Ciudad")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void createCiudad(CiudadWeb ciudadWeb) throws GeneralAppException {

        this.catalogService.createCiudad(ciudadWeb);
    }

    @Path("/catalog/{catalogType}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void createCatalog(@PathParam("catalogType") String catalogType,CatalogWeb catalogWeb) throws GeneralAppException {
        if(StringUtils.isBlank(catalogType)){
            throw new GeneralAppException("Tipo de Catalogo No Implementado", Response.Status.BAD_REQUEST.getStatusCode());
        }
        this.catalogService.createCatalog(catalogType,catalogWeb);
    }

    @Path("/catalog/TipoAsistencia")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void updateTipoAsistencia(TipoAsistenciaWeb tipoAsistenciaWeb) throws GeneralAppException {

        this.catalogService.updateTipoAsistencia(tipoAsistenciaWeb);
    }

    @Path("/catalog/Provincia")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void updateProvincia(ProvinciaWeb provinciaWeb) throws GeneralAppException {

        this.catalogService.updateProvincia(provinciaWeb);
    }
    @Path("/catalog/Ciudad")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void udpateCiudad(CiudadWeb ciudadWeb) throws GeneralAppException {

        this.catalogService.updateCiudad(ciudadWeb);
    }

    @Path("/catalog/{catalogType}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void updateCatalog(@PathParam("catalogType") String catalogType,CatalogWeb catalogWeb) throws GeneralAppException {
        if(StringUtils.isBlank(catalogType)){
            throw new GeneralAppException("Tipo de Catalogo No Implementado", Response.Status.BAD_REQUEST.getStatusCode());
        }
        this.catalogService.updateCatalog(catalogType,catalogWeb);
    }

    @Path("/user")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void user(UserWeb userWeb) throws GeneralAppException {

    }

    @Path("/gruposFamiliaresReport")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GrupoFamiliarSyncWeb gruposFamiliaresReport() throws GeneralAppException {
        GrupoFamiliarSyncWeb r = new GrupoFamiliarSyncWeb();
        r.setGruposFamiliares(this.grupoFamiliarService.findByAllWithData());
        r.setQueryTime(LocalDateTime.now());
        return r;
    }

}
