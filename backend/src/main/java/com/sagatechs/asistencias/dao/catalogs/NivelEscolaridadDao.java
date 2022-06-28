package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.NivelEscolaridad;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class NivelEscolaridadDao extends GenericCatalogDao<NivelEscolaridad> {

    private final static Logger LOGGER = Logger.getLogger(NivelEscolaridadDao.class);

    public NivelEscolaridadDao() {
        super(NivelEscolaridad.class);
    }
}
