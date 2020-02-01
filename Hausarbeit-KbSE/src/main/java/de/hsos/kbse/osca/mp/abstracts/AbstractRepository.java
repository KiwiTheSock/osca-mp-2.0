/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.abstracts;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Philipp Markmann
 * @param <T>
 */
@Transactional(Transactional.TxType.REQUIRES_NEW)
public abstract class AbstractRepository<T extends AbstractEntity> implements Serializable {

    @PersistenceContext(unitName = "de.hsos.kbse.oscar.mp_Hausarbeit-KbSE_war_1.0-SNAPSHOTPU")
    protected EntityManager em;

    protected Class<T> entityClass;

    public T add(T entity) {
        System.out.print("SQL: persist " + entityClass.getName());
        em.persist(entity);
        return entity;
    }

    public T findById(long id) {
        System.out.print("SQL: get " + entityClass.getName() + " " + id);
        return (T) em.find(this.entityClass, id);
    }

    public void remove(T entity) {
        System.out.print("SQL: remove " + entityClass.getName() + " " + entity.getId());
        em.remove(entity);
    }

    public T update(T entity) {
        System.out.print("SQL: update " + entityClass.getName() + " " + entity.getId());
        return em.merge(entity);
    }

}
