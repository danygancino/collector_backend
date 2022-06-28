package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.CatalogoBase;
import com.sagatechs.asistencias.model.catalogs.Ciudad;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class CiudadDao extends GenericCatalogDao<Ciudad> {

    private final static Logger LOGGER = Logger.getLogger(CiudadDao.class);


    public CiudadDao() {
        super(Ciudad.class);
    }
}
