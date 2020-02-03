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
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public interface CustomerRestInterface {

    @POST
    @Path("student")
    public Customer createStudent(
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("email") String email,
            @QueryParam("login") String login,
            @QueryParam("password") String password);

    @POST
    @Path("dozent")
    public Customer createDozent(
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("email") String email,
            @QueryParam("login") String login,
            @QueryParam("password") String password);

    @POST
    @Path("admin")
    public Customer createAdmin(
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("email") String email,
            @QueryParam("login") String login,
            @QueryParam("password") String password);

    @PUT
    @Path("{login}")
    public Customer updateCustomer(
            @PathParam("login") String login,
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("email") String email,
            @QueryParam("password") String password
    );

    @GET
    @Path("{login}")
    public Customer findCustomerByLogin(@PathParam("login") String login);

    @GET
    @Path("dozents")
    public Response findAllDozents();

    @GET
    @Path("students")
    public Response findAllStudents();

    @GET
    @Path("admins")
    public Response findAllAdmins();

}
