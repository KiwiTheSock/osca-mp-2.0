///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package de.hsos.kbse.osca.mp.RESTfulInterfaces;
//
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
///**
// *
// * @author nordm
// */
//public interface ExamRestInterface {
//    
//    /**
//     *
//     * @param modulname
//     * @param semester
//     * @return
//     */
//    @POST
////    @Path("admin")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response createDepartment(
//            @QueryParam("modulname") String modulname,
//            @QueryParam("semester") String semester);
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
//    public Response updateDepartment(
//            @PathParam("modulname") String modulname,
//            @QueryParam("semester") String semester );
//
//    /**
//     *
//     * @param modulname
//     * @return
//     */
//    @DELETE
//    @Path("{modulname}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response deleteDepartment(
//            @PathParam("modulname") String modulname);
//
//    /**
//     *
//     * @param modulname
//     * @return
//     */
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @GET
//    @Path("find/{modulname}")
//    public Response findDepartment(@PathParam("modulname") String modulname);
//    
//}
