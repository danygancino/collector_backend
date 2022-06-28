package com.sagatechs.asistencias.services;

import com.sagatechs.asistencias.dao.NecesidadEspecificaNecesidadHabitabilidadDao;
import com.sagatechs.asistencias.model.NecesidadEspecificaNecesidadHabitabilidad;
import com.sagatechs.asistencias.model.NecesidadEspecificaNecesidadProteccion;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class NecesidadEspecificaNecesidadHabitabilidadService {

    private final static Logger LOGGER = Logger.getLogger(NecesidadEspecificaNecesidadHabitabilidadService.class);

    @Inject
    private NecesidadEspecificaNecesidadHabitabilidadDao necesidadEspecificaNecesidadnecesidadEspecificaNecesidadHabitabilidadDao;

    public NecesidadEspecificaNecesidadHabitabilidad save(NecesidadEspecificaNecesidadHabitabilidad necesidadEspecifica) {
        return this.necesidadEspecificaNecesidadnecesidadEspecificaNecesidadHabitabilidadDao.save(necesidadEspecifica);
    }

    public NecesidadEspecificaNecesidadHabitabilidad update(NecesidadEspecificaNecesidadHabitabilidad necesidadEspecifica) {
        return this.necesidadEspecificaNecesidadnecesidadEspecificaNecesidadHabitabilidadDao.update(necesidadEspecifica);
    }

}
