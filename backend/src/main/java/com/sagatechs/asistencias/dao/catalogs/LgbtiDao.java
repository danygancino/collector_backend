package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.Lgbti;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class LgbtiDao extends GenericCatalogDao<Lgbti> {

    private final static Logger LOGGER = Logger.getLogger(LgbtiDao.class);

    public LgbtiDao() {
        super(Lgbti.class);
    }
}
