package com.sagatechs.asistencias.services;

import com.sagatechs.asistencias.dao.GrupoFamiliarDao;
import com.sagatechs.asistencias.dao.catalogs.*;
import com.sagatechs.asistencias.model.GrupoFamiliar;
import com.sagatechs.asistencias.model.catalogs.*;
import com.sagatechs.asistencias.webServices.modelWeb.*;
import com.sagatechs.generics.persistence.model.State;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Stateless
public class GrupoFamiliarService {

    private final static Logger LOGGER = Logger.getLogger(GrupoFamiliarService.class);

    @Inject
    private GrupoFamiliarDao grupoFamiliarDao;

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

    private final static int monthsBefore = 12;

    public GrupoFamiliar saveOrUpdate(GrupoFamiliar grupoFamiliar) {
        if (grupoFamiliar.getGrupoFamiliarId() == null) {
            this.grupoFamiliarDao.save(grupoFamiliar);
        } else {
        this.grupoFamiliarDao.update(grupoFamiliar);
        }
        return grupoFamiliar;
    }

    public List<GrupoFamiliarWeb> findAfterSyncDate(LocalDateTime syncDate) {
        if (syncDate == null) {
            syncDate = LocalDateTime.now().minusMonths(monthsBefore);
        }

        return this.gruposFamiliaresToGruposFamiliaresWeb(this.grupoFamiliarDao.findByIdWithDataAfterSyncDate(syncDate));
    }

    public GrupoFamiliar findById(UUID id) {
        return this.grupoFamiliarDao.find(id);
    }

    public List<GrupoFamiliarWeb> findByAllWithData() {
        LocalDateTime syncDate = LocalDateTime.of(2020, 1,1,12,0);
        return this.gruposFamiliaresToGruposFamiliaresWeb(this.grupoFamiliarDao.findByIdWithDataAfterSyncDate(syncDate));
    }

    public GrupoFamiliar findByIdWithData(UUID id) {
        return this.grupoFamiliarDao.findByIdWithData(id);
    }

    public List<GrupoFamiliarWeb> gruposFamiliaresToGruposFamiliaresWeb(List<GrupoFamiliar> gfs) {
        List<GrupoFamiliarWeb> r = new ArrayList<>();
        for (GrupoFamiliar gf : gfs) {
            r.add(this.grupoFamiliarToGrupoFamiliarWeb(gf));
        }
        return r;
    }

    public GrupoFamiliarWeb grupoFamiliarToGrupoFamiliarWeb(GrupoFamiliar gf) {
        if (gf == null) {
            return null;
        }
        GrupoFamiliarWeb gfw = new GrupoFamiliarWeb();
        gfw.setId(gf.getId().toString());
        gfw.setGrupoFamiliarId(gf.getGrupoFamiliarId());
        gfw.setUltimaFechaActualizacion(gf.getUltimaFechaActualizacion());
        gfw.setMontoIngresosMensual(gf.getMontoIngresosMensual());
        // beneficiarios
        gfw.setBeneficiarios(this.beneficiarioService.beneficiariosToBeneficiariosWeb(gf.getBeneficiarios()));
        // IntegracionSocioeconomica
        gfw.setIntegracionesSocioEconomicas(this.integracionSocioeconomicaService.integracionesSocioEconomicasToIntegracionesSocioEconomicasWeb(gf.getIntegracionesSocioEconomicas()));
        // NecesidadEspecifica
        gfw.setNecesidadesEspecifica(this.necesidadEspecificaService.necesidadEspecificaToNecesidadEspecificaWeb(gf.getNecesidadesEspecifica()));
        // Seguimiento
        gfw.setSeguimientos(this.seguimientoService.seguimientosToSeguimientosWeb(gf.getSeguimientos()));
        // Asistencia
        gfw.setAsistencias(this.asistenciaService.asistenciasToAsistenciasWeb(gf.getAsistencias()));
        // Referencia
        gfw.setReferencias(this.referenciaService.referenciasToReferenciasWeb(gf.getReferencias()));

        return gfw;
    }
}
