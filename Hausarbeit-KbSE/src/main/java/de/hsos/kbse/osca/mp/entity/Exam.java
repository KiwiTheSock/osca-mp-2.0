/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.entity;

import de.hsos.kbse.osca.mp.abstracts.AbstractEntity;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;
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
@Table(name = "EXAM")
@NamedQueries({
    @NamedQuery(name = "Exam.findAll", query = "SELECT e FROM Exam e"),
    @NamedQuery(name = "Exam.findById", query = "SELECT e FROM Exam e WHERE e.id = :id"),
    @NamedQuery(name = "Exam.findByDay", query = "SELECT e FROM Exam e WHERE e.datum = :datum"),
    @NamedQuery(name = "Exam.findByDuration", query = "SELECT e FROM Exam e WHERE e.duration = :duration"),
    @NamedQuery(name = "Exam.findByFinish", query = "SELECT e FROM Exam e WHERE e.finish = :finish"),
    @NamedQuery(name = "Exam.findBySpaceforstudents", query = "SELECT e FROM Exam e WHERE e.spaceforstudents = :spaceforstudents"),
    @NamedQuery(name = "Exam.findByStart", query = "SELECT e FROM Exam e WHERE e.beginn = :beginn")})
public class Exam extends AbstractEntity {

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datum;

    private Double duration;

    private Time beginn;

    private Time finish;
    
    private Integer spaceforstudents;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Timeslot> timeslotSet;

    public Exam() {
    }

    public Exam(Date day, Double duration, Time start, Time finish, Integer spaceforstudents) {
        this.datum = day;
        this.duration = duration;
        this.beginn = start;
        this.finish = finish;
        this.spaceforstudents = spaceforstudents;
    }
    
    
    
    public Date getStart() {
        return beginn;
    }

    public void setStart(Time start) {
        this.beginn = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Time finish) {
        this.finish = finish;
    }

    public Set<Timeslot> getTimeslotSet() {
        return timeslotSet;
    }

    public void setTimeslotSet(Set<Timeslot> timeslotSet) {
        this.timeslotSet = timeslotSet;
    }
    
    public Date getDay() {
        return datum;
    }

    public void setDay(Date day) {
        this.datum = day;
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
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.datum);
        hash = 29 * hash + Objects.hashCode(this.duration);
        hash = 29 * hash + Objects.hashCode(this.beginn);
        hash = 29 * hash + Objects.hashCode(this.finish);
        hash = 29 * hash + Objects.hashCode(this.spaceforstudents);
        hash = 29 * hash + Objects.hashCode(this.timeslotSet);
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
        if (!Objects.equals(this.timeslotSet, other.timeslotSet)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Exam{" + "day=" + datum + ", duration=" + duration + ", start=" + beginn + ", finish=" + finish + ", spaceforstudents=" + spaceforstudents + ", timeslotSet=" + timeslotSet + '}';
    }
  
}
