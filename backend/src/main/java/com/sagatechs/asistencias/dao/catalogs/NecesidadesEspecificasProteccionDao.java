package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.NecesidadEspecificaProteccion;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class NecesidadesEspecificasProteccionDao extends GenericCatalogDao<NecesidadEspecificaProteccion> {

    private final static Logger LOGGER = Logger.getLogger(NecesidadesEspecificasProteccionDao.class);

    public NecesidadesEspecificasProteccionDao() {
        super(NecesidadEspecificaProteccion.class);
    }
}
