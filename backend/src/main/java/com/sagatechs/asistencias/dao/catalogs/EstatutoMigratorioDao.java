package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.EstatutoMigratorio;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class EstatutoMigratorioDao extends GenericCatalogDao<EstatutoMigratorio> {

    private final static Logger LOGGER = Logger.getLogger(EstatutoMigratorioDao.class);

    public EstatutoMigratorioDao() {
        super(EstatutoMigratorio.class);
    }
}
