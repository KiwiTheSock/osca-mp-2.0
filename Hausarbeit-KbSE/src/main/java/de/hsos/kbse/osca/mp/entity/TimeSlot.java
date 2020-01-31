/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.entity;

import de.hsos.kbse.osca.mp.abstracts.AbstractEntity;
import java.sql.Time;
import java.util.Objects;
import javax.enterprise.inject.Vetoed;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Philipp
 */
@Entity
@Vetoed
@Table(name = "TimeSlot")
public class TimeSlot extends AbstractEntity {

    private float slot;

    public TimeSlot() {
    }

    public TimeSlot(float slot) {
        this.slot = slot;
    }

    public float getSlot() {
        return slot;
    }

    public void setSlot(float slot) {
        this.slot = slot;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Float.floatToIntBits(this.slot);
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
        final TimeSlot other = (TimeSlot) obj;
        if (Float.floatToIntBits(this.slot) != Float.floatToIntBits(other.slot)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TimeSlot{" + "slot=" + slot + '}';
    }

}
