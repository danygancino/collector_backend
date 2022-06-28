package com.sagatechs.asistencias.dao;

import com.sagatechs.asistencias.model.Referencia;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class ReferenciaDao extends GenericDaoJpa<Referencia, UUID> {
    private final static Logger LOGGER = Logger.getLogger(ReferenciaDao.class);


    public ReferenciaDao() {
        super(Referencia.class, UUID.class);
    }
}
