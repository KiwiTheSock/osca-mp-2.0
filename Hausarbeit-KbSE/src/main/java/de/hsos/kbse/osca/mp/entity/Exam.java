/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.entity;

import de.hsos.kbse.osca.mp.abstracts.AbstractEntity;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author nordm
 */
@Entity
@Table(name = "EXAM")
@NamedQueries({
    @NamedQuery(name = "Exam.findAll", query = "SELECT e FROM Exam e")
    ,
    @NamedQuery(name = "Exam.findById", query = "SELECT e FROM Exam e WHERE e.id = :id")
    ,
    @NamedQuery(name = "Exam.findByDay", query = "SELECT e FROM Exam e WHERE e.datum = :datum")
    ,
    @NamedQuery(name = "Exam.findByDuration", query = "SELECT e FROM Exam e WHERE e.duration = :duration")
    ,
    @NamedQuery(name = "Exam.findByFinish", query = "SELECT e FROM Exam e WHERE e.finish = :finish")
    ,
    @NamedQuery(name = "Exam.findBySpaceforstudents", query = "SELECT e FROM Exam e WHERE e.spaceforstudents = :spaceforstudents")
    ,
    @NamedQuery(name = "Exam.findByStart", query = "SELECT e FROM Exam e WHERE e.beginn = :beginn")})
public class Exam extends AbstractEntity {

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datum;

    private Integer duration;

    private Time beginn;

    private Time finish;

    private Integer spaceforstudents;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department; 

    public Department getDepart() {
        return department;
    }

    public void setDepart(Department depart) {
        this.department = depart;
    }
    
    //ManyToMany für den Studenten
    @ManyToMany(fetch= FetchType.EAGER, mappedBy="exams")
    private Set<Customer> customers = new HashSet<>();

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
    
    public Exam() {
    }

    public Exam(Date datum, Integer duration, Time beginn, Time finish, Integer spaceforstudents) {
        this.datum = datum;
        this.duration = duration;
        this.beginn = beginn;
        this.finish = finish;
        this.spaceforstudents = spaceforstudents;
    }

    public Exam(Date datum, Integer duration, Time beginn, Time finish, Integer spaceforstudents, Department department) {
        this.datum = datum;
        this.duration = duration;
        this.beginn = beginn;
        this.finish = finish;
        this.spaceforstudents = spaceforstudents;
        this.department = department;
    }
    
    public Time getBeginn() {
        return beginn;
    }

    public void setBeginn(Time beginn) {
        this.beginn = beginn;
    }

    public Time getFinish() {
        return finish;
    }

    public void setFinish(Time finish) {
        this.finish = finish;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getSpaceforstudents() {
        return spaceforstudents;
    }

    public void setSpaceforstudents(Integer spaceforstudents) {
        this.spaceforstudents = spaceforstudents;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.datum);
        hash = 97 * hash + Objects.hashCode(this.duration);
        hash = 97 * hash + Objects.hashCode(this.beginn);
        hash = 97 * hash + Objects.hashCode(this.finish);
        hash = 97 * hash + Objects.hashCode(this.spaceforstudents);
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
        final Exam other = (Exam) obj;
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        if (!Objects.equals(this.duration, other.duration)) {
            return false;
        }
        if (!Objects.equals(this.beginn, other.beginn)) {
            return false;
        }
        if (!Objects.equals(this.finish, other.finish)) {
            return false;
        }
        if (!Objects.equals(this.spaceforstudents, other.spaceforstudents)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Exam{" + "datum=" + datum + ", duration=" + duration + ", beginn=" + beginn + ", finish=" + finish + ", spaceforstudents=" + spaceforstudents + ", depart=" + department + '}';
    }
}
