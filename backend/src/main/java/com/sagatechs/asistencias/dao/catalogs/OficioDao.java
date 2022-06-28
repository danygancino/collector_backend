package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.Oficio;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class OficioDao extends GenericCatalogDao<Oficio> {

    private final static Logger LOGGER = Logger.getLogger(OficioDao.class);

    public OficioDao() {
        super(Oficio.class);
    }
}
