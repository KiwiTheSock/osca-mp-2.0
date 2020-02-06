/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.controller;

import de.hsos.kbse.osca.mp.entity.Exam;
import de.hsos.kbse.osca.mp.service.AbstractFacade;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public void init() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        // this.getEntityManager().persist(new Exam(formatter.parse("14:22"), 30, Time.valueOf("13:00"), Time.valueOf("13:30"), 1,));));
    }

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

    public List<Exam> findByDay(String date) throws ParseException {
        System.out.println("SQL :"
                + date);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//give format in which you are receiving the `String date_updated`
        Date searchdate = sdf.parse(date);
        //Ausgabe
        System.out.println("Bin: " + searchdate.getClass());
        System.out.println(searchdate.toString());

        TypedQuery<Exam> query;
        query = this.em.createNamedQuery("Exam.findByDay", Exam.class);
        return query.setParameter("datum", searchdate).getResultList();

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
