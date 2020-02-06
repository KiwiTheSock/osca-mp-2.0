/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.RESTfulInterfaces;

import java.text.ParseException;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nordm
 */
public interface ExamRestInterface {
    
    
    
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createExam(
            @QueryParam("tag") String tag,
            @QueryParam("start") String start,
            @QueryParam("finish") String finish,
            @QueryParam("duration") Integer duration,
            @QueryParam("space") Integer space,
            @QueryParam("depid") Long depid
    )throws ParseException ;
    
//
//    /**
//     *
//     * @param modulname
//     * @param semester
//     * @return
//     */
//    @PUT
//    @Path("update/{space}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response updateExamSpace(
//            @PathParam("modulname") String modulname,
//            @QueryParam("space") Integer space);
//
//    /**
//     *
//     * @param modulname
//     * @param semester
//     * @return
//     */
//    @PUT
//    @Path("update/{modulname}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response updateExamFinisch(
//            @PathParam("modulname") String modulname,
//            @QueryParam("semester") String semester);
//
//    /**
//     *
//     * @param modulname
//     * @param semester
//     * @return
//     */
//    @PUT
//    @Path("update/{start}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response updateExamStart(
//            @PathParam("modulname") String modulname,
//            @QueryParam("start") Time start);
//
//    /**
//     *
//     * @param modulname
//     * @param semester
//     * @return
//     */
//    @PUT
//    @Path("update/{day}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response updateExamDay(
//            @PathParam("modulname") String modulname,
//            @QueryParam("day") Date day);
//
//    /**
//     *
//     * @param modulname
//     * @param semester
//     * @return
//     */
//    @PUT
//    @Path("update/{duration}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response updateExamDuration(
//            @PathParam("modulname") String modulname,
//            @QueryParam("duration") Integer duration);
//
//    /**
//     *
//     * @param modulname
//     * @return
//     */
//    @DELETE
//    @Path("{modulname}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response deleteExam(
//            @PathParam("modulname") String modulname);
//
//    /**
//     *
//     * @param modulname
//     * @return
//     */
//    @GET
//    @Path("find/{modulname}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response findExam(@PathParam("modulname") String modulname);

}
