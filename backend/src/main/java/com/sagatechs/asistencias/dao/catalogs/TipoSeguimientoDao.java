package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.TipoSeguimiento;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class TipoSeguimientoDao extends GenericCatalogDao<TipoSeguimiento> {

    private final static Logger LOGGER = Logger.getLogger(TipoSeguimientoDao.class);

    public TipoSeguimientoDao() {
        super(TipoSeguimiento.class);
    }
}
