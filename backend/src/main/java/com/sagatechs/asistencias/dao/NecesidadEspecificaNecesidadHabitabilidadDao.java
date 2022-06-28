package com.sagatechs.asistencias.dao;

import com.sagatechs.asistencias.model.NecesidadEspecificaNecesidadHabitabilidad;
import com.sagatechs.asistencias.model.NecesidadEspecificaNecesidadProteccion;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class NecesidadEspecificaNecesidadHabitabilidadDao extends GenericDaoJpa<NecesidadEspecificaNecesidadHabitabilidad, UUID> {
    private final static Logger LOGGER = Logger.getLogger(NecesidadEspecificaNecesidadHabitabilidadDao.class);


    public NecesidadEspecificaNecesidadHabitabilidadDao() {
        super(NecesidadEspecificaNecesidadHabitabilidad.class, UUID.class);
    }
}
