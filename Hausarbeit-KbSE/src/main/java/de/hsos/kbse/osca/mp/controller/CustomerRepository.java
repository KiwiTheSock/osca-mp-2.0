/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.controller;

import de.hsos.kbse.osca.mp.entity.Customer;
import de.hsos.kbse.osca.mp.entity.Department;
import de.hsos.kbse.osca.mp.service.AbstractFacade;
import de.hsos.kbse.osca.mp.service.AccessType;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * CustomerController (Repository)
 *
 * @author Philipp
 */
@ApplicationScoped
public class CustomerRepository extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "de.hsos.kbse.oscar.mp_Hausarbeit-KbSE_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public CustomerRepository() {
        super(Customer.class);
    }

    /**
     *
     * @param init
     */
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        try {
            super.create(new Customer("Administrator", "Administrator", "admin@hs-osnabrueck.de", "admin", "admin", 0));
            super.create(new Customer("Alias", "Dozent", "chef@hs-osnabrueck.de", "x", "x", 1));
            super.create(new Customer("Boss", "Dozent", "boss@hs-osnabrueck.de", "x", "x", 1));
            super.create(new Customer("Philipp", "Markmann", "pmarkman@hs-osnabrueck.de", "pmarkman", "asdf", 2));
            super.create(new Customer("Leander", "Nordmann", "nordmann@hs-osnabrueck.de", "lnordmann", "x", 2));
            this.getEntityManager().persist(new Department("OOAD", "SOSE2020"));
            this.getEntityManager().persist(new Department("Algorithmen", "SOSE2020"));
            this.getEntityManager().persist(new Department("SWE", "SOSE2020"));
            this.getEntityManager().persist(new Department("Datenbanken", "SOSE2020"));
            System.out.println("Database Initialize\n");
        } catch (Exception dinf) {
            throw new IllegalStateException("Something went wrong.");
        }
    }

     public List<Customer> findAllDozents() {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByType", Customer.class);
        query.setParameter("type", AccessType.DOZENT.getLevelCode());
        return query.getResultList();
     }
    
    
    public Customer getByLogin(String login) {
        System.out.print("SQL: get " + login);
        TypedQuery<Customer> query;
        query = this.getEntityManager().createNamedQuery("Customer.findByLogin", Customer.class);
        return query.setParameter("login", login).getSingleResult();
    }

    public List<Customer> getAll() {
        System.out.print("SQL: getAll()");
        TypedQuery<Customer> query;
        query = this.getEntityManager().createNamedQuery("Customer.findAll", Customer.class);
        return query.getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
