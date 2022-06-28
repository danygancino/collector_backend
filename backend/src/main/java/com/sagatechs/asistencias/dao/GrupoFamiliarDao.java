package com.sagatechs.asistencias.dao;

import com.sagatechs.asistencias.model.GrupoFamiliar;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Stateless
public class GrupoFamiliarDao extends GenericDaoJpa<GrupoFamiliar, UUID> {
    private final static Logger LOGGER = Logger.getLogger(GrupoFamiliarDao.class);


    public GrupoFamiliarDao() {
        super(GrupoFamiliar.class, UUID.class);
    }

    @Override
    public GrupoFamiliar save(GrupoFamiliar grupoFamiliar) {
        if (grupoFamiliar.getGrupoFamiliarId() == null) {
            grupoFamiliar.setGrupoFamiliarId(this.getNextGrupoFamiliarId());
        }
        return super.update(grupoFamiliar);
    }

    private String getNextGrupoFamiliarId() {
        BigInteger sequence = this.getNextGrupoFamiliarSequence();
        String id = "GF" + StringUtils.leftPad(sequence.toString(), 6, "0");
        return id;
    }

    private BigInteger getNextGrupoFamiliarSequence() {
        String sql = "SELECT nextval('asistencias.grupo_familiar_codigo_seq') ";
        Query q = this.entityManager.createNativeQuery(sql);
        return (BigInteger) q.getSingleResult();
    }

    public GrupoFamiliar findByIdWithData(UUID id) {
        String jpql = "SELECT o FROM GrupoFamiliar  o " +
                " left join o.beneficiarios ben " +
                " left join ben.profesiones " +
                " left join ben.oficios " +
                " left join o.integracionesSocioEconomicas inse " +
                " left join inse.tiposAyuda " +
                " left join o.necesidadesEspecifica ne " +
                " left join ne.necesidadEspecificaNecesidadHabitabilidades " +
                " left join ne.necesidadEspecificaNecesidadProtecciones " +
                " left join o.seguimientos seg " +
                " left join o.asistencias asi " +
                " left join o.referencias ref " +
                " left join ref.motivosReferencia " +
                "WHERE o.id = :id";
        Query q = getEntityManager().createQuery(jpql, GrupoFamiliar.class);
        q.setParameter("id", id);
        try {
            return (GrupoFamiliar) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<GrupoFamiliar> findByIdWithDataAfterSyncDate(LocalDateTime syncDate) {
        String jpql = "SELECT distinct o FROM GrupoFamiliar  o " +
                " left join o.beneficiarios ben " +
                " left join ben.profesiones " +
                " left join ben.oficios " +
                " left join o.integracionesSocioEconomicas inse " +
                " left join inse.tiposAyuda " +
                " left join o.necesidadesEspecifica ne " +
                " left join ne.necesidadEspecificaNecesidadProtecciones " +
                " left join ne.necesidadEspecificaNecesidadHabitabilidades " +
                " left join o.seguimientos seg " +
                " left join o.asistencias asi " +
                " left join o.referencias ref " +
                " left join ref.motivosReferencia " +
                "WHERE " +
                " o.ultimaFechaActualizacion > :syncDate ";
        Query q = getEntityManager().createQuery(jpql, GrupoFamiliar.class);
        q.setParameter("syncDate", syncDate);
        return q.getResultList();
    }
}
