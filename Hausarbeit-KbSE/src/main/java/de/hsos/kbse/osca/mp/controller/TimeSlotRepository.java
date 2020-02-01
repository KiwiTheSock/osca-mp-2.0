/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.controller;

import de.hsos.kbse.osca.mp.abstracts.AbstractRepository;
import de.hsos.kbse.osca.mp.entity.Timeslot;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Philipp
 */
@RequestScoped
public class TimeSlotRepository extends AbstractRepository<Timeslot>{
 
    
    public TimeSlotRepository() {
        this.entityClass = Timeslot.class;
    }
    
}