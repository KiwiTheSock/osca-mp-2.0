/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.controller;

import de.hsos.kbse.osca.mp.abstracts.AbstractRepository;
import de.hsos.kbse.osca.mp.entity.Customer;
import javax.ejb.Stateless;

/**
 *
 * CustomerController (Repository)
 *
 * @author Philipp
 */
@Stateless
public class CustomerRepository extends AbstractRepository<Customer> {

    public CustomerRepository() {
    }
    
    public Customer getByLogin(String login) {
        
        System.out.print("SQL: get " + login);
        return query("select e from #table e where e.studentLogin = :login").put("login", login).one();
        
    }
}
