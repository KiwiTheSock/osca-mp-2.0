/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.controller;

import de.hsos.kbse.osca.mp.entity.Exam;
import de.hsos.kbse.osca.mp.service.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Philipp
 */
@RequestScoped
public class ExamRepository extends AbstractFacade<Exam> {

    @PersistenceContext(unitName = "de.hsos.kbse.oscar.mp_Hausarbeit-KbSE_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ExamRepository() {
        super(Exam.class);
    }

    /*    public void createList(List<Exam> examList) {
    
    examList.forEach((Exam) -> {
    super.create(Exam);
    });
    
    System.out.println("Database Initialize\n");
    }*/
    public List<Exam> getAll() {
        System.out.print("SQL: getAll()");
        TypedQuery<Exam> query;
        query = this.em.createNamedQuery("Exam.findAll", Exam.class);
        return query.getResultList();
    }

    public List<Exam> getAllDaybyDepartment(Long departmentId) {
        System.out.print("SQL: get all days by " + departmentId);
        List<Exam> resultList = em.createQuery(
                "select pc "
                + "from Exam pc "
                + "where pc.department.id = :departmentId", Exam.class)
                .setParameter("departmentId", departmentId)
                .getResultList();

        return resultList;
    }
    
    public List<Exam> findByDay(Date date) {
        System.err.println("SQL :"
                + "gwt all z");
        TypedQuery<Exam> query;
        query = this.em.createNamedQuery("Exam.findByDay",Exam.class);
        return query.getResultList();
        
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    
}
