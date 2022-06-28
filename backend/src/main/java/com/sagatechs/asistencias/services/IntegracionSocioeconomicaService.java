package com.sagatechs.asistencias.services;

import com.sagatechs.asistencias.dao.IntegracionSocioeconomicaDao;
import com.sagatechs.asistencias.model.Beneficiario;
import com.sagatechs.asistencias.model.IntegracionSocioeconomica;
import com.sagatechs.asistencias.webServices.modelWeb.IntegracionSocioeconomicaWeb;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
public class IntegracionSocioeconomicaService {

    private final static Logger LOGGER = Logger.getLogger(IntegracionSocioeconomicaService.class);

    @Inject
    private IntegracionSocioeconomicaDao integracionSocioeconomicaDao;

    public IntegracionSocioeconomica save(IntegracionSocioeconomica integracionSocioeconomica) {
        return this.integracionSocioeconomicaDao.save(integracionSocioeconomica);
    }

    public IntegracionSocioeconomica update(IntegracionSocioeconomica integracionSocioeconomica) {
        return this.integracionSocioeconomicaDao.update(integracionSocioeconomica);
    }

    public List<IntegracionSocioeconomicaWeb> integracionesSocioEconomicasToIntegracionesSocioEconomicasWeb(Set<IntegracionSocioeconomica> integracionSocioeconomicas) {
        List<IntegracionSocioeconomicaWeb> r = new ArrayList<>();
        for (IntegracionSocioeconomica integracionSocioeconomica : integracionSocioeconomicas) {
            r.add(this.integracionSocioEconomicaToIntegracionSocioEconomicaWeb(integracionSocioeconomica));
        }
        return r;
    }

    private IntegracionSocioeconomicaWeb integracionSocioEconomicaToIntegracionSocioEconomicaWeb(IntegracionSocioeconomica ise) {
        if (ise == null) {
            return null;
        }
        IntegracionSocioeconomicaWeb isew = new IntegracionSocioeconomicaWeb();
        isew.setId(ise.getId().toString());
        isew.setUltimaFechaActualizacion(ise.getUltimaFechaActualizacion());
        isew.setOrganizacionApoyoId(ise.getOrganizacionApoyo() != null ? ise.getOrganizacionApoyo().getId() : null);
        isew.setTiposAyudaIds(ise.getTiposAyuda().stream().map(tipoAyuda -> tipoAyuda.getId()).collect(Collectors.toSet()));
        isew.setObservaciones(ise.getObservaciones());
        return isew;
    }
}
