/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.controller;

import de.hsos.kbse.osca.mp.abstracts.AbstractRepository;
import de.hsos.kbse.osca.mp.entity.Customer;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;


/**
 *
 * CustomerController (Repository)
 *
 * @author Philipp
 */
@RequestScoped
public class CustomerRepository extends AbstractRepository<Customer> {

    public CustomerRepository() {
        this.entityClass = Customer.class;
    }
    
    public Customer getByLogin(String login) { 
        System.out.print("SQL: get " + login);
        TypedQuery<Customer> query;
        query = this.em.createNamedQuery("Customer.findByLogin", Customer.class);
        return query.getSingleResult();
        
    }

    public List<Customer> getAll() {
        System.out.print("SQL: getAll()");
        TypedQuery<Customer> query;
        query = this.em.createNamedQuery("Customer.findAll", Customer.class);
        return query.getResultList();
    }
}