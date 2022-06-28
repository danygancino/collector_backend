package com.sagatechs.asistencias.services;

import com.sagatechs.asistencias.dao.NecesidadEspecificaDao;
import com.sagatechs.asistencias.model.NecesidadEspecifica;
import com.sagatechs.asistencias.model.NecesidadEspecificaNecesidadHabitabilidad;
import com.sagatechs.asistencias.model.NecesidadEspecificaNecesidadProteccion;
import com.sagatechs.asistencias.webServices.modelWeb.NecesidadEspecificaHabitabilidadWeb;
import com.sagatechs.asistencias.webServices.modelWeb.NecesidadEspecificaProteccionWeb;
import com.sagatechs.asistencias.webServices.modelWeb.NecesidadEspecificaWeb;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Stateless
public class NecesidadEspecificaService {

    private final static Logger LOGGER = Logger.getLogger(NecesidadEspecificaService.class);

    @Inject
    private NecesidadEspecificaDao necesidadEspecificaDao;



    public NecesidadEspecifica save(NecesidadEspecifica necesidadEspecifica) {
        return this.necesidadEspecificaDao.save(necesidadEspecifica);
    }

    public NecesidadEspecifica update(NecesidadEspecifica necesidadEspecifica) {
        return this.necesidadEspecificaDao.update(necesidadEspecifica);
    }

    public List<NecesidadEspecificaWeb> necesidadEspecificasToNecesidadEspecificasWeb(Set<NecesidadEspecifica> necesidadesEspecificas) {
        List<NecesidadEspecificaWeb> r = new ArrayList<>();
        for (NecesidadEspecifica necesidadEspecifica : necesidadesEspecificas) {
            r.add(this.necesidadEspecificaToNecesidadEspecificaWeb(necesidadEspecifica));
        }

        return r;
    }

    public NecesidadEspecificaWeb necesidadEspecificaToNecesidadEspecificaWeb(NecesidadEspecifica ne) {
        if (ne == null) {
            return null;
        }

        NecesidadEspecificaWeb neew = new NecesidadEspecificaWeb();
        neew.setId(ne.getId().toString());
        for (NecesidadEspecificaNecesidadProteccion necesidadEspecificaNecesidadProteccione : ne.getNecesidadEspecificaNecesidadProtecciones()) {
            NecesidadEspecificaProteccionWeb nepw= new NecesidadEspecificaProteccionWeb();
            nepw.setNecesidadEspecificaProteccionId(necesidadEspecificaNecesidadProteccione.getNecesidadEspecificaProteccion().getId());
            nepw.setCantidad(necesidadEspecificaNecesidadProteccione.getCantidad());
            neew.addNecesidadEspecificaProteccion(nepw);
        }

        for (NecesidadEspecificaNecesidadHabitabilidad necesidadEnecesidadEspecificaNecesidadHabitabilidad : ne.getNecesidadEspecificaNecesidadHabitabilidades()) {
            NecesidadEspecificaHabitabilidadWeb nehw= new NecesidadEspecificaHabitabilidadWeb();
            nehw.setNecesidadEspecificaHabitabilidadId(necesidadEnecesidadEspecificaNecesidadHabitabilidad.getNecesidadEspecificaHabitabilidad().getId());
            nehw.setCantidad(necesidadEnecesidadEspecificaNecesidadHabitabilidad.getCantidad());
            neew.addNecesidadEspecificaHabitabilidad(nehw);
        }

        neew.setObservaciones(ne.getObservaciones());

        return neew;
    }
}
