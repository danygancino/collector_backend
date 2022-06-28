package com.sagatechs.asistencias.services;

import com.sagatechs.asistencias.dao.catalogs.NecesidadesEspecificasHabitabilidadDao;
import com.sagatechs.asistencias.model.*;
import com.sagatechs.asistencias.model.catalogs.*;
import com.sagatechs.asistencias.webServices.modelWeb.*;
import com.sagatechs.generics.exceptions.GeneralAppException;
import org.apache.commons.collections4.CollectionUtils;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.Console;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

@Stateless
public class SyncronizactionService {

    private final static Logger LOGGER = Logger.getLogger(SyncronizactionService.class);

    @Inject
    private GrupoFamiliarService grupoFamiliarService;

    @Inject
    private CatalogService catalogService;

    @Inject
    private BeneficiarioService beneficiarioService;

    @Inject
    private IntegracionSocioeconomicaService integracionSocioeconomicaService;

    @Inject
    private NecesidadEspecificaService necesidadEspecificaService;

    @Inject
    private SeguimientoService seguimientoService;

    @Inject
    private AsistenciaService asistenciaService;

    @Inject
    private ReferenciaService referenciaService;

    @Inject
    private NecesidadesEspecificasHabitabilidadDao necesidadesEspecificasHabitabilidadDao;

    @Inject
    private NecesidadEspecificaNecesidadHabitabilidadService necesidadEspecificaNecesidadHabitabilidadService;

    @Inject
    private NecesidadEspecificaNecesidadProteccionService necesidadEspecificaNecesidadProteccionService;

    public void syncGruposFamiliares(List<GrupoFamiliarWeb> grupoFamiliarWebs) throws GeneralAppException {

        for (GrupoFamiliarWeb grupoFamiliarWeb : grupoFamiliarWebs) {
            this.syncAllGrupoFamiliar(grupoFamiliarWeb);
        }

    }

    public void syncAllGrupoFamiliar(GrupoFamiliarWeb grupoFamiliarWeb) throws GeneralAppException {
        //grupo familiar

        GrupoFamiliar gf = this.syncGrupoFamiliar(grupoFamiliarWeb);
        this.syncIntegracionesSocioEconomicas(gf, grupoFamiliarWeb);
        this.syncNecesidadesEspecificas(gf, grupoFamiliarWeb);
        this.syncSeguimientos(gf, grupoFamiliarWeb);
        this.syncAsistencias(gf, grupoFamiliarWeb);
        this.syncReferencias(gf, grupoFamiliarWeb);
        this.syncBeneficiarios(gf, grupoFamiliarWeb);
    }

    private void syncReferencias(GrupoFamiliar gf, GrupoFamiliarWeb gfw) throws GeneralAppException {
        Set<Referencia> refs = gf.getReferencias();
        // compruebo que existan
        if (CollectionUtils.isEmpty(gfw.getReferencias())) {
            return;
        }
        for (ReferenciaWeb refw : gfw.getReferencias()) {
            UUID id = UUID.fromString(refw.getId());
            Optional<Referencia> refOp = refs.stream().filter(referencia -> {
                return referencia.getId().equals(id);
            }).findFirst();
            if (refOp.isPresent()) {
                // ya existe
                Referencia ref = refOp.get();
                ref.setUltimaFechaActualizacion(refw.getUltimaFechaActualizacion());
                ref.setFecha(refw.getFecha());
                ref.setObservacion(refw.getObservacion());
                ref.setOrganizacionReferencia(this.catalogService.findById(refw.getOrganizacionesReferenciaIds(), OrganizacionReferencia.class));
                ref.setMotivosReferencia(new HashSet<>());
                if (CollectionUtils.isNotEmpty(refw.getMotivosReferenciaIds())) {
                    List<Long> mrIds = new ArrayList<>(refw.getMotivosReferenciaIds());
                    List<MotivoReferencia> motivoReferencias = this.catalogService.findByIds(mrIds, MotivoReferencia.class);
                    ref.getMotivosReferencia().addAll(new HashSet<>(motivoReferencias));
                }

                this.referenciaService.update(ref);
            } else {
                // no existe creo
                Referencia ref = new Referencia();
                ref.setId(id);
                ref.setUltimaFechaActualizacion(refw.getUltimaFechaActualizacion());
                ref.setFecha(refw.getFecha());
                ref.setObservacion(refw.getObservacion());
                ref.setOrganizacionReferencia(this.catalogService.findById(refw.getOrganizacionesReferenciaIds(), OrganizacionReferencia.class));
                ref.setMotivosReferencia(new HashSet<>());
                if (CollectionUtils.isNotEmpty(refw.getMotivosReferenciaIds())) {
                    List<Long> mrIds = new ArrayList<>(refw.getMotivosReferenciaIds());
                    List<MotivoReferencia> motivoReferencias = this.catalogService.findByIds(mrIds, MotivoReferencia.class);
                    ref.getMotivosReferencia().addAll(new HashSet<>(motivoReferencias));
                }

                gf.addReferencia(ref);

                // this.grupoFamiliarService.saveOrUpdate(gf);
                this.referenciaService.update(ref);
            }
        }

    }

    private void syncAsistencias(GrupoFamiliar gf, GrupoFamiliarWeb gfw) throws GeneralAppException {
        Set<Asistencia> asis = gf.getAsistencias();
        // compruebo que existan
        if (CollectionUtils.isEmpty(gfw.getAsistencias())) {
            return;
        }
        for (AsistenciaWeb asiw : gfw.getAsistencias()) {
            UUID id = UUID.fromString(asiw.getId());
            Optional<Asistencia> asiOp = asis.stream().filter(asistencia -> {
                return asistencia.getId().equals(id);
            }).findFirst();
            if (asiOp.isPresent()) {
                // ya existe
                Asistencia asi = asiOp.get();
                asi.setUltimaFechaActualizacion(asiw.getUltimaFechaActualizacion());
                asi.setFecha(asiw.getFecha());
                asi.setComponente(this.catalogService.findById(asiw.getComponenteId(), Componente.class));
                asi.setTipoAsistencia(this.catalogService.findById(asiw.getTipoAsistenciaId(), TipoAsistencia.class));
                asi.setNombrePropietarioVivienda(asiw.getNombrePropietarioVivienda());
                asi.setNumeroDocumentoPropietarioVivienda(asiw.getNumeroDocumentoPropietarioVivienda());
                asi.setTelefonoPropietarioVivienda(asiw.getTelefonoPropietarioVivienda());
                asi.setNumeroEspacioHabitables(asiw.getNumeroEspacioHabitables());
                asi.setObservacion(asiw.getObservacion());
                this.asistenciaService.update(asi);
            } else {
                // no existe creo
                Asistencia asi = new Asistencia();
                asi.setId(id);
                asi.setUltimaFechaActualizacion(asiw.getUltimaFechaActualizacion());
                asi.setFecha(asiw.getFecha());
                asi.setComponente(this.catalogService.findById(asiw.getComponenteId(), Componente.class));
                asi.setTipoAsistencia(this.catalogService.findById(asiw.getTipoAsistenciaId(), TipoAsistencia.class));
                asi.setNombrePropietarioVivienda(asiw.getNombrePropietarioVivienda());
                asi.setNumeroDocumentoPropietarioVivienda(asiw.getNumeroDocumentoPropietarioVivienda());
                asi.setTelefonoPropietarioVivienda(asiw.getTelefonoPropietarioVivienda());
                asi.setNumeroEspacioHabitables(asiw.getNumeroEspacioHabitables());
                gf.addAsistencia(asi);
                asi.setObservacion(asiw.getObservacion());
                this.asistenciaService.update(asi);
            }
        }
    }

    private void syncSeguimientos(GrupoFamiliar gf, GrupoFamiliarWeb gfw) throws GeneralAppException {
        Set<Seguimiento> segs = gf.getSeguimientos();
        // compruebo que existan

        if (CollectionUtils.isEmpty(gfw.getSeguimientos())) {
            return;
        }
        for (SeguimientoWeb segw : gfw.getSeguimientos()) {
            UUID id = UUID.fromString(segw.getId());
            Optional<Seguimiento> segOp = segs.stream().filter(seguimiento -> {
                return seguimiento.getId().equals(id);
            }).findFirst();
            if (segOp.isPresent()) {
                // ya existe
                Seguimiento seg = segOp.get();
                seg.setUltimaFechaActualizacion(segw.getUltimaFechaActualizacion());
                seg.setFecha(segw.getFecha());
                seg.setComponente(this.catalogService.findById(segw.getComponenteId(), Componente.class));
                seg.setTipoSeguimiento(this.catalogService.findById(segw.getTipoSeguimientoId(), TipoSeguimiento.class));
                seg.setObservaciones(segw.getObservaciones());


                this.seguimientoService.update(seg);
            } else {
                // no existe creo
                Seguimiento seg = new Seguimiento();
                seg.setId(id);
                seg.setUltimaFechaActualizacion(segw.getUltimaFechaActualizacion());
                seg.setFecha(segw.getFecha());
                seg.setComponente(this.catalogService.findById(segw.getComponenteId(), Componente.class));
                seg.setTipoSeguimiento(this.catalogService.findById(segw.getTipoSeguimientoId(), TipoSeguimiento.class));
                seg.setObservaciones(segw.getObservaciones());
                gf.addSeguimiento(seg);
                this.seguimientoService.update(seg);
            }
        }
    }

    private void syncNecesidadesEspecificas(GrupoFamiliar gf, GrupoFamiliarWeb gfw) throws GeneralAppException {
        NecesidadEspecifica ne = gf.getNecesidadesEspecifica();
        NecesidadEspecificaWeb neew = gfw.getNecesidadesEspecifica();
        if(gfw.getNecesidadesEspecifica()==null){
           return;
        }
        UUID necesidadEspecificaId;
        boolean create = true;

        // todo no deberia pasar
        if (neew.getUltimaFechaActualizacion() == null) {
            neew.setUltimaFechaActualizacion(LocalDateTime.now());
        }
        if (ne == null && neew == null) {
            return;
        } else if (ne == null && neew != null) {
            ne = new NecesidadEspecifica();
            necesidadEspecificaId = UUID.fromString(neew.getId());
            ne.setId(necesidadEspecificaId);
            ne.setUltimaFechaActualizacion(neew.getUltimaFechaActualizacion());
            ne.setGrupoFamiliar(gf);

        } else {
            necesidadEspecificaId = ne.getId();
            create = false;
        }


        if (ne.getUltimaFechaActualizacion().isAfter(neew.getUltimaFechaActualizacion())) {
            // hay un conflicto de sincronización // no actualizo
            // todo
        } else {

            ne.setUltimaFechaActualizacion(neew.getUltimaFechaActualizacion());
            // necesidadEspecificaProteccion


            if (CollectionUtils.isEmpty(neew.getNecesidadesEspecificasProteccion())) {
                ne.removeNecesidadEspecificaProteccionAll();
            } else {
                for (NecesidadEspecificaProteccionWeb necesidadEspecificaProteccionWeb : neew.getNecesidadesEspecificasProteccion()) {
                    NecesidadEspecificaProteccion nep = this.catalogService.findById(necesidadEspecificaProteccionWeb.getNecesidadEspecificaProteccionId(), NecesidadEspecificaProteccion.class);
                    ne.addNecesidadEspecificaProteccion(nep, necesidadEspecificaProteccionWeb.getCantidad());
                }
            }

            if (CollectionUtils.isEmpty(neew.getNecesidadesEspecificasHabitabilidad())) {
                ne.removeNecesidadEspecificaHabitabilidadAll();
            } else {
                for (NecesidadEspecificaHabitabilidadWeb necesidadEspecificaHabitabilidadWeb : neew.getNecesidadesEspecificasHabitabilidad()) {
                    NecesidadEspecificaHabitabilidad neh = necesidadesEspecificasHabitabilidadDao.find(necesidadEspecificaHabitabilidadWeb.getNecesidadEspecificaHabitabilidadId());
                    ne.addNecesidadEspecificaHabitabilidad(neh, necesidadEspecificaHabitabilidadWeb.getCantidad());
                }
            }


            this.necesidadEspecificaService.update(ne);

        }


    }

    private void syncIntegracionesSocioEconomicas(GrupoFamiliar gf, GrupoFamiliarWeb gfw) throws GeneralAppException {
        Set<IntegracionSocioeconomica> ises = gf.getIntegracionesSocioEconomicas();
        // compruebo que existan
        if (CollectionUtils.isEmpty(gfw.getIntegracionesSocioEconomicas())) {
            return;
        }
        for (IntegracionSocioeconomicaWeb isew : gfw.getIntegracionesSocioEconomicas()) {
            UUID id = UUID.fromString(isew.getId());
            Optional<IntegracionSocioeconomica> iseOp = ises.stream().filter(integracionSocioeconomica -> {
                return integracionSocioeconomica.getId().equals(id);
            }).findFirst();
            if (iseOp.isPresent()) {
                // ya existe
                IntegracionSocioeconomica ise = iseOp.get();
                ise.setId(id);
                ise.setUltimaFechaActualizacion(isew.getUltimaFechaActualizacion());
                ise.setOrganizacionApoyo(this.catalogService.findById(isew.getOrganizacionApoyoId(), OrganizacionApoyo.class));
                ise.setObservaciones(isew.getObservaciones());
                ise.setTiposAyuda(new HashSet<>());
                if (CollectionUtils.isNotEmpty(isew.getTiposAyudaIds())) {
                    List<Long> tiposAyudaIds = new ArrayList<>(isew.getTiposAyudaIds());
                    List<TipoAyuda> tiposAyuda = this.catalogService.findByIds(tiposAyudaIds, TipoAyuda.class);
                    ise.getTiposAyuda().addAll(new HashSet<>(tiposAyuda));
                }
                this.integracionSocioeconomicaService.update(ise);
            } else {
                // no existe creo
                IntegracionSocioeconomica ise = new IntegracionSocioeconomica();
                ise.setId(id);
                ise.setUltimaFechaActualizacion(isew.getUltimaFechaActualizacion());
                ise.setOrganizacionApoyo(this.catalogService.findById(isew.getOrganizacionApoyoId(), OrganizacionApoyo.class));
                ise.setObservaciones(isew.getObservaciones());
                ise.setTiposAyuda(new HashSet<>());
                if (CollectionUtils.isNotEmpty(isew.getTiposAyudaIds())) {
                    List<Long> tiposAyudaIds = new ArrayList<>(isew.getTiposAyudaIds());
                    List<TipoAyuda> tiposAyuda = this.catalogService.findByIds(tiposAyudaIds, TipoAyuda.class);
                    ise.getTiposAyuda().addAll(new HashSet<>(tiposAyuda));
                }
                gf.addIntegracionSocioeconomica(ise);
                this.integracionSocioeconomicaService.update(ise);
            }
        }

    }

    private void syncBeneficiarios(GrupoFamiliar gf, GrupoFamiliarWeb gfw) throws GeneralAppException {

        Set<Beneficiario> bens = gf.getBeneficiarios();
        // compruebo que existan
        if (CollectionUtils.isEmpty(gfw.getBeneficiarios())) {
            return;
        }

        for (BeneficiarioWeb benw : gfw.getBeneficiarios()) {
            UUID id = UUID.fromString(benw.getId());
            Optional<Beneficiario> benOp = bens.stream().filter(beneficiario -> {
                return beneficiario.getId().equals(id);
            }).findFirst();
            if (benOp.isPresent()) {
                // ya existe
                Beneficiario ben = benOp.get();
                // hay actualizacion
                // TODO ESTO NO DEBERÍA HABER
                if (benw.getUltimaFechaActualizacion() == null) {
                    benw.setUltimaFechaActualizacion(LocalDateTime.now());
                } else {
                    benw.setUltimaFechaActualizacion(benw.getUltimaFechaActualizacion()); //TODO:
                }

                    // actualizo
                    ben.setUltimaFechaActualizacion(benw.getUltimaFechaActualizacion());
                    ben.setNombres(benw.getNombres());
                    ben.setNivelEscolaridad(this.catalogService.findById(benw.getNivelEscolaridadId(), NivelEscolaridad.class));
                    ben.setSexo(this.catalogService.findById(benw.getSexoId(), Sexo.class));
                    ben.setFechaNacimiento(benw.getFechaNacimiento());
                    ben.setGenero(this.catalogService.findById(benw.getGeneroId(), Genero.class));
                    ben.setLgbti(this.catalogService.findById(benw.getLgbtiId(), Lgbti.class));
                    ben.setEtnia(this.catalogService.findById(benw.getEtniaId(), Etnia.class));
                    ben.setNacionalidad(this.catalogService.findById(benw.getNacionalidadId(), Nacionalidad.class));
                    ben.setDiscapacidad(this.catalogService.findById(benw.getDiscapacidadId(), Discapacidad.class));
                    ben.setFechaIngresoPais(benw.getFechaIngresoPais());
                    ben.setTipoDocumento(this.catalogService.findById(benw.getTipoDocumentoId(), TipoDocumento.class));
                    ben.setNumeroDocumento(benw.getNumeroDocumento());
                    ben.setEstatutoMigratorio(this.catalogService.findById(benw.getEstatutoMigratorioId(), EstatutoMigratorio.class));
                    ben.setSituacionMigratoria(this.catalogService.findById(benw.getSituacionMigratoriaId(), SituacionMigratoria.class));
                    ben.setMotivoSalidaPais(this.catalogService.findById(benw.getMotivoSalidaPaisId(), MotivoSalidaPais.class));
                    ben.setCiudad(this.catalogService.findById(benw.getCiudadId(), Ciudad.class));
                    ben.setSector(benw.getSector());
                    ben.setDireccion(benw.getDireccion());
                    ben.setTelefono(benw.getTelefono());
                    ben.setNivelEscolaridad(this.catalogService.findById(benw.getNivelEscolaridadId(), NivelEscolaridad.class));
                    ben.setNivelParentesco(this.catalogService.findById(benw.getNivelParentescoId(), NivelParentesco.class));
                    ben.setProfesiones(new HashSet<>());
                    if (CollectionUtils.isNotEmpty(benw.getProfesionesIds())) {
                        List<Long> profIds = new ArrayList<>(benw.getProfesionesIds());
                        List<Profesion> profs = this.catalogService.findByIds(profIds, Profesion.class);
                        ben.getProfesiones().addAll(new HashSet<>(profs));
                    }
                    ben.setOficios(new HashSet<>());
                    if (CollectionUtils.isNotEmpty(benw.getOficiosIds())) {
                        List<Long> ofIds = new ArrayList<>(benw.getOficiosIds());
                        List<Oficio> ofs = this.catalogService.findByIds(ofIds, Oficio.class);
                        ben.getOficios().addAll(new HashSet<>(ofs));
                    }
                    ben.setObservaciones(benw.getObservaciones());
                    ben.setUltimaFechaActualizacion(benw.ultimaFechaActualizacion);
                    gf.addBeneficiario(ben);

                    this.beneficiarioService.update(ben);

            } else {
                // no existe creo
                Beneficiario ben = new Beneficiario();

                // actualizo
                ben.setId(UUID.fromString(benw.getId()));
                ben.setUltimaFechaActualizacion(benw.getUltimaFechaActualizacion());
                ben.setNombres(benw.getNombres());
                ben.setNivelEscolaridad(this.catalogService.findById(benw.getNivelEscolaridadId(), NivelEscolaridad.class));
                ben.setSexo(this.catalogService.findById(benw.getSexoId(), Sexo.class));
                ben.setFechaNacimiento(benw.getFechaNacimiento());
                ben.setGenero(this.catalogService.findById(benw.getGeneroId(), Genero.class));
                ben.setLgbti(this.catalogService.findById(benw.getLgbtiId(), Lgbti.class));
                ben.setEtnia(this.catalogService.findById(benw.getEtniaId(), Etnia.class));
                ben.setNacionalidad(this.catalogService.findById(benw.getNacionalidadId(), Nacionalidad.class));
                ben.setDiscapacidad(this.catalogService.findById(benw.getDiscapacidadId(), Discapacidad.class));
                ben.setFechaIngresoPais(benw.getFechaIngresoPais());
                ben.setTipoDocumento(this.catalogService.findById(benw.getTipoDocumentoId(), TipoDocumento.class));
                ben.setNumeroDocumento(benw.getNumeroDocumento());
                ben.setEstatutoMigratorio(this.catalogService.findById(benw.getEstatutoMigratorioId(), EstatutoMigratorio.class));
                ben.setSituacionMigratoria(this.catalogService.findById(benw.getSituacionMigratoriaId(), SituacionMigratoria.class));
                ben.setMotivoSalidaPais(this.catalogService.findById(benw.getMotivoSalidaPaisId(), MotivoSalidaPais.class));
                ben.setCiudad(this.catalogService.findById(benw.getCiudadId(), Ciudad.class));
                ben.setSector(benw.getSector());
                ben.setDireccion(benw.getDireccion());
                ben.setTelefono(benw.getTelefono());
                ben.setNivelEscolaridad(this.catalogService.findById(benw.getNivelEscolaridadId(), NivelEscolaridad.class));
                ben.setNivelParentesco(this.catalogService.findById(benw.getNivelParentescoId(), NivelParentesco.class));
                ben.setProfesiones(new HashSet<>());
                if (CollectionUtils.isNotEmpty(benw.getProfesionesIds())) {
                    List<Long> profIds = new ArrayList<>(benw.getProfesionesIds());
                    List<Profesion> profs = this.catalogService.findByIds(profIds, Profesion.class);
                    ben.getProfesiones().addAll(new HashSet<>(profs));
                }
                ben.setOficios(new HashSet<>());
                if (CollectionUtils.isNotEmpty(benw.getOficiosIds())) {
                    List<Long> ofIds = new ArrayList<>(benw.getOficiosIds());
                    List<Oficio> ofs = this.catalogService.findByIds(ofIds, Oficio.class);
                    ben.getOficios().addAll(new HashSet<>(ofs));
                }
                ben.setObservaciones(benw.getObservaciones());
                ben.setUltimaFechaActualizacion(benw.ultimaFechaActualizacion);
                gf.addBeneficiario(ben);

                this.beneficiarioService.update(ben);

            }
        }


    }

    private GrupoFamiliar syncGrupoFamiliar(GrupoFamiliarWeb gfw) throws GeneralAppException {
        GrupoFamiliar gf;
        UUID id = UUID.fromString(gfw.getId());

        if (id == null) {
            throw new GeneralAppException("Id del grupo familiar no especificado", Response.Status.BAD_REQUEST.getStatusCode());
        }
        gf = this.grupoFamiliarService.findByIdWithData(id);
        // TODO ESTO NO DEBERÍA HABER

        gfw.setUltimaFechaActualizacion(LocalDateTime.now());

        if (gf == null) {
            gf = new GrupoFamiliar();
            gf.setId(id);
            gf.setMontoIngresosMensual(gfw.getMontoIngresosMensual());
            gf.setUltimaFechaActualizacion(gfw.getUltimaFechaActualizacion());
        } else {
            // hay actualizacion

            if (gf.getUltimaFechaActualizacion().isAfter(gfw.getUltimaFechaActualizacion())) {
                // hay un conflicto de sincronización // no actualizo

            } else {
                // actualizo
                gf.setUltimaFechaActualizacion(gfw.getUltimaFechaActualizacion());
                gf.setMontoIngresosMensual(gfw.getMontoIngresosMensual());
            }

        }
        this.grupoFamiliarService.saveOrUpdate(gf);
        return gf;

    }


}
