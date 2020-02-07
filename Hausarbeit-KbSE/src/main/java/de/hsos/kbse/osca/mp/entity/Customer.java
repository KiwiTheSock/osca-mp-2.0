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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author nordm
 */
@Entity
@Table(name = "CUSTOMER")
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
    ,
    @NamedQuery(name = "Customer.findById", query = "SELECT c FROM Customer c WHERE c.id = :id")
    ,
    @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email")
    ,
    @NamedQuery(name = "Customer.findByFirstname", query = "SELECT c FROM Customer c WHERE c.firstname = :firstname")
    ,
    @NamedQuery(name = "Customer.findByLastname", query = "SELECT c FROM Customer c WHERE c.lastname = :lastname")
    ,
    @NamedQuery(name = "Customer.findByLogin", query = "SELECT c FROM Customer c WHERE c.login = :login")
    ,
    @NamedQuery(name = "Customer.findIdByLogin", query = "SELECT c.id FROM Customer c WHERE c.login = :login")
    ,
    @NamedQuery(name = "Customer.findByPassword", query = "SELECT c FROM Customer c WHERE c.password = :password")
    ,
    @NamedQuery(name = "Customer.findByType", query = "SELECT c FROM Customer c WHERE c.type = :type")})
public class Customer extends AbstractEntity {

    private String email;
    private String firstname;
    private String lastname;
    @Column(nullable = false, unique = true)
    private String login;

    private String password;

    private Integer type;

    //Admin des Moduls
    @ManyToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinTable(name = "customer_department",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private Set<Department> departments = new HashSet<>();

    public void addDepartment(Department dep) {
        departments.add(dep);
        dep.getCustomers().add(this);
    }

    public void removeDepartment(Department dep) {
        departments.remove(dep);
        dep.getCustomers().add(null);
    }

    //Bei Prüfung angemeldet
    @ManyToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinTable(name = "customer_exam",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private Set<Exam> exams = new HashSet<>();

    public void addExam(Exam ex) {
        exams.add(ex);
        
        ex.getCustomers().add(this);
    }

    public void removeExam(Exam ex) {
        exams.remove(ex);
        ex.getCustomers().add(null);
    }

    public Customer() {
    }

    public Customer(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Customer(String firstname, String lastname, String email, String login, String password, Integer type) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.type = type;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.firstname);
        hash = 97 * hash + Objects.hashCode(this.lastname);
        hash = 97 * hash + Objects.hashCode(this.login);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + Objects.hashCode(this.departments);
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
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.departments, other.departments)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + ", login=" + login + ", password=" + password + ", type=" + type +'}';
    }

}
