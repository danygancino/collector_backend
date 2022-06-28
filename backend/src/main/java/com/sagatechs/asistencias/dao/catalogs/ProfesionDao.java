package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.Profesion;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class ProfesionDao extends GenericCatalogDao<Profesion> {

    private final static Logger LOGGER = Logger.getLogger(ProfesionDao.class);

    public ProfesionDao() {
        super(Profesion.class);
    }
}
