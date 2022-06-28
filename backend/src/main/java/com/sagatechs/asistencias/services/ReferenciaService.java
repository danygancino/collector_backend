package com.sagatechs.asistencias.services;

import com.sagatechs.asistencias.dao.ReferenciaDao;
import com.sagatechs.asistencias.model.Referencia;
import com.sagatechs.asistencias.model.Seguimiento;
import com.sagatechs.asistencias.webServices.modelWeb.ReferenciaWeb;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
public class ReferenciaService {

    private final static Logger LOGGER = Logger.getLogger(ReferenciaService.class);

    @Inject
    private ReferenciaDao referenciaDao;

    public Referencia save(Referencia referencia) {
        return this.referenciaDao.save(referencia);
    }

    public Referencia update(Referencia referencia) {
        return this.referenciaDao.update(referencia);
    }

    public List<ReferenciaWeb> referenciasToReferenciasWeb(Set<Referencia> referencias) {
        List<ReferenciaWeb> r = new ArrayList<>();
        for (Referencia referencia : referencias) {
            r.add(this.referenciaToReferenciaWeb(referencia));
        }

        return r;
    }

    private ReferenciaWeb referenciaToReferenciaWeb(Referencia r) {
        if (r == null) {
            return null;
        }
        ReferenciaWeb rw = new ReferenciaWeb();
        rw.setId(r.getId().toString());
        rw.setFecha(r.getFecha());
        rw.setUltimaFechaActualizacion(r.getUltimaFechaActualizacion());
        rw.setOrganizacionesReferenciaIds(r.getOrganizacionReferencia() != null ? r.getOrganizacionReferencia().getId() : null);
        rw.setMotivosReferenciaIds(r.getMotivosReferencia().stream().map(motivoReferencia -> motivoReferencia.getId()).collect(Collectors.toSet()));
        rw.setObservacion(r.getObservacion());
        return rw;
    }
}
