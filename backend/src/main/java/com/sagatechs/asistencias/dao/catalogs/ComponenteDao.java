package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.CatalogoBase;
import com.sagatechs.asistencias.model.catalogs.Componente;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import com.sagatechs.generics.persistence.model.State;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ComponenteDao extends GenericCatalogDao<Componente> {

    private final static Logger LOGGER = Logger.getLogger(ComponenteDao.class);

    public ComponenteDao() {
        super(Componente.class);
    }

    @Override
    public List<Componente> findByEstado(State estado) {
        String qlString = "SELECT o FROM Componente o " +
                " left outer join o.tiposAsistencias ta " +
                " WHERE o.estado= :estado " +
                " and ta.estado =:estado " +
                " ORDER BY o.nombre ASC ";
        Query query = getEntityManager().createQuery(qlString, CatalogoBase.class);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
}
