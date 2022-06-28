package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.Pais;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import com.sagatechs.generics.persistence.model.State;
import com.sagatechs.generics.security.model.User;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class PaisDao extends GenericCatalogDao<Pais> {

    private final static Logger LOGGER = Logger.getLogger(PaisDao.class);

    public PaisDao() {
        super(Pais.class);
    }

    @Override
    public List<Pais> findAll() {
        String jpql = "SELECT DISTINCT o FROM Pais o" +
                " left outer join fetch o.provincias pr " +
                " left outer join fetch  pr.ciudades ";
        Query q = getEntityManager().createQuery(jpql, Pais.class);


        return q.getResultList();

    }

    @Override
    public List<Pais> findByEstado(State estado) {
        String jpql = "SELECT DISTINCT o FROM Pais o" +
                " left outer join fetch o.provincias pr " +
                " left outer join fetch  pr.ciudades c" +
                " where o.estado =:estado " +
                " and pr.estado =:estado " +
                " and c.estado =:estado " +
                " ORDER BY o.nombre ASC, pr.nombre ASC, c.nombre ASC ";
        Query q = getEntityManager().createQuery(jpql, Pais.class);
        q.setParameter("estado", estado);
        return q.getResultList();
    }
}
