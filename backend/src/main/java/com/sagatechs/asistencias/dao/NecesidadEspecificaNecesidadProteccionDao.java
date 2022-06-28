package com.sagatechs.asistencias.dao;

import com.sagatechs.asistencias.model.NecesidadEspecifica;
import com.sagatechs.asistencias.model.NecesidadEspecificaNecesidadProteccion;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class NecesidadEspecificaNecesidadProteccionDao extends GenericDaoJpa<NecesidadEspecificaNecesidadProteccion, UUID> {
    private final static Logger LOGGER = Logger.getLogger(NecesidadEspecificaNecesidadProteccionDao.class);


    public NecesidadEspecificaNecesidadProteccionDao() {
        super(NecesidadEspecificaNecesidadProteccion.class, UUID.class);
    }
}
