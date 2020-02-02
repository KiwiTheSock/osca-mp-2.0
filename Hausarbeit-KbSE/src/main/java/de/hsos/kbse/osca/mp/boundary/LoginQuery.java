/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.boundary;

import de.hsos.kbse.osca.mp.abstracts.AbstractRepoAccesor;
import de.hsos.kbse.osca.mp.entity.Customer;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Philipp
 */
@Named
@RequestScoped
public class LoginQuery extends AbstractRepoAccesor implements Serializable {

    boolean loggedIn;
    long accountId;

    private Customer cust;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {

        System.out.println("de.hsos.kbse.osca.mp.boundary.LoginQuery.login()");

        this.cust = Customers.getByLogin(this.username);
        if (this.cust == null | !cust.getPassword().equals(this.password)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Username: " + username + " konnte nicht gefunden werden!"));
            return null;
        } else {

            this.loggedIn = true;
            this.accountId = this.cust.getId();

            //SessionHandler
            try {
                HttpSession sessionObj = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                SessionHandler.store(accountId, sessionObj);
            } catch (Exception ex) {
                System.out.println("HTTPSession went wrong");
            }

            switch (this.cust.getType()) {

                //Superadmin?
                case 0:
                    System.out.println("Superadmin!");
                    return null;
                //Dozent 
                case 1:
                    return "modulAnlegenDozent.xhtml?faces-redirect=true";
                //Student
                case 2:
                    return "modulAuswahlStudent.xhtml?faces-redirect=true";

                default:
                    System.out.println("Default!");
                    return null;
            }

        }
    }

    public String logout() {
        
        System.out.println("de.hsos.kbse.osca.mp.boundary.LoginQuery.logout()");

        if (this.loggedIn == true) {
            System.out.println("Inside: logout()");
            this.loggedIn = true;
            this.accountId = this.cust.getId();
            return "login.xhtml?faces-redirect=true";
        }
        
        return null;
    }

}
