/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.abstracts;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Philipp Markmann
 * @param <T>
 */
@Transactional
public abstract class AbstractRepository<T extends AbstractEntity> implements Serializable {

    @PersistenceContext(unitName = "de.hsos.kbse.oscar.mp_Hausarbeit-KbSE_war_1.0-SNAPSHOTPU")
    protected EntityManager em;

    protected Class<T> entityClass;
    protected String table;

    @PostConstruct
    public void init() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
        this.table = this.entityClass.getSimpleName();
    }

    public T add(T entity) {
        System.out.print("SQL: persist " + table);
        em.persist(entity);
        return entity;
    }

    public T findById(long id) {
        System.out.print("SQL: get " + table + " " + id);
        return (T) em.find(this.entityClass, id);
    }

    public void remove(T entity) {
        System.out.print("SQL: remove " + table + " " + entity.getId());
        em.remove(entity);
    }

    public T update(T entity) {
        System.out.print("SQL: update " + table + " " + entity.getId());
        return em.merge(entity);
    }

    public AbstractQuery<T> query(String sql) {
        return new AbstractQuery(em, sql, entityClass, table);
    }

    public Collection<T> getAll() {
        return query("select e from #table e").all();
    }

}
