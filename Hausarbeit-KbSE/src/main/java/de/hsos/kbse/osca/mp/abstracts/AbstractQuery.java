/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.abstracts;

//import static com.oracle.webservices.internal.api.databinding.DatabindingModeFeature.ID;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
import javax.persistence.TypedQuery;

/**
 *
 * @author Philipp Markmann
 * @param <T>
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractQuery<T extends AbstractEntity> {

    TypedQuery<T> query;
    String sql;

    public AbstractQuery(EntityManager em, String sql, Class<T> entityClass, String table) {
        this.sql = sql.replace("#table", table);
        this.query = em.createQuery(this.sql, entityClass);
    }

    public AbstractQuery<T> put(String name, Object value) {
        this.sql = sql.replace(":" + name, "\"" + value.toString() + "\"");
        this.query.setParameter(name, value);
        return this;
    }

    //Queryanfrage für alle vohanden Objekte 
    public Collection<T> all() {
        try {
            System.out.println("SQL: " + sql);
            return query.getResultList();
        } catch (Exception ex) {
            System.out.println("SQL: " + ex);
            return new ArrayList<>();
        }
    }

    public T one() {
        try {
            System.out.println("SQL: " + sql);
            return query.getSingleResult();
        } catch (Exception ex) {
            System.out.println("SQL: " + ex);
            return null;
        }
    }

}
