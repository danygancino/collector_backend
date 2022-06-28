package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.TipoDocumento;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class TipoDocumentoDao extends GenericCatalogDao<TipoDocumento> {

    private final static Logger LOGGER = Logger.getLogger(TipoDocumentoDao.class);

    public TipoDocumentoDao() {
        super(TipoDocumento.class);
    }
}
