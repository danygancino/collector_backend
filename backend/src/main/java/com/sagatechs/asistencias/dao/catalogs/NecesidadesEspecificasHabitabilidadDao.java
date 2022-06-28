package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.GrupoFamiliar;
import com.sagatechs.asistencias.model.NecesidadEspecifica;
import com.sagatechs.asistencias.model.catalogs.NecesidadEspecificaHabitabilidad;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class NecesidadesEspecificasHabitabilidadDao extends GenericCatalogDao<NecesidadEspecificaHabitabilidad> {

    private final static Logger LOGGER = Logger.getLogger(NecesidadesEspecificasHabitabilidadDao.class);

    public NecesidadesEspecificasHabitabilidadDao() {
        super(NecesidadEspecificaHabitabilidad.class);
    }

    @Override
    public NecesidadEspecificaHabitabilidad find(Long id){
        String jpql = "SELECT distinct o FROM NecesidadEspecificaHabitabilidad  o " +
                "WHERE " +
                " o.id = :id ";
        Query q = getEntityManager().createQuery(jpql, NecesidadEspecificaHabitabilidad.class);
        q.setParameter("id", id);
        return (NecesidadEspecificaHabitabilidad) q.getSingleResult();
    }
}
