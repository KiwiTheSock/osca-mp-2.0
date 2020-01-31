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
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;


/**
 *
 * @author Philipp
 */


@Entity
@Table(name = "Exam")
public class Exam extends AbstractEntity {
    
    @NotNull(message = "duration can't be empty")
    private float duration;
    @NotNull(message = "Starttime can't be empty")
    private Time startT;
    @NotNull(message = "Endtime can't be empty")
    private Time endT;
    @NotNull(message = "Amount can't be empty")
    private int spaceforStudents;
    @NotNull(message = "Day can't be empty")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date examDate;
    
      //Wird die Hashset benötigt??
//    @ManyToOne
//    private HashSet<Module> modules;

    @OneToMany
    private Set<TimeSlot> timeslots = new HashSet<>();
    
    public Exam() {
    }

    public Exam(float duration, Time start, Time end, int spaceforStudents, Date day) {
        this.duration = duration;
        this.startT = start;
        this.endT = end;
        this.spaceforStudents = spaceforStudents;
        this.examDate = day;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public Time getStart() {
        return startT;
    }

    public void setStart(Time start) {
        this.startT = start;
    }

    public Time getEnd() {
        return endT;
    }

    public void setEnd(Time end) {
        this.endT = end;
    }

    public int getSpaceforStudents() {
        return spaceforStudents;
    }

    public void setSpaceforStudents(int spaceforStudents) {
        this.spaceforStudents = spaceforStudents;
    }

    public Date getDay() {
        return examDate;
    }

    public void setDay(Date day) {
        this.examDate = day;
    }

    public Set<TimeSlot> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(HashSet<TimeSlot> timeslots) {
        this.timeslots = timeslots;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Float.floatToIntBits(this.duration);
        hash = 23 * hash + Objects.hashCode(this.startT);
        hash = 23 * hash + Objects.hashCode(this.endT);
        hash = 23 * hash + this.spaceforStudents;
        hash = 23 * hash + Objects.hashCode(this.examDate);
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
        if (Float.floatToIntBits(this.duration) != Float.floatToIntBits(other.duration)) {
            return false;
        }
        if (this.spaceforStudents != other.spaceforStudents) {
            return false;
        }
        if (!Objects.equals(this.startT, other.startT)) {
            return false;
        }
        if (!Objects.equals(this.endT, other.endT)) {
            return false;
        }
        if (!Objects.equals(this.examDate, other.examDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Exam{" + "duration=" + duration + ", start=" + startT + ", end=" + endT + ", spaceforStudents=" + spaceforStudents + ", day=" + examDate + ", timeslots=" + timeslots + '}';
    }


}
