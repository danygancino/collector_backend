package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.SituacionMigratoria;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class SituacionMigratoriaDao extends GenericCatalogDao<SituacionMigratoria> {

    private final static Logger LOGGER = Logger.getLogger(SituacionMigratoriaDao.class);

    public SituacionMigratoriaDao() {
        super(SituacionMigratoria.class);
    }
}
