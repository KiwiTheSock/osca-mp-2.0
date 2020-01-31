/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.boundary;

import de.hsos.kbse.osca.mp.abstracts.AbstractRepoAccesor;
import de.hsos.kbse.osca.mp.entity.Customer;
import de.hsos.kbse.osca.mp.entity.Department;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 *
 * @author Philipp
 */
@Named(value = "filldata")
@Singleton
public class FillDatabase extends AbstractRepoAccesor {

    @PostConstruct
    void init() {
        Locale.setDefault(Locale.GERMANY);
        System.out.println("Outside IF!\n");

        //Check if database is filled
        /*        if(query()) {
        
        }*/
        
        if (Customers.getAll().isEmpty()) {
            
            System.out.println("Inside IF!\n");
            
            //String firstname, String lastname, String email, String studentLogin, String studentPassword, int accountType
            Customers.add(new Customer("Administrator", "Administrator", "admin@hs-osnabrueck.de", "admin", "admin", 1));
            Customers.add(new Customer("Philipp", "Markmann", "pmarkman@hs-osnabrueck.de", "pmarkman", "asdf", 2));
            
            Departments.add(new Department("KbSe", "5"));
            Departments.add(new Department("OOAD", "4"));

            
            System.out.println("de.hsos.kbse.osca.mp.boundary.FillDatabase.init()");
        }else {
            
            System.out.println("Userdaten vorhanden!");
        }
        
    }

}
