package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.TipoAsistencia;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class TipoAsistenciaDao extends GenericCatalogDao<TipoAsistencia> {

    private final static Logger LOGGER = Logger.getLogger(TipoAsistenciaDao.class);

    public TipoAsistenciaDao() {
        super(TipoAsistencia.class);
    }
}
