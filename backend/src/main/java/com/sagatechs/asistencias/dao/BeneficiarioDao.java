package com.sagatechs.asistencias.dao;

import com.sagatechs.asistencias.model.Beneficiario;
import com.sagatechs.asistencias.model.GrupoFamiliar;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class BeneficiarioDao extends GenericDaoJpa<Beneficiario, UUID> {
    private final static Logger LOGGER = Logger.getLogger(BeneficiarioDao.class);


    public BeneficiarioDao() {
        super(Beneficiario.class, UUID.class);
    }
}
