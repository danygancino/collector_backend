package com.sagatechs.asistencias.dao.catalogs;

import com.sagatechs.asistencias.model.catalogs.CatalogoBase;
import com.sagatechs.generics.persistence.GenericDaoJpa;
import com.sagatechs.generics.persistence.model.State;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericCatalogDao<T extends CatalogoBase> extends GenericDaoJpa<T, Long> {

    public GenericCatalogDao(Class<T> entityClass) {
        super(entityClass, Long.class);
    }

    @Override
    public void remove(CatalogoBase catalogoBase) {
        throw new UnsupportedOperationException("No es posible borrar catálogos");
    }

    @Override
    public void removeAll() {
        throw new UnsupportedOperationException("No es posible borrar catálogos");
    }

    public List<T> findByEstado(State estado) {
        String qlString = "SELECT o FROM " + getEntityClass().getSimpleName() + " o WHERE o.estado= :estado ORDER BY o.nombre ASC ";
        Query query = getEntityManager().createQuery(qlString, CatalogoBase.class);
        query.setParameter("estado", estado);
        return query.getResultList();
    }

    public List<T> findByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        String qlString = "SELECT o FROM " + getEntityClass().getSimpleName() + " o WHERE o.id in (:ids) ORDER BY o.nombre ASC ";
        Query query = getEntityManager().createQuery(qlString, CatalogoBase.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    public T findByNombre(String nombre) {

        String tableName = this.getTableName();

        String schemaName = this.getSchemaName();


        String sql="SELECT * FROM "+schemaName+"."+tableName+" c WHERE public.unaccent(LOWER(nombre))=unaccent(LOWER(:nombre))";
        Query query = getEntityManager().createNativeQuery(sql, getEntityClass());
        query.setParameter("nombre", nombre);
        try {
            return (T) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
