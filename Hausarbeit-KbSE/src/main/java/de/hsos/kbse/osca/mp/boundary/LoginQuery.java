/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.boundary;

import de.hsos.kbse.osca.mp.abstracts.AbstractRepoAccesor;
import de.hsos.kbse.osca.mp.entity.Customer;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Philipp Markmann
 */
@Named
@ApplicationScoped
public class LoginQuery extends AbstractRepoAccesor implements Serializable {

    @Inject
    private HttpSession session;

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

        System.out.println("Login");

        FacesMessage msg;

        this.cust = Customers.getByLogin(this.username);
        if (this.cust == null | !cust.getPassword().equals(this.password)) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Parameter nicht ausgewaehlt.");
            return "login.xhtml?faces-redirect=true";
        } else {

            msg = new FacesMessage("Login Erfolgreich!");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            this.accountId = this.cust.getId();
            this.session.setAttribute("username", this.username);
            this.session.setAttribute("id", this.accountId);

            switch (this.cust.getType()) {

                case 0:
                    return null;
                case 1:
                    return "modulAnlegenDozent.xhtml?faces-redirect=true";
                case 2:
                    return "modulAuswahlStudent.xhtml?faces-redirect=true";
                default:
                    return "login.xhtml?faces-redirect=true";
            }
        }
    }

    public String logout() {

        FacesMessage msg = new FacesMessage("Logout erfolgreich!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println("Logout)");
        this.session.invalidate();
        return "login.xhtml?faces-redirect=true";
    }

}
