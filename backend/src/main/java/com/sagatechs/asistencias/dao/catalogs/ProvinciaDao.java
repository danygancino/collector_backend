package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.Provincia;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class ProvinciaDao extends GenericCatalogDao<Provincia> {

    private final static Logger LOGGER = Logger.getLogger(ProvinciaDao.class);

    public ProvinciaDao() {
        super(Provincia.class);
    }
}
