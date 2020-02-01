/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.entity;

import de.hsos.kbse.osca.mp.abstracts.AbstractEntity;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author nordm
 */
@Entity
@Table(name = "TIMESLOT")
@NamedQueries({
    @NamedQuery(name = "Timeslot.findAll", query = "SELECT t FROM Timeslot t"),
    @NamedQuery(name = "Timeslot.findById", query = "SELECT t FROM Timeslot t WHERE t.id = :id"),
    @NamedQuery(name = "Timeslot.findBySlot", query = "SELECT t FROM Timeslot t WHERE t.slot = :slot")})
public class Timeslot extends AbstractEntity {

    private Integer slot;

    public Timeslot() {
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.slot);
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
        final Timeslot other = (Timeslot) obj;
        if (!Objects.equals(this.slot, other.slot)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Timeslot{" + "slot=" + slot + '}';
    }
}
