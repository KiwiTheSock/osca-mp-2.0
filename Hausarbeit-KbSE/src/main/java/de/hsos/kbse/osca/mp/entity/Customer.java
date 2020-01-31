/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.entity;


import de.hsos.kbse.osca.mp.abstracts.AbstractEntity;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Philipp Markmann
 */

@Entity
@Table(name = "Customer")
public class Customer extends AbstractEntity{

    
    @NotNull(message = "Firstname may not be empty")
    private String firstname;
    @NotNull(message = "Lastname may not be empty")
    private String lastname;
    
    @NotNull(message = "Email may not be empty")
    private String email;
    
    /**
     * 
     * Wird eine PLZ, Straße und Stadt benötigt? 
     * 
     * @NotNull(message = "PLZ may not be empty)
     * pirvate int plz;
     * 
     * @NotNull(message = "Street may not be empty)
     * private String street;
     * @NotNull(message = "City may not be empty)
     * private String city
     */
    
    @NotNull(message = "Username may not be empty")
    private String studentLogin;
    @NotNull(message = "Password may not be empty")
    private String studentPassword;
    
    private int accountType; // Admin, Student, Dozent
    
    //Relation zu Modul (OOAD, Mathe 2,...)
    @ManyToMany
    private Set<Department> modules = new HashSet<>();


    public Customer() {
    }

    public Customer(String firstname, String lastname, String email, String studentLogin, String studentPassword, int accountType) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.studentLogin = studentLogin;
        this.studentPassword = studentPassword;
        this.accountType = accountType;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentLogin() {
        return studentLogin;
    }

    public void setStudentLogin(String studentLogin) {
        this.studentLogin = studentLogin;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Customer{" + "firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", studentLogin=" + studentLogin + ", studentPassword=" + studentPassword + ", accountType=" + accountType + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.firstname);
        hash = 83 * hash + Objects.hashCode(this.lastname);
        hash = 83 * hash + Objects.hashCode(this.email);
        hash = 83 * hash + Objects.hashCode(this.studentLogin);
        hash = 83 * hash + Objects.hashCode(this.studentPassword);
        hash = 83 * hash + this.accountType;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (this.accountType != other.accountType) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.studentLogin, other.studentLogin)) {
            return false;
        }
        if (!Objects.equals(this.studentPassword, other.studentPassword)) {
            return false;
        }
        return true;
    }

  

}
