package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.TipoAyuda;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class TipoAyudaDao extends GenericCatalogDao<TipoAyuda> {

    private final static Logger LOGGER = Logger.getLogger(TipoAyudaDao.class);

    public TipoAyudaDao() {
        super(TipoAyuda.class);
    }
}
