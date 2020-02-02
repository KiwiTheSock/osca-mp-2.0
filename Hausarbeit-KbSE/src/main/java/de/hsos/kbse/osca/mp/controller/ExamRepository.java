/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.controller;

import de.hsos.kbse.osca.mp.abstracts.AbstractRepository;
import de.hsos.kbse.osca.mp.entity.Department;
import de.hsos.kbse.osca.mp.entity.Exam;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

/**
 *
 * @author Philipp
 */
@RequestScoped
public class ExamRepository extends AbstractRepository<Exam> {

    public ExamRepository() {
        this.entityClass = Exam.class;
    }

    public List<Exam> getAll() {
        System.out.print("SQL: getAll()");
        TypedQuery<Exam> query;
        query = this.em.createNamedQuery("Department.findAll", Exam.class);
        return query.getResultList();
    }
    
    public List<Exam> getAllDaybyDepartment() {
        System.out.print("SQL: getAllDaysbyDep()");
        TypedQuery<Exam> query;
        query = this.em.createNamedQuery("Department.findAll", Exam.class);
        return query.getResultList();
    }

}
