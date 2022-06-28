package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.MotivoReferencia;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class MotivoReferenciaDao extends GenericCatalogDao<MotivoReferencia> {

    private final static Logger LOGGER = Logger.getLogger(MotivoReferenciaDao.class);

    public MotivoReferenciaDao() {
        super(MotivoReferencia.class);
    }
}
