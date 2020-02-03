/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.RESTfulInterfaces;

import de.hsos.kbse.osca.mp.entity.Customer;
import de.hsos.kbse.osca.mp.service.AccessType;
import java.util.List;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.persistence.TypedQuery;
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
//@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public interface CustomerRestInterface {

    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @POST
    @Path("student")
    public Response createStudent(
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("email") String email,
            @QueryParam("login") String login,
            @QueryParam("password") String password);

    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @POST
    @Path("dozent")
    public Response createDozent(
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("email") String email,
            @QueryParam("login") String login,
            @QueryParam("password") String password);

    @POST
    @Path("admin")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createAdmin(
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("email") String email,
            @QueryParam("login") String login,
            @QueryParam("password") String password);

    @PUT
    @Path("update/{login}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCustomer(
            @PathParam("login") String login,
            @QueryParam("newLogin") String newLogin,
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("email") String email,
            @QueryParam("password") String password
    );

    @DELETE
    @Path("{login}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteCustomer(
            @PathParam("login") String login);

    /**
     *
     * @param login
     * @return
     */
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @GET
    @Path("login/{login}")
    public Response findCustomerByLogin(@PathParam("login") String login);

    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @GET
    @Path("dozents")
    public Response findAllDozents();

    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @GET
    @Path("students")
    public Response findAllStudents();

    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @GET
    @Path("admins")
    public Response findAllAdmins();

}
