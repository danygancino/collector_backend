package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.OrganizacionReferencia;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class OrganizacionReferenciaDao extends GenericCatalogDao<OrganizacionReferencia> {

    private final static Logger LOGGER = Logger.getLogger(OrganizacionReferenciaDao.class);

    public OrganizacionReferenciaDao() {
        super(OrganizacionReferencia.class);
    }
}
