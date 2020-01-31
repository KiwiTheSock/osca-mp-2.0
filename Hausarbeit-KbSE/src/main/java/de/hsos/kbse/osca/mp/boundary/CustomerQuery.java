/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.boundary;

import de.hsos.kbse.osca.mp.controller.CustomerRepository;
import de.hsos.kbse.osca.mp.entity.Customer;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Philipp
 *
 * LoginHandler oder doch Customer?
 */
@Named(value = "customer")
public class CustomerQuery {

    @Inject
    private CustomerRepository repo;
    private Customer cust;

    public CustomerQuery() {
    }

    public CustomerRepository getRepo() {
        return repo;
    }

    public void setRepo(CustomerRepository repo) {
        this.repo = repo;
    }

    public Customer getCust() {
        return cust;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }

    /*    public void getCustomerById() {
    this.cust = (Customer) repo.findById(matnr);
    if(this.cust==null) {
    FacesContext.getCurrentInstance()
    .addMessage(null, new FacesMessage("Studen with "+this.matnr+"hat sich gerad eingeloggt!"));
    }
    }*/
    
    
    
}
