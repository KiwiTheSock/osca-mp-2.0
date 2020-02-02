/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.controller;

import de.hsos.kbse.osca.mp.abstracts.AbstractRepository;
import de.hsos.kbse.osca.mp.entity.Customer;
import de.hsos.kbse.osca.mp.entity.Department;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;


/**
 *
 * @author Philipp
 */
@RequestScoped
public class DepartmentRepository extends AbstractRepository<Department>{

    public DepartmentRepository() {
        this.entityClass = Department.class;
    }

    public List<Department> getAll() {
        System.out.print("SQL: getAll()");
        TypedQuery<Department> query;
        query = this.em.createNamedQuery("Department.findAll", Department.class);
        return query.getResultList();
    }
    
   public List<Department> getAllfromUserId() {
       return null;
   }
}
