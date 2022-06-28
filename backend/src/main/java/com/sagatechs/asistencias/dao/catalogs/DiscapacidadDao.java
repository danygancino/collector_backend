package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.Discapacidad;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class DiscapacidadDao extends GenericCatalogDao<Discapacidad> {

    private final static Logger LOGGER = Logger.getLogger(DiscapacidadDao.class);

    public DiscapacidadDao() {
        super(Discapacidad.class);
    }
}
