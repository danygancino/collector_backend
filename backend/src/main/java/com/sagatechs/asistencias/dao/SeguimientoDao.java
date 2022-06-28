package com.sagatechs.asistencias.dao;

import com.sagatechs.asistencias.model.Seguimiento;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class SeguimientoDao extends GenericDaoJpa<Seguimiento, UUID> {
    private final static Logger LOGGER = Logger.getLogger(SeguimientoDao.class);


    public SeguimientoDao() {
        super(Seguimiento.class, UUID.class);
    }
}
