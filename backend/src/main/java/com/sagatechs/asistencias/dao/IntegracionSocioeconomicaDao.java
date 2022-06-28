package com.sagatechs.asistencias.dao;

import com.sagatechs.asistencias.model.IntegracionSocioeconomica;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class IntegracionSocioeconomicaDao extends GenericDaoJpa<IntegracionSocioeconomica, UUID> {
    private final static Logger LOGGER = Logger.getLogger(IntegracionSocioeconomicaDao.class);


    public IntegracionSocioeconomicaDao() {
        super(IntegracionSocioeconomica.class, UUID.class);
    }
}
