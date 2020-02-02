/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.controller;

import de.hsos.kbse.osca.mp.abstracts.AbstractRepository;
import de.hsos.kbse.osca.mp.entity.Department;
import de.hsos.kbse.osca.mp.entity.Timeslot;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

/**
 *
 * @author Philipp
 */
@RequestScoped
public class TimeSlotRepository extends AbstractRepository<Timeslot>{
 
    
    public TimeSlotRepository() {
        this.entityClass = Timeslot.class;
    }
    
        public List<Timeslot> getAll() {
        System.out.print("SQL: getAll()");
        TypedQuery<Timeslot> query;
        query = this.em.createNamedQuery("Department.findAll", Timeslot.class);
        return query.getResultList();
    }
    
}
