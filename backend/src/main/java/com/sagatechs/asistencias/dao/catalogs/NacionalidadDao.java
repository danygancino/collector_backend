package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.Nacionalidad;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class NacionalidadDao extends GenericCatalogDao<Nacionalidad> {

    private final static Logger LOGGER = Logger.getLogger(NacionalidadDao.class);

    public NacionalidadDao() {
        super(Nacionalidad.class);
    }
}
