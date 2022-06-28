package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.Sexo;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class SexoDao extends GenericCatalogDao<Sexo> {

    private final static Logger LOGGER = Logger.getLogger(SexoDao.class);

    public SexoDao() {
        super(Sexo.class);
    }
}
