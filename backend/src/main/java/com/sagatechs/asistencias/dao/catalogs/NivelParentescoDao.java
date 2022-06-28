package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.NivelParentesco;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class NivelParentescoDao extends GenericCatalogDao<NivelParentesco> {

    private final static Logger LOGGER = Logger.getLogger(NivelParentescoDao.class);
    public NivelParentescoDao() {
        super(NivelParentesco.class);
    }

}
