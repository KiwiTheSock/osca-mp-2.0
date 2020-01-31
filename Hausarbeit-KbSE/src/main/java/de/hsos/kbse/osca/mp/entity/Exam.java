/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.entity;

import de.hsos.kbse.osca.mp.abstracts.AbstractEntity;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author nordm
 */
@Entity
@Table(name = "EXAM", schema = "X")
@NamedQueries({
    @NamedQuery(name = "Exam.findAll", query = "SELECT e FROM Exam e"),
    @NamedQuery(name = "Exam.findById", query = "SELECT e FROM Exam e WHERE e.id = :id"),
    @NamedQuery(name = "Exam.findByDay", query = "SELECT e FROM Exam e WHERE e.day = :day"),
    @NamedQuery(name = "Exam.findByDuration", query = "SELECT e FROM Exam e WHERE e.duration = :duration"),
    @NamedQuery(name = "Exam.findByFinish", query = "SELECT e FROM Exam e WHERE e.finish = :finish"),
    @NamedQuery(name = "Exam.findBySpaceforstudents", query = "SELECT e FROM Exam e WHERE e.spaceforstudents = :spaceforstudents"),
    @NamedQuery(name = "Exam.findByStart", query = "SELECT e FROM Exam e WHERE e.start = :start")})
public class Exam extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date day;

    private Double duration;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date start;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date finish;
    
    private Integer spaceforstudents;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Timeslot> timeslotSet;

    public Exam() {
    }

    public Exam(Long id) {
        this.id = id;
    }

    
    
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public Set<Timeslot> getTimeslotSet() {
        return timeslotSet;
    }

    public void setTimeslotSet(Set<Timeslot> timeslotSet) {
        this.timeslotSet = timeslotSet;
    }

    
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Integer getSpaceforstudents() {
        return spaceforstudents;
    }

    public void setSpaceforstudents(Integer spaceforstudents) {
        this.spaceforstudents = spaceforstudents;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.hsos.kbse.osca.mp.entity.Exam[ id=" + id + " ]";
    }

}
