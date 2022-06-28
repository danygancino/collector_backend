package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.Etnia;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class EtniaDao extends GenericCatalogDao<Etnia> {

    private final static Logger LOGGER = Logger.getLogger(EtniaDao.class);

    public EtniaDao() {
        super(Etnia.class);
    }
}
