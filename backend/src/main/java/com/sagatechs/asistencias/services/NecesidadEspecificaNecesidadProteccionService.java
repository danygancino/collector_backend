package com.sagatechs.asistencias.services;

import com.sagatechs.asistencias.dao.NecesidadEspecificaDao;
import com.sagatechs.asistencias.dao.NecesidadEspecificaNecesidadProteccionDao;
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
public class NecesidadEspecificaNecesidadProteccionService {

    private final static Logger LOGGER = Logger.getLogger(NecesidadEspecificaNecesidadProteccionService.class);

    @Inject
    private NecesidadEspecificaNecesidadProteccionDao necesidadEspecificaNecesidadProteccionDaon;

    public NecesidadEspecificaNecesidadProteccion save(NecesidadEspecificaNecesidadProteccion necesidadEspecifica) {
        return this.necesidadEspecificaNecesidadProteccionDaon.save(necesidadEspecifica);
    }

    public NecesidadEspecificaNecesidadProteccion update(NecesidadEspecificaNecesidadProteccion necesidadEspecifica) {
        return this.necesidadEspecificaNecesidadProteccionDaon.update(necesidadEspecifica);
    }

}
