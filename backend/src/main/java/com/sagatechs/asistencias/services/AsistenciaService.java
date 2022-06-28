package com.sagatechs.asistencias.services;

import com.sagatechs.asistencias.dao.AsistenciaDao;
import com.sagatechs.asistencias.model.Asistencia;
import com.sagatechs.asistencias.model.Seguimiento;
import com.sagatechs.asistencias.webServices.modelWeb.AsistenciaWeb;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Stateless
public class AsistenciaService {

    private final static Logger LOGGER = Logger.getLogger(AsistenciaService.class);

    @Inject
    private AsistenciaDao asistenciaDao;

    public Asistencia save(Asistencia asistencia) {
        return this.asistenciaDao.save(asistencia);
    }

    public Asistencia update(Asistencia asistencia) {
        return this.asistenciaDao.update(asistencia);
    }

    public List<AsistenciaWeb> asistenciasToAsistenciasWeb(Set<Asistencia> asistencias) {
        List<AsistenciaWeb> r = new ArrayList<>();
        for (Asistencia asistencia : asistencias) {
            r.add(this.asistenciaToAsistenciaWeb(asistencia));
        }
        return r;
    }

    private AsistenciaWeb asistenciaToAsistenciaWeb(Asistencia a) {
        if (a == null) {
            return null;
        }
        AsistenciaWeb aw = new AsistenciaWeb();
        aw.setId(a.getId().toString());
        aw.setUltimaFechaActualizacion(a.getUltimaFechaActualizacion());
        aw.setFecha(a.getFecha());
        aw.setComponenteId(a.getComponente() != null ? a.getComponente().getId() : null);
        aw.setTipoAsistenciaId(a.getTipoAsistencia() != null ? a.getTipoAsistencia().getId() : null);
        aw.setNombrePropietarioVivienda(a.getNombrePropietarioVivienda());
        aw.setNumeroDocumentoPropietarioVivienda(a.getNumeroDocumentoPropietarioVivienda());
        aw.setTelefonoPropietarioVivienda(a.getTelefonoPropietarioVivienda());
        aw.setNumeroEspacioHabitables(a.getNumeroEspacioHabitables());
        aw.setObservacion(a.getObservacion());
        return aw;
    }
}
