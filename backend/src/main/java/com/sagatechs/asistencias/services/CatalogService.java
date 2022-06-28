package com.sagatechs.asistencias.services;

import com.sagatechs.asistencias.dao.catalogs.*;
import com.sagatechs.asistencias.model.catalogs.*;
import com.sagatechs.asistencias.webServices.modelWeb.*;
import com.sagatechs.generics.exceptions.GeneralAppException;
import com.sagatechs.generics.persistence.model.State;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CatalogService {

    private final static Logger LOGGER = Logger.getLogger(CatalogService.class);

    @Inject
    private NivelParentescoDao nivelParentescoDao;
    @Inject
    private SexoDao sexoDao;
    @Inject
    private GeneroDao generoDao;
    @Inject
    private LgbtiDao lgbtiDao;
    @Inject
    private NacionalidadDao nacionalidadDao;
    @Inject
    private EtniaDao etniaDao;
    @Inject
    private DiscapacidadDao discapacidadDao;
    @Inject
    private TipoDocumentoDao tipoDocumentoDao;
    @Inject
    private EstatutoMigratorioDao estatutoMigratorioDao;
    @Inject
    private SituacionMigratoriaDao situacionMigratoriaDao;
    @Inject
    private MotivoSalidaPaisDao motivoSalidaPaisDao;
    @Inject
    private NivelEscolaridadDao nivelEscolaridadDao;
    @Inject
    private PaisDao paisDao;
    @Inject
    private ProvinciaDao provinciaDao;
    @Inject
    private CiudadDao ciudadDao;
    @Inject
    private OrganizacionApoyoDao organizacionApoyoDao;
    @Inject
    private ProfesionDao profesionDao;
    @Inject
    private OficioDao oficioDao;
    @Inject
    private NecesidadesEspecificasProteccionDao necesidadesEspecificasProteccionDao;
    @Inject
    private NecesidadesEspecificasHabitabilidadDao necesidadesEspecificasHabitabilidadDao;
    @Inject
    private ComponenteDao componenteDao;
    @Inject
    private TipoAyudaDao tipoAyudaDao;
    @Inject
    private MotivoReferenciaDao motivoReferenciaDao;
    @Inject
    private OrganizacionReferenciaDao organizacionReferenciaDao;
    @Inject
    private TipoAsistenciaDao tipoAsistenciaDao;
    @Inject
    private TipoSeguimientoDao tipoSeguimientoDao;


    public CatalogListWeb getCatalogListWeb() {
        CatalogListWeb r = new CatalogListWeb();
        r.setPaises(this.getPaisesWebActive());
        r.setComponentes(this.getComponentesWeb());
        r.setDiscapacidades(this.catalogsToCatalogWebs(this.discapacidadDao.findByEstado(State.ACTIVE)));
        r.setEstatutosMigratorios(this.catalogsToCatalogWebs(this.estatutoMigratorioDao.findByEstado(State.ACTIVE)));
        r.setEtnias(this.catalogsToCatalogWebs(this.etniaDao.findByEstado(State.ACTIVE)));
        r.setGeneros(this.catalogsToCatalogWebs(this.generoDao.findByEstado(State.ACTIVE)));
        r.setLgbtis(this.catalogsToCatalogWebs(this.lgbtiDao.findByEstado(State.ACTIVE)));
        r.setMotivosReferencias(this.catalogsToCatalogWebs(this.motivoReferenciaDao.findByEstado(State.ACTIVE)));
        r.setMotivosSalidaPais(this.catalogsToCatalogWebs(this.motivoSalidaPaisDao.findByEstado(State.ACTIVE)));
        r.setNacionalidades(this.catalogsToCatalogWebs(this.nacionalidadDao.findByEstado(State.ACTIVE)));
        r.setNecesidadesEspecificasProteccion(this.catalogsToCatalogWebs(this.necesidadesEspecificasProteccionDao.findByEstado(State.ACTIVE)));
        r.setNecesidadesEspecificasHabitabilidad(this.catalogsToCatalogWebs(this.necesidadesEspecificasHabitabilidadDao.findByEstado(State.ACTIVE)));
        r.setNivelesEscolaridad(this.catalogsToCatalogWebs(this.nivelEscolaridadDao.findByEstado(State.ACTIVE)));
        r.setNivelesParentesco(this.catalogsToCatalogWebs(this.nivelParentescoDao.findByEstado(State.ACTIVE)));
        r.setOficios(this.catalogsToCatalogWebs(this.oficioDao.findByEstado(State.ACTIVE)));
        r.setOrganizacionesApoyo(this.catalogsToCatalogWebs(this.organizacionApoyoDao.findByEstado(State.ACTIVE)));
        r.setOrganizacionesReferencia(this.catalogsToCatalogWebs(this.organizacionReferenciaDao.findByEstado(State.ACTIVE)));
        r.setProfesiones(this.catalogsToCatalogWebs(this.profesionDao.findByEstado(State.ACTIVE)));
        r.setSexos(this.catalogsToCatalogWebs(this.sexoDao.findByEstado(State.ACTIVE)));
        r.setSituacionesMigratorias(this.catalogsToCatalogWebs(this.situacionMigratoriaDao.findByEstado(State.ACTIVE)));
        r.setTiposAsistencia(this.catalogsToCatalogWebs(this.tipoAsistenciaDao.findByEstado(State.ACTIVE)));
        r.setTiposAyuda(this.catalogsToCatalogWebs(this.tipoAyudaDao.findByEstado(State.ACTIVE)));
        r.setTiposDocumento(this.catalogsToCatalogWebs(this.tipoDocumentoDao.findByEstado(State.ACTIVE)));
        r.setTiposSeguimiento(this.catalogsToCatalogWebs(this.tipoSeguimientoDao.findByEstado(State.ACTIVE)));


        return r;


    }

    private List<ComponenteWeb> getComponentesWebActive() {
        List<Componente> componentes = this.componenteDao.findByEstado(State.ACTIVE);
        return this.componentesToComponentesWeb(componentes, true);
    }

    private List<ComponenteWeb> componentesToComponentesWeb(List<Componente> componentes, boolean setTiposAsistencia) {
        List<ComponenteWeb> r = new ArrayList<>();
        for (Componente componente : componentes) {
            ComponenteWeb cw = new ComponenteWeb();
            cw.setId(componente.getId());
            cw.setNombre(componente.getNombre());
            cw.setState(componente.getEstado());
            if (setTiposAsistencia) {
                cw.setTiposAsistencia(this.tiposAsistenciaToTiposAsistenciaWeb(new ArrayList<>(componente.getTiposAsistencias())));
            }
            r.add(cw);
        }
        return r;
    }

    private ComponenteWeb componenteToComponenteWeb(Componente componente, boolean setTiposAsistencia) {

        ComponenteWeb cw = new ComponenteWeb();
        cw.setId(componente.getId());
        cw.setNombre(componente.getNombre());
        cw.setState(componente.getEstado());
        if (setTiposAsistencia) {
            cw.setTiposAsistencia(this.tiposAsistenciaToTiposAsistenciaWeb(new ArrayList<>(componente.getTiposAsistencias())));
        }

        return cw;
    }

    private List<TipoAsistenciaWeb> tiposAsistenciaToTiposAsistenciaWeb(List<TipoAsistencia> tiposAsistencia) {
        List<TipoAsistenciaWeb> r = new ArrayList<>();
        for (TipoAsistencia tipoAsistencia : tiposAsistencia) {
            r.add(this.tipoAsistenciaToTipoAsistenciaWeb(tipoAsistencia));
        }
        return r;
    }

    private TipoAsistenciaWeb tipoAsistenciaToTipoAsistenciaWeb(TipoAsistencia tipoAsistencia) {

        TipoAsistenciaWeb cw = new TipoAsistenciaWeb();
        cw.setId(tipoAsistencia.getId());
        cw.setNombre(tipoAsistencia.getNombre());
        cw.setState(tipoAsistencia.getEstado());
        cw.setComponente(this.componenteToComponenteWeb(tipoAsistencia.getComponente(), false));

        return cw;

    }

    private List<PaisWeb> getPaisesWebActive() {

        List<Pais> paises = this.paisDao.findByEstado(State.ACTIVE);
        return this.paisesToPaisesWeb(paises, true);
    }

    private PaisWeb paisToPaisWeb(Pais pais, boolean setProvincias) {
        if (pais == null) {
            return null;
        }

        PaisWeb pw = new PaisWeb(this.catalogToCatalogWeb(pais));
        if (setProvincias) {
            pw.setProvincias(this.provinciasToprovinciasWeb(new ArrayList<>(pais.getProvincias()), setProvincias));
        }
        return pw;
    }

    private List<PaisWeb> paisesToPaisesWeb(List<Pais> paises, boolean setProvincias) {

        List<PaisWeb> r = new ArrayList<>();
        for (Pais pais : paises) {
            r.add(this.paisToPaisWeb(pais, setProvincias));
        }
        return r;
    }

    private List<ProvinciaWeb> provinciasToprovinciasWeb(List<Provincia> provincias, boolean setCiudades) {

        List<ProvinciaWeb> r = new ArrayList<>();
        for (Provincia provincia : provincias) {
            r.add(this.provinciaToProvinciaWeb(provincia, setCiudades));
        }
        return r;
    }

    private ProvinciaWeb provinciaToProvinciaWeb(Provincia provincia, boolean setCiudades) {
        ProvinciaWeb pw = new ProvinciaWeb(this.catalogToCatalogWeb(provincia));
        if (setCiudades) {
            pw.setCiudades(this.ciudadesToCiudadesWeb(new ArrayList(provincia.getCiudades())));
        }
        pw.setPais(this.paisToPaisWeb(provincia.getPais(), false));
        return pw;
    }


    private List<CiudadWeb> ciudadesToCiudadesWeb(List<Ciudad> ciudades) {

        List<CiudadWeb> r = new ArrayList<>();
        for (Ciudad ciudad : ciudades) {
            r.add(this.ciudadToCiudadWeb(ciudad));
        }
        return r;
    }

    private CiudadWeb ciudadToCiudadWeb(Ciudad ciudad) {
        CiudadWeb cw = new CiudadWeb(this.catalogToCatalogWeb(ciudad));
        cw.setProvincia(this.provinciaToProvinciaWeb(ciudad.getProvincia(), false));
        return cw;
    }

    private List<PaisWeb> getPaisesWeb() {

        List<Pais> paises = this.paisDao.findAll();
        return this.paisesToPaisesWeb(paises, true);
    }

    private List<ComponenteWeb> getComponentesWeb() {

        List<Componente> componentes = this.componenteDao.findAll();
        return this.componentesToComponentesWeb(componentes, true);
    }

    private List<TipoAsistenciaWeb> getTiposAsistenciaWeb() {

        List<TipoAsistencia> tiposAsistencia = this.tipoAsistenciaDao.findAll();
        return this.tiposAsistenciaToTiposAsistenciaWeb(tiposAsistencia);
    }

    private List<ProvinciaWeb> getProvinciasWeb() {

        List<Provincia> provincias = this.provinciaDao.findAll();
        return this.provinciasToprovinciasWeb(provincias, true);

    }

    private List<CiudadWeb> getCiudadesWeb() {

        List<Ciudad> ciudades = this.ciudadDao.findAll();
        return this.ciudadesToCiudadesWeb(ciudades);

    }

    private <C extends CatalogoBase> List<CatalogWeb> catalogsToCatalogWebs(List<C> catalogos) {
        List<CatalogWeb> r = new ArrayList<>();
        for (C catalogo : catalogos) {
            r.add(this.catalogToCatalogWeb(catalogo));
        }

        return r;
    }

    private <C extends CatalogoBase> CatalogWeb catalogToCatalogWeb(C catalogo) {

        if (catalogo == null) {
            return null;
        }

        CatalogWeb r = new CatalogWeb();
        r.setId(catalogo.getId());
        r.setNombre(catalogo.getNombre());
        r.setState(catalogo.getEstado());
        return r;
    }

    public <C extends CatalogoBase> C findById(Long id, Class<C> entityClass) throws GeneralAppException {
        if (id == null) {
            return null;
        }
        switch (entityClass.getSimpleName()) {
            case "NivelParentesco":
                return (C) this.nivelParentescoDao.find(id);
            case "Sexo":
                return (C) this.sexoDao.find(id);
            case "Genero":
                return (C) this.generoDao.find(id);
            case "Lgbti":
                return (C) this.lgbtiDao.find(id);
            case "Nacionalidad":
                return (C) this.nacionalidadDao.find(id);
            case "Etnia":
                return (C) this.etniaDao.find(id);
            case "Discapacidad":
                return (C) this.discapacidadDao.find(id);
            case "TipoDocumento":
                return (C) this.tipoDocumentoDao.find(id);
            case "EstatutoMigratorio":
                return (C) this.estatutoMigratorioDao.find(id);
            case "SituacionMigratoria":
                return (C) this.situacionMigratoriaDao.find(id);
            case "MotivoSalidaPais":
                return (C) this.motivoSalidaPaisDao.find(id);
            case "NivelEscolaridad":
                return (C) this.nivelEscolaridadDao.find(id);
            case "Pais":
                return (C) this.paisDao.find(id);
            case "Provincia":
                return (C) this.provinciaDao.find(id);
            case "Ciudad":
                return (C) this.ciudadDao.find(id);
            case "OrganizacionApoyo":
                return (C) this.organizacionApoyoDao.find(id);
            case "Profesion":
                return (C) this.profesionDao.find(id);
            case "Oficio":
                return (C) this.oficioDao.find(id);
            case "NecesidadEspecificaProteccion":
                return (C) this.necesidadesEspecificasProteccionDao.find(id);
            case "NecesidadEspecificaHabitabilidad":
                return (C) this.necesidadesEspecificasHabitabilidadDao.find(id);
            case "Componente":
                return (C) this.componenteDao.find(id);
            case "TipoAyuda":
                return (C) this.tipoAyudaDao.find(id);
            case "MotivoReferencia":
                return (C) this.motivoReferenciaDao.find(id);
            case "OrganizacionReferencia":
                return (C) this.organizacionReferenciaDao.find(id);
            case "TipoAsistencia":
                return (C) this.tipoAsistenciaDao.find(id);
            case "TipoSeguimiento":
                return (C) this.tipoSeguimientoDao.find(id);
            default:
                throw new GeneralAppException("Tipo no implementado " + entityClass.getSimpleName(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }

    }

    public <C extends CatalogWeb> List<C> findAllWeb(String entityClass) throws GeneralAppException {

        switch (entityClass) {
            case "NivelParentesco":
                return (List<C>) this.catalogsToCatalogWebs(this.nivelParentescoDao.findAll());
            case "Sexo":
                return (List<C>) this.catalogsToCatalogWebs(this.sexoDao.findAll());
            case "Genero":
                return (List<C>) this.catalogsToCatalogWebs(this.generoDao.findAll());
            case "Lgbti":
                return (List<C>) this.catalogsToCatalogWebs(this.lgbtiDao.findAll());
            case "Nacionalidad":
                return (List<C>) this.catalogsToCatalogWebs(this.nacionalidadDao.findAll());
            case "Etnia":
                return (List<C>) this.catalogsToCatalogWebs(this.etniaDao.findAll());
            case "Discapacidad":
                return (List<C>) this.catalogsToCatalogWebs(this.discapacidadDao.findAll());
            case "TipoDocumento":
                return (List<C>) this.catalogsToCatalogWebs(this.tipoDocumentoDao.findAll());
            case "EstatutoMigratorio":
                return (List<C>) this.catalogsToCatalogWebs(this.estatutoMigratorioDao.findAll());
            case "SituacionMigratoria":
                return (List<C>) this.catalogsToCatalogWebs(this.situacionMigratoriaDao.findAll());
            case "MotivoSalidaPais":
                return (List<C>) this.catalogsToCatalogWebs(this.motivoSalidaPaisDao.findAll());
            case "NivelEscolaridad":
                return (List<C>) this.catalogsToCatalogWebs(this.nivelEscolaridadDao.findAll());
            case "Pais":
                return (List<C>) this.getPaisesWeb();
            case "Provincia":
                return (List<C>) this.getProvinciasWeb();
            case "Ciudad":
                return (List<C>) this.getCiudadesWeb();
            case "OrganizacionApoyo":
                return (List<C>) this.catalogsToCatalogWebs(this.organizacionApoyoDao.findAll());
            case "Profesion":
                return (List<C>) this.catalogsToCatalogWebs(this.profesionDao.findAll());
            case "Oficio":
                return (List<C>) this.catalogsToCatalogWebs(this.oficioDao.findAll());
            case "NecesidadEspecificaProteccion":
                return (List<C>) this.catalogsToCatalogWebs(this.necesidadesEspecificasProteccionDao.findAll());
            case "NecesidadEspecificaHabitabilidad":
                return (List<C>) this.catalogsToCatalogWebs(this.necesidadesEspecificasHabitabilidadDao.findAll());
            case "Componente":
                return (List<C>) this.getComponentesWeb();
            case "TipoAyuda":
                return (List<C>) this.catalogsToCatalogWebs(this.tipoAyudaDao.findAll());
            case "MotivoReferencia":
                return (List<C>) this.catalogsToCatalogWebs(this.motivoReferenciaDao.findAll());
            case "OrganizacionReferencia":
                return (List<C>) this.catalogsToCatalogWebs(this.organizacionReferenciaDao.findAll());
            case "TipoAsistencia":
                return (List<C>) this.getTiposAsistenciaWeb();
            case "TipoSeguimiento":
                return (List<C>) this.catalogsToCatalogWebs(this.tipoSeguimientoDao.findAll());
            default:
                throw new GeneralAppException("Tipo no implementado " + entityClass, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }

    }


    public <C extends CatalogoBase> List<C> findByIds(List<Long> ids, Class<C> entityClass) throws GeneralAppException {
        switch (entityClass.getSimpleName()) {
            case "NivelParentesco":
                return (List<C>) this.nivelEscolaridadDao.findByIds(ids);
            case "Sexo":
                return (List<C>) this.sexoDao.findByIds(ids);
            case "Genero":
                return (List<C>) this.generoDao.findByIds(ids);
            case "Lgbti":
                return (List<C>) this.lgbtiDao.findByIds(ids);
            case "Nacionalidad":
                return (List<C>) this.nacionalidadDao.findByIds(ids);
            case "Etnia":
                return (List<C>) this.etniaDao.findByIds(ids);
            case "Discapacidad":
                return (List<C>) this.discapacidadDao.findByIds(ids);
            case "TipoDocumento":
                return (List<C>) this.tipoDocumentoDao.findByIds(ids);
            case "EstatutoMigratorio":
                return (List<C>) this.estatutoMigratorioDao.findByIds(ids);
            case "SituacionMigratoria":
                return (List<C>) this.situacionMigratoriaDao.findByIds(ids);
            case "MotivoSalidaPais":
                return (List<C>) this.motivoSalidaPaisDao.findByIds(ids);
            case "NivelEscolaridad":
                return (List<C>) this.nivelEscolaridadDao.findByIds(ids);
            case "Pais":
                return (List<C>) this.paisDao.findByIds(ids);
            case "Provincia":
                return (List<C>) this.provinciaDao.findByIds(ids);
            case "Ciudad":
                return (List<C>) this.ciudadDao.findByIds(ids);
            case "OrganizacionApoyo":
                return (List<C>) this.organizacionApoyoDao.findByIds(ids);
            case "Profesion":
                return (List<C>) this.profesionDao.findByIds(ids);
            case "Oficio":
                return (List<C>) this.oficioDao.findByIds(ids);
            case "NecesidadEspecificaProteccion":
                return (List<C>) this.necesidadesEspecificasProteccionDao.findByIds(ids);
            case "NecesidadEspecificaHabitabilidad":
                return (List<C>) this.necesidadesEspecificasHabitabilidadDao.findByIds(ids);
            case "Componente":
                return (List<C>) this.componenteDao.findByIds(ids);
            case "TipoAyuda":
                return (List<C>) this.tipoAyudaDao.findByIds(ids);
            case "MotivoReferencia":
                return (List<C>) this.motivoReferenciaDao.findByIds(ids);
            case "OrganizacionReferencia":
                return (List<C>) this.organizacionReferenciaDao.findByIds(ids);
            case "TipoAsistencia":
                return (List<C>) this.tipoAsistenciaDao.findByIds(ids);
            case "TipoSeguimiento":
                return (List<C>) this.tipoSeguimientoDao.findByIds(ids);
            default:
                throw new GeneralAppException("Tipo no implementado by ids" + entityClass.getSimpleName(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }

    }


    public void createCatalog(String catalogType, CatalogWeb catalogWeb) throws GeneralAppException {
        if (catalogWeb == null) {
            throw new GeneralAppException("El catalogo no puede ser null", Response.Status.BAD_REQUEST.getStatusCode());
        }

        switch (catalogType) {
            case "NivelParentesco":
                NivelParentesco nivelParentescoO = this.nivelParentescoDao.findByNombre(catalogWeb.getNombre());
                if (nivelParentescoO != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                NivelParentesco i = new NivelParentesco();
                this.catalogWebToCatalog(i, catalogWeb);
                this.nivelParentescoDao.save(i);
                break;

            case "Sexo":
                Sexo sexoO = this.sexoDao.findByNombre(catalogWeb.getNombre());
                if (sexoO != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                Sexo sexo = new Sexo();
                this.catalogWebToCatalog(sexo, catalogWeb);
                this.sexoDao.save(sexo);
                break;
            case "Genero":
                Genero Generoo = this.generoDao.findByNombre(catalogWeb.getNombre());
                if (Generoo != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                Genero genero = new Genero();
                this.catalogWebToCatalog(genero, catalogWeb);
                this.generoDao.save(genero);
                break;
            case "Lgbti":
                Lgbti Lgbtio = this.lgbtiDao.findByNombre(catalogWeb.getNombre());
                if (Lgbtio != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                Lgbti Lgbti = new Lgbti();
                this.catalogWebToCatalog(Lgbti, catalogWeb);
                this.lgbtiDao.save(Lgbti);
                break;
            case "Nacionalidad":
                Nacionalidad Nacionalidado = this.nacionalidadDao.findByNombre(catalogWeb.getNombre());
                if (Nacionalidado != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                Nacionalidad Nacionalidad = new Nacionalidad();
                this.catalogWebToCatalog(Nacionalidad, catalogWeb);
                this.nacionalidadDao.save(Nacionalidad);
                break;
            case "Etnia":
                Etnia Etniao = this.etniaDao.findByNombre(catalogWeb.getNombre());
                if (Etniao != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                Etnia Etnia = new Etnia();
                this.catalogWebToCatalog(Etnia, catalogWeb);
                this.etniaDao.save(Etnia);
                break;
            case "Discapacidad":
                Discapacidad Discapacidado = this.discapacidadDao.findByNombre(catalogWeb.getNombre());
                if (Discapacidado != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                Discapacidad Discapacidad = new Discapacidad();
                this.catalogWebToCatalog(Discapacidad, catalogWeb);
                this.discapacidadDao.save(Discapacidad);
                break;
            case "TipoDocumento":
                TipoDocumento TipoDocumentoo = this.tipoDocumentoDao.findByNombre(catalogWeb.getNombre());
                if (TipoDocumentoo != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                TipoDocumento TipoDocumento = new TipoDocumento();
                this.catalogWebToCatalog(TipoDocumento, catalogWeb);
                this.tipoDocumentoDao.save(TipoDocumento);
                break;
            case "EstatutoMigratorio":
                EstatutoMigratorio EstatutoMigratorioo = this.estatutoMigratorioDao.findByNombre(catalogWeb.getNombre());
                if (EstatutoMigratorioo != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                EstatutoMigratorio EstatutoMigratorio = new EstatutoMigratorio();
                this.catalogWebToCatalog(EstatutoMigratorio, catalogWeb);
                this.estatutoMigratorioDao.save(EstatutoMigratorio);
                break;
            case "SituacionMigratoria":
                SituacionMigratoria SituacionMigratoriao = this.situacionMigratoriaDao.findByNombre(catalogWeb.getNombre());
                if (SituacionMigratoriao != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                SituacionMigratoria SituacionMigratoria = new SituacionMigratoria();
                this.catalogWebToCatalog(SituacionMigratoria, catalogWeb);
                this.situacionMigratoriaDao.save(SituacionMigratoria);
                break;
            case "MotivoSalidaPais":
                MotivoSalidaPais MotivoSalidaPaiso = this.motivoSalidaPaisDao.findByNombre(catalogWeb.getNombre());
                if (MotivoSalidaPaiso != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                MotivoSalidaPais MotivoSalidaPais = new MotivoSalidaPais();
                this.catalogWebToCatalog(MotivoSalidaPais, catalogWeb);
                this.motivoSalidaPaisDao.save(MotivoSalidaPais);
                break;
            case "NivelEscolaridad":
                NivelEscolaridad NivelEscolaridado = this.nivelEscolaridadDao.findByNombre(catalogWeb.getNombre());
                if (NivelEscolaridado != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                NivelEscolaridad NivelEscolaridad = new NivelEscolaridad();
                this.catalogWebToCatalog(NivelEscolaridad, catalogWeb);
                this.nivelEscolaridadDao.save(NivelEscolaridad);
                break;
            case "Pais":
                Pais Paiso = this.paisDao.findByNombre(catalogWeb.getNombre());
                if (Paiso != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                Pais Pais = new Pais();
                this.catalogWebToCatalog(Pais, catalogWeb);
                this.paisDao.save(Pais);
                break;

            case "OrganizacionApoyo":
                OrganizacionApoyo OrganizacionApoyoo = this.organizacionApoyoDao.findByNombre(catalogWeb.getNombre());
                if (OrganizacionApoyoo != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                OrganizacionApoyo OrganizacionApoyo = new OrganizacionApoyo();
                this.catalogWebToCatalog(OrganizacionApoyo, catalogWeb);
                this.organizacionApoyoDao.save(OrganizacionApoyo);
                break;
            case "Profesion":
                NivelParentesco NivelParentescoo = this.nivelParentescoDao.findByNombre(catalogWeb.getNombre());
                if (NivelParentescoo != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                Profesion Profesion = new Profesion();
                this.catalogWebToCatalog(Profesion, catalogWeb);
                this.profesionDao.save(Profesion);
                break;
            case "Oficio":
                Oficio Oficioo = this.oficioDao.findByNombre(catalogWeb.getNombre());
                if (Oficioo != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                Oficio Oficio = new Oficio();
                this.catalogWebToCatalog(Oficio, catalogWeb);
                this.oficioDao.save(Oficio);
                break;
            case "NecesidadEspecificaProteccion":
                NecesidadEspecificaProteccion NecesidadEspecificaProtecciono = this.necesidadesEspecificasProteccionDao.findByNombre(catalogWeb.getNombre());
                if (NecesidadEspecificaProtecciono != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                NecesidadEspecificaProteccion NecesidadEspecificaProteccion = new NecesidadEspecificaProteccion();
                this.catalogWebToCatalog(NecesidadEspecificaProteccion, catalogWeb);
                this.necesidadesEspecificasProteccionDao.save(NecesidadEspecificaProteccion);
                break;
            case "NecesidadEspecificaHabitabilidad":
                NecesidadEspecificaHabitabilidad NecesidadEspecificaHabitabilidado = this.necesidadesEspecificasHabitabilidadDao.findByNombre(catalogWeb.getNombre());
                if (NecesidadEspecificaHabitabilidado != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                NecesidadEspecificaHabitabilidad NecesidadEspecificaHabitabilidad = new NecesidadEspecificaHabitabilidad();
                this.catalogWebToCatalog(NecesidadEspecificaHabitabilidad, catalogWeb);
                this.necesidadesEspecificasHabitabilidadDao.save(NecesidadEspecificaHabitabilidad);
                break;
            case "Componente":
                Componente Componenteo = this.componenteDao.findByNombre(catalogWeb.getNombre());
                if (Componenteo != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                Componente Componente = new Componente();
                this.catalogWebToCatalog(Componente, catalogWeb);
                this.componenteDao.save(Componente);
                break;
            case "TipoAyuda":
                TipoAyuda TipoAyudao = this.tipoAyudaDao.findByNombre(catalogWeb.getNombre());
                if (TipoAyudao != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                TipoAyuda TipoAyuda = new TipoAyuda();
                this.catalogWebToCatalog(TipoAyuda, catalogWeb);
                this.tipoAyudaDao.save(TipoAyuda);
                break;
            case "MotivoReferencia":
                MotivoReferencia MotivoReferenciao = this.motivoReferenciaDao.findByNombre(catalogWeb.getNombre());
                if (MotivoReferenciao != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                MotivoReferencia MotivoReferencia = new MotivoReferencia();
                this.catalogWebToCatalog(MotivoReferencia, catalogWeb);
                this.motivoReferenciaDao.save(MotivoReferencia);
                break;
            case "OrganizacionReferencia":
                OrganizacionReferencia OrganizacionReferenciao = this.organizacionReferenciaDao.findByNombre(catalogWeb.getNombre());
                if (OrganizacionReferenciao != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                OrganizacionReferencia OrganizacionReferencia = new OrganizacionReferencia();
                this.catalogWebToCatalog(OrganizacionReferencia, catalogWeb);
                this.organizacionReferenciaDao.save(OrganizacionReferencia);
                break;
            case "TipoSeguimiento":
                TipoSeguimiento TipoSeguimientoo = this.tipoSeguimientoDao.findByNombre(catalogWeb.getNombre());
                if (TipoSeguimientoo != null) {
                    throw new GeneralAppException("Ya existe un item con este nombre");
                }
                TipoSeguimiento TipoSeguimiento = new TipoSeguimiento();
                this.catalogWebToCatalog(TipoSeguimiento, catalogWeb);
                this.tipoSeguimientoDao.save(TipoSeguimiento);
                break;


            default:
                throw new GeneralAppException("Tipo no implementado " + catalogType, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }

    }

    private <T extends CatalogoBase> T catalogWebToCatalog(T c, CatalogWeb catalogWeb) {
        if (c == null) {
            return null;
        }

        c.setId(catalogWeb.getId());
        c.setNombre(catalogWeb.getNombre());
        c.setEstado(catalogWeb.getState());

        return c;
    }

    public void createTipoAsistencia(TipoAsistenciaWeb tipoAsistenciaWeb) throws GeneralAppException {
        TipoAsistencia tipoAsistencia = new TipoAsistencia();
        this.catalogWebToCatalog(tipoAsistencia, tipoAsistenciaWeb);
        if (tipoAsistenciaWeb.getComponente() == null || tipoAsistenciaWeb.getComponente().getId() == null) {
            throw new GeneralAppException("No se especifico el componente", Response.Status.BAD_REQUEST.getStatusCode());
        }
        Componente comp = this.findById(tipoAsistenciaWeb.getComponente().getId(), Componente.class);
        tipoAsistencia.setComponente(comp);
        comp.addTipoAsistencia(tipoAsistencia);
        this.tipoAsistenciaDao.save(tipoAsistencia);
    }


    public void createProvincia(ProvinciaWeb provinciaWeb) throws GeneralAppException {
        Provincia provincia = new Provincia();
        this.catalogWebToCatalog(provincia, provinciaWeb);
        if (provinciaWeb.getPais() == null || provinciaWeb.getPais().getId() == null) {
            throw new GeneralAppException("No se especifico el pais", Response.Status.BAD_REQUEST.getStatusCode());
        }
        Pais comp = this.findById(provinciaWeb.getPais().getId(), Pais.class);
        provincia.setPais(comp);
        comp.addProvincia(provincia);
        this.provinciaDao.save(provincia);
    }


    public void createCiudad(CiudadWeb ciudadWeb) throws GeneralAppException {
        Ciudad ciudad = new Ciudad();
        this.catalogWebToCatalog(ciudad, ciudadWeb);
        if (ciudadWeb.getProvincia() == null || ciudadWeb.getProvincia().getId() == null) {
            throw new GeneralAppException("No se especifico la provincia", Response.Status.BAD_REQUEST.getStatusCode());
        }
        Provincia comp = this.findById(ciudadWeb.getProvincia().getId(), Provincia.class);
        ciudad.setProvincia(comp);
        comp.addCiudad(ciudad);
        this.ciudadDao.save(ciudad);
    }

    public void updateTipoAsistencia(TipoAsistenciaWeb tipoAsistenciaWeb) throws GeneralAppException {

        if (tipoAsistenciaWeb.getId() == null || tipoAsistenciaWeb.getComponente() == null || tipoAsistenciaWeb.getComponente().getId() == null) {
            throw new GeneralAppException("No se especifico el componente", Response.Status.BAD_REQUEST.getStatusCode());
        }
        TipoAsistencia tipoAsistencia = this.tipoAsistenciaDao.find(tipoAsistenciaWeb.getId());
        this.catalogWebToCatalog(tipoAsistencia, tipoAsistenciaWeb);
        Componente comp = this.findById(tipoAsistenciaWeb.getComponente().getId(), Componente.class);
        tipoAsistencia.setComponente(comp);
        comp.addTipoAsistencia(tipoAsistencia);
        this.tipoAsistenciaDao.update(tipoAsistencia);
    }


    public void updateProvincia(ProvinciaWeb provinciaWeb) throws GeneralAppException {
        if (provinciaWeb.getId() == null || provinciaWeb.getPais() == null || provinciaWeb.getPais().getId() == null) {
            throw new GeneralAppException("No se especifico el pais", Response.Status.BAD_REQUEST.getStatusCode());
        }
        Provincia provincia = this.provinciaDao.find(provinciaWeb.getId());
        this.catalogWebToCatalog(provincia, provinciaWeb);

        Pais comp = this.findById(provinciaWeb.getPais().getId(), Pais.class);
        provincia.setPais(comp);
        comp.addProvincia(provincia);
        this.provinciaDao.update(provincia);
    }


    public void updateCiudad(CiudadWeb ciudadWeb) throws GeneralAppException {
        if (ciudadWeb.getId() == null || ciudadWeb.getProvincia() == null || ciudadWeb.getProvincia().getId() == null) {
            throw new GeneralAppException("No se especifico la provincia", Response.Status.BAD_REQUEST.getStatusCode());
        }
        Ciudad ciudad = this.ciudadDao.find(ciudadWeb.getId());
        this.catalogWebToCatalog(ciudad, ciudadWeb);

        Provincia comp = this.findById(ciudadWeb.getProvincia().getId(), Provincia.class);
        ciudad.setProvincia(comp);
        comp.addCiudad(ciudad);
        this.ciudadDao.update(ciudad);
    }

    public void updateCatalog(String catalogType, CatalogWeb catalogWeb) throws GeneralAppException {
        if (catalogWeb == null || catalogWeb.getId() == null) {
            throw new GeneralAppException("El catalogo no puede ser null", Response.Status.BAD_REQUEST.getStatusCode());
        }

        switch (catalogType) {
            case "NivelParentesco":
                NivelParentesco i = this.nivelParentescoDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(i, catalogWeb);
                this.nivelParentescoDao.update(i);
                break;

            case "Sexo":
                Sexo sexo = this.sexoDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(sexo, catalogWeb);
                this.sexoDao.update(sexo);
                break;
            case "Genero":
                Genero genero = this.generoDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(genero, catalogWeb);
                this.generoDao.update(genero);
                break;
            case "Lgbti":
                Lgbti Lgbti = this.lgbtiDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(Lgbti, catalogWeb);
                this.lgbtiDao.update(Lgbti);
                break;
            case "Nacionalidad":
                Nacionalidad Nacionalidad = this.nacionalidadDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(Nacionalidad, catalogWeb);
                this.nacionalidadDao.update(Nacionalidad);
                break;
            case "Etnia":
                Etnia Etnia = this.etniaDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(Etnia, catalogWeb);
                this.etniaDao.update(Etnia);
                break;
            case "Discapacidad":
                Discapacidad Discapacidad = this.discapacidadDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(Discapacidad, catalogWeb);
                this.discapacidadDao.update(Discapacidad);
                break;
            case "TipoDocumento":
                TipoDocumento TipoDocumento = this.tipoDocumentoDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(TipoDocumento, catalogWeb);
                this.tipoDocumentoDao.update(TipoDocumento);
                break;
            case "EstatutoMigratorio":
                EstatutoMigratorio EstatutoMigratorio = this.estatutoMigratorioDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(EstatutoMigratorio, catalogWeb);
                this.estatutoMigratorioDao.update(EstatutoMigratorio);
                break;
            case "SituacionMigratoria":
                SituacionMigratoria SituacionMigratoria = this.situacionMigratoriaDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(SituacionMigratoria, catalogWeb);
                this.situacionMigratoriaDao.update(SituacionMigratoria);
                break;
            case "MotivoSalidaPais":
                MotivoSalidaPais MotivoSalidaPais = this.motivoSalidaPaisDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(MotivoSalidaPais, catalogWeb);
                this.motivoSalidaPaisDao.update(MotivoSalidaPais);
                break;
            case "NivelEscolaridad":
                NivelEscolaridad NivelEscolaridad = this.nivelEscolaridadDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(NivelEscolaridad, catalogWeb);
                this.nivelEscolaridadDao.update(NivelEscolaridad);
                break;
            case "Pais":
                Pais Pais = this.paisDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(Pais, catalogWeb);
                this.paisDao.update(Pais);
                break;

            case "OrganizacionApoyo":
                OrganizacionApoyo OrganizacionApoyo = this.organizacionApoyoDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(OrganizacionApoyo, catalogWeb);
                this.organizacionApoyoDao.update(OrganizacionApoyo);
                break;
            case "Profesion":
                Profesion Profesion = this.profesionDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(Profesion, catalogWeb);
                this.profesionDao.update(Profesion);
                break;
            case "Oficio":
                Oficio Oficio = this.oficioDao.find(catalogWeb.getId());
                ;
                this.catalogWebToCatalog(Oficio, catalogWeb);
                this.oficioDao.update(Oficio);
                break;
            case "NecesidadEspecificaProteccion":
                NecesidadEspecificaProteccion NecesidadEspecificaProteccion = this.necesidadesEspecificasProteccionDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(NecesidadEspecificaProteccion, catalogWeb);
                this.necesidadesEspecificasProteccionDao.update(NecesidadEspecificaProteccion);
                break;
            case "NecesidadEspecificaHabitabilidad":
                NecesidadEspecificaHabitabilidad NecesidadEspecificaHabitabilidad = this.necesidadesEspecificasHabitabilidadDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(NecesidadEspecificaHabitabilidad, catalogWeb);
                this.necesidadesEspecificasHabitabilidadDao.update(NecesidadEspecificaHabitabilidad);
                break;
            case "Componente":
                Componente Componente = this.componenteDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(Componente, catalogWeb);
                this.componenteDao.update(Componente);
                break;
            case "TipoAyuda":
                TipoAyuda TipoAyuda = this.tipoAyudaDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(TipoAyuda, catalogWeb);
                this.tipoAyudaDao.update(TipoAyuda);
                break;
            case "MotivoReferencia":
                MotivoReferencia MotivoReferencia = this.motivoReferenciaDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(MotivoReferencia, catalogWeb);
                this.motivoReferenciaDao.update(MotivoReferencia);
                break;
            case "OrganizacionReferencia":
                OrganizacionReferencia OrganizacionReferencia = this.organizacionReferenciaDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(OrganizacionReferencia, catalogWeb);
                this.organizacionReferenciaDao.update(OrganizacionReferencia);
                break;
            case "TipoSeguimiento":
                TipoSeguimiento TipoSeguimiento = this.tipoSeguimientoDao.find(catalogWeb.getId());
                this.catalogWebToCatalog(TipoSeguimiento, catalogWeb);
                this.tipoSeguimientoDao.update(TipoSeguimiento);
                break;


            default:
                throw new GeneralAppException("Tipo no implementado " + catalogType, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }

    }
}
