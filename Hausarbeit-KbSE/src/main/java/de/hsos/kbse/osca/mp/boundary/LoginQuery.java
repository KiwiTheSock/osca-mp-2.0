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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Philipp
 */
@Named
@SessionScoped
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

    //Customer customer = new Customer();
    public String login() {

        System.out.println("de.hsos.kbse.osca.mp.boundary.LoginQuery.login()");
        //System.out.println("1 Username: " + this.username + " Password: " + this.password);

        this.cust = Customers.getByLogin(this.username);
        if (this.cust == null | !cust.getStudentPassword().equals(this.password)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Username: " + username + " konnte nicht gefunden werden!"));
            return null;
        } else {

            this.loggedIn = true;
            this.accountId = this.cust.getId();

            /*
        SessionHandler wird wofür benötigt? 
        try {
        HttpSession sessionObj = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        SessionHandler.put(accountId, sessionObj);
        
        } catch (Exeption ex) {
        
        }*/
            return "modulAuswahlStudent.xhtml?faces-redirect=true";
        }
    }

    public String loginDozent() {

        System.out.println("de.hsos.kbse.osca.mp.boundary.LoginQuery.loginDozent()");
        //System.out.println("1 Username: " + this.username + " Password: " + this.password);

        this.cust = Customers.getByLogin(this.username);
        if (this.cust == null | !cust.getStudentPassword().equals(this.password)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Username: " + username + " konnte nicht gefunden werden!"));
            return null;
        } else {

            if(this.cust.getAccountType()==1) {
                this.loggedIn = true;
                this.accountId = this.cust.getId();
                return "modulAnlegenDozent.xhtml?faces-redirect=true";
            }

            return null;
        }
    }

}
