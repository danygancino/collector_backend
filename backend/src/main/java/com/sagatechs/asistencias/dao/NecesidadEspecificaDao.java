package com.sagatechs.asistencias.dao;

import com.sagatechs.asistencias.model.NecesidadEspecifica;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class NecesidadEspecificaDao extends GenericDaoJpa<NecesidadEspecifica, UUID> {
    private final static Logger LOGGER = Logger.getLogger(NecesidadEspecificaDao.class);


    public NecesidadEspecificaDao() {
        super(NecesidadEspecifica.class, UUID.class);
    }
}
