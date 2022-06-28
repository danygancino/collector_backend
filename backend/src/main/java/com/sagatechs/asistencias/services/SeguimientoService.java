package com.sagatechs.asistencias.services;

import com.sagatechs.asistencias.dao.SeguimientoDao;
import com.sagatechs.asistencias.model.NecesidadEspecifica;
import com.sagatechs.asistencias.model.Seguimiento;
import com.sagatechs.asistencias.webServices.modelWeb.SeguimientoWeb;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Stateless
public class SeguimientoService {

    private final static Logger LOGGER = Logger.getLogger(SeguimientoService.class);

    @Inject
    private SeguimientoDao seguimientoDao;

    public Seguimiento save(Seguimiento seguimiento) {
        return this.seguimientoDao.save(seguimiento);
    }

    public Seguimiento update(Seguimiento seguimiento) {
        return this.seguimientoDao.update(seguimiento);
    }

    public List<SeguimientoWeb> seguimientosToSeguimientosWeb(Set<Seguimiento> seguimientos) {
        List<SeguimientoWeb> r = new ArrayList<>();
        for (Seguimiento seguimiento : seguimientos) {
            r.add(this.seguimientoToSeguimientoWeb(seguimiento));
        }

        return r;
    }

    private SeguimientoWeb seguimientoToSeguimientoWeb(Seguimiento seg) {
        if (seg == null) {
            return null;
        }
        SeguimientoWeb segw = new SeguimientoWeb();
        segw.setId(seg.getId().toString());
        segw.setUltimaFechaActualizacion(seg.getUltimaFechaActualizacion());
        segw.setFecha(seg.getFecha());
        segw.setComponenteId(seg.getComponente() != null ? seg.getComponente().getId() : null);
        segw.setTipoSeguimientoId(seg.getTipoSeguimiento() != null ? seg.getTipoSeguimiento().getId() : null);
        segw.setObservaciones(seg.getObservaciones());
        return segw;
    }
}
