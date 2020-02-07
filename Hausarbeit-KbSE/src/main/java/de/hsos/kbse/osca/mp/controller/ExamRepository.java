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
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.bind.Jsonb;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Philipp
 */
@Named
@RequestScoped
public class ExamRepository extends AbstractFacade<Exam> {

    @Inject
    private Jsonb jsonb;

    @PersistenceContext(unitName = "de.hsos.kbse.oscar.mp_Hausarbeit-KbSE_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ExamRepository() {
        super(Exam.class);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
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

    public Exam getByDayAndTime(String date, String time) throws ParseException {
        System.out.println("SQL: by Day:" + date + " and Time " + time);

        //List<Exam> resultList = findByDay(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//give format in which you are receiving the `String date_updated`
        Date searchdate = sdf.parse(date);
        LocalTime start = LocalTime.parse(time);
        Time t = Time.valueOf(start);

        Exam result = em.createQuery(
                "SELECT e "
                + "FROM Exam e "
                + "WHERE e.datum = :datum "
                + "AND e.finish = :finish", Exam.class)
                .setParameter("datum",searchdate)
                .setParameter("finish", t)
                .getSingleResult();

        return result;
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
        //System.out.println("Bin: " + searchdate.getClass());
        //System.out.println(searchdate.toString());

        TypedQuery<Exam> query;
        query = this.em.createNamedQuery("Exam.findByDay", Exam.class);
        return query.setParameter("datum", searchdate).getResultList();

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Jsonb getJsonb() {
        return jsonb;
    }

    public void setJsonb(Jsonb jsonb) {
        this.jsonb = jsonb;
    }


    
    

}
