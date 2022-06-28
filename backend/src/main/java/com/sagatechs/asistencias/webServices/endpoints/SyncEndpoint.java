package com.sagatechs.asistencias.webServices.endpoints;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sagatechs.asistencias.services.CatalogService;
import com.sagatechs.asistencias.services.GrupoFamiliarService;
import com.sagatechs.asistencias.services.SyncronizactionService;
import com.sagatechs.asistencias.webServices.modelWeb.*;
import com.sagatechs.generics.exceptions.GeneralAppException;
import com.sagatechs.generics.utils.DateUtils;
import org.jboss.logging.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Path("/sync")
@RequestScoped
public class SyncEndpoint {

    @Inject
    private DateUtils dateUtils;
    @Inject
    private SyncEndpointMock syncEndpointMock;

    @Inject
    private SyncronizactionService syncronizactionService;

    @Inject
    private CatalogService catalogService;

    @Inject
    private GrupoFamiliarService grupoFamiliarService;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(SyncEndpoint.class);


    @Path("/grupoFamiliar/{lastQueryTime}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GrupoFamiliarSyncWeb getGruposFamiliaresByDate(@PathParam("lastQueryTime") String localDateTimeString) throws GeneralAppException {
        LocalDateTime dateTime = null;
        try {
            dateTime = dateUtils.StringtoLocalDateTime(localDateTimeString);
        } catch (Exception e) {
            throw new GeneralAppException("Error en el parámetro fecha de ultima sincronización", Response.Status.BAD_REQUEST.getStatusCode());
        }
        List<GrupoFamiliarWeb> gfws = this.grupoFamiliarService.findAfterSyncDate(dateTime);
        GrupoFamiliarSyncWeb r = new GrupoFamiliarSyncWeb();
        r.setGruposFamiliares(gfws);
        r.setQueryTime(LocalDateTime.now());
        return r;
    }

    @Path("/grupoFamiliar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GrupoFamiliarSyncWeb getGruposFamiliares() {

        LocalDateTime dateTime = null;
        List<GrupoFamiliarWeb> gfws = this.grupoFamiliarService.findAfterSyncDate(dateTime);
        GrupoFamiliarSyncWeb r = new GrupoFamiliarSyncWeb();
        r.setGruposFamiliares(gfws);
        r.setQueryTime(LocalDateTime.now());
        return r;

    }


    @Path("/grupoFamiliar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void postGruposFamiliaresByDate(
            List<GrupoFamiliarWeb> grupoFamiliarWebs
    ) throws GeneralAppException {
        this.syncronizactionService.syncGruposFamiliares(grupoFamiliarWebs);

    }


    @Path("/catalogs")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CatalogListWeb getAllCatalogs() {

        return this.catalogService.getCatalogListWeb();
    }



    @Path("/testDate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TestDateWeb testget() {
        return new TestDateWeb();
    }

}
