/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.service;

import de.hsos.kbse.osca.mp.RESTfulInterfaces.ExamRestInterface;
import de.hsos.kbse.osca.mp.controller.DepartmentRepository;
import de.hsos.kbse.osca.mp.controller.ExamRepository;
import de.hsos.kbse.osca.mp.entity.Department;
import de.hsos.kbse.osca.mp.entity.Exam;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
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
public class ExamFacadeREST implements ExamRestInterface {

    @Inject
    ExamRepository exrepo;

    @Inject
    DepartmentRepository deprepo;

    @Inject
    private Jsonb jsonb;

    @PersistenceContext(unitName = "de.hsos.kbse.oscar.mp_Hausarbeit-KbSE_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ExamFacadeREST() {
//       this.exrepo(Exam.class);
    }

    /**
     *
     * @param tag
     * @param start
     * @param finish
     * @param duration
     * @param space
     * @param depid
     * @return
     * @throws ParseException
     */
    @Override
    public Response createExam(String tag, String start,
            String finish, Integer duration,
            Integer space, Long depid
    ) throws ParseException {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = simpleDateFormat.parse(tag);

            LocalTime st = LocalTime.parse(start);
            Time time1 = Time.valueOf(st);

            LocalTime end = LocalTime.parse(finish);
            Time time2 = Time.valueOf(end);

            Exam exam = new Exam(date, duration, time1, time2, space);

            this.exrepo.create(exam);

            return Response
                    .status(Response.Status.CREATED).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

//    @POST
//    @Override
//        @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public void create(Exam entity) {
//        super.create(entity);
//    }
    @PUT
    @Path("update/{ID}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateExam(@PathParam("ID") Long ID ,@QueryParam("newID") Long newID) {
        try {
            Exam exam = this.exrepo.find(newID);
            this.exrepo.edit(exam);
            return Response
                    .status(Response.Status.CREATED)//.build();
                    .entity(this.jsonb.toJson(exam)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

//    public void edit(@PathParam("id") Long id, Exam entity) {
//        this.exrepo.edit(entity);
//    }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteExam(@PathParam("id") Long id) {
        try {
            Exam exam = this.exrepo.find(id);
            this.exrepo.remove(exam);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(this.jsonb.toJson(exam)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Exam find(@PathParam("id") Long id
    ) {
        return this.exrepo.find(id);
    }

    @GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Exam> findAll() {
        return this.exrepo.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Exam> findRange(@PathParam("from") Integer from,
            @PathParam("to") Integer to
    ) {
        return this.exrepo.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(this.exrepo.count());
    }

    protected EntityManager getEntityManager() {
        return em;
    }

}
