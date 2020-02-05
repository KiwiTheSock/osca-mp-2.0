/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.service;

import de.hsos.kbse.osca.mp.entity.Exam;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nordm
 */
@RequestScoped
@Path("de.hsos.kbse.osca.mp.entity.exam")
public class ExamFacadeREST extends AbstractFacade<Exam> {

    @PersistenceContext(unitName = "de.hsos.kbse.oscar.mp_Hausarbeit-KbSE_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ExamFacadeREST() {
        super(Exam.class);
    }
    /*
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createExam(
    @QueryParam("tag") String tag,
    @QueryParam("start") String start,
    @QueryParam("finish") String finish,
    @QueryParam("duration") Integer duration,
    @QueryParam("spaceforstudents") Integer spaceforstudents
    ) throws ParseException {
    
    //        GregorianCalendar calender3 = new GregorianCalendar(Locale.GERMANY);
    //        calender3.set(2015, 03, 12, 16, 00);
    //        Date finish1 = calender3.getTime();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date date = simpleDateFormat.parse(tag);
    
    LocalTime st = LocalTime.parse(start);
    Time time1 = Time.valueOf(st);
    
    LocalTime end = LocalTime.parse(finish);
    Time time2 = Time.valueOf(end);
    
    Exam exam = new Exam(date, duration, time1, time2, spaceforstudents);
    super.create(exam);
    //        try {
    //            return (Response.ok(exam, MediaType.APPLICATION_JSON)).build();
    //        } catch (Exception e) {
    return Response.status(200)
    .entity("newEntity : " + exam.getDatum() + " with " + exam.getDuration()
    + " and " + exam.getBeginn() + " and " + exam.getFinish()).build();
    //        }
    
    }*/

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Exam entity) {
        super.create(entity);
    }
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Exam entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Exam find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exam> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exam> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
