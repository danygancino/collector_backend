package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.MotivoSalidaPais;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class MotivoSalidaPaisDao extends GenericCatalogDao<MotivoSalidaPais> {

    private final static Logger LOGGER = Logger.getLogger(MotivoSalidaPaisDao.class);

    public MotivoSalidaPaisDao() {
        super(MotivoSalidaPais.class);
    }
}
