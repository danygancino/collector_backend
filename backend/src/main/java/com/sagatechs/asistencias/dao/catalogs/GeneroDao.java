package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.Genero;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class GeneroDao extends GenericCatalogDao<Genero> {

    private final static Logger LOGGER = Logger.getLogger(GeneroDao.class);

    public GeneroDao() {
        super(Genero.class);
    }
}
