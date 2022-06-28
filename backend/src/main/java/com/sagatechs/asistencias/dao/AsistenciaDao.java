package com.sagatechs.asistencias.dao;

import com.sagatechs.asistencias.model.Asistencia;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class AsistenciaDao extends GenericDaoJpa<Asistencia, UUID> {
    private final static Logger LOGGER = Logger.getLogger(AsistenciaDao.class);


    public AsistenciaDao() {
        super(Asistencia.class, UUID.class);
    }
}
