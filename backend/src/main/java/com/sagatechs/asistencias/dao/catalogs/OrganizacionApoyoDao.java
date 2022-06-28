package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.OrganizacionApoyo;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class OrganizacionApoyoDao extends GenericCatalogDao<OrganizacionApoyo> {

    private final static Logger LOGGER = Logger.getLogger(OrganizacionApoyoDao.class);

    public OrganizacionApoyoDao() {
        super(OrganizacionApoyo.class);
    }
}
